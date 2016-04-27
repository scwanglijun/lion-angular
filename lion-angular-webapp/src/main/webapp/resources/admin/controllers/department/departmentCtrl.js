/**
 * Created by wash on 16/3/30.
 */
var DBApp = angular.module('DBApp');

//DBApp.controller("resourceCtrl", ['$scope', 'dbUtils', '$timeout', ResourceCtrl]);
DBApp.controller("departmentCtrl", ['$scope', 'dbUtils','dbImService', '$timeout', departmentCtrl]);
function departmentCtrl($scope, dbUtils, dbImService,$timeout) {
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
    doGetDepartmentTreeData();
    //form 初始化
    $scope.dbTree.itemClickEvent = function (item) {

        if (!item['parentCode']) {//资源管理不允许编辑
            return;
        }

        var originData = angular.copy(formData);
        dbUtils.post("system.department.departmentGet", {id: item['parentCode']}, function (data) {
            originData = angular.extend({}, originData, item.attr);
            $scope.dbForm.setOriginData(originData);

            //是否可编辑
            //if(item.attr.editable){
            //    $scope.dbForm.setFormDataField("editable",true);
            //}

            $scope.dbForm.setFormDataField("parentDepartmentName",data.nameZh);
            $scope.dbForm.setFormDataField("parentDepartmentId",data.id);

        });
    };

    //!!FORM--START!!
    $scope.dbForm = {
        settings: {transCode: "system.department.editor", cols: 3, showClose: false},
        title: {label: "部门", icon: "fujiaxinxi"},
        sections:
            [{
            sectionTitle: {show: true, icon: "gengduo", label: "部门"},
            fields:
                [
                {name: "parentDepartmentName", label: "上级部门", type: "departmentTree", required: true, placeholder: "请选择上级部门", readonly: true},
                {name: "nameZh", label: "部门名称（中文）", type: "text", required: true, placeholder: "请输入部门中文名称"},
                {name: "nameEn", label: "部门名称（英文）", type: "text", required: true, placeholder: "请输入部门英文名称"},
                {name: "departmentNo", label: "部门编号", type: "text", required: false, placeholder: "请输入部门编号"},
                {name: "description", label: "描述", type: "text", required: false, placeholder: "请输入部门描述"},
                {name: "editable", label: "是否可编辑", type: "checkbox"}
                ]
        }]
    };

    //!!FORM-END!!
    //表单处理事件
    $scope.dbForm.events = {
        "afterSubmit": function (retval) {
            doGetDepartmentTreeData();
            dbUtils.success("修改成功", "提示");
        }
    };

    //机构树选择后的回调事件
    $scope.dbDepartmentTree = {settings: {noCache: true}};
    $scope.dbDepartmentTree.onResourceSelected = function (item) {

        $scope.dbForm.setFormDataField("parentDepartmentName",item.nameZh);
        $scope.dbForm.setFormDataField("parentDepartmentId",item.id);
        console.log(item)
    };
    //临时解决编辑按钮不出现的问题
    $timeout(function () {
        $scope.dbForm.setOriginData(formData);
    }, 500);

    function doGetDepartmentTreeData() {
        dbUtils.post("system.department.list", {}, function (resourceData) {
            initDbDepartmentTree(resourceData);
        });
    }


    //初始化树形结构的数据


    function initDbDepartmentTree(resourceData) {
        //构造树结构
        //1.查找root
        var root = null;
        angular.forEach(resourceData, function (item) {
            if (angular.isUndefined(item['parentDepartmentId']) || !item['parentDepartmentId']) {
                root = {text: item['nameZh'], parentCode: item['parentDepartmentId'], code: item['id'], attr: item, departmentId: item['id'], opened: true,treeId: item['id']};
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
                if (item['parentDepartmentId'] == parentCode) {
                    //var iconClass = item['isLeaf'] == true ? 'icon-state-warning' : 'icon-state-success';
                    var iconClass = 'icon-state-success';
                    var o = {text: item['nameZh'], parentCode: item['parentDepartmentId'], code: item['id'], attr: item, departmentId: item['id'], children: [], treeId: item['id'], canSelect: true};
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