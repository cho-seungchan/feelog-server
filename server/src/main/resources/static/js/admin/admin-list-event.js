const insertButton = document.querySelector(".manageBtn");
const insertInputs = document.querySelector(".inputRow");
const saveButton = document.querySelector(".saveBtn1");
const cancleButton = document.querySelector(".cancelBtn");
const account = document.querySelector(".inputAccount");
const password = document.querySelector(".inputPassword");
const name = document.querySelector(".inputName");
const phone = document.querySelector(".inputPhone");
const email = document.querySelector(".inputEmail");
const firstDiv = document.querySelector(".mainUserListDiv");
const deleteButton = document.querySelector(".deleteBtn");
const checkboxs = document.querySelectorAll(".usersCheckbox");

insertButton.addEventListener("click", (e) => {
    insertInputs.classList.remove("hidden");
});

cancleButton.addEventListener("click", (e) => {
    insertInputs.classList.add("hidden");
});

saveButton.addEventListener("click", (e) => {
    const inputs = insertInputs.querySelectorAll("input");
    let infos = new Array();

    inputs.forEach((input, i) => {
        infos[i] = input.value;
    });

    const parent = firstDiv.parentNode;
    const newLi = document.createElement("li");

    newLi.innerHTML = `
                <div class="userListDiv">
                    <label class="">
                        <input type="checkbox" class="usersCheckbox">
                    </label>
                    <div class="idDiv">
                        23
                    </div>
                    <div class="accountDiv">
                        ${infos[0]}
                    </div>
                    <div class="nameDiv">
                        ${infos[2]}
                    </div>
                    <div class="phoneDiv">
                    ${infos[3]}
                    </div>
                    <div class="emailDiv">
                    ${infos[4]}
                    </div>
                </div>
    `;
    parent.insertBefore(newLi, firstDiv.nextSibling);
});

deleteButton.addEventListener("click", (e) => {
    checkboxs.forEach((box) => {
        if (box.checked) {
            box.closest("li").remove();
        }
    });
});
