// 2025.04.23 조승찬

// 2025.04.23 조승찬  썸네일을 화면에 보여주기

function createThumbnail(file) {
    let text = ``;
    // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
    text += `<li  class="uploadFile"     
					data-file-name="${file.fileName}" data-file-path="${file.filePath}"  >`;
    text += `<img src="/images/cancel.jpg" class="file-cancel" alt="calcel"">`;
    const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
    text += `<img   loading="lazy"
                    decoding="async"
                    data-nimg="fill"
                    style="
                    position: absolute;
                    height: 100%;
                    width: 100%;
                    left: 0;
                    top: 0;
                    right: 0;
                    bottom: 0;
                    object-fit: cover;
                    color: transparent;  "
                    src="/files/display?path=${encodedFilePath}" class="image-files" alt="thumbnail">&nbsp;&nbsp;`;  // 이미지 파일 여부 확인 할 별도 필드도 마련되어야 함
    text += `</li>`;
    document.querySelector(".FeelogBox-root.flog-div-78").innerHTML = text;
    document.querySelector(".FeelogBox-root.flog-div-78").scrollIntoView({ behavior: "smooth", block: "start" });   // 추가된 행들이 처지지 않게 위치 잡아주기

}
