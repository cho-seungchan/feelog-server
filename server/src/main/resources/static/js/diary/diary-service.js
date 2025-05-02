const diaryService = (() => {
    const getDiaryList = async (callback,page=1) => {
        const response = await fetch(`/diary/diaryList?page=${page}`)
        const diaryListData = await response.json()
        if(callback){
            callback(diaryListData)
        }
    }

    const getDiaryReportIds = async (memberId=Number(loginMember.id)) => {
        const response = await fetch(`/diary/diaryReportIds?memberId=${memberId}`)
        return await response.json()
    }

    return{
        getDiaryList:getDiaryList,
        getDiaryReportIds:getDiaryReportIds
    }
})()