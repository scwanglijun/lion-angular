/**
 * Created by ziv.hung on 16/1/6.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("systemApplicationInfoCtrl", ['$scope', '$modal', 'dbUtils',ApplicationInfoCtrl]);

function ApplicationInfoCtrl($scope, $modal, dbUtils) {

    //编辑modal
    //function loadin() {
            dbUtils.post("system.applicationinfo",{id: '1'},function (data) {
                console.dir(data);
                $scope.applicationInfo = data.applicationInfo;
                $scope.dataBaseInfo=data.dataBaseInfo;
            });
    //}

}