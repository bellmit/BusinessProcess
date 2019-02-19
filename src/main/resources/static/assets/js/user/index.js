$(function () {
    //页面加载时执行
    var username = localStorage.getItem("uname");
    var nick = localStorage.getItem("nick");
    var usertoken = localStorage.getItem("usertoken");
    //设置用户名
    $('#user-name-span').html(username);
    //设置用户昵称
    $('#user-nick-span').html(nick);


    /**
     * websocket
     */
    var socket;
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        // socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
        var url = getRootPath()+"/websocket/"+localStorage.getItem("uid").replace("http","ws")+"?usertoken="+usertoken;
        socket = new WebSocket(url.replace("http","ws"));
        //打开事件
        socket.onopen = function() {
            console.log("Socket 已打开");
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        socket.onclose = function() {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
            //此时可以尝试刷新页面
        }
        //离开页面时，关闭socket
        //jquery1.8中已经被废弃，3.0中已经移除
        // $(window).unload(function(){
        //     socket.close();
        //});
    }

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

/**
 * 权限管理跳转
 */
$("#permission-manager-a").on("click",function () {
    //获取usertoken
    var usertoken = localStorage.getItem("usertoken");
    //usertoken为空,跳转到登陆页
    if(usertoken == null){
        // 返回信息
        $('#login-modal-alert .am-modal-bd').text("登陆已失效，请重新登陆!");
        $('#login-modal-alert').modal();
        window.location.href = getRootPath()+"/login";
    }
    window.location.href = getRootPath()+"/permission-manager?usertoken="+usertoken;
})





