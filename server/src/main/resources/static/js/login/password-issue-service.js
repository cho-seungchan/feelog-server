// 2025.04.19 조승찬

// 비밀번호 재설정 요청
function requestPasswordIssue(data) {
    return fetch (`/login/password-issue`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log("비밀번호 재발급 성공");
                provideOkInform();
            } else {
                console.error("비밀번호 재발급 실패");
                provideErrorInform();
                throw new Error("비밀번호 재발급 실패"); // 에러 처리
            }
        })
        .catch(error => console.error("네트워크 오류", error));
}