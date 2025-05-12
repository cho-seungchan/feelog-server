let totalSliderCount = 3;
let loadedSliderCount = 0;
let emptySliderCount = 0;

document.addEventListener('DOMContentLoaded', () => {
    const channelUrl = document.body.dataset.channelUrl;
    const viewerId = document.body.dataset.viewerId || '';

    if (document.querySelector(".write-message-button")) {
        document.querySelector(".write-message-button").addEventListener("click", (e) => {
            const messageContainer = document.querySelector(".message-listContainer");
            const messageButtonImg = document.querySelector(".message-buttonImg");
            const xButtonImg = document.querySelector(".x-buttonImg");

            messageContainer.innerHTML = ``;

            const targetInfo = e.target.closest(".write-message-button");
            const newDiv = document.createElement("div")

            newDiv.classList.add("diary_container_01");

            const chatImg = targetInfo.dataset.img ? `/files/display?path=${targetInfo.getAttribute("data-img")}` : "/images/avatar_blank.png"

            newDiv.innerHTML = `
                <div role="dialog" aria-modal="true" aria-labelledby=":r4j:" aria-describedby=":r4k:" data-google-interstitial="false" tabindex="-1" class="MuiModalDialog-root MuiModalDialog-variantOutlined MuiModalDialog-colorNeutral MuiModalDialog-sizeMd MuiModalDialog-layoutAdaptive joy-l3kvea">
                    <div class="joy-h3xwdq" data-first-child="">
                        <div class="joy-1r5to7m">
                            <a class="joy-uv29nh" href="/feelog.com/@${targetInfo.getAttribute("data-url")}/community">
                                <div class="imgWrap_05">
                                    <img alt="" src=${chatImg} loading="lazy" class="aTag_divImg_01">
                                </div>
                            </a>
                            <p class="title_h5_01">${targetInfo.getAttribute("data-name")}</p>
                        </div>
                        <div class="joy-1j6tmez">
                            <button tabindex="0" aria-haspopup="menu" aria-expanded="false" aria-controls=":r4l:" class="MuiIconButton-sizeMd joy-14llzfj" type="button" data-index=${targetInfo.id}>
                                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01">
                                    <path d="M20.125 12a1.875 1.875 0 1 1-3.75 0 1.875 1.875 0 0 1 3.75 0Zm-6.25 0a1.875 1.875 0 1 1-3.751 0 1.875 1.875 0 0 1 3.751 0ZM5.75 13.875a1.875 1.875 0 1 1 0-3.75 1.875 1.875 0 0 1 0 3.75Z" fill="currentcolor"></path>
                                </svg>
                            </button>
                            <button class="MuiModalClose-root MuiModalClose-variantPlain MuiModalClose-colorNeutral MuiModalClose-sizeMd joy-oklso3" type="button">
                                <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="CloseIcon" class="joy-c6bb0f">
                                    <path d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <hr class="MuiDivider-insetContext joy-12udg69">
                    <div class="joy-uabdzs">
                        <div class="joy-1fptnyw">
                        </div>
                    </div>
                    <hr class="MuiDivider-insetContext joy-12udg69">
                    <div class="joy-1m2yi2j">
                        <div class="joy-spcbp9">
                            <textarea rows="1" placeholder="메시지를 입력하세요." class="textareaInput_01 textareaInput_02" style="height: 28px; overflow-y: auto;"></textarea>
                            <div class="upload_buttonContainer_01 joy-18i8jzi">
                                <button class="button_disabled_01 MuiIconButton-variantSolid MuiIconButton-colorPrimary MuiIconButton-sizeMd joy-ut6eac" type="button" data-index=${targetInfo.id}>
                                    <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="menuButton_svg_01">
                                        <path d="M12.682 3.54a.949.949 0 0 0-.68-.29c-.258 0-.5.105-.68.29L4.76 10.413a.937.937 0 0 0 1.355 1.293l4.95-5.18v13.286c0 .519.418.937.937.937.52 0 .938-.418.938-.938V6.527l4.945 5.184a.938.938 0 0 0 1.355-1.293l-6.562-6.875.004-.004Z" fill="currentcolor"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>                        
            `;
            messageContainer.appendChild(newDiv);

            messageContainer.classList.remove("message-listHidden");
            messageButtonImg.classList.remove("view-messageImg");
            messageButtonImg.classList.add("hidden-messageImg");
            xButtonImg.classList.remove("hidden-xImg");
            xButtonImg.classList.add("view-xImg");
        })
    }

    loadSlider({
        sectionId: 'diary-slider-section',
        wrapperClass: 'diary-section-wrapper',
        nextBtnId: 'diary-slide-nextBtn',
        prevBtnId: 'diary-slide-prevBtn',
        api: `/feelog.com/channel/${channelUrl}/diaries/slide?viewerId=${viewerId}`,
        linkPrefix: '/diary/diary-read?id='
    });

    loadSlider({
        sectionId: 'post-slider-section',
        wrapperClass: 'post-section-wrapper',
        nextBtnId: 'post-slide-nextBtn',
        prevBtnId: 'post-slide-prevBtn',
        api: `/feelog.com/channel/${channelUrl}/posts/slide`,
        linkPrefix: '/post/read?id='
    });

    loadSlider({
        sectionId: 'cheer-slider-section',
        wrapperClass: 'cheer-section-wrapper',
        nextBtnId: 'cheer-slide-nextBtn',
        prevBtnId: 'cheer-slide-prevBtn',
        api: `/feelog.com/channel/${channelUrl}/cheers/slide`,
        linkPrefix: '/post/read?id='
    });
});

// 섹션 제거용
function loadSlider({sectionId, wrapperClass, nextBtnId, prevBtnId, api, linkPrefix}) {
    const section = document.getElementById(sectionId);
    const wrapper = document.querySelector(`.${wrapperClass}`);
    const track = section?.querySelector('.slick-track');

    if (!section || !track) return;

    fetch(api)
        .then(res => res.json())
        .then(data => {
            track.innerHTML = '';

            if (!data || data.length === 0) {
                wrapper?.remove();
                emptySliderCount++;
            } else {
                data.forEach(item => {
                    const html = renderSlideHtml(item, linkPrefix);
                    track.insertAdjacentHTML('beforeend', html);
                });
            }
        })
        .finally(() => {
            loadedSliderCount++;

            // 모든 슬라이더 다 로딩됐고 모두 비어있을 경우만 메시지 출력
            if (loadedSliderCount === totalSliderCount && emptySliderCount === totalSliderCount) {
                const main = document.getElementById('main');
                main.innerHTML = ''; // 기존 내용 제거

                const message = document.createElement('p');
                message.className = 'jk-feelog-no-content-message1';
                message.textContent = '아직 등록된 콘텐츠가 없어요.';

                main.appendChild(message);
            }
        });

    document.getElementById(nextBtnId)?.addEventListener('click', () => {
        track.scrollBy({left: 368, behavior: 'smooth'});
    });

    document.getElementById(prevBtnId)?.addEventListener('click', () => {
        track.scrollBy({left: -368, behavior: 'smooth'});
    });
}

function stripHtml(html) {
    const div = document.createElement('div');
    div.innerHTML = html;
    return (div.textContent || div.innerText || '').trim().slice(0, 80);
}

function renderTags(tags) {
    if (!tags || tags.length === 0) return '';
    return tags.map(tag => `
    <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004">
      <span class="FlgChip-label-need jk-feelog-span006">${tag}</span>
    </a>`).join('');
}

function renderSlideHtml(item, urlPrefix) {
    const content = stripHtml(item.postContent || item.diaryContent);
    const title = item.postTitle || item.diaryTitle;
    const id = item.postId || item.id;

    const thumbnail = item.postFilePath && item.postFileName
        ? `/files/display?path=${item.postFilePath}/${item.postFileName}`
        : item.diaryFilePath && item.diaryFileName
            ? `/files/display?path=${item.diaryFilePath}/${item.diaryFileName}`
            : '/images/default-thumbnail.png';

    const profileImage = item.memberProfilePath && item.memberProfileName
        ? `/files/display?path=${item.memberProfilePath}/${item.memberProfileName}`
        : '/images/avatar_blank.png';

    const tags = item.tagsList || [];

    return `
    <div class="slick-slide" style="width: 368px;">
      <div>
        <div class="FlgBox-root-need joy-nruknm" style="width: 100%; display: inline-block;">
          <div class="FlgStack-root-need jk-feelog-div052">
            <div class="FlgStack-root-need normal-post jk-feelog-div053">
              <div class="FlgStack-root-need jk-feelog-div054">
                <a href="${urlPrefix}${id}" class="jk-feelog-a-002">
                  <div class="jk-feelog-div055">
                    <div class="FlgBox-root-need">
                      <div class="FlgBox-root-need jk-feelog-div056" style="height: 190px;">
                        <img src="${thumbnail}" alt="${title}" style="position: absolute; height: 100%; width: 100%; object-fit: cover;">
                      </div>
                    </div>
                  </div>
                </a>
                <a href="${urlPrefix}${id}" class="FlgStack-root-need jk-feelog-a-003">
                  <div class="FlgStack-root-need jk-feelog-div057">
                    <h3 class="jk-feelog-h3-001">${title}</h3>
                    <p class="jk-feelog-p010">${content}</p>
                  </div>
                </a>
              </div>
              <div class="FlgStack-root-need jk-feelog-div031">
                <div class="FlgStack-root-need jk-feelog-div058">
                  ${renderTags(tags)}
                </div>
                <a class="FlgStack-root-need jk-feelog-a-006" href="/feelog.com/@${item.channelUrl}">
                  <p class="jk-feelog-p011"><span class="jk-feelog-span007"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg06">
                     <path d="M11.999 5.125c-2.547 0-4.64 1.156-6.246 2.645C4.249 9.168 3.21 10.828 2.679 12c.531 1.172 1.57 2.832 3.07 4.23 1.61 1.489 3.703 2.645 6.25 2.645s4.64-1.156 6.246-2.645c1.504-1.398 2.543-3.058 3.074-4.23-.53-1.172-1.57-2.832-3.07-4.23-1.61-1.489-3.703-2.645-6.25-2.645ZM4.476 6.398c1.84-1.71 4.367-3.148 7.523-3.148 3.156 0 5.684 1.438 7.524 3.148 1.828 1.7 3.05 3.727 3.632 5.122.13.308.13.652 0 .96-.582 1.395-1.804 3.426-3.633 5.122-1.84 1.71-4.367 3.148-7.523 3.148-3.156 0-5.684-1.438-7.523-3.148-1.829-1.696-3.051-3.727-3.63-5.122a1.242 1.242 0 0 1 0-.96c.579-1.395 1.801-3.426 3.63-5.122Zm7.523 8.727a3.124 3.124 0 1 0 0-6.25h-.078a2.502 2.502 0 0 1-3.047 3.047V12a3.124 3.124 0 0 0 3.125 3.125Zm0-8.125a5 5 0 1 1 0 10 5 5 0 0 1 0-10Z" fill="currentcolor"></path></svg></span>${item.viewCount ?? 0}</p>
                  <p class="jk-feelog-p011"><span class="jk-feelog-span007"><svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none" xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg06">
                        <path d="m10.82 20.116-.097-.09-6.844-6.355A5.882 5.882 0 0 1 2 9.359v-.13C2 6.48 3.953 4.12 6.656 3.606A5.71 5.71 0 0 1 12 5.417a5.562 5.562 0 0 1 .977-.871 5.73 5.73 0 0 1 4.367-.945A5.73 5.73 0 0 1 22 9.23v.129c0 1.636-.68 3.199-1.879 4.312l-6.844 6.355-.097.09c-.32.297-.742.465-1.18.465a1.72 1.72 0 0 1-1.18-.465Zm.52-12.625a.205.205 0 0 1-.04-.043l-.695-.78-.003-.005A3.85 3.85 0 0 0 3.875 9.23v.13c0 1.113.465 2.18 1.281 2.937L12 18.651l6.844-6.355a4.012 4.012 0 0 0 1.281-2.937v-.13a3.851 3.851 0 0 0-6.723-2.566l-.004.004-.003.004-.696.781c-.011.016-.027.028-.039.043a.935.935 0 0 1-1.32 0v-.004Z" fill="currentcolor"></path></svg></span>${item.likeCount ?? 0}</p>
                </a>
              </div>
              <div class="FlgStack-root-need jk-feelog-div059">
                <a href="/feelog.com/@${item.channelUrl}" class="jk-feelog-a-007">
                  <div class="jk-feelog-div060">
                    <img alt="${item.memberNickname}" src="${profileImage}" loading="lazy" class="jk-feelog-img001" />
                  </div>
                </a>
                <a class="FlgBox-root-need jk-feelog-a-008" href="/feelog.com/${item.channelUrl}">
                  <p class="jk-feelog-p012">${item.memberNickname}</p>
                </a>
                <div class="FlgStack-root-need jk-feelog-div061">
                  <div class="FlgStack-root-need jk-feelog-div062">
                    <p class="jk-feelog-p013">·${item.updatedDate}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    `;

}
