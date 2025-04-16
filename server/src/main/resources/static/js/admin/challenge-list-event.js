const types = document.querySelectorAll(".courseTypeDiv");
const selectBox = document.querySelector(".selectCourseOpt");
const selectButton = document.querySelector(".selectCourseBtn");
const checkboxs = document.querySelectorAll(".usersRadio");

selectButton.addEventListener("click", (e) => {
    types.forEach((type) => {
        if (selectBox.value === type.textContent.trim()) {
            type.textContent = "";
        }
    });
    checkboxs.forEach((box) => {
        if (box.checked) {
            const parent = box.closest(".userListDiv");
            const target = parent.querySelector(".courseTypeDiv");

            if (target && selectBox.value) {
                target.innerText = selectBox.value;
            }
        }
    });
});
