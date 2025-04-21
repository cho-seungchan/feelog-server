// 2025.04.19 조승찬

// 로그인 요청
function requestLogin(data) {
    return fetch ("/login/email-login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                // 서버에서 리다이렉션 URL을 Promise로 반환
                // response.text()는 비동기 함수이기 때문에, 반환값을 즉시 사용할 수 없고,
                // 이를 처리하려면 .then()을 사용해야 합니다.
                return response.text();
            } else {
                console.error("로그인 실패");
                provideInform();
                throw new Error("로그인 실패"); // 에러 처리
            }
        })
        .then(redirectUrl => {  // 리다이렉션 처리
            console.log("로그인 성공");
            window.location.href = redirectUrl; // 리다이렉션 URL로 이동
        })
        .catch(error => console.error("네트워크 오류", error));
}