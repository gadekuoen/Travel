$(function(){
    $("#btn_sub").click(function(){
        $.post("user/login",$("#loginForm").serialize(),function(data){
            if (data.flag){
                location.href="index.html";
            } else {
                $("#errorMsg").html(data.errorMsg);
            }
        });
    });
});