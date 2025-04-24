document.querySelectorAll(".subscribe-btn").forEach(btn => {
    btn.addEventListener("click", function () {
        const channelId = this.dataset.channelId;

        fetch(`/subscribe/${channelId}`, {
            method: "POST"
        })
            .then(res => res.text())
            .then(result => {
                if (result === "subscribed") {
                    this.textContent = "구독 취소";
                } else if (result === "unsubscribed") {
                    this.textContent = "구독";
                }
            });
    });
});