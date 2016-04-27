/**
 * Created by wash on 16/3/28.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("userEditorCtrl", ['$scope','$modal','$modalInstance','dbUtils','dbImService', 'source', userEditorCtrl]);

//显示数据
function userEditorCtrl($scope,$modal,$modalInstance,dbUtils,dbImService,source){


    dbImService.bindByJSON($scope,'gender',function(data){
    });

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
            department: null,
            departmentId:null,
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
        $scope.submited = true;
        if (isValid) {
            if($scope.data.gender==null){
                dbUtils.info('性别不能为空！');
                return;
            }
            //$scope.data.departmentId=;
            $scope.data.gender=$scope.data.gender.value;
            $scope.data.credentialExpiredDate="2016-12-12";
            $scope.data.accountExpiredDate="2016-12-12";
            var reqBody = angular.copy($scope.data);

            dbUtils.post(angular.isUndefined(source) ? 'system.user.add' : 'system.user.edit',reqBody, function (data) {
                dbUtils.success('操作成功!');
                $modalInstance.close();
            }, function (error) {
                dbUtils.error(error);
            });
        }
    };



    $scope.dbTree = {settings: {useCheckBox: false, treeScrollHeight: "350px", noCache: true}};

    $scope.showTree1 = false;

    $scope.departmentTree=function(){
        $scope.showTree1 = true;
        doGetDepartmentTreeData();
    }

    //$scope.showTree1 = true;
    //doGetDepartmentTreeData();

    //dbTree 初始化数据

    //form 初始化
    $scope.dbTree.itemClickEvent = function (item) {
        console.log(item)

        $scope.data.department=item.text;
        $scope.data.departmentId=item.departmentId;
        $scope.showTree1 = false;

    };


    function doGetDepartmentTreeData() {
        dbUtils.post("system.department.list", {}, function (resourceData) {
            initDbDepartmentTree(resourceData);
        });
    }

    function initDbDepartmentTree(resourceData) {
        //构造树结构
        //1.查找root
        var root = null;
        angular.forEach(resourceData, function (item) {
            if (angular.isUndefined(item['parentDepartmentId']) || !item['parentDepartmentId']) {
                root = {text: item['nameZh'], parentCode: item['parentDepartmentId'], code: item['id'], attr: item, departmentId: item['id'], opened: true,treeId: item['id']};
                return false;
            }
        });
        if (!root) {
            console.log("db-org-tree root is null");
            return;
        }
        //2.递归循环所有节点,将节点加入到父节点当中
        function getChildren(parentCode) {
            var child = [];

            angular.forEach(resourceData, function (item) {
                if (item['parentDepartmentId'] == parentCode) {
                    //var iconClass = item['isLeaf'] == true ? 'icon-state-warning' : 'icon-state-success';
                    var iconClass = 'icon-state-success';
                    var o = {text: item['nameZh'], parentCode: item['parentDepartmentId'], code: item['id'], attr: item, departmentId: item['id'], children: [], treeId: item['id'], canSelect: true};
                    child.push(o);
                }
            });
            angular.forEach(child, function (item) {
                item.children = getChildren(item['code']);
            });
            return child;
        }

        //生成树结构数据
        root.children = getChildren(root['code']);
        //渲染树结构
        if ($scope.dbTree) {
            $scope.dbTree.setData([root]);
        } else {
            $scope.dbTree = {
                data: [root]
            }
        }
    }


}
