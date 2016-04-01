var DBApp = angular.module('DBApp');

DBApp.controller("accountProfileCtrl", ['$scope','$modal', 'dbUtils',accountProfileCtrl]);

function accountProfileCtrl($scope, $modal, dbUtils) {
	dbUtils.post("system.account.profile",{id: '1'},function (data) {
		alert("pp");
	    console.log(data);
	    });
}