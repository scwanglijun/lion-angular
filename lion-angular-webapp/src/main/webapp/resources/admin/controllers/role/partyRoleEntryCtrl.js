/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("partyRoleEntryCtrl", ['$scope', '$modal', 'dbUtils', PartyRoleEntryCtrl]);

function PartyRoleEntryCtrl($scope,$modal, dbUtils) {

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "nameZh", label: "角色名称", type: "text", required: true, placeholder: "角色名称", readonly: true, labelCols: "3"}
            ]
        },
        grid: {
            settings: {
                transCode: "system.role.list",
                autoLoad: true,
                page: {pageSize:10},
                showCheckBox: true
            },
            header: [
                {name: "角色名称(英文)", width: "18%", field: "nameEn"},
                {name: "角色名称(中文)", width: "18%", field: "nameZh"},
                {name: "描述", width: "18%", field: "description"},
                {name: "可编辑", width: "10%", field: "editable"},
                {name: "创建时间", width: "18%", field: "createdDate"},
                {name: "更新时间", width: "18%", field: "updatedDate"}
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
                name: "新增", class: "btn-success", icon: "tianjia", click: function () {
                    openModal();
                }
            },{
                name: "编辑", class: "btn-info", icon: "luru", click: function (row) {
                    editModal(row);
                }
            },{
                name: "删除", class: "btn-danger", icon: "shanchu", click: function () {
                    quit();
                }
            },{
                name: "Excel", class: "btn-primary", icon: "queding", click: function (row) {
                    imports();
                }
            },{
                name: "授权", class: "btn-primary", icon: "queding", click: function (row) {
                    openAuthModal(row);
                }
            }]
        }
    };

    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};
    //打开modal
    function openModal(source) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'views/admin/system/role/test.html',
            controller: 'testCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
                source: function () {
                    return source;
                }
            }
        });
        instance.result.then(function () {
            $scope.lionFormGrid.reLoadData();
        });
    }


    //编辑modal
    function editModal(row) {
        if($scope.lionFormGrid.getAllSelectRows().length == 0){
            dbUtils.info('请选择要编辑的行数据');
        }else if($scope.lionFormGrid.getAllSelectRows().length > 1){
            dbUtils.info('请选择一行数据');
        }else{
            //console.log($scope.lionFormGrid.getAllSelectRows());
            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/role/test.html',
                controller: 'testCtrl',
                size: "md",
                backdrop: "static",
                resolve: {
                    source: function(){
                        return $scope.lionFormGrid.getAllSelectRows();
                    }
                }
            });
            instance.result.then(function () {
                $scope.lionFormGrid.reLoadData();
            });


        };
    }


    /**
     * 删除操作
     */
    function quit() {
        var selectRows = $scope.lionFormGrid.getAllSelectRows();
        if (selectRows.length === 0) {
            dbUtils.info('请选择要删除的行数据');
        }else{
            console.log(selectRows);
            var ids = dbUtils.getFieldArray(selectRows, "id");
            dbUtils.confirm("确定要对所选角色进行<span style='color: red'>删除</span>操作?", function () {
                dbUtils.post('system.role.delete', {'ids': ids}, function (data) {
                    console.dir(data);
                    if (data.code==='200') {
                        dbUtils.success("角色删除成功！!");
                    } else {
                        dbUtils.error("删除失败!");
                    }
                    $scope.lionFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error("角色删除处理异常!" + error);
                });
            });
        }
    }

    /**
     * 导出操作
     */
    function imports() {
        //dbUtils.post('system.role.delete', {'ids': ""}, function (data) {
        //    console.dir(data);
        //    if (data.code==='200') {
        //        dbUtils.success("角色删除成功！!");
        //    } else {
        //        dbUtils.error("删除失败!");
        //    }
        //    $scope.lionFormGrid.reLoadData();
        //}, function (error) {
        //    dbUtils.error("角色删除处理异常!" + error);
        //});
    }



//授权

    function openAuthModal(row) {
        if($scope.lionFormGrid.getAllSelectRows().length == 0){
            dbUtils.info('请选择要授权的行数据');
        }else if($scope.lionFormGrid.getAllSelectRows().length > 1){
            dbUtils.info('请选择一行数据');
        }else{
            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/role/roleAuth.html',
                controller: 'testCtrl',
                size: "md",
                backdrop: "static",
                resolve: {
                    source: function(){
                        return row;
                    }
                }
            });
            instance.result.then(function () {
                $scope.lionFormGrid.reLoadData();
            });
        };
    }

}