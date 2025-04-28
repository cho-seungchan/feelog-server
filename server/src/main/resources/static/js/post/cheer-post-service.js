const cheerPostService = (() => {
    const getCheerPostList = async (callback, page = 1) =>{
        const response = await fetch(`/cheerPostList?page=${page}`)
        const cheerPostListData = await response.json()
        if(callback){
            callback(cheerPostListData)
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
        getCheerPostList:getCheerPostList,
        addScrap:addScrap,
        addReport:addReport,
        getReportList:getReportList
    }
})()