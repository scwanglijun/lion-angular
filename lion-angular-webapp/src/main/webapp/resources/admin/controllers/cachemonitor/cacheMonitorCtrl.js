/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("cacheMonitorCtrl", ['$scope', '$modal', 'dbUtils',cacheMonitorCtrl]);

function cacheMonitorCtrl($scope, $modal, dbUtils) {

    //function loadin() {
    dbUtils.post("system.monitor.cache",{id: "1"},function (data) {
        $scope.managerModel = data.managerModel;
    });
}