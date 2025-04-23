const faqService = (() => {
    const addFaq = async (faq) => {
        await fetch("/admin/addFaq", {
            method: "post",
            body: JSON.stringify(faq),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getList = async (callback, page=1) => {
        const response = await fetch(`/admin/faqLists?page=${page}`)
        const faqListData = await response.json()
        if(callback) {
            callback(faqListData)
        }
    }

    const updateFaq = async (faq) => {
        await fetch("/admin/updateFaq", {
            method: "put",
            body: JSON.stringify(faq),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const deleteFaq = async (faq) => {
        await fetch("/admin/deleteFaq", {
            method: "put",
            body: JSON.stringify(faq),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        addFaq:addFaq,
        getList:getList,
        updateFaq:updateFaq,
        deleteFaq:deleteFaq
    }
})()
