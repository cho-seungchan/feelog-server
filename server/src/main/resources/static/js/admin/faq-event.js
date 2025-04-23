faqService.getList(faqLayout.showFaq)


htmlWrap.addEventListener("click", async (e) => {
    if (e.target.classList.contains("faqButtons")) {
        await faqService.getList(faqLayout.faqButtonEvent, e.target.getAttribute("data-index"));
    }

    if (e.target.classList.contains("faqRegistBtn")) {
        modal.style.display = "flex";
        modal.innerHTML = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 자주 묻는 질문 등록 </span>
                <span class="closeNoticeModal">×</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">제목</div>
                    <textarea class="noticeModal-TitleInput faq-title"></textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">내용</div>
                    <textarea class="noticeModal-ContentInput faq-content"></textarea>
                </div>
                <div class="notice-button-container">
                    <button class="confirmBtn add-faq" data-index=1>질문등록</button>
                </div>             
            </div>
        </div>
        `;
    }

    if (e.target.closest(".faqListDiv")) {
        const parent = e.target.closest(".faqListDiv")
        const id = parent.querySelector(".idDiv")
        const title = parent.querySelector(".titleDiv")
        const content = parent.querySelector(".contentDiv")

        modal.style.display = "flex";
        modal.innerHTML = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 자주 묻는 질문 상세 </span>
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
                    <div class="ImageList-sc-9v1mt2-0 hGJMVS"></div>
                </div>
                <div class="notice-button-container">
                    <button class="updateBtn faqUpdateBtn" >질문 수정</button>
                    <button class="deleteBtn faqDeleteBtn" data-index=${id.innerText}>질문 삭제</button>
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
    if(e.target.classList.contains("add-faq")){
        console.log("추가")

        const titleText = document.querySelector(".faq-title");
        const content = document.querySelector(".faq-content");

        if(titleText.value === ""){
            alert("제목을 입력해주세요")
            return;
        }
        if(content.value === ""){
            alert("내용을 입력해주세요")
            return;
        }

        await faqService.addFaq({
            faqTitle: titleText.value,
            faqContent: content.value,
            memberId: loginMember.id //로그인 적용 후 로그인한 관리자 id로 변환
        })

        alert("등록완료")

        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";

        await faqService.getList(faqLayout.faqButtonEvent,1)
    }

    if (e.target.classList.contains("faqUpdateBtn")) {
        console.log("수정")
        const id = document.querySelector(".noticeModal-TitleDiv").getAttribute("data-index");

        await faqService.updateFaq({
            id:id,
            faqTitle:changeTitle,
            faqContent:changeContent
        })


        alert("수정완료")
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";

        await faqService.getList(faqLayout.faqButtonEvent,1)

    }

    if(e.target.classList.contains("faqDeleteBtn")){
        console.log("삭제")

        const id = document.querySelector(".noticeModal-TitleDiv").getAttribute("data-index");

        await faqService.deleteFaq(id);

        alert("삭제완료")
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";

        await faqService.getList(faqLayout.faqButtonEvent,1)

    }

});

