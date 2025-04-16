document.addEventListener("DOMContentLoaded", () => {
    const menu = document.getElementById("report-menu");
    const modal = document.querySelector(".report-detail");
    const reportBtn = document.getElementById("report-btn");

    let radioHandlersRegistered = false;

    // 메뉴 열기
    document.querySelectorAll(".more-btn").forEach((button) => {
        button.addEventListener("click", (e) => {
            e.stopPropagation();
            const isVisible = menu.style.display === "block";
            menu.style.display = "none";

            if (!isVisible) {
                const rect = button.getBoundingClientRect();
                const scrollTop =
                    window.scrollY || document.documentElement.scrollTop;
                const scrollLeft =
                    window.scrollX || document.documentElement.scrollLeft;

                menu.style.display = "block";
                menu.style.position = "absolute";
                menu.style.removeProperty("transform");
                menu.style.top = `${rect.bottom + scrollTop}px`;
                menu.style.left = `${rect.left + scrollLeft}px`;
                menu.style.zIndex = "1000";
                menu.style.backgroundColor = "#FFFFFF";
            }
        });
    });

    // 외부 클릭 시 메뉴 닫기
    document.addEventListener("click", () => {
        menu.style.display = "none";
    });

    // 신고 모달 열기
    reportBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        modal.style.display = "block";
        menu.style.display = "none";

        // 클릭 안되는 이슈 방지: pointer-events 복구
        modal.querySelectorAll(".FlgRadio-action").forEach((el) => {
            el.style.pointerEvents = "auto";
            el.style.cursor = "pointer";
            el.style.zIndex = "10";
        });

        modal.querySelectorAll('input[name="reason"]').forEach((input) => {
            input.style.pointerEvents = "auto";
            input.style.opacity = "0";
            input.style.position = "relative";
            input.style.zIndex = "10";
        });

        if (!radioHandlersRegistered) {
            registerRadioHandlers();
            radioHandlersRegistered = true;
        }
    });

    // 모달 닫기
    const closeBtn = modal.querySelector(".FlgModalClose-root-need");
    if (closeBtn) {
        closeBtn.addEventListener("click", () => {
            modal.style.display = "none";
        });
    }

    const cancelBtn = document.querySelector(".joy-1bxt4bb");
    if (cancelBtn) {
        cancelBtn.addEventListener("click", () => {
            if (modal) modal.style.display = "none";
        });
    }

    modal.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
});

function registerRadioHandlers() {
    // 실제 클릭 감지
    document.querySelectorAll(".FlgRadio-action").forEach((action) => {
        action.addEventListener("click", () => {
            const input = action.querySelector('input[type="radio"]');
            if (input) {
                input.checked = true;

                const changeEvent = new Event("change", { bubbles: true });
                input.dispatchEvent(changeEvent);
            }
        });
    });

    // 상태에 따라 클래스 동기화
    document.querySelectorAll('input[name="reason"]').forEach((radio) => {
        radio.addEventListener("change", () => {
            document
                .querySelectorAll(
                    ".FlgRadio-root, .FlgRadio-radio, .FlgRadio-action"
                )
                .forEach((el) => el.classList.remove("Flg-checked"));
            document.querySelectorAll(".FlgRadio-radio").forEach((el) => {
                el.classList.remove("joy-11c58eo");
                el.classList.add("joy-8ktuf5");
            });

            const action = radio.parentElement;
            const radioWrapper = action.parentElement;
            const root = radioWrapper.parentElement;

            if (action && radioWrapper && root) {
                action.classList.add("Flg-checked");
                radioWrapper.classList.add("Flg-checked");
                root.classList.add("Flg-checked");

                radioWrapper.classList.remove("joy-8ktuf5");
                radioWrapper.classList.add("joy-11c58eo");
            }
        });
    });
}
