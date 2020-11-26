(function() {
    const contextPath = $("meta[name='ctx']").attr("content");
    console.log("document : " + document);
    var stepperElem = document.querySelector('.bs-stepper');
    console.log('stepperElem : ' + stepperElem)
    var stepper = new Stepper(stepperElem);
    var done = false;
    var currStep = 1;
    history.pushState(currStep, '');
    //切換到步驟前觸發，呼叫e.preventDefault()可阻止切換
    stepperElem.addEventListener("show.bs-stepper", function (e) {
      var idx = e.detail.indexStep;
      if (done) { //若程序完成，不再切換
        e.preventDefault();
        return;
      }
    });
    //切換到步驟後觸發，e.detail.indexStep為目前步驟序號(從0開始)
    console.log(2)
    stepperElem.addEventListener("shown.bs-stepper", function (e) {
      var idx = e.detail.indexStep + 1;
      currStep = idx;
      //pushState()記下歷程以支援瀏覽器回上頁功能
      history.pushState(idx, '');
    })
    //瀏覽器上一頁下一頁觸發
    console.log(3)
    window.onpopstate = function (e) {
      if (e.state && e.state != currStep)
        stepper.to(e.state);
    };
})();

let validateEmail = function() {
    $('#mailEmpty').hide();
    $('#mailError').hide();
    let emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

    let flag = false;
    let mail = $(".mailValidation").val().trim();

    if (mail == '') {
        $('#mailEmpty').show();
    } else if (mail.search(emailRule) == -1) {
        $('#mailError').show();
    } else {
      sendCodeMail(mail);
      flag = true;
    }

    if (flag) {
        stepper.next();
    }
}

let sendCodeMail = function(mail) {
    $.ajax({
      url: contextPath + "/api/sendCodeMail",
      data: {
        mail: mail
      },
      dataType: 'json'
    });
}

let validateCode = function() {
    $('#codeError').hide();
    let code = $(".codeValidation").val().trim();
    let flag = false;

    $.ajax({
      url: contextPath + "/api/validCode",
      type: "POST",
      data: {
        code: code
      },
      dataType: 'text',
      async : false,
      success: function(data) {
        if (data === '驗證成功') {
          flag = true;
        } else {
          $('#codeError').text('*' + data);
          $('#codeError').show();
        }
      },
      error: function (e) {
        alert(e);
        console.log(e);
      }
    });

    if (flag) {
        stepper.next();
    }
}

let validatePassword = function() {
    $('#passwordEmpty').hide();
    $('#passwordError').hide();
    let password1 = $(".password1").val().trim();
    let password2 = $(".password2").val().trim();
    let flag = false;

    if (password1 == '' || password2 == '') {
        $('#passwordEmpty').show();
    } else if (password1 != password2) {
        $('#passwordError').show();
    } else {
        $.ajax({
          url: contextPath + "/api/changePassword",
          type: "POST",
          data: {
            password1: password1,
            password2: password2
          },
          dataType: 'text',
          async : false,
          success: function(data) {
            if (data) {
              flag = true;
            } else {
              alert('修改密碼失敗');
            }
          },
          error: function (e) {
            alert(e);
            console.log(e);
          }
        });
    }

    if (flag) {
        stepper.next();
        done = true;
    }
}