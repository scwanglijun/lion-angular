<#import "/WEB-INF/tags/spring.ftl" as spring>
<#assign base = request.contextPath/>
<!DOCTYPE html>
<!--
Template Name:  Newtouch Admin Dashboard Template build with Twitter Bootstrap 3.3.1
Version: 3.6.1
Author: wanglijun
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title><@spring.message "login.page.title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${base}/resources/global/css/fonts.css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${base}/resources/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${base}/resources/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${base}/resources/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<!--<link rel="shortcut icon" href="favicon.ico"/>-->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.htm">
		<img src="${base}/resources/logo/logo-login.png" alt=""/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form"  method="post"  action="${base}/login.htm">
		<h3 class="form-title">
			<@spring.message "login.dialog.title.welcome"/>
		</h3>
		<div class="alert alert-danger display-hide" <#if  login_error?length gt 0>style="display:block;"</#if> >
			<button class="close" data-close="alert"></button>
			<span>
				${login_error!}
			</span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">				
				<@spring.message "login.form.username.label.text"/>
			</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder='<@spring.message "login.form.username.label.text"/>' name="username"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">
				<@spring.message "login.form.password.label.text"/>
			</label>	 
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder='<@spring.message "login.form.password.label.text"/>' name="password"/>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-success uppercase">
				<@spring.message "login.form.button.submit"/>
			</button>
			<label class="rememberme check">
			<input type="checkbox" name="rememberMe" value="true"/>
				<@spring.message "login.form.checkbox.rememberme"/>
			</label>
			<!--<a href="javascript:;" id="forget-password" class="forget-password">
				 <@spring.message "login.form.forget.password"/>-->
			</a>
		</div>
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form class="forget-form" action="index.html" method="post">
		<h3><@spring.message "login.form.forget.password"/></h3>
		<p>
		 	 <@spring.message "login.form.forget.tip.text"/>
		</p>
		<div class="form-group">
			<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder='<@spring.message "login.form.forget.email"/>' name="email"/>
		</div>
		<div class="form-actions">
			<button type="button" id="back-btn" class="btn btn-default">
				<@spring.message "login.form.forget.backbtn"/>
			</button>
			<button type="submit" class="btn btn-success uppercase pull-right">
				 <@spring.message "login.form.forget.submit"/>
			</button>
		</div>
	</form>
	<!-- END FORGOT PASSWORD FORM -->
</div>
<div class="copyright">
<@spring.message "login.footer.copyright"/>
</div>
<!-- END LOGIN -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${base}/resources/global/plugins/respond.min.js"></script>
<script src="${base}/resources/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${base}/resources/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${base}/resources/admin/pages/scripts/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
$(function() {     
Metronic.init(); // init metronic core components
Login.init();//登录验证框架
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
