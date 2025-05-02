const replyService = (() => {
    const insertDiaryReply = async (reply) => {
        console.log(reply)
        await fetch("/diary/insertDiaryReply", {
            method: "post",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getReplyList = async (callback, diaryId = diaryInfo.id) => {
        const response = await fetch(`/diary/replyLists?diaryId=${diaryId}`)
        const replyListData = await response.json()
        if(callback){
            callback(replyListData)
        }
    }

    const insertDiaryReplyLike = async (reply) => {
        console.log(reply)
        await fetch("/diary/insertDiaryReplyLike", {
            method: "post",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        insertDiaryReply:insertDiaryReply,
        getReplyList:getReplyList,
        insertDiaryReplyLike:insertDiaryReplyLike
    }
})()