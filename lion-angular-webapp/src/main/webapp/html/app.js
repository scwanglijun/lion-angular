/***
 Metronic AngularJS App Main Script
 ***/

/* Metronic App */
var DBApp = angular.module("DBApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "db.components.grid",
    "db.components.form.grid",
    "db.components.data.table",
    "db.components.form",
    "db.components.form.fields",
    "db.components.orgTree",
    "db.components.resourceTree",
    "db.components.departmentTree",
    "db.components.datetimepicker",
    "db.components.datepicker",
    "db.components.tree",
    "ui.select",
    "ngSanitize",
    "dbImService",
    "dbUtils",
    "ngAnimate",
    "ngStorage",
    "ui.load",
    "ui.jq",
    "pascalprecht.translate",
    "toaster",
    "hljs"
]);


/*国际化配置*/
DBApp.config(['$translateProvider',function($translateProvider){
    $translateProvider.useStaticFilesLoader({
        prefix: '../l10n/',
        suffix: '.js'
    });

    //告诉module使用什么默认语言
    $translateProvider.preferredLanguage('Zh');

    //告诉module存储语言到localstorage
    //$translateProvider.useLocalStorage();
}]);


/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
DBApp.config(['$ocLazyLoadProvider', '$httpProvider', function ($ocLazyLoadProvider, $httpProvider) {
    $ocLazyLoadProvider.config({
        debug: true
    });
    //添加请求头，防止后台无法判断是ajax请求
    $httpProvider.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest'
}]);

/* Setup Rounting For All Pages */
DBApp.config(['$stateProvider', '$urlRouterProvider', StateConfigController]);

/* Setup global settings */
DBApp.factory('settings', ['$rootScope', function ($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        layoutImgPath: Metronic.getAssetsPath() + 'admin/layout/img/',
        layoutCssPath: Metronic.getAssetsPath() + 'admin/layout/css/'
    };
    $rootScope.settings = settings;

    return settings;
}]);
/*UI-SELECT多选框使用到的过滤器*/
DBApp.filter('selectPropsFilter', function () {
    return function (items, props) {
        var out = [];
        if (angular.isArray(items)) {
            items.forEach(function (item) {
                var itemMatches = false;

                var keys = Object.keys(props);
                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                        itemMatches = true;
                        break;
                    }
                }

                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            out = items;
        }
        return out;
    };
});

/* Setup App Main Controller */
DBApp.controller('AppController', ['$scope', '$rootScope', AppController]);

function AppController($scope,$rootScope) {
    $scope.$on('$viewContentLoaded', function () {
        Metronic.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive 
    });
}

/***
 Layout Partials.
 By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial
 initialization can be disabled and Layout.init() should be called on page load complete as explained above.
 ***/

/* Setup Layout Part - Header */
DBApp.controller('HeaderController', ['$scope', '$window', '$http', '$state', 'dbUtils', function ($scope, $window, $http, $state, dbUtils) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initHeader(); // init header
    });

    var loginName = $window.sessionStorage.getItem("loginName");
    $scope.loginName = loginName;
    $scope.logout = function () {
        dbUtils.confirm("确定退出系统吗?", function () {


            // var reqBody = angular.copy($scope.data);
            dbUtils.post('user.logout',{},function(data){
                $window.sessionStorage.setItem("loginName", "");
                    $window.location.href = "login.html";//退出后直接返回主页

            });

        });
    }
    $scope.reBackIndex = function () {
        $state.go("dashboard");
    }
}]);

/* Setup Layout Part - Sidebar */
DBApp.controller('SidebarController', ['$scope', '$http', '$modal', '$window', '$state', function ($scope, $http, $modal, $window, $state) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initSidebar($scope, $http, $modal, $window, $state); // init sidebar
    });
}]);

/* Setup Layout Part - Sidebar */
DBApp.controller('PageHeadController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
DBApp.controller('FooterController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initFooter(); // init footer
    });
}]);

/**
 * 登录窗口控制器
 */
DBApp.controller('userLoginFormCtrl', ['$scope', '$http', 'dbUtils', '$modalInstance', '$window',
    function ($scope, $http, dbUtils, $modalInstance, $window) {

        $scope.data = {
            userName: null,
            password: null,
            validCode: null
        };
        //登录请求
        $scope.submitDialogForm = function (isValid) {
            $scope.submited = true;
            if (isValid) {
                Metronic.startPageLoading();
                var reqBody = angular.copy($scope.data);
                $http.post("../login.do", reqBody).success(function (data) {
                    Metronic.stopPageLoading();
                    var errorMsg = data["errorMsg"];
                    if (errorMsg != "") {
                        $scope.resetVerifyCode();
                        dbUtils.tip(errorMsg);
                    } else {
                        $window.sessionStorage.setItem("loginName", $scope.data.userName);
                        $modalInstance.close("success");
                    }
                }).error(function (data, status, headers, config) {
                    Metronic.stopPageLoading();
                    console.log(data);
                    dbUtils.tip("系统异常！");
                });
            }
        };

        $scope.verifyCodeUrl = "../verfyCode.do";
        $scope.resetVerifyCode = function () {
            $scope.verifyCodeUrl = $scope.verifyCodeUrl + "?d" + new Date();
        }

    }]);

function StateConfigController($stateProvider, $urlRouterProvider) {

    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard");

    $stateProvider.state('dashboard', {
        url: "/dashboard",
        views: {
            "mainContentContainer": { //mainContentContainer 对应页面上的ui-view值，用于指定view显示在哪个位置
                controller: "DashboardController", // 新加载的页面对应controller，需要确保值唯一
                templateUrl: "dashboard.html"//具体需要显示的页面URL路径
            }
        },
        data: {pageTitle: '应用管理系统'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                //在切换到这个view的时候需要先加载对应的js或其他文件，load当中可以放入数组加载多个文件
                return $ocLazyLoad.load(['DashboardController.js']);
            }]
        }
    })
        .state('system/users/rolelist', {
            url: "/system/users/rolelist",
            views: {
                "mainContentContainer": {
                    controller: "partyRoleEntryCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'Role', pageSubTitle: '用户管理|角色管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie:true,
                        files:['../resources/admin/controllers/role/partyRoleEntryCtrl.js',
                        '../resources/admin/controllers/role/test.js',
                            '../resources/admin/controllers/role/roleAuthCtrl.js',
                            '../resources/admin/controllers/role/roleGroupCtrl.js',
                            '../resources/admin/controllers/role/roleUserCtrl.js',
                            '../resources/admin/controllers/role/authGroupCtrl.js',
                            '../resources/admin/controllers/role/authUserCtrl.js',
                            '../resources/admin/controllers/role/authResourceCtrl.js']
                    }]);
                }]
            }
        })
        .state('system/users/usergrouplist', {
            url: "/system/users/usergrouplist",
            views: {
                "mainContentContainer": {
                    controller: "usergroupListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'Usergroup', pageSubTitle: '用户管理|用户组管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/usergroup/usergroupListCtrl.js',
                        '../resources/admin/controllers/usergroup/usergroupDetailCtrl.js',
                        '../resources/admin/controllers/usergroup/groupAuthRoleCtrl.js',
                        '../resources/admin/controllers/usergroup/groupAuthUserCtrl.js',
                        '../resources/admin/controllers/usergroup/addRoleGroupCtrl.js',
                        '../resources/admin/controllers/usergroup/addUserGroupCtrl.js']);
                }]
            }
        })
        .state('system/users/userlist', {
            url: "/system/users/userlist",
            views: {
                "mainContentContainer": {
                    controller: "userListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'User', pageSubTitle: '用户管理|用户管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/user/userListCtrl.js',
                        '../resources/admin/controllers/user/userEditorCtrl.js',
                        '../resources/admin/controllers/user/userAuthCtrl.js',
                        '../resources/admin/controllers/user/userRoleCtrl.js',
                        '../resources/admin/controllers/user/userGroupCtrl.js',
                        '../resources/admin/controllers/user/addGroupUserCtrl.js',
                        '../resources/admin/controllers/user/addRoleUserCtrl.js',
                        '../resources/admin/controllers/user/userDetailCtrl.js']);
                }]
            }
        })
        .state('system/code/codetypelist', {
            url: "/system/code/codetypelist",
            views: {
                "mainContentContainer": {
                    controller: "codeTypeListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'CodeType', pageSubTitle: '编码管理|编码类型'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/codetype/codeTypeListCtrl.js',
                        '../resources/admin/controllers/codetype/codeTypeEditorCtrl.js']);
                }]
            }
        })
        .state('system/code/codelist', {
            url: "/system/code/codelist",
            views: {
                "mainContentContainer": {
                    controller: "codeListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'CodeList', pageSubTitle: '编码管理|编码列表'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/code/codeListCtrl.js',
                        '../resources/admin/controllers/code/codeDetailCtrl.js']);
                }]
            }
        })

        .state('system/resource', {
            url: "/system/resource",
            views: {
                "mainContentContainer": {
                    controller: "resourceCtrl",
                    templateUrl: "views/admin/system/resource/resourceModifyView.html"
                }
            },
            data: {pageTitle: 'Resource', pageSubTitle: '系统设置 | 资源管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/resource/resourceCtrl.js',
                        '../resources/admin/controllers/resource/resourceEntityCtrl.js']);
                }]
            }
        })

        .state('system/appliProperty', {
            url: "/system/appliProperty",
            views: {
                "mainContentContainer": {
                    controller: "appliPropertyCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'Applicationproperty', pageSubTitle: '系统设置|项目属性配置'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/appliProperty/appliPropertyCtrl.js',
                        '../resources/admin/controllers/appliProperty/appliPropertyEditorCtrl.js']);
                }]
            }
        })

        .state('system/iconlist', {
            url: "/system/iconlist",
            views: {
                "mainContentContainer": {
                    controller: "iconListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'IconList', pageSubTitle: '系统设置|部门管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/icon/iconListCtrl.js',
                        '../resources/admin/controllers/icon/iconDetailCtrl.js']);
                }]
            }
        })

        .state('system/monitor/sessionlist', {
            url: "/system/monitor/sessionlist",
            views: {
                "mainContentContainer": {
                    controller: "sessionMonitorCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'Session', pageSubTitle: '系统设置|监控系统|用户会话监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/sessionmonitor/sessionMonitorCtrl.js']);
                }]
            }
        })

        //.state('system/monitor/druid', {
        //    url: "/system/monitor/druid",
        //    views: {
        //        "mainContentContainer": {
        //            controller: "druidListCtrl"
        //            templateUrl: "db/lion-form-grid.html"
        //        }
        //    },
        //    data: {pageTitle: 'Druid', pageSubTitle: '系统设置|系统参数'},
        //    resolve: {
        //        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
        //            return $ocLazyLoad.load(['../resources/admin/controllers/druid/druidListCtrl.js']);
        //        }]
        //    }
        //})

        .state('system/datatable', {
            url: "/system/datatable",
            views: {
                "mainContentContainer": {
                    controller: "datatableCtrl",
                    templateUrl: "views/datatable.html"
                }
            },
            data: {pageTitle: 'Datatable', pageSubTitle: '系统设置|Datatable'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/datatable/datatableCtrl.js']);
                }]
            }
        })
        .state('account/profile', {
            url: "/account/profile",
            views: {
                "mainContentContainer": {
                    controller: "accountProfileCtrl",
                    templateUrl: "views/admin/system/account/index.html"
                }
            },
            data: {pageTitle: '个人资料', pageSubTitle: '账户管理|个人资料'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(["../resources/js/ng-img-crop.js","../resources/admin/controllers/account/accountProfileCtrl.js"]);
                }]
            }
        })


        .state('system/monitor/hibernate', {
            url: "/system/monitor/hibernate",
            views: {
                "mainContentContainer": {
                    controller: "hibernateMonitorCtrl",
                    templateUrl: "views/admin/system/hibernatemonitor/index.html"
                }
            },
            data: {pageTitle: 'hibernateMonitor', pageSubTitle: '系统设置|监控系统|Hibernate监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/hibernatemonitor/hibernateMonitorCtrl.js']);
                }]
            }
        })
        .state('system/monitor/jvmmemory', {
            url: "/system/monitor/jvmmemory",
            views: {
                "mainContentContainer": {
                    controller: "jvmMemoryMonitorCtrl",
                    templateUrl: "views/admin/system/jvmmonitor/memory.html"
                }
            },
            data: {pageTitle: 'JvmMemoryMonitor', pageSubTitle: '系统设置|监控系统|JVM内存监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/jvmmonitor/jvmMemoryMonitorCtrl.js']);
                }]
            }
        })
        .state('system/monitor/jvmmoniter', {
            url: "/system/monitor/jvmmoniter",
            views: {
                "mainContentContainer": {
                    controller: "jvmMonitorCtrl",
                    templateUrl: "views/admin/system/jvmmonitor/index.html"
                }
            },
            data: {pageTitle: 'JvmMonitor', pageSubTitle: '系统设置|监控系统|JVM监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/jvmmonitor/jvmMonitorCtrl.js']);
                }]
            }
        })
        .state('system/monitor/jvmthread', {
            url: "/system/monitor/jvmthread",
            views: {
                "mainContentContainer": {
                    controller: "jvmThreadMonitorCtrl",
                    templateUrl: "views/admin/system/jvmmonitor/thread.html"
                }
            },
            data: {pageTitle: 'JvmThreadMonitor', pageSubTitle: '系统设置|监控系统|JVM线程监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/jvmmonitor/jvmThreadMonitorCtrl.js']);
                }]
            }
        })
        .state('system/monitor/cachemoniter', {
            url: "/system/monitor/cachemoniter",
            views: {
                "mainContentContainer": {
                    controller: "cacheMonitorCtrl",
                    templateUrl: "views/admin/system/cachemonitor/index.html"
                }
            },
            data: {pageTitle: 'CacheMonitor', pageSubTitle: '系统设置|监控系统|缓存监控'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(["../resources/admin/controllers/cachemonitor/cacheMonitorCtrl.js"]);
                }]
            }
        })

        .state('account/notifications', {
            url: "/account/notifications",
            views: {
                "mainContentContainer": {
                    controller: "notificationCtrl",
                    templateUrl: "views/admin/system/account/notification.html"
                }
            },
            data: {pageTitle: '通知消息', pageSubTitle: '账户管理|通知消息'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(["../resources/admin/controllers/notification/notificationCtrl.js"]);
                }]
            }
        })

        .state('documents/datagrid', {
            url: "/documents/datagrid",
            views: {
                "mainContentContainer": {
                    controller: "docuDataGridCtrl",
                    templateUrl: "views/admin/system/documents/docuDataGrid.html"
                }
            },
            data: {pageTitle: '文档说明', pageSubTitle: '文档说明 | Datagrid'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(["../resources/admin/controllers/documents/docuDataGridCtrl.js"]);
                }]
            }
        })
        .state('system/datagrid/datagridlist', {
            url: "/system/datagrid/datagridlist",
            views: {
                "mainContentContainer": {
                    controller: "dataGridCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'DataGrid', pageSubTitle: 'DataGrid管理|DataGrid'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/datagrid/dataGridCtrl.js',
                        '../resources/admin/controllers/datagrid/dataGridEditorCtrl.js']);
                }]
            }
        })
        .state('system/datagrid/datacolumnlist', {
            url: "/system/datagrid/datacolumnlist",
            views: {
                "mainContentContainer": {
                    controller: "dataColumnCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: 'dataColumn', pageSubTitle: 'DataGrid管理|dataColumn'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/datacolumn/dataColumnCtrl.js',
                        '../resources/admin/controllers/datacolumn/dataColumnEditorCtrl.js']);
                }]
            }
        })
        .state('system/application', {
            url: "/system/application",
            views: {
                "mainContentContainer": {
                    controller: "systemApplicationInfoCtrl",
                    templateUrl: "../html/views/admin/system/applicationinfo/index.html"
                }
            },
            data: {pageTitle: '系统信息', pageSubTitle: '系统信息'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/systemapplicationinfo/systemApplicationInfoCtrl.js']);
                }]
            }
        })
        .state('system/parameterlist', {
            url: "/system/parameterlist",
            views: {
                "mainContentContainer": {
                    controller: "parameterListCtrl",
                    templateUrl: "db/lion-form-grid.html"
                }
            },
            data: {pageTitle: '系统参数', pageSubTitle: '系统设置|系统参数'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/parameter/parameterListCtrl.js',
                        '../resources/admin/controllers/parameter/parameterEditorCtrl.js']);
                }]
            }
        })
        .state('system/department', {
            url: "/system/department",
            views: {
                "mainContentContainer": {
                    controller: "departmentCtrl",
                    templateUrl: "views/admin/system/department/departmentModifyView.html"
                }
            },
            data: {pageTitle: 'Department', pageSubTitle: '系统设置|部门管理'},
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load(['../resources/admin/controllers/department/departmentCtrl.js',
                        '../resources/admin/controllers/department/departmentEntityCtrl.js']);
                }]
            }
        })

}

/* Init global settings and run the app */
DBApp.run(["$rootScope", "settings", "$state", "$templateCache", function ($rootScope, settings, $state, $templateCache) {
    $rootScope.$state = $state; // state to be accessed from view
    $templateCache.put("db/db-form.html", "<div class=''><db-form></db-form></div>");
    $templateCache.put("db/lion-form-grid.html", "<div class=''><lion-form-grid></lion-form-grid></div>");

}]);
