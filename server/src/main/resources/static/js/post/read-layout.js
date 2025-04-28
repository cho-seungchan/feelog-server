const readLayout = (() => {
    const showNextPost = (nextPostData) => {
        const channelPostWrap = document.querySelector(".channel-posts");


        if(nextPostData) {
            const newATag = document.createElement("a");
            newATag.classList.add("next_a_01");
            newATag.setAttribute("href", `/post/read?id=${nextPostData.id}`);
            newATag.innerHTML = `
                <div class="post_buttonWrap_01" role="button" tabindex="0">
                    <div class="title_subtitleWrap_01">
                        <div class="title_wrap_01">
                            <h5 class="diary_nicknameWrap_01title_h5_01">
                                ${nextPostData.postTitle}
                            </h5>
                        </div>
                    </div>
                        <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_buttonWrap_svg_01">
                            <path d="M15.291 11.332a.934.934 0 0 1 0 1.325l-5.25 5.254a.937.937 0 0 1-1.324-1.324L13.303 12l-4.59-4.59a.937.937 0 0 1 1.324-1.324l5.254 5.245Z" fill="currentcolor"></path>
                        </svg>
                </div>
            `;
            channelPostWrap.appendChild(newATag);
        } else{
            console.log("없음")
        }
    }

    const showPreviousPost = (previousData) => {
        const channelPostWrap = document.querySelector(".channel-posts");

       if(previousData){
           const newATag = document.createElement("a");
           newATag.classList.add("next_a_01");
           newATag.setAttribute("href", `/post/read?id=${previousData.id}`);
           newATag.innerHTML = `
                <div class="post_buttonWrap_01" role="button" tabindex="0">
                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_buttonWrap_svg_01">
                        <path d="M8.713 11.332a.934.934 0 0 0 0 1.325l5.25 5.254a.937.937 0 0 0 1.324-1.324l-4.586-4.59 4.59-4.586a.937.937 0 0 0-1.324-1.324l-5.254 5.245Z" fill="currentcolor"></path>
                    </svg>
                    
                    <div class="title_subtitleWrap_01">
                        <div class="title_wrap_01">
                            <h5 class="diary_nicknameWrap_01title_h5_01">
                                ${previousData.postTitle}
                            </h5>
                        </div>
                        
                    </div>
                </div>
            `;
           channelPostWrap.prepend(newATag)
       }else{
           console.log("없음")
       }
    }

    const showRandomPost = (postData) =>{
        const randomPostWrap = document.querySelector(".recommend_post_wrap_01");

        postData.forEach((post) => {
            console.log(post)

            const newDiv = document.createElement("div")
            newDiv.classList.add("post_container_04");

            const date = post.updatedDate;
            const newDate = new Date(date);
            const formatDate = newDate.toISOString().split("T")[0];



            newDiv.innerHTML = `
                    <div class="post_wrap_02">
                        <a href="/post/read?id=${post.id}" class="post_a_01"><div class="imgWrap_03">
                                <div class="img_container_01" data-first-child="">
                                    <div class="img_wrap_01">
                                    </div>
                                </div></div></a>
                        <div class="recommend_post_container_02">
                            <a class="recommend_post_a_01" href="/post/read?id=${post.id}"><h3 class="recommend_h3_01">
                                    ${post.postTitle}
                                </h3></a>
                            <div class="recommend_channelInfo_wrap_01 tagWrap" >
                            </div>
                            <a class="post_info_03" href="/post/read?id=${post.id}"><p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path></svg></span>${post.postReadCount}
                                </p>
                                <p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path></svg></span>${post.postLikeCount}
                                </p>
                                <p class="post_pTag_01">
                                    <span class="post_span_01"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="post_span_svg_01">
                                            <path d="M6.832 17.535a1.877 1.877 0 0 1 1.742-.25c1.035.375 2.194.59 3.428.59 4.87 0 8.123-3.145 8.123-6.25s-3.253-6.25-8.123-6.25c-4.87 0-8.122 3.145-8.122 6.25 0 1.25.484 2.453 1.394 3.484.336.38.5.88.46 1.387a6.92 6.92 0 0 1-.44 1.93 9.811 9.811 0 0 0 1.538-.887v-.004Zm-3.999 1.586c.07-.105.137-.21.2-.316.39-.649.76-1.5.835-2.457-1.172-1.332-1.863-2.961-1.863-4.723 0-4.488 4.475-8.125 9.997-8.125C17.526 3.5 22 7.137 22 11.625c0 4.488-4.475 8.125-9.998 8.125-1.448 0-2.823-.25-4.065-.7-.465.34-1.222.805-2.12 1.196a9.564 9.564 0 0 1-1.957.629c-.031.008-.062.012-.094.02-.171.03-.34.058-.515.074-.008 0-.02.004-.027.004-.2.02-.399.03-.598.03a.625.625 0 0 1-.445-1.066 5.606 5.606 0 0 0 .629-.797l.011-.019h.012Z" fill="currentcolor"></path></svg></span>${post.postReplyCount}
                                </p></a>
                                <div class="profile_container_01">
<!--                            채널주소-->
                                    <a href="" aria-label="프로필" class="profile_a_01">
                                        <div class="profile_a_div_01">
                                        </div>
                                    </a>
                                    <div class="profile_infoWrap_01">
                                        <a href="/profile/@fz7mzt" class="a_nickname_01"><span class="span_nickname_01">${post.channelMemberNickname}</span></a>
                                        <p class="date_p_01">
                                            ·${formatDate}
                                        </p>
                                    </div>
                                    <div class="scrap-buttonWrap_01">
                                        <button aria-label="스크랩" class="uploadButton_01 scrap-button_01" type="button" data-index=${post.id}>
                                            <input type="hidden" value=${post.scrapped} class="scrappedValue">
                                            <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="scrap-button_svg_01">
                                                <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                        </div>
                    </div>
            `;

            randomPostWrap.appendChild(newDiv);

            let channelMainImg = null;
            let postMainImg = null;
            let defaultImg = "/images/avatar_blank.png"

            if(post.channelFilePath != null && post.channelFileName != null){
                channelMainImg = encodeURIComponent(`${post.channelFilePath}/${post.channelFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }
            if(post.postMainFilePath != null && post.postMainFileName != null){
                postMainImg = encodeURIComponent(`${post.postMainFilePath}/${post.postMainFileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
            }

            const postImgWrap = newDiv.querySelector(".img_wrap_01");
            const channelImgWrap = newDiv.querySelector(".profile_a_div_01");
            const scrapWrap = newDiv.querySelector(".scrap-button_01");


            if(postMainImg){
                postImgWrap.innerHTML = `
                <img alt=${post.postTitle} loading="lazy" decoding="async" data-nimg="fill" src="/files/display?path=${postMainImg}" style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;">
                `;
            }else{
                postImgWrap.innerHTML = `
                <img alt=${post.postTitle} loading="lazy" decoding="async" data-nimg="fill" src=${defaultImg} style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;">
                `;
            }

            if(channelMainImg){
                channelImgWrap.innerHTML = `
                    <img alt=${post.channelMemberNickname} src="/files/display?path=${channelMainImg}" loading="lazy" class="aTag_divImg_01">
                `;
            }else{
                channelImgWrap.innerHTML = `
                    <img alt=${post.channelMemberNickname} src=${defaultImg} loading="lazy" class="aTag_divImg_01">
                `;
            }

            if(post.scrapped){
                scrapWrap.innerHTML = `
                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd joy-1wbk7pq">
                        <path d="M4.5 3.875v17.176a.95.95 0 0 0 1.496.777L12 17.625l6.004 4.203a.95.95 0 0 0 1.496-.777V3.875C19.5 2.84 18.66 2 17.625 2H6.375C5.34 2 4.5 2.84 4.5 3.875Z" fill="currentcolor"></path>
                    </svg>
                `;
            }else{
                scrapWrap.innerHTML = `
                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="MuiSvgIcon-root MuiSvgIcon-sizeMd post_menuButton_svg_01">
                    <path d="M4.5 3.875C4.5 2.84 5.34 2 6.375 2v17.242l5.082-3.629a.933.933 0 0 1 1.09 0l5.078 3.63V3.874H6.375V2h11.25c1.035 0 1.875.84 1.875 1.875v17.188a.938.938 0 0 1-1.48.762L12 17.526l-6.02 4.297a.938.938 0 0 1-1.48-.762V3.875Z" fill="currentcolor"></path>
                </svg>
                `;
            }

            if(post.tagList){
                const tagWrap = document.querySelector(".tagWrap");
                post.tagList.forEach((tag)=>{
                    const newAtag = document.createElement("a")
                    newAtag.classList.add("aTag_myDiary_01", "aTag_myDiary_03", "aTag_tag_01")
                    newAtag.setAttribute("href", `/search/search?keyword=${tag.tagContent}`);

                    newAtag.innerHTML = `
                    <span class="Md aTag_span_01">${tag.tagContent}</span>
                    `;
                    tagWrap.appendChild(newAtag)
                })
            }
        })
    }

    return{
        showNextPost:showNextPost,
        showPreviousPost:showPreviousPost,
        showRandomPost:showRandomPost
    }
})()