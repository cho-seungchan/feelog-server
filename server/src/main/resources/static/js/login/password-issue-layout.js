// 2025.04.19 조승찬

// 이메일에 해당하는 회원이 없을 경우
function provideErrorInform() {
    document.querySelector(".flog-div-43").textContent = "이메일 주소가 잘못 되었습니다. 이메일 주소를 확인해 주세요."
    document.querySelector(".flog-div-40").style.display = "block";
};

// 이메일에 해당하는 회원이 있을 경우
function provideOkInform() {
    document.querySelector(".flog-div-43").textContent = "입력한 이메일 주소로 인증 메일을 보냈어요. 메일을 열어 10분내에 링크를 클릭하면 인증이 완료돼요."
    document.querySelector(".flog-div-40").style.display = "block";
};
