'use strict';
var DBApp = angular.module('DBApp');//加载模块
DBApp.controller('DashboardController', function($rootScope, $scope, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        //Metronic.initAjax();

        $scope.treeData = [{
            "text": "Same but with checkboxes",
            "children": [{
                "text": "initially selected",
                "state": {
                    "selected": true
                }
            }, {
                "text": "custom icon",
                "icon": "fa fa-warning icon-state-danger"
            }, {
                "text": "initially open",
                "icon" : "fa fa-folder icon-state-default",
                "state": {
                    "opened": true
                },
                "children": ["Another node"]
            }, {
                "text": "custom icon",
                "icon": "fa fa-warning icon-state-warning"
            }, {
                "text": "disabled node",
                "icon": "fa fa-check icon-state-success",
                "state": {
                    "disabled": true
                }
            }]
        },
        "And wholerow selection"
        ];
    });
});

