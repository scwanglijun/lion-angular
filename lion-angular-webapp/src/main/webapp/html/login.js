/**
 * Created by ziv.hung on 16/1/11.
 */
var loginApp = angular.module('loginApp', [
    "ui.bootstrap",
    "dbUtils",
    "dbImService",
    "toaster"
]);
loginApp.controller("loginController", ['$scope', '$http', '$window','dbUtils', LoginController]);

function LoginController($scope, $http, $window,dbUtils) {
    $scope.data = {
        username:'wanglijun',
        password: '111aaa',
        validCode: "1111"
    };

    //登录请求
    $scope.submitDbForm = function (isValid) {
        $scope.submited = true;

        var userName = $scope.data.username;
        var password = $scope.data.password;

        if (!userName || !password ) {
            alert("登录信息不全,检查后重试!");
            return;
        }
        console.dir(isValid);
        if (isValid) {
            var reqBody = angular.copy($scope.data);
            dbUtils.post('user.login',reqBody,function(data){
                console.log(data.code);
               if(data.code==='200'){
                   $window.sessionStorage.setItem("loginName", userName);
                   $window.location.href = "index.html";
               }else{
                   alert(data.message);
               }
            });
            //$http.post("../login.do", reqBody).success(function (data) {
            //    var errorMsg = data["errorMsg"];
            //    if (errorMsg != "") {
            //        $scope.resetVerifyCode();
            //        alert(errorMsg);
            //    } else {
            //        $window.sessionStorage.setItem("loginName", userName);
            //        $window.location.href = "index.html";
            //    }
            //}).error(function () {
            //    alert("系统异常！");
            //});
        }
    };

    $scope.verifyCodeUrl = "../verfyCode.do";
    $scope.resetVerifyCode = function () {
        $scope.verifyCodeUrl = $scope.verifyCodeUrl + "?d" + new Date();
    }
}