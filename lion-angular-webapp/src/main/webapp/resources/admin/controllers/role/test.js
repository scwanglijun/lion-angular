/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("testCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', test]);

//显示数据
function test($scope,$modalInstance,dbUtils,dbImService,source){
    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}
