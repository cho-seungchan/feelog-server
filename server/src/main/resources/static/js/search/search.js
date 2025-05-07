// document.addEventListener("DOMContentLoaded", () => {
//     const menu = document.getElementById("report-menu");
//     const modal = document.querySelector(".report-detail");
//     const reportBtn = document.getElementById("report-btn");
//
//     let radioHandlersRegistered = false;
//
//     // 메뉴 열기
//     document.querySelectorAll(".more-btn").forEach((button) => {
//         button.addEventListener("click", (e) => {
//             e.stopPropagation();
//             const isVisible = menu.style.display === "block";
//             menu.style.display = "none";
//
//             if (!isVisible) {
//                 const rect = button.getBoundingClientRect();
//                 const scrollTop =
//                     window.scrollY || document.documentElement.scrollTop;
//                 const scrollLeft =
//                     window.scrollX || document.documentElement.scrollLeft;
//
//                 menu.style.display = "block";
//                 menu.style.position = "absolute";
//                 menu.style.removeProperty("transform");
//                 menu.style.top = `${rect.bottom + scrollTop}px`;
//                 menu.style.left = `${rect.left + scrollLeft}px`;
//                 menu.style.zIndex = "1000";
//                 menu.style.backgroundColor = "#FFFFFF";
//             }
//         });
//     });
//
//     // 외부 클릭 시 메뉴 닫기
//     document.addEventListener("click", () => {
//         menu.style.display = "none";
//     });
//
//     // 신고 모달 열기
//     reportBtn.addEventListener("click", (e) => {
//         e.stopPropagation();
//         modal.style.display = "block";
//         menu.style.display = "none";
//
//         // 클릭 안되는 이슈 방지: pointer-events 복구
//         modal.querySelectorAll(".FlgRadio-action").forEach((el) => {
//             el.style.pointerEvents = "auto";
//             el.style.cursor = "pointer";
//             el.style.zIndex = "10";
//         });
//
//         modal.querySelectorAll('input[name="reason"]').forEach((input) => {
//             input.style.pointerEvents = "auto";
//             input.style.opacity = "0";
//             input.style.position = "relative";
//             input.style.zIndex = "10";
//         });
//
//         if (!radioHandlersRegistered) {
//             registerRadioHandlers();
//             radioHandlersRegistered = true;
//         }
//     });
//
//     // 모달 닫기
//     const closeBtn = modal.querySelector(".FlgModalClose-root-need");
//     if (closeBtn) {
//         closeBtn.addEventListener("click", () => {
//             modal.style.display = "none";
//         });
//     }
//
//     const cancelBtn = document.querySelector(".joy-1bxt4bb");
//     if (cancelBtn) {
//         cancelBtn.addEventListener("click", () => {
//             if (modal) modal.style.display = "none";
//         });
//     }
//
//     modal.addEventListener("click", (e) => {
//         if (e.target === modal) {
//             modal.style.display = "none";
//         }
//     });
// });
//
// function registerRadioHandlers() {
//     // 실제 클릭 감지
//     document.querySelectorAll(".FlgRadio-action").forEach((action) => {
//         action.addEventListener("click", () => {
//             const input = action.querySelector('input[type="radio"]');
//             if (input) {
//                 input.checked = true;
//
//                 const changeEvent = new Event("change", { bubbles: true });
//                 input.dispatchEvent(changeEvent);
//             }
//         });
//     });
//
//     // 상태에 따라 클래스 동기화
//     document.querySelectorAll('input[name="reason"]').forEach((radio) => {
//         radio.addEventListener("change", () => {
//             document
//                 .querySelectorAll(
//                     ".FlgRadio-root, .FlgRadio-radio, .FlgRadio-action"
//                 )
//                 .forEach((el) => el.classList.remove("Flg-checked"));
//             document.querySelectorAll(".FlgRadio-radio").forEach((el) => {
//                 el.classList.remove("joy-11c58eo");
//                 el.classList.add("joy-8ktuf5");
//             });
//
//             const action = radio.parentElement;
//             const radioWrapper = action.parentElement;
//             const root = radioWrapper.parentElement;
//
//             if (action && radioWrapper && root) {
//                 action.classList.add("Flg-checked");
//                 radioWrapper.classList.add("Flg-checked");
//                 root.classList.add("Flg-checked");
//
//                 radioWrapper.classList.remove("joy-8ktuf5");
//                 radioWrapper.classList.add("joy-11c58eo");
//             }
//         });
//     });
// }

function attachDiaryCardClickHandler(card) {
    card.addEventListener("click", async (e) => {
        console.log("이벤트 바인딩 완료:", card);
        console.log("클릭 감지됨!");

        e.preventDefault();

        const diaryId = card.dataset.diaryId;
        const tags = Array.from(card.querySelectorAll(".tag")).map(tag => tag.textContent.trim().replace("#", ""));
        const keyword = sessionStorage.getItem("searchKeyword");

        const redirectSpan = card.querySelector("[data-href]");
        const redirectUrl = redirectSpan?.dataset.href || `/diary/diary-read?id=${diaryId}`;

        console.log("sessionStorage.getItem('searchKeyword') =", keyword);
        console.log("바인딩된 diary-card ID:", diaryId, "→ 이동할 URL:", redirectUrl);

        if (keyword && tags.length > 0) {
            const limitedTags = tags.slice(0, 2);
            try {
                await Promise.all(limitedTags.map(tag =>
                    fetch("/search/related-search/save-by-content", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ keyword, tag })
                    }).then(res => res.text()).then(result => {
                        console.log(`저장 결과 [${tag}]:`, result);
                    })
                ));
            } catch (err) {
                console.error("관련 키워드 저장 실패:", err);
            }
        }

        setTimeout(() => {
            window.location.href = redirectUrl;
        }, 100);
    });
}

function attachPostCardClickHandler(card) {
    card.addEventListener("click", async (e) => {
        console.log("[포스트] 카드 클릭 감지!");
        e.preventDefault();

        const postId = card.dataset.postId;
        const tags = Array.from(card.querySelectorAll(".tag")).map(tag => tag.textContent.trim().replace("#", ""));
        const keyword = sessionStorage.getItem("searchKeyword");

        const redirectSpan = card.querySelector("[data-href]");
        const redirectUrl = redirectSpan?.dataset.href || `/post/read?id=${postId}`;

        console.log("keyword =", keyword, "redirectUrl =", redirectUrl);

        if (keyword && tags.length > 0) {
            const limitedTags = tags.slice(0, 2);
            try {
                await Promise.all(limitedTags.map(tag =>
                    fetch("/search/related-search/save-by-content", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ keyword, tag })
                    }).then(res => res.text()).then(result => {
                        console.log(`저장 결과 [${tag}]:`, result);
                    })
                ));
            } catch (err) {
                console.error("포스트 관련 키워드 저장 실패:", err);
            }
        }

        setTimeout(() => {
            window.location.href = redirectUrl;
        }, 100);
    });
}

function attachCheerCardClickHandler(card) {
    card.addEventListener("click", async (e) => {
        console.log("[응원글] 카드 클릭 감지!");
        e.preventDefault();

        const cheerId = card.dataset.cheerId;
        const tags = Array.from(card.querySelectorAll(".tag")).map(tag => tag.textContent.trim().replace("#", ""));
        const keyword = sessionStorage.getItem("searchKeyword");

        const redirectSpan = card.querySelector("[data-href]");
        const redirectUrl = redirectSpan?.dataset.href || `/cheer/${cheerId}`;

        console.log("keyword =", keyword, "redirectUrl =", redirectUrl);

        if (keyword && tags.length > 0) {
            const limitedTags = tags.slice(0, 2);
            try {
                await Promise.all(limitedTags.map(tag =>
                    fetch("/search/related-search/save-by-content", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ keyword, tag })
                    }).then(res => res.text()).then(result => {
                        console.log(`저장 결과 [${tag}]:`, result);
                    })
                ));
            } catch (err) {
                console.error("응원글 관련 키워드 저장 실패:", err);
            }
        }

        setTimeout(() => {
            window.location.href = redirectUrl;
        }, 100);
    });
}


document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".diary-card").forEach(card => attachDiaryCardClickHandler(card));
    document.querySelectorAll(".post-card").forEach(card => attachPostCardClickHandler(card));
    document.querySelectorAll(".cheer-card").forEach(card => attachCheerCardClickHandler(card));

    const searchInput = document.querySelector("input[name='keyword']");
    const searchForm = document.querySelector("form[action='/search/search']");


    if (!searchInput || !searchForm) return;

    searchForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const keyword = searchInput.value.trim();

        if (keyword.length === 0) {
            sessionStorage.removeItem("searchKeyword");
            alert("검색어를 입력해주세요.");
            return;
        }

        document.querySelectorAll(".diary-card").forEach(attachDiaryCardClickHandler);

        sessionStorage.setItem("searchKeyword", keyword);
        console.log("sessionStorage 저장 완료: searchKeyword =", sessionStorage.getItem("searchKeyword"));

        // sessionStorage 저장 이후 다음 프레임에 이동
        requestAnimationFrame(() => {
            window.location.href = `/search/search?keyword=${encodeURIComponent(keyword)}`;
        });
    });
});



document.querySelectorAll(".load-more-btn-diary").forEach(button => {
    button.addEventListener("click", () => {
        console.log("Load More 버튼 클릭됨");
        const type = button.dataset.type;
        const offset = parseInt(button.dataset.offset);
        const keyword = button.dataset.keyword || "";
        const url = `/search/search/${type}/load?keyword=${encodeURIComponent(keyword)}&offset=${offset}`;

        fetch(url)
            .then(res => res.json())
            .then(data => {
                const container = document.querySelector(`.container-${type}`);

                // 만약 받아온 데이터가 없으면 버튼 숨김
                if (data.length === 0) {
                    button.style.display = "none";
                    return;
                }

                data.forEach(diary => {
                    const el = document.createElement("div");
                    el.className = "FlgBox-root-need joy-1hw15it ";
                    el.innerHTML = `
                    
        <div class="FlgStack-root-need joy-w5a8kp diary-card">
            <div class="FlgStack-root-need joy-16yrq9k ">
                <a class="FlgStack-root-need joy-1kkt86i" href="/diary/diary-read?id=${diary.id}">
                    <h3 class="joy-qs45i0">${diary.title}</h3>
                    <p class="main-post-subtitle jk-feelog-p010">
                        <span class="joy-i9tg8j">${diary.content}</span>
                    </p>
                </a>
                <div class="FlgStack-root-need joy-1q1zl3i">
                    ${(diary.tagsList || []).map(tag => `
                        <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need jk-feelog-a-004"
                           href="/search/search?keyword=${encodeURIComponent(tag)}">
                            <span class="tag FlgChip-label-need jk-feelog-span006" data-tag-content="${tag}">#${tag}</span>
                        </a>
                    `).join('')}
                </div>
            </div>
            <a class="FlgBox-root-need joy-rtjsnx" href="/diary/diary-read?id=${diary.id}">
                <img src="${(diary.thumbnailUrl
                        && diary.thumbnailUrl.trim() !== ""
                        && diary.thumbnailUrl !== "/"
                        && diary.thumbnailUrl !== "null"
                        && diary.thumbnailUrl !== null)
                        ? `/files/display?path=${diary.thumbnailUrl.trim()}`
                        : '/images/channel_banner.png'}" 
                style="position: absolute; height: 100%; width: 100%; object-fit: cover;" alt="썸네일" />
            </a>
        </div>
        <div class="FlgStack-root-need joy-k2m8sx" data-google-interstitial="false">
            <div class="FlgStack-root-need joy-1uop93y">
                <a href="/profile/${diary.memberId}" class="joy-12vg8yv">
                    <div class="main-post-avatar joy-auxif9">
                        <img src="${diary.memberProfileImg ? `/files/display?path=${diary.memberProfileImg}` : '/images/avatar_blank.png'}" alt="프로필 이미지" class="jk-feelog-img001" />
                    </div>
                </a>
                <div class="FlgStack-root-need joy-1n1ctis">
                    <p class="joy-ux2rns">
                        <a class="FlgTypography-inherit joy-1w8agmd" href="/profile/${diary.memberId}">${diary.nickname}</a>
                    </p>
                    <div class="FlgStack-root-need main-post-metadata joy-14ly3d9">
                        <p class="jk-feelog-p013">${diary.createdDate}</p>
                    </div>
                </div>
            </div>
        </div>
        <hr class="jk-feelog-hr01" />
    `;
                    el.dataset.diaryId = diary.id;


                    container.appendChild(el);

                    attachDiaryCardClickHandler(el);


                });
                button.dataset.offset = offset + 5;
            });
    });
});


document.querySelectorAll(".load-more-btn-post").forEach(button => {
    button.addEventListener("click", () => {
        const type = button.dataset.type; // 'post' 또는 'cheer'
        const offset = parseInt(button.dataset.offset);
        const keyword = button.dataset.keyword;
        const url = `/search/search/${type}/load?keyword=${encodeURIComponent(keyword)}&offset=${offset}`;

        fetch(url)
            .then(res => res.json())
            .then(data => {
                const container = document.querySelector(`.container-${type}`);
                if (!container) return;

                if (data.length === 0) {
                    button.style.display = "none";
                    return;
                }

                data.forEach(post => {
                    const el = document.createElement("div");
                    el.className = "FlgStack-root-need joy-12smpxv post-card";
                    el.dataset.postId = post.id;

                    el.innerHTML = `
        <span class="FlgBox-root-need joy-1lrlkwu" role="link" style="cursor: pointer;" data-href="/post/read?id=${post.id}">
            <div class="FlgBox-root-need jk-feelog-div056">
                <img src="${(post.thumbnailUrl
                        && post.thumbnailUrl.trim() !== ""
                        && post.thumbnailUrl !== "/"
                        && post.thumbnailUrl !== "null"
                        && post.thumbnailUrl !== null)
                        ? `/files/display?path=${post.thumbnailUrl.trim()}`
                        : '/images/channel_banner.png'}"
                alt="대표 이미지"
                     loading="lazy" decoding="async"
                     style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;" />
            </div>
        </span>
        <div class="FlgStack-root-need joy-pvvyl1">
            <span role="link" style="cursor: pointer;" data-href="/post/read?id=${post.id}">
                <h3 class="joy-j4zrps">${post.title}</h3>
            </span>
            <span role="link" style="cursor: pointer;" data-href="/post/read?id=${post.id}">
                <p class="joy-if6bzd">${post.content}</p>
            </span>
            <p class="joy-mf6g6b">
                <a class="FlgTypography-inherit joy-1onqwxe" href="/profile/${post.memberId}">
                    ${post.nickname}
                </a>
            </p>
            <div class="FlgStack-root-need joy-cs0i1i">
                ${(post.tagsList || []).map(tag => `
                    <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need joy-1e5bhkj"
                       href="/search/search?keyword=${encodeURIComponent(tag)}">
                        <span class="tag FlgChip-label-need jk-feelog-span006" data-tag-content="${tag}">#${tag}</span>
                    </a>
                `).join("")}
            </div>
        </div>
    `;

                    container.appendChild(el);

                    attachPostCardClickHandler(el);
                });

                button.dataset.offset = offset + 5;
            });
    });
});

document.querySelectorAll(".load-more-btn-cheer").forEach(button => {
    button.addEventListener("click", () => {
        const type = button.dataset.type; // cheer
        const offset = parseInt(button.dataset.offset);
        const keyword = button.dataset.keyword;
        const url = `/search/search/${type}/load?keyword=${encodeURIComponent(keyword)}&offset=${offset}`;

        fetch(url)
            .then(res => res.json())
            .then(data => {
                const container = document.querySelector(`.container-${type}`);
                if (!container) return;

                if (data.length === 0) {
                    button.style.display = "none";
                    return;
                }

                data.forEach(cheer => {
                    const el = document.createElement("div");
                    el.className = "FlgStack-root-need joy-12smpxv cheer-card";
                    el.dataset.cheerId = cheer.id;

                    el.innerHTML = `
        <span class="FlgBox-root-need joy-1lrlkwu"
              role="link" style="cursor: pointer;"
              data-href="/cheer/${cheer.id}">
            <div class="FlgBox-root-need jk-feelog-div056">
                <img src="${(cheer.thumbnailUrl
                        && cheer.thumbnailUrl.trim() !== ""
                        && cheer.thumbnailUrl !== "/"
                        && cheer.thumbnailUrl !== "null"
                        && cheer.thumbnailUrl !== null)
                        ? `/files/display?path=${cheer.thumbnailUrl.trim()}`
                        : '/images/channel_banner.png'}"
                 alt="대표 이미지"
                     loading="lazy" decoding="async"
                     style="position: absolute; height: 100%; width: 100%; inset: 0px; object-fit: cover; color: transparent;" />
            </div>
        </span>
        <div class="FlgStack-root-need joy-pvvyl1">
            <span role="link" style="cursor: pointer;" data-href="/cheer/${cheer.id}">
                <h3 class="joy-j4zrps">${cheer.title}</h3>
            </span>
            <span role="link" style="cursor: pointer;" data-href="/cheer/${cheer.id}">
                <p class="joy-if6bzd">${cheer.content}</p>
            </span>
            <p class="joy-mf6g6b">
                <a class="FlgTypography-inherit joy-1onqwxe" href="/profile/${cheer.memberId}">
                    ${cheer.nickname}
                </a>
            </p>
            <div class="FlgStack-root-need joy-cs0i1i">
                ${(cheer.tagsList || []).map(tag => `
                    <a class="FlgChip-root-need FlgChip-colorNeutral-need FlgChip-sizeSm-need FlgChip-variantSoft-need joy-1e5bhkj"
                       href="/search/search?keyword=${encodeURIComponent(tag)}">
                        <span class="tag FlgChip-label-need jk-feelog-span006" data-tag-content="${tag}">#${tag}</span>
                    </a>
                `).join("")}
            </div>
        </div>
    `;

                    container.appendChild(el);

                    attachCheerCardClickHandler(el);
                });

                button.dataset.offset = offset + 5;
            });
    });
});

document.querySelectorAll(".load-more-btn-channel").forEach(button => {
    button.addEventListener("click", () => {
        const offset = parseInt(button.dataset.offset);
        const keyword = button.dataset.keyword;
        const url = `/search/search/channel/load?keyword=${encodeURIComponent(keyword)}&offset=${offset}`;

        fetch(url)
            .then(res => res.json())
            .then(data => {
                const container = document.querySelector(".container-channel");
                if (!container) return;

                if (data.length === 0) {
                    button.style.display = "none";
                    return;
                }

                data.forEach(channel => {
                    const el = document.createElement("div");
                    el.className = "FlgBox-root-need joy-c1ffoy";
                    el.innerHTML = `
    <a href="/feelog.com/@${channel.channelUrl}" style="height: fit-content">
        <div class="joy-1mszhh9">
            <div class="jk-feelog-div055">
                <div class="FlgBox-root-need jk-feelog-div056">
                    <img src="${channel.thumbnailUrl && channel.thumbnailUrl.trim() !== '' ? `/files/display?path=${channel.thumbnailUrl}` : '/images/channel_banner.png'}" alt="채널 대표 이미지"
                         style="position:absolute;height:100%;width:100%;inset:0;object-fit:cover;" />
                </div>
            </div>
        </div>
        <div class="joy-18i1ml1">
            <div class="jk-feelog-div055">
                <div class="FlgBox-root-need jk-feelog-div056">
                    <img src="${channel.thumbnailUrl && channel.thumbnailUrl.trim() !== '' ? `/files/display?path=${channel.thumbnailUrl}` : '/images/channel_banner.png'}" alt="프로필 미리보기"
                         style="position:absolute;height:100%;width:100%;inset:0;object-fit:cover;" />
                </div>
            </div>
        </div>
    </a>
    <div class="FlgStack-root-need joy-1x7p52c">
        <div class="FlgStack-root-need joy-187jtt">
            <div class="FlgStack-root-need joy-1n1ctis">
                <a href="/channel/${channel.id}">
                    <h3 class="joy-1wgv3md">${channel.title}</h3>
                </a>
                <p class="joy-1xn91ef">
                    <a class="FlgTypography-inherit joy-1l8panl">${channel.nickname}</a> ·
                    <a class="FlgTypography-inherit joy-4xpt5n">${channel.subscriberCount}명</a>
                </p>
            </div>
            <div class="FlgStack-root-need joy-19sxttz">
                <button class="FlgButton-sizeSm-need joy-ubyl7c subscribe-btn" type="button"
                        data-channel-id="${channel.id}">
                    ${channel.subscribed ? "구독 취소" : "구독"}
                </button>
            </div>
        </div>
        <p class="joy-1dtob7v">${channel.description}</p>
    </div>
    <hr class="jk-feelog-hr01" />
`;
                    container.appendChild(el);
                });

                button.dataset.offset = offset + 5;
            });
    });
});



document.querySelectorAll('.search-tab a').forEach(link => {
    link.addEventListener('click', (e) => {
        // e.preventDefault();

        // 1. 모든 링크 초기화
        document.querySelectorAll('.search-tab a').forEach(a => {
            a.classList.remove('default', 'joy-yhpm4r');
            a.classList.add('joy-1t2bmje');

            const p = a.querySelector('p');
            if (p) {
                p.classList.remove('joy-7rqbdn');
                p.classList.add('joy-r8ur5l');
            }
        });

        // 2. 현재 선택된 링크에 클래스 적용
        const selected = e.currentTarget;
        selected.classList.remove('joy-1t2bmje');
        selected.classList.add('default', 'joy-yhpm4r');

        const selectedP = selected.querySelector('p');
        if (selectedP) {
            selectedP.classList.remove('joy-r8ur5l');
            selectedP.classList.add('joy-7rqbdn');
        }
    });
});
