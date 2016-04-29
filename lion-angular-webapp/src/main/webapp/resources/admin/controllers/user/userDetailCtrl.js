/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("userDetailCtrl", ['$scope','$modal','$modalInstance','dbUtils','dbImService', 'source', userDetailCtrl]);

//显示数据
function userDetailCtrl($scope,$modal,$modalInstance,dbUtils,dbImService,source){


    if (angular.isUndefined(source)) {
        $scope.data = {
            username: null,
            email: null,
            credentialExpiredDate: null,
            credentialExpired: null,
            accountExpiredDate: null,
            accountExpired: null,
            nameZh: null,
            nameEn: null,
            telephone: null,
            officePhone: null,
            location: null,
            mobile: null,
            description: null,
            gender: null,
            employeeCode: null,
            department: null,
            departmentId:null,
            fax: null,
            postcode: null,
            editable: null,
        };
    } else {
        $scope.data = angular.copy(source)[0];
    }
    if($scope.gender===0){
        $scope.gender=='男'
;    }else{
        $scope.gender=='女'
    }
    console.log($scope.data)
console.log($scope.data.username)
    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };



}
