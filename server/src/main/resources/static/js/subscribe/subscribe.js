document.querySelectorAll(".subscribe-btn").forEach(button => {
    button.addEventListener("click", () => {
        const channelId = button.dataset.channelId;

        fetch(`/subscribe/${channelId}`, {
            method: "POST",
        })
            .then(response => {
                if (response.status === 401) {
                    window.location.href = "/login/login";
                    return;
                }
                if (response.status === 403) {
                    alert("자기 채널은 구독할 수 없습니다.");
                    return;
                }
                return response.text();
            })
            .then(result => {
                if (!result) return;

                if (result === "구독 완료") {
                    const confirmSub = confirm("이 채널을 구독하시겠습니까?");
                    if (confirmSub) {
                        button.textContent = "구독 취소";
                        alert("구독이 완료되었습니다.");
                    } else {
                        // 취소했으면 다시 요청해서 구독 취소 처리
                        fetch(`/subscribe/${channelId}`, { method: "POST" })
                            .then(() => {
                                button.textContent = "구독";
                            });
                    }
                } else if (result === "구독 취소") {
                    const confirmCancel = confirm("정말 구독을 취소하시겠습니까?");
                    if (confirmCancel) {
                        button.textContent = "구독";
                        alert("구독이 취소되었습니다.");
                    } else {
                        // 취소한 경우 → 다시 구독 처리
                        fetch(`/subscribe/${channelId}`, { method: "POST" })
                            .then(() => {
                                button.textContent = "구독 취소";
                            });
                    }
                }
            })
            .catch(error => {
                console.error("구독 요청 중 오류 발생:", error);
            });
    });
});
