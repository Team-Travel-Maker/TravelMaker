$(document).ready(function () {

    let text ="";
    let noticeText = "";
    const $noticeListContainer = $('.article-list');

    const $detailContainer = [$('.article__bg')];


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


    function showDetail(result) {
        let fileName = result.files[0].filePath + "/t_" + result.files[0].fileUuid + "_" + result.files[0].fileName;
        console.log(result.files[0]);
        noticeText =`
                         <h3 class="article-head">${result.noticeTitle}</h3>
                            
                        <div class="article-body">
                            <img src="/api/files/display?fileName=${fileName}" alt="">
                            <br>
                            <div><strong>${result.noticeContent}</strong></div>
                        </div>
                    `
        if($detailContainer.length !=0){
            $detailContainer[0].html(noticeText);
        }
    }


    function showList(result) {
        $noticeListContainer.html('');
        text="";

        result.content.forEach(notice => {
            text += `
                    <li class="article">
                        <a href="/informations/notice/detail/${notice.id}">${notice.noticeTitle}</a>
                    </li>
                    `
            $noticeListContainer.html(text);

        });
    }

})