/**
 * Created by wash on 16/3/30.
 */
var DBApp = angular.module('DBApp');

//DBApp.controller("resourceCtrl", ['$scope', 'dbUtils', '$timeout', ResourceCtrl]);
DBApp.controller("resourceCtrl", ['$scope', 'dbUtils','dbImService', '$timeout', ResourceCtrl]);
function ResourceCtrl($scope, dbUtils, dbImService,$timeout) {
    var formData = {
       code: '',
        parentNamePath: '',
        type: '',
        isMenu: '',
        permission: '',
        sortNo: null
    };
    //dbTree 初始化数据
    $scope.dbTree = {settings: {useCheckBox: false, treeScrollHeight: "350px", noCache: true}};

    doGetResourceTreeData();

    //form 初始化
    $scope.dbTree.itemClickEvent = function (item) {
        if (!item['parentCode']) {//资源管理不允许编辑
            return;
        }
        var originData = angular.copy(formData);
        dbUtils.post("system.resource.resourceGet", {id: item['parentCode']}, function (data) {
            originData = angular.extend({}, originData, item.attr);
            //配置资源类型
            dbImService.bindByJSON($scope,'resourcetype',function(data){
            });
            //配置target
            dbImService.bindByJSON($scope,'resourcetarget',function(data){
            });



            //配置editable
            dbImService.bindByJSON($scope,'resourceeditable',function(data){
            });
            $scope.dbForm.setOriginData(originData);

            $scope.dbForm.setFormDataField("parentResourceName",data.nameZh);
            $scope.dbForm.setFormDataField("parentResourceId",data.id);

        });
    };

    //!!FORM--START!!
    $scope.dbForm = {
        settings: {transCode: "system.resource.resourceEditor", cols: 3, showClose: false},
        title: {label: "资源", icon: "fujiaxinxi"},
        sections: [{
            sectionTitle: {show: true, icon: "gengduo", label: "资源"},
            fields: [
                {name: "parentResourceName", label: "上级资源", type: "resourceTree", required: true, placeholder: "请选择上级菜单资源", readonly: true},
                {name: "type", label: "资源类型", type: "select", dropDownItemType: "json", dropDownItem: "resourcetype", required: true},
                {name: "nameZh", label: "资源名称（中文）", type: "text", required: true, placeholder: "请输入资源中文名称"},
                {name: "nameEn", label: "资源名称（英文）", type: "text", required: true, placeholder: "请输入资源英文名称"},
                {name: "path", label: "路径", type: "text", required: true, placeholder: "请输入资源路径"},
                {name: "target", label: "Target", type: "select", dropDownItemType: "json", dropDownItem: "resourcetarget", required: true},
                {name: "permission", label: "权限配置", type: "text", required: true, placeholder: "请输入权限配置"},
                {name: "seqNum", label: "显示顺序", type: "text", required: true, placeholder: "请输入显示顺序"},
                {name: "description", label: "资源描述", type: "text", required: false, placeholder: "请输入资源描述"},
                {name: "editable", label: "是否可编辑", type: "checkbox"},
                {name: "icon", label: "资源图标", type: "select", dropDownItemType: "json", dropDownItem: "resourceicon", required: true}
            ]
        }]
    };

    //!!FORM-END!!
    //表单处理事件
    $scope.dbForm.events = {
        "afterSubmit": function (retval) {
            doGetResourceTreeData();
            dbUtils.success("修改成功", "提示");
        }
    };

    //机构树选择后的回调事件
    $scope.dbResourceTree = {settings: {noCache: true}};
    $scope.dbResourceTree.onResourceSelected = function (item) {
        $scope.dbForm.setFormDataField("parentResourceName",item.nameZh);
        $scope.dbForm.setFormDataField("parentResourceId",item.id);
    };
    //临时解决编辑按钮不出现的问题
    $timeout(function () {
        $scope.dbForm.setOriginData(formData);
    }, 500);

    function doGetResourceTreeData() {
        dbUtils.post("system.resource.list", {}, function (resourceData) {
            initDbResourceTree(resourceData);
        });
    }


    //初始化树形结构的数据


    function initDbResourceTree(resourceData) {
        //构造树结构
        //1.查找root
        var root = null;
        angular.forEach(resourceData, function (item) {
            if (angular.isUndefined(item['parentResourceId']) || !item['parentResourceId']) {
                root = {text: item['nameZh'], parentCode: item['parentResourceId'], code: item['id'], attr: item, resourceId: item['id'],types:item['type'],paths:item['path'], opened: true, iconClass: "icon-state-warning", treeId: item['id']};
                return false;
            }
        });
        if (!root) {
            console.log("db-org-tree root is null");
            return;
        }
        //2.递归循环所有节点,将节点加入到父节点当中
        function getChildren(parentCode) {
            var child = [];

           angular.forEach(resourceData, function (item) {
                if (item['parentResourceId'] == parentCode) {
                    //var iconClass = item['isLeaf'] == true ? 'icon-state-warning' : 'icon-state-success';
                    var iconClass = 'icon-state-success';
                    var o = {text: item['nameZh'], parentCode: item['parentResourceId'], code: item['id'], attr: item, resourceId: item['id'],type:item['type'],path:item['path'], children: [], iconClass: iconClass, treeId: item['id'], canSelect: true};
                    child.push(o);
                }
            });
            angular.forEach(child, function (item) {
                item.children = getChildren(item['code']);
            });
            return child;
        }

        //生成树结构数据
        root.children = getChildren(root['code']);
        //渲染树结构
        if ($scope.dbTree) {
            $scope.dbTree.setData([root]);
        } else {
            $scope.dbTree = {
                data: [root]
            }
        }
    }
}