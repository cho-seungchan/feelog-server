const search = document.querySelector(".search-input");
const button = document.querySelector(".search-button");
const noticeLists = document.querySelectorAll(".noticeList-container>li");
const notices = document.querySelectorAll(".noticeListDiv");
const modal = document.querySelector(".admin-modal-body");
const writeNotice = document.querySelector(".faqRegistBtn");

search.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        const target = search.value;
        noticeLists.forEach((list) => {
            const noticeList = list.querySelector(".noticeListDiv");
            const listValue = noticeList.querySelector(".noticeTitleDiv");
            const content = listValue.textContent;
            if (content.includes(target)) {
                console.log(noticeList);
                return;
            }
        });
    }
});

notices.forEach((notice) => {
    notice.addEventListener("click", (e) => {
        console.log(e.target);
        modal.style.display = "flex";
        modal.innerHTML = detail;
    });
});

modal.addEventListener("click", (e) => {
    if (e.target.className == "closeNoticeModal") {
        document.querySelector(".admin-modal-body").innerHTML = ``;
        document.querySelector(".admin-modal-body").style.display = "none";
    }
});

writeNotice.addEventListener("click", (e) => {
    modal.style.display = "flex";
    modal.innerHTML = write;
});
