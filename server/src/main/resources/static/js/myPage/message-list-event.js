//  2025.04.06 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(메세지 목록, 이용자 차단) 클릭 이벤트
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

    // 목록 클릭 이벤트 => 메세지 입력 모달창 보여주기
    document.querySelectorAll(".flog-div-20").forEach((title) => {
        title.addEventListener("click", (e) => {
            e.preventDefault();
            document.querySelector(".FeelogModal-root.flog-div-40").style.display = "block";
        });
    });

    // 모달창 메세지 입력창 focus 이벤트
    document.querySelector(".flog-textarea-1").addEventListener("focus", (e) => {
        document.querySelector(".flog-div-107").classList.add("Feelog-focused");
    });
    document.querySelector(".flog-textarea-1").addEventListener("blur", (e) => {
        document.querySelector(".flog-div-107").classList.remove("Feelog-focused");
    });

    // 모달창 메세지 입력창 입력 이벤트
    document.querySelector("textarea.flog-textarea-1").addEventListener("input", () => {
        if (document.querySelector("textarea.flog-textarea-1").value.trim().length > 0) {
            // 글자가 있으면 버튼 활성화
            document.querySelector("button.flog-button-22").classList.remove("Feelog-disabled");
            document.querySelector("button.flog-button-22").removeAttribute("disabled");
        } else {
            // 글자가 없으면 버튼 비활성화
            document.querySelector("button.flog-button-22").classList.add("Feelog-disabled");
            document.querySelector("button.flog-button-22").setAttribute("disabled", "");
        }
    });

    // 입력 버튼 클릭시 이벤트 :: 서버 갔다 온 이후 동작으로 수정
    document.querySelector(".flog-div-108").addEventListener("click", (e) => {
        // 새로 생성된 말풍선 만들기
        const speechBubble = document.createElement("div");
        speechBubble.classList.add("FeelogStack-root", "flog-div-98");
        speechBubble.innerHTML = `
            <div class="FeelogStack-root flog-div-99">
                <div class="FeelogStack-root flog-div-100">
                    <button
                        class="FeelogIconButton-root FeelogIconButton-variantPlain FeelogIconButton-colorNeutral FeelogIconButton-sizeSm flog-button-20  delete-button"
                        type="button"
                    >
                        <svg
                            focusable="false"
                            aria-hidden="true"
                            viewBox="0 0 24 24"
                            width="24"
                            height="24"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                            class="FeelogSvgIcon-root FeelogSvgIcon-sizeMd flog-svg-7"
                        >
                            <path
                                d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z"
                                fill="currentcolor"
                            ></path>
                        </svg>
                    </button>
                    <div class="FeelogBox-root flog-div-101">
                        <span class="FeelogTypography-root FeelogTypography-body-md flog-span-21">${
                            document.querySelector("textarea.flog-textarea-1").value
                        }</span>
                    </div>
                </div>
                 <p class="FeelogTypography-root FeelogTypography-body-xs flog-p-19">2025. 4. 2. 15:28</p>
            </div>`;
        // 새로 생성된 말풍선 삽입
        document.querySelector(".flog-div-95").appendChild(speechBubble);
    });

    document.body.addEventListener("click", (e) => {
        // 삭제 버튼 클릭 이벤트
        if (e.target.closest(".delete-button")) {
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
                aria-labelledby=":r27:"
                aria-describedby=":r28:"
                tabindex="-1"
                class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
            >
                <h2 id=":r27:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
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
                <div id=":r28:" class="FeelogDialogContent-root flog-div-43">
                    해당 메시지를 삭제할까요? 메시지는 나에게서만 삭제되며 삭제된 메시지는 복구할 수 없어요.
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

        // 케밥 메뉴 클릭 이벤트 :: 메세지 차단, 대화방 삭제
        if (e.target.closest(".flog-button-18")) {
            e.preventDefault();

            if (e.target.closest(".flog-button-18").classList.contains("expanded")) {
                document.querySelector(".flog-ul-2").remove();
                e.target.closest(".flog-button-18").classList.remove("expanded");
                return;
            }

            // 메세지 차단, 대화방 삭제 모달창 생성// 요소 생성 스크립트
            const menuElement = document.createElement("ul");
            menuElement.setAttribute("role", "menu");
            menuElement.setAttribute("tabindex", "-1");
            menuElement.setAttribute("id", ":r19:");
            menuElement.setAttribute(
                "class",
                "base-Popper-root FeelogMenu-root Feelog-expanded FeelogMenu-variantPlain FeelogMenu-colorNeutral FeelogMenu-sizeMd flog-ul-2"
            );
            menuElement.setAttribute("style", "position: absolute; inset: 0px 0px auto auto; margin: 0px; ");
            menuElement.setAttribute("data-popper-placement", "bottom-end");
            menuElement.innerHTML = `
                <li
                    tabindex="0"
                    id=":r79:"
                    role="menuitem"
                    data-first-child=""
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-1"
                >
                    메시지 차단
                </li>
                <li
                    tabindex="-1"
                    id=":r7b:"
                    role="menuitem"
                    data-last-child=""
                    class="FeelogMenuItem-root FeelogMenuItem-colorNeutral FeelogMenuItem-variantPlain flog-li-2"
                >
                    대화방 삭제
                </li>`;

            // 메세지 차단, 대화방 삭제 모달창 삽입
            document.body.appendChild(menuElement);
            e.target.closest(".flog-button-18").classList.add("expanded");
        }

        // 메세지 차단 클릭 이벤트
        if (e.target.classList.contains("flog-li-1")) {
            e.preventDefault();

            // 케밥 메뉴 삭제
            document.querySelector(".flog-ul-2").remove();
            document.querySelector(".flog-button-18").classList.remove("expanded");

            // 확인 모달창 생성
            conformModal = document.createElement("div");
            conformModal.classList.add("FeelogModal-root", "flog-div-40");
            conformModal.innerHTML = `
                <div aria-hidden="true" open="" class="FeelogModal-backdrop flog-div-41"></div>
                <div tabindex="0" data-testid="sentinelStart"></div>
                <div
                    role="dialog"
                    aria-modal="true"
                    aria-labelledby=":rq:"
                    aria-describedby=":rr:"
                    tabindex="-1"
                    class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
                >
                    <h2 id=":rq:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
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
                    <div id=":rr:" class="FeelogDialogContent-root flog-div-43">
                        해당 이용자를 차단할까요?
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

        // 대화방 삭제 클릭 이벤트
        if (e.target.classList.contains("flog-li-2")) {
            e.preventDefault();

            // 케밥 메뉴 삭제
            document.querySelector(".flog-ul-2").remove();
            document.querySelector(".flog-button-18").classList.remove("expanded");

            // 확인 모달창 생성
            conformModal = document.createElement("div");
            conformModal.classList.add("FeelogModal-root", "flog-div-40");
            conformModal.innerHTML = `
                <div aria-hidden="true" open="" class="FeelogModal-backdrop flog-div-41"></div>
                <div tabindex="0" data-testid="sentinelStart"></div>
                <div
                    role="dialog"
                    aria-modal="true"
                    aria-labelledby=":rq:"
                    aria-describedby=":rr:"
                    tabindex="-1"
                    class="FeelogModalDialog-root FeelogModalDialog-variantOutlined FeelogModalDialog-colorNeutral FeelogModalDialog-sizeMd FeelogModalDialog-layoutAlert flog-div-42"
                >
                    <h2 id=":rq:" data-first-child="" class="FeelogDialogTitle-root FeelogDialogTitle-title-lg flog-h2-4">
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
                    <div id=":r1t:" class="FeelogDialogContent-root flog-div-43">
                        대화방을 삭제하시겠어요? 대화 내역은 나에게서만 삭제되며 삭제된 대화방은 복구할 수 없어요.
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
        if (
            e.target.closest(".flog-svg-6") ||
            e.target.closest(".flog-div-44") ||
            e.target.closest(".flog-button-17")
        ) {
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
    });
});
