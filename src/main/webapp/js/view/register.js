//校验用户名
function checkUserName(){
    var username = $("#username").val();
    var reg_username = /^\w{8,20}$/;
    var flag = reg_username.test(username);
    if (flag){
        //合法
        $("#username").css("border","");
    } else {
        //不合法
        $("#username").css("border","1px solid red");
    }
    return flag;
}

//校验密码
function checkPassword(){
    var password = $("#password").val();
    var reg_password = /^\w{8,20}$/;
    var flag = reg_password.test(password);
    if (flag){
        //合法
        $("#password").css("border","");
    } else {
        //不合法
        $("#password").css("border","1px solid red");
    }
    return flag;
}

//校验邮箱
function checkEmail(){
    var email = $("#email").val();
    var reg_email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

    var flag = reg_email.test(email);
    if (flag){
        //合法
        $("#email").css("border","");
    } else {
        //不合法
        $("#email").css("border","1px solid red");
    }
    return flag;
}

//校验姓名
function checkName(){
    var name = $("#name").val();
    var reg_name = /^\w{2,64}$/;

    var flag = reg_name.test(name);
    if (flag){
        //合法
        $("#name").css("border","");
    } else {
        //不合法
        $("#name").css("border","1px solid red");
    }
    return flag;
}

//校验手机号
function checkTelephone(){
    var telephone = $("#telephone").val();
    var reg_telephone = /^(1[2|3|4|5|6|7|8|9][0-9])\d{8}$/;
    var flag = reg_telephone.test(telephone);
    if (flag){
        //合法
        $("#telephone").css("border","");
    } else {
        //不合法
        $("#telephone").css("border","1px solid red");
    }
    return flag;
}

//校验日期
function checkBirthday(){
    var birthday = $("#birthday").val();
    var reg_birthday = /^\d{4}-\d{1,2}-\d{1,2}$/;
    var flag = reg_birthday.test(birthday);
    if (flag){
        //合法
        $("#birthday").css("border","");
    } else {
        //不合法
        $("#birthday").css("border","1px solid red");
    }
    return flag;
}

$(function(){
    $("#registerForm").submit(function(){
        if (checkUserName()
            && checkPassword()
            && checkEmail()
            && checkName()
            && checkTelephone()
            && checkBirthday()){
            $.post("user/regist",$(this).serialize(),function(data){
                if(data.flag){
                    //注册成功，跳转成功页面
                    location.href="register_ok.html";
                }else{
                    //注册失败,给errorMsg添加提示信息
                    $("#errorMsg").html(data.errorMsg);

                }
            });
        }

        //禁止页面跳转
        return false;
    });

    $("#username").blur(checkUserName);
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);
    $("#name").blur(checkName);
    $("#telephone").blur(checkTelephone);
    $("#birthday").blur(checkBirthday);
});