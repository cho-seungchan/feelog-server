const saveButton = document.querySelector(".save-button");
const checkboxs = document.querySelectorAll(".challenge-check");

saveButton.addEventListener("click", (e) => {
    if (adressInput && !addressInput.value) {
        alert("🚫 주소를 입력 해주세요");
    }
});

checkboxs.forEach((box) => {
    box.addEventListener("change", (e) => {
        if (e.target.checked) {
            checkboxs.forEach((otherbox) => {
                if (otherbox !== e.target) {
                    console.log(otherbox);
                    otherbox.checked = false;
                }
            });
        }
        if (!e.target.checked) {
            checkboxs.forEach((otherbox) => {
                if (otherbox !== e.target) {
                    otherbox.checked = true;
                }
            });
        }
    });
});
