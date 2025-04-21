const noticeService = (() => {
    const addNotice = async (notice) => {
        await fetch("/admin/addNotice", {
            method: "post",
            body: JSON.stringify(notice),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getList = async (callback, page=1) => {
        const response = await fetch(`/admin/noticeLists?page=${page}`)
        const noticeListData = await response.json()
        if(callback) {
            callback(noticeListData)
        }
    }

    const update = async (notice) => {
        await fetch("/admin/updateNotice", {
            method: "put",
            body: JSON.stringify(notice),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const deleteNotice = async (notice) => {
        console.log(notice)
        await fetch("/admin/deleteNotice", {
            method: "put",
            body: JSON.stringify(notice),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        addNotice:addNotice,
        getList:getList,
        update:update,
        deleteNotice:deleteNotice
    }
})()