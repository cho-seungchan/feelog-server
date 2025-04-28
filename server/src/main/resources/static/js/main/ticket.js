document.addEventListener("DOMContentLoaded", function () {
    const button = document.getElementById("header-dropdown-toggle");
    const menu = document.getElementById("header-dropdown-menu");

    button.addEventListener("click", function (e) {
        e.stopPropagation(); // 바깥 클릭 방지용
        menu.style.display = menu.style.display === "block" ? "none" : "block";
    });

    // 메뉴 밖 클릭 시 닫기
    document.addEventListener("click", function () {
        menu.style.display = "none";
    });

    const trigger = document.querySelector(".nesty-input");
    const panel = document.getElementById("request_issue_type_select");
    const options = panel.querySelectorAll("li");

    // 1. a 태그 클릭 → 패널 열기
    trigger.addEventListener("click", function (e) {
        e.preventDefault();
        panel.style.display =
            panel.style.display === "block" ? "none" : "block";
    });

    // 2. li 항목 클릭 시 → a 안에 텍스트 넣고 닫기
    options.forEach(function (option) {
        option.addEventListener("click", function () {
            const selectedText = option.textContent.trim();

            // a 안 텍스트 변경
            trigger.textContent = selectedText;

            // 숨겨진 input에 선택한 값 넣기
            document.getElementById("request_subject").value = selectedText;

            // aria-selected 갱신
            options.forEach((opt) =>
                opt.setAttribute("aria-selected", "false")
            );
            option.setAttribute("aria-selected", "true");

            // 클래스도 갱신 (선택된 항목 표시용)
            options.forEach((opt) => opt.classList.remove("nesty-selected"));
            option.classList.add("nesty-selected");

            // 닫기
            panel.style.display = "none";
        });
    });

    // 3. 외부 클릭 시 패널 닫기
    document.addEventListener("click", function (e) {
        if (!trigger.contains(e.target) && !panel.contains(e.target)) {
            panel.style.display = "none";
        }
    });

    const form = document.getElementById("new_request");
    const agreeCheckbox = document.getElementById("request_custom_fields_360009496894");

    form.addEventListener("submit", function (e) {
        if (!agreeCheckbox.checked) {
            e.preventDefault();
            alert("개인정보 수집·이용에 동의해주셔야 제출할 수 있습니다.");
        }
    });
});
