<#assign  base = request.contextPath/>
<#import  "/tags/lion.ftl" as lions/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>控件测试－应用管理系统</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>

<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2_locale_zh-CN.js"></script>
</script>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/jquery-multi-select/css/multi-select.css"/>


<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combonew.js" type="text/javascript"></script>
<!--lion UI JS End-->

<script src="${base}/resources/admin/scripts/combo-test.js" type="text/javascript"></script>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN CONTAINER -->
 <!-- BEGIN PAGE CONTENT INNER -->
 <div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12">
				 <form action="#" class="form-horizontal form-row-sepe">
			<div class="col-md-12 margin-bottom-10">
			 		<div class="form-group">
						<label class="control-label col-md-3">Extra Large</label>
						<div class="col-md-4">
							<select id="select2_sample1" class="form-control input-medium select2" data-placeholder="请选择...">
								<option value=""></option>
								<option value="AL"  selected>Alabama</option>
								<option value="WY">Wyoming</option>
							</select>
						</div>
					</div>

			</div>
			<div class="col-md-12 margin-bottom-10">
			 		<div class="form-group">					 	
						 <a id="btnGetVal" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i>获取选中的值</a>
				<a id="btnChangeVal" class="btn btn-sm red"><i class="fa fa-edit"></i>改变值</a>
				<a href="javascript:void(0)" id="btnData" class="btn btn-sm purple"><i class="fa fa-times"></i> 加载数据 </a>
				<a href="javascript:void(0)" id="btnClear" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 清除内容  </a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> 获取所有数据 </a>
					</div>

			</div>
			<div class="col-md-12 margin-bottom-10">
			 		<div class="form-group">
						<label class="control-label col-md-3">Extra Large</label>
						<div class="col-md-4">
							<select id="select2_sample2" class="lion-combo form-control input-medium select2"   placeholder="请选择参数列表..."  data-valueField='codeValue'   value=""
														 	data-textField='nameZh' data-url="${base}/system/code/combox.htm?nameEn=SystemParamter">
								<option value=""></option>
								<option value="AL">Alabama</option>
								<option value="WY">Wyoming</option>
							</select>
						</div>
					</div>

			</div>
			<div class="col-md-12 margin-bottom-10">
			 	<div class="form-group">					 	
						 <a id="btnGetValCombo" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i>获取选中的值</a>
				<a id="btnChangeValCombo" class="btn btn-sm red"><i class="fa fa-edit"></i>改变值</a>
				<a href="javascript:void(0)" id="btnDataCombo" class="btn btn-sm purple"><i class="fa fa-times"></i> 加载数据 </a>
				<a href="javascript:void(0)" id="btnClearCombo" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 清除内容  </a>
				<a href="javascript:void(0)" id="btnGetAllDataCombo"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> 获取所有数据 </a>
					</div>

			</div>
			<div class="col-md-12 margin-bottom-10">
			 		<div class="form-group">
						<label class="control-label col-md-3">Extra Large</label>
						<div class="col-md-4">
				<select  id="sys_resource_icon"  name="icon" placeholder="请选择资源图标..."   class="lion-combo form-control select2 select2-offscreen " data-icon="true" data-valueField='iconClass'  data-textField='iconClass' data-URL="${base}/system/code/icon.json?iconType=RESOURCE_ICON">
																</select>
					</div>
					</div>

			</div>
			<div class="col-md-12 margin-bottom-10">
			 		<div class="form-group">
						<label class="control-label col-md-3">Extra Large</label>
						<div class="col-md-4">
							<select id="select2_sample3" class="form-control input-medium select2" data-placeholder="请选择...">
								<option value=""></option>
								<option value="AL">Alabama</option>
								<option value="WY">Wyoming</option>
							</select>
						</div>
					</div>

			</div>
				</form>
		</div>
 	</div>
</div>
<!-- END JAVASCRIPTS -->
</body>
</html>