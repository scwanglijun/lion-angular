/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("jvmMemoryMonitorCtrl", ['$scope', '$modal', 'dbUtils',jvmMemoryMonitorCtrl]);

function jvmMemoryMonitorCtrl($scope, $modal, dbUtils) {

    //function loadin() {
    dbUtils.post("system.jvm",{id: '1'},function (data) {
        $scope.memory = data.memoryUsage;
    });
}