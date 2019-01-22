$(function () {
    //页面加载时执行
    var username = localStorage.getItem("uname");
    var nick = localStorage.getItem("nick");
    //设置用户名
    $('#user-name-span').html(username);
    //设置用户昵称
    $('#user-nick-span').html(nick);

})

/**
 * 用户管理跳转
 */
$("#user-manager-a").on("click",function () {
    //获取usertoken
    var usertoken = localStorage.getItem("usertoken");
    //usertoken为空,跳转到登陆页
    if(usertoken == null){
        // 返回信息
        $('#login-modal-alert .am-modal-bd').text("登陆已失效，请重新登陆!");
        $('#login-modal-alert').modal();
        window.location.href = getRootPath()+"/login";
    }
    window.location.href = getRootPath()+"/user-manager?usertoken="+usertoken;
})


/**
 * 角色管理跳转
 */
$("#role-manager-a").on("click",function () {
    //获取usertoken
    var usertoken = localStorage.getItem("usertoken");
    //usertoken为空,跳转到登陆页
    if(usertoken == null){
        // 返回信息
        $('#login-modal-alert .am-modal-bd').text("登陆已失效，请重新登陆!");
        $('#login-modal-alert').modal();
        window.location.href = getRootPath()+"/login";
    }
    window.location.href = getRootPath()+"/role-manager?usertoken="+usertoken;
})

