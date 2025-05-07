noticeService.getNextNotice(noticeLayout.showNextNotice)
noticeService.getPreviousNotice(noticeLayout.showPreviousNotice)
console.log(noticeInfo)

const subscribeButton = document.querySelector(".subscribe-button");

subscribeButton.addEventListener("click", async (e) => {
    if (loginMember == null) {
        alert("로그인 후 이용해주세요")
        window.location.href = "/login/login"
        return;
    }

    if (noticeInfo.subscribed) {
        if (confirm("구독을 해지하시겠습니까?")) {
            const channelId = subscribeButton.getAttribute("data-index")
            const buttonWrap = document.querySelector(".add_channelWrap_01")

            await noticeService.deleteSubscribe({
                memberId: loginMember.id,
                channelId: channelId
            })

            buttonWrap.innerHTML = `
                <button class="more_diaryButton_01 add_channelButton_01 subscribe-button" type="button" data-index=${channelId}>
                    구독
                </button>
            `;
        }
    } else {
        if (confirm("구독하시겠습니까?")) {
            const channelId = subscribeButton.getAttribute("data-index")
            const buttonWrap = document.querySelector(".add_channelWrap_01")
            await noticeService.addSubscribe({
                memberId: loginMember.id,
                channelId: channelId
            })

            buttonWrap.innerHTML = `
            <button class="MuiButton-root MuiButton-variantSoft MuiButton-colorNeutral MuiButton-sizeMd subscribe-button subscribing" type="button" data-index=${channelId}>
                구독 중
            </button>
        `;
        }

    }
})