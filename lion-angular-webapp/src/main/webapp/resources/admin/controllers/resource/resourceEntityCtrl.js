/**
 * Created by LiXiaoHao on 16/4/12.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("resourceEntryCtrl", ['$scope', 'dbUtils', function ($scope, dbUtils) {

    //!!FORM--START!!
    $scope.dbForm = {
        settings: {transCode: "system.resource.add", cols: 3, showClose: false},
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
        beforeSubmit: function (reqBody) {
        },
        "afterSubmit": function (retval) {
            dbUtils.success("添加成功", "提示");
        }
    };
    //机构树选择后的回调事件
    $scope.dbResourceTree = {settings: {noCache: true}};
    $scope.dbResourceTree.onResourceSelected = function (item) {
        $scope.dbForm.setFormDataField("parentResourceName",item.nameZh);
        $scope.dbForm.setFormDataField("parentResourceId",item.id);
    };

}]);