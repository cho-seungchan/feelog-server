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
    document.querySelectorAll(".flog-input-5").forEach((checkbox) => {
        checkbox.addEventListener("click", (e) => {
            const switchWrapper = e.target.closest(".flog-div-54");

            const switchRoot = switchWrapper.querySelector(".FeelogSwitch-root");
            const switchTrack = switchWrapper.querySelector(".FeelogSwitch-track");
            const switchThumb = switchWrapper.querySelector(".FeelogSwitch-thumb");

            const isOn = switchRoot.classList.contains("Feelog-checked");

            if (isOn) {
                // ✅ OFF 상태로 전환
                switchRoot.classList.remove("Feelog-checked", "FeelogSwitch-colorPrimary", "flog-div-111");
                switchRoot.classList.add("FeelogSwitch-colorNeutral", "flog-div-000");

                switchTrack.classList.remove("Feelog-checked");
                switchThumb.classList.remove("Feelog-checked");
            } else {
                // ✅ ON 상태로 전환
                switchRoot.classList.add("Feelog-checked", "FeelogSwitch-colorPrimary", "flog-div-111");
                switchRoot.classList.remove("FeelogSwitch-colorNeutral", "flog-div-000");

                switchTrack.classList.add("Feelog-checked");
                switchThumb.classList.add("Feelog-checked");
            }
        });
    });

    // 변경 내용 저장 버튼 이벤트 :: 서버에서 결과 받아서 뿌리는 거로 변경 필요
    // 2025.04.22  조승찬 :: 변경사항 저장 버튼 클릭시
    document.querySelector(".flog-div-113 .flog-button-10").addEventListener("click", e=>{

        e.preventDefault();

        // 포스트 댓글 알림
        if (document.querySelector(".memberNotificationPostReply").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationPostReply']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationPostReply']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationPostReply']").value);

        // 포스트 댓글 좋아요 알림
        if (document.querySelector(".memberNotificationPostReplyLike").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationPostReplyLike']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationPostReplyLike']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationPostReplyLike']").value);

        // 포스트 좋아요 알림
        if (document.querySelector(".memberNotificationPostLike").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationPostLike']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationPostLike']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationPostLike']").value);

        // 구독 알림
        if (document.querySelector(".memberNotificationSubscribe").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationSubscribe']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationSubscribe']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationSubscribe']").value);

        // 커뮤니티 새글 알림
        if (document.querySelector(".memberNotificationCommunityPost").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationCommunityPost']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationCommunityPost']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationCommunityPost']").value);

        // 커뮤니티 새글 알림
        if (document.querySelector(".memberNotificationMessage").classList.contains("Feelog-checked")) {
            document.querySelector("input[name='memberNotificationMessage']").value = "설정";
        } else {
            document.querySelector("input[name='memberNotificationMessage']").value = "해제";
        }
        console.log(document.querySelector("input[name='memberNotificationMessage']").value);

        document.querySelector(".flog-form-2").submit();
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
