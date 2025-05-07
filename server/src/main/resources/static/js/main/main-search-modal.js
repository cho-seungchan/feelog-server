document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.querySelector("#modal-search-input");
    const form = document.querySelector("#modal-search-form");

    if (!form || !searchInput) return;

    form.addEventListener("submit", function (e) {
        e.preventDefault();
        const keyword = searchInput.value.trim();
        if (keyword) {
            // 검색어 저장
            sessionStorage.setItem("searchKeyword", keyword);
            // 검색 실행
            fetchAndRenderSearch(keyword);
        }
    });
});

async function fetchAndRenderSearch(keyword) {
    try {
        const res = await fetch(`/api/search/all?keyword=${encodeURIComponent(keyword)}`);
        const data = await res.json();

        renderSlider(".mind-log-wrap", data.mindLogs, "diary");
        renderSlider(".channel-post-wrap", data.channelPosts.filter(p => p.postType === "POST"), "post");
        renderSlider(".cheering-wrap", data.channelPosts.filter(p => p.postType === "CHEERING"), "cheering");
    } catch (err) {
        console.error("검색 실패:", err);
    }
}

function renderSlider(wrapperSelector, posts, type) {
    const track = document.querySelector(`${wrapperSelector} .slick-track`);
    const slider = document.querySelector(`${wrapperSelector} .slick-slider`);
    console.log(`[${type}] 트랙`, track);
    console.log(`[${type}] 포스트 수`, posts.length);
    if (!track || !slider) return;

    // 기존 슬라이드 제거
    track.innerHTML = "";

    // 카드 생성
    posts.forEach((post, idx) => {
        console.log(`[${type}] 삽입할 포스트 #${idx}`, post);

        const postUrl = type === "diary"
            ? `/diary/diary-read?id=${post.id}`
            : `/post/read?id=${post.id}`;

        const tagHTML = (post.tags || "")
            .split(",")
            .filter(tag => tag.trim() !== "")
            .map(tag =>
            `<a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004">
                <span class="FlgChip-label-need jk-feelog-span006">${tag}</span>
             </a>`
        ).join("");

        const imageUrl = post.thumbnailUrl
            ? `/files/display?path=${post.thumbnailUrl}`
            : '/images/channel_banner.png';
        const html = `
            <div class="slick-slide" style="width: 220px;">
              <div class="FlgBox-root-need joy-121vee3">
                <a href="${postUrl}" class="joy-gq6ixk">
                  <div class="post-thumbnail FlgBox-root-need joy-1ysquz1">
                    <img src="${imageUrl}" alt="${post.title || ''}" style="object-fit: cover; width: 100%; height: 112px;" />
                  </div>
                </a>
                <a href="${postUrl}" class="joy-1f57m21">${post.title || '(제목 없음)'}</a>
                <div class="FlgBox-root-need joy-tjk6v9">
                  <a href="/profile/@${post.nickname}" class="joy-19a1vy9">${post.nickname || '익명'}</a>
                  <p class="jk-feelog-p013">${post.createdDate || ''}</p>
                </div>
                <div class="FlgStack-root-need joy-1667m44">${tagHTML}</div>
              </div>
            </div>
        `;

        track.insertAdjacentHTML("beforeend", html);
    });

    // 슬라이드 수 만큼 트랙 너비 설정
    const cardCount = posts.length;
    const slideWidth = 220 + 16; // 카드 폭 + 간격
    track.style.width = `${cardCount * slideWidth}px`;

    setupSlideButtons(wrapperSelector);
}

// 좌우 스크롤 버튼 구현
function setupSlideButtons(wrapperSelector) {
    const wrapper = document.querySelector(wrapperSelector);
    const track = wrapper.querySelector('.slick-track');
    const prevBtn = wrapper.querySelector('.prev-btn');
    const nextBtn = wrapper.querySelector('.next-btn');

    let currentIndex = 0;
    const cardWidth = 236; // 카드 폭 + 여백
    const visibleCount = Math.floor(wrapper.querySelector('.slider-container').offsetWidth / cardWidth);
    const totalCards = track.children.length;

    function updateSlidePosition() {
        const maxIndex = Math.max(0, totalCards - visibleCount);
        currentIndex = Math.max(0, Math.min(currentIndex, maxIndex));

        const offset = -currentIndex * cardWidth;
        track.style.transform = `translateX(${offset}px)`;

        // 버튼 보이기/숨기기 처리
        if (prevBtn) prevBtn.style.display = currentIndex === 0 ? "none" : "inline-block";
        if (nextBtn) nextBtn.style.display = currentIndex === maxIndex ? "none" : "inline-block";
    }

    if (prevBtn) {
        prevBtn.addEventListener("click", () => {
            currentIndex--;
            updateSlidePosition();
        });
    }

    if (nextBtn) {
        nextBtn.addEventListener("click", () => {
            currentIndex++;
            updateSlidePosition();
        });
    }

    updateSlidePosition(); // 초기 위치 적용
}

// document.addEventListener("DOMContentLoaded", () => {
//     const input = document.querySelector(".jk-feelog-input002");
//     const tagContainer = input.closest(".joy-1rrzdok");
//     const saveBtn = document.querySelector(".joy-tai27k");
//     const savedTagContainer = document.querySelector(".joy-kvb41a");
//     const modal = document.querySelector(".search-filter");
//     const searchBackdrop = document.querySelector(".searchBackdrop")
//     const maxTags = 5;
//     const tagSet = new Set();
//
//     // 태그 생성 함수
//     const createTagChip = (tag) => {
//         const chip = document.createElement("div");
//         chip.className = "FlgChip-root-need FlgChip-colorPrimary FlgChip-sizeMd-need FlgChip-variantSoft-need joy-6ieviu";
//         chip.innerHTML = `
//             <span class="FlgChip-label-need FlgChip-label-needMd jk-feelog-span006">${tag}</span>
//             <span class="FlgChip-endDecorator joy-1i201st">
//                 <button class="FlgChipDelete-root FlgChipDelete-variantSoft FlgChipDelete-colorPrimary joy-1rgf1fl" type="button">
//                     <svg focusable="false" aria-hidden="true" viewBox="0 0 24 24" width="24" height="24" fill="none"
//                         xmlns="http://www.w3.org/2000/svg" class="jk-feelog-main-svg01">
//                         <path d="M17.979 7.354a.937.937 0 0 0-1.324-1.324l-4.65 4.648-4.651-4.653A.937.937 0 0 0 6.03 7.35l4.648 4.649-4.653 4.652a.937.937 0 0 0 1.324 1.324l4.649-4.648 4.652 4.652a.937.937 0 0 0 1.324-1.324l-4.648-4.648 4.652-4.652Z" fill="currentcolor"></path>
//                     </svg>
//                 </button>
//             </span>
//         `;
//         chip.querySelector("button").addEventListener("click", () => {
//             tagSet.delete(tag);
//             chip.remove();
//         });
//         tagContainer.insertBefore(chip, input.parentElement);
//     };
//
//     // 입력 이벤트 처리
//     input.addEventListener("keyup", (e) => {
//         const value = input.value.trim();
//
//         if (e.key === " " && value !== "") {
//             if (tagSet.size >= maxTags) return input.value = "";
//             const valid = /^[ㄱ-ㅎ가-힣a-zA-Z0-9_]{1,20}$/.test(value);
//             if (!valid || tagSet.has(value)) return input.value = "";
//
//             tagSet.add(value);
//             createTagChip(value);
//             input.value = "";
//         }
//     });
//
//     // 저장 버튼 클릭 시
//     saveBtn.addEventListener("click", () => {
//         savedTagContainer.innerHTML = "";
//
//         tagSet.forEach(tag => {
//             const chip = document.createElement("div");
//             chip.className = "FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeLg FlgChip-variantSoft-need FlgChip-clickable joy-1540tkf";
//             chip.innerHTML = `
//                 <a aria-labelledby="tag-${tag}" href="/search?keyword=${encodeURIComponent(tag)}" class="FlgChip-action joy-4mjzzc"></a>
//                 <span class="FlgChip-label-need FlgChip-label-needLg joy-tq8baf" id="tag-${tag}">#${tag}</span>
//             `;
//             savedTagContainer.appendChild(chip);
//         });
//
//         // 모달 닫기 + 초기화
//         modal.classList.add("hidden");
//         searchBackdrop.classList.add("hidden");
//
//         input.value = "";
//         tagSet.clear();
//         tagContainer.querySelectorAll(".FlgChip-root-need").forEach(el => {
//             if (!el.classList.contains("FlgInput-root-need")) el.remove();
//         });
//     });
//
//     // 닫기 버튼에도 모달 닫기 적용
//     document.querySelectorAll(".search-filterBtn-close, .search-filterBtn-close2").forEach(btn => {
//         btn.addEventListener("click", () => {
//             modal.classList.add("hidden");
//             searchBackdrop.classList.add("hidden");
//
//             input.value = "";
//             tagSet.clear();
//             tagContainer.querySelectorAll(".FlgChip-root-need").forEach(el => {
//                 if (!el.classList.contains("FlgInput-root-need")) el.remove();
//             });
//         });
//     });
// });