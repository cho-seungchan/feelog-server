noticeService.getList(noticeLayout.showNotice);

const search = document.querySelector(".search-input");
const button = document.querySelector(".search-button");
const noticeLists = document.querySelectorAll(".noticeList-container>li");
const notices = document.querySelectorAll(".noticeListDiv");

// search.addEventListener("keydown", (e) => {
//     if (e.key === "Enter") {
//         const target = search.value;
//         noticeLists.forEach((list) => {
//             const noticeList = list.querySelector(".noticeListDiv");
//             const listValue = noticeList.querySelector(".noticeTitleDiv");
//             const content = listValue.textContent;
//             if (content.includes(target)) {
//                 console.log(noticeList);
//                 return;
//             }
//         });
//     }
// });

// notices.forEach((notice) => {
//     notice.addEventListener("click", (e) => {
//         console.log(e.target);
//         modal.style.display = "flex";
//         modal.innerHTML = detail;
//     });
// });

modal.addEventListener("click", (e) => {
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
});


