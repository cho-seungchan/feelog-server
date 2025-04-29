// 25.04.28 조승찬

// 인풋창에 입력된 파일들을 서버로 보내서 경로와 썸네일 만들기
function inputFileUpload(formData){
    return fetch("/files/upload/multi", {
        method: "post",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            createThumbnail(data.thumbnails);  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식
        })
        .catch(error => {
            console.error("reply 데이타 등록을 요청하는 중 오류", error);
            throw error;
        });
}

// 인풋창에 입력된 파일들을 서버로 보내서 경로와 썸네일 만들기
function inputFileUploadOne(formData){
    return fetch("/files/upload", {
        method: "post",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.thumbnail)
            createThumbnailOne(data.thumbnail);  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식
        })
        .catch(error => {
            console.error("reply 데이타 등록을 요청하는 중 오류", error);
            throw error;
        });
}
// 데이타 상세 조회
function communityPostRead(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community/${postId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    }).then(response => response.json())
        .then(data => {
            console.log(data.postDTO);
            createUpdateModal(data.postDTO);
    }).catch(error => {
        console.error("Error fetching community post:", error);
        throw error;
    });
}

// 데이타 삭제
function communityPostDelete(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-delete/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
        })
        .catch(error => {
            console.error("삭제 요청 중 오류 발생:", error);
        });
}

// 좋아요
function postCommunityPostLike(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-like/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setLikeCount(postId, data.likeCount);
        })
        .catch(error => {
            console.error("좋아요 요청 중 오류 발생:", error);
        });
}


// 좋아요 취소
function cancelCommunityPostLike(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-like-cancel/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setLikeCount(postId, data.likeCount);
        })
        .catch(error => {
            console.error("좋아요 취소 요청 중 오류 발생:", error);
        });
}


// 신고
function postCommunityPostReport(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-report/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReportCount(postId, data.reportCount);
        })
        .catch(error => {
            console.error("신고 요청 중 오류 발생:", error);
        });
}


// 신고 취소
function cancelCommunityPostReport(postId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-report-cancel/${postId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReportCount(postId, data.reportCount);
        })
        .catch(error => {
            console.error("신고 취소 요청 중 오류 발생:", error);
        });
}


// 2025.04.29 조승찬 :: 댓글  좋아요
function postCommunityPostReplyLike(replyId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-reply-like/${replyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReplyLikeCount(replyId, data.likeCount);
        })
        .catch(error => {
            console.error("댓글 좋아요 요청 중 오류 발생:", error);
        });
}


// 2025.04.29 조승찬 :: 댓글   좋아요 취소
function cancelCommunityPostReplyLike(replyId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-reply-like-cancel/${replyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReplyLikeCount(replyId, data.likeCount);
        })
        .catch(error => {
            console.error("댓글 좋아요 취소 요청 중 오류 발생:", error);
        });
}


// 2025.04.29 조승찬 :: 댓글   신고
function postCommunityPostReplyReport(replyId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-reply-report/${replyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReplyReportCount(replyId, data.reportCount);1
        })
        .catch(error => {
            console.error("댓글 신고 요청 중 오류 발생:", error);
        });
}


// 2025.04.29 조승찬 :: 댓글   신고 취소
function cancelCommunityPostReplyReport(replyId, currentChannelUrl) {
    return fetch(`/feelog.com/@${currentChannelUrl}/community-reply-report-cancel/${replyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`서버 요청 실패: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            setReplyReportCount(replyId, data.reportCount);
        })
        .catch(error => {
            console.error("댓글 신고 취소 요청 중 오류 발생:", error);
        });
}

