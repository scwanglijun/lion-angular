/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("codeDetailCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', codeDetailCtrl]);

//显示数据
function codeDetailCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    dbImService.bindByJSON($scope,'codeListType',function(data){
        console.log(data);
    });

    if (angular.isUndefined(source)) {
        $scope.data = {
            codeListType: null,
            codeValue: null,
            nameZh: null,
            nameEn: null,
            sortNo: null,
            editable:true,
            selected:false
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

        //表格类型
        if($scope.data.codeListType==null){
            dbUtils.info('编码类型不能为空！');
            return;
        }
        //$scope.dbForm.setFormDataField("type",$scope.data.systemParameterType.value);
        $scope.data.codeType = $scope.data.codeListType.value;
        if($scope.data.editable==null || $scope.data.editable==undefined){
            $scope.data.editable=false;
        }
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
