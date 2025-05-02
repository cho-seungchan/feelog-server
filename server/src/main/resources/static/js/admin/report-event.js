const postReportList = document.querySelector(".postReportList");

postReportList.addEventListener("click" , async (e) =>{
    await reportService.getPostReport(reportLayout.showPostReportList);
})

htmlWrap.addEventListener("click", async (e) => {
    if(e.target.classList.contains("post-report-buttons")){
        await reportService.getPostReport(reportLayout.showPostReportList,e.target.getAttribute("data-index"))
    }

    if(e.target.classList.contains("delete-Report")){
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if(!userResponse){
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box)=>{
            if(box.checked){
                idList.push(box.id)
            }
        })

        await reportService.deleteReport(idList)

        await reportService.getPostReport(reportLayout.showPostReportList,1);
    }

    if(e.target.classList.contains("return-post-button")){
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if(!userResponse){
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box)=>{
            if(box.checked){
                idList.push(box.id)
            }
        })

        await reportService.returnReport(idList)

        await reportService.getPostReport(reportLayout.showPostReportList,1);
    }
})