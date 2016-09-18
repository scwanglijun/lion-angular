/**
 * Created by ziv.hung on 16/2/19.
 */
'use strict';

var dbDatetimePickerDirectives = angular.module('db.components.datepicker', ['dbUtils']);

dbDatetimePickerDirectives.dbDatetimePickerCaches = {};//机构树数据缓存对象

dbDatetimePickerDirectives.directive('datePicker', ['dbUtils', function (dbUtils) {
    //datetimepicker默认参数,针对settings值
    var options = {
        format:'yyyy-mm-dd',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        language:'cn',
        showMeridian: 1
    };

    return {
        restrict: 'E',
        template: '<input id="datepicker" ng-model="data.datepicker" value="datepicker" name="datepicker" type="text" placeholder="选择日期" data-date-format="yyyy-mm-dd" ng-required="true" readonly class="form-control date-picker form-control-readonly"/>',
        replace: true,
        transclude: true,
        controller: ['$scope', '$modal', function ($scope, $modal) {

            if (angular.isUndefined($scope.datePicker)) {
                $scope.datePicker = {settings: {}};
            }
            //替换默认值
            $scope.datePicker.settings = angular.extend({}, options, $scope.datePicker.settings);

            var dbDateTimeTreeSettings = $scope.datePicker.settings;

            $('#datepicker').datepicker(dbDateTimeTreeSettings);

            //获取当前时间
            $scope.datePicker.getDate=function(){
                return $('#datePicker').attr('value');
            }
        }],
        link: function (scope, element, attrs) {
            console.log("link datePicker")
        }
    }
}]);