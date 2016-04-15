/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("codeDetailCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', codeDetailCtrl]);

//显示数据
function codeDetailCtrl($scope,$modalInstance,dbUtils,dbImService,source){

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

            dbUtils.post(angular.isUndefined(source) ? 'system.codelist.add' : 'system.codelist.edit',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
