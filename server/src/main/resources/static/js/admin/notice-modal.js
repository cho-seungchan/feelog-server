// 모달창 예시
const noticeWrite = document.querySelector(".adminNoticeInput");
const modal = document.querySelector(".admin-modal-body");

let write = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 상세 </span>
                <span class="closeNoticeModal">×</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">Title</div>
                    <textarea class="noticeModal-TitleInput"></textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">Contents</div>
                    <textarea class="noticeModal-ContentInput"></textarea>
                </div>
                <div class="notice-button-container">
                    <button class="noticeConfirmBtn">공지등록</button>
                </div>             
            </div>
        </div>
`;

noticeWrite.addEventListener("click", (e) => {
    modal.style.display = "flex";
    modal.innerHTML = write;
});

let detail = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 상세 </span>
                <span class="closeNoticeModal">&times;</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">Title</div>
                    <textarea class="noticeModal-TitleInput">예시제목목</textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">Contents</div>
                    <textarea class="noticeModal-ContentInput">예시내용</textarea>
                </div>
                <div class="notice-button-container">
                    <button class="noticeUpdateBtn">공지 수정</button>
                    <button class="noticeDeleteBtn">공지 삭제</button>
                </div>
            </div>
        </div>
`;


