document.addEventListener("DOMContentLoaded", () => {
    const nextBtn = document.querySelector(".next-btn");
    const postBtn = document.querySelector(".post-btn");
    const cancelBtn = document.querySelector(".cancel-bnt");
    const editorZone = document.querySelector(".keep-editor-selection-zone");
    const summernote = document.querySelector(".summernote");
    const modalContent = document.querySelector(
        ".FlgModal-root-need .jk-feelog-div018"
    );

    // note-editor 찾기
    let noteEditor = summernote;
    while (noteEditor && !noteEditor.classList.contains("note-editor")) {
        noteEditor = noteEditor.parentElement;
    }

    nextBtn.addEventListener("click", () => {
        // 1. 에디터 숨기기
        summernote.style.display = "none";
        const noteEditor = document.querySelector(".note-editor");
        if (noteEditor)
            noteEditor.style.setProperty("display", "none", "important");

        // 2. 기존 inline 영역 제거
        const existingInline = editorZone.querySelector(
            ".inline-publish-section"
        );
        if (existingInline) existingInline.remove();

        // 3. 모달 내용 복사하여 삽입
        const clone = document.createElement("div");
        clone.classList.add("inline-publish-section");
        clone.innerHTML = modalContent.innerHTML;
        editorZone.appendChild(clone);

        // 다음버튼 누르면 제목이 p 태그로 바뀌고, input은 사라짐
        const titleInput = document.querySelector("#title-input");
        if (titleInput) {
            const titleValue = titleInput.value.trim() || "제목 없음";
            const titleText = document.createElement("p");

            // 클래스 복사
            titleText.className = titleInput.className;
            titleText.textContent = titleValue;
            titleText.id = "title-fixed"; // 나중에 다시 input으로 돌릴 때 필요

            // <input> → <p>로 교체
            titleInput.parentNode.replaceChild(titleText, titleInput);
        }

        // 4. 버튼 전환
        nextBtn.style.display = "none";
        postBtn.style.display = "block";

        // 5. cancel 버튼 → back 버튼으로 역할 변경
        cancelBtn.classList.remove("cancel-bnt");
        cancelBtn.classList.add("back-btn");
        cancelBtn.setAttribute("aria-label", "뒤로가기");

        // 6. 뒤로가기 동작 정의
        cancelBtn.onclick = () => {
            // 에디터 다시 보이기
            summernote.style.display = "";
            if (noteEditor) {
                noteEditor.style.setProperty("display", "block", "important");
                noteEditor.style.setProperty(
                    "margin-left",
                    "auto",
                    "important"
                );
                noteEditor.style.setProperty(
                    "margin-right",
                    "auto",
                    "important"
                );
                noteEditor.style.setProperty("max-width", "742px", "important");
            }

            const zone = document.querySelector(".keep-editor-selection-zone");
            if (zone) {
                zone.style.setProperty(
                    "justify-content",
                    "center",
                    "important"
                );
            }

            // 제목 p 태그를 다시 input으로 교체
            const titleFixed = document.querySelector("#title-fixed");
            if (titleFixed) {
                const titleInput = document.createElement("input");

                titleInput.type = "text";
                titleInput.className = titleFixed.className;
                titleInput.value = titleFixed.textContent;
                titleInput.id = "title-input";
                titleInput.placeholder = "제목을 입력하세요";

                // <p> → <input>로 교체
                titleFixed.parentNode.replaceChild(titleInput, titleFixed);
            }

            // 발행 섹션 제거
            const inline = editorZone.querySelector(".inline-publish-section");
            if (inline) inline.remove();

            // 버튼 원복
            postBtn.style.display = "none";
            nextBtn.style.display = "block";

            // back → cancel 복원
            cancelBtn.classList.remove("back-btn");
            cancelBtn.classList.add("cancel-bnt");
            cancelBtn.setAttribute("aria-label", "종료");
            cancelBtn.onclick = null;
        };

        // 7. 이벤트 연결
        initSelectDropdown(clone);
        initSelectDropdown2nd(clone);
        initFileUpload(clone);
        initTagInput(clone);
    });
});

function initSelectDropdown(container) {
    const toggleBtn = container.querySelector("#select-toggle");
    const optionList = container.querySelector("#select-options");
    const options = optionList.querySelectorAll("li");

    if (!toggleBtn || !optionList) return;

    toggleBtn.addEventListener("click", (e) => {
        e.stopPropagation();

        // 다른 드롭다운 닫기
        document.querySelectorAll("[id^='select-options']").forEach((el) => {
            if (el !== optionList) {
                el.setAttribute("hidden", "");
            }
        });
        document.querySelectorAll("[id^='select-toggle']").forEach((btn) => {
            if (btn !== toggleBtn) {
                btn.setAttribute("aria-expanded", "false");
            }
        });

        // 현재 드롭다운 토글
        const isHidden = optionList.hasAttribute("hidden");
        if (isHidden) {
            optionList.removeAttribute("hidden");
            toggleBtn.setAttribute("aria-expanded", "true");
        } else {
            optionList.setAttribute("hidden", "");
            toggleBtn.setAttribute("aria-expanded", "false");
        }
    });

    optionList.addEventListener("click", (e) => {
        const clicked = e.target.closest("li");
        if (!clicked) return;

        options.forEach((opt) => {
            opt.classList.remove("Flg-selected");
            opt.setAttribute("aria-selected", "false");
        });

        clicked.classList.add("Flg-selected");
        clicked.setAttribute("aria-selected", "true");
        toggleBtn.textContent = clicked.textContent;

        optionList.setAttribute("hidden", "");
        toggleBtn.setAttribute("aria-expanded", "false");
    });

    document.addEventListener("click", (e) => {
        const isInside =
            toggleBtn.contains(e.target) || optionList.contains(e.target);
        if (!isInside) {
            optionList.setAttribute("hidden", "");
            toggleBtn.setAttribute("aria-expanded", "false");
        }
    });
}

function initSelectDropdown2nd(container) {
    const toggleBtn2nd = container.querySelector("#select-toggle2nd");
    const optionList2nd = container.querySelector("#select-options2nd");
    const options2nd = optionList2nd.querySelectorAll("li");

    if (!toggleBtn2nd || !optionList2nd) return;

    toggleBtn2nd.addEventListener("click", (e) => {
        e.stopPropagation();

        // 다른 열린 드롭다운 모두 닫기
        document.querySelectorAll("[id^='select-options']").forEach((el) => {
            if (el !== optionList2nd) {
                el.setAttribute("hidden", "");
            }
        });
        document.querySelectorAll("[id^='select-toggle']").forEach((btn) => {
            if (btn !== toggleBtn2nd) {
                btn.setAttribute("aria-expanded", "false");
            }
        });

        // 현재 드롭다운 토글
        const isHidden2nd = optionList2nd.hasAttribute("hidden");
        if (isHidden2nd) {
            optionList2nd.removeAttribute("hidden");
            toggleBtn2nd.setAttribute("aria-expanded", "true");
        } else {
            optionList2nd.setAttribute("hidden", "");
            toggleBtn2nd.setAttribute("aria-expanded", "false");
        }
    });

    optionList2nd.addEventListener("click", (e) => {
        const clicked2nd = e.target.closest("li");
        if (!clicked2nd) return;

        options2nd.forEach((opts) => {
            opts.classList.remove("Flg-selected");
            opts.setAttribute("aria-selected", "false");
        });

        clicked2nd.classList.add("Flg-selected");
        clicked2nd.setAttribute("aria-selected", "true");
        toggleBtn2nd.textContent = clicked2nd.textContent;

        optionList2nd.setAttribute("hidden", "");
        toggleBtn2nd.setAttribute("aria-expanded", "false");
    });

    document.addEventListener("click", (e) => {
        const isInside2nd =
            toggleBtn2nd.contains(e.target) || optionList2nd.contains(e.target);
        if (!isInside2nd) {
            optionList2nd.setAttribute("hidden", "");
            toggleBtn2nd.setAttribute("aria-expanded", "false");
        }
    });
}

function initFileUpload(container) {
    const fileButton = container.querySelector(".jk-feelog-btn007");
    const fileInput = container.querySelector("#hidden-file-input");
    const preview = container.querySelector(".file-preview");

    if (!fileButton || !fileInput) return;

    fileButton.addEventListener("click", () => fileInput.click());

    fileInput.addEventListener("change", (e) => {
        const files = Array.from(e.target.files);
        if (preview) {
            preview.innerHTML =
                files.length === 0
                    ? ""
                    : files
                          .map((f, i) => `📎 파일 ${i + 1}: ${f.name}`)
                          .join("<br>");
        }
    });
}

function initTagInput(container) {
    const input = container.querySelector(".FlgInput-input-need");
    const tagBox = container.querySelector(".jk-feelog-div025");

    const tags = new Set();
    const MAX_TAGS = 5;
    const MAX_TAG_LENGTH = 20;
    const TAG_PATTERN = /^[ㄱ-ㅎ가-힣a-zA-Z0-9_]+$/;

    function renderTags() {
        tagBox
            .querySelectorAll(".FlgChip-root-need")
            .forEach((el) => el.remove());

        tags.forEach((text) => {
            const tagEl = document.createElement("div");
            tagEl.className =
                "FlgChip-root-need FlgChip-colorPrimary FlgChip-sizeMd-need FlgChip-variantSoft-need joy-1g753be";
            tagEl.innerHTML = `
                <span class="FlgChip-label-need FlgChip-label-needMd jk-feelog-span006">${text}</span>
                <span class="FlgChip-endDecorator joy-1i201st">
                    <button class="FlgChipDelete-root FlgChipDelete-variantSoft FlgChipDelete-colorPrimary joy-1rgf1fl" type="button">
                        <svg viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M17.979 7.354a.937.937 0 0 0-1.324-1.324l-4.65 4.648-4.651-4.653A.937.937 0 0 0 6.03 7.35l4.648 4.649-4.653 4.652a.937.937 0 0 0 1.324 1.324l4.649-4.648 4.652 4.652a.937.937 0 0 0 1.324-1.324l-4.648-4.648 4.652-4.652Z" fill="currentcolor"></path>
                        </svg>
                    </button>
                </span>`;
            tagBox.insertBefore(tagEl, input.closest(".FlgInput-root-need"));
        });
    }

    input.addEventListener("keydown", (e) => {
        if (e.key === "Enter" || e.key === " ") {
            e.preventDefault();
            const value = input.value.trim();
            const tagWords = value.split(/\s+/);

            tagWords.forEach((word) => {
                if (
                    word &&
                    word.length <= MAX_TAG_LENGTH &&
                    TAG_PATTERN.test(word) &&
                    !tags.has(word)
                ) {
                    if (tags.size < MAX_TAGS) {
                        tags.add(word);
                        renderTags();
                    } else {
                        alert("최대 5개의 태그만 추가할 수 있습니다.");
                    }
                }
            });

            input.value = "";
        }
    });

    tagBox.addEventListener("click", (e) => {
        if (e.target.closest(".FlgChipDelete-root")) {
            const text = e.target
                .closest(".FlgChip-root-need")
                .querySelector(".FlgChip-label-need").textContent;
            tags.delete(text);
            renderTags();
        }
    });
}
