// // 상단 오른쪽 시작지점 모달 창 ===========================

// const modal = document.getElementById("customModal");
// const openBtn = document.getElementById("openModalBtn");
// const closeBtn = document.querySelector(".feelog-header-mdl-closebtn"); // 닫기 아이콘 버튼
// const backdrop = document.querySelector(".feelog-header-mdl-div01"); // 모달 바깥 영역

// // 열기 버튼 클릭 시
// openBtn.addEventListener("click", () => {
//     modal.style.display = "flex";
// });

// // 닫기 버튼 클릭 시
// closeBtn.addEventListener("click", () => {
//     modal.style.display = "none";
// });

// // 모달 바깥(backdrop) 클릭 시 닫기
// backdrop.addEventListener("click", () => {
//     modal.style.display = "none";
// });

// // 오른쪽 상단 프로필 사진 클릭 + 위치 계산 ===========================

// const avatarBtn = document.getElementById("avatar-button");
// const profileMenu = document.getElementById("profile-menu");

// function toggleProfileMenu(e) {
//     e.stopPropagation(); // 클릭 이벤트 전파 방지

//     const isVisible = !profileMenu.classList.contains("hidden");

//     if (isVisible) {
//         profileMenu.classList.add("hidden");
//     } else {
//         updateMenuPosition(); // 메뉴 위치 업데이트
//         profileMenu.classList.remove("hidden");
//     }
// }

// // 메뉴 위치 계산 및 업데이트 함수
// function updateMenuPosition() {
//     const rect = avatarBtn.getBoundingClientRect(); // 아바타 버튼의 위치
//     const menuWidth = profileMenu.offsetWidth; // 메뉴의 너비
//     const viewportWidth = window.innerWidth; // 화면의 너비

//     // 메뉴의 오른쪽 끝을 아바타 버튼의 오른쪽 끝에 맞추기 위해 계산
//     const left = rect.left - 270;
//     const top = rect.bottom + window.scrollY - 35; // 메뉴를 아바타 버튼보다 5px 위로 올림

//     // 화면의 오른쪽 경계를 넘지 않도록 조정
//     const adjustedLeft = Math.min(left, viewportWidth - menuWidth - 8); // 화면의 오른쪽 끝

//     profileMenu.style.position = "absolute";
//     profileMenu.style.left = `${adjustedLeft}px`; // 메뉴의 왼쪽 끝
//     profileMenu.style.top = `${top}px`; // 메뉴의 상단 끝
// }

// // 닫기 함수
// function closeProfileMenu(e) {
//     if (!profileMenu.contains(e.target) && !avatarBtn.contains(e.target)) {
//         profileMenu.classList.add("hidden");
//     }
// }

// // 윈도우 리사이즈 시에도 메뉴 위치 자동 재계산
// window.addEventListener("resize", () => {
//     if (!profileMenu.classList.contains("hidden")) {
//         updateMenuPosition();
//     }
// });

// // 이벤트 연결
// avatarBtn.addEventListener("click", toggleProfileMenu);
// document.addEventListener("click", closeProfileMenu);

fetch("../../templates/main/header.html")
    .then((res) => res.text())
    .then((html) => {
        const container = document.getElementById("header-container");
        container.innerHTML = html;

        // 여기서부터 header DOM이 생성된 이후에 이벤트 연결
        const modal = document.getElementById("customModal");
        const openBtn = document.getElementById("openModalBtn");
        const closeBtn = document.querySelector(".feelog-header-mdl-closebtn");
        const backdrop = document.querySelector(".feelog-header-mdl-div01");

        if (modal && openBtn && closeBtn && backdrop) {
            console.log("들어옴 1");
            openBtn.addEventListener("click", () => {
                console.log("들어옴 2");
                modal.style.display = "flex";
            });

            closeBtn.addEventListener("click", () => {
                console.log("들어옴 3");
                modal.style.display = "none";
            });

            backdrop.addEventListener("click", () => {
                console.log("들어옴 4");
                modal.style.display = "none";
            });
        }

        const avatarBtn = document.getElementById("avatar-button");
        const profileMenu = document.getElementById("profile-menu");

        function toggleProfileMenu(e) {
            e.stopPropagation();

            const isVisible = !profileMenu.classList.contains("hidden");
            if (isVisible) {
                profileMenu.classList.add("hidden");
            } else {
                updateMenuPosition();
                profileMenu.classList.remove("hidden");
            }
        }

        function updateMenuPosition() {
            const rect = avatarBtn.getBoundingClientRect();
            const menuWidth = profileMenu.offsetWidth;
            const viewportWidth = window.innerWidth;

            const left = rect.left - 270;
            const top = rect.bottom - 35;

            const adjustedLeft = Math.min(left, viewportWidth - menuWidth - 8);

            profileMenu.style.position = "absolute";
            profileMenu.style.left = `${adjustedLeft}px`;
            profileMenu.style.top = `${top}px`;
        }

        function closeProfileMenu(e) {
            if (
                !profileMenu.contains(e.target) &&
                !avatarBtn.contains(e.target)
            ) {
                profileMenu.classList.add("hidden");
            }
        }

        if (avatarBtn && profileMenu) {
            avatarBtn.addEventListener("click", toggleProfileMenu);
            document.addEventListener("click", closeProfileMenu);
            window.addEventListener("resize", () => {
                if (!profileMenu.classList.contains("hidden")) {
                    updateMenuPosition();
                }
            });
        }

        // ======= 추가된 검색 모달 기능 시작 =======

        const searchBtn = document.querySelector(".search-btn");
        const searchMain = document.querySelector(".search-main");
        const searchCloseBtn = document.querySelector(".search-close-btn");
        const searchFilterBtn = document.querySelector(".search-filterBtn");
        const searchFilterModal = document.querySelector(".search-filter");
        const searchFilterCloseBtn = document.querySelector(
            ".search-filterBtn-close"
        );
        const searchFilterCloseBtn2 = document.querySelector(
            ".search-filterBtn-close2"
        );
        const searchBackdrop = document.querySelector(".jk-feelog-div014");

        // 검색 메인 모달 열기
        if (searchBtn && searchMain) {
            searchBtn.addEventListener("click", () => {
                searchMain.classList.remove("hidden");
                document.body.style.overflow = "hidden";
            });
        }

        // 검색 메인 모달 닫기
        if (searchCloseBtn && searchMain) {
            searchCloseBtn.addEventListener("click", () => {
                searchMain.classList.add("hidden");
                document.body.style.overflow = "auto";
            });
        }

        // 검색 필터 모달 열기
        if (searchFilterBtn && searchFilterModal && searchBackdrop) {
            searchFilterBtn.addEventListener("click", () => {
                searchFilterModal.classList.remove("hidden");
                searchBackdrop.classList.remove("hidden");
            });
        }

        // 검색 필터 모달 닫기 (닫기 버튼 클릭)
        if (
            searchFilterCloseBtn &&
            searchFilterModal &&
            searchFilterCloseBtn2 &&
            searchBackdrop
        ) {
            searchFilterCloseBtn.addEventListener("click", () => {
                searchFilterModal.classList.add("hidden");
                searchBackdrop.classList.add("hidden");
            });
            searchFilterCloseBtn2.addEventListener("click", () => {
                searchFilterModal.classList.add("hidden");
                searchBackdrop.classList.add("hidden");
            });
        }

        // 검색 필터 모달 닫기 (회색 배경 클릭)
        if (searchBackdrop && searchFilterModal) {
            searchBackdrop.addEventListener("click", () => {
                searchFilterModal.classList.add("hidden");
                searchBackdrop.classList.add("hidden");
            });
        }

        // ======= 추가된 검색 모달 기능 끝 =======
        const button = document.getElementById("notificationBtn");
        const menu = document.getElementById("notificationMenu");

        if (button && menu) {
            button.addEventListener("click", toggleMenu);
            document.addEventListener("click", function (e) {
                if (!menu.contains(e.target) && !button.contains(e.target)) {
                    closeMenu();
                }
            });
        }

        // 메뉴 열림 상태 체크 변수
        let isOpen = false;

        function toggleProfileMenu(e) {
            e.stopPropagation();

            // 🔒 알림 메뉴가 열려 있으면 닫기
            if (isOpen) {
                closeMenu(); // 메뉴 상태값까지 같이 닫기
            }

            const isVisible = !profileMenu.classList.contains("hidden");
            if (isVisible) {
                profileMenu.classList.add("hidden");
            } else {
                updateMenuPosition();
                profileMenu.classList.remove("hidden");
            }
        }

        // 메뉴 닫기
        function closeMenu() {
            if (isOpen) {
                isOpen = false;
                menu.style.display = "none";
            }
        }

        // 버튼 클릭 시 토글
        button.addEventListener("click", toggleMenu);

        // 메뉴 바깥 클릭 시 닫기
        document.addEventListener("click", function (e) {
            const isClickInside =
                menu.contains(e.target) || button.contains(e.target);
            if (!isClickInside) {
                closeMenu();
            }
        });

        function updateNotificationMenuPosition() {
            const rect = button.getBoundingClientRect();
            const menuWidth = menu.offsetWidth;
            const viewportWidth = window.innerWidth;

            const left = rect.left - 440;
            const top = rect.bottom + 14; // 버튼 아래 8px 정도

            const adjustedLeft = Math.min(left, viewportWidth - menuWidth - 8);

            menu.style.position = "absolute";
            menu.style.left = `${adjustedLeft}px`;
            menu.style.top = `${top}px`;
        }

        function toggleMenu(e) {
            e.stopPropagation();

            // 프로필 메뉴가 열려 있으면 닫기
            if (!profileMenu.classList.contains("hidden")) {
                profileMenu.classList.add("hidden");
            }

            isOpen = !isOpen;
            if (isOpen) {
                updateNotificationMenuPosition();
                menu.style.display = "block";
            } else {
                menu.style.display = "none";
            }
        }

        window.addEventListener("resize", () => {
            if (isOpen) {
                updateNotificationMenuPosition();
            }
        });

        const markAllAsReadBtn = document.querySelector(".joy-fsdjpg");
        const allBadges = document.querySelectorAll(".joy-yhhqut");
        const topCount = document.querySelector(".feelog-header-topSpan01");

        if (markAllAsReadBtn) {
            markAllAsReadBtn.addEventListener("click", () => {
                allBadges.forEach((badge) => (badge.style.display = "none"));
                if (topCount) topCount.style.display = "none";
            });
        }
        // =========================메세지 창==========================//
        const messageButton = document.querySelector(".message-button");
        console.log(messageButton);
        const messageList = document.querySelector(".message-listContainer");
        const messageButtonImg = document.querySelector(".message-buttonImg");
        const xButtonImg = document.querySelector(".x-buttonImg");
        const messageChats = document.querySelectorAll(".joy-1521dpu");

        const menuDiv = document.createElement("div");
        menuDiv.id = "menu-button";

        messageButton.addEventListener("click", (e) => {
            if (messageList.classList.contains("message-listHidden")) {
                messageList.classList.remove("message-listHidden");
                messageButtonImg.classList.remove("view-messageImg");
                messageButtonImg.classList.add("hidden-messageImg");
                xButtonImg.classList.remove("hidden-xImg");
                xButtonImg.classList.add("view-xImg");
            } else {
                messageList.classList.add("message-listHidden");
                messageButtonImg.classList.remove("hidden-messageImg");
                messageButtonImg.classList.add("view-messageImg");
                xButtonImg.classList.remove("view-xImg");
                xButtonImg.classList.add("hidden-xImg");
            }
        });

        messageList.addEventListener("input", (e) => {
            if (e.target.classList.contains("textareaInput_02")) {
                e.target.style.height = "auto";
                e.target.style.height =
                    Math.min(e.target.scrollHeight, 200) + "px";
            }
        });

        messageList.addEventListener("click", (e) => {
            const list = e.target.closest(".joy-1521dpu");
            const xButton = e.target.closest(".joy-oklso3");
            const menuButton = e.target.closest(".joy-14llzfj");
            if (list) {
                messageList.innerHTML = `
        <div id="message-listContainer"  style="position: relative!important;height:100%!important;width: 100%!important;border:none!important;">
        <div class="diary_container_01" data-first-child="" data-last-child="">
        <div role="dialog" aria-modal="true" aria-labelledby=":r4j:" aria-describedby=":r4k:" data-google-interstitial="false" tabindex="-1" class="MuiModalDialog-root MuiModalDialog-variantOutlined MuiModalDialog-colorNeutral MuiModalDialog-sizeMd MuiModalDialog-layoutAdaptive joy-l3kvea"><div class="joy-h3xwdq" data-first-child=""><div class="joy-1r5to7m"><a class="joy-uv29nh" href="/profile/@m4idfj"><div class="imgWrap_05"><img alt="" src="https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/avatar_blank.png?w=100&amp;h=100&amp;q=65" loading="lazy" class="aTag_divImg_01"></div></a><p class="title_h5_01">Jake Jung</p></div><div class="joy-1j6tmez"><button tabindex="0" aria-haspopup="menu" aria-expanded="false" aria-controls=":r4l:" class="MuiIconButton-sizeMd joy-14llzfj" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01"><path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path></svg></button><button class="MuiModalClose-root MuiModalClose-variantPlain MuiModalClose-colorNeutral MuiModalClose-sizeMd joy-oklso3" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="CloseIcon" class="joy-c6bb0f"><path d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path></svg></button></div></div><hr class="MuiDivider-insetContext joy-12udg69"><div class="joy-uabdzs"><div class="joy-1fptnyw"><div class=" joy-1age63q"><div role="separator" class="joy-bwcods"><p class=" previous_p_01">2025. 4. 1.</p></div></div><div class="joy-131bx5v" aria-live="polite"><a href="/profile/@m4idfj" class="joy-kd81xj"><div class="MuiAvatar-sizeXs joy-7tv6fu"><img alt="" src="https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/avatar_blank.png?w=50&amp;h=50&amp;q=65" loading="lazy" class="aTag_divImg_01"></div></a><div class="joy-11cui4a"><div class="joy-e6pf26"><div class=" joy-6wq7gy"><p class=" joy-a0hgnt">팀장님 수고가 많으십니다</p></div><button class="uploadButton_01 joy-1dgehg0" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01"><path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path></svg></button></div><p class=" joy-9nvto4">2025. 4. 1. 16:03</p></div></div><div class="joy-1udr1jv"><div class="joy-1y9kyp6"><div class="joy-e6pf26"><button class="uploadButton_01 joy-ozngwz" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="section_div_svg_02"><path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path></svg></button><div class=" joy-3diwkv"><p class=" joy-1kp8wdt">어쩔티비</p></div></div><p class=" joy-9nvto4">2025. 4. 1. 16:04</p></div></div><div class=" joy-1age63q"><div role="separator" class="joy-bwcods"><p class=" previous_p_01">2025. 4. 10.</p></div></div><div class="joy-131bx5v" aria-live="polite"><a href="/profile/@m4idfj" class="joy-kd81xj"><div class="MuiAvatar-sizeXs joy-7tv6fu"><img alt="" src="https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/avatar_blank.png?w=50&amp;h=50&amp;q=65" loading="lazy" class="aTag_divImg_01"></div></a><div class="joy-11cui4a"><div class="joy-e6pf26"><div class=" joy-6wq7gy"><p class=" joy-a0hgnt">메세지 하나만주세요</p></div><button class="uploadButton_01 joy-1dgehg0" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01"><path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path></svg></button></div><p class=" joy-9nvto4">2025. 4. 10. 13:41</p></div></div><div class="joy-1udr1jv"><div class="joy-1y9kyp6"><div class="joy-e6pf26"><button class="uploadButton_01 joy-ozngwz" type="button"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="section_div_svg_02"><path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path></svg></button><div class=" joy-3diwkv"><p class=" joy-1kp8wdt">ㄴㄴ</p></div></div><p class=" joy-9nvto4">2025. 4. 10. 16:09</p></div></div></div></div><hr class="MuiDivider-insetContext joy-12udg69"><div class="joy-1m2yi2j"><div class="joy-spcbp9"><textarea rows="1" placeholder="메시지를 입력하세요." class="textareaInput_01 textareaInput_02" style="height: 28px; overflow-y: auto;"></textarea>
        <div class="upload_buttonContainer_01 joy-18i8jzi"><button class="button_disabled_01 MuiIconButton-variantSolid MuiIconButton-colorPrimary MuiIconButton-sizeMd joy-ut6eac" type="button" disabled=""><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01"><path d="M12.682 3.54a.949.949 0 0 0-.68-.29c-.258 0-.5.105-.68.29L4.76 10.413a.937.937 0 0 0 1.355 1.293l4.95-5.18v13.286c0 .519.418.937.937.937.52 0 .938-.418.938-.938V6.527l4.945 5.184a.938.938 0 0 0 1.355-1.293l-6.562-6.875.004-.004Z" fill="currentcolor"></path></svg></button></div></div></div></div>                        
        </div>
        </div>
        `;
                return;
            }

            if (xButton) {
                messageList.innerHTML = `
                <div id="message-listContainer"  style="position: relative!important;height:100%!important;width: 100%!important;border:none!important;">
                    <div class="diary_container_01" data-first-child="" data-last-child="">
                        <div class="joy-1dox06v">
                            <div class="joy-dmwqxb">
                                <a target="" rel="noreferrer" href="/messages" class="more_diaryButton_01 joy-zok3ae">
                                    <p class=" joy-c7faj8">메시지</p>
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="more_diaryButton_svg_01">
                                        <path d="M16.416 11.332a.934.934 0 0 1 0 1.325l-7.5 7.504a.937.937 0 0 1-1.324-1.324L14.428 12l-6.84-6.84a.937.937 0 0 1 1.324-1.324l7.504 7.495Z" fill="currentcolor"></path>
                                    </svg>
                                </a>
                            </div>
                            <hr class="joy-w2e6ki">
                        </div>
                        <ul role="group" class="MuiList-root MuiList-vertical MuiList-variantPlain MuiList-colorNeutral MuiList-nesting joy-50s8vl">
                            <li tabindex="0" id=":r2g:" role="menuitem" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1521dpu">
                                <div class="MuiListItemAvatar-root joy-11ol5nn">
                                    <div class="imgWrap_05">
                                        <img alt="" src="https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/avatar_blank.png?w=100&amp;h=100&amp;q=65" loading="lazy" class="aTag_divImg_01">
                                    </div>
                                </div>
                                <div class="MuiListItemContent-root joy-hflw33">
                                    <div class="diary_nicknameWrap_01">
                                        <p class="title_h5_01">Jake Jung</p>
                                        <p class="joy-bfn6hx">2025. 4. 10.</p>
                                    </div>
                                    <p class="joy-wtfzkc">ㄴㄴ</p>
                                </div>
                            </li>
                            <hr class="joy-w2e6ki">
                            <li tabindex="-1" id=":r2i:" role="menuitem" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1521dpu">
                                <div class="MuiListItemAvatar-root joy-11ol5nn">
                                    <div class="imgWrap_05">
                                        <img alt="" src="https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/avatar_blank.png?w=100&amp;h=100&amp;q=65" loading="lazy" class="aTag_divImg_01">
                                    </div>
                                </div>
                                <div class="MuiListItemContent-root joy-hflw33">
                                    <div class="diary_nicknameWrap_01">
                                        <p class="title_h5_01">조승찬</p>
                                        <p class="joy-bfn6hx">2025. 4. 2.</p>
                                    </div>
                                    <p class="joy-wtfzkc">안녕하십니까</p>
                                </div>
                            </li>
                            <hr class="joy-w2e6ki">
                            <li tabindex="-1" id=":r2k:" role="menuitem" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1521dpu">
                                <div class="MuiListItemAvatar-root joy-11ol5nn">
                                    <div class="imgWrap_05">
                                        <img alt="" src="https://d3mcojo3jv0dbr.cloudfront.net/2024/08/04/08/22/3e525faf47117161a07e6f5e548859d1.png?w=100&amp;h=100&amp;q=65" loading="lazy" class="aTag_divImg_01">
                                    </div>
                                </div>
                                <div class="MuiListItemContent-root joy-hflw33">
                                    <div class="diary_nicknameWrap_01">
                                        <p class="title_h5_01">하하호</p>
                                        <p class="joy-bfn6hx">2025. 4. 1.</p>
                                    </div>
                                    <p class="joy-wtfzkc">잘못보냈어요 ㅠㅠ</p>
                                </div>
                            </li>
                            <hr class="joy-w2e6ki">
                        </ul>
                    </div>
                </div>
        `;
                return;
            }

            if (menuButton) {
                const existDiv = document.querySelector("#menu-button");
                const buttonWrap = e.target.closest(".joy-1j6tmez");

                if (existDiv) {
                    existDiv.remove();
                    return;
                }

                menuDiv.innerHTML = `
        <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
            <button type ="button" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p">
                <p class=" button_text_01">대화방 삭제</p>
            </button>
        </ul>
        `;

                const rect = menuButton.getBoundingClientRect();
                const absoluteTop =
                    menuButton.offsetTop + menuButton.offsetHeight;
                const absoluteLeft = menuButton.offsetLeft;

                menuDiv.style.position = "absolute";
                menuDiv.style.top = `${absoluteTop}px`;
                menuDiv.style.left = `${absoluteLeft - 50}px`;

                buttonWrap.appendChild(menuDiv);
            }
        });
    });
