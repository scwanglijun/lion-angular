/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("userEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', userEditorCtrl]);

//显示数据
function userEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

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
            depement: null,
            fax: null,
            postcode: null,
            editable: null,
        };
    } else {
        $scope.formDisabled = false;
        $scope.editData = true;
        $scope.data = angular.copy(source)[0];
        //console.log($scope.data[0].nameZh);
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

            dbUtils.post(angular.isUndefined(source) ? 'system.user.add' : 'system.user.edit',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
