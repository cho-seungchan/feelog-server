noticeService.getList(noticeLayout.showNotice);

const search = document.querySelector(".search-input");
const button = document.querySelector(".search-button");
const noticeButtons = document.querySelectorAll(".noticeButtons")
let changeTitle = null
let changeContent = null
let defaultTitle = null
let defaultContent = null

htmlWrap.addEventListener("click", async (e) => {
    if (e.target.classList.contains("noticeButtons")) {
        await noticeService.getList(noticeLayout.noticeButtonEvent, e.target.getAttribute("data-index"));
    }

    if (e.target.classList.contains("noticeRegistBtn")) {
        modal.style.display = "flex";
        modal.innerHTML = write;
    }

    if (e.target.closest(".userListDiv")) {

        const parent = e.target.closest(".userListDiv")
        const id = parent.querySelector(".idDiv")
        const title = parent.querySelector(".titleDiv")
        const content = parent.querySelector(".contentDiv")

        modal.style.display = "flex";
        modal.innerHTML = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 상세 </span>
                <span class="closeNoticeModal">&times;</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv" data-index=${id.innerText}>Title</div>
                    <textarea class="noticeModal-TitleInput">${title.innerText}</textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">Contents</div>
                    <textarea class="noticeModal-ContentInput">${content.innerText.trim()}</textarea>
                </div>
                <div class="notice-button-container">
                    <button class="noticeUpdateBtn">공지 수정</button>
                    <button class="noticeDeleteBtn" data-index=${id.innerText}>공지 삭제</button>
                </div>
            </div>
        </div>
        `;
        const updateTitle = document.querySelector(".noticeModal-TitleInput");
        const updateContent = document.querySelector(".noticeModal-ContentInput");
        defaultTitle = updateTitle.value
        defaultContent = updateContent.value
    }
});



modal.addEventListener("input", (e) => {
    if(e.target.classList.contains("noticeModal-TitleInput")){
        changeTitle = document.querySelector(".noticeModal-TitleInput").value;
    }
    if(e.target.classList.contains("noticeModal-ContentInput")){
        changeContent = document.querySelector(".noticeModal-ContentInput").value;
    }
})



modal.addEventListener("click", async (e) => {
    if (e.target.classList.contains("closeNoticeModal")) {
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
    }

    if(e.target.classList.contains("noticeConfirmBtn")){
        const titleText = document.querySelector(".noticeModal-TitleInput");
        const content = document.querySelector(".noticeModal-ContentInput");

        if(titleText.value === ""){
            alert("제목을 입력해주세요")
            return;
        }
        if(content.value === ""){
            alert("내용을 입력해주세요")
            return;
        }

        noticeService.addNotice({
            noticeTitle: titleText.value,
            noticeContent: content.value,
            memberId: 2 //로그인 적용 후 로그인한 관리자 id로 변환
        })

        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
    }

    if (e.target.classList.contains("noticeUpdateBtn")) {
        const id = document.querySelector(".noticeModal-TitleDiv").getAttribute("data-index");
        const updateTitle = document.querySelector(".noticeModal-TitleInput");
        const updateContent = document.querySelector(".noticeModal-ContentInput");
        console.log(defaultTitle)
        console.log(defaultContent)
        console.log(changeTitle)
        console.log(changeContent)

        if(updateTitle.value === defaultTitle){
            alert("제목을 변경해주세요")
            return;
        }

        if(updateContent.value === defaultContent){
            alert("내용을 변경해주세요")
            return;
        }

        await noticeService.update({
            id:id,
            noticeTitle:changeTitle,
            noticeContent:changeContent
        })

        alert("수정완료")
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
    }
    
    if(e.target.classList.contains("noticeDeleteBtn")){
        const id = document.querySelector(".noticeModal-TitleDiv").getAttribute("data-index");

        await noticeService.deleteNotice(id);

        alert("삭제완료")
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
        await noticeService.getList(noticeLayout.noticeButtonEvent, 1);
    }
});


