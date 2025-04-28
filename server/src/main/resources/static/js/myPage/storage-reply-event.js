//  2025.04.07 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(스크랩, 좋아요, 댓글) 클릭 이벤트
    document.querySelectorAll(".FeelogListItem-variantPlain").forEach((title) => {
        title.addEventListener("click", (e) => {
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

    // 2025.04.25 조승찬 :: 삭제 버튼 클릭시 모달창 열기
    let id;          // 댓글 삭제시 서버에 보낼 아이디
    let page;       //  댓글 삭제 후 돌아올 페이지
    document.querySelectorAll(".flog-button-20").forEach((button, inded) => {
        button.addEventListener("click", (e) => {
            e.preventDefault();
            // 댓글 삭제할 아이디, 댓글 삭제 후 돌아올 페이지
            id = e.target.closest(".flog-button-20").getAttribute('data-id')
            page = e.target.closest(".flog-button-20").getAttribute('data-page');
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
                form.action = '/myPage/storage-delete-reply';

                // hidden input 생성 및 값 설정
                const idInput = document.createElement('input');
                idInput.type = 'hidden';
                idInput.name = 'id';
                idInput.value = id;
                form.appendChild(idInput);

                const pageInput = document.createElement('input');
                pageInput.type = 'hidden';
                pageInput.name = 'page';
                pageInput.value = page;
                form.appendChild(pageInput);

                // form을 body에 추가 후 제출
                document.body.appendChild(form);
                console.log("id and page :: "+id+" "+page);
                form.submit();

            } else {
                document.querySelector(".flog-div-40").style.display = "none";
                // 원상 복구
                document.querySelector('[aria-labelledby=":r0:"]').style.display = "block";
                document.querySelector('[aria-labelledby=":r4:"]').style.display = "none";
            }
        });
    });

    // 2025.04.25  조승찬 ::  페이징 처리
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            e.preventDefault(); // 기본 동작 방지
            document.querySelector(".StorageList").value = pageValue;
            document.forms["pageForm"].submit(); // 폼 제출
        }
    });
    // 2025.04.25  조승찬 ::  페이징 처리
});
