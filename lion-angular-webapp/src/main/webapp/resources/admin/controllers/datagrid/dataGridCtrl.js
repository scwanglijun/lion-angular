/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("dataGridCtrl", ['$scope', '$modal', 'dbUtils',DataGridCtrl]);

function DataGridCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "title", label: "标题", type: "text", required: true, placeholder: "标题", readonly: true, labelCols: "3"}
            ]
        },
        grid: {
            settings: {
                transCode: "system.datagrid.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "表格类型", width: "18%", field: "type"},
                {name: "表格ID", width: "18%", field: "tableId"},
                {name: "标题", width: "18%", field: "title"},
                {name: "请求方法", width: "10%", field: "method"},
                {name: "当前页码", width: "18%", field: "pageNumber"},
                {name: "每一记录数", width: "18%", field: "pageSize"},
                {name: "显示复选框", width: "18%", field: "checkOnSelect"},
                {name: "是否多选", width: "18%", field: "singleSelect"}
            ],
            rowOperation: {show: false}
        }
    }

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
            templateUrl: 'views/admin/system/datagrid/dataGridEditorView.html',
            controller: 'dataGridEditorCtrl',
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
        if ($scope.lionFormGrid.getAllSelectRows().length == 0) {
            dbUtils.info('请选择要编辑的行数据');
        }else if($scope.lionFormGrid.getAllSelectRows().length>1){
            dbUtils.info("只能选择一行数据");
        } else {
            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/datagrid/dataGridEditorView.html',
                controller: 'dataGridEditorCtrl',
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
    }

    /**
     * 删除操作
     */
    function quit() {
        var selectRows = $scope.lionFormGrid.getAllSelectRows();
        if (selectRows.length === 0) {
            dbUtils.info('  请选择需要删除的行');
            return;
        }
        if(selectRows.length>1){
            dbUtils.info('只能选择一行');
            return;
        }
        var ids = dbUtils.getFieldArray(selectRows, "id");

        dbUtils.confirm("确定要对所选人员进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('system.datagird.delete',{'id': ids[0]}, function (data) {
                if (data) {
                    dbUtils.success("渠道维护人员删除成功！!");
                } else {
                    dbUtils.error(data + "以上渠道维护人员不能删除，请先迁移其所辖的代理机构！");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("人员删除处理异常!" + error);
            });
        });
    }

    /**
     * 导出Excel
     */
    function imports(){
        dbUtils.post('system.role.delete', {'tableId': "datagrid_dt"}, function (data) {
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
    }

}