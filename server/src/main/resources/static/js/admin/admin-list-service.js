const adminListService = (() => {
    const getList = async (callback, page=1)=> {
        console.log(`pageCount = ${page}`)
        const response = await fetch(`/admin/adminlists?page=${page}`)
        const adminListsData = await response.json();
        if(callback){
            callback(adminListsData);
        }
    }
    const addAdmin = async (admin) => {
        await fetch("/admin/addAdmin", {
            method: "post",
            body: JSON.stringify(admin),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }
    return {
        getList:getList,
        addAdmin:addAdmin
    }
})();