$(document).ready(function () {

    const form = new FormData();
    const form2 = new FormData();

    /*페이징 감싸는 wrap*/
    const $pagingWrap = $('#paging-wrap');

    /*맴버 목록*/
    const $memberListWrap = $('.member-list-wrap');

    let pagingText= "";

    const $modifyStatusBtn = $('.modify-status');
    const $modifyTypeBtn = $('.modify-type');

    let text=``;


    let memberIds = []

    /*실제 페이지*/
    let page = 0;
    /*화면 페이지*/
    let page2 =1;
    /*page는 0, 1로 나누어서 계산하기 위한 변수*/
    let nextPage;
    /*고대 문법/*/
    let rowCount;
    let pageCount;
    let total;
    let endPage;
    let startPage;
    let realEnd;

    let memberService = (function () {

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
                data: form2,
                success: function(){
                    alert("성공");
                }
            })
        }

        return {getList : getList, modifyType: modifyType, modifyStatus : modifyStatus}
    })()

    memberService.getList(showList, page)

    function showList(result){
        text='';
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
    }


    $modifyStatusBtn.on("click", function () {
        form.delete("ids");
        memberIds =[];
        const $checkBoxs = $('.member-checkbox');
        $checkBoxs.each((i,box) => {
            if($(box).is(":checked")){
                memberIds.push($(box).val());
            }
        })
        console.log(memberIds);
        form.append("ids", new Blob([JSON.stringify(memberIds)],{ type: "application/json" }))
        memberService.modifyStatus()
        memberService.getList(showList)
    })


    $modifyTypeBtn.on("click", function () {
        form2.delete("ids");
        memberIds =[];
        const $checkBoxs = $('.member-checkbox');
        $checkBoxs.each((i,box) => {
            if($(box).is(":checked")){
                memberIds.push($(box).val());
            }
        })

        console.log(memberIds);
        form2.append("ids", new Blob([JSON.stringify(memberIds)],{ type: "application/json" }))
        memberService.modifyType()
        memberService.getList(showList)
    })
})