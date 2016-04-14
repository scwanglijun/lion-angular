/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("sessionMonitorCtrl", ['$scope', '$modal', 'dbUtils',sessionMonitorCtrl]);

function sessionMonitorCtrl($scope, $modal, dbUtils) {
    var formGridOptions = {
        form: {
            settings: {
                cols:0
            },
            fields: {}
        },
        grid: {
            settings: {
                transCode: "system.session.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "会话ID", width: "18%", field: "id"},
                {name: "用户名", width: "10%", field: "username"},
                {name: "登录IP", width: "10%", field: "host"},
                {name: "状态", width: "10%", field: "valid"},
                {name: "TimeOut", width: "10%", field: "timeout"},
                {name: "最后访问时间", width: "17%", field: "lastAccessTime"},
                {name: "开始时间", width: "10%", field: "startTimestamp"},
                {name: "是否过期", width: "15%", field: "expired"}
            ],
            rowOperation: {show: false}
        }
    }
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
                "createdDateFormat":function(value,row){
                    return new Date(parseInt(row) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                }
            },
            operationEvents: [{
                name: "刷新", class: "btn-success", icon: "shuaxin", click: function () {
                    console.log();
                }
            },{
                name: "Excel", class: "btn-info", icon: "excel", click: function (row) {
                    console.log(row);
                }
            },{
                name: "强制退出", class: "btn-danger", icon: "tuichu", click: function () {
                    console.log();
                }
            }]
        }
    };

    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};

    //机构树选择后的回调事件
    $scope.dbOrgTree = {settings: {noCache: true, showDivision: true, showDepartment: true}};
    $scope.dbOrgTree.onOrgSelected = function (item) {
        $scope.lionFormGrid.setFormDataField("organizationName", item['orgNamePath']);
        $scope.lionFormGrid.setFormDataField("organizationId", item['orgId']);
        $scope.lionFormGrid.setFormDataField("departmentId", item['departId']);
    };
}