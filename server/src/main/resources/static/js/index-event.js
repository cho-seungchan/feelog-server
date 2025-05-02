indexService.getCheerPost(indexLayout.showCheerPost)
indexService.getList(indexLayout.showList)

const reportDiv = document.createElement("div");
const postContainer = document.querySelector(".joy-16vwv4v");
const body = document.querySelector("body");
const moreButton = document.querySelector(".joy-1fkv557");
const cheerContainer = document.querySelector(".cheerComentContainer");

reportDiv.id = "report-button";

postContainer.addEventListener("click", async (e) => {
    if(e.target.closest(".scrap-button")){
        e.preventDefault()
        const svg = e.target.closest(".scrap-button").querySelector("svg");

        if(!loginMember){
            alert("로그인 후 이용해주세요 😊")
            window.location.href = "/login/login";
        }

        const memberId = loginMemberId;
        const postId = e.target.closest(".joy-11dfn0q").querySelector(".post-id-wrap").getAttribute("data-index")
        await indexService.addScrap({
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

    if(e.target.closest(".menu-button")){
        const existingDiv = document.querySelector("#report-button");
        const postId = e.target.closest(".menu-button").getAttribute("data-index")

        if (existingDiv) {
            existingDiv.remove();
        } else {
            text = `
        <ul role="menu" tabindex="-1" id=":r2l:" class="base-Popper-root MuiMenu-root Mui-expanded MuiMenu-variantPlain MuiMenu-colorNeutral MuiMenu-sizeMd joy-oqjr4q" style="" data-popper-placement="bottom-start">
            <button id=${postId} class="MuiMenuItem-root MuiMenuItem-colorNeutral MuiMenuItem-variantPlain joy-1nwwb6p report-post">
                <p class=" button_text_01">신고하기</p>
            </button>
        </ul>
        `;
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

})

body.addEventListener("click", async (e)=> {
    if(e.target.closest(".report-post")){
        if(loginMember == null){
            alert("로그인 후 이용해주세요")
            window.location.href = "/login/login";
            return;
        }

        const reportListData = await indexService.getReportList();
        const reportPostIds = reportListData.map(report => report.postId);
        const reportPostId = e.target.closest(".report-post").id;
        const duplicationId = reportPostIds.includes(Number(reportPostId));
        console.log(duplicationId)

        if(!duplicationId){
            console.log("들어옴")
            if(confirm("이 게시글을 신고하시겠습니까?")){
                await indexService.addReport({
                    reportMemberId:loginMemberId,
                    postId:reportPostId
                })
            }
        }else{
            alert("이미 신고된 게시글입니다.")
        }
    }
})

moreButton.addEventListener("click", async (e) =>{
    const pageIndex = parseInt(moreButton.getAttribute("data-index"));
    await indexService.getList(indexLayout.showList, pageIndex);
    moreButton.setAttribute("data-index", pageIndex + 1);
})

cheerContainer.addEventListener("click", (e)=>{
    if(e.target.closest(".joy-oklso3")){
        const container = e.target.closest(".joy-16vwv4v");
        const cheerPost = container.querySelector(".cheerPostContainer")
        if(cheerPost){
            cheerPost.classList.add("slide-out")

            setTimeout(()=>{
                cheerPost.remove();
            }, 500);
        }
        if(cheerContainer){
            cheerContainer.classList.add("slide-out")

            setTimeout(()=>{
                cheerContainer.remove();
            }, 500);
        }
    }
})

