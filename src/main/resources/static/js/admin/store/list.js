
console.log("들어옴")
const form = new FormData();

let text ="";
const $storeListContainer = $('.store-list');

let deleteIds = [];

let storeService = (function () {

    function getList(callback,page){
        $.ajax({
            url: `/api/admins/store?page=` +page,
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
    return {getList: getList};

})()


storeService.getList(showList);


function showList(result) {
    $storeListContainer.html('');
    $pagingWrap.html('');
    pagingText="";
    text="";

    result.content.forEach(store => {
        let fileName;
        store.storeFiles.forEach(file =>{
            if(file.fileType.code == "REPRESENTATIVE"){
                fileName = file.filePath + "/t_" + file.fileUuid + "_" + file.fileName;
            }
        })

                 text += `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="noticeCheckbox"
                                    name="check"
                                    value="${store.id}"/>
                        </td>
                        <td>${store.id}</td>
                        `
            if(store.storeStatus.code=="PENDING") {
                 text+= `<td class="waiting">${store.storeStatus.name}</td>`
            }else if(store.storeStatus.code=="APPROVED"){
                text+= `<td class="approval">${store.storeStatus.name}</td>`
            }else{
                text+= `<td class="companion">${store.storeStatus.name}</td>`
            }
                 text+= `
                        <td>${store.memberEmail}</td>
                        <td>
                            <a href="/admins/store/detail/${store.id}">${store.storeTitle}</a>
                        </td>
                        <td>${store.storeType.name}</td>
                        <td>${store.createdDate}</td>
                        <td>${store.updatedDate}</td>
                        <td class="image-wrap">
                            <img src="/api/files/display?fileName=${fileName}" style="width: 30px; height: 30px">
                        </td>
                    </tr>
                    `
    });
    $storeListContainer.html(text);
    pagaing(result.pageable.pageSize,result.totalElements,result.pageable.pageNumber);

}
