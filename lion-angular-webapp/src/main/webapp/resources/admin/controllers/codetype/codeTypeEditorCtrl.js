/**
 * Created by ZhangYake on 2016/4/18.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("codeTypeEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', codeTypeEditorCtrl]);
//显示数据
function codeTypeEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    dbImService.bindByJSON($scope,'codeType',function(data){
        console.log(data);
    });

    if (angular.isUndefined(source)) {

        $scope.data = {
            codeType: null,
            nameZh: null,
            nameEn: null,
            codeLenLimit: null,
            editable: null
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

        //表格类型
        if($scope.data.codeType==null){
            dbUtils.info('编码类型不能为空！');
            return;
        }
        //$scope.dbForm.setFormDataField("type",$scope.data.systemParameterType.value);
        $scope.data.type = $scope.data.codeType.value;
        if($scope.data.editable==null || $scope.data.editable==undefined){
            $scope.data.editable=false;
        }
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.codetype.add' : 'system.codetype.edit',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
