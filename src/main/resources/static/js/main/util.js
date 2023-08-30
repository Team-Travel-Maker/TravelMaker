
let mainService = (function () {
    function getList(callback, option){
        $.ajax({
            url: option.url,
            type: `get`,
            async: false,
            success: function(result){
                console.log(result)
                if(callback){
                    callback(result, option);
                }
            }
        })
    }
    return {getList: getList};

})()

function showList(json, option){
    let contents = ""
    /*파일이 있으면 담을*/
    let fileName = ""
    /*jquery 인거 검사*/
    option.contentText  === option.contentText instanceof jQuery ? option.contentText: $(option.contentText);
    option.container === option.container instanceof jQuery ? option.container : $(option.container)

    if(json.content.length > 1){
        json.content.forEach(data => {
            option.contentText.find('*').each((i, item)=> {

                /* 이미지 검사*/
                if($(item).attr("role") == "img"){
                    let file = data[$(item).attr("data-name")][0];
                    fileName =  file.filePath + "/" + file.fileUuid + "_" + file.fileName;
                    $(item).css("background-image", `url('/api/files/display?fileName=${fileName}')`);
                }

                /*일반 내용 검사*/
                if($(item).attr("data-class")){
                    /** depth 가 2 일 경우 도 검사해 야한다.*/
                    $(item).attr('data-class').split('.')[1] === undefined ? $(item).text(`${data[$(item).attr('data-class')]}`)
                           : $(item).text(`${data[$(item).attr('data-class').split('.')[0]][$(item).attr('data-class').split('.')[1]]}`)
                }
            })

            /** 모든 태그를 찾은 후 다시 전체를 감싸는 태그 안에 변경 된 값을 넣어주고  contents에 담아 주기*/
            contents +=   option.contentText.eq(0).html(option.contentText.find('*')[0].outerHTML)[0].outerHTML;
        });

        option.container.html(contents)
    }
}
