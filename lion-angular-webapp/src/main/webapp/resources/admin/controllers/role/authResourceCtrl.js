/**
 * Created by wash on 16/3/28.
 */


var DBApp = angular.module('DBApp');

DBApp.controller("authResourceCtrl", ['$scope', 'dbUtils','dbImService','$timeout',authResourceCtrl]);

function authResourceCtrl($scope, dbUtils, dbImService,$timeout) {
    var formData = {
        code: '',
        parentNamePath: '',
        type: '',
        isMenu: '',
        permission: '',
        sortNo: null
    };
    //dbTree 初始化数据
    $scope.dbTree = {settings: {useCheckBox: true, treeScrollHeight: "350px", noCache: true}};

    doGetResourceTreeData();
    //form 初始化
    $scope.dbTree.itemClickEvent = function (item) {
        if (!item['parentCode']) {//资源管理不允许编辑
            return;
        }
        var originData = angular.copy(formData);
        dbUtils.post("system.resource.resourceGet", {id: item['resourceId']}, function (data) {
            originData = angular.extend({}, originData, data);

            //$scope.dbForm.setOriginData(originData);
            //$scope.dbForm.setFormDataField("parentResourceName",data.nameZh);
            //$scope.dbForm.setFormDataField("parentResourceId",data.id);

        });
    };

    //机构树选择后的回调事件
    $scope.dbResourceTree = {settings: {noCache: true}};
    $scope.dbResourceTree.onResourceSelected = function (item) {
        //$scope.dbForm.setFormDataField("parentResourceName",item.nameZh);
        //$scope.dbForm.setFormDataField("parentResourceId",item.id);
    };
    //临时解决编辑按钮不出现的问题
    $timeout(function () {
        //$scope.dbForm.setOriginData(formData);
    }, 500);

    function doGetResourceTreeData() {
        dbUtils.post("system.role.authResourceRole",$scope.data, function (resourceData) {
            initDbResourceTree(resourceData);
        });
    }


    //初始化树形结构的数据

    function initDbResourceTree(resourceData) {
        //构造树结构
        //1.查找root
        var root = null;
        angular.forEach(resourceData, function (item) {
            if (angular.isUndefined(item['parentResourceId']) || !item['parentResourceId']) {
                root = {text: item['nameZh'], parentCode: item['parentResourceId'], code: item['id'], attr: item, resourceId: item['id'],types: item['type'],paths: item['path'], opened: true, iconClass: "icon-state-warning", treeId: item['id']};
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
                if (item['parentResourceId'] == parentCode) {

                    //var iconClass = item['isLeaf'] == true ? 'icon-state-warning' : 'icon-state-success';

                    var iconClass = 'icon-state-success';
                    var o = {text: item['nameZh'],parentCode: item['parentResourceId'], code: item['id'], attr: item, resourceId: item['id'],type:item['type'],path:item['path'], children: [], iconClass: iconClass, treeId: item['id'], canSelect: true};
                    if(item.checked){
                        o.selected=true;
                    }
                    child.push(o);
                }
            });
            angular.forEach(child,function (item) {
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


    //授权
    $scope.submitDialog = function () {
        $scope.submited = true;
        var id=$scope.data.id;
        //已选择的对象
        var selecteds=[];
        //目标的对象
        var auths=[];
        console.log($scope.dbTree.getAllSelectedData());
        angular.forEach($scope.dbTree.getAllSelectedData(), function (selectedsRow) {

            selecteds.push(selectedsRow.id);
        });

        var reqBody = {
            id:id,
            selecteds:selecteds,
            auths:auths
        }
        console.log(reqBody);
        dbUtils.post('system.role.addResourceRole',reqBody,function (data) {
            dbUtils.success('操作成功!');
        }, function (error) {
            dbUtils.error(error);
        });
    };
}
