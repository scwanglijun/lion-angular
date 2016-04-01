var DBApp = angular.module('DBApp');

DBApp.controller("accountDataCtrl", ['$scope','$modal', 'dbUtils',accountDataCtrl]);

function accountDataCtrl($scope, $modal, dbUtils) {
	dbUtils.post("system.account.personaldata",{id: '1'},function (data) {
		alert("pp");
	    console.log(data);
	    });
}