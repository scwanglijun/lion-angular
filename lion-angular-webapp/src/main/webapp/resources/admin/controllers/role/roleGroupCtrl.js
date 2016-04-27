/**
 * Created by wash on 16/3/28.
 */

var DBApp = angular.module('DBApp');

DBApp.controller("roleGroupCtrl", ['$scope','$modal','dbUtils','dbImService', roleGroupCtrl]);


//显示数据
function roleGroupCtrl($scope,$modal,dbUtils,dbImService){

    $scope.showPortlet = true;
    $scope.gridTile="已关联用户组";
    //$scope.showButton=true;
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
                transCode: "system.role.roleGroupCtrl",
                autoLoad: true,
                page: {pageSize:3},
                showCheckBox: false
            },
            header: [
                {name: "用户组名称(英文)", width: "18%", field: "nameEn"},
                {name: "用户组名称(中文)", width: "18%", field: "nameZh"},
                {name: "描述", width: "18%", field: "description"}
            ],
            rowOperation: {show: false}
        }
    }
    //!!formGridOptions-END!!

        //hiddenParams:$scope.data
    var formGridEvents = {
        grid: {}
    };



    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};


}
