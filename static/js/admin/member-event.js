const pauseButton = document.querySelector(".pauseBtn");
const restartButton = document.querySelector(".restartBtn");
const deleteButton = document.querySelector(".expelBtn");
const checkBoxs = document.querySelectorAll(".usersCheckbox");

pauseButton.addEventListener("click", (e) => {
    checkBoxs.forEach((box) => {
        if (box.checked) {
            let text = " N ";
            const parent = box.parentElement;
            const siblings = Array.from(parent.parentElement.children);
            const target = siblings.find((sibling) =>
                sibling.classList.contains("actDiv")
            );
            target.innerHTML = text;
        }
    });
});

restartButton.addEventListener("click", (e) => {
    checkBoxs.forEach((box) => {
        if (box.checked) {
            let text = " Y ";
            const parent = box.parentElement;
            const siblings = Array.from(parent.parentElement.children);
            const target = siblings.find((sibling) =>
                sibling.classList.contains("actDiv")
            );
            target.innerHTML = text;
        }
    });
});

deleteButton.addEventListener("click", (e) => {
    checkBoxs.forEach((box) => {
        if (box.checked) {
            const target = box.closest("li");
            target.remove();
        }
    });
});
