document.addEventListener("DOMContentLoaded", () => {
    renderOfficialCards("/feelog/notice/slider", "notice-slider-track", "NOTICE");
    renderOfficialCards("/feelog/challenge/slider", "challenge-slider-track", "CHALLENGE");
});

function renderOfficialCards(apiUrl, containerId, type) {
    const container = document.querySelector(`#${containerId} .slick-track`);
    if (!container) return;

    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            if (!Array.isArray(data)) {
                console.error("응답이 배열이 아님:", data);
                return;
            }

            container.innerHTML = '';

            data.slice(0, 5).forEach(item => {
                const card = type === 'NOTICE' ? createNoticeCard(item) : createChallengeCard(item);
                container.appendChild(card);
            });

            // 공지사항인 경우에만 좌우 슬라이드 버튼 이벤트 추가
            if (type === 'NOTICE') {
                const track = container;

                document.getElementById("notice-slide-nextBtn")?.addEventListener('click', () => {
                    track.scrollBy({ left: 368, behavior: 'smooth' });
                });

                document.getElementById("notice-slide-prevBtn")?.addEventListener('click', () => {
                    track.scrollBy({ left: -368, behavior: 'smooth' });
                });
            }
        });
}

// 공지사항 카드 생성
function createNoticeCard(item) {
    const div = document.createElement("div");
    div.className = "slick-slide slick-active";
    div.style = "outline: none; width: 368px;";

    div.innerHTML = `
    <div>
      <div class="FlgBox-root-need joy-nruknm" tabindex="-1" style="width: 100%; display: inline-block;">
        <div class="FlgStack-root-need jk-feelog-div052">
          <div class="FlgStack-root-need normal-post jk-feelog-div053">
            <div class="FlgStack-root-need jk-feelog-div054">
              <a href="/notice/notice?id=${item.id}" class="jk-feelog-a-002">
                <div class="jk-feelog-div055">
                  <div class="FlgBox-root-need" data-first-child="">
                    <div class="FlgBox-root-need jk-feelog-div056" style="height: 190px">
                      <img alt="${item.title}" loading="lazy" decoding="async"
                        style="position: absolute; height: 100%; width: 100%; object-fit: cover;"
                         src="${(item.filePath && item.fileName && item.filePath.trim() !== '' && item.fileName.trim() !== '') ?
        '/files/display?path=' + item.filePath + '/' + item.fileName :
        '/images/channel_notice.png'}" />
                    </div>
                  </div>
                </div>
              </a>
              <a class="FlgStack-root-need jk-feelog-a-003" href="/notice/notice?id=${item.id}">
                <div class="FlgStack-root-need jk-feelog-div057">
                  <h3 class="jk-feelog-h3-001">${item.title}</h3>
                  <p class="jk-feelog-p010">${item.noticeContent || ''}</p>
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
                <a class="jk-feelog-a-007"><div class="jk-feelog-div060">
                  <img alt="Feelog" src="/images/avatar_blank.png" class="jk-feelog-img001" />
                </div></a>
                <a class="FlgBox-root-need jk-feelog-a-008">
                  <p class="jk-feelog-p012">${item.memberNickname || 'Feelog 공식'}</p>
                </a>
                <div class="jk-feelog-div061">
                  <div class="jk-feelog-div062"><p class="jk-feelog-p013">·${new Date(item.createdDate).toLocaleDateString('ko-KR')}</p></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `;

    return div;
}

// 챌린지 카드 생성
function createChallengeCard(item) {
    console.log("챌린지 taskUrl:", item.taskUrl);
    const div = document.createElement("div");
    div.className = "slick-slide slick-active";
    div.style = "outline: none; width: 368px;";

    div.innerHTML = `
    <div>
      <div class="FlgBox-root-need joy-nruknm" tabindex="-1" style="width: 100%; display: inline-block;">
        <div class="FlgStack-root-need jk-feelog-div052">
          <div class="FlgStack-root-need normal-post jk-feelog-div053">
            <div class="FlgStack-root-need jk-feelog-div054">
              <a href="${item.taskUrl}" target="_blank" class="jk-feelog-a-002">
                <div class="jk-feelog-div055">
                  <div class="FlgBox-root-need" data-first-child="">
                    <div class="FlgBox-root-need jk-feelog-div056" style="height: 190px" >
                      <img alt="${item.title}" loading="lazy" decoding="async"
                        style="position: absolute; height: 100%; width: 100%; object-fit: cover;"
                        src="${(item.filePath && item.fileName && item.filePath.trim() !== '' && item.fileName.trim() !== '') ?
        '/files/display?path=' + item.filePath + '/' + item.fileName :
        '/images/channel_notice.png'}" />
                    </div>
                  </div>
                </div>
              </a>
              <a class="FlgStack-root-need jk-feelog-a-003" href="${item.taskUrl}" target="_blank">
                <div class="FlgStack-root-need jk-feelog-div057">
                  <h3 class="jk-feelog-h3-001">${item.title}</h3>
                </div>
              </a>
            </div>
            <div class="FlgStack-root-need jk-feelog-div031">
              <div class="FlgStack-root-need jk-feelog-div058">
                <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004">
                  <span class="FlgChip-label-need jk-feelog-span006">챌린지</span>
                </a>
              </div>
              <div class="FlgStack-root-need jk-feelog-div059">
                <a class="jk-feelog-a-007"><div class="jk-feelog-div060">
                  <img alt="Feelog" src="/images/avatar_blank.png" class="jk-feelog-img001" />
                </div></a>
                <a class="FlgBox-root-need jk-feelog-a-008">
                  <p class="jk-feelog-p012">Feelog 공식</p>
                </a>
                <div class="jk-feelog-div061">
                  <div class="jk-feelog-div062"><p class="jk-feelog-p013">·${new Date(item.createdDate).toLocaleDateString('ko-KR')}</p></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `;

    return div;
}
