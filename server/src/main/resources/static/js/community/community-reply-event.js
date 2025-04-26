// 2025.04.12 조승찬
document.addEventListener("DOMContentLoaded", () => {
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

        // 게시글 작성 버튼 클릭시
        if (e.target.closest(".flog-button-30")) {
            document.querySelector(".flog-div-176").style.display = "block";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".flog-nav-6").style.zIndex = "100";
        }

        // 모달창 이미지 추가 버튼 클릭시
        if (e.target.closest(".flog-button-37")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";
            input.multiple = true;

            // 최대 5개 :: <ul class="FeelogStack-root flog-ul-7"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (event) => {
                if (event.target.files.length > 5) {
                    alert("최대 5개의 이미지만 업로드할 수 있습니다.");
                    event.target.value = "";
                }
            });

            input.click();
        }

        // 모달창 x, 취소, 게시 버튼 클릭시 모달 없애고, 헤드 z-index 500으로 원복
        if (
            e.target.closest(".flog-button-36") ||
            e.target.classList.contains("post") ||
            e.target.classList.contains("cancel")
        ) {
            document.querySelector(".flog-div-176").style.display = "none";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "10000";
            document.querySelector(".flog-nav-6").style.zIndex = "500";
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
        }

        // 글 수정 버튼 클릭시
        if (e.target.classList.contains("flog-li-13")) {
            document.querySelector(".flog-div-176").style.display = "block";
            document.querySelector(".feelog-header-mainDiv").style.zIndex = "100";
            document.querySelector(".flog-nav-6").style.zIndex = "100";
        }

        // 댓글 이미지 추가 버튼 클릭시
        if (e.target.closest(".flog-button-41")) {
            const input = document.createElement("input");
            input.type = "file";
            input.accept = "image/*";

            // 최대 1개 :: <ul class="FeelogStack-root flog-ul-7"> 에 data-count 추가해서 체크하는거로 수정
            input.addEventListener("change", (event) => {
                if (event.target.files.length > 1) {
                    alert("1개의 이미지만 업로드할 수 있습니다.");
                    event.target.value = "";
                }
            });

            input.click();
        }

        // 좋아요 버튼 클릭시
        if (e.target.closest(".flog-button-32")) {
            // 기존 버튼 및 다른 요소들 선택
            const currentButton = e.target.closest(".flog-button-32");
            const newLikeButtonHTML = `
                <button type="button" aria-label="좋아요 버튼" class="FeelogButton-root FeelogButton-variantPlain FeelogButton-colorNeutral FeelogButton-sizeSm flog-button-39">
                    <span class="FeelogButton-startDecorator flog-span-42">
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-22">
                            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                        </svg>
                    </span>
                    1
                </button>`;

            // 기존 버튼 교체
            currentButton.outerHTML = newLikeButtonHTML;
        }

        // 좋아요 버튼 클릭시(취소)
        if (e.target.closest(".flog-button-39")) {
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
        }

        // 신고 버튼 클릭시 이벤트
        if (e.target.closest(".report-button")) {
            e.preventDefault();

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
                            해당 메시지를 신고할까요?
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

        // 네 버튼 클릭시 :: 서버에 갔다 온 후 메세지를 다르게 변경하고, 동의 한 내용에 따라 다른 액션 실시
        if (e.target.classList.contains("flog-button-10")) {
            e.preventDefault();

            // 확인 버튼 클릭시 :: 최종 결과를 보여주는 창에만 뜨는 버튼 명
            if (e.target.textContent.trim() == "확인") {
                e.target.closest(".FeelogModal-root.flog-div-40").remove();
                return;
            }

            // 서버 결과 보여주는 창 생성
            conformModal = document.createElement("div");
            conformModal.classList.add("FeelogModal-root", "flog-div-40");
            conformModal.innerHTML = `
                        <div aria-hidden="true" open="" class="FeelogModal-backdrop flog-div-41"></div>
                        <div tabindex="0" data-testid="sentinelStart"></div>
                        <div
                            role="dialog"
                            aria-modal="true"
                            aria-labelledby=":r28:"
                            aria-describedby=":r29:"
                            tabindex="-1"
                            class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
                        >
                            <h2 id=":r28:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
                                알림
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
                            <div id=":r29:" class="FeelogDialogContent-root flog-div-43">
                                이미 차단한 이용자예요.
                            </div>
                            <div data-last-child="" class="FeelogDialogActions-root flog-div-44">
                                <button
                                    class="FeelogButton-root FeelogButton-variantSolid FeelogButton-colorPrimary FeelogButton-sizeMd flog-button-10"
                                    type="button"
                                >
                                    확인
                                </button>
                            </div>
                        </div>
                        <div tabindex="0" data-testid="sentinelEnd"></div>`;

            // 확인 모달창 삽입
            document.body.appendChild(conformModal);
        }

        // 신고버튼 클릭
        // if (e.target.closest(".report-button")) {
        //     const reportButton = e.target.closest(".report-button");
        //     if (reportButton.classList.contains("white")) {
        //         reportButton.classList.remove("white");
        //         reportButton.classList.add("red");
        //     } else {
        //         reportButton.classList.remove("red");
        //         reportButton.classList.add("white");
        //     }
        // }
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
                }
            } else {
                // 글자가 없고 Feelog-disabled 클래스가 없다면 추가
                if (!hasDisabledClass) {
                    button.classList.add("Feelog-disabled");
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
