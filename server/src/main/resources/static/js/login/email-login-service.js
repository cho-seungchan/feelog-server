// 2025.04.18 조승찬

// 로그인 요청
requestLogin("/login/email-login", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
})
    .then(response => {
        if (response.ok) {
            console.log("로그인 성공");
        } else {
            console.error("로그인 실패");
            provideInform();
        }
    })
    .catch(error => console.error("네트워크 오류", error));