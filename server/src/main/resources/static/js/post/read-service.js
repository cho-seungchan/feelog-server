console.log(postInfo)
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
        console.log(scrap)
        await fetch("/scrapPost", {
            method: "post",
            body: JSON.stringify(scrap),
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
        addScrap:addScrap
    }
})()