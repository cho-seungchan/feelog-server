adminListService.getList(adminListLayout.showList);

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

        await adminListService.addAdmin({
            memberEmail : account.value,
            memberPassword : password.value,
            memberNickname : name.value
        })
        account.value = "";
        password.value = "";
        name.value = "";

        adminListService.getList(adminListLayout.buttonEvent, e.target.getAttribute("data-index"));
    }

    if(e.target.classList.contains("deleteBtn")){
        const checkboxs = document.querySelectorAll(".usersCheckbox");
        const idList = new Array();

        checkboxs.forEach((box) => {
            if (box.checked) {
                const target = box.id;
                idList.push(target);
            }
        });
        await adminListService.deleteAdmin(idList);

        await adminListService.getList(adminListLayout.buttonEvent, e.target.getAttribute("data-index"));
    }

    if(e.target.classList.contains("buttons")){
        console.log(document.querySelector(".NoticePage__NoticeListWrapper"));
        adminListService.getList(adminListLayout.buttonEvent, e.target.getAttribute("data-index"));
    }
    
    if(e.target.classList.contains("search")){
    }
})

htmlWrap.addEventListener("keydown", (e) => {
    if(e.key === "Enter"){
        const input = document.querySelector(".search_input");
        const statuses = document.querySelectorAll(".statusDiv")
        statuses.forEach((status) => {
            if(input.value !== status.textContent){

            }
        })
    }
})