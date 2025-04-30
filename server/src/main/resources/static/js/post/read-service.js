const readService = (() => {
    const getNextPost = async (callback, channelId, id) => {
        try {
            const response = await fetch(`/post/nextPost?channelId=${channelId}&id=${id}`);

            // 서버 응답이 정상인지 확인
            if (!response.ok) {
                throw new Error(`HTTP 오류 발생: ${response.status}`);
            }

            // 응답이 비어 있거나 JSON 파싱이 불가능한 경우 방지
            const textData = await response.text();
            const nextPostData = textData && textData.trim() ? JSON.parse(textData) : null;

            // 콜백 실행 (데이터가 있을 때만)
            if (callback) {
                callback(nextPostData);
            }

            return nextPostData;
        } catch (error) {
            console.error("게시글 불러오기 실패:", error);
            return null; // 오류 발생 시 null 반환
        }
    };

    const getPreviousPost = async (callback, channelId, id) => {
        try {
            const response = await fetch(`/post/previousPost?channelId=${channelId}&id=${id}`);

            // 서버 응답이 정상인지 확인
            if (!response.ok) {
                throw new Error(`HTTP 오류 발생: ${response.status}`);
            }

            // 응답이 비어 있거나 JSON 파싱이 불가능한 경우 방지
            const textData = await response.text();
            const previousPostData = textData && textData.trim() ? JSON.parse(textData) : null;

            // 콜백 실행 (데이터가 있을 때만)
            if (callback) {
                callback(previousPostData);
            }

            return previousPostData;
        } catch (error) {
            console.error("이전 게시글 불러오기 실패:", error);
            return null; // 오류 발생 시 null 반환
        }
    };

    const addSubscribe = async (channelId) => {
        console.log(channelId)
        await fetch("/post/addSubscribe", {
            method: "POST",
            body: JSON.stringify({ channelId: channelId }), // JSON 객체 형식으로 수정
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const deleteSubscribe = async (channelId) => {
        console.log(channelId)
        await fetch("/post/deleteSubscribe", {
            method: "put",
            body: JSON.stringify({ channelId: channelId }), // JSON 객체 형식으로 수정
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getRandomPost = async (callback) => {
        const response = await fetch("/post/randomPost")
        const postData = await response.json()
        if(callback){
            callback(postData)
        }
    }

    const addScrap = async (scrap) => {
        await fetch("/scrapPost", {
            method: "post",
            body: JSON.stringify(scrap),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const addReply = async (reply) => {
        console.log(reply)
        await fetch("/post/addReply", {
            method: "post",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const getReplyList = async (callback, postId = postInfo.id) => {
        const response = await fetch(`/post/replyList?postId=${postId}`)
        const replyListData = await response.json();
        if(callback){
            callback(replyListData)
        }
    }

    const addReplyLike = async (like) => {
        console.log(like)
        await fetch("/post/addOrDeleteReplyLike", {
            method: "post",
            body: JSON.stringify(like),
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

    const getReportList = async (id = loginMember.id) => {
        const response = await fetch(`/reportList?id=${id}`)
        return await response.json()
    }

    const getReplyReportCheck = async (replyId, memberId, callback) => {
        try {
            const response = await fetch(`/post/replyPostCheck?replyId=${replyId}&memberId=${memberId}`);

            // 서버 응답이 정상인지 확인
            if (!response.ok) {
                throw new Error(`HTTP 오류 발생: ${response.status}`);
            }

            // 응답이 비어 있거나 JSON 파싱이 불가능한 경우 방지
            const textData = await response.text();
            const replyData = textData && textData.trim() ? JSON.parse(textData) : null;

            // 콜백 실행 (데이터가 있을 때만)
            if (callback) {
                callback(replyData);
            }

            return replyData;
        } catch (error) {
            console.error("이전 게시글 불러오기 실패:", error);
            return null; // 오류 발생 시 null 반환
        }
    };

    const addReplyReport = async (replyReport) => {
        await fetch("/post/addReplyReport", {
            method: "post",
            body: JSON.stringify(replyReport),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const addPostLike = async (postLike) => {
        console.log(postLike)
        await fetch("/post/addPostLike", {
            method: "post",
            body: JSON.stringify(postLike),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return{
        getNextPost:getNextPost,
        getPreviousPost:getPreviousPost,
        addSubscribe:addSubscribe,
        deleteSubscribe:deleteSubscribe,
        getRandomPost:getRandomPost,
        addScrap:addScrap,
        addReply:addReply,
        getReplyList:getReplyList,
        addReplyLike:addReplyLike,
        addReport:addReport,
        getReportList:getReportList,
        getReplyReportCheck:getReplyReportCheck,
        addReplyReport:addReplyReport,
        addPostLike:addPostLike
    }
})()