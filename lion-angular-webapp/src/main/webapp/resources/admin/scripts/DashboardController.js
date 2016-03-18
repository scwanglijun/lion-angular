/**
 * Created by wanglijun on 16/3/17.
 */
'use strict';
var lionApp = angular.module('lionApp');//加载模块
lionApp.controller('DashboardController', function($rootScope, $scope, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        //Metronic.initAjax();
    });
});

