var DBApp = angular.module('DBApp');

DBApp.controller("usergroupListCtrl", ['$scope', '$modal', 'dbUtils', usergroupListCtrl]);

function usergroupListCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {
                    name: "nameZh",
                    label: "用户组名称",
                    type: "text",
                    required: true,
                    placeholder: "用户组名称",
                    readonly: true,
                    labelCols: "3"
                },

            ]
        },
        grid: {
            settings: {
                transCode: "system.usergroup.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [

                {name: "用户组(中文)", width: "18%", field: "nameZh"},
                {name: "用户组(英文)", width: "18%", field: "nameEn"},
                {name: "描述", width: "18%", field: "description"},
                {name: "可编辑", width: "10%", field: "editable"},
                {name: "创建时间", width: "18%", field: "createdDate"},
                {name: "更新时间", width: "18%", field: "updatedDate"},
            ],
            rowOperation: {show: false}
        }
    }
    var formGridEvents = {
        grid: {
            fieldEvents: {},
            operationEvents: [{
                name: "新增", class: "btn-success", icon: "tianjia", click: function () {
                    openModal();
                }
            }, {
                name: "编辑", class: "btn-info", icon: "luru", click: function (row) {
                    editModal(row);
                }
            }, {
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
            templateUrl: 'views/admin/system/usergroup/usergroupDetail.html',
            controller: 'usergroupDetailCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
                source: function () {
                    return source;
                }
            }
        });
    }
    //编辑modal
    function editModal(row) {
        if ($scope.lionFormGrid.getAllSelectRows().length == 0) {
            dbUtils.info('请选择要编辑的数据');
        } else if ($scope.lionFormGrid.getAllSelectRows().length > 1) {
            dbUtils.info('请选择一行数据');
        } else {

            console.log($scope.lionFormGrid.getAllSelectRows());

            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/usergroup/usergroupDetail.html',
                controller: 'usergroupDetailCtrl',
                size: "md",
                backdrop: "static",
                resolve: {
                    source: function () {
                        return $scope.lionFormGrid.getAllSelectRows();
                    }
                }
            });
            instance.result.then(function () {
                $scope.lionFormGrid.reLoadData();
            });
        }
        ;
    }


    /**
     * 删除操作
     */
    function quit() {
        var selectRows = $scope.lionFormGrid.getAllSelectRows();
        if (selectRows.length === 0) {
            dbUtils.info('请选择要删除的用户组数据');
        }else{
            var ids = dbUtils.getFieldArray(selectRows, "id");
            dbUtils.confirm("确定要对所选项目用户组信息进行<span style='color: red'>删除</span>操作?", function () {
                dbUtils.post('system.usergroup.delete', {'ids': ids}, function (data) {
                    if (data.code==='200') {
                        dbUtils.success("用户组删除成功！!");
                    } else {
                        dbUtils.error("用户组删除失败!");
                    }
                    $scope.lionFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error("用户组删除处理异常!" + error);
                });
            });
        }
    }

}