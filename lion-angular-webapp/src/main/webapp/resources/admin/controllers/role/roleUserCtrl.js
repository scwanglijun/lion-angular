/**
 * Created by wash on 16/3/28.
 */

var DBApp = angular.module('DBApp');

DBApp.controller("roleUserCtrl", ['$scope','$modal', 'dbUtils', roleUserCtrl]);

//显示数据
function roleUserCtrl($scope,dbUtils,$modal){

    $scope.showPortlet = true;
    $scope.gridTile="已关联用户";

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [],
            hiddenParams:$scope.data
        },
        grid: {
            settings: {
                transCode: "system.role.roleUserCtrl",
                autoLoad: true,
                page: {pageSize: 3},
                showCheckBox: false
            },
            header: [
                {name: "用户名（英文）", width: "18%", field: "username"},
                {name: "用户名(中文)", width: "18%", field: "realnameZh"},
                {name: "员工", width: "18%", field: "employeeCode"}
            ],
            rowOperation: {show: false}
        }
    }
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {}
    };
    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};


}