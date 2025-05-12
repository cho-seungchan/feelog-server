document.addEventListener("DOMContentLoaded", () => {
    const section = document.querySelector('.jk-feelog-section002');
    const channelUrl = section.dataset.channelUrl;
    const cheerListSection = document.querySelector(".jk-feelog-section003");
    const paginationContainer = document.querySelector(".jk-feelog-div065");

    const limit = 9;
    let currentPage = 1;

    window.loadCheers = function (page) {
        const offset = (page - 1) * limit;

        fetch(`/feelog.com/channel/${channelUrl}/cheers?offset=${offset}&limit=${limit}`)
            .then(res => res.json())
            .then(data => {
                renderCheerCards(data.posts);
                renderPagination(data.totalCount, page);
                currentPage = page;
            })
            .catch(err => console.error("응원글 로드 실패:", err));
    };

    loadCheers(currentPage);

    function renderCheerCards(cheers) {
        cheerListSection.innerHTML = '';

        if (!cheers || cheers.length === 0) {
            cheerListSection.innerHTML = `
                <p class="jk-feelog-no-content-message">
                    아직 작성한 응원글이 없어요.
                </p>
            `;
            return;
        }

        cheers.forEach(d => {
            const thumbnail = d.postFilePath && d.postFileName
                ? `/files/display?path=${d.postFilePath}/${d.postFileName}`
                : '/images/default-thumbnail.png';

            // Null 체크와 빈 값 체크
            const profilePath = (d.memberProfilePath && d.memberProfilePath.trim() !== '' && d.memberProfilePath !== 'null' && d.memberProfilePath !== 'undefined')
                ? d.memberProfilePath
                : null;

            const profileName = (d.memberProfileName && d.memberProfileName.trim() !== '' && d.memberProfileName !== 'null' && d.memberProfileName !== 'undefined')
                ? d.memberProfileName
                : null;

            // 경로 설정
            const profileUrl = (profilePath && profileName)
                ? `/files/display?path=${profilePath}/${profileName}`
                : '/images/avatar_blank.png';

            const tags = d.tags?.map(tag => `
                <a href="/search/post?keyword=${tag}&options_tags=1"
                   class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004">
                    <span class="FlgChip-label-need jk-feelog-span006">${tag}</span>
                </a>
            `).join('') || '';

            const html = `
                <div class="FlgGrid-root-need jk-feelog-div051">
                    <div class="FlgStack-root-need jk-feelog-div052">
                        <div class="FlgStack-root-need normal-post jk-feelog-div053">
                            <div class="FlgStack-root-need jk-feelog-div054">
                                <a href="/post/read?id=${d.postId}" class="jk-feelog-a-002">
                                    <div class="jk-feelog-div055">
                                        <div class="FlgBox-root-need">
                                            <div class="FlgBox-root-need jk-feelog-div056" style="height: 190px">
                                                <img src="${thumbnail}" alt="${d.postTitle}" loading="lazy"
                                                     style="position:absolute; height:100%; width:100%; object-fit:cover;" />
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <a href="/post/read?id=${d.postId}" class="FlgStack-root-need jk-feelog-a-003">
                                    <div class="FlgStack-root-need jk-feelog-div057">
                                        <h3 class="jk-feelog-h3-001">${d.postTitle}</h3>
                                        <p class="jk-feelog-p010">${stripHtml(d.postContent)}</p>
                                    </div>
                                </a>
                            </div>
                            <div class="FlgStack-root-need jk-feelog-div031">
                                <div class="FlgStack-root-need jk-feelog-div058">${tags}</div>
                                <a class="FlgStack-root-need jk-feelog-a-006" href="/feelog.com/@${d.channelUrl}">
                                    <p class="jk-feelog-p011">
                                        <span class="jk-feelog-span007"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg06">
                     <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path></svg></span>${d.viewCount ?? 0}
                                    </p>
                                    <p class="jk-feelog-p011">
                                        <span class="jk-feelog-span007"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg06">
                        <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path></svg></span>${d.likeCount ?? 0}
                                    </p>
                                </a>
                            </div>
                            <div class="FlgStack-root-need jk-feelog-div059">
                                <a href="/feelog.com/@${d.channelUrl}" class="jk-feelog-a-007">
                                    <div class="jk-feelog-div060">
                                        <img alt="${d.memberNickname}" src="${profileUrl}" loading="lazy" class="jk-feelog-img001" />
                                    </div>
                                </a>
                                <a class="FlgBox-root-need jk-feelog-a-008" href="/feelog.com/@${d.channelUrl}">
                                    <p class="jk-feelog-p012">${d.memberNickname}</p>
                                </a>
                                <div class="FlgStack-root-need jk-feelog-div061">
                                    <div class="FlgStack-root-need jk-feelog-div062">
                                        <p class="jk-feelog-p013">·${d.updatedDate}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;

            cheerListSection.insertAdjacentHTML("beforeend", html);
        });
    }

    function renderPagination(totalCount, current) {
        const totalPages = Math.ceil(totalCount / limit);
        paginationContainer.innerHTML = '';

        const maxButtons = 10;
        const half = Math.floor(maxButtons / 2);
        let start = Math.max(1, current - half);
        let end = Math.min(totalPages, start + maxButtons - 1);

        if (current > 1) {
            paginationContainer.insertAdjacentHTML('beforeend', `
                <button onclick="loadCheers(${current - 1})" class="jk-feelog-btn001">←</button>
            `);
        }

        for (let i = start; i <= end; i++) {
            paginationContainer.insertAdjacentHTML('beforeend', `
                <button onclick="loadCheers(${i})" class="${i === current ? 'jk-feelog-btn015' : 'jk-feelog-btn001'}">${i}</button>
            `);
        }

        if (current < totalPages) {
            paginationContainer.insertAdjacentHTML('beforeend', `
                <button onclick="loadCheers(${current + 1})" class="jk-feelog-btn001">→</button>
            `);
        }
    }

    function stripHtml(html) {
        const div = document.createElement("div");
        div.innerHTML = html;
        return (div.textContent || div.innerText || '').trim().slice(0, 80);
    }
});