//  2025.04.20 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(오늘, 주간, 월간 첼린지) 클릭 이벤트
    document.querySelectorAll(".FeelogListItem-variantPlain").forEach((title) => {
        title.addEventListener("click", (e) => {
            e.preventDefault();
            // 타이틀이 새로 선택되면 타이틀 진하게, 밑줄 표현 되도록 수정
            if (!e.target.classList.contains("selected")) {
                // 기존에 선택되었던 타이틀 원상 복귀
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.add("flog-p-4");
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.remove("flog-p-5");
                document.querySelector(".selected.flog-a-7").classList.add("flog-a-6");
                document.querySelector(".selected.flog-a-7").classList.remove("selected", "flog-a-7");
                // 클릭된 타이틀에 진하게, 밑줄 표현 클래스명 추가
                e.target.closest(".FeelogListItem-variantPlain").querySelector(".flog-p-4").classList.add("flog-p-5");
                e.target
                    .closest(".FeelogListItem-variantPlain")
                    .querySelector(".flog-p-4")
                    .classList.remove("flog-p-4");
                e.target.closest(".FeelogListItem-variantPlain").classList.add("selected", "flog-a-7");
                e.target.closest(".FeelogListItem-variantPlain").classList.remove("flog-a-6");
            }
        });
    });

    // 도전 클릭 이벤트
    document.querySelectorAll(".challenge-member-task").forEach(task => {
        task.addEventListener("click", e => {

            e.preventDefault();
            const taskId = e.target.getAttribute('data-taskid');
            let id = '0';
            if (e.target.getAttribute("data-challengeid")) {
                id = e.target.getAttribute("data-challengeid");
            }
            const requestBody = {id: id, taskId: taskId};
            if (e.target.classList.contains("selected")) {
                e.target.classList.remove("selected");
                e.target.textContent = '도전';
                // 완료 버튼 숨기기
                e.target.closest(".button-conatiner").querySelector(".complete-member-task").style.display = "none";
                cancelMemberTask(requestBody);
            } else {
                e.target.classList.add("selected");
                e.target.textContent = '중단';
                // 완료 버튼 보이기
                e.target.closest(".button-conatiner").querySelector(".complete-member-task").style.display = "block";
                selectMemberTask(requestBody);
            }
        });
    });

    document.querySelectorAll(".challenge-common-task").forEach(task => {
        task.addEventListener("click", e => {

            e.preventDefault();
            const taskId = e.target.getAttribute('data-taskid');
            let id = '0';
            if (e.target.getAttribute("data-challengeid")) {
                id = e.target.getAttribute("data-challengeid");
            }
            const requestBody = { id: id, taskId: taskId };
            if (e.target.classList.contains("selected")) {
                e.target.classList.remove("selected");
                e.target.textContent = '도전';
                // 완료 버튼 숨기기
                e.target.closest(".button-conatiner").querySelector(".complete-common-task").style.display = "none";
                cancelCommonTask(requestBody);
            } else {
                e.target.classList.add("selected");
                e.target.textContent = '중단';
                // 완료 버튼 보이기
                e.target.closest(".button-conatiner").querySelector(".complete-common-task").style.display = "block";
                selectCommonTask(requestBody);
            }
        });
    });
    // 도전 클릭 이벤트

    // 2025.04.22  조승찬 ::  페이징 처리
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            e.preventDefault(); // 기본 동작 방지
            document.querySelector(".challengeList").value = pageValue;
            document.forms["pageForm"].submit(); // 폼 제출
        }
    });
    // 2025.04.22  조승찬 ::  페이징 처리

});
