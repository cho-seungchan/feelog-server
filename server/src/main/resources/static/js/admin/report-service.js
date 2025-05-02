const reportService = (() => {
    const getPostReport = async (callback, page=1) => {
        const response = await fetch(`/admin/postReportList?page=${page}`)
        const reportListData = await response.json()
        if (callback){
            callback(reportListData)
        }
    }

    const getDiaryReport = async (callback, page=1) => {
        const response = await fetch(`/admin/diaryReportList?page=${page}`)
        const reportListData = await response.json()
        if (callback){
            callback(reportListData)
        }
    }

    const getReplyReport = async (callback,page=1) => {
        const response = await fetch(`/admin/replyReportList?page=${page}`)
        const reportListData = await response.json()
        if (callback){
            callback(reportListData)
        }
    }

    const deleteReport = async (report) => {
        await fetch("/admin/deleteReport", {
            method: "put",
            body: JSON.stringify(report),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const returnReport = async (report) => {
        console.log(report)
        await fetch("/admin/returnReport", {
            method: "put",
            body: JSON.stringify(report),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        getPostReport:getPostReport,
        getDiaryReport:getDiaryReport,
        getReplyReport:getReplyReport,
        deleteReport:deleteReport,
        returnReport:returnReport
    }
})()