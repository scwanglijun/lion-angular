var DBApp = angular.module('DBApp');

DBApp.controller("accountProfileCtrl", ['$scope','$modal', 'dbUtils',accountProfileCtrl]);
function accountProfileCtrl($scope, $modal, dbUtils) {
	console.log($scope);
	dbUtils.post("system.account.profile",{id: '1'},function (data) {
		alert("kkk");
	    console.log(data);
	    });
}