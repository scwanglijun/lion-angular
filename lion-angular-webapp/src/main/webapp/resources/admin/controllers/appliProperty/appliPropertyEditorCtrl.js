/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("appliPropertyEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', appliPropertyEditorCtrl]);

//显示数据
function appliPropertyEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    if (angular.isUndefined(source)) {
        $scope.data = {
            appId: null,
            key: null,
            value: null,
            description: null
        };
    } else {
        $scope.formDisabled = false;
        $scope.editData = true;
        $scope.data = angular.copy(source)[0];
    }

    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    //提交成功
    $scope.submitDialogForm = function (isValid) {
        //console.log($scope.data);
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.applicationProperty.add':'system.applicationProperty.edit',reqBody, function (data) {
                dbUtils.success('添加系统配置信息成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
