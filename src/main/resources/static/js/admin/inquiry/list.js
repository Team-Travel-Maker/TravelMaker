$(document).ready(function () {

    const form = new FormData();

    const $inquiryListContainer = $('.inquiry-list-container');

    let text=``;

    let deleteIds = [];


    let customService = (function () {
        function getList(callback){
            $.ajax({
                url: `/api/admins/inquiry/list`,
                type: `get`,
                async: false,
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                }
            })
        }

       function deleteInquiry(){
            $.ajax({
                url: `/api/admins/inquiry`,
                type: `delete`,
                async: false,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                data: form,
                success: function(){
                }
            })
        }

        return {getList: getList, deleteInquiry : deleteInquiry};
    })();


    customService.getList(showList);


    function showList(result) {
        $inquiryListContainer.html('');
        text=''

        result.content.forEach(inquiry => {
            text += `
                       <tr>
                                                    <td class="checkbox-line">
                                                        <input
                                                                type="checkbox"
                                                                class="inquiryCheckbox"
                                                                value="${inquiry.id}"
                                                                name="check"/>
                                                    </td>
                                                    <td>${inquiry.id}</td>
                                                    <td>
                                                        <a href="/admins/inquiry/detail/${inquiry.id}">${inquiry.csTitle}</a>
                                                    </td>
                                                    <td>${inquiry.memberName}</td>
                                                    <td>${inquiry.memberEmail}</td>
                                                    </td>
                                                    <td>${inquiry.createdDate}</td>
                                                    <td>${inquiry.csType.name}</td>
                                                    `
                    if(inquiry.csAnswers.length == 0){
                        text += `<td style="color:#ff0000">답변 미완료</td>`

                    }else {
                        text += `<td style="color:green">답변 완료</td>`
                    }
               text+= `</tr>`
        })

        $inquiryListContainer.html(text);

    }

    /*삭제버튼*/
    $('.delete-button').on("click",async function () {
        console.log( $('.inquiryCheckbox'));
        const $inquiryListTr = $('.inquiry-list-container tr');
        const deletedIdxs = [];
        $('.inquiryCheckbox').each((index,checkBox) => {
            if($(checkBox).is(":checked")){
                deleteIds.push($(checkBox).val());
                deletedIdxs.push(index);
            }
        })
       form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
        customService.deleteInquiry();
        customService.getList(showList);
        showWarnModal("삭제되었습니다");
    })


})
