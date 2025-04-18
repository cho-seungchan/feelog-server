// 2025.04.18 조승찬
// 확인 이메일 보내기
function sendConfirmEmail() {
    return fetch(`/join/certifying`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
    })
        .then(response => {
            if (!response.ok) {
                console.error("확인 메일을 보내는 중 오류");
                throw new Error("메일 요청 실패");
            }
            // 메일 정상 전송시 화면에 메세지 보여주기
            provideInform();
        })
        .catch(error => {
            console.error("확인 메일을 요청하는 중 오류", error);
        });
}