document.addEventListener("DOMContentLoaded", () => {
    const nextBtn = document.querySelector(".preview-btn");
    const postBtn = document.querySelector(".post-btn");
    const cancelBtn = document.querySelector(".cancel-bnt");
    const editorZone = document.querySelector(".keep-editor-selection-zone");
    const summernote = document.querySelector(".summernote");
    const modalContent = document.querySelector(".FlgModal-root-need .jk-feelog-div018");

    let noteEditor = summernote;
    while (noteEditor && !noteEditor.classList.contains("note-editor")) {
        noteEditor = noteEditor.parentElement;
    }

    nextBtn.addEventListener("click", () => {
        summernote.style.display = "none";
        const noteEditor = document.querySelector(".note-editor");
        if (noteEditor)
            noteEditor.style.setProperty("display", "none", "important");

        const existingInline = editorZone.querySelector(".inline-publish-section");
        if (existingInline) existingInline.remove();

        const clone = document.createElement("div");
        clone.classList.add("inline-publish-section");
        clone.innerHTML = modalContent.innerHTML;
        editorZone.appendChild(clone);

        const noteContent = $(".summernote").summernote("code");

        clone.querySelector("#preview-section").innerHTML = noteContent;
        const imgtag1 = document.querySelector("#preview-section>p>img");
        console.log(imgtag1)

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

        cancelBtn.onclick = () => {
            summernote.style.display = "";
            if (noteEditor) {
                noteEditor.style.setProperty("display", "block", "important");
                noteEditor.style.setProperty("margin-left", "auto", "important");
                noteEditor.style.setProperty("margin-right", "auto", "important");
                noteEditor.style.setProperty("max-width", "742px", "important");
            }

            const zone = document.querySelector(".keep-editor-selection-zone");
            if (zone) zone.style.setProperty("justify-content", "center", "important");

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
            cancelBtn.onclick = null;
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

    if (!toggleBtn || !optionList) return;

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
        const hiddenInput = container.querySelector("input[name='postType']");
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
    const preview = container.querySelector(".file-preview");
    const form = document.querySelector("form");

    if (!fileButton || !fileInput) return;

    fileButton.addEventListener("click", () => fileInput.click());

    fileInput.addEventListener("change", (e) => {
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
                const fileDTO = data.thumbnail;
                const imageUrl = "/files/display?path=" + fileDTO.filePath + "/" + fileDTO.fileName;

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
                    const oldInput = form.querySelector(`input[name='${key}']`);
                    if (oldInput) oldInput.remove();
                });

                const pathInput = document.createElement("input");
                pathInput.type = "hidden";
                pathInput.name = "postFilePath";
                pathInput.value = fileDTO.filePath;
                form.appendChild(pathInput);

                const nameInput = document.createElement("input");
                nameInput.type = "hidden";
                nameInput.name = "postFileName";
                nameInput.value = fileDTO.fileName;
                form.appendChild(nameInput);

                preview.querySelector(".delete-thumbnail-btn").addEventListener("click", () => {
                    preview.innerHTML = "";
                    fileInput.value = "";
                    [pathInput, nameInput].forEach(input => input.remove());
                });
            })
            .catch(() => alert("대표 이미지 업로드 실패"));
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
        tagBox.querySelectorAll(".FlgChip-root-need").forEach(el => el.remove());
        const form = document.querySelector("form");
        form.querySelectorAll("input[name='tags']").forEach(el => el.remove());

        tags.forEach(text => {
            const tagEl = document.createElement("div");
            tagEl.className =
                "FlgChip-root-need FlgChip-colorPrimary FlgChip-sizeMd-need FlgChip-variantSoft-need joy-1g753be";
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

    const textarea = document.getElementById("ctrlZ");
    textarea.addEventListener("keydown", (e) => {
        if (e.key === "z" && e.ctrlKey) {
            // 또는 event.keyCode === 90 && event.ctrlKey
            e.preventDefault();
            console.log("되돌리기");
        }
    });
    const textarea1 = document.getElementById("ctrlZ1");
    textarea1.addEventListener("keydown", (e) => {
        if (e.key === "z" && e.ctrlKey) {
            // 또는 event.keyCode === 90 && event.ctrlKey
            e.preventDefault();
            console.log("되돌리기");
        }
    });
    document.querySelector('.note-editable').addEventListener('keydown', (e) => {
        if (e.ctrlKey && e.key === 'z') {
            e.preventDefault();
            console.log("되돌리기");
        }
    });
}
