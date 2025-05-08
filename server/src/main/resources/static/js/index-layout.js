const indexLayout = (() => {
    const showList = async (postListData) => {
        const postContainer = document.querySelector(".joy-16vwv4v");

        let text = "";

        postListData.postList.forEach((post) => {
            const newArticle = document.createElement("article")
            newArticle.classList.add("MuiStack-root", "joy-65n013");

            const dateString = post.updatedDate;
            const date = new Date(dateString);
            const tagLists = new Array();
            const formattedDate = date.toLocaleDateString("ko-KR", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit"
            }).replace(/-/g, ".");

            let channelMainImg = null;
            let postMainImg = null;
            const defaultImg = "/images/channel_banner.png"

            if (post.memberFilePath !== null && post.memberFileName !== null) {
                channelMainImg = encodeURIComponent(`${post.memberFilePath}/${post.memberFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }

            if (post.postMainFilePath !== null && post.postMainFileName !== null) {
                postMainImg = encodeURIComponent(`${post.postMainFilePath}/${post.postMainFileName}`);
            }


            if (post.tagList) {
                post.tagList.forEach((tag) => {
                    const aTag = document.createElement("a");
                    aTag.classList.add("MuiChip-root", "MuiChip-colorNeutral", "MuiChip-sizeMd", "MuiChip-variantSoft", "joy-7upex7")
                    aTag.href = `/search/search?keyword=${tag.tagContent}`;
                    aTag.innerHTML = `
                        <span class="MuiChip-label MuiChip-labelMd joy-tymi7a" id=":r15:">${tag.tagContent}</span>
                    `;
                    tagLists.push(aTag);
                })
            }


            text = `
                                <!--추후 개인채널 주소 연결-->
                <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-1eriol4" href="/feelog.com/@${post.channelUrl}/community">
                    <div class="MuiAvatar-root MuiAvatar-variantPlain MuiAvatar-colorNeutral MuiAvatar-sizeMd joy-1stxrp2 channelImgWrap">
                    </div>
                </a>
                <div class="MuiStack-root joy-11dfn0q channel-id-wrap" data-index=${post.channelId}>
                    <div class="MuiStack-root joy-17arh4m">
                        <div class="MuiStack-root joy-xb6gjk">
                            <div class="MuiStack-root joy-v2pzzj">
                                <div class="MuiStack-root diary_nicknameWrap_01">
                                    <p class="MuiTypography-root MuiTypography-title-md joy-1ry9yvs">
                                        ${post.channelTitle}
                                    </p>
                                </div>
                                <div class="MuiBox-root joy-0">
                                        <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr" href="/feelog.com/@${post.channelUrl}/community">
                                            <span class="MuiTypography-root MuiTypography-body-sm joy-1iqmx8b">
                                                <time class="MuiTypography-root MuiTypography-inherit joy-1g74u8a">${formattedDate}</time>
                                                <span class="MuiTypography-root MuiTypography-inherit joy-hke6pf">&nbsp;·&nbsp;
                                                </span>${post.channelMemberNickname}
                                            </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="MuiBox-root joy-1rr4qq7"></div>
                        <button data-index=${post.id} aria-haspopup="menu" aria-expanded="false" aria-controls=":r12:" class="MuiIconButton-root MuiIconButton-variantPlain MuiIconButton-colorNeutral MuiIconButton-sizeSm MuiMenuButton-root MuiMenuButton-variantOutlined MuiMenuButton-colorNeutral MuiMenuButton-sizeMd joy-1caho3t menu-button" type="button">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                <path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path>
                            </svg>
                        </button>
                    </div>
                    <div class="MuiStack-root joy-1ka30t1 post-id-wrap" data-index=${post.id}>
                        <div class="MuiStack-root joy-j7qwjs">
                                                    <!--게시글 이동-->
                            <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr" href="/post/read?id=${post.id}"><h2 class="MuiTypography-root MuiTypography-h4 joy-63nzw0">
                                    ${post.postTitle}
                                </h2></a>
                        </div>
                        <p class="MuiTypography-root MuiTypography-body-md joy-bxmlpm">
                            ${post.postContent}
                        </p>
<!--                        태그 추가자리-->
                        <div class="MuiStack-root joy-1r6jpu3">
                        </div>
                    </div>
                    <a href="/post/read?id=${post.id}" style="text-decoration: none">
                        <div class="MuiBox-root joy-8a80tx">
                            <div class="swiper swiper-initialized swiper-horizontal swiper-backface-hidden" style="width: 100%">
                                <div class="swiper-wrapper" style="
                                        transform: translate3d(
                                            0px,
                                            0px,
                                            0px
                                        );
                                    ">
                                    <div class="swiper-slide swiper-slide-active postMainImgWrap" style="
                                            margin-right: 12px;
                                        ">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="MuiStack-root joy-1ribkjo">
                        <div class="MuiStack-root joy-1it29ig">
                            <div class="MuiStack-root joy-5ax1kt">
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postReadCount}
                                </p>
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op like-count">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postLikeCount}
                                </p>
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="M6.832 17.535a1.877 1.877 0 0 1 1.742-.25c1.035.375 2.194.59 3.428.59 4.87 0 8.123-3.145 8.123-6.25s-3.253-6.25-8.123-6.25c-4.87 0-8.122 3.145-8.122 6.25 0 1.25.484 2.453 1.394 3.484.336.38.5.88.46 1.387a6.92 6.92 0 0 1-.44 1.93 9.811 9.811 0 0 0 1.538-.887v-.004Zm-3.999 1.586c.07-.105.137-.21.2-.316.39-.649.76-1.5.835-2.457-1.172-1.332-1.863-2.961-1.863-4.723 0-4.488 4.475-8.125 9.997-8.125C17.526 3.5 22 7.137 22 11.625c0 4.488-4.475 8.125-9.998 8.125-1.448 0-2.823-.25-4.065-.7-.465.34-1.222.805-2.12 1.196a9.564 9.564 0 0 1-1.957.629c-.031.008-.062.012-.094.02-.171.03-.34.058-.515.074-.008 0-.02.004-.027.004-.2.02-.399.03-.598.03a.625.625 0 0 1-.445-1.066 5.606 5.606 0 0 0 .629-.797l.011-.019h.012Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postReplyCount}
                                </p>
                            </div>
                            <div class="MuiStack-root joy-5ax1kt">
                                <button class="MuiIconButton-root MuiIconButton-variantPlain MuiIconButton-colorNeutral MuiIconButton-sizeSm joy-ashs5q scrap-button" type="button">
                                </button>
                            </div>
                        </div>
                        </div>
                    </a>
                </div>
            </div>
            `;


            newArticle.innerHTML = text;
            postContainer.appendChild(newArticle);


            const tagContainer = newArticle.querySelector(".joy-1r6jpu3");
            tagLists.forEach((tag) => {
                tagContainer.appendChild(tag);
            })

            const channelImgWrap = newArticle.querySelector(".channelImgWrap")
            const postMainImgWrap = newArticle.querySelector(".postMainImgWrap")
            if (channelMainImg !== null) {
                channelImgWrap.innerHTML = `
                        <img alt="" src="/files/display?path=${channelMainImg}" loading="lazy" class="MuiAvatar-img aTag_divImg_01">
                `;
            } else {
                channelImgWrap.innerHTML = `
                        <img alt="" src=${defaultImg} loading="lazy" class="MuiAvatar-img aTag_divImg_01">
                `;
            }

            if (postMainImg !== null) {
                postMainImgWrap.innerHTML = `
                    <img class="MuiBox-root joy-1hn5ivd" src="/files/display?path=${postMainImg}" alt="" draggable="false" loading="lazy">
                `;
            } else {
                postMainImgWrap.innerHTML = `
                    <img class="MuiBox-root joy-1hn5ivd" src=${defaultImg} alt="" draggable="false" loading="lazy">
                `;
            }

            const scrapButton = newArticle.querySelector(".scrap-button");
            if (post.scrapped) {
                scrapButton.innerHTML = `
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-1wbk7pq">
                            <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
                        </svg>
                `;
            } else {
                scrapButton.innerHTML = `
                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd post_menuButton_svg_01">
                        <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
                    </svg>
                `;
            }

            const likeWrap = newArticle.querySelector(".like-count");
            if (post.liked) {
                likeWrap.innerHTML = `
                <span class="MuiTypography-startDecorator joy-oqc71l">
                <svg
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        class="joy-fkbdob"
                >
                   <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                </svg
                >
                </span>${post.postLikeCount}
                `;
            } else {
                likeWrap.innerHTML = `
                <span class="MuiTypography-startDecorator joy-oqc71l">
                <svg
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        class="like_svg_01"
                    >
                        <path
                            d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z"
                            fill="currentcolor"
                        ></path>
                    </svg
                >
                </span>${post.postLikeCount}
                `;
            }

            text = "";
        })
    }

    const showCheerPost = async (cheerPostData) => {
        const post = cheerPostData;
        const postContainer = document.querySelector(".joy-16vwv4v");
        let text = "";
        const newArticle = document.createElement("article")
        newArticle.classList.add("MuiStack-root", "joy-65n013", "cheerPostContainer");

        const dateString = post.updatedDate;
        const date = new Date(dateString);
        const tagLists = new Array();
        const formattedDate = date.toLocaleDateString("ko-KR", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit"
        }).replace(/-/g, ".");

        let channelMainImg = null;
        let postMainImg = null;
        const defaultImg = "/images/channel_banner.png"

        if (post.memberFilePath !== null && post.memberFileName !== null) {
            channelMainImg = encodeURIComponent(`${post.memberFilePath}/${post.memberFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
        }

        if (post.postMainFilePath !== null && post.postMainFileName !== null) {
            postMainImg = encodeURIComponent(`${post.postMainFilePath}/${post.postMainFileName}`);
        }


        if (post.tagList) {
            post.tagList.forEach((tag) => {
                const aTag = document.createElement("a");
                aTag.classList.add("MuiChip-root", "MuiChip-colorNeutral", "MuiChip-sizeMd", "MuiChip-variantSoft", "joy-7upex7")
                aTag.href = `/search/search?keyword=${tag.tagContent}`;
                aTag.innerHTML = `
                        <span class="MuiChip-label MuiChip-labelMd joy-tymi7a" id=":r15:">${tag.tagContent}</span>
                    `;
                tagLists.push(aTag);
            })
        }

        text = `
                                <!--추후 개인채널 주소 연결-->
                <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-1eriol4" href="/feelog.com/@${post.channelUrl}/community">
                    <div class="MuiAvatar-root MuiAvatar-variantPlain MuiAvatar-colorNeutral MuiAvatar-sizeMd joy-1stxrp2 channelImgWrap">
                    </div>
                </a>
                <div class="MuiStack-root joy-11dfn0q channel-id-wrap" data-index=${post.channelId}>
                    <div class="MuiStack-root joy-17arh4m">
                        <div class="MuiStack-root joy-xb6gjk">
                            <div class="MuiStack-root joy-v2pzzj">
                                <div class="MuiStack-root diary_nicknameWrap_01">
                                    <p class="MuiTypography-root MuiTypography-title-md joy-1ry9yvs">
                                        ${post.channelTitle}
                                    </p>
                                </div>
                                <div class="MuiBox-root joy-0">
                                        <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr" href="/feelog.com/@${post.channelUrl}/community">
                                            <span class="MuiTypography-root MuiTypography-body-sm joy-1iqmx8b">
                                                <time class="MuiTypography-root MuiTypography-inherit joy-1g74u8a">${formattedDate}</time>
                                                <span class="MuiTypography-root MuiTypography-inherit joy-hke6pf">&nbsp;·&nbsp;
                                                </span>${post.channelMemberNickname}
                                            </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="MuiBox-root joy-1rr4qq7"></div>
                        <button data-index=${post.id} aria-haspopup="menu" aria-expanded="false" aria-controls=":r12:" class="MuiIconButton-root MuiIconButton-variantPlain MuiIconButton-colorNeutral MuiIconButton-sizeSm MuiMenuButton-root MuiMenuButton-variantOutlined MuiMenuButton-colorNeutral MuiMenuButton-sizeMd joy-1caho3t menu-button" type="button">
                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                <path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path>
                            </svg>
                        </button>
                    </div>
                    <div class="MuiStack-root joy-1ka30t1 post-id-wrap" data-index=${post.id}>
                        <div class="MuiStack-root joy-j7qwjs">
<!--                        게시글 이동-->
                            <a class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr" href="/post/read?id=${post.id}"><h2 class="MuiTypography-root MuiTypography-h4 joy-63nzw0">
                                    ${post.postTitle}
                                </h2></a>
                        </div>
                        <p class="MuiTypography-root MuiTypography-body-md joy-bxmlpm">
                            ${post.postContent}
                        </p>
<!--                        태그 추가자리-->
                        <div class="MuiStack-root joy-1r6jpu3">
                        </div>
                    </div>
                    <a href="/post/read?id=${post.id}" style="text-decoration: none">
                        <div class="MuiBox-root joy-8a80tx">
                            <div class="swiper swiper-initialized swiper-horizontal swiper-backface-hidden" style="width: 100%">
                                <div class="swiper-wrapper" style="
                                        transform: translate3d(
                                            0px,
                                            0px,
                                            0px
                                        );
                                    ">
                                    <div class="swiper-slide swiper-slide-active postMainImgWrap" style="
                                            margin-right: 12px;
                                        ">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="MuiStack-root joy-1ribkjo">
                        <div class="MuiStack-root joy-1it29ig">
                            <div class="MuiStack-root joy-5ax1kt">
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postReadCount}
                                </p>
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op like-count">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postLikeCount}
                                </p>
                                <p class="MuiTypography-root MuiTypography-body-sm joy-1e395op">
                                    <span class="MuiTypography-startDecorator joy-oqc71l">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-4rp8fm">
                                            <path d="M6.832 17.535a1.877 1.877 0 0 1 1.742-.25c1.035.375 2.194.59 3.428.59 4.87 0 8.123-3.145 8.123-6.25s-3.253-6.25-8.123-6.25c-4.87 0-8.122 3.145-8.122 6.25 0 1.25.484 2.453 1.394 3.484.336.38.5.88.46 1.387a6.92 6.92 0 0 1-.44 1.93 9.811 9.811 0 0 0 1.538-.887v-.004Zm-3.999 1.586c.07-.105.137-.21.2-.316.39-.649.76-1.5.835-2.457-1.172-1.332-1.863-2.961-1.863-4.723 0-4.488 4.475-8.125 9.997-8.125C17.526 3.5 22 7.137 22 11.625c0 4.488-4.475 8.125-9.998 8.125-1.448 0-2.823-.25-4.065-.7-.465.34-1.222.805-2.12 1.196a9.564 9.564 0 0 1-1.957.629c-.031.008-.062.012-.094.02-.171.03-.34.058-.515.074-.008 0-.02.004-.027.004-.2.02-.399.03-.598.03a.625.625 0 0 1-.445-1.066 5.606 5.606 0 0 0 .629-.797l.011-.019h.012Z" fill="currentcolor"></path>
                                            </svg>
                                            </span>${post.postReplyCount}
                                </p>
                            </div>
                            <div class="MuiStack-root joy-5ax1kt">
                                <button class="MuiIconButton-root MuiIconButton-variantPlain MuiIconButton-colorNeutral MuiIconButton-sizeSm joy-ashs5q scrap-button" type="button">
                                </button>
                            </div>
                        </div>
                        </div>
                    </a>
                </div>
            </div>
            `;


        newArticle.innerHTML = text;
        postContainer.appendChild(newArticle);


        const tagContainer = newArticle.querySelector(".joy-1r6jpu3");
        tagLists.forEach((tag) => {
            tagContainer.appendChild(tag);
        })

        const channelImgWrap = newArticle.querySelector(".channelImgWrap")
        const postMainImgWrap = newArticle.querySelector(".postMainImgWrap")
        if (channelMainImg !== null) {
            channelImgWrap.innerHTML = `
                        <img alt="" src="/files/display?path=${channelMainImg}" loading="lazy" class="MuiAvatar-img aTag_divImg_01">
                `;
        } else {
            channelImgWrap.innerHTML = `
                        <img alt="" src=${defaultImg} loading="lazy" class="MuiAvatar-img aTag_divImg_01">
                `;
        }

        if (postMainImg !== null) {
            postMainImgWrap.innerHTML = `
                    <img class="MuiBox-root joy-1hn5ivd" src="/files/display?path=${postMainImg}" alt="" draggable="false" loading="lazy">
                `;
        } else {
            postMainImgWrap.innerHTML = `
                    <img class="MuiBox-root joy-1hn5ivd" src=${defaultImg} alt="" draggable="false" loading="lazy">
                `;
        }

        const scrapButton = newArticle.querySelector(".scrap-button");
        if (post.scrapped) {
            scrapButton.innerHTML = `
                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-1wbk7pq">
                        <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
                    </svg>
                `;
        } else {
            scrapButton.innerHTML = `
                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd post_menuButton_svg_01">
                    <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
                </svg>
                `;
        }
        const likeWrap = newArticle.querySelector(".like-count");
        if (post.liked) {
            likeWrap.innerHTML = `
                <span class="MuiTypography-startDecorator joy-oqc71l">
                <svg
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        class="joy-fkbdob"
                >
                   <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                </svg
                >
                </span>${post.postLikeCount}
                `;
        } else {
            likeWrap.innerHTML = `
                <span class="MuiTypography-startDecorator joy-oqc71l">
                <svg
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        class="like_svg_01"
                    >
                        <path
                            d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z"
                            fill="currentcolor"
                        ></path>
                    </svg
                >
                </span>${post.postLikeCount}
                `;
        }

        text = "";
    }

    const showNoticeMain = async (listData) => {
        const noticeContainer = document.querySelector(".noticeContainer");

        listData.forEach((notice) => {
            const newArticle = document.createElement("article")
            const newHr = document.createElement("hr");
            newHr.classList.add("MuiDivider-root", "MuiDivider-horizontal", "joy-1txva48")
            newArticle.classList.add("joy-ht0wgf")

            newArticle.innerHTML = `
            <div class="joy-bco1gb">
                <a href="/notice/notice?id=${notice.id}" class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr"><h2 class="MuiTypography-root MuiTypography-title-sm joy-lf9fqo">
                        ${notice.noticeTitle}
                    </h2></a>
                <div class="joy-cboudr">
                    <div class="joy-loaz0u">
                        <div class="MuiAvatar-root MuiAvatar-variantSoft MuiAvatar-colorNeutral MuiAvatar-sizeXs2 joy-1fpbmdi">
                            <img alt="이용자 포스타입" src="/images/channel_banner.png" loading="lazy" class="MuiAvatar-img aTag_divImg_01">
                        </div>
                        <a href="/notice/notice" class="MuiLink-root MuiLink-colorPrimary MuiLink-body-md MuiLink-underlineNone joy-151y3tr"><span class="MuiTypography-root MuiTypography-title-sm joy-ixn4cl">필로그</span></a>
                    </div>
                    <p class="MuiTypography-root MuiTypography-body-sm joy-zpfz47">
                        ${timeAgo(notice.updatedDate)}
                    </p>
                </div>
            </div>
            <div class="MuiAspectRatio-root joy-1x63z2m">
                <div class="MuiAspectRatio-content MuiAspectRatio-variantSoft MuiAspectRatio-colorNeutral joy-1h16">
                </div>
            </div>
            `;

            const defaultImg = "/images/channel_notice.png";
            const imgWrap = newArticle.querySelector(".joy-1h16");
            let noticeImg = null;

            if (notice.noticeFileName && notice.noticeFileName.trim() !== '' && notice.noticeFilePath !== '') {
                noticeImg = encodeURIComponent(`${notice.noticeFilePath}/${notice.noticeFileName}`);
                imgWrap.innerHTML = `
                <img class="MuiBox-root joy-0" src="/files/display?path=${noticeImg}" alt="" data-first-child="">
                `;
            } else {
                imgWrap.innerHTML = `
                    <img class="MuiBox-root joy-0" src="${defaultImg}" alt="" data-first-child="">
                `;
            }
            noticeContainer.appendChild(newArticle)
            noticeContainer.appendChild(newHr)
        })
    }

    return {
        showList: showList,
        showCheerPost: showCheerPost,
        showNoticeMain: showNoticeMain
    }
})()