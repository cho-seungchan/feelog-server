// 2025.04.20 조승찬

// 2025.04.20 조승찬 :: 개별 과제에 도전하고 결과를 받았을 때 다음 수정을 위해 chellangeId 저장
function setMemberChallengeId(challengeId) {
    console.log("layout "+challengeId);
    // 모든 버튼 요소를 가져옵니다.
    const button = document.querySelector('.challenge-member-task');
    const currentTaskId = button.getAttribute('data-taskid');

    // data-challengeid 값을 challengeId로 수정
    button.setAttribute('data-challengeid', challengeId);
}

// 2025.04.20 조승찬 :: 공통 과제에 도전하고 결과를 받았을 때 다음 수정을 위해 chellangeId 저장
function setCommonChallengeId(challengeId, taskId) {
    // 모든 버튼 요소를 가져옵니다.
    const buttons = document.querySelectorAll('.challenge-common-task');

    buttons.forEach(button => {
        // 버튼의 data-taskid 값 가져오기
        const currentTaskId = button.getAttribute('data-taskid');

        // taskId와 currentTaskId가 같을 경우 속성 수정 및 클래스 제거
        if (currentTaskId === taskId) {
            // data-challengeid 값을 challengeId로 수정
            button.setAttribute('data-challengeid', challengeId);
        }
    });
}