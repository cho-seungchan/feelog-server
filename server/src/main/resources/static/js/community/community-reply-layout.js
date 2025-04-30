// 25.04.28 조승찬

function createThumbnail(thumbnails) {

    let text = ``;
    thumbnails.forEach((file, index) => {
        // 파일 삭제를 위한 index, 서버로 전달할 정보 세팅
        // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
        text += `<li data-index="${index}" class="uploadFile"
					data-file-name="${file.fileName}" data-file-path="${file.filePath}" data-file-size="${file.fileSize}" >`;
        text += `<img src="/images/cancel.jpg" class="file-cancel" alt="calcel" >`;
        const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
        text += `<img src="/files/display?path=${encodedFilePath}" class="image-files" alt="thumbnail" >&nbsp;&nbsp;`;                             // 이미지 파일 여부 확인 할 별도 필드도 마련되어야 함
        text += `</li>`;
    })
    document.querySelector(".uploadFile-container").innerHTML = text;
}

function createThumbnailOne(file) {

    let text = ``;
    // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
    text += `<li  class="uploadFile"     
					data-file-name="${file.fileName}" data-file-path="${file.filePath}" data-file-size="${file.fileSize}" >`;
    text += `<img src="/images/cancel.jpg" class="file-cancel" alt="calcel"">`;
    const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
    text += `<img src="/files/display?path=${encodedFilePath}" class="image-files" alt="thumbnail">&nbsp;&nbsp;`;                             // 이미지 파일 여부 확인 할 별도 필드도 마련되어야 함
    text += `</li>`;
    document.querySelector(".uploadFile-container2").innerHTML = text;
}

// 조회한 데이타를 모달창으로 보여주기
function createUpdateModal(post) {

    console.log(post)

    // 포스트 내용 넣어주기
    document.querySelector('.flog-textarea-5').value = post.postContent;

    // 파일 보여주기
    let text=``;
    post.files.forEach((file, index) => {
        // 파일 삭제를 위한 index, 서버로 전달할 정보 세팅
        // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
        text += `<li data-index="${index}" class="uploadFile"     
					data-file-name="${file.fileName}" data-file-path="${file.filePath}" data-file-size="${file.fileSize}" >`;
        text += `<img src="/images/cancel.jpg" class="file-cancel" alt="calcel" >`;
        const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
        text += `<img src="/files/display?path=${encodedFilePath}" class="image-files" alt="thumbnail" >&nbsp;&nbsp;`;                             // 이미지 파일 여부 확인 할 별도 필드도 마련되어야 함
        text += `</li>`;
    });
    document.querySelector(".uploadFile-container").innerHTML = text;

    // 모달창 보여주기
    document.querySelector(".flog-div-176").style.display = "block";
}

// 좋아요 건수 보여주기
function setLikeCount(postId, likeCount) {

    // 모든 like-count 요소를 가져옵니다
    const likeCountElements = document.querySelectorAll('.likeCount');

    // 각 요소를 순회하며 data-id 값이 입력받은 id와 같은지 확인합니다
    likeCountElements.forEach(element => {
        if (element.getAttribute('data-postId') === String(postId)) {
            // 해당 요소의 텍스트 값을 새로운 LikeCount로 변경
            element.textContent = likeCount;
        }
    });
};


// 댓글 좋아요 건수 보여주기
function setReplyLikeCount(replyId, likeCount) {

    // 모든 like-count 요소를 가져옵니다
    const likeCountElements = document.querySelectorAll('.likeCount');

    // 각 요소를 순회하며 data-id 값이 입력받은 id와 같은지 확인합니다
    likeCountElements.forEach(element => {
        if (element.getAttribute('data-replyId') === String(replyId)) {
            // 해당 요소의 텍스트 값을 새로운 LikeCount로 변경
            element.textContent = likeCount;
        }
    });
};


// 2025.04.29  조승찬 :: 신고 건수 보여주기
function setReportCount(postId, reportCount) {

    // 모든 reportCount 요소를 가져옵니다
    const reportCountElements = document.querySelectorAll('.reportCount');

    // 각 요소를 순회하며 data-id 값이 입력받은 id와 같은지 확인합니다
    reportCountElements.forEach(element => {
        if (element.getAttribute('data-postId') === String(postId)) {
            // 해당 요소의 텍스트 값을 새로운 reportCount로 변경
            element.textContent = reportCount;
        }
    });
};


// 2025.04.29 조승찬 :: 댓글 신고 건수 보여주기
function setReplyReportCount(replyId, reportCount) {

    // 모든 reportCount 요소를 가져옵니다
    const reportCountElements = document.querySelectorAll('.reportCount');

    // 각 요소를 순회하며 data-id 값이 입력받은 id와 같은지 확인합니다
    reportCountElements.forEach(element => {
        if (element.getAttribute('data-replyId') === String(replyId)) {
            // 해당 요소의 텍스트 값을 새로운 reportCount로 변경
            element.textContent = reportCount;
        }
    });
};