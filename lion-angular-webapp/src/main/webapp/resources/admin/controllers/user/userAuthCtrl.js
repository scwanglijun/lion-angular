/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("userAuthCtrl", ['$scope','$modalInstance','dbUtils','source','dbImService', userAuthCtrl]);

//显示数据
function userAuthCtrl($scope,$modalInstance,dbUtils,source,dbImService){
    console.log(source)

    $scope.auth_username=source.username;
    $scope.auth_employeeCode=source.employeeCode;
    $scope.auth_accountLocked=(source.accountLocked===true?'已锁定':'未锁定');
    $scope.auth_accountExpired=source.accountExpired===true?'有效':'无效';
    $scope.auth_credentialExpired=source.credentialExpired===true?'有效':'无效';
    //$('#auth_accountExpiredDate').text(formatterDate(source.accountExpiredDate));
    //$('#auth_credentialExpiredDate').text(formatterDate(source.credentialExpiredDate));
    //$('#auth_department').text(formatterDepartment(source.department));

    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

}
