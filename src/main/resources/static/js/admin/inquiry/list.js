$(document).ready(function () {

    const $inquiryListContainer = $('.inquiry-list-container');


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
        let text=``;

        result.content.forEach(inquiry => {
            console.log(inquiry.memberEmail)
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
                                                        <a>${inquiry.csTitle}</a>
                                                    </td>
                                                    <td>${inquiry.memberName}</td>
                                                    <td>${inquiry.memberEmail}</td>
                                                    </td>
                                                    <td>${inquiry.createdDate}</td>
                                                    <td>${inquiry.csType.name}</td>
                                                    <td style="color:#ff0000">답변 미완료</td>
                                                </tr>
            
                     `



        })

        $inquiryListContainer.html(text);

    }


})
