document.addEventListener("DOMContentLoaded", () => {
    const nextBtn = document.querySelector(".preview-btn");
    const postBtn = document.querySelector(".post-btn");
    const cancelBtn = document.querySelector(".cancel-bnt");
    const editorZone = document.querySelector(".keep-editor-selection-zone");
    const summernote = document.querySelector(".summernote");
    const modalContent = document.querySelector(".FlgModal-root-need .jk-feelog-div018");

    // 최초 로딩 시, 종료 버튼 이벤트 설정
    cancelBtn.addEventListener("click", (event) => {
        event.preventDefault();
        if (cancelBtn.classList.contains("cancel-bnt")) {
            window.location.href = '/';
        }
    });

    let noteEditor = summernote;
    while (noteEditor && !noteEditor.classList.contains("note-editor")) {
        noteEditor = noteEditor.parentElement;
    }

    nextBtn.addEventListener("click", () => {
        summernote.style.display = "none";
        const noteEditor = document.querySelector(".note-editor");
        if (noteEditor) noteEditor.style.setProperty("display", "none", "important");

        const existingInline = editorZone.querySelector(".inline-publish-section");
        if (existingInline) existingInline.remove();

        const clone = document.createElement("div");
        clone.classList.add("inline-publish-section");
        clone.innerHTML = modalContent.innerHTML;
        editorZone.appendChild(clone);

        const noteContent = $(".summernote").summernote("code");
        clone.querySelector("#preview-section").innerHTML = noteContent;

        const titleInput = document.querySelector("#title-input");
        if (titleInput) {
            const titleValue = titleInput.value.trim() || "제목 없음";
            const titleText = document.createElement("p");
            titleText.className = titleInput.className;
            titleText.textContent = titleValue;
            titleText.id = "title-fixed";

            const hiddenTitleInput = document.createElement("input");
            hiddenTitleInput.type = "hidden";
            hiddenTitleInput.name = "postTitle";
            hiddenTitleInput.value = titleValue;

            titleInput.parentNode.replaceChild(titleText, titleInput);
            titleText.insertAdjacentElement("afterend", hiddenTitleInput);
        }

        nextBtn.style.display = "none";
        postBtn.style.display = "block";

        cancelBtn.classList.remove("cancel-bnt");
        cancelBtn.classList.add("back-btn");
        cancelBtn.setAttribute("aria-label", "뒤로가기");

        cancelBtn.replaceWith(cancelBtn.cloneNode(true));

        const newCancelBtn = document.querySelector(".back-btn");
        newCancelBtn.onclick = () => {
            summernote.style.display = "";
            if (noteEditor) {
                noteEditor.style.setProperty("display", "block", "important");
                noteEditor.style.setProperty("margin-left", "auto", "important");
                noteEditor.style.setProperty("margin-right", "auto", "important");
                noteEditor.style.setProperty("max-width", "742px", "important");
            }

            const titleFixed = document.querySelector("#title-fixed");
            if (titleFixed) {
                const titleInput = document.createElement("input");
                titleInput.type = "text";
                titleInput.className = titleFixed.className;
                titleInput.value = titleFixed.textContent;
                titleInput.id = "title-input";
                titleInput.placeholder = "제목을 입력하세요";

                titleFixed.parentNode.replaceChild(titleInput, titleFixed);
                const hiddenInput = document.querySelector("input[name='postTitle']");
                if (hiddenInput) hiddenInput.remove();
            }

            const inline = editorZone.querySelector(".inline-publish-section");
            if (inline) inline.remove();

            postBtn.style.display = "none";
            nextBtn.style.display = "block";

            cancelBtn.classList.remove("back-btn");
            cancelBtn.classList.add("cancel-bnt");
            cancelBtn.setAttribute("aria-label", "종료");
            cancelBtn.onclick = () => {
                window.location.href = '/';
            };
        };

        initSelectDropdown(clone);
        initFileUpload(clone);
        initTagInput(clone);
    });
});

function initSelectDropdown(container) {
    const toggleBtn = container.querySelector("#select-toggle");
    const optionList = container.querySelector("#select-options");
    const options = optionList?.querySelectorAll("li");
    const postTypeMap = {
        "포스트": "POST",
        "응원글": "CHEERING"
    };
    const postTypeReverseMap = {
        POST: "포스트",
        CHEERING: "응원글"
    };

    if (!toggleBtn || !optionList) return;

    // 초기 텍스트 설정
    const hiddenInput = container.querySelector("input[name='postType']");
    if (hiddenInput && hiddenInput.value) {
        toggleBtn.textContent = postTypeReverseMap[hiddenInput.value] || "포스트";
        options.forEach(li => {
            if (li.textContent.trim() === postTypeReverseMap[hiddenInput.value]) {
                li.classList.add("Flg-selected");
                li.setAttribute("aria-selected", "true");
            }
        });
    }

    toggleBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        optionList.toggleAttribute("hidden");
        toggleBtn.setAttribute("aria-expanded", !optionList.hasAttribute("hidden"));
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

        const value = postTypeMap[clicked.textContent.trim()];
        if (hiddenInput && value) hiddenInput.value = value;
    });

    document.addEventListener("click", (e) => {
        if (!toggleBtn.contains(e.target) && !optionList.contains(e.target)) {
            optionList.setAttribute("hidden", "");
            toggleBtn.setAttribute("aria-expanded", "false");
        }
    });
}

function initFileUpload(container) {
    const fileButton = container.querySelector(".jk-feelog-btn007");
    const fileInput = container.querySelector("#hidden-file-input");
    const form = document.querySelector("form");

    // preview 위치를 .jk-feelog-div023 아래로 변경
    const preview = container.querySelector(".file-preview") || (() => {
        const div = document.createElement("div");
        div.className = "file-preview";
        const target = container.querySelector(".jk-feelog-div023")?.querySelector(".jk-feelog-btn007")?.parentElement;
        target?.insertAdjacentElement("afterend", div);
        return div;
    })();

    if (!fileButton || !fileInput) {
        console.warn("파일 업로드 버튼 또는 파일 입력이 존재하지 않음");
        return;
    }

    // 기존 이벤트 제거 후 새로 바인딩 (중복 방지)
    const clonedInput = fileInput.cloneNode(true);
    fileInput.replaceWith(clonedInput);

    fileButton.addEventListener("click", (e) => {
        e.preventDefault();
        clonedInput.value = ""; // 초기화
        clonedInput.click();
    });

    clonedInput.addEventListener("change", (e) => {
        const file = e.target.files[0];
        if (!file) return;

        const formData = new FormData();
        formData.append("file", file);

        fetch("/files/upload", {
            method: "POST",
            body: formData,
        })
            .then((res) => res.json())
            .then((data) => {
                const filePath = data.thumbnail?.filePath || data.filePath;
                const fileName = data.thumbnail?.fileName || data.fileName;

                if (!filePath || !fileName) {
                    alert("대표 이미지 정보가 누락되어 있습니다.");
                    return;
                }

                const imageUrl = "/files/display?path=" + filePath + "/" + fileName;

                preview.innerHTML = `
                <div class="preview-wrapper" style="position: relative; display: inline-block;">
                    <img src="${imageUrl}" style="max-width: 100px; border-radius: 8px;" />
                    <button type="button" class="delete-thumbnail-btn" style="
                        position: absolute;
                        top: 4px;
                        right: 4px;
                        background: rgba(0,0,0,0.5);
                        border: none;
                        color: white;
                        border-radius: 50%;
                        width: 24px;
                        height: 24px;
                        font-weight: bold;
                        cursor: pointer;
                    ">×</button>
                </div>`;

                ["postFilePath", "postFileName"].forEach((key) => {
                    form.querySelector(`input[name='${key}']`)?.remove();
                });

                const inputs = [
                    { name: "postFilePath", value: filePath },
                    { name: "postFileName", value: fileName },
                ];

                inputs.forEach(({ name, value }) => {
                    const input = document.createElement("input");
                    input.type = "hidden";
                    input.name = name;
                    input.value = value;
                    form.appendChild(input);
                });

                preview.querySelector(".delete-thumbnail-btn").addEventListener("click", () => {
                    preview.innerHTML = "";
                    clonedInput.value = "";
                    ["postFilePath", "postFileName"].forEach((key) => {
                        form.querySelector(`input[name='${key}']`)?.remove();
                    });
                });
            })
            .catch(() => alert("대표 이미지 업로드 실패"));
    });
    // 초기 렌더된 X 버튼에도 이벤트 연결
    const existingDelete = container.querySelector(".file-preview .delete-thumbnail-btn");
    if (existingDelete) {
        existingDelete.addEventListener("click", () => {
            preview.innerHTML = "";
            clonedInput.value = "";
            ["postFilePath", "postFileName"].forEach((key) => {
                form.querySelector(`input[name='${key}']`)?.remove();
            });
        });
    }
}


function initTagInput(container) {
    const input = container.querySelector(".FlgInput-input-need");
    const tagBox = container.querySelector(".jk-feelog-div025");

    const tags = new Set();
    const form = document.querySelector("form");
    const existing = form.querySelectorAll("input[name='tags']");
    existing.forEach(tag => tag.value && tags.add(tag.value));

    renderTags();

    const MAX_TAGS = 5;
    const MAX_TAG_LENGTH = 20;
    const TAG_PATTERN = /^[ㄱ-ㅎ가-힣a-zA-Z0-9_]+$/;

    function renderTags() {
        tagBox.querySelectorAll(".FlgChip-root-need").forEach(el => el.remove());
        form.querySelectorAll("input[name='tags']").forEach(el => el.remove());

        tags.forEach(text => {
            const tagEl = document.createElement("div");
            tagEl.className = "FlgChip-root-need FlgChip-colorPrimary FlgChip-sizeMd-need FlgChip-variantSoft-need joy-1g753be";
            tagEl.innerHTML = `
                <span class="FlgChip-label-need FlgChip-label-needMd jk-feelog-span006">${text}</span>
                <span class="FlgChip-endDecorator joy-1i201st">
                    <button class="FlgChipDelete-root FlgChipDelete-variantSoft FlgChipDelete-colorPrimary joy-1rgf1fl" type="button">
                        <svg viewBox="0 0 24 24" width="24" height="24" fill="none">
                            <path d="M17.979 7.354a.937.937 0 0 0-1.324-1.324l-4.65 4.648-4.651-4.653A.937.937 0 0 0 6.03 7.35l4.648 4.649-4.653 4.652a.937.937 0 0 0 1.324 1.324l4.649-4.648 4.652 4.652a.937.937 0 0 0 1.324-1.324l-4.648-4.648 4.652-4.652Z" fill="currentcolor"></path>
                        </svg>
                    </button>
                </span>`;
            tagBox.insertBefore(tagEl, input.closest(".FlgInput-root-need"));

            const hiddenInput = document.createElement("input");
            hiddenInput.type = "hidden";
            hiddenInput.name = "tags";
            hiddenInput.value = text;
            form.appendChild(hiddenInput);
        });
    }

    input.addEventListener("keydown", (e) => {
        if (e.key === "Enter" || e.key === " ") {
            e.preventDefault();
            const value = input.value.trim();
            const tagWords = value.split(/\s+/);

            tagWords.forEach((word) => {
                if (word && word.length <= MAX_TAG_LENGTH && TAG_PATTERN.test(word) && !tags.has(word)) {
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
            const text = e.target.closest(".FlgChip-root-need").querySelector(".FlgChip-label-need").textContent;
            tags.delete(text);
            renderTags();
        }
    });
}
