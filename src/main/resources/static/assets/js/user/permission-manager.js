/**
 * usertoken
 */
var usertoken = "";
$(function () {
    //页面加载时执行

    /**
     * 获取usertoken
     */
    usertoken = localStorage.getItem("usertoken");
    //usertoken为空,跳转到登陆页
    if (usertoken == null) {
        // 返回信息
        $('#modal-alert .am-modal-bd').text("登陆已失效，请重新登陆!");
        $('#modal-alert').modal();
        window.location.href = getRootPath() + "/login";
    }

    /**
     * 加载动画
     */
    $('#reload-modal-loading').modal();

    /**
     * 设置用户名和昵称
     * @type {string | null}
     */
    var username = localStorage.getItem("uname");
    var nick = localStorage.getItem("nick");
    //设置用户名
    $('#user-name-span').html(username);
    //设置用户昵称
    $('#user-nick-span').html(nick);


    /**
     * 日期选择组
     * @type {Date}
     */
    var startDate = new Date(2014, 11, 20);
    var endDate = new Date(2014, 11, 25);
    var $alert = $('#my-alert');
    $('#my-start').datepicker().on('changeDate.datepicker.amui', function (event) {
        if (event.date.valueOf() > endDate.valueOf()) {
            $alert.find('p').text('开始日期应小于结束日期！').end().show();
        } else {
            $alert.hide();
            startDate = new Date(event.date);
            $('#my-startDate').text($('#my-start').data('date'));
        }
        $(this).datepicker('close');
    });

    $('#my-end').datepicker().on('changeDate.datepicker.amui', function (event) {
        if (event.date.valueOf() < startDate.valueOf()) {
            $alert.find('p').text('结束日期应大于开始日期！').end().show();
        } else {
            $alert.hide();
            endDate = new Date(event.date);
            $('#my-endDate').text($('#my-end').data('date'));
        }
        $(this).datepicker('close');
    });

    /**
     * 获取权限数据
     */
    getMainPermissionList(1, 5, usertoken);


})

/**
 * /getRoleList参数
 * @param pageNum
 * @param pageSize
 * @param uname
 * @param usertoken
 * @returns {{pageNum: *, pageSize: *, uname: *, usertoken: *}}
 */
function getPermissionListParms(pageNum, pageSize, usertoken) {
    var pageNum = pageNum;
    var pageSize = pageSize;
    var usertoken = usertoken;

    var data = {
        'pageNum': pageNum,
        'pageSize': pageSize,
        'usertoken': usertoken
    }
    return data;
}


/**
 * 获取权限列表
 */
function getMainPermissionList(pageNum, pageSize, usertoken) {
    $.ajax({
        url: '/api/getPermissionPage',
        cache: true,
        type: 'GET',
        dataType: 'json',
        data: getPermissionListParms(pageNum, pageSize, usertoken)
    }).then(function (data) {
        // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
        //取消加载动画
        $('#reload-modal-loading').modal('close');
        if (data.code == 200 && data.message == "success") {
            // 返回信息
            console.log("获取数据成功！");
            console.log(data)
            //设置底部分页栏
            setPageBar(data);
            //设置数据列表
            setPermissionDataList(data);
        } else {
            // 返回信息
            $('#modal-alert .am-modal-bd').text(data.data);
            $('#modal-alert').modal();
        }
    }, function (data) {
        // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
        // 返回信息
        $('#modal-alert .am-modal-bd').text('登陆失败！');
        $('#modal-alert').modal();
    });
}


/**
 * 设置底部分页栏
 */
function setPageBar(data) {
    var nextLi = '';
    var previousLi = '';
    var currentPageCenter = '';
    //判断总页数是否大于5，设置是否可以点击下一组页码

    /**
     *当前页
     */
        //当前页
    var prePage = data.data.prePage;
    //总页数
    var pages = data.data.pages;
    //hasNextPage
    var hasNextPage = data.data.hasNextPage;
    //hasPreviousPage
    var hasPreviousPage = data.data.hasPreviousPage;
    //prePage当前页
    var prePage = data.data.prePage + 1;

    //是否可以点击下一页
    if (hasNextPage) {
        nextLi = "<li id=\"next-page-li\"><a href=\"javascript:;\" onclick=\"nexPage(" + prePage + ")\">下一页</a></li>";
    } else {
        nextLi = "<li  id=\"next-page-li\"><a href=\"javascript:;\" onclick=\"setAlertPage('next')\">下一页</a></li>";
    }
    currentPageCenter += "<li class='am-active'><a>" + prePage + "</a></li>";
    //是否可以点击上一页
    if (hasPreviousPage) {
        previousLi = "<li id=\"previous-page-li\"><a href=\"javascript:;\" onclick=\"prePage(" + prePage + ")\">上一页</a></li>";
    } else {
        previousLi = "<li  id=\"previous-page-li\"><a href=\"javascript:;\" onclick=\"setAlertPage('pre')\">上一页</a></li>";
    }
    $('#pageBar').html();
    var ulStringBegin = "<ul class=\"am-pagination tpl-pagination\">";
    var ulStringEnd = "</ul>";
    $('#pageBar').html(ulStringBegin + previousLi + currentPageCenter + nextLi + ulStringEnd);
}

/**
 * 设置数据列表
 */
function setPermissionDataList(result) {
    var list = '';
    var {code, message, data} = result;
    var html = '';
    data.list.forEach((item, index) => {
        let pid = "\'" + item.pid + "\'";
        html += "<tr class=\"gradeX\">" +
            "  <td>" + item.pname + "</td>" +
            "  <td>" + item.pvalue + "</td>" +
            "  <td>" + item.createdTime + "</td>" +
            "  <td>" +
            "      <div class=\"tpl-table-black-operation\">" +
            "            <a href=\"javascript:;\" onclick=\"editPermission(" + pid + ")\">" +
            "                <i class=\"am-icon-pencil\"></i> 编辑" +
            "            </a>" +
            "            <a href=\"javascript:;\" class=\"tpl-table-black-operation-del\" onclick=\"deletePermission(" + pid + ")\">" +
            "                 <i class=\"am-icon-trash\"></i> 删除" +
            "            </a>" +
            "       </div>" +
            "  </td>" +
            "</tr>";
    })
    $('#user-list-body').html();
    $('#user-list-body').html(html);
}

/**
 *上一页、下一页设置警告
 */
function setAlertPage(flag) {
    if (flag == "next") {
        $('#modal-alert .am-modal-bd').text('没有下一页！');
        $('#modal-alert').modal();
    } else {
        $('#modal-alert .am-modal-bd').text('没有上一页！');
        $('#modal-alert').modal();
    }
}

/**
 * 下一页
 * @param hasNextPage
 * @param prePage
 */
function nexPage(prePage) {
    getMainPermissionList(prePage + 1, 5, usertoken);
}

/**
 * 上一页
 * @param hasPreviousPage
 * @param prePage
 */
function prePage(prePage) {
    getMainPermissionList(prePage - 1, 5, usertoken);
}

/**
 * 新增权限
 */
$('#add-role-buttton').on('click', function () {
    //修改标题为新增用户
    $('#permission-edit-title').html("新增权限");
    $('#pname-input').val("");
    $('#pvalue-input').val("");
    //新增
    $('#add-role-modal').modal();
})


/**
 * 新增、编辑用户提交
 */
$('#adduser-submit-button').on('click', function () {
    //校验信息输入
    if (validateAddUserInput() == true) {
        //信息校验成功
        $.ajax({
            url: '/api/postPermission',
            cache: true,
            type: 'POST',
            dataType: 'json',
            data: postPermission()
        }).then(function (data) {
            // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
            //取消加载动画
            $('#reload-modal-loading').modal('close');
            if (data.code == 200 && data.message == "success") {
                // 返回信息
                $('#add-role-modal').modal("close");
                // 返回信息
                $('#modal-alert .am-modal-bd').text(data.data);
                $('#modal-alert').modal();
            } else {
                $('#add-role-modal').modal("close");
                // 返回信息
                $('#modal-alert .am-modal-bd').text(data.data);
                $('#modal-alert').modal();
            }
        }, function (data) {
            // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
            // 返回信息
            $('#add-role-modal').modal("close");
            $('#modal-alert .am-modal-bd').text('登陆失败！');
            $('#modal-alert').modal();
        });
    }
})

/**
 * 校验输入信息是否正确
 * validateAddUserInput
 */
function validateAddUserInput() {
    var pnamePattern = "[\u4e00-\u9fa5]";
    var pvaluePattern = "^[a-zA-Z]{3,20}$";
    var pname = $('#pname-input').val();
    var pvalue = $('#pvalue-input').val();
    console.log(pname)
    console.log(pname.match(pnamePattern))
    if (pname.match(pnamePattern) == null) {
        $('#pname-alert').css("display", "block");
        return false;
    } else {
        $('#pname-alert').css("display", "none");
    }
    if (pvalue.match(pvaluePattern) == null) {
        $('#pvalue-alert').css("display", "block");
        return false;
    } else {
        $('#pvalue-alert').css("display", "none");
    }
    return true;
}

/**
 * 获取参数
 */
function postPermission() {
    var pname = $('#pname-input').val();
    var pvalue = $('#pvalue-input').val();
    var pid = $('#pid-input').val();

    var data = {
        "pname": pname,
        "pvalue": pvalue,
        "usertoken": usertoken,
        "pid": pid
    }
    return data;
}

/**
 * 刷新页面
 */
$('#reload-buttton').on('click', function () {
    window.location.reload();
})

/**
 * 编辑
 */
function editPermission(pid) {
    //获取对应用户信息
    $.ajax({
        url: '/api/getPermission',
        cache: true,
        type: 'GET',
        dataType: 'json',
        data: {"pid": pid, "usertoken": usertoken}
    }).then(function (data) {
        // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
        //取消加载动画
        $('#reload-modal-loading').modal('close');
        if (data.code == 200 && data.message == "success") {
            // 返回信息并设置到输入框中
            $('#pname-input').val(data.data.pname);
            $('#pvalue-input').val(data.data.pvalue);
            $('#pid-input').val(pid);
            //修改标题为编辑用户
            $('#permission-edit-title').html("编辑权限");
            //新增
            $('#add-role-modal').modal();
        } else {
            // 返回信息
            $('#modal-alert .am-modal-bd').text(data.data);
            $('#modal-alert').modal();
        }
    }, function (data) {
        // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
        // 返回信息
        $('#modal-alert .am-modal-bd').text('登陆失败！');
        $('#modal-alert').modal();
    });
}


/**
 * 删除角色信息
 * @param rid
 */
function deletePermission(pid) {
    $('#delete-confirm').modal({
        relatedTarget: this,
        onConfirm: function (options) {
            //获取对应用户信息
            $.ajax({
                url: '/api/postPermission',
                cache: true,
                type: 'POST',
                dataType: 'json',
                data: {"pid": pid, "usertoken": usertoken}
            }).then(function (data) {
                // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
                //取消加载动画
                $('#reload-modal-loading').modal('close');
                if (data.code == 200 && data.message == "success") {
                    // 返回信息
                    $('#modal-alert .am-modal-bd').text(data.data);
                    $('#modal-alert').modal();
                } else {
                    // 返回信息
                    $('#modal-alert .am-modal-bd').text(data.data);
                    $('#modal-alert').modal();
                }
            }, function (data) {
                // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
                // 返回信息
                $('#modal-alert .am-modal-bd').text('登陆失败！');
                $('#modal-alert').modal();
            });
        },
        onCancel: function () {
            return;
        }
    });

}

/**
 * 退出登陆
 */
$('#loginout-a').on('click', function () {
    //清空session
    localStorage.removeItem('nick');
    localStorage.removeItem('uname');
    //退出
    $.ajax({
        url: '/api/logout',
        cache: true,
        type: 'GET',
        dataType: 'json',
        data: {"usertoken": usertoken}
    }).then(function (data) {
        // Ajax 请求成功，根据服务器返回的信息，设置 validity.valid = true or flase
        //取消加载动画
        $('#reload-modal-loading').modal('close');
        if (data.code == 200 && data.message == "success") {
            // 返回信息
            $('#modal-alert .am-modal-bd').text(data.data);
            $('#modal-alert').modal();
        } else {
            // 返回信息
            $('#modal-alert .am-modal-bd').text(data.data);
            $('#modal-alert').modal();
        }
    }, function (data) {
        // Ajax 请求失败，根据需要决定验证是否通过，然后返回 validity
        // 返回信息
        $('#modal-alert .am-modal-bd').text('登陆失败！');
        $('#modal-alert').modal();
    });
    //清除usertoken
    localStorage.removeItem('usertoken');
    //重定向
    window.location.href = getRootPath();
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
 * 首页跳转
 */
$("#index-a").on("click",function () {
    //获取usertoken
    var usertoken = localStorage.getItem("usertoken");
    //usertoken为空,跳转到登陆页
    if(usertoken == null){
        // 返回信息
        $('#login-modal-alert .am-modal-bd').text("登陆已失效，请重新登陆!");
        $('#login-modal-alert').modal();
        window.location.href = getRootPath()+"/login";
    }
    window.location.href = getRootPath()+"/index?usertoken="+usertoken;
})