const diaryReportList = document.querySelector(".diaryReportList");

diaryReportList.addEventListener("click", async (e) => {
    await reportService.getDiaryReport(reportLayout.showDiaryReportList);
})

htmlWrap.addEventListener("click", async (e) => {
    if (e.target.classList.contains("diary-report-buttons")) {
        await reportService.getDiaryReport(reportLayout.showDiaryReportList, e.target.getAttribute("data-index"))
    }

    if (e.target.classList.contains("delete-diary-Report")) {
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if (!userResponse) {
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box) => {
            if (box.checked) {
                idList.push(box.id)
            }
        })

        await reportService.deleteReport(idList)

        await reportService.getDiaryReport(reportLayout.showDiaryReportList, 1);
    }

    if (e.target.classList.contains("return-diary-button")) {
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if (!userResponse) {
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box) => {
            if (box.checked) {
                idList.push(box.id)
            }
        })

        await reportService.returnReport(idList)

        await reportService.getDiaryReport(reportLayout.showDiaryReportList, 1);
    }
})