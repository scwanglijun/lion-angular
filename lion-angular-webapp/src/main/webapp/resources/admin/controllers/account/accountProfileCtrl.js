var DBApp = angular.module('DBApp');

DBApp.controller("accountProfileCtrl", ['$scope','$modal', 'dbUtils',accountProfileCtrl]);
function accountProfileCtrl($scope, $modal, dbUtils) {
	dbUtils.post("system.account.profile",{id: '1'},function (data) {
		$scope.realnameZh=data.realnameZh;
		$scope.realnameEn=data.realnameEn;
		$scope.mobile=data.mobile;
		$scope.telephone=data.telephone;
		$scope.officePhone=data.officePhone;
		$scope.fax=data.fax;
		$scope.postcode=data.postcode;
		$scope.location=data.location;
		$scope.description=data.description;
	});
	$scope.btnBaseInfoSave = function(isValid){
		alert($scope.data);
		//dbUtils.post('system.account.profile.edit',reqBody, function (data) {
		//	dbUtils.success('操作成功!');
		//}, function (error) {
		//	dbUtils.error(error);
		//});
	}

}