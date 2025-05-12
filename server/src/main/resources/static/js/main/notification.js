document.addEventListener("DOMContentLoaded", () => {
    const notificationBtn = document.getElementById("notificationBtn");
    const notificationMenu = document.getElementById("notificationMenu");
    const notificationCount = document.querySelector(".feelog-header-topSpan01");


    fetch("/main/notifications/unread-count")
        .then(res => {
            if (res.status === 401) return null;
            return res.json();
        })
        .then(count => {
            if (count > 0) {
                if (notificationCount) {
                    notificationCount.style.display = "inline-block";
                    notificationCount.innerText = count;
                }
            } else {
                if (notificationCount) {
                    notificationCount.style.display = "none";
                }
            }
        })
        .catch(err => console.error("알림 수 가져오기 실패", err));

    loadNotifications();


    let isOpen = false;

    function loadNotifications() {
        fetch("/main/notifications")
            .then(res => {
                if (res.status === 401) return []; // 비로그인이면 빈 배열
                return res.json();
            })
            .then(data => {
                const limitedData = data.slice(0, 15);
                renderNotifications(limitedData);
            })
            .catch(err => console.error("알림 가져오기 실패", err));
    }

    function renderNotifications(notifications) {
        let html = `
            <div class="FlgStack-root-need jk-feelog-div031" data-first-child="" data-last-child="">
                <div class="FlgStack-root-need joy-1dox06v">
                    <div class="FlgStack-root-need joy-dmwqxb">
                        <a href="/myPage/notify-reply-list" class="FlgButton-variantPlain FlgButton-sizeMd-need joy-zok3ae">
                            <p class="joy-mkgk3h">알림</p>
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="joy-1w4q56z">
                                <path d="M16.416 11.332a.934.934 0 0 1 0 1.325l-7.5 7.504a.937.937 0 0 1-1.324-1.324L14.428 12l-6.84-6.84a.937.937 0 0 1 1.324-1.324l7.504 7.495Z" fill="currentColor"></path>
                            </svg>
                        </a>
                        <button class="FlgButton-variantPlain FlgButton-sizeSm-need joy-fsdjpg" type="button">
                            모두 읽기
                        </button>
                    </div>
                    <hr class="jk-feelog-hr01" />
                </div>
                <div class="FlgStack-root-need joy-14vbmfy">
        `;

        if (notifications.length === 0) {
            html += `
                <div style="padding: 16px; text-align: center;">
                    알림이 없습니다.
                </div>
            `;
        } else {
            notifications.forEach(n => {
                const profileImg = (n.senderProfilePath && n.senderProfileName)
                    ? `/files/display?path=${n.senderProfilePath}/${n.senderProfileName}`
                    : `/images/avatar_blank.png`;

                const thumbnailImg = (n.thumbnailPath && n.thumbnailName)
                    ? `/files/display?path=${n.thumbnailPath}/${n.thumbnailName}`
                    : null;

                html += `
                    <div class="FlgBox-root-need joy-fwihks">
                        <span class="jk-feelog-span012">
                            <a href="/feelog.com/@${n.senderNickname}" class="jk-feelog-a-012">
                                <div class="FlgBox-root-need joy-1725qqy">
                                    <div class="FlgBox-root-need jk-feelog-div056">
                                        <img src="${profileImg}" alt="" style="position: absolute; height: 100%; width: 100%; object-fit: cover;">
                                    </div>
                                </div>
                            </a>
                            ${(n.notificationChecked === "UNREAD" || n.notificationChecked === "안읽음") ? `<span class="FlgBadge-anchorOriginTopLeft joy-yhhqut"></span>` : ``}
                        </span>
                        <div class="FlgStack-root-need joy-1ofqig9">
                            <div class="FlgStack-root-need joy-4c2guu">
                                <p class="joy-suf4y6">
                                    <a href="/feelog.com/@${n.senderNickname}" class="FlgLink-inherit joy-ko48tn">
                                        <span class="FlgTypography-title-sm joy-9avztx">${n.senderNickname}</span>
                                    </a>
                                    <span class="joy-colylv">${n.messageSummary}</span>
                                    <span class="joy-ap3vt9">${formatTimeAgo(n.createdDate)}</span>
                                </p>
                            </div>
                            ${n.subSummary ? `
                                <div class="FlgStack-root-need joy-1ofqig9">
                                    <div class="FlgStack-root-need joy-1efus4g">
                                        <a href="${n.subLink || '#'}" class="joy-kynq33" target="_blank">
                                            <div class="FlgStack-root-need joy-16besyk">
                                                <div class="FlgStack-root-need joy-mbssy4">
                                                    <span class="FlgTypography-title-sm joy-1uuuh8o">${n.subSummary}</span>
                                                    ${n.subSubSummary ? `<span class="joy-16ts1gy">${n.subSubSummary}</span>` : ``}
                                                </div>
                                                ${thumbnailImg ? `
                                                    <div class="FlgBox-root-need joy-3rf2es">
                                                        <img src="${thumbnailImg}" alt="" style="position: absolute; height: 100%; width: 100%; object-fit: cover;">
                                                    </div>
                                                ` : ``}
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            ` : ``}
                        </div>
                    </div>
                `;
            });
        }

        html += `
                </div>
            </div>
        `;

        notificationMenu.innerHTML = html;

        updateMarkAllAsReadBtn(); // 모두 읽기 버튼 다시 바인딩

        const unreadCount = notifications.filter(n => n.notificationChecked === "UNREAD" || n.notificationChecked === "안읽음").length;
        if (notificationCount) {
            if (unreadCount > 0) {
                notificationCount.style.display = "inline-block";
                notificationCount.innerText = unreadCount;
            } else {
                notificationCount.style.display = "none";
            }
        }
    }

    function updateMarkAllAsReadBtn() {
        const markAllBtn = document.querySelector(".joy-fsdjpg");
        if (markAllBtn) {
            markAllBtn.addEventListener("click", () => {
                // 서버에 읽음처리 요청
                fetch("/main/notifications/mark-all-read", {
                    method: "POST"
                })
                    .then(res => {
                        if (res.status === 401) {
                            console.warn("비로그인 상태로 알림 읽음 요청 무시됨");
                            return;
                        }

                        if (res.ok) {
                            // 성공했으면 UI도 변경
                            document.querySelectorAll(".joy-yhhqut").forEach(badge => badge.style.display = "none");
                            if (notificationCount) notificationCount.style.display = "none";

                            // 현재 메모리 상 알림들도 상태를 "읽음"으로 바꾼다
                            document.querySelectorAll(".joy-colylv").forEach(msg => {
                                if (msg.innerText.includes("새 알림이")) {
                                    msg.innerText = msg.innerText.replace("새 알림이", "읽은 알림");
                                }
                            });
                        } else {
                            console.error("모두 읽기 실패");
                        }
                    })
                    .catch(err => console.error("모두 읽기 요청 실패", err));
            });
        }
    }

    function formatTimeAgo(createdDate) {
        const created = new Date(createdDate);
        const now = new Date();
        const diff = Math.floor((now - created) / (1000 * 60)); // 분 단위

        if (diff < 1) return "방금 전";
        if (diff < 60) return `${diff}분 전`;
        const hours = Math.floor(diff / 60);
        if (hours < 24) return `${hours}시간 전`;
        const days = Math.floor(hours / 24);
        return `${days}일 전`;
    }

    function updateNotificationMenuPosition() {
        const rect = notificationBtn.getBoundingClientRect();
        const menuWidth = notificationMenu.offsetWidth;
        const viewportWidth = window.innerWidth;

        const left = rect.left - 440;
        const top = rect.bottom + 14;
        const adjustedLeft = Math.min(left, viewportWidth - menuWidth - 8);

        notificationMenu.style.position = "absolute";
        notificationMenu.style.left = `${adjustedLeft}px`;
        notificationMenu.style.top = `${top}px`;
    }
    function closeAllMenusExcept(except) {
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


    function toggleNotificationMenu(e) {
        e.stopPropagation();
        isOpen = !isOpen;
        if (isOpen) {
            closeAllMenusExcept("notification");
            loadNotifications();
            updateNotificationMenuPosition();
            notificationMenu.style.display = "block";
        } else {
            notificationMenu.style.display = "none";
        }
    }

    function closeNotificationMenu() {
        if (isOpen) {
            isOpen = false;
            notificationMenu.style.display = "none";
        }
    }

    if (notificationBtn) {
        notificationBtn.addEventListener("click", toggleNotificationMenu);

        document.addEventListener("click", function (e) {
            if (!notificationMenu.contains(e.target) && !notificationBtn.contains(e.target)) {
                closeNotificationMenu();
            }
        });
    }

    window.addEventListener("resize", () => {
        if (isOpen) {
            updateNotificationMenuPosition();
        }

    });

    fetch("/main/channel/my")
        .then(res => {
            if (!res.ok) throw new Error("채널 없음");
            return res.json();
        })
        .then(data => {
            // 채널이 있을 경우: 아무 것도 하지 않음 (href 유지)
        })
        .catch(() => {
            // 채널이 없을 경우: 링크 클릭 막고 리디렉션
            const requiresChannel = [
                "/main/mind-log",
                "/main/post"
            ];

            document.querySelectorAll("a.feelog-header-mdl-a01").forEach(link => {
                const href = link.getAttribute("href");
                if (requiresChannel.includes(href)) {
                    link.addEventListener("click", function (e) {
                        e.preventDefault();
                        window.location.href = "/myPage/make-channel";
                    });
                }
            });
        });

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
                        <img alt="channel" loading="lazy" decoding="async" data-nimg="fill"
                            src="${data && data.channelFilePath && data.channelFileName
                ? `/files/display?path=${data.channelFilePath}/${data.channelFileName}`
                : 'https://d33pksfia2a94m.cloudfront.net/assets/img/avatar/blog_blank.png?w=50&h=50&q=65'}"
                            style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;">
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



});
