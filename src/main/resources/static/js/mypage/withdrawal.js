const $btn = $('.leave-agree-btn');
console.log($btn);

$("input[name=check]").click(function() {
    var total = $("input[name=check]").length;
    var checked = $("input[name=check]:checked").length;
    console.log(checked);
    $btn.attr("disabled",'disabled');
    if(checked==2){
        $btn.removeAttr("disabled")
    }
});