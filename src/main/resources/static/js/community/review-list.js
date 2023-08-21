const $categoryBtns = $('.category-Item');

console.log($categoryBtns);

$categoryBtns.on('click',function(){
    $categoryBtns.removeClass("category-btn-list");
    if(!$(this).hasClass("category-btn-list")){
        $(this).addClass("category-btn-list");
    }
});

$(document).ready(function () {
    $(".selected").addClass("category-btn-list")
})



