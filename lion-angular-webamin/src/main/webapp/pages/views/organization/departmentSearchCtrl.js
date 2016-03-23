/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("departmentSearchCtrl", ['$scope', '$window','dbImService' ,'$modal', DepartmentSearchCtrl]);

function DepartmentSearchCtrl($scope,$window,dbImService,$modal) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form:{
            settings: {
                cols: 2
            },
            fields:[
                {
                    name: "parentOrgNamePath",
                    label: "直属机构",
                    type: "orgTree",
                    required: true,
                    placeholder: "请选择上级机构",
                    readonly:true
                }
            ]
        },
        grid: {
            settings: {
                transCode: "departmentPage",
                autoLoad: true,
                showCheckBox:false
            },
            header: [
                {name: "直属机构", width: "10%", field: "flowType_name"},
                {name: "部门代码", width: "5%", field: "orgCode"},
                {name: "部门名称", width: "10%", field: "orgName"},
                {name: "部门级别", width: "5%", field: "deptLevel_"},
                {name: "部门类型", width: "5%", field: "deptType_"},
                {name: "成立日期", width: "10%", field: "createDate"},
                {name: "负责人", width: "10%", field: "principalName"},
                {name: "联系电话", width: "10%", field: "conPhone"},
            ],
            rowOperation: {show:false,width: "17%"}
        }
    };
    //!!formGridOptions-END!!

    var formGridEvents = {
        grid: {
            fieldEvents: {
                "applyDateFormat":function(value,row){
                    if(value!=null){
                        return  value.substring(0,10);
                    }
                },
                "auditDateFormat":function(value,row){
                    if(value!=null){
                        return  value.substring(0,10);
                    }
                },
                "businessIdClick": function (row) {
                    //显示审核详细信息
                },
                "descClick":function(row){

                }
            }
        }
    };
    //加载字典数据
    var gridLoadedFn=function(rows){
        angular.forEach(rows, function (row) {
            //业务审核类型
            dbImService.queryByJSON('deptLevel', function (dicts) {
                angular.forEach(dicts, function (dict) {
                    if (dict.value == row['deptLevel']) {
                        row['deptLevel_'] = dict.name;
                    }
                })
            });
            //审核状态
            dbImService.queryByJSON('deptType',function(dicts){
                angular.forEach(dicts,function(dict){
                    if(dict.value==row['deptType']){
                        row['deptType_']=dict.name;
                    }
                });
            });
        });
    };
    //构建dbFromGrid
    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents,gridLoaded:gridLoadedFn};

    //机构树选择后的回调事件
    $scope.dbOrgTree = {settings:{noCache:true,showDepartment:false}};
    $scope.dbOrgTree.onOrgSelected = function(item){
        $scope.dbFormGrid.setFormDataField("parentOrgNamePath",item.orgNamePath);
        $scope.dbFormGrid.setFormDataField("parentOrgPath",item.orgPath);
        $scope.dbFormGrid.setFormDataField("parentOrgId",item.orgId);
        $scope.dbFormGrid.setFormDataField("parentOrgType",item.orgType);
    };
}