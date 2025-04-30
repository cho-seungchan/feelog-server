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

    return{
        addDiaryReport:addDiaryReport
    }
})()