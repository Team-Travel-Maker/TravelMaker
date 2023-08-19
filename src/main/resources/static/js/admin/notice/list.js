
    const form = new FormData();

    let text ="";
    const $noticeListContainer = $('.notice-list');


    let deleteIds = [];


    let noticeService = (function () {

            function getList(callback,page){
                $.ajax({
                    url: `/api/admins/notice?page=` +page,
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

        function deleteNotice(){
            $.ajax({
                url: `/api/admins/notice`,
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

        return {getList: getList, deleteNotice : deleteNotice};

    })()

    noticeService.getList(showList)

    function showList(result) {
        $noticeListContainer.html('');
        $pagingWrap.html('');
        pagingText="";
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
        });
        $noticeListContainer.html(text);
        pagaing(result.pageable.pageSize,result.totalElements,result.pageable.pageNumber);

    }

    /*삭제버튼*/
    $('.delete-button').on("click",async function () {
        const deletedIdxs = [];
        $('.noticeCheckbox').each((index,checkBox) => {
            if($(checkBox).is(":checked")){
                deleteIds.push($(checkBox).val());
                deletedIdxs.push(index);
            }
        })
        form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
        noticeService.deleteNotice();
        noticeService.getList(showList);
        showWarnModal("삭제되었습니다");
    })