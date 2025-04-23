const memberListButtons = document.querySelectorAll(".member-list");

// 화면생성
memberListButtons.forEach((memberListButton)=>{
    memberListButton.addEventListener("click", (e) => {
        memberService.getList(memberLayout.showList,1)
    });
});

htmlWrap.addEventListener("click", async (e) => {
    if(e.target.classList.contains("member-buttons")){
        memberService.getList(memberLayout.showList,e.target.getAttribute("data-index"))
    }

    if(e.target.classList.contains("banned-member")){
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if(!userResponse){
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box)=>{
            if(box.checked){
                idList.push(box.id)
            }
        })

        await memberService.bannedMember(idList)

        await memberService.getList(memberLayout.showList, 1);
    }

    if(e.target.classList.contains("active-member")){
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if(!userResponse){
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box)=>{
            if(box.checked){
                idList.push(box.id)
            }
        })

        await memberService.activeMember(idList)

        await memberService.getList(memberLayout.showList, 1);
    }

    if(e.target.classList.contains("delete-member")){
        const userResponse = confirm("이 작업을 진행하시겠습니까?");
        if(!userResponse){
            return;
        }

        const checkBoxes = document.querySelectorAll(".usersCheckbox")
        let idList = new Array();

        checkBoxes.forEach((box)=>{
            if(box.checked){
                idList.push(box.id)
            }
        })

        await memberService.deleteMember(idList)

        await memberService.getList(memberLayout.showList, 1);
    }
})

