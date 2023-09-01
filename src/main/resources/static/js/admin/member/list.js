$(document).ready(function () {

    const form = new FormData();


    /*맴버 목록*/
    const $memberListWrap = $('.member-list-wrap');


    const $modifyStatusBtn = $('.modify-status');
    const $modifyTypeBtn = $('.modify-type');
    const $modifyAdminBtn = $('.modify-admin');

    let text=``;


    let memberIds = []



    let customService = (function () {

        function getList(callback, page){
            $.ajax({
                url: `/api/admins/member?page=`+page,
                type: `get`,
                async: false,
                success: function(result){
                    console.log(result);
                    if(callback){
                        callback(result);
                    }
                }
            })
        }

        function modifyStatus(){
            $.ajax({
                url: `/api/admins/member/status`,
                type: `put`,
                async: false,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                data: form,
                success: function(){
                    alert("성공");
                }
            })
        }

        function modifyType(){
            $.ajax({
                url: `/api/admins/member/type`,
                type: `put`,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                async: false,
                data: form,
                success: function(){
                    alert("성공");
                }
            })
        }

        function modifyAdmin(){
            $.ajax({
                url: `/api/admins/member/admin`,
                type: `put`,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                async: false,
                data: form,
                success: function(){
                    alert("성공");
                }
            })
        }

        return {getList : getList, modifyType: modifyType, modifyStatus : modifyStatus, modifyAdmin : modifyAdmin}
    })()

    customService.getList(showList, page)

    function showList(result){
        $pagingWrap.html('');
        text='';
        pagingText='';
        $memberListWrap.html('');
        result.content.forEach(member =>{
            text +=
                    `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="member-checkbox"
                                    name="check"
                                    value="${member.id}"/>
                        </td>
                        <td>${member.id}</td>
                     `
                if(!member.deleted){
                  text +=  `<td style="color:#0fa34c">정상 회원</td>`
                }else{
                  text +=  `<td style="color:#ff0000">탈퇴 회원</td>`
                }
                text += `
                        <td>${member.memberRole.name}</td>
                        <td>${member.memberEmail}</td>
                        <td>${member.memberName}</td>
                        <td>${member.memberPhone}</td>
                        <td>${member.createdDate}</td>
                        `
                if(!member.deleted){
                  text +=  ` <td>탈퇴 회원 아님</td>`
                }else{
                  text +=  ` <td>${member.updatedDate}</td>`
                }
                  text+= `
                    </tr>
                    `
        })
        $memberListWrap.html(text)

        pagaing(result.pageable.pageSize,result.totalElements,result.pageable.pageNumber);

        $('#allSelect').prop("checked", false);
    }


    $modifyStatusBtn.on("click", function () {
        form.delete("statusIds");
        memberIds =[];
        const $checkBoxs = $('.member-checkbox');
        $checkBoxs.each((i,box) => {
            if($(box).is(":checked")){
                memberIds.push($(box).val());
            }
        })
        form.append("statusIds", new Blob([JSON.stringify(memberIds)],{ type: "application/json" }))
        customService.modifyStatus()
        customService.getList(showList)
    })


    $modifyTypeBtn.on("click", function () {
        form.delete("typeIds");
        memberIds =[];
        const $checkBoxs = $('.member-checkbox');
        $checkBoxs.each((i,box) => {
            if($(box).is(":checked")){
                memberIds.push($(box).val());
            }
        })

        form.append("typeIds", new Blob([JSON.stringify(memberIds)],{ type: "application/json" }))
        customService.modifyType()
        customService.getList(showList)
    })

    $modifyAdminBtn.on("click", function () {
        form.delete("adminIds");
        memberIds =[];
        const $checkBoxs = $('.member-checkbox');
        $checkBoxs.each((i,box) => {
            if($(box).is(":checked")){
                memberIds.push($(box).val());
            }
        })

        form.append("adminIds", new Blob([JSON.stringify(memberIds)],{ type: "application/json" }))
        customService.modifyAdmin()
        customService.getList(showList)
    })

})