// 25.04.05 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 로그인 버튼 클릭 이벤트
    document.querySelectorAll(".flog-a-13").forEach((button) => {
        button.addEventListener("click", (e) => {
            // 이메일로그인
            if (e.target.closest("button.flog-a-13")) {
                window.location.href = "/login/email-login";
            }
        });
    });

});
