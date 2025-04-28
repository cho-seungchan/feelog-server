document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const keyword = urlParams.get('query');

    let apiUrl = "/main/faq/list";
    if (keyword) {
        apiUrl += `?query=${encodeURIComponent(keyword)}`;
    }

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const faqList = document.querySelector(".component-list-items");
            faqList.innerHTML = "";

            if (data.length === 0 && keyword) {
                // 검색 결과가 없을 때
                const noResult = document.createElement("p");
                noResult.textContent = "검색 결과가 없습니다. 전체 목록을 불러옵니다.";
                noResult.style.margin = "20px";
                faqList.appendChild(noResult);

                // 전체 목록 다시 요청
                fetch("/main/faq/list")
                    .then(response => response.json())
                    .then(fullData => {
                        renderFaqList(fullData);
                    });
            } else {
                // 검색 결과가 있을 때
                renderFaqList(data);
            }
        })
        .catch(error => console.error("FAQ 목록 불러오기 실패:", error));

    // FAQ 리스트를 그리는 함수 분리
    function renderFaqList(data) {
        const faqList = document.querySelector(".component-list-items");

        data.forEach(faq => {
            const li = document.createElement("li");
            li.setAttribute("align", "left");

            const a = document.createElement("a");
            a.href = `/main/faq/${faq.id}`;

            const div = document.createElement("div");
            div.className = "icon-text-box";

            const p = document.createElement("p");
            p.textContent = faq.faqTitle;

            const i = document.createElement("i");
            i.className = "icon-arrow-14";

            const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
            svg.setAttribute("viewBox", "0 0 320 512");

            const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
            path.setAttribute("d", "M305 239c9.4 9.4 9.4 24.6 0 33.9L113 465c-9.4 9.4-24.6 9.4-33.9 0s-9.4-24.6 0-33.9l175-175L79 81c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0L305 239z");

            svg.appendChild(path);
            i.appendChild(svg);

            div.appendChild(p);
            a.appendChild(div);
            a.appendChild(i);
            li.appendChild(a);
            faqList.appendChild(li);
        });
    }
});
