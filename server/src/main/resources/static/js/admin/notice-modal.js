// 모달창 예시
const noticeWrite = document.querySelector(".adminNoticeInput");
const questionWrite = document.querySelector(".questionWrite");
const modal = document.querySelector(".admin-modal-body");

// 공지 등록
noticeWrite.addEventListener("click", (e) => {
    modal.style.display = "flex";
    modal.innerHTML = `
    <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 등록 </span>
                <span class="closeNoticeModal">×</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">제목</div>
                    <textarea class="noticeModal-TitleInput noticeTitle"></textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">내용</div>
                    <textarea class="noticeModal-ContentInput noticeContent"></textarea>
                    <div class="ImageList-sc-9v1mt2-0 hGJMVS">
                    </div>
                </div>
                <div class="notice-button-container">
                    <label class="InputImageReview__Wrapper-sc-1oapt4s-0 ipbuZD">
                        <input type="file" accept=".jpg, .jpeg, .png" class="add-img">
                        <span> 사진 첨부하기</span>
                    </label>
                    <button class="confirmBtn noticeConfirmBtn noticeWrite">공지등록</button>
                </div>             
            </div>
        </div>
    `;
    // 파일첨부 후 div 구현
    modal.addEventListener("change", (e) => {
        console.log(e.target.files)
        const files = e.target.files[0]; // FileList 객체

        const formData = new FormData();
        formData.append("file", files);
        // 서버로 전송하여 path와 썸네일 생성
        inputFileUpload(formData);
    });
});

// 질문 등록
questionWrite.addEventListener("click", (e) => {
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
                    <button class="confirmBtn add-faq">질문등록</button>
                </div>             
            </div>
        </div>
    `;
})

modal.addEventListener("click", (e)=>{
    const imgWrap = document.querySelector(".kZTsQf");
    if(e.target.classList.contains("benIbu")){
        e.target.closest(".kZTsQf").remove();
    }

    if(e.target.classList.contains("add-img") && imgWrap){
        alert("이미지는 하나만 가능합니다")
        e.preventDefault()
        return;
    }

    if (e.target.classList.contains("closeNoticeModal")) {
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
    }
})