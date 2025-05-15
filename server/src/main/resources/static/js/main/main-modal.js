document.addEventListener("DOMContentLoaded", async () => {
    // 여기서부터 header DOM이 생성된 이후에 이벤트 연결
    const modal = document.getElementById("customModal");
    const openBtn = document.getElementById("openModalBtn");
    const closeBtn = document.querySelector(".feelog-header-mdl-closebtn");
    const backdrop = document.querySelector(".feelog-header-mdl-div01");

    if (modal && openBtn && closeBtn && backdrop) {
        openBtn.addEventListener("click", () => {
            modal.style.display = "flex";
        });

        closeBtn.addEventListener("click", () => {
            modal.style.display = "none";
        });

        backdrop.addEventListener("click", () => {
            modal.style.display = "none";
        });
    }

    function closeAllMenusExcept2(except) {
        // profile
        const profileMenu = document.getElementById("profile-menu");
        if (profileMenu && except !== "profile") profileMenu.classList.add("hidden");

        // 알림
        if (notificationMenu && except !== "notification") {
            notificationMenu.style.display = "none";
            isOpen = false;
        }

        // 메시지 패널
        const messageList = document.querySelector(".message-listContainer");
        const messageButtonImg = document.querySelector(".message-buttonImg");
        const xButtonImg = document.querySelector(".x-buttonImg");
        if (messageList && !messageList.classList.contains("message-listHidden") && except !== "message") {
            messageList.classList.add("message-listHidden");
            if (messageButtonImg && xButtonImg) {
                messageButtonImg.classList.remove("hidden-messageImg");
                messageButtonImg.classList.add("view-messageImg");
                xButtonImg.classList.remove("view-xImg");
                xButtonImg.classList.add("hidden-xImg");
            }
        }

        // 모달
        const modal = document.getElementById("customModal");
        if (modal && except !== "modal") {
            modal.style.display = "none";
        }
    }

    const avatarBtn = document.getElementById("avatar-button");
    const profileMenu = document.getElementById("profile-menu");

    function toggleProfileMenu(e) {
        e.stopPropagation();

        closeAllMenusExcept2("profile");

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
        if (!profileMenu.contains(e.target) && !avatarBtn.contains(e.target)) {
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
    const searchFilterCloseBtn = document.querySelector(".search-filterBtn-close");
    const searchFilterCloseBtn2 = document.querySelector(".search-filterBtn-close2");
    const searchBackdrop = document.querySelector(".jk-feelog-div014");

    function initSearchModal() {
        fetch("/main/init")
            .then(res => {
                if (!res.ok) throw new Error("응답 실패: " + res.status);
                return res.json();
            })
            .then(data => {
                console.log("받은 데이터: ", data);

                data.channelPosts.forEach(p => {
                    console.log(`[CHECK] postType:`, p.postType, typeof p.postType);
                });

                renderSlider(".mind-log-wrap", data.mindLogs, "diary");
                renderSlider(".channel-post-wrap", data.channelPosts.filter(p => p.postType === "POST"), "post");
                renderSlider(".cheering-wrap", data.channelPosts.filter(p => p.postType === "CHEERING"), "cheering");
            })
            .catch(err => {
                console.error("초기화 실패: ", err);
            });
    }

    // 검색 메인 모달 열기
    if (searchBtn && searchMain) {
        searchBtn.addEventListener("click", () => {
            searchMain.classList.remove("hidden");
            document.body.style.overflow = "hidden";

            // 검색 모달 열릴 때 초기 목록 호출
            initSearchModal();
        });
    }

    // 검색 메인 모달 닫기
    if (searchCloseBtn && searchMain) {
        searchCloseBtn.addEventListener("click", () => {
            searchMain.classList.add("hidden");
            document.body.style.overflow = "auto";
        });
    }

    fetch("/main/channel/my")
        .then(res => {
            if (!res.ok || res.status === 204) return null;
            return res.json();
        })
        .then(data => {
            const menuList = document.querySelector(".feelog-header-menuUi.menu-ui03");
            if (!menuList) return;

            // 기존 채널 li 제거
            const oldLi = menuList.querySelector(".feelog-header-profileMenu");
            if (oldLi) oldLi.remove();

            // 새 li 생성
            const li = document.createElement("li");
            li.setAttribute("role", "presentation");
            li.className = "feelog-header-profileMenu menu-li02";

            const a = document.createElement("a");
            a.href = data && data.channelUrl ? `/feelog.com/@${data.channelUrl}` : "/myPage/make-channel";
            a.className = "feelog-header-topA02";
            a.setAttribute("data-first-child", "");

            a.innerHTML = `
            <div class="feelog-header-topDiv10">
                <div class="feelog-header-topDiv11">
                    <div class="feelog-header-topDiv12" data-first-child="">
                        <img alt="channel" loading="lazy" decoding="async"
                            src="${data && data.channelFilePath && data.channelFileName
                ? `/files/display?path=${data.channelFilePath}/${data.channelFileName}`
                : 'https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/blog_blank.png?w=50&h=50&q=65'}"
                            style="position: absolute; height: 100%; width: 100%; object-fit: cover; color: transparent;">
                    </div>
                </div>
            </div>
            <span class="feelog-header-topSpan05">${data && data.channelTitle ? data.channelTitle : "채널 만들기"}</span>
        `;

            const div = document.createElement("div");
            div.className = "feelog-header-topDiv13";

            li.appendChild(a);
            li.appendChild(div);

            // 구분선 앞에 삽입
            const separator = menuList.querySelector(".menu-li03");
            menuList.insertBefore(li, separator);
        })
        .catch(err => {
            console.error("채널 정보 불러오기 실패", err);
        });

    // =========================메세지 창==========================//
    const messageButton = document.querySelector(".message-button");
    const messageList = document.querySelector(".message-listContainer");
    const messageButtonImg = document.querySelector(".message-buttonImg");
    const xButtonImg = document.querySelector(".x-buttonImg");
    const messageChats = document.querySelectorAll(".joy-1521dpu");

    const menuDiv = document.createElement("div");
    menuDiv.id = "menu-button";

    messageButton.addEventListener("click", (e) => {
        if (loginMember == null) {
            alert("로그인 후 이용해주세요 😊")
            window.location.href = "/login/login"
        }

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
            e.target.style.height = Math.min(e.target.scrollHeight, 200) + "px";
        }
    });

    // 메세지 목록 받아오기

    async function getMessageListAsync(callback, memberId = Number(loginMember.id)) {
        const response = await fetch(`/message/list?memberId=${memberId}`)
        const messageListData = await response.json()
        if (callback) {
            callback(messageListData)
        }
    }


    const getMessageMemberList = (messageListData) => {
        const firstMember = new Map();

        messageListData.forEach((message) => {
            const memberId = message.participantId;

            // 첫 번째로 등장한 memberId만 저장 (이미 존재하면 덮어쓰지 않음)
            if (!firstMember.has(memberId)) {
                firstMember.set(memberId, message);
            }
        });

        return Array.from(firstMember.values()); // 중복 제거된 메시지 리스트 반환
    };

    const renderMessageList = (messageListData) => {
        console.log(messageListData)
        const result = getMessageMemberList(messageListData);
        const memberList = document.querySelector(".message-listContainer");

        // ✅ 메시지 컨테이너 생성
        const messageContainerDiv = document.createElement("div");
        messageContainerDiv.id = "message-listContainer";
        messageContainerDiv.style.cssText = "position: relative !important; height: 100% !important; width: 100% !important; border: none !important;";

        messageContainerDiv.innerHTML = `
        <div class="MuiStack-root joy-j7qwjs">
            <div class="MuiStack-root joy-1dox06v">
                <div class="MuiStack-root joy-dmwqxb">
                    <a target="" rel="noreferrer" href="" class="MuiButton-root MuiButton-variantPlain MuiButton-colorNeutral MuiButton-sizeMd joy-zok3ae">
                        <p class="MuiTypography-root MuiTypography-title-lg joy-c7faj8">메시지</p>
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-1w4q56z">
                            <path d="M16.416 11.332a.934.934 0 0 1 0 1.325l-7.5 7.504a.937.937 0 0 1-1.324-1.324L14.428 12l-6.84-6.84a.937.937 0 0 1 1.324-1.324l7.504 7.495Z" fill="currentcolor"></path>
                        </svg>
                    </a>
                </div>
                <hr class="MuiDivider-root MuiDivider-horizontal joy-w2e6ki">
            </div>
            <ul role="group" class="MuiList-root joy-50s8vl"></ul>
        </div>
    `;

        memberList.appendChild(messageContainerDiv);
        const memberListWrap = messageContainerDiv.querySelector(".joy-50s8vl");

        // ✅ 메시지 리스트 렌더링
        result.forEach((message) => {
            const newLi = document.createElement("li");
            const newHr = document.createElement("hr");
            const date = new Date(message.updatedDate);
            const formatDate = `${date.getFullYear()}.${date.getMonth() + 1}.${date.getDate()}`;

            newLi.classList.add("MuiMenuItem-root", "MuiMenuItem-colorNeutral", "MuiMenuItem-variantPlain", "joy-1521dpu");
            newLi.setAttribute("data-name", message.memberNickname);
            newLi.setAttribute("data-url", message.memberUrl);
            newLi.id = message.participantId;

            newHr.classList.add("MuiDivider-root", "MuiDivider-horizontal", "joy-w2e6ki");

            const defaultImg = "/images/channel_banner.png";
            let senderImg = message.memberFileName ? encodeURIComponent(`${message.memberFilePath}/${message.memberFileName}`) : null;

            if (senderImg) {
                newLi.setAttribute("data-img", senderImg);
            }
            newLi.innerHTML = `
            <div class="MuiListItemAvatar-root joy-11ol5nn">
                <div class="imgWrap_05">
                </div>
            </div>
            <div class="MuiListItemContent-root joy-hflw33">
                <div class="diary_nicknameWrap_01">
                    <p class="title_h5_01">${message.memberNickname}</p>
                    <p class="joy-bfn6hx">${formatDate}</p>
                </div>
                <p class="joy-wtfzkc">${message.messageContent}</p>
            </div>
        `;

            if (message.memberFileName != null) {
                newLi.querySelector(".imgWrap_05").innerHTML = `
                    <img alt="" src="/files/display?path=${senderImg}" loading="lazy" class="aTag_divImg_01">
                `;
            } else {
                newLi.querySelector(".imgWrap_05").innerHTML = `
                    <img alt="" src=${defaultImg} loading="lazy" class="aTag_divImg_01">
                `;
            }

            // ✅ 메시지 리스트에 추가
            memberListWrap.appendChild(newLi);
            memberListWrap.appendChild(newHr);
        });
    };

// ✅ 함수 실행
    await getMessageListAsync(renderMessageList);
    // 메세지 목록 받아오기

    // 메세지 대화내용 조회
    async function getMessageListByparticipantId(callback, participantId) {
        const response = await fetch(`/message/messageList?participantId=${participantId}`)
        const messageData = await response.json()
        if (callback) {
            callback(messageData)
        }
    }

    messageList.addEventListener("click", async (e) => {
        if (e.target.closest(".joy-oklso3")) {
            messageList.innerHTML = ``;
            await getMessageListAsync(renderMessageList);
        }

        if (e.target.closest(".joy-1521dpu")) {

            messageList.innerHTML = ``;
            const targetInfo = e.target.closest(".joy-1521dpu");
            const newDiv = document.createElement("div")

            newDiv.classList.add("diary_container_01");

            const chatImg = targetInfo.dataset.img ? `/files/display?path=${targetInfo.getAttribute("data-img")}` : "/images/channel_banner.png"

            newDiv.innerHTML = `
                <div role="dialog" aria-modal="true" aria-labelledby=":r4j:" aria-describedby=":r4k:" data-google-interstitial="false" tabindex="-1" class="MuiModalDialog-root MuiModalDialog-variantOutlined MuiModalDialog-colorNeutral MuiModalDialog-sizeMd MuiModalDialog-layoutAdaptive joy-l3kvea">
                    <div class="joy-h3xwdq" data-first-child="">
                        <div class="joy-1r5to7m">
                            <a class="joy-uv29nh" href="/feelog.com/@${targetInfo.getAttribute("data-url")}/community">
                                <div class="imgWrap_05">
                                    <img alt="" src=${chatImg} loading="lazy" class="aTag_divImg_01">
                                </div>
                            </a>
                            <p class="title_h5_01">${targetInfo.getAttribute("data-name")}</p>
                        </div>
                        <div class="joy-1j6tmez">
                            <button tabindex="0" aria-haspopup="menu" aria-expanded="false" aria-controls=":r4l:" class="MuiIconButton-sizeMd joy-14llzfj" type="button" data-index=${targetInfo.id}>
                                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01">
                                    <path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path>
                                </svg>
                            </button>
                            <button class="MuiModalClose-root MuiModalClose-variantPlain MuiModalClose-colorNeutral MuiModalClose-sizeMd joy-oklso3" type="button">
                                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="CloseIcon" class="joy-c6bb0f">
                                    <path d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <hr class="MuiDivider-insetContext joy-12udg69">
                    <div class="joy-uabdzs">
                        <div class="joy-1fptnyw">
                        </div>
                    </div>
                    <hr class="MuiDivider-insetContext joy-12udg69">
                    <div class="joy-1m2yi2j">
                        <div class="joy-spcbp9">
                            <textarea rows="1" placeholder="메시지를 입력하세요." class="textareaInput_01 textareaInput_02" style="height: 28px; overflow-y: auto;"></textarea>
                            <div class="upload_buttonContainer_01 joy-18i8jzi">
                                <button class="button_disabled_01 MuiIconButton-variantSolid MuiIconButton-colorPrimary MuiIconButton-sizeMd joy-ut6eac" type="button" data-index=${targetInfo.id}>
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01">
                                        <path d="M12.682 3.54a.949.949 0 0 0-.68-.29c-.258 0-.5.105-.68.29L4.76 10.413a.937.937 0 0 0 1.355 1.293l4.95-5.18v13.286c0 .519.418.937.937.937.52 0 .938-.418.938-.938V6.527l4.945 5.184a.938.938 0 0 0 1.355-1.293l-6.562-6.875.004-.004Z" fill="currentcolor"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>                        
            `;
            messageList.appendChild(newDiv);

            const groupMessagesByDate = (messageData) => {
                return messageData.reduce((groupedMessages, message) => {
                    const dateKey = message.updatedDate.split(" ")[0]; // ✅ 날짜 부분(YYYY-MM-DD)만 추출

                    if (!groupedMessages[dateKey]) {
                        groupedMessages[dateKey] = []; // ✅ 새로운 날짜 키 생성
                    }

                    groupedMessages[dateKey].push(message); // ✅ 해당 날짜에 메시지 추가
                    return groupedMessages;
                }, {});
            };

            await getMessageListByparticipantId((messageData) => {
                const groupedMessages = groupMessagesByDate(messageData);

                Object.keys(groupedMessages).forEach((message) => {
                    const messageListWrap = newDiv.querySelector(".joy-1fptnyw");
                    messageListWrap.setAttribute("data-url", `/feelog.com/@${targetInfo.getAttribute("data-url")}/community`);
                    const dateDiv = document.createElement("div");
                    dateDiv.classList.add("date-wrapper");
                    dateDiv.innerHTML = `
                        <div role="separator" class="joy-bwcods">
                            <p class="previous_p_01">${message}</p>
                        </div>
                        `;

                    messageListWrap.appendChild(dateDiv);

                    groupedMessages[message].forEach((data) => {
                        const messageDiv = document.createElement("div");
                        if (data.messageType === "received") {
                            messageDiv.classList.add("joy-131bx5v", "messageWrapper")

                            messageDiv.innerHTML = `
                                <a href="/feelog.com/@${targetInfo.getAttribute("data-url")}/community" class="joy-kd81xj">
                                    <div class="MuiAvatar-sizeXs joy-7tv6fu">
                                        <img alt="" src=${chatImg} loading="lazy" class="aTag_divImg_01">
                                    </div>
                                </a>
                                <div class="joy-11cui4a">
                                    <div class="joy-e6pf26">
                                        <div class=" joy-6wq7gy">
                                            <p class=" joy-a0hgnt">${data.messageContent}</p>
                                        </div>
                                        <button class="uploadButton_01 joy-1dgehg0" type="button" data-index=${data.id} data-type=${data.messageType}>
                                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01">
                                                <path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path>
                                            </svg>
                                        </button>
                                    </div>
                                    <p class=" joy-9nvto4">${data.updatedDate}</p>
                                </div>
                            `;
                        } else {
                            messageDiv.classList.add("joy-1udr1jv", "messageWrapper")

                            messageDiv.innerHTML = `
                                <div class="joy-1y9kyp6">
                                    <div class="joy-e6pf26">
                                        <button class="uploadButton_01 joy-ozngwz" type="button" data-index=${data.id} data-type=${data.messageType}>
                                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="section_div_svg_02">
                                                <path d="M10.168 3.875h3.66a.31.31 0 0 1 .262.14l.742 1.11H9.168l.742-1.11a.319.319 0 0 1 .262-.14h-.004Zm6.918 1.25-1.434-2.152A2.196 2.196 0 0 0 13.832 2h-3.664c-.73 0-1.414.367-1.82.973L6.914 5.125H4.187a.935.935 0 0 0-.937.938c0 .519.418.937.938.937h.453l.937 12.684A2.502 2.502 0 0 0 8.07 22h7.86a2.502 2.502 0 0 0 2.492-2.316L19.359 7h.453c.52 0 .938-.418.938-.938a.935.935 0 0 0-.938-.937h-2.726ZM17.48 7l-.93 12.547a.624.624 0 0 1-.625.578H8.07a.63.63 0 0 1-.625-.578L6.52 7h10.96Z" fill="currentcolor"></path>
                                            </svg>
                                        </button>
                                        <div class=" joy-3diwkv">
                                            <p class=" joy-1kp8wdt">${data.messageContent}</p>
                                        </div>
                                    </div>
                                    <p class=" joy-9nvto4">${data.updatedDate}</p>
                                </div>
                            `;
                        }
                        messageListWrap.appendChild(messageDiv)
                    })
                })
            }, Number(e.target.closest(".joy-1521dpu").id));
        }
        // 메세지 대화내용 조회

        // 메세지 insert
        const insertMessage = async (message) => {
            console.log(message);

            await fetch("/message/insertMessage", {
                method: "post",
                body: JSON.stringify(message),
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            })
        }


        if (e.target.closest(".joy-ut6eac")) {
            const target = e.target.closest(".joy-ut6eac")
            const listWraptarget = e.target.closest(".joy-1m2yi2j");
            const messageListParent = listWraptarget.previousElementSibling.previousElementSibling;
            const messageListWrapper = messageListParent.querySelector(".joy-1fptnyw");
            console.log(messageListWrapper)
            const parent = target.parentElement;
            const textValue = parent.previousElementSibling;
            const messageDiv = document.createElement("div");
            const now = new Date(); // ✅ 현재 날짜 및 시간

            const year = now.getFullYear();   // ✅ 년도 (YYYY)
            const month = now.getMonth() + 1; // ✅ 월 (0부터 시작하므로 +1)
            const day = now.getDate();        // ✅ 일
            const hours = now.getHours();     // ✅ 시
            const minutes = now.getMinutes(); // ✅ 분
            const seconds = now.getSeconds(); // ✅ 초

            const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

            const newMessage = {
                memberId: loginMember.id,
                participantId: Number(target.getAttribute("data-index")),
                messageContent: textValue.value
            };

            await insertMessage(newMessage);

            messageDiv.classList.add("joy-1udr1jv")

            messageDiv.innerHTML = `
                <div class="joy-1y9kyp6">
                    <div class="joy-e6pf26">
                        <div class=" joy-3diwkv">
                            <p class=" joy-1kp8wdt">${textValue.value}</p>
                        </div>
                    </div>
                    <p class=" joy-9nvto4">${formattedDate}</p>
                </div>
            `;

            messageListWrapper.appendChild(messageDiv);

            textValue.value = "";

        }
        // 메세지 insert

        // 메세지 삭제
        const deleteMessage = async (message) => {
            console.log(message);
            await fetch("/message/deleteMessage", {
                method: "put",
                body: JSON.stringify(message),
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            })
        }

        if (e.target.closest(".uploadButton_01")) {
            const target = e.target.closest(".uploadButton_01");
            const deleteInfo = {
                id: Number(target.getAttribute("data-index")),
                messageType: target.getAttribute("data-type")
            }

            await deleteMessage(deleteInfo)
            e.target.closest(".messageWrapper").remove();
        }
        // 메세지 삭제

        // 대화창 삭제
        if (e.target.closest(".joy-14llzfj")) {
            const existDiv = document.querySelector("#menu-button");
            const targetInfo = e.target.closest(".joy-14llzfj")
            const buttonWrap = e.target.closest(".joy-1j6tmez");
            const menuButton = e.target.closest(".joy-14llzfj");

            if (existDiv) {
                existDiv.remove();
                return;
            }

            menuDiv.innerHTML = `
                <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
                    <button type ="button" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p" data-index=${targetInfo.getAttribute("data-index")}>
                        <p class=" button_text_01">대화방 삭제</p>
                    </button>
                </ul>
                `;

            const absoluteTop =
                menuButton.offsetTop + menuButton.offsetHeight;
            const absoluteLeft = menuButton.offsetLeft;

            menuDiv.style.position = "absolute";
            menuDiv.style.top = `${absoluteTop}px`;
            menuDiv.style.left = `${absoluteLeft - 50}px`;

            buttonWrap.appendChild(menuDiv);
        }

        const deleteMessageList = async (participantId) => {
            console.log(participantId);
            await fetch("/message/deleteMessageListByParticipantId", {
                method: "put",
                body: JSON.stringify(participantId),
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            })
        }

        if (e.target.closest(".joy-1nwwb6p")) {
            const target = e.target.closest(".joy-1nwwb6p");
            const participantId = Number(target.getAttribute("data-index"));
            if (confirm("정말 삭제하시겠습니까?")) {
                await deleteMessageList(participantId);

                messageList.innerHTML = ``;
                await getMessageListAsync(renderMessageList);
            }
        }
        // 대화창 삭제
    })


    //     만들기버튼 클릭시 나오는 ai코멘트
    function getAicomment(callback, text = "날씨") {
        fetch(`/bot/chat?prompt=${encodeURIComponent(text)}`)
            .then(res => {
                if (!res.ok) throw new Error("AI 응답 실패: " + res.status);
                return res.text();
            })
            .then(aiText => {
                if (callback) callback(aiText);
            })
            .catch(err => {
                console.error("AI 코멘트 로딩 실패:", err);
            });
    }

    getAicomment((response) => {
        const commentElement = document.querySelector(".dayly-comment");
        if (commentElement) {
            commentElement.innerText = response;
        }
    }, "긍정적인 마인드를 가지고 살아갈 수 있게 하고싶은 문구를 보여줘 이모티콘도 하나 포함시켜줬으면 좋겠어");

    //     만들기버튼 클릭시 나오는 ai코멘트
});
