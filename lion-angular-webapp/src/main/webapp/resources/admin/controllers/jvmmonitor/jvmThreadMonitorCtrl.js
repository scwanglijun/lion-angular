/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("jvmThreadMonitorCtrl", ['$scope', '$modal', 'dbUtils',jvmThreadMonitorCtrl]);

function jvmThreadMonitorCtrl($scope, $modal, dbUtils) {

    dbUtils.post("system.jvm.thread",{id: '1'},function (data) {
        /*console.dir(data.threadSummary);*/
        $scope.threadSummary = data.threadSummary;
    });
}