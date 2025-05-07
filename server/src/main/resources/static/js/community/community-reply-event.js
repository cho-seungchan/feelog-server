// 2025.04.12 조승찬
document.addEventListener("DOMContentLoaded", () => {

    const currentChannelUrl = document.querySelector(".flog-div-189").getAttribute("data-currentChannelUrl");
    const allFiles = [];  // 파일 추가시 기존 파일 유지되게 하기 위한 배열
    let   postId, id;           // 수정 삭제할 포스트 아이디와 replyId
    document.body.addEventListener("click", (e) => {

        // 이미지 이전 버튼 클릭시
        if (e.target.closest(".prev-button")) {
            // 부모와 총 이미지 수 찾기
            const container = e.target.closest(".slider-container");
            // 움직일 판 찾기
            const slider = container.querySelector(".slider");
            // 현재 이미지 인덱스 찾기
            const currentActive = container.querySelector(".swiper-slide.active");
            let currentIndex = parseInt(currentActive.getAttribute("data-index"), 10);

            // 액티브 해제
            currentActive.classList.remove("active");

            currentIndex -= 1;
            // 판 움직이기
            if (window.innerWidth < 744) {
                slider.style.transform = `translate3d(-${(currentIndex - 1) * 330}px, 0, 0)`;
            } else {
                slider.style.transform = `translate3d(-${(currentIndex - 1) * 510}px, 0, 0)`;
            }

            // 액티브 인덱스 새로 지정하기
            const nextActive = container.querySelector(`.slider div[data-index="${currentIndex}"]`);
            nextActive.classList.add("active");

            // 이전 이미지가 없을 경우 이전 버튼 없애기
            if (currentIndex == 1) {
                e.target.closest(".prev-button").classList.add("swiper-button-disabled");
            }
            // 이후 버튼 보이게 하기
            if (
                e.target
                    .closest(".slider-container")
                    .querySelector(".next-button")
                    .classList.contains("swiper-button-disabled")
            ) {
                e.target
                    .closest(".slider-container")
                    .querySelector(".next-button")
                    .classList.remove("swiper-button-disabled");
            }
        }

        // 이미지 이후 버튼 클릭시
        if (e.target.closest(".next-button")) {
            // 부모와 총 이미지 수 찾기
            const container = e.target.closest(".slider-container");
            const totalSlides = parseInt(container.getAttribute("data-total"), 10);
            // 움직일 판 찾기
            const slider = container.querySelector(".slider");
            // 현재 이미지 인덱스 찾기
            const currentActive = container.querySelector(".swiper-slide.active");
            let currentIndex = parseInt(currentActive.getAttribute("data-index"), 10);

            // 액티브 해제
            currentActive.classList.remove("active");

            // 판 움직이기
            if (window.innerWidth < 744) {
                slider.style.transform = `translate3d(-${currentIndex * 330}px, 0, 0)`;
            } else {
                slider.style.transform = `translate3d(-${currentIndex * 510}px, 0, 0)`;
            }

            currentIndex += 1;
            // 액티브 인덱스 새로 지정하기
            const nextActive = container.querySelector(`.slider div[data-index="${currentIndex}"]`);
            nextActive.classList.add("active");

            // 이후 이미지가 없을 경우 이후 버튼 없애기
            if (currentIndex == totalSlides) {
                e.target.closest(".next-button").classList.add("swiper-button-disabled");
            }
            // 이전 버튼 보이게 하기
            if (
                e.target
                    .closest(".slider-container")
                    .querySelector(".prev-button")
                    .classList.contains("swiper-button-disabled")
            ) {
                e.target
                    .closest(".slider-container")
                    .querySelector(".prev-button")
                    .classList.remove("swiper-button-disabled");
            }
        }

        // 케밥 버튼 클릭시
        if (e.target.closest(".flog-button-31")) {
            e.preventDefault();

            const kebabButton = e.target.closest(".flog-button-31");

            // 이미 열려 있는 버튼을 눌렀을 경우 기존 모달창 삭제 후 종료
            if (kebabButton.classList.contains("expanded")) {
                document.querySelector(".flog-ul-8").remove();
                kebabButton.classList.remove("expanded");
                return;
            }

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".flog-ul-8")?.remove();

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".flog-button-31.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });

            // 메뉴 요소 생성
            const menuElement = document.createElement("ul");
            menuElement.setAttribute("role", "menu");
            menuElement.setAttribute("tabindex", "-1");
            menuElement.setAttribute("id", ":r2c:");
            menuElement.setAttribute(
                "class",
                "base-Popper-root FeelogMenu-root Feelog-expanded FeelogMenu-variantPlain FeelogMenu-colorNeutral FeelogMenu-sizeMd flog-ul-8"
            );

            // 케밥 버튼 위치 기준으로 메뉴 위치 계산
            const rect = kebabButton.getBoundingClientRect();
            const top = rect.bottom + window.scrollY;
            const left = rect.right + window.scrollX - 100;

            menuElement.setAttribute(
                "style",
                `position: absolute; top: ${top}px; left: ${left}px; margin: 0px; transform: none;`
            );
            menuElement.setAttribute("data-popper-placement", "bottom-end");

            menuElement.innerHTML = `
                <li
                    tabindex="0"
                    id=":r3r:"
                    role="menuitem"
                    data-first-child=""
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-13"
                >
                    글 수정
                </li>
                <li
                    tabindex="-1"
                    id=":r3t:"
                    role="menuitem"
                    data-last-child=""
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-14"
                >
                    글 삭제
                </li>`;

            document.body.appendChild(menuElement);
            kebabButton.classList.add("expanded");

            // 서버로 보낼 포스트 아이지 저장
            postId = e.target.closest(".flog-button-31").getAttribute("data-postId");

        }

        // 모달창 x, 취소, 게시 버튼 클릭시 모달 없애고, 헤드 z-index 500으로 원복
        if (
            e.target.closest(".flog-button-36") ||
            e.target.classList.contains("post") ||
            e.target.classList.contains("cancel")
        ) {
            document.querySelector(".flog-div-176").style.display = "none";
            // document.querySelector(".feelog-header-mainDiv").style.zIndex = "10000";
            // document.querySelector(".flog-nav-6").style.zIndex = "500";
        }

        // 글 수정 버튼 클릭시
        if (e.target.classList.contains("flog-li-13")) {
            document.querySelector(".flog-div-176").style.display = "block";
            // document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            // document.querySelector(".flog-nav-6").style.zIndex = "100";

            // 첨부 파일 보관 배열 클리어
            allFiles.splice(0, allFiles.length);

            // 2025.04.26 조승찬 :: rest controller 방식으로 조회 요청
            communityPostRead(postId, currentChannelUrl);
        }

        // 2025.04.28 조승찬 :: 글 삭제 버튼 클릭시
        if (e.target.classList.contains("flog-li-14")) {

            // 확인 모달창 생성
            conformModal = document.createElement("div");
            conformModal.classList.add("FeelogModal-root", "flog-div-40");
            conformModal.innerHTML = `
                    <div aria-hidden="true" open="" class="FeelogModal-backdrop flog-div-41"></div>
                    <div tabindex="0" data-testid="sentinelStart"></div>
                    <div
                        role="dialog"
                        aria-modal="true"
                        aria-labelledby=":r2b:"
                        aria-describedby=":r2c:"
                        tabindex="-1"
                        class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
                    >
                        <h2 id=":r2b:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
                            확인
                        </h2>
                        <button
                            aria-label="닫기"
                            class="FeelogModalClose-root FeelogModalClose-variantPlain FeelogModalClose-colorNeutral FeelogModalClose-sizeMd flog-button-9"
                            type="button"
                        >
                            <svg
                                focusable="false"
                                aria-hidden="true"
                                viewBox="0 0 24 24"
                                data-testid="CloseIcon"
                                class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-6"
                            >
                                <path d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path>
                            </svg>
                        </button>
                        <div id=":r2c:" class="FeelogDialogContent-root flog-div-43">
                            해당 글을 삭제할까요?
                        </div>
                        <div data-last-child="" class="FeelogDialogActions-root flog-div-44">
                            <button
                                class="FeelogButton-root FeelogButton-variantSolid FeelogButton-colorPrimary FeelogButton-sizeMd flog-button-10"
                                type="button"
                            >
                                네
                            </button>
                            <button
                                class="FeelogButton-root FeelogButton-variantSoft FeelogButton-colorNeutral FeelogButton-sizeMd flog-a-13"
                                type="button"
                            >
                                아니요
                            </button>
                        </div>
                    </div>
                    <div tabindex="0" data-testid="sentinelEnd"></div>`;

            // 확인 모달창 삽입
            document.body.appendChild(conformModal);


            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".flog-ul-8")?.remove();

            // 첨부 파일 보관 배열 클리어
            allFiles.splice(0, allFiles.length);

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".flog-button-31.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });

        }

        // 모달창(메세지창 포함) 의 네, 아니요, x 버튼 클릭시  이벤트  :: 모달창 삭제
        if (e.target.closest(".flog-svg-6") || e.target.closest(".flog-div-44")) {
            // 클릭된 모달화면 전체 삭제
            if (e.target.closest(".FeelogModal-root.flog-div-40").style.display) {
                document.querySelector(".FeelogModal-root.flog-div-40").style.display = "none";
            } else {
                e.target.closest(".FeelogModal-root.flog-div-40").remove();
            }

            // 열려있는 케밥 메뉴 삭제
            if (document.querySelector(".flog-ul-2")) {
                document.querySelector(".flog-ul-2").remove();
                document.querySelector(".flog-button-18").classList.remove("expanded");
            }
        }

        // 2025.04.28 조승찬 파일 첨부 처리
        // 모달창 이미지 추가 버튼 클릭시
        if (e.target.closest(".flog-button-37")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";
            input.multiple = true;

            // 최대 5개 :: <ul class="FeelogStack-root flog-ul-7"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (e) => {

                // 파일 추가시 기존 파일에 추가되는 형식으로 모여서 서버로 전송
                const files = e.target.files;

                Array.from(files).forEach(newFile => {
                    // 중복이 있는지 확인하고 없을 때 추가
                    const isDup = allFiles.some(existingFile => {
                        return existingFile.name == newFile.name && existingFile.size == newFile.size;
                    });

                    if (!isDup) { // 중복이 없을 때만 추가
                        if (allFiles.length >= 5) {
                            alert("최대 5개의 이미지만 업로드할 수 있습니다.");
                            return;
                        }  else {
                            allFiles.push(newFile);
                        }
                    }
                });

                const formData = new FormData();
                allFiles.forEach( file => {
                    formData.append("files", file);
                });

                // 서버로 전송하여 path와 썸네일 생성
                inputFileUpload(formData);

            });

            input.click();
        }

        // 새로 생성된 썸네일 삭제버튼 클릭시
        // 신규 추가된 파일이 추가될 수 있도록 기존 배열에서 삭제처리
        if (e.target.className == "file-cancel"){
            const index = e.target.closest("li").dataset.index; // 클릭된곳의 인덱스 찾아오기
            allFiles.splice(index, 1); // 배열에서 파일 제거
            e.target.closest(".uploadFile").remove()
        }

        //2025.04.28 조승찬 :: 게시 클릭시 서버로 전송
        if (e.target.classList.contains("post")) {

            e.preventDefault(); // 폼 제출 방지

            let text =``;
            document.querySelectorAll(".uploadFile").forEach((li, index) => {
                text += `
	            <input type="hidden" name="files[${index}].fileName" value="${li.dataset.fileName}">
	            <input type="hidden" name="files[${index}].filePath" value="${li.dataset.filePath}">
			 `;
            });

            const form = document.querySelector('[name="uploadFile-form"]');
            form.insertAdjacentHTML("beforeend", text)
            // URL 동적 설정
            form.setAttribute('action', `/feelog.com/@${currentChannelUrl}/community-update`);
            // id를 postVO id로 보내기 위해 input 생성
            hiddenInput = document.createElement('input');
            hiddenInput.setAttribute('type', 'hidden');
            hiddenInput.setAttribute('name', 'id');
            hiddenInput.setAttribute('value', postId); // 바로 설정
            form.appendChild(hiddenInput);
            // 서버 전송
            form.submit();
        }
        //2025.04.28 조승찬 :: 게시 클릭시 서버로 전송

        // 2025.04.28 조승찬 :: 글 삭제 버튼 클릭시
        if (e.target.classList.contains("flog-button-10")) {
            // communityPostDelete(postId, currentChannelUrl)
            window.location.href = `/feelog.com/@${currentChannelUrl}/community-delete/${postId}`;
        }
        // 2025.04.28 조승찬 :: 글 삭제 버튼 클릭시

        // 2025.04.28 조승찬 :: 포스트 좋아요 버튼 클릭시
        if (e.target.closest(".flog-div-168 .flog-button-32")) {
            // 아이디 저장
            postId = e.target.closest(".flog-div-168 .flog-button-32").getAttribute("data-postId");

            // 변경할 span 위치 설정
            const currntButton = e.target.closest(".flog-div-168 .flog-button-32");
            const currentSpan  = e.target.closest(".flog-div-168 .flog-button-32").querySelector(".flog-span-41");
            // div 클래스명 변경
            currntButton.classList.add("flog-button-39");
            currntButton.classList.remove("flog-button-32");

            // 기존 버튼 및 다른 요소들 선택
            const newLikeSpanHTML = `
                    <span class="FeelogButton-startDecorator flog-span-42">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-22">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>`;

            // 기존 버튼 교체
            currentSpan.outerHTML = newLikeSpanHTML;

            // 서버 호출
            postCommunityPostLike(postId, currentChannelUrl);
        }

        // 2025.04.28 조승찬 :: 포스트 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".flog-div-168 .flog-button-39")) {
            // 아이디 저장
            postId = e.target.closest(".flog-div-168 .flog-button-39").getAttribute("data-postId");

            // 변경할 span 위치 설정
            const currntButton = e.target.closest(".flog-div-168 .flog-button-39");
            const currentSpan  = e.target.closest(".flog-div-168 .flog-button-39").querySelector(".flog-span-42");
            // div 클래스명 변경
            currntButton.classList.add("flog-button-32");
            currntButton.classList.remove("flog-button-39");

            const newLikeSpanHTML = `
                       <span class="FeelogButton-startDecorator flog-span-41">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-19">
                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                            </svg>
                        </span>`;

            // 기존 버튼 교체
            currentSpan.outerHTML = newLikeSpanHTML;

            // 서버 호출
            cancelCommunityPostLike(postId, currentChannelUrl);
        }

        // 2025.04.29 조승찬 :: 포스트 댓글 좋아요 버튼 클릭시
        if (e.target.closest(".flog-div-209 .flog-button-32")) {
            // 아이디 저장
            replyId = e.target.closest(".flog-div-209 .flog-button-32").getAttribute("data-replyId");

            // 변경할 span 위치 설정
            const currntButton = e.target.closest(".flog-div-209 .flog-button-32");
            const currentSpan  = e.target.closest(".flog-div-209 .flog-button-32").querySelector(".flog-span-41");
            // div 클래스명 변경
            currntButton.classList.add("flog-button-39");
            currntButton.classList.remove("flog-button-32");

            // 기존 버튼 및 다른 요소들 선택
            const newLikeSpanHTML = `
                    <span class="FeelogButton-startDecorator flog-span-42">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-22">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>`;

            // 기존 버튼 교체
            currentSpan.outerHTML = newLikeSpanHTML;

            // 서버 호출
            postCommunityPostReplyLike(replyId, currentChannelUrl);
        }

        // 2025.04.29 조승찬 :: 포스트 댓글 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".flog-div-209 .flog-button-39")) {
            // 아이디 저장
            replyId = e.target.closest(".flog-div-209 .flog-button-39").getAttribute("data-replyId");

            // 변경할 span 위치 설정
            const currntButton = e.target.closest(".flog-div-209 .flog-button-39");
            const currentSpan  = e.target.closest(".flog-div-209 .flog-button-39").querySelector(".flog-span-42");
            // div 클래스명 변경
            currntButton.classList.add("flog-button-32");
            currntButton.classList.remove("flog-button-39");

            const newLikeSpanHTML = `
                       <span class="FeelogButton-startDecorator flog-span-41">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-19">
                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                            </svg>
                        </span>`;

            // 기존 버튼 교체
            currentSpan.outerHTML = newLikeSpanHTML;

            // 서버 호출
            cancelCommunityPostReplyLike(replyId, currentChannelUrl);
        }

        // 2025.04.28  조승찬 ::   포스트 신고 버튼 클릭
        if (e.target.closest(".flog-div-168 .report-button")) {
            // 아이디 저장
            postId = e.target.closest(".flog-div-168 .report-button").getAttribute("data-postId");

            if (e.target.closest(".flog-div-168 .report-button").classList.contains("white")) {
                e.target.closest(".flog-div-168 .report-button").classList.remove("white");
                e.target.closest(".flog-div-168 .report-button").classList.add("red");
                // 신고 저장 호출
                postCommunityPostReport(postId, currentChannelUrl);
            } else if (e.target.closest(".flog-div-168 .report-button").classList.contains("red")) {
                e.target.closest(".flog-div-168 .report-button").classList.remove("red");
                e.target.closest(".flog-div-168 .report-button").classList.add("white");
                // 신고 취소 호출
                cancelCommunityPostReport(postId, currentChannelUrl);
            }
        }

        // 2025.04.29  조승찬 ::  포스트 댓글 신고 버튼 클릭
        if (e.target.closest(".flog-div-209 .report-button")) {
            // 아이디 저장
            replyId = e.target.closest(".flog-div-209 .report-button").getAttribute("data-replyId");

            if (e.target.closest(".flog-div-209 .report-button").classList.contains("white")) {
                e.target.closest(".flog-div-209 .report-button").classList.remove("white");
                e.target.closest(".flog-div-209 .report-button").classList.add("red");
                // 신고 저장 호출
                postCommunityPostReplyReport(replyId, currentChannelUrl);
            } else if (e.target.closest(".flog-div-209 .report-button").classList.contains("red")) {
                e.target.closest(".flog-div-209 .report-button").classList.remove("red");
                e.target.closest(".flog-div-209 .report-button").classList.add("white");
                // 신고 취소 호출
                cancelCommunityPostReplyReport(replyId, currentChannelUrl);
            }
        }

        // 댓글 이미지 추가 버튼 클릭시
        if (e.target.closest(".flog-button-41")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";

            // 최대 1개 :: <ul class="FeelogStack-root flog-ul-7"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (e) => {

                const file = e.target.files[0];

                // multipart/form-data 형식으로 데이터를 자동 처리
                const formData = new FormData();
                formData.append("file", file);
                // 파일 한개만 서버로 전송하여 path와 썸네일 생성
                inputFileUploadOne(formData);

            });

            input.click();
        }

        // 2025.04.28 조승찬 :: 댓글 등록 버튼 처리
        if (e.target.classList.contains("flog-button-42")){

            e.preventDefault(); // 폼 제출 방지

            const form = document.querySelector('[name="communityPostReply-form"]');

            if (document.querySelector(".uploadFile-container2").querySelector(".uploadFile"))  {
                // 첨부 파일을 input 태그에 저장
                const filePath = document.querySelector(".uploadFile-container2").querySelector(".uploadFile").getAttribute("data-file-path");
                let pathInput = document.createElement('input');
                pathInput.setAttribute('type', 'hidden');
                pathInput.setAttribute('name', 'replyFilePath');
                pathInput.setAttribute('value', filePath); // 바로 설정
                form.appendChild(pathInput);
                const fileName = document.querySelector(".uploadFile-container2").querySelector(".uploadFile").getAttribute("data-file-name");
                let nameInput = document.createElement('input');
                nameInput.setAttribute('type', 'hidden');
                nameInput.setAttribute('name', 'replyFileName');
                nameInput.setAttribute('value', fileName); // 바로 설정
                form.appendChild(nameInput);

            }

            // 댓글 확인 후 서버 전송
            // form.submit();
            const content = document.querySelector(".flog-textarea-6").value;
            handleSubmit(content, form);
        }


        // 2025.04.29 조승찬 :: 댓글 삭제 버튼 클릭시
        if (e.target.closest(".flog-button-20")) {

            // 확인 모달창 생성
            conformModal = document.createElement("div");
            conformModal.classList.add("FeelogModal-root", "flog-div-40");
            conformModal.innerHTML = `
                    <div aria-hidden="true" open="" class="FeelogModal-backdrop flog-div-41"></div>
                    <div tabindex="0" data-testid="sentinelStart"></div>
                    <div
                        role="dialog"
                        aria-modal="true"
                        aria-labelledby=":r2b:"
                        aria-describedby=":r2c:"
                        tabindex="-1"
                        class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
                    >
                        <h2 id=":r2b:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
                            확인
                        </h2>
                        <button
                            aria-label="닫기"
                            class="FeelogModalClose-root FeelogModalClose-variantPlain FeelogModalClose-colorNeutral FeelogModalClose-sizeMd flog-button-9"
                            type="button"
                        >
                            <svg
                                focusable="false"
                                aria-hidden="true"
                                viewBox="0 0 24 24"
                                data-testid="CloseIcon"
                                class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-6"
                            >
                                <path d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path>
                            </svg>
                        </button>
                        <div id=":r2c:" class="FeelogDialogContent-root flog-div-43">
                            해당 글을 삭제할까요?
                        </div>
                        <div data-last-child="" class="FeelogDialogActions-root flog-div-44">
                            <button
                                class="FeelogButton-root FeelogButton-variantSolid FeelogButton-colorPrimary FeelogButton-sizeMd delete-reply-button"
                                type="button"
                            >
                                네
                            </button>
                            <button
                                class="FeelogButton-root FeelogButton-variantSoft FeelogButton-colorNeutral FeelogButton-sizeMd flog-a-13"
                                type="button"
                            >
                                아니요
                            </button>
                        </div>
                    </div>
                    <div tabindex="0" data-testid="sentinelEnd"></div>`;

            // 확인 모달창 삽입
            document.body.appendChild(conformModal);

            // 기존에 열린 메뉴가 있으면 삭제
            document.querySelector(".flog-ul-8")?.remove();

            // 모든 케밥 버튼에서 expanded 클래스 제거
            document.querySelectorAll(".flog-button-31.expanded").forEach((btn) => {
                btn.classList.remove("expanded");
            });

            postId = document.querySelector(".flog-div-189").getAttribute("data-postId");
            id     = e.target.closest(".flog-button-20").getAttribute("data-replyId");
        }

        // 2025.04.28 조승찬 :: 댓글 삭제 버튼 클릭시
        if (e.target.classList.contains("delete-reply-button")) {
            const form = document.createElement('form');
            form.setAttribute('method', 'POST');
            form.setAttribute('action', `/feelog.com/@${currentChannelUrl}/community-reply-delete`);
            // id를 보내기 위해 input 생성
            idInput = document.createElement('input');
            idInput.setAttribute('type', 'hidden');
            idInput.setAttribute('name', 'id');
            idInput.setAttribute('value', id);
            form.appendChild(idInput);
            // postId를 보내기 위해 input 생성
            postIdInput = document.createElement('input');
            postIdInput.setAttribute('type', 'hidden');
            postIdInput.setAttribute('name', 'postId');
            postIdInput.setAttribute('value', postId);
            form.appendChild(postIdInput);
            // form을 DOM에 추가
            document.body.appendChild(form);
            // 서버 전송
            form.submit();
        }
        // 2025.04.28 조승찬 :: 댓글 삭제 버튼 클릭시

    });

    document.body.addEventListener("input", (e) => {

        // 입력창에 글자 입력시 5줄까지는 입력창 확장, 등록버튼 활성화
        if (e.target.classList.contains("flog-textarea-6")) {
            const textarea = document.querySelector(".flog-textarea-6");

            // 등록버튼 활성화 비활성화
            const button = document.querySelector(".flog-button-42");
            const hasDisabledClass = button.classList.contains("Feelog-disabled"); // Feelog-disabled 존재 여부 확인

            if (textarea.value.trim().length > 0) {
                // 글자가 있고 Feelog-disabled 클래스가 있다면 제거
                if (hasDisabledClass) {
                    button.classList.remove("Feelog-disabled");
                    button.disabled = false;
                }
            } else {
                // 글자가 없고 Feelog-disabled 클래스가 없다면 추가
                if (!hasDisabledClass) {
                    button.classList.add("Feelog-disabled");
                    button.disabled = true;
                }
            }

            // 입력창 크기 조절
            textarea.style.height = "auto"; // 항상 먼저 초기화

            const lineHeight = parseFloat(getComputedStyle(textarea).lineHeight);
            const maxHeight = lineHeight * 5;
            const scrollHeight = textarea.scrollHeight;

            if (scrollHeight > maxHeight) {
                textarea.style.height = `${maxHeight}px`;
                textarea.style.overflowY = "auto";
            } else {
                textarea.style.height = `${scrollHeight}px`;
                textarea.style.overflowY = "hidden";
            }
        }
    });

    // 댓글 입력 창 포커스
    document.querySelector(".flog-textarea-6").addEventListener("focus", (e) => {
        document.querySelector(".flog-div-201").classList.toggle("Feelog-focused");
    });
    document.querySelector(".flog-textarea-6").addEventListener("blur", (e) => {
        document.querySelector(".flog-div-201").classList.toggle("Feelog-focused");
    });
});
