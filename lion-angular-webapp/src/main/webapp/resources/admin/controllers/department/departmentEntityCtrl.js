/**
 * Created by LiXiaoHao on 16/4/12.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("departmentEntryCtrl", ['$scope', 'dbUtils', function ($scope, dbUtils) {

    //!!FORM--START!!
    $scope.dbForm = {
        settings: {transCode: "system.department.add", cols: 3, showClose: false},
        title: {label: "部门", icon: "fujiaxinxi"},
        sections: [{
            sectionTitle: {show: true, icon: "gengduo", label: "部门"},
            fields: [
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
        beforeSubmit: function (reqBody) {
        },
        "afterSubmit": function (retval) {
            dbUtils.success("添加成功", "提示");
        }
    };
    //机构树选择后的回调事件
    $scope.dbDepartmentTree = {settings: {noCache: true}};
    $scope.dbDepartmentTree.onResourceSelected = function (item) {

        $scope.dbForm.setFormDataField("parentDepartmentName",item.nameZh);
        $scope.dbForm.setFormDataField("parentDepartmentId",item.id);
        console.log(item)
    };

}]);