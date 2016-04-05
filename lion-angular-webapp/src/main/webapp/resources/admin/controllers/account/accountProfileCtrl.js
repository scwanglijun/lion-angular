var DBApp = angular.module('DBApp');

DBApp.controller("accountProfileCtrl", ['$scope','$modal', 'dbUtils',accountProfileCtrl]);
function accountProfileCtrl($scope, $modal, dbUtils) {
	dbUtils.post("system.account.profile",{id: '1'},function (data) {
		$scope.data = data;
	});

	$scope.btnBaseInfoSave = function(isValid) {
		$scope.submited = true;
		if (isValid) {
			var reqBody = angular.copy($scope.data);

			dbUtils.post('system.account.edit',reqBody, function (data) {
				dbUtils.success('修改用户信息成功!');
			}, function (error) {
				dbUtils.error(error);
			});
		}
	}
	$scope.btnChanagePwdSave = function(isValid){
		$scope.submited =true;
		if (isValid) {
			var reqBody = angular.copy($scope.pwd);
			if($scope.pwd.password==$scope.pwd.confirmpassword){
				dbUtils.post('system.account.editPassword',reqBody, function (pwd) {
					dbUtils.success('修改密码成功!');
				}, function (error) {
					dbUtils.error(error);
				});
			}
			else{
				dbUtils.warning("密码不一致！");
			}

		}
	}
	$scope.cancel=function(){
		$modal.dismiss('cancel');
	}
	$scope.show = function(t){

	}
}