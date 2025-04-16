document.addEventListener("DOMContentLoaded", function () {
    const lottieContainer = document.getElementById("lottie-timer");

    if (lottieContainer) {
        lottie.loadAnimation({
            container: lottieContainer,
            renderer: "svg",
            loop: true,
            autoplay: true,
            path: "/static/assets/clock.json",
        });
    }

    function updateCountdownToMidnight() {
        const now = new Date();

        // UTC 기준 시간에 9시간 더해서 한국 시간 만들기
        const utc = now.getTime() + now.getTimezoneOffset() * 60000;
        const kstNow = new Date(utc + 9 * 60 * 60 * 1000);

        // 내일 00:00:00 (한국 시간) 생성
        const tomorrow = new Date(kstNow);
        tomorrow.setHours(24, 0, 0, 0); // 오늘 자정(=내일 0시)

        // 남은 시간 계산 (ms)
        const diffMs = tomorrow - kstNow;

        // 음수 방지
        const remainingSec = Math.max(0, Math.floor(diffMs / 1000));

        const hours = String(Math.floor(remainingSec / 3600)).padStart(2, "0");
        const minutes = String(Math.floor((remainingSec % 3600) / 60)).padStart(
            2,
            "0"
        );
        const seconds = String(remainingSec % 60).padStart(2, "0");

        const hourEl = document.getElementById("hour");
        const minuteEl = document.getElementById("minute");
        const secondEl = document.getElementById("second");

        if (hourEl) hourEl.textContent = hours;
        if (minuteEl) minuteEl.textContent = minutes;
        if (secondEl) secondEl.textContent = seconds;

        setTimeout(updateCountdownToMidnight, 1000);
    }

    updateCountdownToMidnight();
});
