$(document).ready(function () {

    let text ="";
    const $noticeListContainer = $('.notice-list');

    let noticeService = (function () {

            function getList(callback){
                $.ajax({
                    url: `/api/admins/notice`,
                    type: `get`,
                    async: false,
                    success: function(result){
                        console.log(result)
                        if(callback){
                            callback(result);
                        }
                    }
                })
            }

        return {getList: getList};

    })()

    noticeService.getList(showList)

    function showList(result) {
        $noticeListContainer.html('');
        text="";

        result.content.forEach(notice => {
            text += `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="noticeCheckbox"
                                    name="check"
                                    value="${notice.id}"/>
                        </td>
                        <td>${notice.id}</td>
                        <td>
                            <a href="/admins/notice/detail/${notice.id}">${notice.noticeTitle}</a>
                        </td>
                        <td>${notice.createdDate}</td>
                        <td>${notice.updatedDate}</td>
                    </tr>-->
                    `
            $noticeListContainer.html(text);

        });
    }




})