document.addEventListener("DOMContentLoaded", () => {
    const notificationBtn = document.getElementById("notificationBtn");
    const notificationMenu = document.getElementById("notificationMenu");
    const notificationCount = document.querySelector(".feelog-header-topSpan01");

    let isOpen = false;

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
        const profileMenu = document.getElementById("profile-menu");
        if (profileMenu && except !== "profile") profileMenu.classList.add("hidden");

        if (notificationMenu && except !== "notification") {
            notificationMenu.style.display = "none";
            isOpen = false;
        }

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
        if (isOpen) updateNotificationMenuPosition();
    });

    // 모두 읽기 처리
    const markAllBtn = document.querySelector(".joy-fsdjpg");
    if (markAllBtn) {
        markAllBtn.addEventListener("click", () => {
            fetch("/main/notifications/mark-all-read", {
                method: "POST"
            })
                .then(res => {
                    if (res.ok) {
                        // 읽음 뱃지 숨기기
                        document.querySelectorAll(".joy-yhhqut").forEach(badge => badge.style.display = "none");

                        // 카운트 뱃지도 숨기기
                        if (notificationCount) notificationCount.style.display = "none";
                    }
                })
                .catch(err => console.error("모두 읽기 실패:", err));
        });
    }
});
