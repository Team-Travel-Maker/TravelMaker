
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
/*                    if(result.pageable){
                        pagaing(result.pageable.pageSize, result.totalElements, result.pageable.pageNumber)}*/
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
            option.contentText.find('[data-key]').each((i, item)=> {
                /* 이미지 검사*/
                if(item.dataset.type !== undefined){
                    let file = data[$(item).data('key')][0];
                    /** data-img 속성 있으면 썸네일 아니면 원본*/
                    $(item).data("img") !== undefined ?  fileName =  file.filePath + "/t_" + file.fileUuid + "_" + file.fileName :
                        fileName =  file.filePath + "/" + file.fileUuid + "_" + file.fileName;
                    /** data-role 속성 있으면 url 없으면 img src*/
                    $(item).data('role') !== undefined ? $(item).css("background-image", `url('/api/files/display?fileName=${fileName}')`) :
                        $(item).attr("src", `/api/files/display?fileName=${fileName}`);
                }

                /*일반 내용 검사*/
                /** depth 가 2 일 경우 도 검사해 야한다.*/
                if($(item).data('type') === undefined){
                    let dataKey =$(item).data('key');
                    let tags =  $(item).data('key').split('.');

                    if($(item).get(0).nodename == "input" ||  $(item).get(0).nodename == "select"){
                        tags[1] === undefined ? $(item).val(`${data[dataKey]}`)
                            : $(item).val(`${data[tags[0]][tags[1]]}`)
                    }else{
                        tags[1] === undefined ? $(item).text(`${data[dataKey]}`)
                            : $(item).text(`${data[tags[0]][tags[1]]}`)
                    }
                }
            })

            /** 모든 태그를 찾은 후 다시 전체를 감싸는 태그 안에 변경 된 값을 넣어주고  contents에 담아 주기*/
            contents +=  option.contentText.html();
        });

        option.container.html(contents)
    }
}
