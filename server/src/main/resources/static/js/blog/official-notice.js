document.addEventListener("DOMContentLoaded", () => {
    loadNoticeCards(1);
});

function loadNoticeCards(page = 1) {
    fetch(`/feelog/notice/list-api?page=${page}`)
        .then(res => res.json())
        .then(data => {
            const container = document.getElementById("notice-card-wrapper");
            const paginationContainer = document.getElementById("pagination-container");
            container.innerHTML = '';

            data.notices.forEach(item => {
                const outer = document.createElement("div");
                outer.className = "FlgGrid-root-need jk-feelog-div051";
                outer.appendChild(createNoticeCard(item));
                container.appendChild(outer);
            });

            renderPagination(data.totalCount, data.currentPage);
        });
}

function createNoticeCard(item) {
    const div = document.createElement("div");
    div.className = "FlgStack-root-need jk-feelog-div052";
    div.innerHTML = `
        <div class="FlgStack-root-need normal-post jk-feelog-div053">
            <div class="FlgStack-root-need jk-feelog-div054">
                <a href="/notice/notice?id=${item.id}" class="jk-feelog-a-002">
                    <div class="jk-feelog-div055">
                        <div class="FlgBox-root-need" data-first-child="">
                            <div class="FlgBox-root-need jk-feelog-div056">
                                <img alt="${item.title}" loading="lazy" decoding="async"
                                     src="${(item.filePath && item.fileName && item.filePath.trim() !== '' && item.fileName.trim() !== '') ?
        '/files/display?path=' + item.filePath + '/' + item.fileName :
        '/images/channel_notice.png'}"
                                    style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;" />
                            </div>
                        </div>
                    </div>
                </a>
                <a class="FlgStack-root-need jk-feelog-a-003" href="/notice/notice?id=${item.id}">
                    <div class="FlgStack-root-need jk-feelog-div057">
                        <h3 class="jk-feelog-h3-001">${item.title}</h3>
                        <p class="jk-feelog-p010">${stripHtml(item.noticeContent)}</p>
                    </div>
                </a>
            </div>
            <div class="FlgStack-root-need jk-feelog-div031">
                <div class="FlgStack-root-need jk-feelog-div058">
                    <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004">
                        <span class="FlgChip-label-need jk-feelog-span006">공지사항</span>
                    </a>
                </div>
                <div class="FlgStack-root-need jk-feelog-div059">
                    <a class="jk-feelog-a-007">
                        <div class="jk-feelog-div060">
                            <img alt="Feelog" src="/images/avatar_blank.png" class="jk-feelog-img001" />
                        </div>
                    </a>
                    <a class="FlgBox-root-need jk-feelog-a-008">
                        <p class="jk-feelog-p012">${item.memberNickname || 'Feelog 공식'}</p>
                    </a>
                    <div class="jk-feelog-div061">
                        <div class="FlgStack-root-need jk-feelog-div062">
                            <p class="jk-feelog-p013">·${new Date(item.createdDate).toLocaleDateString('ko-KR')}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
    return div;
}

function renderPagination(totalCount, current) {
    const paginationContainer = document.getElementById("pagination-container");
    const limit = 9;
    const totalPages = Math.ceil(totalCount / limit);
    paginationContainer.innerHTML = '';

    const maxButtons = 10;
    const half = Math.floor(maxButtons / 2);

    let start = current - half;
    let end = current + half - 1;

    if (start < 1) {
        start = 1;
        end = Math.min(totalPages, maxButtons);
    }

    if (end > totalPages) {
        end = totalPages;
        start = Math.max(1, end - maxButtons + 1);
    }

    if (current > 1) {
        paginationContainer.insertAdjacentHTML('beforeend', `
            <button aria-label="이전 페이지" class="FlgIconButton-root-need FlgIconButton-sizeSm-need jk-feelog-btn001"
                    onclick="loadNoticeCards(${current - 1})">
                <svg viewBox="0 0 24 24" width="24" height="24" fill="none"
                    xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg01">
                    <path d="M8.713 11.332a.934.934 0 0 0 0 1.325l5.25 5.254a.937.937 0 0 0 1.324-1.324l-4.586-4.59 4.59-4.586a.937.937 0 0 0-1.324-1.324l-5.254 5.245Z"
                        fill="currentcolor"></path>
                </svg>
            </button>
        `);
    }

    for (let i = start; i <= end; i++) {
        paginationContainer.insertAdjacentHTML('beforeend', `
            <button type="button"
                    class="FlgIconButton-root-need FlgIconButton-sizeSm-need ${i === current ? 'jk-feelog-btn015' : 'jk-feelog-btn001'}"
                    onclick="loadNoticeCards(${i})">${i}</button>
        `);
    }

    if (current < totalPages) {
        paginationContainer.insertAdjacentHTML('beforeend', `
            <button aria-label="다음 페이지" class="FlgIconButton-root-need FlgIconButton-sizeSm-need jk-feelog-btn001"
                    onclick="loadNoticeCards(${current + 1})">
                <svg viewBox="0 0 24 24" width="24" height="24" fill="none"
                    xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg01">
                    <path d="M15.291 11.332a.934.934 0 0 1 0 1.325l-5.25 5.254a.937.937 0 0 1-1.324-1.324L13.303 12l-4.59-4.59a.937.937 0 0 1 1.324-1.324l5.254 5.245Z"
                        fill="currentcolor"></path>
                </svg>
            </button>
        `);
    }
}

function stripHtml(html) {
    const div = document.createElement("div");
    div.innerHTML = html || '';
    return (div.textContent || div.innerText || '').trim().slice(0, 80);
}
