const noticeLayout = (() => {
    const showNextNotice = ((nextNoticeData) => {
        const noticeContainer = document.querySelector(".post-navigationBar");

        if (nextNoticeData != null) {
            const newA = document.createElement("a")
            newA.classList.add("post_buttonContainer_01")
            newA.setAttribute("href", `/notice/notice?id=${nextNoticeData.id}`)

            newA.innerHTML = `
            <div class="navigationBar-leftbutton" role="button" tabindex="0">
                <div class="previousPost-ContentWrap">
                    <div class="previousPost-titleWrap">
                        <h5 class="previousPost-title">
                        ${nextNoticeData.noticeTitle}
                        </h5>
                    </div>
                </div>
                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="leftButton-Img">
                    <path d="M15.291 11.332a.934.934 0 0 1 0 1.325l-5.25 5.254a.937.937 0 0 1-1.324-1.324L13.303 12l-4.59-4.59a.937.937 0 0 1 1.324-1.324l5.254 5.245Z" fill="currentcolor"></path>
                </svg>
            </div>
            `;
            noticeContainer.appendChild(newA)
        } else {
            const newDiv = document.createElement("div")
            newDiv.classList.add("nextPost-ContentWrap")

            newDiv.innerHTML = `
            <p class="empty-content">
                다음 포스트가 없어요.
            </p>
            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="nonePost_svg_01">
                <path d="M15.291 11.332a.934.934 0 0 1 0 1.325l-5.25 5.254a.937.937 0 0 1-1.324-1.324L13.303 12l-4.59-4.59a.937.937 0 0 1 1.324-1.324l5.254 5.245Z" fill="currentcolor"></path>
            </svg>
            `;
            noticeContainer.appendChild(newDiv);
        }

    })

    const showPreviousNotice = ((previousNoticeData) => {
        const noticeContainer = document.querySelector(".post-navigationBar");

        if (previousNoticeData != null) {
            const newA = document.createElement("a")
            newA.classList.add("post_buttonContainer_01")
            newA.setAttribute("href", `/notice/notice?id=${previousNoticeData.id}`)
            console.log(newA)

            newA.innerHTML = `
            <div class="navigationBar-leftbutton" role="button" tabindex="0">
                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="leftButton-Img">
                    <path d="M8.713 11.332a.934.934 0 0 0 0 1.325l5.25 5.254a.937.937 0 0 0 1.324-1.324l-4.586-4.59 4.59-4.586a.937.937 0 0 0-1.324-1.324l-5.254 5.245Z" fill="currentcolor"></path>
                </svg>
                <div class="previousPost-ContentWrap">
                    <div class="previousPost-titleWrap">
                        <h5 class="previousPost-title">
                            ${previousNoticeData.noticeTitle}
                        </h5>
                    </div>
                </div>
            </div>
            `;

            console.log(newA)

            noticeContainer.prepend(newA)
        } else {
            console.log("true")
            const newDiv = document.createElement("div")
            newDiv.classList.add("nextPost-ContentWrap")

            newDiv.innerHTML = `
            <p class="empty-content">
                이전 포스트가 없어요.
            </p>
            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="nonePost_svg_01">
                <path d="M8.713 11.332a.934.934 0 0 0 0 1.325l5.25 5.254a.937.937 0 0 0 1.324-1.324l-4.586-4.59 4.59-4.586a.937.937 0 0 0-1.324-1.324l-5.254 5.245Z" fill="currentcolor"></path>
            </svg>
            `;
            noticeContainer.appendChild(newDiv);
        }
    })

    return {
        showNextNotice: showNextNotice,
        showPreviousNotice: showPreviousNotice
    }
})()