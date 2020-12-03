let validForm = function() {
    $('.validAccount').hide();
    $('.validPassword').hide();
    let account = $('#account').val().trim();
    let password = $('#password').val().trim();
    let flag = true;
    if (account == '') {
        $('#account').focus();
        $('.validAccount').show();
        flag = false;
    }
    if (password == '') {
        $('#password').focus();
        $('.validPassword').show();
        flag = false;
    }
    return flag;
}