/**
 * Created by ziv.hung on 16/2/19.
 */
'use strict';

var dbDatetimePickerDirectives = angular.module('db.components.datetimepicker', ['dbUtils']);

dbDatetimePickerDirectives.dbDatetimePickerCaches = {};//机构树数据缓存对象

dbDatetimePickerDirectives.directive('dateTime', ['dbUtils', function (dbUtils) {
    //datetimepicker默认参数,针对settings值
    var options = {
        format:'yyyy-mm-dd',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    };

    return {
        restrict: 'E',
        template: "<input id='date' class='span2' size='16' type='text' readonly value='2012-02-12'>",
        replace: true,
        transclude: true,
        scope:true,
        controller: ['$scope', '$modal', function ($scope, $modal) {



            if (angular.isUndefined($scope.dateTime)) {
                $scope.dateTime = {settings: {}};
            }
            //替换默认值
            $scope.dateTime.settings = angular.extend({}, options, $scope.dateTime.settings);

            var dbDateTimeTreeSettings = $scope.dateTime.settings;

            //$('#date').datetimepicker(options);
            $('#date').datetimepicker(options);

            $.fn.datetimepicker.dates['zh-CN'];

            //获取当前时间
             $scope.dateTime.getTime=function(){
                return $('#date').attr('value');
            }
        }],
        link: function (scope, element, attrs) {
            console.log("link dateTime")
        }
    }
}]);