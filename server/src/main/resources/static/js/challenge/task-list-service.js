// 2025.04.19 조승찬

// 개별과제 도전
function selectMemberTask(requestBody) {
    return fetch(`/challenge/member-challenge`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (!response.ok) {
                console.error("개별과제 선택 요청 오류");
                throw new Error("개별과제 선택  요청 실패");
            }
        })
        .catch(error => {
            console.error("개별과제 선택 요청하는 중 오류", error);
        });
}

// 개별과제 도전 취소
function cancelMemberTask(requestBody) {
    return fetch(`/challenge/member-cancel`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (!response.ok) {
                console.error("개별과제 취소 요청 오류");
                throw new Error("개별과제 취소 요청 실패");
            }
        })
        .catch(error => {
            console.error("개별과제 취소 요청하는 중 오류", error);
        });
}

// 공통과제 도전
function selectCommonTask(requestBody) {
    return fetch(`/challenge/common-challenge`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (!response.ok) {
                console.error("공통과제 선택 요청 오류");
                throw new Error("공통과제 선택  요청 실패");
            }
        })
        .catch(error => {
            console.error("공통과제 선택 요청하는 중 오류", error);
        });
}

// 개별과제 도전 취소
function cancelCommonTask(requestBody) {
    return fetch(`/challenge/common-cancel`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (!response.ok) {
                console.error("공통과제 취소 요청 오류");
                throw new Error("공통과제 취소 요청 실패");
            }
        })
        .catch(error => {
            console.error("공통과제 취소 요청하는 중 오류", error);
        });
}