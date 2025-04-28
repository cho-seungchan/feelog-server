// 2025.04.25 조승찬

// 스크랩 취소
function storageOffScrap(id) {
    return fetch(`/myPage/storage-off-scrap?id=${id}`, {
        method: "POST", // POST 요청 설정
    })
        .then(response => {
            if (!response.ok) {
                console.error("스크랩 취소 요청 오류");
                throw new Error("스크랩 취소 요청 실패");
            }
        })
        .catch(error => {
            console.error("스크랩 취소 요청하는 중 오류", error);
        });
}


// 스크랩 재설정
function storageOnScrap(id) {
    return fetch(`/myPage/storage-on-scrap?id=${id}`, {
        method: "POST", // POST 요청 설정
    })
        .then(response => {
            if (!response.ok) {
                console.error("스크랩 재설정 요청 오류");
                throw new Error("스크랩 재설정 요청 실패");
            }
        })
        .catch(error => {
            console.error("스크랩 재설정 요청하는 중 오류", error);
        });
}
