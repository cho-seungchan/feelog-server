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
