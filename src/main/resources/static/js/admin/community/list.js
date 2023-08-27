
const form = new FormData();

let text ="";
const $communityListContainer = $('.community-list');

let deleteIds = [];

let communityService = (function () {
    function deleteCommunity(form){
        $.ajax({
            url: `/api/admins/community`,
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
            url: `/api/admins/community?page=` +page,
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


    return {getList: getList, deleteCommunity: deleteCommunity};

})()


communityService.getList(showList);


function showList(result) {
    $communityListContainer.html('');
    $pagingWrap.html('');
    pagingText="";
    text="";

    result.content.forEach(community => {
        let fileName;
        community.files.forEach(file =>{
                fileName = file.filePath + "/t_" + file.fileUuid + "_" + file.fileName;
        })

        text += `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="community-check-box"
                                    name="check"
                                    value="${community.id}"/>
                        </td>
                        <td>${community.id}</td>
                        <td>${community.communityCategory.name}</td>
                        <td>${community.memberEmail}</td>
                        <td>
                            <a href="#" style="text-decoration:underline">${community.communityTitle}</a>
                        </td>
                        <td>${community.createdDate}</td>
                        <td>${community.updatedDate}</td>
                        <td class="image-wrap">
                            <img src="/api/files/display?fileName=${fileName}" style="width: 30px; height: 30px">
                        </td>
                    </tr>
                    `
    });
    $communityListContainer.html(text);
    pagaing(result.pageable.pageSize,result.totalElements,result.pageable.pageNumber);

}

/*삭제버튼*/
$('.delete-button').on("click",async function () {
    form.delete("ids");
    deleteIds = [];
    $('.community-check-box').each((index,checkBox) => {
        if($(checkBox).is(":checked")){
            deleteIds.push($(checkBox).val());
        }
    })
    form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
    communityService.deleteCommunity(form);
    communityService.getList(showList);
    showWarnModal("삭제되었습니다");
    $('#allSelect').prop("checked", false);
})
