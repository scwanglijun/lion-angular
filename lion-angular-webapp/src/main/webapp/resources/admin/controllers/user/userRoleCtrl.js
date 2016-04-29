/**
 * Created by wash on 16/3/28.
 */

var DBApp = angular.module('DBApp');

DBApp.controller("userRoleCtrl", ['$scope','$modal', 'dbUtils', userRoleCtrl]);

//显示数据
function userRoleCtrl($scope,$modal,dbUtils){
    $scope.showPortlet = true;
    $scope.gridTile="已关联角色";

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
                transCode: "system.user.userRole",
                autoLoad: true,
                page: {pageSize: 3},
                showCheckBox: false
            },
            header: [
                {name: "角色名（英文）", width: "18%", field: "nameEn"},
                {name: "角色名(中文)", width: "18%", field: "nameZh"},
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



}