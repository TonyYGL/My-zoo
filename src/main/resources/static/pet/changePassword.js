(function() {
    var stepperElem = document.querySelector('.bs-stepper');
    var stepper = new Stepper(stepperElem);
    var done = false;
    var currStep = 1;
    history.pushState(currStep, '');
});



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
      flag = true;
    }

    if (flag) {
        stepper.next();
    }
}

let validateCode = function() {
    $('#codeError').hide();
    let code = $(".codeValidation").val().trim();
    let flag = false;
    if (code == '1') {
        flag = true;
    } else {
        $('#codeError').show();
    }

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
        flag = true;
    }
    if (flag) {
        stepper.next();
        done = true;
    }
}

//切換到步驟前觸發，呼叫e.preventDefault()可阻止切換
stepperElem.addEventListener("show.bs-stepper", function (e) {
  var idx = e.detail.indexStep;
  if (done) { //若程序完成，不再切換
    e.preventDefault();
    return;
  }
});
//切換到步驟後觸發，e.detail.indexStep為目前步驟序號(從0開始)
stepperElem.addEventListener("shown.bs-stepper", function (e) {
  var idx = e.detail.indexStep + 1;
  currStep = idx;
  //pushState()記下歷程以支援瀏覽器回上頁功能
  history.pushState(idx, '');
})
//瀏覽器上一頁下一頁觸發
window.onpopstate = function (e) {
  if (e.state && e.state != currStep)
    stepper.to(e.state);
};