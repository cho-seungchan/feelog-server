//  2025.04.06 조승찬

document.addEventListener("DOMContentLoaded", () => {
    let channelId;  // 구독 취소시 서버에 보낼 아이디
    let page;       // 구독 취소 후 돌아올 페이지
    // 구독중 버튼 이벤트
    document.querySelectorAll(".flog-button-13").forEach((button, inded) => {
        button.addEventListener("click", (e) => {
            e.preventDefault();
            // 구독 취소할 아이디, 취소 후 돌아올 페이지
            channelId = e.target.getAttribute('data-channelId');
            memberId = e.target.getAttribute('data-memberId')
            page = e.target.getAttribute('data-page');
            document.querySelector(".flog-div-40").style.display = "block";
        });
    });

    // 모달창 네, 아니오, x 버튼 클릭 이벤트
    document.querySelectorAll(".flog-svg-6, .flog-button-10, .flog-a-13").forEach((button) => {
        button.addEventListener("click", (e) => {
            // 차단해제 클릭 후 모달창에서 '네' 클릭시 ::
            if (e.target.textContent.trim() == "네") {
                // GET 방식으로 전송 시
                // window.location.href = `/myPage/notify-cancel-subscribe?channelId=${channelId}&memberId=${memberId}&page=${page}`;
                // 동적으로 form 생성
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '/myPage/notify-cancel-subscribe';

                // hidden input 생성 및 값 설정
                const memberIdInput = document.createElement('input');
                memberIdInput.type = 'hidden';
                memberIdInput.name = 'memberId';
                memberIdInput.value = memberId;
                form.appendChild(memberIdInput);

                const pageInput = document.createElement('input');
                pageInput.type = 'hidden';
                pageInput.name = 'page';
                pageInput.value = page;
                form.appendChild(pageInput);

                // form을 body에 추가 후 제출
                document.body.appendChild(form);
                form.submit();

            } else {
                document.querySelector(".flog-div-40").style.display = "none";
                // 원상 복구
                document.querySelector('[aria-labelledby=":r0:"]').style.display = "block";
                document.querySelector('[aria-labelledby=":r4:"]').style.display = "none";
            }
        });
    });

    // 2025.04.24  조승찬 ::  페이징 처리
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            e.preventDefault(); // 기본 동작 방지
            document.querySelector(".SubscriptList").value = pageValue;
            document.forms["pageForm"].submit(); // 폼 제출
        }
    });
    // 2025.04.24  조승찬 ::  페이징 처리
});
