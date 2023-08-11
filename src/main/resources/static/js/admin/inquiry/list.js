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
                        callback();
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
            text += `
                       <tr>
                                                    <td class="checkbox-line">
                                                        <input
                                                                type="checkbox"
                                                                class="inquiryCheckbox"
                                                                name="check"/>
                                                    </td>
                                                    <td>1</td>
                                                    <td>
                                                        <a th:href="@{/admins/inquiry/detail}">문의 제목</a>
                                                    </td>
                                                    <td th:text="이종문"></td>
                                                    <td th:text="닉네임"></td>
                                                    </td>
                                                    <td th:text="날짜"></td>
                                                    <td th:text="문의"></td>
                                                    <td style="color:#ff0000">답변 미완료</td>
                                                </tr>
            
                     `



        })

    }


})
