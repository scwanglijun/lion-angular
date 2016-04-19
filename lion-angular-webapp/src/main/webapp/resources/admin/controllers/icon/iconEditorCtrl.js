/**
 * Created by ZhangYake on 2016/4/18.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("iconEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', iconEditorCtrl]);
//显示数据
function iconEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    dbImService.bindByJSON($scope,'systemIconType',function(data){
    });

    if (angular.isUndefined(source)) {

        $scope.data = {
            systemIconType: null,
            iconClass: null,
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
        if($scope.data.systemIconType==null){
            dbUtils.info('图标类型不能为空！');
            return;
        }
        //$scope.dbForm.setFormDataField("type",$scope.data.systemParameterType.value);
        $scope.data.iconType = $scope.data.systemIconType.value;
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.icon.add' : 'system.icon.edit',reqBody, function (data) {
                console.log(data)
                if(data.code=="200"){
                    dbUtils.success('操作成功!');
                }else{
                    dbUtils.error('操作失败!');
                }

                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}
