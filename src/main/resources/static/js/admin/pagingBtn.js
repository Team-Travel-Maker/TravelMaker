/*페이지 버튼클릭*/
$(document).on("click", ".not-checked", function () {
    page2 = $(this).text();
    page = $(this).text()-1;
    customService.getList(showList, page);
})

$(document).on("click", ".prev", function () {
    page2 = startPage -2;
    page = startPage -3;
    customService.getList(showList, page2);
})
$(document).on("click", ".next", function () {
    page = nextPage-1
    page2 = nextPage
    customService.getList(showList, page);
})