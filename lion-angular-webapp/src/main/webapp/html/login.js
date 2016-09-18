/**
 * Created by ziv.hung on 16/1/11.
 */
var loginApp = angular.module('loginApp', [
    "ui.bootstrap",
    "dbUtils",
    "dbImService",
    "toaster"
    // 'toaster',
    // 'ui.router',
    // 'dbUtils',
]);
loginApp.controller("loginController", ['$scope', '$http', '$window','dbUtils', LoginController]);

function LoginController($scope, $http, $window,toaster) {

    $scope.app={
        name:"PAAS"
    }


    $scope.data = {
        username :'wanglijun',
        password: '111aaa',
        captchaCode:''
    };
    //登录请求
    $scope.submitDbForm = function (isValid) {
        $scope.submited = true;

        var username = $scope.data.username;
        var password = $scope.data.password;
        var captchaCode=$scope.data.captchaCode;

        if (!username) {
            showToasterError('','用户名不为空!');
            return;
        }
        if(!password){
            showToasterError('','密码不能不为空!');
            return;
        }
        // if(!captchaCode){
        //     showToasterError('','验证码不能不为空!');
        //     return;
        // }

        if (isValid) {
            var  reqBody=angular.copy($scope.data);
            $http.post('../login.do', reqBody).success(function (data) {
                var errorMsg = data['login_error']||'';
                if (errorMsg != '') {
                    $scope.refreshCaptchaCode();
                    showToasterError('',errorMsg);
                } else {
                    var loginUser = JSON.stringify(data)
                    console.log(data)
                    //add by lixiaohao
                    $window.sessionStorage.setItem('loginUser',loginUser);
                    //$state.go('app.paas.mysql');
                    window.location.href = 'index.html';
                }
            }).error(function () {
                showToasterError('','系统异常,请联系管理员');
            });
        }
    };

    $scope.captchaCodeUrl = '../captcha.do';

    $scope.refreshCaptchaCode = function () {
        $scope.captchaCodeUrl = $scope.captchaCodeUrl + '?d' + new Date();
    }

    //function testFn(){
    //    $scope.captchaCodeUrl = $scope.captchaCodeUrl + '?d' + new Date();
    //}
    //testFn();

    //错误提示框架
    function showToasterError(title,content){
        showToaster('error',title,content);
    }

    //显示提示框
    function showToaster(type, title, content) {
        alert(content);
        // toaster.pop({
        //     type: type,
        //     title: title,
        //     body: content,
        //     timeout: 4000,
        //     bodyOutputType: 'trustedHtml',
        //     showCloseButton: true
        // });
    }
}


// function LoginController($scope, $http, $window, dbUtils) {
//     $scope.data = {
//         username:'wanglijun',
//         password: '111aaa',
//         validCode: "1111"
//     };
//
//     //登录请求
//     $scope.submitDbForm = function (isValid) {
//         $scope.submited = true;
//
//         var userName = $scope.data.username;
//         var password = $scope.data.password;
//
//         if (!userName || !password ) {
//             alert("登录信息不全,检查后重试!");
//             return;
//         }
//         console.dir(isValid);
//         if (isValid) {
//             var reqBody = angular.copy($scope.data);
//             dbUtils.post('user.login',reqBody,function(data){
//                 console.log(data)
//                if(data.code==='200'){
//                    $window.sessionStorage.setItem("loginName", userName);
//                    $window.location.href = "index.html";
//                }else{
//                    alert(data.message);
//                }
//             });
//             //$http.post("../login.do", reqBody).success(function (data) {
//             //    var errorMsg = data["errorMsg"];
//             //    if (errorMsg != "") {
//             //        $scope.resetVerifyCode();
//             //        alert(errorMsg);
//             //    } else {
//             //        $window.sessionStorage.setItem("loginName", userName);
//             //        $window.location.href = "index.html";
//             //    }
//             //}).error(function () {
//             //    alert("系统异常！");
//             //});
//         }
//     };
//
//     $scope.verifyCodeUrl = "../verfyCode.do";
//     $scope.resetVerifyCode = function () {
//         $scope.verifyCodeUrl = $scope.verifyCodeUrl + "?d" + new Date();
//     }
// }