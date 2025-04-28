let lastScrapId =null

const indexService = (() => {
    const getList = async (callback, page=1) => {
        const response = await fetch(`/postList?page=${page}`)
        const postListData = await response.json()
        if(callback){
            callback(postListData);
        }
    }

    const getCheerPost = async (callback) => {
        const response = await fetch("/cheerPost")
        const cheerPostData = await response.json()
        if(callback){
            callback(cheerPostData)
        }
    }

    const addScrap = async (scrap) => {
        console.log(scrap)
        await fetch("/scrapPost", {
            method: "post",
            body: JSON.stringify(scrap),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const addReport = async (report) => {
        console.log(report)
        await fetch("/insertChannelPostReport", {
            method: "post",
            body: JSON.stringify(report),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getReportList = async (id = loginMemberId) => {
        const response = await fetch(`/reportList?id=${id}`)
        return await response.json()
    }

    return{
        getList:getList,
        getCheerPost:getCheerPost,
        addScrap:addScrap,
        addReport:addReport,
        getReportList:getReportList
    }
})()