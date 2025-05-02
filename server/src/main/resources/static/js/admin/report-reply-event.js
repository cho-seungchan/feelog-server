const replyReportList = document.querySelector(".replyList");

replyReportList.addEventListener("click" , async (e) =>{
    await reportService.getReplyReport(reportLayout.showReplyReportList);
})

htmlWrap.addEventListener("click", async (e) => {
    if(e.target.classList.contains("reply-report-buttons")){
        await reportService.getReplyReport(reportLayout.showReplyReportList,e.target.getAttribute("data-index"))
    }

    if(e.target.classList.contains("delete-reply-Report")){
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

        await reportService.getReplyReport(reportLayout.showReplyReportList,1);
    }

    if(e.target.classList.contains("return-reply-button")){
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

        await reportService.getReplyReport(reportLayout.showReplyReportList,1);

    }
})