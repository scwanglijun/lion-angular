/**
 * Created by wash on 16/3/28.
 */

var DBApp = angular.module('DBApp');

DBApp.controller("addGroupUserCtrl", ['$scope','$modal', 'dbUtils', addGroupUserCtrl]);

//显示数据
function addGroupUserCtrl($scope,$modal,dbUtils){
    $scope.showPortlet = true;
    var params={
        id:$scope.data.id,
        username:$scope.data.username,
        employeeCode:$scope.data.employeeCode,
        email:$scope.data.email,
    };

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [],
            hiddenParams:params
        },
        grid: {
            settings: {
                transCode: "system.user.authGroupList",
                autoLoad: true,
                page: {pageSize: 3},
                showCheckBox: true
            },
            header: [
                {name: "用户名（英文）", width: "18%", field: "nameEn"},
                {name: "用户名(中文)", width: "18%", field: "nameZh"},
                {name: "描述", width: "18%", field: "description"}
            ],
            rowOperation: {show: false}
        }
    }
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {}
    };

    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};

    var rows = $scope.lionFormGrid.rows;
    $scope.lionFormGrid.gridLoaded = function(rows){
        angular.forEach($scope.lionFormGrid.rows, function (row) {
            if (row.userId!=null) {
                row.checked=true;
            }
        });
    }


    //授权
    $scope.submitDialog = function () {
        $scope.submited = true;
        var id=$scope.data.id;
        //已选择的对象
        var selecteds=[];
        //目标的对象
        var auths=[];

        angular.forEach($scope.lionFormGrid.getAllSelectRows(), function (selectedsRow) {
            selecteds.push(selectedsRow.id);
        });

        angular.forEach($scope.lionFormGrid.rows, function (authsRow) {
            auths.push(authsRow.id);
        });

        var reqBody = {
            id:id,
            selecteds:selecteds,
            auths:auths
        }
        dbUtils.post('system.user.addGroupUser',reqBody,function (data) {
            dbUtils.success('操作成功!');
        }, function (error) {
            dbUtils.error(error);
        });
    };

    ////机构树选择后的回调事件
    //$scope.dbOrgTree = {settings: {noCache: true, showDivision: true, showDepartment: true}};
    //$scope.dbOrgTree.onOrgSelected = function (item) {
    //    $scope.lionFormGrid.setFormDataField("organizationName", item['orgNamePath']);
    //    $scope.lionFormGrid.setFormDataField("organizationId", item['orgId']);
    //    $scope.lionFormGrid.setFormDataField("departmentId", item['departId']);
    //};

}