/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("partyRoleEntryCtrl", ['$scope', '$modal', 'dbUtils', PartyRoleEntryCtrl]);

function PartyRoleEntryCtrl($scope, $modal, dbUtils) {
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
                page: {pageSize: 10},
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
            $scope.dbFormGrid.reLoadData();
        });
    }


    //编辑modal
    function editModal(row) {
        if($scope.lionFormGrid.getAllSelectRows().length == 0){
            dbUtils.info('请选择要编辑的行数据');
        }else if($scope.lionFormGrid.getAllSelectRows().length > 1){
            dbUtils.info('请选择一行数据');
        }else{

            console.log($scope.lionFormGrid.getAllSelectRows());

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
                $scope.dbFormGrid.reLoadData();
            });


            /*dbUtils.post("roleModifyGet", {id: row['id']}, function (data) {
             var instance = $modal.open({
             animation: true,
             templateUrl: 'views/admin/system/role/test.html',
             controller: 'roleEditCtrl',
             size: "md",
             backdrop: "static",
             resolve: {
             source: function () {
             return data;
             }
             }
             });
             instance.result.then(function () {
             $scope.dbFormGrid.reLoadData();
             });
             });*/
        };
    }


    /**
     * 删除操作
     */
    function quit() {
        var selectRows = $scope.lionFormGrid.getAllSelectRows();
        if (selectRows.length === 0) {
            return;
        }
        var ids = dbUtils.getFieldArray(selectRows, "id");
        dbUtils.confirm("确定要对所选角色进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('partyRoleQuit', {'ids': ids}, function (data) {
                if (data) {
                    dbUtils.error(data + "以上角色不能删除!")
                } else {
                    dbUtils.success("角色删除成功！!");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("角色删除处理异常!" + error);
            });
        });
    }

    /**
     * 查看审核记录
     * @param currentRecord
     */
    /*function auditStatusHistory(currentRecord) {
        $modal.open({
            animation: true,
            templateUrl: 'views/roles.json/partNoAuditHistoryView.html',
            controller: 'partNoAuditHistoryCtrl',
            size: "lg",
            backdrop: "static",
            resolve: {
                source: function () {
                    return currentRecord;
                }
            }
        });
    }*/


}