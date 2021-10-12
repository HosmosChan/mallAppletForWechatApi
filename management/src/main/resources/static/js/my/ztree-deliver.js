function getDeliverTree() {
    var root = {
        id: 0,
        name: "deliverWay",
        open: true,
    };
    $.ajax({
        type: 'get',
        url: '/productsShown/good/deliverWay/all',
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data) {
            var length = data.length;
            var deliverWay = [];
            for (var i = 0; i < length; i++) {
                var d = data[i];
                var node = createNode(d);
                deliverWay[i] = node;
            }
            root.children = deliverWay;
        }
    });
    return root;
}

function initDeliverWayDatas(goodId) {
    $.ajax({
        type: 'get',
        url: '/productsShown/good/getDeliverWayByGoodId?goodId=' + goodId,
        success: function (data) {
            var length = data.length;
            var ids = [];
            for (var i = 0; i < length; i++) {
                ids.push(data[i]['id']);
            }
            initDeliverCheck(ids);
        }
    });
}

function initDeliverCheck(ids) {
    var treeObj = $.fn.zTree.getZTreeObj("deliverWay");
    var length = ids.length;
    if (length > 0) {
        var node = treeObj.getNodeByParam("id", 0, null);
        treeObj.checkNode(node, true, false);
    }
    for (var i = 0; i < length; i++) {
        var node = treeObj.getNodeByParam("id", ids[i], null);
        treeObj.checkNode(node, true, false);
    }
}

function getCheckedDeliverIds() {
    var treeObj = $.fn.zTree.getZTreeObj("deliverWay");
    var nodes = treeObj.getCheckedNodes(true);
    var length = nodes.length;
    var ids = [];
    for (var i = 0; i < length; i++) {
        var n = nodes[i];
        var id = n['id'];
        ids.push(id);
    }
    return ids;
}

function createNode(d) {
    var id = d['id'];
    var name = d['deliverWay'];
    var node = {
        open: true,
        id: id,
        name: name
    };
    return node;
}

function getSettting() {
    var setting = {
        check: {
            enable: true,
            chkboxType: {
                "Y": "ps",
                "N": "ps"
            }
        },
        async: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {
            onCheck: zTreeOnCheck
        }
    };
    return setting;
}
function zTreeOnCheck(event, treeId, treeNode) {
//	console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked
//			+ "," + treeNode.pId);
//	console.log(JSON.stringify(treeNode));
}