$(document).ready(function () {

    const form = new FormData();

    let text ="";
    const $noticeListContainer = $('.notice-list');

    /*페이징 감싸는 wrap*/
    const $pagingWrap = $('#paging-wrap');

    let pagingText= "";

    let deleteIds = [];

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
            $noticeListContainer.html(text);

        });

        rowCount = result.pageable.pageSize;
        pageCount =5 ;
        total = result.totalElements;
        endPage = (Math.ceil(page2 / pageCount) * pageCount)
        startPage = endPage - pageCount + 1;
        realEnd =Math.ceil(total / rowCount);
        if(realEnd < endPage) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }

        nextPage = Math.ceil((page+1)/5)*5+1;

        if(startPage >1){
            pagingText += ` <a class="paging paging-move prev"><img src="/images/admin/prev.png" width="15px"></a>`
        }

        for (let i =startPage; i <= endPage; i++){
            if(result.pageable.pageNumber+1 == i){
                pagingText += `<a class="paging paging-checked">${i}</a>`
            }else{
                pagingText += `<a class="paging not-checked">${i}</a>`
            }
        }
        if(endPage < realEnd){
            pagingText += `<a class=" paging paging-move next">
                                <img src="/images/admin/next.png" width="15px"></a>`
        }
        pagingText+=`<div></div>`
        $pagingWrap.html(pagingText);
    }

    /*페이지 버튼클릭*/
    $(document).on("click", ".not-checked", function () {
        page2 = $(this).text();
        page = $(this).text()-1;
        noticeService.getList(showList, page);
    })

    $(document).on("click", ".prev", function () {
        page = startPage -3;
        page2 = startPage -2;
        noticeService.getList(showList, page2);
    })
    $(document).on("click", ".next", function () {
        page = nextPage-1
        page2 = nextPage
        noticeService.getList(showList, page);
    })

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
})