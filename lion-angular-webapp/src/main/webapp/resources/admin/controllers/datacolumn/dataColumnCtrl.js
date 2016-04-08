/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("dataColumnCtrl", ['$scope', '$modal', 'dbUtils',DataColumnCtrl]);

function DataColumnCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "name", label: "表格名称", type: "text", required: true, placeholder: "表格名称", readonly: true, labelCols: "3"}
            ]
        },
        grid: {
            settings: {
                transCode: "system.datacolumn.list",
                autoLoad: true,
                page: {pageSize: 5},
                showCheckBox: true
            },
            header: [
                {name: "表格名称", width: "18%", field: "dataGridTitle"},
                {name: "列名", width: "18%", field: "name"},
                {name: "列字段名", width: "18%", field: "field"},
                {name: "对齐", width: "10%", field: "align"},
                {name: "顺序", width: "18%", field: "showOrder"},
                {name: "宽度", width: "18%", field: "width"},
                {name: "排序", width: "18%", field: "sortable"},
                {name: "方向", width: "18%", field: "order"},
                {name: "标题对齐", width: "18%", field: "headerAlign"},
                {name: "显示BOX", width: "18%", field: "checkbox"}
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
            templateUrl: 'views/admin/system/datacolumn/dataColumnEditorView.html',
            controller: 'dataColumnEditorCtrl',
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
        if ($scope.lionFormGrid.getAllSelectRows().length === 0) {
            dbUtils.info('请选择要编辑的行数据');
        }else if($scope.lionFormGrid.getAllSelectRows().length>1){
            dbUtils.info("只能选择一行数据");
        } else {
            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/datacolumn/dataColumnEditorView.html',
                controller: 'dataColumnEditorCtrl',
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
            dbUtils.info('至少选择一行');
            return;
        }

        if (selectRows.length > 1) {
            dbUtils.info('至少最多选中一行');
            return;
        }

        var ids = dbUtils.getFieldArray(selectRows, "id");


        dbUtils.confirm("确定要对所选人员进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('system.datacolumn.delete', {'id': ids[0]}, function (data) {
                if (data) {
                    dbUtils.success("渠道维护人员删除成功！");
                } else {
                    dbUtils.error(data + "以上渠道维护人员不能删除，请先迁移其所辖的代理机构！")
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("人员删除处理异常!" + error);
            });
        });
    }

    /***
     * 导出excel
     */
    function exports(){

        dbUtils.post('system.datacolumn.export', {'tableId': 'sys_codelist_tb'},function(data){
            console.dir(data);
        });
    }




    /**
     * 查看审核记录
     * @param currentRecord
     */
    //function auditStatusHistory(currentRecord) {
    //    $modal.open({
    //        animation: true,
    //        templateUrl: 'views/roles.json/partNoAuditHistoryView.html',
    //        controller: 'partNoAuditHistoryCtrl',
    //        size: "lg",
    //        backdrop: "static",
    //        resolve: {
    //            source: function () {
    //                return currentRecord;
    //            }
    //        }
    //    });
    //}
}