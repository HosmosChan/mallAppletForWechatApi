function checkPermission() {
    var permissions = [];
    $.ajax({
        type: 'get',
        url: '/management/permission/owns',
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data) {
            permissions = data;
            $("[permission]").each(function () {
                var per = $(this).attr("permission");
                if ($.inArray(per, data) < 0) {
                    $(this).hide();
                }
            });
        }
    });
    return permissions;
}