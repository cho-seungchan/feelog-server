function createThumbnail(file) {
    let text = ``;
    const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공

    // 파일 삭제를 위한 index, 서버로 전달할 정보 세팅
    // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
    text = `
        <div class="ImageList__ImageWrapper-sc-9v1mt2-1 kZTsQf">
            <div class="Image__Wrapper-v97gyx-0 gDuKGF uploadFile"
            data-file-name="${file.fileName}" data-file-path="${file.filePath}" data-file-size="${file.fileSize}">
                <div class="Ratio " style="display: block;">
                    <div class="Ratio-ratio " style="height: 0px; position: relative; width: 100%; padding-top: 50%;">
                        <div class="Ratio-content " style="height: 100%; left: 0px; position: absolute; top: 0px; width: 100%;">
                        <img src="/files/display?path=${encodedFilePath}" class="hVNKgp">
                        </div>
                    </div>
                </div>
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero'%3E %3Cpath fill='%23FFF' fill-opacity='0' d='M0 0h18v18H0z'/%3E %3Cg stroke='%23FFF' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E" class="ImageList__IconDelete-sc-9v1mt2-2 benIbu">
            </div>
        </div>
        `;
    document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").innerHTML = text;
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
