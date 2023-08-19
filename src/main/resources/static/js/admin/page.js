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
const $pagingWrap = $('#paging-wrap');
let pagingText= "";


function pagaing(pageSize,pageTotal,pageNumber) {


    rowCount = pageSize;
    pageCount =5 ;
    total = pageTotal;
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
        if(pageNumber+1 == i){
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

