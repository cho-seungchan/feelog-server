// 전체 스크립트 시작

document.addEventListener("DOMContentLoaded", () => {
    const nextBtn = document.querySelector(".next-btn");
    const postBtn = document.querySelector(".post-btn");
    const cancelBtn = document.querySelector(".cancel-bnt");
    const editorZone = document.querySelector(".keep-editor-selection-zone");
    const summernote = document.querySelector(".summernote");
    const modalContent = document.querySelector(".FlgModal-root-need .jk-feelog-div018");

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
        if (noteEditor)
            noteEditor.style.setProperty("display", "none", "important");

        const existingInline = editorZone.querySelector(".inline-publish-section");
        if (existingInline) existingInline.remove();

        const clone = document.createElement("div");
        clone.classList.add("inline-publish-section");
        clone.innerHTML = modalContent.innerHTML;
        editorZone.appendChild(clone);

        function stripHtmlTags(html) {
            const temp = document.createElement("div");
            temp.innerHTML = html;
            return temp.textContent || temp.innerText || "";
        }

        const titleInput = document.querySelector("#title-input");
        if (titleInput) {
            const titleValue = titleInput.value.trim() || "제목 없음";
            const titleText = document.createElement("p");
            titleText.className = titleInput.className;
            titleText.textContent = titleValue;
            titleText.id = "title-fixed";

            const hiddenTitleInput = document.createElement("input");
            hiddenTitleInput.type = "hidden";
            hiddenTitleInput.name = "diaryTitle";
            hiddenTitleInput.value = titleValue;

            titleInput.parentNode.replaceChild(titleText, titleInput);
            titleText.insertAdjacentElement("afterend", hiddenTitleInput);
        }

        nextBtn.style.display = "none";
        postBtn.style.display = "block";

        cancelBtn.classList.remove("cancel-bnt");
        cancelBtn.classList.add("back-btn");
        cancelBtn.setAttribute("aria-label", "뒤로가기");

        // 기존 종료 이벤트 삭제
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

            const zone = document.querySelector(".keep-editor-selection-zone");
            if (zone) {
                zone.style.setProperty("justify-content", "center", "important");
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

                const hiddenInput = document.querySelector("input[name='diaryTitle']");
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

        let title = "";
        const titleInputEl = document.querySelector("#title-input");
        if (titleInputEl) {
            title = titleInputEl.value || "";
        } else {
            const fixedTitleEl = document.querySelector("#title-fixed");
            if (fixedTitleEl) {
                title = fixedTitleEl.textContent || "";
            }
        }

        const contentHtml = $(".summernote").summernote("code");
        const plainTextContent = stripHtmlTags(contentHtml);
        const contents = title.trim() + " " + plainTextContent.trim();

        fetch("/main/api/feeling-check", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ contents })
        })
            .then(res => res.json())
            .then(data => {
                const { score } = data;
                return fetch(`/main/feeling-score/${score}`);
            })
            .then(res => res.json())
            .then(scoreData => {
                const { scoreMessage, scoreFilePath, scoreFileName, id } = scoreData;
                console.log("[DEBUG] clone DOM:", clone.outerHTML);
                renderEmotionFeedback(clone, scoreData);

                const hiddenScoreInput = document.createElement("input");
                hiddenScoreInput.type = "hidden";
                hiddenScoreInput.name = "diaryScore";
                hiddenScoreInput.value = id;
                clone.appendChild(hiddenScoreInput);
            })
            .catch(err => {
                console.error("감정 검사 실패:", err);
            });

        initSelectDropdown(clone);
        initSelectDropdown2nd(clone);
        initFileUpload(clone);
        initTagInput(clone);
    });
});

function renderEmotionFeedback(container, scoreData) {
    const { scoreMessage, scoreFilePath, scoreFileName, id } = scoreData;

    const previewBox = container.querySelector("div");
    if (previewBox) {
        previewBox.innerHTML = `
            <div style="padding: 20px; text-align: center;">
                <p style="font-size: 20px; font-weight: bold;">Feelog AI 생각</p>
                <img src="/files/display?path=${scoreFilePath}/${scoreFileName}" 
                     alt="감정 이미지" 
                     style="max-height: 200px; margin-bottom: 10px;" />
                <p style="font-size: 16px; font-weight: bold;">${scoreMessage}</p>
                <p style="color: #666;">AI 감정 점수: ${id}</p>
                <label for="manual-score-select" style="font-weight: bold; margin-top: 10px;">
                    혹시 AI 평가가 마음에 들지 않으신가요? 지금 기분을 점수로 말해주세요!
                </label>
                <select id="manual-score-select" style="border-color: #dadadc; border-radius:4px; margin-top: 5px;">
                    ${[...Array(10).keys()].map(i => {
            const val = i + 1;
            return `<option value="${val}" ${val === id ? 'selected' : ''}>${val}점</option>`;
        }).join('')}
                </select>
            </div>
        `;

        // hidden input이 이미 있으면 갱신, 없으면 새로 생성
        let hiddenInput = container.querySelector("input[name='diaryScore']");
        if (!hiddenInput) {
            hiddenInput = document.createElement("input");
            hiddenInput.type = "hidden";
            hiddenInput.name = "diaryScore";
            container.appendChild(hiddenInput);
        }
        hiddenInput.value = id;

        // 셀렉트 변경 이벤트 연결
        const select = previewBox.querySelector("#manual-score-select");
        select.addEventListener("change", (e) => {
            const newScore = e.target.value;
            hiddenInput.value = newScore;

            fetch(`/main/feeling-score/${newScore}`)
                .then(res => res.json())
                .then(newScoreData => {
                    renderEmotionFeedback(container, newScoreData);
                })
                .catch(err => console.error("감정 점수 변경 실패:", err));
        });
    }
}
function initSelectDropdown(container) {
    const toggleBtn = container.querySelector("#select-toggle");
    const optionList = container.querySelector("#select-options");
    const options = optionList.querySelectorAll("li");
    const diaryOpenMap = {
        "전체 공개": "ALL",
        "구독자에게만 공개": "SUBSCRIBE",
        "비공개": "CLOSE"
    };

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

        // 여기에서 input[name='visibilityOption']에 값 설정
// 드롭다운 초기 텍스트 설정 (diaryOpen)
        const hiddenInput = container.querySelector("input[name='diaryOpen']");
        if (hiddenInput) {
            hiddenInput.value = diaryOpenMap[clicked.textContent.trim()];
        }
        const diaryOpenDisplayMap = {
            ALL: "전체 공개",
            SUBSCRIBE: "구독자에게만 공개",
            CLOSE: "비공개"
        };
        if (hiddenInput) {
            toggleBtn.textContent = diaryOpenDisplayMap[hiddenInput.value] || "전체 공개";
        }


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
    const diaryNameOpenMap = {
        "비공개(익명)": "UNKNOWN",
        "닉네임": "KNOWN"
    };

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

        // 여기에서 input[name='diaryNameOpen']에 값 설정
        const hiddenNameInput = container.querySelector("input[name='diaryNameOpen']");
        if (hiddenNameInput) {
            hiddenNameInput.value = diaryNameOpenMap[clicked2nd.textContent.trim()];
        }
        const diaryNameOpenDisplayMap = {
            KNOWN: "닉네임",
            UNKNOWN: "비공개(익명)"
        };
        if (hiddenNameInput) {
            toggleBtn2nd.textContent = diaryNameOpenDisplayMap[hiddenNameInput.value] || "비공개(익명)";
        }
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
    const form = document.querySelector("form");

    if (!fileButton || !fileInput) return;

    // 기존 렌더된 버튼도 삭제 이벤트 연결
    const existingDeleteBtn = preview.querySelector(".delete-thumbnail-btn");
    if (existingDeleteBtn) {
        existingDeleteBtn.addEventListener("click", () => {
            preview.innerHTML = "";
            fileInput.value = "";

            // 기존 hidden input도 제거
            ["postFilePath", "postFileName", "postFileSize"].forEach(key => {
                const input = form.querySelector(`input[name='${key}']`);
                if (input) input.remove();
            });
        });
    }

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
            .then(res => res.json())
            .then(data => {
                const fileDTO = data.thumbnail;
                const imageUrl = "/files/display?path=" + fileDTO.filePath + "/" + fileDTO.fileName;

                // 대표 이미지 미리보기
                if (preview) {
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
                }

                // 기존 hidden input 제거
                ["diaryFilePath", "diaryFileName", "diaryFileSize"].forEach((key) => {
                    const oldInput = form.querySelector(`input[name='${key}']`);
                    if (oldInput) oldInput.remove();
                });

                // 새로운 hidden input 추가
                const pathInput = document.createElement("input");
                pathInput.type = "hidden";
                pathInput.name = "diaryFilePath";
                pathInput.value = fileDTO.filePath;
                form.appendChild(pathInput);

                const nameInput = document.createElement("input");
                nameInput.type = "hidden";
                nameInput.name = "diaryFileName";
                nameInput.value = fileDTO.fileName;
                form.appendChild(nameInput);

                const sizeInput = document.createElement("input");
                sizeInput.type = "hidden";
                sizeInput.name = "diaryFileSize";
                sizeInput.value = fileDTO.fileSize;
                form.appendChild(sizeInput);

                // 삭제 버튼 이벤트
                preview.querySelector(".delete-thumbnail-btn").addEventListener("click", () => {
                    preview.innerHTML = "";
                    fileInput.value = "";

                    [pathInput, nameInput, sizeInput].forEach(input => input.remove());
                });
            })
            .catch(() => {
                alert("대표 이미지 업로드 실패");
            });
    });
}
function initTagInput(container) {
    const input = container.querySelector(".FlgInput-input-need");
    const tagBox = container.querySelector(".jk-feelog-div025");

    const tags = new Set();
    const MAX_TAGS = 5;
    const MAX_TAG_LENGTH = 20;
    const TAG_PATTERN = /^[ㄱ-ㅎ가-힣a-zA-Z0-9_]+$/;

    // 기존 hidden input에서 태그 복원
    const existingTagInputs = container.querySelectorAll("input[name='tags']");
    existingTagInputs.forEach(input => {
        if (input.value) {
            tags.add(input.value);
        }
    });

    renderTags(); // 초기 렌더링

    function renderTags() {
        tagBox.querySelectorAll(".FlgChip-root-need").forEach(el => el.remove());
        const form = document.querySelector("form");
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
            const text = e.target.closest(".FlgChip-root-need").querySelector(".FlgChip-label-need").textContent;
            tags.delete(text);
            renderTags();
        }
    });
}