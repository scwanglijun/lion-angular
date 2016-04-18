/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("codeListCtrl", ['$scope', '$modal', 'dbUtils', codeListCtrl]);

function codeListCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {
                    name: "nameZh",
                    label: "编码名称",
                    type: "text",
                    required: true,
                    placeholder: "编码名称(中文)",
                    readonly: true,
                    labelCols: "3"
                },
                {
                    name: "codeType",
                    label: "编码列表类型",
                    type: "select",
                    dropDownItemType: "json",
                    dropDownItem: "codeListType",
                    required: true
                }
            ]
        },
        grid: {
            settings: {
                transCode: "system.codelist.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [

                {name: "编码类型", width: "16%", field: "codeType"},
                {name: "编码值", width: "16%", field: "codeValue"},
                {name: "编码名称(中文)", width: "16%", field: "nameZh"},
                {name: "编码名称(英文)", width: "16%", field: "nameEn"},
                {name: "编码排序", width: "10%", field: "sortNo"},
                {name: "可编辑", width: "10%", field: "editable"},
                {name: "默认项", width: "10%", field: "selected"},
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
            templateUrl: 'views/admin/system/code/codeDetail.html',
            controller: 'codeDetailCtrl',
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
            dbUtils.info('请选择要编辑的行数据');
        } else if ($scope.lionFormGrid.getAllSelectRows().length > 1) {
            dbUtils.info('请选择一行数据');
        } else {

            console.log($scope.lionFormGrid.getAllSelectRows());

            var instance = $modal.open({
                animation: true,
                templateUrl: 'views/admin/system/code/codeDetail.html',
                controller: 'codeDetailCtrl',
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
            dbUtils.info('请选择要删除的用户数据');
        } else {
            var ids = dbUtils.getFieldArray(selectRows, "id");
            dbUtils.confirm("确定要对所选项目编码信息进行<span style='color: red'>删除</span>操作?", function () {
                dbUtils.post('system.userlist.delete', {'ids': ids}, function (data) {
                    if (data.code === '200') {
                        dbUtils.success("编码删除成功！!");
                    } else {
                        dbUtils.error("编码删除失败!");
                    }
                    $scope.lionFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error("编码删除处理异常!" + error);
                });
            });
        }
    }
}