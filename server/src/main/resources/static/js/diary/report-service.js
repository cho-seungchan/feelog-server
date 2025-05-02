const reportService = (() => {
    const addDiaryReport = async (diaryReport) => {
        console.log(diaryReport)
        await fetch("/diary/insertDiaryReport", {
            method: "post",
            body: JSON.stringify(diaryReport),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getDiaryReplyReportList = async (memberId = loginMember.id) => {
        const response = await fetch(`/diary/diaryReplyReportList?memberId=${memberId}`)
        return await response.json();
    }

    const addDiaryReplyReport = async (Report) => {
        console.log(Report)
        await fetch("/diary/addDiaryReplyReport", {
            method: "post",
            body: JSON.stringify(Report),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        addDiaryReport:addDiaryReport,
        getDiaryReplyReportList:getDiaryReplyReportList,
        addDiaryReplyReport:addDiaryReplyReport
    }
})()