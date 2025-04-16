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

    // 체크박스 클릭 이벤트
    document.querySelectorAll(".joy-q7japm").forEach((checkbox) => {
        checkbox.addEventListener("click", (e) => {
            const switchWrapper = e.target.closest(".flog-div-54");

            const switchRoot = switchWrapper.querySelector(".FeelogSwitch-root");
            const switchTrack = switchWrapper.querySelector(".FeelogSwitch-track");
            const switchThumb = switchWrapper.querySelector(".FeelogSwitch-thumb");

            const isOn = switchRoot.classList.contains("Feelog-checked");

            if (isOn) {
                // ✅ OFF 상태로 전환
                switchRoot.classList.remove("Feelog-checked", "FeelogSwitch-colorPrimary", "joy-1yog6pe");
                switchRoot.classList.add("FeelogSwitch-colorNeutral", "joy-ituqjw");

                switchTrack.classList.remove("Feelog-checked");
                switchThumb.classList.remove("Feelog-checked");
            } else {
                // ✅ ON 상태로 전환
                switchRoot.classList.add("Feelog-checked", "FeelogSwitch-colorPrimary", "joy-1yog6pe");
                switchRoot.classList.remove("FeelogSwitch-colorNeutral", "joy-ituqjw");

                switchTrack.classList.add("Feelog-checked");
                switchThumb.classList.add("Feelog-checked");
            }
        });
    });

    // 변경 내용 저장 버튼 이벤트 :: 서버에서 결과 받아서 뿌리는 거로 변경 필요
    document.querySelector(".joy-2ertzu .flog-button-10").addEventListener("click", (e) => {
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
