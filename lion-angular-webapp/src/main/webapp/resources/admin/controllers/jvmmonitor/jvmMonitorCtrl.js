/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("jvmMonitorCtrl", ['$scope', '$modal', 'dbUtils',jvmMonitorCtrl]);

function jvmMonitorCtrl($scope, $modal, dbUtils) {

    //function loadin() {
    dbUtils.post("system.jvm",{id: '1'},function (data) {
        $scope.runtime = data.runtimeInfo;
        $scope.osInfo=data.operatingSystemInfo;
    });
}