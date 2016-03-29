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
                {name: "表格类型", width: "18%", field: "dataGird"},
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
                    //quit();
                }
            },{
                name: "编辑", class: "btn-info", icon: "luru", click: function () {
                    //quit();
                }
            },{
                name: "删除", class: "btn-danger", icon: "shanchu", click: function () {
                    quit();
                }
            }]
        }
    };

    $scope.lionFormGrid = {options: formGridOptions, events: formGridEvents};

}