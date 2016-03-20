/**
 * Created by wanglijun on 16/3/15.
 */
'use strict';
var lionApp=angular.module('lionApp',[
    'ui.router',
    'ui.bootstrap',
    'oc.lazyLoad',
    'ngSanitize',
    'ngAnimate'
]);
/*Config*/
lionApp.config(['$ocLazyLoadProvider','$httpProvider',function($ocLazyLoadProvider,$httpProvider){
    $ocLazyLoadProvider.config({
        debug:true
    });

    //添加请求头,防止后台无法判断是Ajax请求
    $httpProvider.defaults.headers.post['X-Requested-With']='XMLHttpRequest';
}]);

lionApp.factory('settings',['$rootScope','$state',function($rootScope,$state){

    /**获取应用上下文根属性*/
    var context=function(){
        //获取应用的上下文根路径
        var pathname=window.location.pathname;
        var indexNext= pathname.indexOf("/",1);
        return pathname.substr(0,indexNext);
    }();

    var settings={
        layout:{
            pageSidebarClosed:false,
            pageAutoScrollOnLoad:1000,

        },
        layoutImgPath:Metronic.getAssetsPath(),
        layoutCssPath:Metronic.getAssetsPath(),
        context:context,
    };

    $rootScope.settings=settings;
    return settings;
}]);


//App页面控制
lionApp.controller('AppController',['$scope',"$rootScope",function($scope){
      $scope.$on('$viewContentLoaded',function(){
            Metronic.initComponents();
      });
}]);

//Header Controller
lionApp.controller('HeaderController',['$scope','$window', '$http', '$state',function($scope,$window,$http,$state){
       $scope.$on('$includeContentLoaded',function(){
           Layout.initHeader();
       });

}]);
//SideBarController
lionApp.controller('SideBarController',['$scope','$http', '$modal', '$window', '$state',function($scope,$http,$modal,$window,$state){
    $scope.$on('$includeContentLoaded', function () {
        Layout.initSidebar($scope,$http,$modal,$window,$state);
    });
}]);
//PageHeaderController
lionApp.controller('PageHeadController',['$scope',function($scope){
    $scope.$on('$includeContentLoaded', function () {
        Demo.init(); // init footer
    });
}]);
//FooterController
lionApp.controller('FooterController',['$scope',function($scope){
    $scope.$on('$includeContentLoaded', function () {
        Layout.initFooter(); // init footer
    });
}]);
/*UI Routing for All Pages*/
lionApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
    console.dir($stateProvider);
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("dashboard");

    $stateProvider.state('dashboard', {
        url: '/dashboard',
        views: {
            'contentContainer': { //contentContainer 对应页面上的ui-view值，用于指定view显示在哪个位置
                controller: 'DashboardController', // 新加载的页面对应controller，需要确保值唯一
                templateUrl: '/admin/html/views/dashboard.html' //具体需要显示的页面URL路径
            }
        },
        data: {pageTitle: 'DashBoard', pageSubTitle: '主页 | 欢迎页面'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                //在切换到这个view的时候需要先加载对应的js或其他文件，load当中可以放入数组加载多个文件
                return $ocLazyLoad.load(['../resources/admin/scripts/DashboardController.js']);
            }]
        }
    }).state('account/person', {
        url: '/account/person',
        views: {
            'contentContainer': { //contentContainer 对应页面上的ui-view值，用于指定view显示在哪个位置
                controller: 'AccountController', // 新加载的页面对应controller，需要确保值唯一
                templateUrl: '/admin/html/views/account/index.html' //具体需要显示的页面URL路径
            }
        },
        data: {pageTitle: '账户管理', pageSubTitle: '个人资料 | 基本信息'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                //在切换到这个view的时候需要先加载对应的js或其他文件，load当中可以放入数组加载多个文件
                return $ocLazyLoad.load(['../resources/admin/scripts/account/AccountController.js']);
            }]
        }
    });
}]);



/* Init global settings and run the app */
lionApp.run(['$rootScope', 'settings', '$state', "$templateCache", function ($rootScope, settings, $state, $templateCache) {
    $rootScope.$state = $state; // state to be accessed from view
   // console.log(settings)
   // $templateCache.put("db/db-form.html", "<div class='row'><db-form></db-form></div>");
   // $templateCache.put("db/db-form-grid.html", "<div class='row'><db-form-grid></db-form-grid></div>");

}]);

