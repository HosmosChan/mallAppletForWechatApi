$.ajaxSetup({
	cache : false,
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		if (code == 400) {
			layer.msg(message);
		} else if (code == 401) {
			layer.msg('未登録');
		} else if (code == 403) {
			console.log("未授權:" + message);
			layer.msg('未授權');
		} else if (code == 500) {
			layer.msg('系統錯誤：' + message);
		}
	}
});

function buttonDel(data, permission, permissions){
	if(permission != ""){
		if ($.inArray(permission, permissions) < 0) {
			return "";
		}
	}
	
	var btn = $("<button class='layui-btn layui-btn-xs' title='刪除' onclick='del(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}

function buttonEdit(href, permission, permissions){
	if(permission != ""){
		if ($.inArray(permission, permissions) < 0) {
			return "";
		}
	}
	
	var btn = $("<button class='layui-btn layui-btn-xs' title='編輯' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe642;</i></button>");
	return btn.prop("outerHTML");
}


function deleteCurrentTab(){
	var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
	parent.active.tabDelete(lay_id);
}
