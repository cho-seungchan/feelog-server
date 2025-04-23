const memberService = (() => {

    const getList = async (callback, page=1) => {
        const response = await fetch(`/admin/memberLists?page=${page}`)
        const memberListData = await response.json()
        if(callback){
            callback(memberListData)
        }
    }

    const deleteMember = async (member) => {
        await fetch("/admin/deleteMember", {
            method: "put",
            body: JSON.stringify(member),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const bannedMember = async (member) => {
        console.log("member:" + member)
        await fetch("/admin/bannedMember", {
            method: "put",
            body: JSON.stringify(member),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const activeMember = async (member) => {
        await fetch("/admin/activeMember", {
            method: "put" ,
            body : JSON.stringify(member),
            headers: {
                "Content-Type" : "application/json;charset=utf-8"
            }
        })
    }

    return{
        getList:getList,
        deleteMember:deleteMember,
        bannedMember:bannedMember,
        activeMember:activeMember
    }
})()