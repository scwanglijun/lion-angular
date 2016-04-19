/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("iconListCtrl", ['$scope', '$modal', 'dbUtils', iconListCtrl]);

function iconListCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "iconClass", label: "图标类名", type: "text", required: true, placeholder: "图标类名", readonly: true, labelCols: "3"},
                {name: "iconType", label: "图标类型", type: "select", dropDownItemType: "json", dropDownItem: "systemIconType", required: true}
            ]
        },
        grid: {
            settings: {
                transCode: "system.icon.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "图标类型", width: "18%", field: "iconType"},
                {name: "图标类名", width: "12%", field: "iconClass"},
                {name: "创建时间", width: "18%", field: "createdDataFormatter"},
                {name: "更新时间", width: "18%", field: "updatedDataFormatter"},
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
            templateUrl: 'views/admin/system/icon/iconEditorView.html',
            controller: 'iconEditorCtrl',
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
                templateUrl: 'views/admin/system/icon/iconEditorView.html',
                controller: 'iconEditorCtrl',
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

        dbUtils.confirm("确定要对所选图标信息进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('system.icon.delete',{'id': ids[0]}, function (data) {
                if (data) {
                    dbUtils.success("图标信息删除成功！!");
                } else {
                    dbUtils.error(data + "图标信息删除失败！");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("人员删除处理异常!" + error);
            });
        });
    }
}