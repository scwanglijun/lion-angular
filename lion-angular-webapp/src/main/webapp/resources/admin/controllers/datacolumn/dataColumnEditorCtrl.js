/**
 * Created by wash on 16/3/28.
 */
/**
 * Created by wash on 16/3/28.
 */

var DBApp = angular.module('DBApp');

DBApp.controller("dataColumnEditorCtrl", ['$scope','$modalInstance','dbUtils','dbImService', 'source', dataColumnEditorCtrl]);
//显示数据
function dataColumnEditorCtrl($scope,$modalInstance,dbUtils,dbImService,source){

  /* dbUtils.post( 'system.datacolumn.combox',{'nameEn':'datagrid_type'}, function (data) {
       console.log('1')
       console.log(data);
    }, function (error) {
        dbUtils.error(error);
    }).then(function(data){
       console.log('2');
   });*/


    dbImService.bindByJSON($scope,'type',function(data){
        console.log(data);
    });
    dbImService.bindByJSON($scope,'dataGridId',function(datas){
        console.log(datas);
    });

    if (angular.isUndefined(source)) {
        $scope.data = {
            type: null,
            dataGridId:null,
            field: null,
            name: null,
            showOrder: null,
            width: null,
            align: null
        };

    } else {
        $scope.formDisabled = false;
        $scope.editData = true;
        $scope.data = angular.copy(source)[0];
    }

    console.dir($scope.data.type);
    //取消Modal
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    //提交成功
    $scope.submitDialogForm = function (isValid) {
        //标题对齐
        //$scope.data.headerAlign=radio('headerAlign');

        //对齐
        $scope.data.align=radio('align');

        //是否隐藏
        //$scope.data.hidden=checkbox('hidden');

        //显示复选框
        //$scope.data.checkbox=checkbox('checkbox');

        //是否排序
        //$scope.data.sortable=checkbox('sortable');
        //if(checkbox('sortable')){
        //    //排序方向
        //    $scope.data.order=radio('order');
        //}



        //表格名称

        if($scope.data.dataGridId==null){
            dbUtils.info('表格名称不能为空！');
            return;
        }


        $scope.data.dataGridId = $scope.data.dataGridId.value;

        console.log($scope.data);

        $scope.submited = true;
        if (isValid) {
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.datacolumn.add' : 'system.datacolumn.editor',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };
}

/***
 * 获取单选个框中的值
 * @param rname
 */
function radio(raname){
    var que = '[name='+raname+']';
    return  $(que).filter(":checked").val();
}

/***
 * 判断复选框是否选中
 * @param rname
 */
function checkbox(raname){
    var que = '[name='+raname+']';
    return $(que).get(0).checked;
}