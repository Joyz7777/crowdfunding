// 生成树形结构的函数
function generateTree() {
    // 1.准备生成树形结构的JSON数据，数据的来源是发送Ajax请求得到
    $.ajax({
        "url": "menu/get/list.json",
        "type": "post",
        "dataType": "json",
        "success": function (response) {
            var result = response.result;
            if (result == "SUCCESS") {

                // 2.创建JSON对象用于存储对zTree所做的设置
                var setting = {
                    "view": {
                        "addDiyDom": myAddDiyDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": myRemoveHoverDom
                        // },
                        // "data": {
                        //     "key": {
                        //         "url": "maomi"
                    },
                    "data": {
                        "key": {
                            "url": "maomi"
                        }
                    }
                };

                // 3.从响应体中获取用来生成树形结构的JSON数据
                var zNodes = response.data;

                // 4.初始化树形结构
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }

            if (result == "FAILED") {
                layer.msg(response.message);
            }
        }
    });
}


function myRemoveHoverDom(treeId, treeNode) {

    // 拼接按钮组的id
    var btnGroupId = treeNode.tId + "_btnGrp";

    // 移除对应的元素
    $("#" + btnGroupId).remove();

}

function myAddDiyDom(treeId, treeNode) {

    // treeId是整个树形结构附着的ul标签的id
    console.log("treeId=" + treeId);

    //拿到tid
    var spanId = treeNode.tId + "_ico";

    $("#" + spanId).removeClass().addClass(treeNode.icon);

}

function myAddHoverDom(treeId, treeNode) {

    // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
    // 按钮组出现的位置：节点中treeDemo_n_a超链接的后面

    // 为了在需要移除按钮组的时候能够精确定位到按钮组所在span，需要给span设置有规律的id
    var btnGroupId = treeNode.tId + "_btnGrp";

    // 判断一下以前是否已经添加了按钮组
    if ($("#" + btnGroupId).length > 0) {
        return;
    }

    // 准备各个按钮的HTML标签
    var addBtn = "<a id='" + treeNode.id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='" + treeNode.id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='" + treeNode.id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";

    // 获取当前节点的级别数据
    var level = treeNode.level;

    // 声明变量存储拼装好的按钮代码
    var btnHTML = "";

    // 判断当前节点的级别
    if (level == 0) {
        // 级别为0时是根节点，只能添加子节点
        btnHTML = addBtn;
    }

    if (level == 1) {
        // 级别为1时是分支节点，可以添加子节点、修改
        btnHTML = addBtn + " " + editBtn;

        // 获取当前节点的子节点数量
        var length = treeNode.children.length;

        // 如果没有子节点，可以删除
        if (length == 0) {
            btnHTML = btnHTML + " " + removeBtn;
        }

    }

    if (level == 2) {
        // 级别为2时是叶子节点，可以修改、删除
        btnHTML = editBtn + " " + removeBtn;
    }

    // 找到附着按钮组的超链接
    var anchorId = treeNode.tId + "_a";

    // 执行在超链接后面附加span元素的操作
    $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + btnHTML + "</span>");

}