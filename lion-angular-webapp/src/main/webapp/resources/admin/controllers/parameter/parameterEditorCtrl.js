/**
 * Created by wash on 16/3/28.
 */
/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("parameterEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', parameterEditorCtrl]);
//显示数据
function parameterEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    dbImService.bindByJSON($scope,'systemParameterType',function(data){
        console.log(data);
    });

    if (angular.isUndefined(source)) {

        $scope.data = {
            systemParameterType: null,
            nameZh: null,
            nameEn: null,
            value: null,
            description: null,
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
        if($scope.data.systemParameterType==null){
           dbUtils.info('表格类型不能为空！');
            return;
        }
        //$scope.dbForm.setFormDataField("type",$scope.data.systemParameterType.value);
        $scope.data.type = $scope.data.systemParameterType.value;
        if($scope.data.editable==null || $scope.data.editable==undefined){
            $scope.data.editable=false;
        }
        console.log($scope.data);
        //console.log(source);
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.parameter.add' : 'system.parameter.editor',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
