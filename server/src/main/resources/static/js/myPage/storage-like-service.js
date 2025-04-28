// 2025.04.25 조승찬

//  취소
function storageOffLike(id, postId) {
    return fetch(`/myPage/storage-off-like?id=${id}&postId=${postId}`, {
        method: "POST", // POST 요청 설정
    })
        .then(response => {
            if (!response.ok) {
                console.error("좋아요 취소 요청 오류");
                throw new Error("좋아요 취소 요청 실패");
            }
            return response.json();
        })
        .then(data => {
            setLikeCount(id, data.likeCount);
        })
        .catch(error => {
            console.error("좋아요 취소 요청하는 중 오류", error);
        });
}

// 좋아요 재설정
function storageOnLike(id, postId) {
    return fetch(`/myPage/storage-on-like?id=${id}&postId=${postId}`, {
        method: "POST", // POST 요청 설정
    })
        .then(response => {
            if (!response.ok) {
                console.error("좋아요 재설정 요청 오류");
                throw new Error("좋아요 재설정 요청 실패");
            }
            return response.json();
        })
        .then(data => {
            setLikeCount(id, data.likeCount);
        })
        .catch(error => {
            console.error("좋아요 재설정 요청하는 중 오류", error);
        });
}

function setLikeCount(id, likeCount) {
    // 모든 like-count 요소를 가져옵니다
    const likeCountElements = document.querySelectorAll('.like-count');

    // 각 요소를 순회하며 data-id 값이 입력받은 id와 같은지 확인합니다
    likeCountElements.forEach(element => {
        if (element.getAttribute('data-id') === String(id)) {
            // 해당 요소의 텍스트 값을 새로운 LikeCount로 변경
            element.textContent = likeCount;
        }
    });
}
