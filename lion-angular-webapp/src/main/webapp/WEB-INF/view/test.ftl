<#assign  base = request.contextPath/>
<#import  "/tags/lion.ftl" as lions/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>控件测试－应用管理系统</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/jquery-multi-select/css/multi-select.css"/>
<link   href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.css" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>

<script type="text/javascript" src="${base}/resources/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${base}/resources/admin/pages/scripts/components-dropdowns.js"></script>
<script type="text/javascript" src="${base}/resources/admin/pages/scripts/components-dropdowns.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${base}/resources/admin/scripts/test.js" type="text/javascript"></script>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN CONTAINER -->
 <!-- BEGIN PAGE CONTENT INNER -->
 <div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-4">
				 <div class="input-group input-medium date date-picker" data-date-format="yyyy-mm-dd" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>

			</div>
			<div class="col-md-4">
				<@lion.combobox id="sysCodeTypeList" codeName="codeType" dataClass="bootstrap-select bs-select form-control input-small" title="请选择通用编码列表" dataSize="8" multipleDataMaxOptions="1"/>
			</div>
		</div>
 	</div>
</div>
<!-- END JAVASCRIPTS -->
<script  language="javascript"  src="${base}/resources/global/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>