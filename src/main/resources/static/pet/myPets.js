$(function() {

    $("#imgInput").change(function() {
      readURL(this);
    });

    $("#submitBtn").click(function (event) {
//      event.preventDefault();
      imgUpload();
    });
})

let imgUpload = function() {
    let contextPath = $("meta[name='ctx']").attr("content");
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
        },
        error: function (e) {
          alert(e);
          console.log(e);
          $("#submitBtn").prop("disabled", false);
        }
    })


}

let readURL = function(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#preview').attr('href', e.target.result);
      $('#previewImg').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]); // convert to base64 string
    $('#preview').show();
    $('#submitBtn').show();
  } else {
    $('#preview').hide();
    $('#submitBtn').hide();
  }
}

lightbox.option({
  'resizeDuration': 500,
  'wrapAround': true,
})