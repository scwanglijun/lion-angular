/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("roleAuthCtrl", ['$scope','$modalInstance','dbUtils','source','dbImService', roleAuthCtrl]);

//显示数据
function roleAuthCtrl($scope,$modalInstance,dbUtils,source,dbImService){
    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

}
