function showDictSelect(id, type, all) {
    var data = getDict(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>" + all + "</option>");
    }

    $.each(data, function (k, v) {
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

function showCategoryByAppId(id, type, all, param) {
    var data = getCategoryByAppId(type, param);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>" + all + "</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function getCategoryByAppId(type, param) {
    var v = sessionStorage[type];
    if (v == null || v == "" || v == "{}") {
        $.ajax({
            type: 'get',
            url: '/productsShown/category/getCategoryByAppId',
            data: 'appId=' + param,
            async: false,
            success: function (data) {
                v = {};
                $.each(data, function (i, d) {
                    v[d.categoryId] = d.categoryName;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}

function showCompany(id, type, all) {
    var data = getCompany(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>" + all + "</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function getCompany(type) {
    var v = sessionStorage[type];
    if (v == null || v == "" || v == "{}") {
        $.ajax({
            type: 'get',
            url: '/applet/getCompany',
            async: false,
            success: function (data) {
                v = {};
                $.each(data, function (i, d) {
                    v[d.companyId] = d.company;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}

function showCompanyByAppId(id, type, all, param) {
    var data = getCompanyByAppId(type, param);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>" + all + "</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function getCompanyByAppId(type, param) {
    var v = sessionStorage[type];
    if (v == null || v == "" || v == "{}") {
        $.ajax({
            type: 'get',
            url: '/applet/getCompanyByAppId',
            data: 'appId=' + param,
            async: false,
            success: function (data) {
                v = {};
                $.each(data, function (i, d) {
                    v[d.companyId] = d.company;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}