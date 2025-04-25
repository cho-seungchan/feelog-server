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

    document.body.addEventListener("click", (e) => {

        // 2025.04.25 조승찬
        // 좋아요 버튼 클릭시
        if (e.target.closest(".flog-button-32")) {
            // 좋아요 id 값
            const id = e.target.closest(".flog-div-137").getAttribute("data-id");
            const postId = e.target.closest(".flog-div-137").getAttribute("data-postId");
            // 좋아요 숫자 증가
            // let likeCount = parseInt(e.target.closest(".flog-div-132").querySelector(".like-count").textContent, 10) || 0;
            // e.target.closest(".flog-div-132").querySelector(".like-count").textContent = likeCount + 1;

            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".flog-button-32");
            const newLikeButtonHTML = `
                <button type="button" aria-label="좋아요 버튼" class="FeelogButton-root FeelogButton-variantPlain FeelogButton-colorNeutral FeelogButton-sizeSm flog-button-39">
                    <span class="FeelogButton-startDecorator flog-span-42">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-22">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>
                </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;

            storageOnLike(id, postId);
        }

        // 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".flog-button-39")) {
            // 좋아요 id 값
            const id = e.target.closest(".flog-div-137").getAttribute("data-id");
            const postId = e.target.closest(".flog-div-137").getAttribute("data-postId");
            // 좋아요 숫자 감소
            // let likeCount = parseInt(e.target.closest(".flog-div-132").querySelector(".like-count").textContent, 10) || 0;
            // e.target.closest(".flog-div-132").querySelector(".like-count").textContent = likeCount - 1;

            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".flog-button-39");
            const newLikeButtonHTML = `
                    <button type="button" aria-label="좋아요 버튼" class="FeelogButton-root FeelogButton-variantPlain FeelogButton-colorNeutral FeelogButton-sizeSm flog-button-32">
                        <span class="FeelogButton-startDecorator flog-span-41">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-19">
                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                            </svg>
                        </span>
                    </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;

            storageOffLike(id, postId);
        }

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
