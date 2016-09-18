/**
 * Created by wash on 16/3/28.
 */
/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("dataGridEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', dataGridEditorCtrl]);
//显示数据
function dataGridEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

    dbImService.bindByJSON($scope,'type',function(data){
    });


    if (angular.isUndefined(source)) {
        $scope.data = {
            type: null,
            tableId: null,
            title: null
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
        if($scope.data.type==null){
           dbUtils.info('表格类型不能为空！');
            return;
        }
        $scope.data.type = $scope.data.type.value;
        //console.log(source);
        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.datagrid.add' : 'system.datagrid.editor',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };


}
