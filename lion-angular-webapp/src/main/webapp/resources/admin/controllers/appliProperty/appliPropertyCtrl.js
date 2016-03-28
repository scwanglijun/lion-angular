/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("appliPropertyCtrl", ['$scope', '$modal', 'dbUtils', appliPropertyCtrl]);

function appliPropertyCtrl($scope, $modal, dbUtils) {
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "appId", label: "AppId", type: "text", required: true, placeholder: "AppId", readonly: true, labelCols: "3"},
                {name: "value", label: "Value", type: "text", required: true, placeholder: "Value", readonly: true, labelCols: "3"}
            ]
        },
        grid: {
            settings: {
                transCode: "system.applicationProperty.list",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
                {name: "AppId", width: "18%", field: "appId"},
                {name: "Key", width: "12%", field: "key"},
                {name: "Value", width: "12%", field: "value"},
                {name: "Description", width: "10%", field: "description"},
                {name: "创建时间", width: "18%", field: "createdDate"},
                {name: "更新时间", width: "18%", field: "updatedDate"},
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
                name: "删除", class: "btn-danger", icon: "shanchu", click: function () {
                    quit();
                }
            },{
                name: "新增", class: "btn-success", icon: "shanchu", click: function () {
                    //quit();
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


    /**
     * 删除操作
     */
    function quit() {
        var selectRows = $scope.lionFormGrid.getAllSelectRows();
        if (selectRows.length === 0) {
            return;
        }
        var ids = dbUtils.getFieldArray(selectRows, "id");
        dbUtils.confirm("确定要对所选项目属性进行<span style='color: red'>删除</span>操作?", function () {
            dbUtils.post('partyRoleQuit', {'ids': ids}, function (data) {
                if (data) {
                    dbUtils.error(data + "以上渠道维护人员不能删除，请先迁移其所辖的代理机构！")
                } else {
                    dbUtils.success("渠道维护人员删除成功！!");
                }
                $scope.lionFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error("人员删除处理异常!" + error);
            });
        });
    }

    /**
     * 查看审核记录
     * @param currentRecord
     */
    function auditStatusHistory(currentRecord) {
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
    }


}