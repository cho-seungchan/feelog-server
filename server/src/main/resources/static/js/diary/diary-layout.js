const diaryLayout = (() => {
    const showDiaryListClose = (diaryListData) => {
        console.log(diaryListData)
        const diaryWrap = document.querySelector(".diary_wrap_01");

        let text = ``;

        diaryListData.diaryList.forEach((diary) => {
            const newArticle = document.createElement("article")
            newArticle.classList.add("diary_article_01")

            const dateString = diary.updatedDate;
            const date = new Date(dateString);
            const tagLists = new Array();
            const formattedDate = date.toLocaleDateString("ko-KR", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit"
            }).replace(/-/g, ".");

            let channelMainImg = null;
            let diaryMainImg = null;
            const defaultImg = "/images/avatar_blank.png"

            if (diary.memberFilePath !== null && diary.memberFileName !== null) {
                channelMainImg = encodeURIComponent(`${diary.memberFilePath}/${diary.memberFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }

            if (diary.diaryFilePath !== null && diary.diaryFileName !== null) {
                diaryMainImg = encodeURIComponent(`${diary.diaryFilePath}/${diary.diaryFileName}`);
            }


            if (diary.diaryTags) {
                diary.diaryTags.forEach((tag) => {
                    const aTag = document.createElement("a");
                    aTag.classList.add("aTag_myDiary_01", "aTag_myDiary_03", "aTag_tag_01")
                    aTag.href = "";
                    aTag.innerHTML = `
                        <span class="MuiChip-label MuiChip-labelMd joy-tymi7a" id=":r15:">${tag}</span>
                    `;
                    tagLists.push(aTag);
                })
            }

            text = `
                <div
                    class="post_mainContainer_01"
                >
                    <div
                        class="diary_wrap_02 button-wrap"
                    >
                        <div
                            class="div_imgContainer_01"
                        >
                            <div
                                class="diary_header_01"
                            >
                                <div
                                    class="diary_nicknameWrap_01"
                                >
                                    <p
                                        class="nickname_01"
                                    >
                                        ${diary.memberNickname}
                                    </p>
                                </div>
                                <div
                                    class=" "
                                >
                                    <a
                                        class="channer_a_01 known-channel"
                                        href="/feelog.com/@${diary.channelUrl}/community"
                                        ><span
                                            class="channer_aTag_span_01"
                                            ><time
                                                class="a_date_01"
                                                >${formattedDate}</time
                                            ><span
                                                class="a_channerName_01"
                                                >&nbsp;·&nbsp;</span
                                            >${diary.channelTitle}</span
                                        ></a
                                    >
                                </div>
                            </div>
                        </div>
                        <button
                            data-index=${diary.id}
                            aria-haspopup="menu"
                            aria-expanded="false"
                            aria-controls=":r12:"
                            class="uploadButton_01 menuButton_css_01 menu-button"
                            type="button"
                        >
                            <svg
                                focusable="false"
                                aria-hidden="true"
                                viewBox="0 0 24 24"
                                width="24"
                                height="24"
                                fill="none"
                                xmlns="http://www.w3.org/2000/svg"
                                class="post_menuButton_svg_01"
                            >
                                <path
                                    d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z"
                                    fill="currentcolor"
                                ></path>
                            </svg>
                        </button>
                    </div>
                    <div
                        class="post_container_02"
                    >
                        <div
                            class="diary_container_01"
                        >
                            <a
                                class="channer_a_01"
                                href="/diary/diary-read?id=${diary.id}"
                                ><h2
                                    class="a_h2_01"
                                >
                                    ${diary.diaryTitle}
                                </h2>
                            </a>
                        </div>
                        <p
                            class=" post_content_01"
                        >
                            ${diary.diaryContent}
                        </p>
                        <div
                            class="aTag_wrap_01"
                        >
                        </div>
                    </div>
                    <a href="/diary/diary-read?id=${diary.id}" style="text-decoration: none">
                        <div
                            class=" swiper_container_01"
                        >
                            <div
                                class="swiper swiper-initialized swiper-horizontal swiper-backface-hidden"
                                style="width: 100%"
                            >
                                <div
                                    class="swiper-wrapper"
                                    style="
                                        transform: translate3d(
                                            0px,
                                            0px,
                                            0px
                                        );
                                    "
                                >
                                    <div
                                        class="swiper-slide swiper-slide-active"
                                        style="
                                            margin-right: 12px;
                                        "
                                    >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="ai-container">
                            <div class="ai-title" style="color: #202022">
                                <span class="ai-think">AI의 생각</span>
                                <div class="comment-wrap">
                                    <span
                                        >오늘의 점수는 ${diary.diaryScore}점이에요.😊</span
                                    ></br>
                                    <div>
                                        ${diary.scoreMessage}</div>
                                </div>
                            </div>
                        </div>
                        <div
                            class="post_infoContainer_01"
                        >
                            <div
                                class="post_infoWrap_01"
                            >
                                <div
                                    class="post_infoWrap_02"
                                >
                                    <p
                                        class="post_readcount_01"
                                    >
                                        <span
                                            class="p_span_01"
                                            ><svg
                                                focusable="false"
                                                aria-hidden="true"
                                                viewBox="0 0 24 24"
                                                width="24"
                                                height="24"
                                                fill="none"
                                                xmlns="http://www.w3.org/2000/svg"
                                                class="post_menuButton_svg_01"
                                            >
                                                <path
                                                    d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z"
                                                    fill="currentcolor"
                                                ></path></svg></span
                                        >${diary.diaryReadCount}
                                    </p>
                                    <p
                                        class="post_readcount_01 like-count"
                                    >
                                        <span
                                            class="p_span_01"
                                            ><svg
                                                focusable="false"
                                                aria-hidden="true"
                                                viewBox="0 0 24 24"
                                                width="24"
                                                height="24"
                                                fill="none"
                                                xmlns="http://www.w3.org/2000/svg"
                                                class="post_menuButton_svg_01"
                                            >
                                                <path
                                                    d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z"
                                                    fill="currentcolor"
                                                ></path></svg></span
                                        >${diary.likeCount}
                                    </p>
                                    <p
                                        class="post_readcount_01"
                                    >
                                        <span
                                            class="p_span_01"
                                            ><svg
                                                focusable="false"
                                                aria-hidden="true"
                                                viewBox="0 0 24 24"
                                                width="24"
                                                height="24"
                                                fill="none"
                                                xmlns="http://www.w3.org/2000/svg"
                                                class="post_menuButton_svg_01"
                                            >
                                                <path
                                                    d="M6.832 17.535a1.877 1.877 0 0 1 1.742-.25c1.035.375 2.194.59 3.428.59 4.87 0 8.123-3.145 8.123-6.25s-3.253-6.25-8.123-6.25c-4.87 0-8.122 3.145-8.122 6.25 0 1.25.484 2.453 1.394 3.484.336.38.5.88.46 1.387a6.92 6.92 0 0 1-.44 1.93 9.811 9.811 0 0 0 1.538-.887v-.004Zm-3.999 1.586c.07-.105.137-.21.2-.316.39-.649.76-1.5.835-2.457-1.172-1.332-1.863-2.961-1.863-4.723 0-4.488 4.475-8.125 9.997-8.125C17.526 3.5 22 7.137 22 11.625c0 4.488-4.475 8.125-9.998 8.125-1.448 0-2.823-.25-4.065-.7-.465.34-1.222.805-2.12 1.196a9.564 9.564 0 0 1-1.957.629c-.031.008-.062.012-.094.02-.171.03-.34.058-.515.074-.008 0-.02.004-.027.004-.2.02-.399.03-.598.03a.625.625 0 0 1-.445-1.066 5.606 5.606 0 0 0 .629-.797l.011-.019h.012Z"
                                                    fill="currentcolor"
                                                ></path></svg></span
                                        >${diary.replyCount}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </article>
            `;

            newArticle.innerHTML = text;
            diaryWrap.appendChild(newArticle);

            const tagContainer = newArticle.querySelector(".aTag_wrap_01");
            tagLists.forEach((tag) => {
                tagContainer.appendChild(tag);
            })

            const diaryMainImgWrap = newArticle.querySelector(".swiper-slide-active");
            const nameWrap = newArticle.querySelector(".nickname_01");
            const channelAtag = document.createElement("a");
            const channelUrl = newArticle.querySelector(".known-channel")
            channelAtag.classList.add("article_aTag_01");

            if (diary.diaryNameOpen === "KNOWN") {
                nameWrap.innerText = diary.memberNickname
                if (channelMainImg !== null) {
                    channelAtag.setAttribute("href", `/feelog.com/@${diary.channelUrl}/community`)
                    channelAtag.innerHTML = `
                    <div class="aTag_div_01">
                        <img alt="" src="/files/display?path=${channelMainImg}" loading="lazy" class="aTag_divImg_01">
                    </div>
                    `;
                } else {
                    channelAtag.innerHTML = `
                    <div class="aTag_div_01">
                        <img alt="" src=${defaultImg} loading="lazy" class="aTag_divImg_01">
                    </div>
                    `;
                }

                newArticle.prepend(channelAtag)
            } else {
                nameWrap.innerText = "익명"
                channelAtag.classList.add("unknown-member")
                channelUrl.classList.replace("known-channel", "unknown-member")
                channelUrl.removeAttribute("href")

                channelAtag.innerHTML = `
                    <div class="aTag_div_01">
                        <img alt="" src=${defaultImg} loading="lazy" class="aTag_divImg_01">
                    </div>
                    `;
                newArticle.prepend(channelAtag)
            }

            if (diaryMainImg !== null) {
                diaryMainImgWrap.innerHTML = `
                    <img class="" src="/files/display?path=${diaryMainImg}" alt="" draggable="false" loading="lazy">
                `;
            } else {
                diaryMainImgWrap.innerHTML = `
                    <img class="" src=${defaultImg} alt="" draggable="false" loading="lazy">
                `;
            }

            const likeWrap = newArticle.querySelector(".like-count");
            if (diary.liked) {
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
                </span>${diary.likeCount}
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
                </span>${diary.likeCount}
                `;
            }

            text = ``;
        })
    }

    const showRandomDiary = (randomDiaryListData) => {
        const randomPostWrap = document.querySelector(".recommend_post_wrap_01");

        randomDiaryListData.forEach((diary) => {
            const newDiv = document.createElement("div")
            newDiv.classList.add("post_container_04");

            const date = diary.updatedDate;
            const newDate = new Date(date);
            const formatDate = newDate.toISOString().split("T")[0];


            newDiv.innerHTML = `
                    <div class="post_wrap_02">
                        <a href="/diary/diary-read?id=${diary.id}" class="post_a_01"><div class="imgWrap_03">
                                <div class="img_container_01" data-first-child="">
                                    <div class="img_wrap_01">
                                    </div>
                                </div></div></a>
                        <div class="recommend_post_container_02">
                            <a class="recommend_post_a_01" href="/diary/diary-read?id=${diary.id}"><h3 class="recommend_h3_01">
                                    ${diary.diaryTitle}
                                </h3></a>
                            <div class="recommend_channelInfo_wrap_01 tagWrap" >
                            </div>
                            <a class="post_info_03" href="/diary/diary-read?id=${diary.id}"><p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path></svg></span>${diary.diaryReadCount}
                                </p>
                                <p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path></svg></span>${diary.likeCount}
                                </p>
                                <p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="M6.832 17.535a1.877 1.877 0 0 1 1.742-.25c1.035.375 2.194.59 3.428.59 4.87 0 8.123-3.145 8.123-6.25s-3.253-6.25-8.123-6.25c-4.87 0-8.122 3.145-8.122 6.25 0 1.25.484 2.453 1.394 3.484.336.38.5.88.46 1.387a6.92 6.92 0 0 1-.44 1.93 9.811 9.811 0 0 0 1.538-.887v-.004Zm-3.999 1.586c.07-.105.137-.21.2-.316.39-.649.76-1.5.835-2.457-1.172-1.332-1.863-2.961-1.863-4.723 0-4.488 4.475-8.125 9.997-8.125C17.526 3.5 22 7.137 22 11.625c0 4.488-4.475 8.125-9.998 8.125-1.448 0-2.823-.25-4.065-.7-.465.34-1.222.805-2.12 1.196a9.564 9.564 0 0 1-1.957.629c-.031.008-.062.012-.094.02-.171.03-.34.058-.515.074-.008 0-.02.004-.027.004-.2.02-.399.03-.598.03a.625.625 0 0 1-.445-1.066 5.606 5.606 0 0 0 .629-.797l.011-.019h.012Z" fill="currentcolor"></path></svg></span>${diary.replyCount}
                                </p></a>
                                <div class="profile_container_01">
                                    <a href="" aria-label="프로필" class="profile_a_01">
                                        <div class="profile_a_div_01">
                                        </div>
                                    </a>
                                    <div class="profile_infoWrap_01">
                                        <a href="/profile/@fz7mzt" class="a_nickname_01"><span class="span_nickname_01">${diary.memberNickname}</span></a>
                                        <p class="date_p_01">
                                            ·${formatDate}
                                        </p>
                                    </div>
                                </div>
                        </div>
                    </div>
            `;

            randomPostWrap.appendChild(newDiv);

            let channelMainImg = null;
            let diaryMainImg = null;
            let defaultImg = "/images/avatar_blank.png"

            if (diary.channelFilePath != null && diary.channelFileName != null) {
                channelMainImg = encodeURIComponent(`${diary.channelFilePath}/${diary.channelFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }
            if (diary.diaryFilePath != null && diary.diaryFileName != null) {
                diaryMainImg = encodeURIComponent(`${diary.diaryFilePath}/${diary.diaryFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }

            const postImgWrap = newDiv.querySelector(".img_wrap_01");
            const channelImgWrap = newDiv.querySelector(".profile_a_div_01");


            if (diaryMainImg) {
                postImgWrap.innerHTML = `
                <img alt=${diary.diaryTitle} loading="lazy" decoding="async" data-nimg="fill" src="/files/display?path=${diaryMainImg}" style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;">
                `;
            } else {
                postImgWrap.innerHTML = `
                <img alt=${diary.diaryTitle} loading="lazy" decoding="async" data-nimg="fill" src=${defaultImg} style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;">
                `;
            }

            if (channelMainImg) {
                channelImgWrap.innerHTML = `
                    <img alt=${diary.channelMemberNickname} src="/files/display?path=${channelMainImg}" loading="lazy" class="aTag_divImg_01">
                `;
            } else {
                channelImgWrap.innerHTML = `
                    <img alt=${diary.channelMemberNickname} src=${defaultImg} loading="lazy" class="aTag_divImg_01">
                `;
            }

            if (diary.diaryTags) {
                const tagWrap = newDiv.querySelector(".tagWrap");
                diary.diaryTags.forEach((tag) => {
                    const newAtag = document.createElement("a")
                    newAtag.classList.add("aTag_myDiary_01", "aTag_myDiary_03", "aTag_tag_01")
                    newAtag.setAttribute("href", `/search/search?keyword=${tag}`);

                    newAtag.innerHTML = `
                    <span class="Md aTag_span_01">${tag}</span>
                    `;
                    tagWrap.appendChild(newAtag)
                })
            }
        })
    }

    const showReplyList = (replyListData) => {
        const replyContainer = document.querySelector(".reply-container");

        replyListData.forEach((reply) => {
            const newDiv = document.createElement("div");
            newDiv.classList.add("diary_div_001");
            newDiv.innerHTML = `
                <div class="diary_container_01" id="comment-19227472">
                    <div class="diary_container_01">
                        <div class="post_container_001">
                            <div class="profile_wrap_01">
                            <a href="" class="profile_a_02">
                                <div class="nickname_imgWrap_01"></div>
                            </a>
                        </div>
                        <div class="post_contentContainer_01">
                            <div class="post_contentWrap_01">
                                <div class="nickname_a_01">
                                    <a href="" class="profile_a_02">
                                        <span class="diary_nicknameWrap_01 nickname_span_01">${reply.memberNickname}</span>
                                    </a>
                                </div>
                                <button aria-label="2025. 3. 18. 01:49" class="date_button_01">
                                    ${timeAgo(reply.updatedDate)}
                                </button>
                            </div>
                            <div class="replyContent_wrap_01">
                                <p class="replyContent_01">
                                    ${reply.replyContent}
                                </p>
                            </div>
                            <div class="likeButton_container_01 button-wrap">
                                <div class="likeButton_wrap_01">
                                    <button class="upload-buttonSize_01 like_button_01 like_button" type="button" data-index=${reply.id}>
                                        <span class="more_diaryButton_span_01 reply-button-wrap unlike-wrap">
                                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="like_svg_01">
                                                <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path></svg></span>${reply.replyLikeCount}
                                    </button>
                                </div>
                                <button data-index=${reply.id} aria-haspopup="menu" aria-expanded="false" aria-controls=":r5a:" aria-label="댓글 더보기 메뉴" class="uploadButton_01 moreReply_button_01 reply-button menu-button" type="button">
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="section_div_svg_02">
                                        <path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            `;

            replyContainer.prepend(newDiv);

            const replyImgWrap = newDiv.querySelector(".replyContent_wrap_01");
            const memberImgWrap = newDiv.querySelector(".nickname_imgWrap_01");
            const likeSvg = newDiv.querySelector(".like_button").querySelector("svg")

            const replyDiv = document.createElement("div");
            replyDiv.classList.add("reply-imgWrap");

            if (reply.replyFileName) {
                replyDiv.innerHTML = `
                    <img class="reply-img" src="/files/display?path=${reply.replyFilePath}/${reply.replyFileName}" alt="">
                `;

                replyImgWrap.appendChild(replyDiv);
            }

            if (reply.memberFilePath) {
                memberImgWrap.innerHTML = `
                    <img alt="" src="/files/display?path=${reply.memberFilePath}/${reply.memberFileName}" loading="lazy" class="aTag_divImg_01"></div></a>
                `;
            } else {
                memberImgWrap.innerHTML = `
                    <img alt="" src="/images/avatar_blank.png" loading="lazy" class="aTag_divImg_01"></div></a>
                `;
            }

            if (reply.liked) {
                likeSvg.classList.remove("like_svg_01");
                likeSvg.classList.add("joy-fkbdob");
                likeSvg.innerHTML = `
                <path d="M7.313 3.268a5.319 5.319 0 0 0-3.761 1.585A5.492 5.492 0 0 0 2 8.667c0 1.415.566 2.81 1.552 3.814l7.86 8.004c.323.33.853.33 1.177 0l7.859-8.004A5.444 5.444 0 0 0 22 8.667c0-1.428-.557-2.8-1.552-3.814a5.27 5.27 0 0 0-3.76-1.585 5.27 5.27 0 0 0-3.761 1.585L12 5.797l-.927-.944a5.319 5.319 0 0 0-3.76-1.585Z" fill="currentcolor"></path>
                `;
            }
        })
    }

    return {
        showDiaryListClose: showDiaryListClose,
        showRandomDiary: showRandomDiary,
        showReplyList: showReplyList
    }
})()