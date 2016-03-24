/**
 * Created by ziv.hung on 16/1/11.
 */

var loginApp = angular.module('loginApp',['lionUtils']);

loginApp.controller("loginController", ['$scope', '$http', '$window','lionUtils',LoginController]);

function LoginController($scope, $http, $window,lionUtils) {
    $scope.data = {
        username:'wanglijun',
        password: '123456',
        verifyCode: "1111",
        rememberMe:false
    };
    //登录请求
    $scope.submitDbForm = function (isValid) {
        $scope.submited = true;

        var username = $scope.data.username;
        var password = $scope.data.password;
        var verifyCode = $scope.data.verifyCode;
        if (!username || !password || !verifyCode) {
            alert("登录信息不全,检查后重试!");
            return;
        }
        if (isValid) {
            var reqBody = angular.copy($scope.data);
            lionUtils.fetch('user.login',reqBody,function(data){
                console.dir(data);
                if(data.responseBody.code === '200'){
                    $window.sessionStorage.setItem("loginName", username);
                    window.location.href="index.html";
                }else{
                    alert(data.responseBody.message);
                }

            });
        }
    };

    $scope.verifyCodeUrl = "../verfyCode.do";
    $scope.resetVerifyCode = function () {
        $scope.verifyCodeUrl = $scope.verifyCodeUrl + "?d" + new Date();
    }
}