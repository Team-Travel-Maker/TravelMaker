const $lists = $('.title-list-slick-track')

let firstText = `

                `

let MainService = (function () {


    function getList(callback, url, index){
        $.ajax({
            url: url,
            type: `get`,
            async: false,
            success: function(result){
                console.log(result)
                if(callback){
                    callback(result, index);
                }
            }
        })
    }


    return {getList: getList};

})()


MainService.getList(showList, "/api/admins/store" , 0);


function showList(results, index) {
    $lists.eq(index).html('');
    text="";

    results.content.forEach(result => {

        if(result.storeStatus.code == "PENDING" || result.storeStatus.code == "REJECTED"){return false}

        let fileName;
        result.storeFiles.forEach(file =>{
            if(file.fileType.code == "REPRESENTATIVE"){
                fileName = file.filePath + "/" + file.fileUuid + "_" + file.fileName;
            }
        })

        firstText += `
                <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="https://www.wanted.co.kr/company/5927">
                            <header role="img" style="background-image: url('/api/files/display?fileName=${fileName}');"></header>
                            <div class="card-body-wrap">
                                <div class="card-body-flexbox">
                                    <div class="card-body-info">
                                        <p class="card-body-info-title">${result.storeTitle}</p>
                                        <p class="card-body-info-category">${result.storeType.name}</p>
                                    </div>
                                </div>
                                <button type="button" class="card-body-button">
                                    <span class="card-body-button-label">바로가기</span>
                                    <span class="card-body-button-interaction"></span>
                                </button>
                            </div>
                        </a>
                    </div>
                </div>
                    `
    });
    $lists.eq(index).html(firstText);
}