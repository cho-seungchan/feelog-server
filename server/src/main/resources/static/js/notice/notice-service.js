const noticeService = (() => {
    const getNextNotice = async (callback, id = noticeInfo.id) => {
        try {
            const response = await fetch(`/notice/nextNotice?id=${id}`);

            // 서버 응답이 정상인지 확인
            if (!response.ok) {
                throw new Error(`HTTP 오류 발생: ${response.status}`);
            }

            // 응답이 비어 있거나 JSON 파싱이 불가능한 경우 방지
            const textData = await response.text();
            const nextNoticeData = textData && textData.trim() ? JSON.parse(textData) : null;

            // 콜백 실행 (데이터가 있을 때만)
            if (callback) {
                callback(nextNoticeData);
            }

            return nextNoticeData;
        } catch (error) {
            console.error("게시글 불러오기 실패:", error);
            return null; // 오류 발생 시 null 반환
        }
    };

    const getPreviousNotice = async (callback, id = noticeInfo.id) => {
        try {
            const response = await fetch(`/notice/previousNotice?id=${id}`);

            // 서버 응답이 정상인지 확인
            if (!response.ok) {
                throw new Error(`HTTP 오류 발생: ${response.status}`);
            }

            // 응답이 비어 있거나 JSON 파싱이 불가능한 경우 방지
            const textData = await response.text();
            const previousNoticeData = textData && textData.trim() ? JSON.parse(textData) : null;

            // 콜백 실행 (데이터가 있을 때만)
            if (callback) {
                callback(previousNoticeData);
            }

            return previousNoticeData;
        } catch (error) {
            console.error("게시글 불러오기 실패:", error);
            return null; // 오류 발생 시 null 반환
        }
    };

    const addSubscribe = async (info) => {
        console.log(info)
        await fetch("/notice/notice", {
            method: "POST",
            body: JSON.stringify(info), // JSON 객체 형식으로 수정
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    const deleteSubscribe = async (info) => {
        console.log(info)
        await fetch("/notice/deleteSubscribe", {
            method: "POST",
            body: JSON.stringify(info), // JSON 객체 형식으로 수정
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }

    return {
        getNextNotice: getNextNotice,
        getPreviousNotice: getPreviousNotice,
        addSubscribe: addSubscribe,
        deleteSubscribe: deleteSubscribe
    }
})()