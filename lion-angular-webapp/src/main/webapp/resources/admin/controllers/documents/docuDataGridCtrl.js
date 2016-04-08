/**
 * Created by wash on 16/3/31.
 */
var DBApp = angular.module('DBApp');

DBApp.controller("docuDataGridCtrl", ['$scope', '$http',DocuDataGridCtrl]);

function DocuDataGridCtrl($scope, $http) {
    var vm = $scope.vm = {
        htmlSource : "",
        cssSource:"",
        jsSource:""
    };

    $http.get("../resources/admin/controllers/datagrid/dataGridCtrl.js").success(function (result) {
        vm.jsSource = result;
    });

    $http.get("../resources/templates/lionFormGrid.html").success(function (result) {
        vm.htmlSource = result;
    });

    $http.get("../resources/css/login.css").success(function (result) {
        vm.cssSource = result;
    });
}
