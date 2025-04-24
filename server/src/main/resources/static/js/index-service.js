const indexService = (() => {
    const getList = async (callback, page=1) => {
        const response = await fetch(`/postList?page${page}`)
        const postListData = await response.json()
        if(callback){
            callback(postListData);
        }
    }

    return{
        getList:getList
    }
})()