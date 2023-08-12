$(document).ready(function () {

    const $inquiryListContainer = $('.inquiry-list-container');
    let text=``;

    let customService = (function () {
        function getList(callback){
            $.ajax({
                url: `/api/admins/inquiry/list`,
                type: `get`,
                success: function(result){
                    console.log(result);
                    if(callback){
                        callback(result);
                    }
                }
            })
        }
        return {getList: getList};
    })();

    customService.getList(showList);
    function showList(result) {
        text=''

        result.content.forEach(inquiry => {
            text += `
                       <tr>
                                                    <td class="checkbox-line">
                                                        <input
                                                                type="checkbox"
                                                                class="inquiryCheckbox"
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


})
