<#import "/WEB-INF/tags/lion.ftl" as lion>
<#import "/WEB-INF/tags/spring.ftl" as spring>
<!DOCTYPE html>
<!-- 
Template Name: Newtouch Admin Dashboard Template build with Twitter Bootstrap 3.3.1
Version: 1.0
Author: wanglijun
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>${title}| Application Management System </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${base}/resources/global/css/fonts.css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${base}/resources/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/morris/morris.css" rel="stylesheet" type="text/css">
<!-- END PAGE LEVEL PLUGIN STYLES -->

<!-- BEGIN PAGE STYLES -->
<link href="${base}/resources/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
${head}
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="${base}/resources/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/admin/layout4/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/admin/layout4/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${base}/resources/admin/layout4/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<#include "header.ftl"/> 
<!-- BEGIN PAGE -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<#include "menu.ftl"/> 
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<#include "navigationbar.ftl">
			<!-- BEGIN PAGE CONTENT INNER -->
		 	${body}
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- END PAGE --> 
<!-- BEGIN FOOTER -->
<#include "footer.ftl"> 
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${base}/resources/global/plugins/respond.min.js"></script>
<script src="${base}/resources/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${base}/resources/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${base}/resources/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/global/js/lang/<@spring.message "browser.language"/>.js" type="text/javascript"></script>
<script src="${base}/resources/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${base}/resources/admin/layout4/scripts/layout.js" type="text/javascript"></script>
<script src="${base}/resources/admin/pages/scripts/tasks.js" type="text/javascript"></script>
${javascript!}
<!-- END PAGE LEVEL SCRIPTS 
<script>
$(document).ready(function() { 
Metronic.init(); // init metronic core componets
Layout.init(); // init layout
Tasks.initDashboardWidget(); // init tash dashboard widget  
});
</script>-->
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
