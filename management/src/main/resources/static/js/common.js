//form序列化為json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//將額外的form添加入json
$.fn.addSerializeObject = function (form) {
    var o = form;
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//獲取url后的參數值
function getUrlParam(key) {
    var href = window.location.href;
    var url = href.split("?");
    if (url.length <= 1) {
        return "";
    }
    var params = url[1].split("&");
    for (var i = 0; i < params.length; i++) {
        var param = params[i].split("=");
        if (key == param[0]) {
            return param[1];
        }
    }
}

// 檢查登録狀態
function loginInfo() {
    var user = "";
    $.ajax({
        type: 'get',
        url: '/login',
        async: false,
        success: function (data) {
            if (data != null && data != "") {
                user = data;
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            var msg = xhr.responseText;
            var response = JSON.parse(msg);
            $("#info").html(response.message);
        }
    });
    return user;
}