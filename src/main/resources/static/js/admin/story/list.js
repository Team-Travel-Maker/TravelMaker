
const form = new FormData();

let text ="";
const $storyListContainer = $('.story-list');

let deleteIds = [];

let storyService = (function () {
    function deleteStore(form){
        $.ajax({
            url: `/api/admins/story`,
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
            url: `/api/admins/story?page=` +page,
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


storyService.getList(showList);


function showList(result) {
    $storyListContainer.html('');
    $pagingWrap.html('');
    pagingText="";
    text="";

    result.content.forEach(story => {
        let fileName;
        story.files.forEach(file =>{
            if(file.fileType.code == "CONTENT_REPRESENTATIVE"){
                fileName = file.filePath + "/t_" + file.fileUuid + "_" + file.fileName;
            }
        })

        text += `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="story-check-box"
                                    name="check"
                                    value="${story.id}"/>
                        </td>
                        <td>${story.id}</td>
                        <td>${story.memberEmail}</td>
                        <td>
                            <a href="#" style="text-decoration:underline">${story.storyTitle}</a>
                        </td>
                        <td>${story.createdDate}</td>
                        <td>${story.updatedDate}</td>
                        <td class="image-wrap">
                            <img src="/api/files/display?fileName=${fileName}" style="width: 30px; height: 30px">
                        </td>
                    </tr>
                    `
    });
    $storyListContainer.html(text);
    pagaing(result.pageable.pageSize,result.totalElements,result.pageable.pageNumber);

}

/*삭제버튼*/
$('.delete-button').on("click",async function () {
    form.delete("ids");
    deleteIds = [];
    $('.story-check-box').each((index,checkBox) => {
        if($(checkBox).is(":checked")){
            deleteIds.push($(checkBox).val());
        }
    })
    form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
    storyService.deleteStore(form);
    storyService.getList(showList);
    showWarnModal("삭제되었습니다");
    $('#allSelect').prop("checked", false);
})
