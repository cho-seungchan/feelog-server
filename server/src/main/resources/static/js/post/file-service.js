function createThumbnail(file) {
    console.log(file)
    const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
    const newSpan = document.createElement("span");
    newSpan.classList.add("joy-jj02o9");
    // 파일 삭제를 위한 index, 서버로 전달할 정보 세팅
    // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
    newSpan.innerHTML = `
            <div class="MuiAspectRatio-root joy-o66529 upload-file" data-file-path=${file.filePath} data-file-name=${file.fileName}>
                <div class="MuiAspectRatio-content MuiAspectRatio-variantSoft MuiAspectRatio-colorNeutral joy-1h16">
                     <img src="/files/display?path=${encodedFilePath}" alt="uploaded_image" width="100%" data-first-child="">
                </div>
            </div>
            <span class="MuiBadge-badge MuiBadge-anchorOriginTopRight MuiBadge-variantPlain MuiBadge-colorPrimary MuiBadge-sizeMd joy-1kyk4ad">
                <button class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-vbxkza">
                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-hc017">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 1 0 0 20 10 10 0 0 0 0-20ZM9.707 8.293a1 1 0 0 0-1.414 1.414L10.586 12l-2.293 2.293a1 1 0 1 0 1.414 1.414L12 13.414l2.293 2.293a1 1 0 0 0 1.414-1.414L13.414 12l2.293-2.293a1 1 0 0 0-1.414-1.414L12 10.586 9.707 8.293Z" fill="currentcolor">
                        </path>
                    </svg>
                </button>
            </span>
        `;
    document.querySelector(".upload_buttonContainer_02").prepend(newSpan);
}

function inputFileUpload(formData){
    return fetch("/files/upload", {
        method: "post",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            createThumbnail(data.thumbnail);  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식
        })
        .catch(error => {
            console.error("파일 데이타 업로드 중 오류", error);
            throw error;
        });
}
