/**
 * Created by wanglijun on 16/3/20.
 */
var loginApp=angular.module('loginApp',[]);
loginApp.controller('loginController',['$scope','$http','$window',function($scope,$http,$window){
    $scope.data={
        userName:'admin',
        password:'111aaa',
        validationCode:'1111'
    }

    //登录提交
    $scope.loginSubmit=function(isValid){
        $scope.submit=true;
        //登录提交数据
    }


    //刷新验证码
    $scope.validationCodeUrl="../";
    $scope.refresh=function(){
            //刷新验证码
    }
}]);