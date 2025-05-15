document.querySelectorAll(".subscribe-btn").forEach(button => {
    console.log("[구독버튼 등록됨] 채널 ID:", button.dataset.channelId);

    button.addEventListener("click", () => {
        const channelId = button.dataset.channelId;
        console.log("[클릭됨] 채널 ID:", channelId);

        fetch(`/subscribe/${channelId}`, {
            method: "POST",
        })
            .then(response => {
                console.log("[응답 수신] status:", response.status);

                if (response.status === 401) {
                    console.warn("[경고] 로그인 안됨, 로그인 페이지로 이동");
                    window.location.href = "/login/login";
                    return;
                }
                if (response.status === 403) {
                    console.warn("[경고] 자기 채널은 구독 불가");
                    alert("자기 채널은 구독할 수 없습니다.");
                    return;
                }

                return response.text().then(text => {
                    console.log("[응답 본문]:", text);
                    return text;
                });
            })
            .then(result => {
                if (!result) {
                    console.warn("[경고] 응답 본문 없음");
                    return;
                }

                if (result === "구독 완료") {
                    console.log("[서버 응답] 구독 완료");

                    const confirmSub = confirm("이 채널을 구독하시겠습니까?");
                    console.log("[사용자 응답] 구독 확인 여부:", confirmSub);

                    if (confirmSub) {
                        button.textContent = "구독 취소";
                        alert("구독이 완료되었습니다.");
                    } else {
                        console.log("[사용자 응답] 구독 취소 요청됨 → 다시 POST");

                        fetch(`/subscribe/${channelId}`, { method: "POST" })
                            .then(() => {
                                button.textContent = "구독";
                                console.log("[처리 완료] 구독 취소됨 (되돌림)");
                            });
                    }

                } else if (result === "구독 취소") {
                    console.log("[서버 응답] 구독 취소");

                    const confirmCancel = confirm("정말 구독을 취소하시겠습니까?");
                    console.log("[사용자 응답] 구독 취소 확인 여부:", confirmCancel);

                    if (confirmCancel) {
                        button.textContent = "구독";
                        alert("구독이 취소되었습니다.");
                    } else {
                        console.log("[사용자 응답] 구독 유지 요청됨 → 다시 POST");

                        fetch(`/subscribe/${channelId}`, { method: "POST" })
                            .then(() => {
                                button.textContent = "구독 취소";
                                console.log("[처리 완료] 다시 구독됨 (되돌림)");
                            });
                    }
                } else {
                    console.warn("[경고] 알 수 없는 응답:", result);
                }
            })
            .catch(error => {
                console.error("[에러 발생] 구독 요청 중 오류:", error);
            });
    });
});
