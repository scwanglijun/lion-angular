/**
 * Created by ZhangYake on 2016/3/30.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("hibernateMonitorCtrl", ['$scope', '$modal', 'dbUtils',hibernateMonitorCtrl]);

function hibernateMonitorCtrl($scope, $modal, dbUtils) {

    //function loadin() {
    dbUtils.post("system.monitor.hibernatemonitor",{id: "1"},function (data) {
        $scope.statistics = data.statistics;
        $scope.sessionFactory=data.sessionFactory;
        $scope.properties = data.properties;
        $scope.upseconds=data.upseconds;
        $scope.secondLevelCacheHitPercent=data.secondLevelCacheHitPercent;
        $scope.usedMemory = data.usedMemory;
        $scope.maxMemory=data.maxMemory;
        $scope.totalMemorySize = data.totalMemorySize;
        $scope.totalMemoryCount=data.totalMemoryCount;
        $scope.totalDiskCount=data.totalDiskCount;
    });
}