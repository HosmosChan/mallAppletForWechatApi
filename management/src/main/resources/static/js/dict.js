function showDictSelect(id, type, all) {
	var data = getDict(type);
	var select = $("#" + id);
	select.empty();

	if (all != undefined || all) {
		select.append("<option value=''>"+ all +"</option>");
	}

	$.each(data, function(k, v) {
		select.append("<option value ='" + k + "'>" + v + "</option>");
	});

	return data;
}

function getDict(type) {
    var v = sessionStorage[type];
    if (v == null || v == "" || v == "{}") {
        $.ajax({
            type: 'get',
            url: '/dict?type=' + type,
            async: false,
            success: function (data) {
                v = {};
                $.each(data, function (i, d) {
                    v[d.key] = d.value;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}
