const challengeService = (() => {
    const insertMemberTaskPool = async (insertMemberTaskPool) => {
        console.log(insertMemberTaskPool)
        await fetch("/admin/insertMemberTaskPool", {
            method: "post",
            body: JSON.stringify(insertMemberTaskPool),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getList = async (callback, page=1) => {
        const response = await fetch(`/admin/memberTaskPoolList?page=${page}`);
        const listData = await response.json()
        if(callback){
            callback(listData)
        }
    }

    const deleteMemberTaskPool = async (deleteMemberTaskPool) => {
        await fetch("/admin/deleteMemberTaskPool", {
            method: "put",
            body: JSON.stringify(deleteMemberTaskPool),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }
    const updateMemberTaskPool = async (updateMemberTaskPool) => {
        console.log(updateMemberTaskPool)
        await fetch("/admin/updateMemberTaskPool", {
            method: "put",
            body: JSON.stringify(updateMemberTaskPool),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        insertMemberTaskPool:insertMemberTaskPool,
        getList:getList,
        deleteMemberTaskPool:deleteMemberTaskPool,
        updateMemberTaskPool:updateMemberTaskPool
    }
})()