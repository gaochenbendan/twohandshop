// 显示错误信息
function showErrorMsg(msg) {
    $.confirm({
        title: '错误',
        content: msg,
        type: 'red',
        typeAnimated: true,
        buttons: {
            omg: {
                text: '好的ヽ(￣▽￣)و',
                btnClass: 'btn-red',
                action: function(){
                }
            }
        }
    });
};
function showWarinMsg(msg) {

    $.confirm({
        title: '警告',
        content: msg,
        type: 'red',
        typeAnimated: true,
        buttons: {
            omg: {
                text: '好的ヽ(￣▽￣)و',
                btnClass: 'btn-red',
                action: function(){

                }
            }
        }
    });
};
function showSuccess(msg,callback) {

    $.confirm({
        title: '添加成功',
        content: msg,
        type: 'greeb',
        typeAnimated: true,
        buttons: {
            omg: {
                text: '好的ヽ(￣▽￣)و',
                btnClass: 'btn-green',
                action: function(){
                    callback();
                }
            }
        }
    });
};
// 检查表单
function checkForm(formId) {
    var flag = true;
    $("#"+formId).find(".required").each(function (i,e) {
        if($(e).val() ===""){
            showErrorMsg($(e).attr("tips"));
            return flag = false;
        }

    });
    return flag;
};
function upload(showPictureImage,input) {

    var formData = new FormData();
    var data = document.getElementById("select-file").files[0];

    formData.append('photo',data);

    $.ajax({
        url:'/upload/upload_photo',
        contentType:false,
        processData:false,
        cache:false,
        data:formData,
        type: "POST",
        success:function(data){
            if(data.code === 0){
                showSuccess('图片上传添加成功!',function(){

                    $("#"+showPictureImage).attr("src",encodeURI('/photo/view?filename='+data.data));
                    $("#"+input).val(data.data);
                })

            }else{
                showErrorMsg(data.msg);
            }
        },
        error:function(data){
            alert('网络错误!');
        }

    })
};

function upload_icon(showPictureImage,input) {

    var formData = new FormData();
    var data = document.getElementById("select-file").files[0];

    formData.append('photo',data);

    $.ajax({
        url:'/upload/upload_photo_icon',
        contentType:false,
        processData:false,
        cache:false,
        data:formData,
        type: "POST",
        success:function(data){
            if(data.code === 0){
                showSuccess('图片上传添加成功!',function(){

                    $("#"+showPictureImage).attr("src",encodeURI('/photo/view?filename='+data.data));
                    $("#"+input).val(data.data);
                })

            }else{
                showErrorMsg(data.msg);
            }
        },
        error:function(data){
            alert('网络错误!');
        }

    })
};

function ajaxRequest(url,requestType,data,callback){
    $.ajax({
        url:url,
        type:requestType,
        data:data,
        dataType:'json',
        success:function(rst){
            if(rst.code === 0){
                callback(rst);
            }else{
                showErrorMsg(rst.msg);
            }
        },
        error:function(data){
            alert('网络错误!');
        }
    });
}