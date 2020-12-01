$(function() {
    $("#imgInput").change(function() {
      $("#previewImages").html(""); // 清除預覽
      readURL(this);
    });

    $("#submitBtn").click(function (event) {
      imageUpload();
    });

    findImagesByUserId(1);
})

var findImagesByUserId = function(userId) {
    $.ajax({
        type: "GET",
        url: contextPath + "/api/images",
        data: {
            userId: userId
        },
        timeout: 1000000,
        success: function (data) {
          $.each(data, function() {
            let fileName = this.fileName;
            let createTime = this.createTime;
            let content = '<div class="responsive"><div class="gallery"><a data-lightbox="roadtrip2" href="' +  fileName + '">' +
                          '<img src="' +  fileName + '"></a></div></div>';
            $(".album").append(content);
          })
        },
        error: function (e) {
          alert(e);
        }
    })
}

var readURL = function(input) {
    if (input.files && input.files.length > 0) {
        for (let i = 0; i < input.files.length; i++) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var box = $("<a class='box' data-lightbox='roadtrip' style='display:none'>").attr('href', e.target.result);
                var img = $("<img width='150' height='150' style='margin: 2px;'>").attr('src', e.target.result);
                var lightbox = box.append(img);
                $("#previewImages").append(lightbox);
                $('.box').show();
                $('#submitBtn > i').text("上傳照片共" + input.files.length + "張")
                $('#submitBtn').show();
            }
            reader.readAsDataURL(input.files[i]);
        }
    } else {
        $('#submitBtn').hide();
    }
}

var imageUpload = function() {
    var form = $('#imgForm')[0];
    var data = new FormData(form);
    var file = data.get("files");
    $("#submitBtn").prop("disabled", true);

    $.ajax({
        type: "POST",               //使用POST傳輸,檔案上傳只能用POST
        enctype: 'multipart/form-data', //將資料加密傳輸 檔案上傳一定要有的屬性
        url: contextPath + "/api/imgUpload", //要傳輸對應的接口
        data: data,         //要傳輸的資料,我們將form 內upload打包成data
        processData: false, //防止jquery將data變成query String
        contentType: false,
        cache: false,  //不做快取
        async : false, //設為同步
        timeout: 1000000, //設定傳輸的timeout,時間內沒完成則中斷
        success: function (data) {
          alert(data);
          $("#submitBtn").prop("disabled", false);
          location.reload();
        },
        error: function (e) {
          alert(e);
          $("#submitBtn").prop("disabled", false);
        }
    })
}

lightbox.option({
  'resizeDuration': 100,
  'wrapAround': true,
})