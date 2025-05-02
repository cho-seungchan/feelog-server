readService.getNextPost(readLayout.showNextPost, postInfo.channelId, postInfo.id)
readService.getPreviousPost(readLayout.showPreviousPost, postInfo.channelId, postInfo.id)
readService.getRandomPost(readLayout.showRandomPost);
readService.getReplyList(readLayout.showReplyList);

const reportDiv = document.createElement("div");
const replyContainer = document.querySelector(".reply_container_01");
const subscribe = document.querySelector(".subscribe-button");
const moreButton = document.querySelector(".more_diaryButton_02");
const addImg = document.querySelector(".upload_buttonContainer_02");
const footerButtons = document.querySelector(".footer_navBar");

reportDiv.id = "report-button";


let text = ``;

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

const writeReplyButton = document.querySelector(".uploadButton_css_01");

if(writeReplyButton){
    writeReplyButton.addEventListener("click", async (e) => {
        const replyCountText = document.querySelector(".reply-count-button").querySelector("p");
        const replyContent = document.querySelector(".textareaInput_02");
        const replyWrap = document.querySelector(".reply-container");
        const uploadFile = e.target.closest(".upload_buttonContainer_02").querySelector(".upload-file");
        let replyCount = postInfo.replyCount;

        if(uploadFile){
            const filePath = uploadFile.getAttribute("data-file-path")
            const fileName = uploadFile.getAttribute("data-file-name")

            await readService.addReply({
                postId:postInfo.id,
                memberId:loginMember.id,
                replyContent:replyContent.value,
                replyFilePath:filePath,
                replyFileName:fileName,
                postMemberId:postInfo.memberId
            })
        }else {
            await readService.addReply({
                postId:postInfo.id,
                memberId:loginMember.id,
                replyContent:replyContent.value,
                postMemberId:postInfo.memberId
            })
        }
        alert("댓글이 등록됐습니다.")
        replyCount += 1;
        replyCountText.innerText = replyCount
        replyWrap.innerHTML = "";
        replyContent.value = "";
        addImg.querySelector(".joy-jj02o9").remove();

        await readService.getReplyList(readLayout.showReplyList);
    })
}

moreButton.addEventListener("click", async(e) => {
    const randomPostWrap = document.querySelector(".recommend_post_wrap_01");
    randomPostWrap.innerHTML = ``;
    await readService.getRandomPost(readLayout.showRandomPost)
})
if(addImg){
    addImg.addEventListener("change", (e) => {
        console.log(e.target.files)
        const files = e.target.files[0]; // FileList 객체

        const formData = new FormData();
        formData.append("file", files);
        // 서버로 전송하여 path와 썸네일 생성
        inputFileUpload(formData);
    })

    addImg.addEventListener("click",  (e) => {
        if(e.target.closest(".joy-vbxkza")){
            e.target.closest(".joy-jj02o9").remove();
            return;
        }
    })
}

if(document.querySelector(".image-wrap-reply")){
    document.querySelector(".image-wrap-reply").addEventListener("click",(e) => {
        const imgWrap = addImg.querySelector(".upload-file");
        console.log(imgWrap)
        if(imgWrap){
            alert("이미지는 하나만 가능합니다.")
            e.stopPropagation();
            e.preventDefault();
            return;
        }
    })
}

replyContainer.addEventListener("click", async (e) => {
    if(e.target.closest(".like_button")){
        if (!loginMember) {
            alert("로그인 후 이용해주세요");
            window.location.href = "/login/login";
            return;
        }
        const replyId = e.target.closest(".like_button").getAttribute("data-index")
        const replyWrap = document.querySelector(".reply-container");


        await readService.addReplyLike({
            replyId:Number(replyId),
            memberId:Number(loginMember.id)
        })

        replyWrap.innerHTML = "";
        await readService.getReplyList(readLayout.showReplyList);
    }
})

document.body.addEventListener("click", async (e) => {
    if(e.target.closest(".menu-button")){
        const existingDiv = document.querySelector("#report-button");

        if (existingDiv) {
            existingDiv.remove();
        } else {
            if(e.target.closest(".post-button")){
            const postId = e.target.closest(".post-button").getAttribute("data-index")

            text = `
                <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
                    <button id=${postId} class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p report-button report-post">
                        <p class=" button_text_01">신고하기</p>
                    </button>
                </ul>
            `;
            }else {
                const replyId = e.target.closest(".reply-button").getAttribute("data-index");

                text = `
                <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
                    <button id=${replyId} class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p report-button report-reply">
                        <p class=" button_text_01">신고하기</p>
                    </button>
                </ul>
            `;
            }

            reportDiv.innerHTML = text;

            const rect = e.target.closest(".menu-button").getBoundingClientRect();
            const absoluteTop = rect.bottom + window.scrollY;
            const absoluteLeft = rect.left + window.scrollX;

            reportDiv.style.position = "absolute";
            reportDiv.style.top = `${absoluteTop}px`;
            reportDiv.style.left = `${absoluteLeft}px`;
            document.body.appendChild(reportDiv);
        }
    }

    if(e.target.closest(".report-button")){
        if (!loginMember) {
            alert("로그인 후 이용해주세요");
            window.location.href = "/login/login";
            return;
        }

        if(e.target.closest(".report-post")){
            const reportListData = await readService.getReportList();
            const reportPostIds = reportListData.map(report => report.postId);
            const reportPostId = e.target.closest(".report-post").id;
            const duplicationId = reportPostIds.includes(Number(reportPostId));

            if(!duplicationId){
                if(confirm("이 게시글을 신고하시겠습니까?")){
                    await readService.addReport({
                        reportMemberId:loginMember.id,
                        postId:reportPostId
                    })
                    alert("신고완료")
                }
            }else{
                alert("이미 신고된 게시글입니다.")
            }
        }

        if(e.target.closest(".report-reply")){
            const replyId = e.target.closest(".report-reply").id
            const memberId = Number(loginMember.id);
            if(confirm("이 댓글을 신고하시겠습니까?")){
                const replyData = await readService.getReplyReportCheck(replyId, memberId);

                if(!replyData){
                    await readService.addReplyReport({
                        replyId:replyId,
                        memberId:memberId
                    })
                    alert("신고완료")
                } else{
                    alert("이미 신고된 댓글입니다.")
                }
            }
        }

    }
})

footerButtons.addEventListener("click", async (e) => {
    if(e.target.closest(".like-post-button")){
        if (!loginMember) {
            alert("로그인 후 이용해주세요");
            window.location.href = "/login/login";
            return;
        }
        const likeSvg = e.target.closest(".like-post-button").querySelector("svg");
        const likeCount = e.target.closest(".like-post-button").querySelector("p");

        const postId = e.target.closest(".like-post-button").getAttribute("data-index");
        const memberId = loginMember.id
        await readService.addPostLike({
            postId:postId,
            memberId:memberId
        })

        // 좋아요추가
        if(!postInfo.liked){
            postInfo.liked = true;
            postInfo.likeCount +=1

            likeSvg.classList.remove("like_svg_01");
            likeSvg.classList.add("joy-fkbdob");
            likeSvg.innerHTML = `
            <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
            `;
            likeCount.innerText = postInfo.likeCount

        }else{
            // 좋아요취소
            postInfo.liked = false
            postInfo.likeCount -=1

            likeSvg.classList.remove("joy-fkbdob");
            likeSvg.classList.add("like_svg_01");
            likeSvg.innerHTML = `
            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
            `;

            likeCount.innerText = postInfo.likeCount

        }
    }
})