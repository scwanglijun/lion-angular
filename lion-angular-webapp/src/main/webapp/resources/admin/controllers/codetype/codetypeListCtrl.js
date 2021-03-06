/**
 * Created by wash on 16/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("codeTypeListCtrl", ['$scope', '$modal', 'dbUtils', codeTypeListCtrl]);

function codeTypeListCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "nameZh", label: "编码名称（中文）", type: "text", required: true, placeholder: "请输入中文名", readonly: true, labelCols: "5"},
                {name: "type", label: "编码类型", type: "select", dropDownItemType: "json", dropDownItem: "codeType", required: true}
            ]
        },
        grid: {
            settings: {
                transCode: "system.codetype.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "类型", width: "16%", field: "type"},
                {name: "名称（中文）", width: "18%", field: "nameZh"},
                {name: "名称（英文）", width: "18%", field: "nameEn"},
                {name: "参数长度", width: "10%", field: "codeLenLimit"},
                {name: "可编辑", width: "10%", field: "editable"},
                {name: "创建时间", width: "14%", field: "createDateFormatter"},
                {name: "更新时间", width: "14%", field: "updateDateFormatter"}
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
            templateUrl: 'views/admin/system/codetype/codeTypeEditorView.html',
            controller: 'codeTypeEditorCtrl',
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
                templateUrl: 'views/admin/system/codetype/codeTypeEditorView.html',
                controller: 'codeTypeEditorCtrl',
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

        dbUtils.confirm("确定要对所选信息进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('system.codetype.delete',{'id': ids[0]}, function (data) {
                if (data) {
                    dbUtils.success("编码信息删除成功！!");
                } else {
                    dbUtils.error(data + "以上渠道维护人员不能删除，请先迁移其所辖的代理机构！");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("人员删除处理异常!" + error);
            });
        });
    }
}