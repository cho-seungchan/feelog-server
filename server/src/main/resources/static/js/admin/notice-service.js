const noticeService = (() => {
    const addNotice = async (notice) => {
        console.log(notice)
        await fetch("/admin/addNotice", {
            method: "post",
            body: JSON.stringify(notice),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getList = async (callback, page=1) => {
        const response = await fetch("/admin/noticeLists")
        const noticeListData = response.json()
        if(callback) {
            callback(noticeListData)
        }
    }

    return{
        addNotice:addNotice,
        getList:getList
    }
})()