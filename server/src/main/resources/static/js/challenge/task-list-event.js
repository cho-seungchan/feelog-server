//  2025.04.08 조승찬

document.addEventListener("DOMContentLoaded", () => {
    // 타이틀(오늘, 주간, 월간 첼린지) 클릭 이벤트
    document.querySelectorAll(".FeelogListItem-variantPlain").forEach((title) => {
        title.addEventListener("click", (e) => {
            e.preventDefault();
            // 타이틀이 새로 선택되면 타이틀 진하게, 밑줄 표현 되도록 수정
            if (!e.target.classList.contains("selected")) {
                // 기존에 선택되었던 타이틀 원상 복귀
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.add("flog-p-4");
                document.querySelector(".selected.flog-a-7").querySelector(".flog-p-5").classList.remove("flog-p-5");
                document.querySelector(".selected.flog-a-7").classList.add("flog-a-6");
                document.querySelector(".selected.flog-a-7").classList.remove("selected", "flog-a-7");
                // 클릭된 타이틀에 진하게, 밑줄 표현 클래스명 추가
                e.target.closest(".FeelogListItem-variantPlain").querySelector(".flog-p-4").classList.add("flog-p-5");
                e.target
                    .closest(".FeelogListItem-variantPlain")
                    .querySelector(".flog-p-4")
                    .classList.remove("flog-p-4");
                e.target.closest(".FeelogListItem-variantPlain").classList.add("selected", "flog-a-7");
                e.target.closest(".FeelogListItem-variantPlain").classList.remove("flog-a-6");
            }
        });
    });

    // 2025.04.19 조승찬 :: 도전 클릭 이벤트
    document.querySelector(".challenge-member-task").addEventListener("click", e => {

        e.preventDefault();
        const taskId = e.target.getAttribute('data-taskid');
        let id = '0';
        if (e.target.getAttribute("data-challengeid")) {
            id = e.target.getAttribute("data-challengeid");
        }
        const requestBody = { id: id, taskId: taskId };
        if (e.target.classList.contains("selected")) {
            e.target.classList.remove("selected");
            e.target.textContent = '도전';
            cancelMemberTask(requestBody);
        } else {
            e.target.classList.add("selected");
            e.target.textContent = '중단';
            selectMemberTask(requestBody);
        }
    });

    document.querySelectorAll(".challenge-common-task").forEach(task => {
        task.addEventListener("click", e => {

            e.preventDefault();
            const taskId = e.target.getAttribute('data-taskid');
            let id = '0';
            if (e.target.getAttribute("data-challengeid")) {
                id = e.target.getAttribute("data-challengeid");
            }
            const requestBody = { id: id, taskId: taskId };
            if (e.target.classList.contains("selected")) {
                e.target.classList.remove("selected");
                e.target.textContent = '도전';
                cancelCommonTask(requestBody);
            } else {
                e.target.classList.add("selected");
                e.target.textContent = '중단';
                selectCommonTask(requestBody);
            }
        });
    });
    // 2025.04.19 조승찬 :: 도전 클릭 이벤트

});
