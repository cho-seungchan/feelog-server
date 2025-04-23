const uploadedFileIds = new Set();
const originalFileNames = new Set();
const originalTagContents = new Set();

$(document).ready(function () {
    // Summernote 초기화
    $(".summernote").summernote({
        height: 650,
        fontNames: [
            "Arial", "Noto Sans KR", "Jua", "Dongle", "Do Hyeon",
            "Gowun Dodum", "Poor Story", "Hi Melody", "Gamja Flower", "Gaegu"
        ],
        fontNamesIgnoreCheck: [
            "Noto Sans KR", "Jua", "Dongle", "Do Hyeon", "Gowun Dodum",
            "Poor Story", "Hi Melody", "Gamja Flower", "Gaegu"
        ],
        disableResizeEditor: true,
        callbacks: {
            onImageUpload: function (files) {
                for (let i = 0; i < files.length; i++) {
                    const formData = new FormData();
                    formData.append("file", files[i]);

                    $.ajax({
                        url: "/files/upload",
                        method: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (res) {
                            const fileDTO = res.thumbnail;
                            const imageUrl = "/files/display?path=" + fileDTO.filePath + "/" + fileDTO.fileName;

                            $(".summernote").summernote("insertImage", imageUrl);

                            const fileInfo = {
                                filePath: fileDTO.filePath,
                                fileName: fileDTO.fileName,
                                fileSize: fileDTO.fileSize
                            };

                            $.ajax({
                                url: "/main/post/image/save",
                                method: "POST",
                                contentType: "application/json",
                                data: JSON.stringify(fileInfo),
                                success: function (saveRes) {
                                    const fileId = saveRes.fileId;
                                    if (uploadedFileIds.has(fileId)) return;
                                    uploadedFileIds.add(fileId);

                                    const form = document.querySelector("form");
                                    if (!form.querySelector(`input[name='fileIds'][value='${fileId}']`)) {
                                        const input = document.createElement("input");
                                        input.type = "hidden";
                                        input.name = "fileIds";
                                        input.value = fileId;
                                        form.appendChild(input);
                                    }
                                },
                                error: function () {
                                    alert("이미지 DB 저장 실패");
                                }
                            });
                        },
                        error: function () {
                            alert("이미지 업로드 실패");
                        }
                    });
                }
            }
        }
    });

    // 기존 콘텐츠 적용
    $(".summernote").summernote("code", postContent);

    // 기존 이미지 추적
    const tempDiv = document.createElement("div");
    tempDiv.innerHTML = postContent;
    tempDiv.querySelectorAll("img").forEach(img => {
        const fileName = img.getAttribute("src").split('/').pop();
        originalFileNames.add(fileName);
    });

    // form 제출 시 삭제 이미지 / 태그 추적
    $("form").on("submit", function () {
        // 삭제 이미지 추적
        $(this).find("input[name='removedFileNames']").remove();

        const currentFileNames = new Set();
        const tempDiv = document.createElement("div");
        tempDiv.innerHTML = $(".summernote").summernote("code");

        tempDiv.querySelectorAll("img").forEach(img => {
            const fileName = img.getAttribute("src").split('/').pop();
            currentFileNames.add(fileName);
        });

        originalFileNames.forEach(fileName => {
            if (!currentFileNames.has(fileName)) {
                const input = document.createElement("input");
                input.type = "hidden";
                input.name = "removedFileNames";
                input.value = fileName;
                this.appendChild(input);
            }
        });

        // 삭제 태그 추적
        $(this).find("input[name='removedTagContents']").remove();

        const currentTagContents = new Set();
        document.querySelectorAll("input[name='tags']").forEach(input => {
            currentTagContents.add(input.value.trim());
        });

        console.log("currentTagContents →", currentTagContents);

        originalTagContents.forEach(content => {
            if (!currentTagContents.has(content)) {
                const input = document.createElement("input");
                input.type = "hidden";
                input.name = "removedTagContents";
                input.value = content;
                this.appendChild(input);
            }
        });
    });
});

// 기존 태그 추적 (동적 렌더링 대응)
const tagContainer = document.querySelector(".FlgStack-root-need");
if (tagContainer) {
    const observer = new MutationObserver(() => {
        const chips = tagContainer.querySelectorAll(".FlgChip-label-need");
        if (chips.length > 0) {
            originalTagContents.clear();
            chips.forEach(chip => {
                originalTagContents.add(chip.textContent.trim());
            });
            console.log("태그 수집 완료", originalTagContents);
            observer.disconnect();
        }
    });

    observer.observe(tagContainer, { childList: true, subtree: true });
}
