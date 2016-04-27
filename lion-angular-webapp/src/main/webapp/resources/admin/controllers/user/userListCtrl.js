/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("userListCtrl", ['$scope', '$modal', 'dbUtils', userListCtrl]);

function userListCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 3
            },
            fields: [
                {name: "username", label: "用户名", type: "text", required: true, placeholder: "用户名", readonly: true, labelCols: "3"},
                {name: "employeeCode", label: "员工号", type: "text", required: true, placeholder: "员工号", readonly: true, labelCols: "3"},
                {name: "email", label: "邮箱", type: "text", required: true, placeholder: "邮箱", readonly: true, labelCols: "3"}
            ]
        },
        grid: {
            settings: {
                transCode: "system.user.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "用户名", width: "12%", field: "username"},
                {name: "姓名(中文)", width: "12%", field: "realnameZh"},
                {name: "姓名(英文)", width: "12%", field: "realnameEn"},
                {name: "员工号", width: "10%", field: "employeeCode"},
                {name: "所属部门", width: "12%", field: "department"},
                {name: "锁定状态", width: "12%", field: "accountLocked"},
                {name: "账户状态", width: "12%", field: "accountExpired"},
                {name: "账户有效日期", width: "18%", field: "accountExpiredDate"}
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
            templateUrl: 'views/admin/system/user/userEditorView.html',
            controller: 'userEditorCtrl',
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
                var instance = $modal.open({
                    animation: true,
                    templateUrl: 'views/admin/system/user/userEditorView.html',
                    controller: 'userEditorCtrl',
                    size: "lg",
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

        dbUtils.confirm("确定要对所选用户进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('system.user.delete',{'id': ids[0]}, function (data) {
                if (data) {
                    dbUtils.success("用户删除成功！!");
                } else {
                    dbUtils.error(data + "以上用户不能删除，请先迁移其所辖的代理机构！");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("用户删除处理异常!" + error);
            });
        });
    }

}