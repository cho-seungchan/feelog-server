readService.getNextPost(readLayout.showNextPost, postInfo.channelId, postInfo.id)
readService.getPreviousPost(readLayout.showPreviousPost, postInfo.channelId, postInfo.id)
readService.getRandomPost(readLayout.showRandomPost);

const postButtons = document.querySelectorAll(".post-button");
const scrapButtons = document.querySelectorAll(".scrap-button_01");
const likeButtons = document.querySelectorAll(".like_button");
const replyButton = document.querySelector(".reply_button");
const subscribe = document.querySelector(".subscribe-button");
const moreButton = document.querySelector(".more_diaryButton_02");

const div = document.createElement("div");
div.id = "report-button";

let text = ``;
postButtons.forEach((postButton) => {
    postButton.addEventListener("click", (e) => {
        const existingDiv = document.querySelector("#report-button");

        if (existingDiv) {
            existingDiv.remove();
        } else {
            text = `
        <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
            <a tabindex="-1" id=":r2p:" role="menuitem" class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p">
                <p class=" button_text_01">신고하기</p>
            </a>
        </ul>
        `;
            div.innerHTML = text;

            const buttonWrap = e.target.closest(".button-wrap");

            const rect = postButton.getBoundingClientRect();
            const absoluteTop = rect.bottom + window.scrollY;
            const absoluteLeft = rect.left + window.scrollX;

            div.style.position = "absolute";
            div.style.top = `${absoluteTop}px`;
            div.style.left = `${absoluteLeft}px`;
            buttonWrap.appendChild(div);
        }
    });
});

scrapButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
        const wrap = e.currentTarget; // 항상 button 요소가 됨
        const svg = wrap.querySelector("svg"); // 내부 svg 찾기

        if (!svg) return;

        console.log("현재 svg 클래스:", svg.className);

        if (svg.classList.contains("scrap-button_svg_01")) {
            svg.classList.remove("scrap-button_svg_01");
            svg.classList.add("joy-1wbk7pq");

            svg.innerHTML = `
                <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
            `;
        } else if (svg.classList.contains("joy-1wbk7pq")) {
            console.log("----check----");

            svg.classList.remove("joy-1wbk7pq");
            svg.classList.add("scrap-button_svg_01");

            svg.innerHTML = `
                <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
            `;
        }
    });
});

likeButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
        const wrap = e.currentTarget;
        const svg = wrap.querySelector("svg");
        console.log(svg);

        if (!svg) return;

        if (svg.classList.contains("like_svg_01")) {
            svg.classList.remove("like_svg_01");
            svg.classList.add("joy-fkbdob");
            svg.innerHTML = `
            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
            `;
        } else if (svg.classList.contains("joy-fkbdob")) {
            svg.classList.remove("joy-fkbdob");
            svg.classList.add("like_svg_01");
            svg.innerHTML = `
            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
            `;
        }

        if (svg.classList.contains("post_menuButton_svg_01")) {
            svg.classList.remove("post_menuButton_svg_01");
            svg.classList.add("joy-13ig9df");
            svg.innerHTML = `
            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
            `;
        } else if (svg.classList.contains("joy-13ig9df")) {
            svg.classList.remove("joy-13ig9df");
            svg.classList.add("post_menuButton_svg_01");
            svg.innerHTML = `
            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
            `;
        }
    });
});

document.addEventListener("click", async (e) => {
    if (e.target.classList.contains("subscribe-button")) {
        if (!loginMember) {
            alert("로그인 후 이용해주세요");
            window.location.href = "/login/login";
            return;
        }

        const subscribeButton = document.querySelector(".add_channelWrap_01");
        const channelId = Number(e.target.getAttribute("data-index"));

        if (e.target.classList.contains("add_channelButton_01")) {
            if (confirm("구독하시겠습니까?")) {
                await readService.addSubscribe(channelId);
                subscribeButton.innerHTML = `
                    <button class="MuiButton-root MuiButton-variantSoft MuiButton-colorNeutral MuiButton-sizeMd subscribe-button subscribing" type="button" data-index=${channelId}>
                        구독 중
                    </button>
                `;
            }
        } else {
                if (confirm("구독을 해지하시겠습니까?")) {
                    await readService.deleteSubscribe(channelId);
                    subscribeButton.innerHTML = `
                        <button class="more_diaryButton_01 add_channelButton_01 subscribe-button" type="button" data-index=${channelId}>
                            구독
                        </button>
                    `;
            }
        }
    }

    if(e.target.closest(".scrap-button_01")){
        const svg = e.target.closest(".scrap-button_01").querySelector("svg");

        if(loginMember == null){
            alert("로그인 후 이용해주세요")
            window.location.href = "/login/login"
        }
        const memberId = Number(loginMember.id);
        const postId = e.target.closest(".scrap-button_01").getAttribute("data-index")
        await readService.addScrap({
            memberId: memberId,
            postId: postId
        })

        if (svg.classList.contains("post_menuButton_svg_01")) {

            svg.classList.remove("post_menuButton_svg_01");
            svg.classList.add("joy-1wbk7pq");

            svg.innerHTML = `
                <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
            `;
        } else if (svg.classList.contains("joy-1wbk7pq")) {
            svg.classList.remove("joy-1wbk7pq");
            svg.classList.add("post_menuButton_svg_01");

            svg.innerHTML = `
                <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
            `;
        }

    }
});

moreButton.addEventListener("click", async(e) => {
    const randomPostWrap = document.querySelector(".recommend_post_wrap_01");
    randomPostWrap.innerHTML = ``;
    await readService.getRandomPost(readLayout.showRandomPost)
})
