//  2025.04.06 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(메세지 목록, 이용자차단) 클릭 이벤트
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

    // 차단해제 버튼 이벤트
    document.querySelectorAll(".flog-button-12").forEach((button, inded) => {
        button.addEventListener("click", (e) => {
            e.preventDefault();
            document.querySelector(".flog-div-40").style.display = "block";
        });
    });
    // 모달창 네, 아니오, x 버튼 클릭 이벤트
    document.querySelectorAll(".flog-svg-6, .flog-button-10, .flog-a-13").forEach((button) => {
        button.addEventListener("click", (e) => {
            // 차단해제 클릭 후 모달창에서 '네' 클릭시 :: 서버에 갔다온 뒤 뿌리는 거로 수정
            if (e.target.textContent.trim() == "네") {
                // 차단해제 alert 창 막기
                document.querySelector('[aria-labelledby=":r0:"]').style.display = "none";
                // 처리결과 alert 창 열기
                document.querySelector('[aria-labelledby=":r4:"]').style.display = "block";
            } else {
                document.querySelector(".flog-div-40").style.display = "none";
                // 원상 복구
                document.querySelector('[aria-labelledby=":r0:"]').style.display = "block";
                document.querySelector('[aria-labelledby=":r4:"]').style.display = "none";
            }
        });
    });
});
