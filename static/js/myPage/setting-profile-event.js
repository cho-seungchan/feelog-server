/* 2025.04.05 조승찬 */

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(프로필, 알림) 클릭 이벤트
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

    // 파일 첨부 처리
    // 1. 버튼 클릭 → input 클릭 트리거(클릭된 것으로 처리)
    document.querySelector(".flog-button-14").addEventListener("click", () => {
        document.getElementById(":R359uuukv9ul7rlqakq:").click();
    });

    // 2. 파일 선택 → 파일명 표시
    document.getElementById(":R359uuukv9ul7rlqakq:").addEventListener("change", () => {
        if (document.getElementById(":R359uuukv9ul7rlqakq:").files.length > 0) {
            document.querySelector(".flog-p-17").textContent = `${
                document.getElementById(":R359uuukv9ul7rlqakq:").files[0].name
            }`;
        } else {
            document.querySelector(".flog-p-17").textContent = "파일을 선택해 주세요.";
        }
    });

    // 닉네임과 자기소개 포커스 색상 변경 처리
    document.querySelector("#\\:R559uuukv9ul7rlqakq\\:").addEventListener("focus", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R559uuukv9ul7rlqakq\\:").addEventListener("blur", (e) => {
        e.target.closest(".flog-div-83").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R759uuukv9ul7rlqakq\\:").addEventListener("focus", (e) => {
        e.target.closest(".flog-div-118").classList.toggle("Feelog-focused");
    });
    document.querySelector("#\\:R759uuukv9ul7rlqakq\\:").addEventListener("blur", (e) => {
        e.target.closest(".flog-div-118").classList.toggle("Feelog-focused");
    });

    //  버튼 이벤트 :: 서버에서 결과 받아서 뿌리는 거로 변경 필요
    document.querySelector(".flog-div-119 .flog-button-10").addEventListener("click", (e) => {
        e.preventDefault();
        document.querySelector(".flog-div-40").style.display = "block";
    });

    // 모달창 확인, x 버튼 클릭 이벤트
    document.querySelector(".flog-div-44 .flog-button-10").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
    document.querySelector(".flog-svg-6").addEventListener("click", (e) => {
        document.querySelector(".flog-div-40").style.display = "none";
    });
});
