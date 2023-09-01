
const form = new FormData();

let text ="";
const $storeListContainer = $('.store-list');

let deleteIds = [];

let customService = (function () {
    function deleteStore(form){
        $.ajax({
            url: `/api/admins/store`,
            type: `delete`,
            async: false,
            enctype: "multipart/form-data", //form data 설정
            processData : false,
            contentType : false,
            data: form,
            success: function(){
            }
        })
    }

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


    return {getList: getList, deleteStore: deleteStore};

})()


customService.getList(showList);


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
                            <a href="/admins/store/detail/${store.id}" style="text-decoration:underline">${store.storeTitle}</a>
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

/*삭제버튼*/
$('.delete-button').on("click",async function () {
    form.delete("ids");
    deleteIds = [];
    $('.noticeCheckbox').each((index,checkBox) => {
        if($(checkBox).is(":checked")){
            deleteIds.push($(checkBox).val());
        }
    })
    form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
    customService.deleteStore(form);
    customService.getList(showList);
    showWarnModal("삭제되었습니다");
    $('#allSelect').prop("checked", false);
})
