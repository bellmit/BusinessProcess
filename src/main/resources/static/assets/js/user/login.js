/**
 * 浏览器标识
 * @type {string}
 */
var browerToken = "";
/**
 * 校验输入
 */
$(function () {
    //获取验证码
    getCode();
    var $input = $('input');
    var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
    $tooltip.appendTo(document.body);
    $input.keyup(function (e) {
        var $this = $(this);
        var offset = $this.offset();
        var value = $this.val();
        var msg = $this.data('foolishMsg');
        var pattern = $this.attr("pattern");
        var matchResult = value.match(pattern);
        console.log("matchResult:"+matchResult)
        if (matchResult != null) {
            $tooltip.hide();
        }else{
            $tooltip.text(msg).show().css({
                left: offset.left + 70,
                top: offset.top + $(this).outerHeight() + 10
            });
        }
    });
});

/**
 * 点击登陆按钮
 */
$('#button-login').on('click', function () {
    //校验
    var $input =  $('input');
    //用于判断校验个数
    var flag = true;
    for(var i = 0; i < $input.length; i++){
        var pattern = $('input').eq(i).attr("pattern");
        var value = $('input').eq(i).val();
        var matchResult = value.match(pattern);
        if (matchResult  != null && matchResult.length > 0) {
            continue;
        }else{
            //校验失败
            $('#login-modal-alert').modal();
            flag = false;
            return;
        }
    }
    //三项都校验通过后执行登陆
    if(flag){
        login();
    }
})

/**
 * 点击验证码图片
 */
$('#img-code').on('click',function () {
    getCode();
})


/**
 * 登陆
 */
function login() {
    //显示加载动画
    $('#login-modal-loading').modal();
    $.ajax({
        url: '/api/login',
        cache: true,
        type: 'POST',
        dataType: 'json',
        data: dataObject()
    }).then(function (data) {
        // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
        //取消加载动画
        $('#login-modal-loading').modal('close');
        if(data.code == 200 && data.message == "success"){
            // 返回信息
            $('#login-modal-alert .am-modal-bd').text('登陆成功！');
            $('#login-modal-alert').modal();
            console.log(data.data.usertoken);
            //将usertoken存在localstorage
            localStorage.setItem('usertoken',data.data.usertoken);
            localStorage.setItem('uname',data.data.uname);
            localStorage.setItem('nick',data.data.nick);
            //跳转到首页
            window.location.href = getRootPath()+"/index?usertoken="+data.data.usertoken;
        }else{
            // 返回信息
            $('#login-modal-alert .am-modal-bd').text(data.data);
            $('#login-modal-alert').modal();
        }
    }, function (data) {
        // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
        // 返回信息
        $('#login-modal-alert .am-modal-bd').text('登陆失败！');
        $('#login-modal-alert').modal();
    });
}

/**
 * 登陆参数
 * @returns {{username: (*|jQuery), password: (*|jQuery)}}
 */
function dataObject(){
    var username = $('#user-name').val();
    var password = $('#user-password').val();
    //验证码
    var randomCode = $('#code').val();
    var data = {
        'username':username,
        'password':password,
        'randomCode':randomCode,
        'browerToken':browerToken
    }
    return data;
}


//获取验证码
function getCode(){
    $.ajax({
        url:"/api/getBrowerToken",
        dataType:"json",
        type:"GET",
        contentType:"application/x-www-form-urlencoded",
        success:function (data) {
            if(data.message == "success" && data.code == 200){
                browerToken = data.data;
                $("#img-code").attr("src","/api/getRandomCode?browerToken="+browerToken);
            }else{
                alert(data.data);
            }
        }
    });
}



