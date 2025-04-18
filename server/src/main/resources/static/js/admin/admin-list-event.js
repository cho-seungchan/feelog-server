adminListService.getList(adminListLayout.showList);

const password = document.querySelector(".inputPassword");
const name = document.querySelector(".inputName");
const email = document.querySelector(".inputEmail");
const htmlWrap = document.querySelector(".AppLayout_contents")

htmlWrap.addEventListener("click",async (e)=>{
    if(e.target.classList.contains("manageBtn")){
        document.querySelector(".inputRow").classList.remove("hidden");
    }
    if(e.target.classList.contains("cancelBtn")){
        document.querySelector(".inputRow").classList.add("hidden");
    }

    if(e.target.classList.contains("saveBtn1")){
        const account = document.querySelector(".inputAccount");
        const password = document.querySelector(".inputPassword");
        const name = document.querySelector(".inputName");
        console.log(account.value)
        console.log(password.value)
        console.log(name.value)

        await adminListService.addAdmin({
            memberEmail : account.value,
            memberPassword : password.value,
            memberNickname : name.value
        })
        account.value = "";
        password.value = "";
        name.value = "";
    }

    if(e.target.classList.contains("deleteBtn")){
        const checkboxs = document.querySelectorAll(".usersCheckbox");
            checkboxs.forEach((box) => {
            if (box.checked) {
                box.closest("li").remove();
            }
        });
    }

    if(e.target.classList.contains("buttons")){
        console.log(document.querySelector(".NoticePage__NoticeListWrapper"));
        adminListService.getList(adminListLayout.buttonEvent, e.target.getAttribute("data-index"));
    }
})
