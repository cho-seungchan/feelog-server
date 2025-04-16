const reportLists = document.querySelectorAll(".reportListDiv");
const modal = document.querySelector(".admin-modal-body");

reportLists.forEach((list) => {
    list.addEventListener("click", (e) => {
        const target = e.target.closest(".reportListDiv");
        console.log(modal);
        if (target) {
            modal.style.display = "flex";
            modal.innerHTML = report;
        }
    });
});

modal.addEventListener("click", (e) => {
    if (e.target.classList.contains("closeReportModal")) {
        modal.style.display = "none";
        modal.innerHTML = "";
    }
});
