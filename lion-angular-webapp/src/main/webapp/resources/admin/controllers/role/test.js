/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("testCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', test]);

//显示数据
function test($scope,$modalInstance,dbUtils,dbImService,source){

    if (angular.isUndefined(source)) {
        $scope.data = {
            nameZh: null,
            nameEn: null,
            description: null,
            editable:true
        };
    } else {
        $scope.formDisabled = false;
        $scope.editData = true;
        $scope.data = angular.copy(source)[0];
        console.log(source);
    }

    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };


    //提交成功
    $scope.submitDialogForm = function (isValid) {
        console.log($scope.data);
        console.log(source);
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);
            dbUtils.post(angular.isUndefined(source) ? 'system.role.add' : 'system.role.edit',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
