const diaryService = (() => {
    const getDiaryList = async (callback, page = 1, memberId = Number(loginMember.id)) => {
        const response = await fetch(`/diary/diaryListClose?page=${page}&memberId=${memberId}`)
        const diaryListData = await response.json()
        if (callback) {
            callback(diaryListData)
        }
    }

    const getDiaryReportIds = async (memberId = Number(loginMember.id)) => {
        const response = await fetch(`/diary/diaryReportIds?memberId=${memberId}`)
        return await response.json()
    }

    const getDiaryListALlAndSubscribe = async (callback, page = 1) => {
        const response = await fetch(`/diary/diaryListAllAndSubscribe?page=${page}`)
        const diaryListData = await response.json()
        if (callback) {
            callback(diaryListData)
        }
    }

    const getRandomDiaryList = async (callback) => {
        const response = await fetch("/diary/randomDiaryList")
        const randomDiaryListData = await response.json()
        if (callback) {
            callback(randomDiaryListData)
        }
    }

    const addOrDeleteDiaryLike = async (diary) => {
        console.log(diary)
        await fetch("/diary/addOrDeleteDiaryLike", {
            method: "post",
            body: JSON.stringify(diary),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return {
        getDiaryList: getDiaryList,
        getDiaryReportIds: getDiaryReportIds,
        getDiaryListALlAndSubscribe: getDiaryListALlAndSubscribe,
        getRandomDiaryList: getRandomDiaryList,
        addOrDeleteDiaryLike: addOrDeleteDiaryLike
    }
})()