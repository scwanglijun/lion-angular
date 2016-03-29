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