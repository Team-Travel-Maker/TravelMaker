$(document).ready(function () {

    let text ="";
    let noticeText = "";
    const $noticeListContainer = $('.article-list');

    const $detailContainer = [$('.article__bg')];

    /*페이징*/
    const $pagingWrap= $('.pagination-ul')
    let pagingText= "";
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

        function getList(callback, page){
            $.ajax({
                url: `/api/admins/notice?page=` + page,
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

        if(noticeId != ""){
            function getDetail(callback){
                $.ajax({
                    url: `/api/admins/notice/detail/${noticeId}`,
                    type: `get`,
                    success: function(result){
                        console.log(result);
                        if(callback){
                            callback(result);
                        }
                    }
                })
            }
        }

        return {getList: getList, getDetail : getDetail};

    })()

    noticeService.getList(showList)

    /*상세*/
    if(noticeId != ""){
     noticeService.getDetail(showDetail);
    }


    function showList(result) {
        $noticeListContainer.html('');
        $pagingWrap.html('')
        pagingText="";
        text="";

        result.content.forEach(notice => {
            text += `
                    <li class="article">
                        <a href="/informations/notice/detail/${notice.id}">${notice.noticeTitle}</a>
                    </li>
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
            pagingText += `  
                          <li class="pagination-prev">
                            <a rel="prev nofollow">‹</a>
                          </li>
                          `
        }

        for (let i =startPage; i <= endPage; i++){
            if(result.pageable.pageNumber+1 == i){
                pagingText += `
                              <li class="pagination-current">
                                <span>${i}</span>
                              </li>
                              `
            }else{
                pagingText += `
                            <li>
                                <a rel="next nofollow" class="not-checked">${i}</a>
                            </li>
                              `
            }
        }
        if(endPage < realEnd){
            pagingText += `
                           <li class="pagination-next">
                             <a rel="next nofollow" class="next">›</a>
                           </li>
                          `
        }
        pagingText+=`<div></div>`
        $pagingWrap.html(pagingText);
    }

    /*페이지 버튼클릭*/
    $(document).on("click", ".not-checked", function () {
        sectionPage = page;
        page2 = $(this).text();
        page = $(this).text()-1;
        noticeService.getList(showList, page);
    })

    $(document).on("click", ".pagination-prev", function () {
        page = startPage -3;
        page2 = startPage -2;
        noticeService.getList(showList, page2);
    })
    $(document).on("click", ".pagination-next", function () {
        page = nextPage-1
        page2 = nextPage
        noticeService.getList(showList, page);
    })

    function showDetail(result) {
        if(result.files.length ==0){
            noticeText =`
                         <h3 class="article-head">${result.noticeTitle}</h3>
                        <div class="article-body">
                            <div><strong>${result.noticeContent}</strong></div>
                        </div>
                    `
        }else{
            let fileName = result.files[0].filePath + "/t_" + result.files[0].fileUuid + "_" + result.files[0].fileName;
            noticeText =`
                         <h3 class="article-head">${result.noticeTitle}</h3>
                        <div class="article-body">
                            <img src="/api/files/display?fileName=${fileName}" alt="">
                            <br>
                            <div><strong>${result.noticeContent}</strong></div>
                        </div>
                    `
        }
        if($detailContainer.length !=0){
            $detailContainer[0].html(noticeText);
        }

        noticeService.getList(showList)
    }

})