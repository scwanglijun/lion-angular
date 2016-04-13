var DBApp = angular.module('DBApp',["ngImgCrop"]);

DBApp.controller("accountProfileCtrl", ['$scope','$modal', 'dbUtils',accountProfileCtrl]);
function accountProfileCtrl($scope, $modal, dbUtils) {
	dbUtils.post("system.account.profile",{id: '1'},function (data) {
		$scope.data = data;

		$scope.myImage='';
		$scope.myCroppedImage='';

		var handleFileSelect=function(evt) {
			var file=evt.currentTarget.files[0];
			var reader = new FileReader();
			reader.onload = function (evt) {
				$scope.$apply(function($scope){
					$scope.myImage=evt.target.result;
				});
			};
			reader.readAsDataURL(file);
		};
		angular.element(document.querySelector('#image')).on('change',handleFileSelect);
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
	$scope.PreviewImage = function(imgFile){
		var filextension=imgFile.value.substring(imgFile.value.lastIndexOf("."),imgFile.value.length);
		filextension=filextension.toLowerCase();
		if ((filextension!='.jpg')&&(filextension!='.gif')&&(filextension!='.jpeg')&&(filextension!='.png')&&(filextension!='.bmp')){
			alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
			imgFile.focus();
		}
		else{
			var path;
			if(document.all){
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgDiv").innerHTML="";
				document.getElementById("imgDiv").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
			}
			else{
				path=window.URL.createObjectURL(imgFile.files[0]);// FF 7.0以上
				//path = imgFile.files[0].getAsDataURL();// FF 3.0
				alert(path);
				document.getElementById("imgDiv").innerHTML = "<img id='img1' width: 300px  height: 300px  src='"+path+"'/>";
				//document.getElementById("img1").src = path;
			}
		}
	}
	$scope.btnImgUploadSave = function(isValid){
		if(isValid){
			var reqBody = angular.copy($scope.image);
			alert($scope.image);
		}
	}
}