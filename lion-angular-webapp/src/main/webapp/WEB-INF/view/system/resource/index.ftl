<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>资源管理</title>
<!--zTree css Start-->
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--EasyUI css Start-->
<link href="${base}/resources/global/plugins/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
<!--EasyUI css End-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<!--lion UI css Start-->
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/dialog/lion.dialog.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combo/lion.combo.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!--lion UI css End-->
<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2_locale_zh-CN.js"></script>
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combonew.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>>
<!--lion UI JS End-->

<!--lang-->
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>script>
<script src="${base}/resources/admin/scripts/system/resource.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic">
					<i class="fa fa-plus"></i>  <@spring.message "common.toolbar.btn.add.text"/> </a>
				<a id="btnEdit" class="btn btn-sm red" role="button">
					<i class="fa fa-edit"></i>
					 <@spring.message "common.toolbar.btn.edit.text"/> 				 
				</a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> <@spring.message "common.toolbar.btn.delete.text"/> </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue">
					<i class="fa fa-refresh"></i> <@spring.message "common.toolbar.btn.reload.text"/> 
				</a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green">
					<i class="fa  fa-file-excel-o"></i> <@spring.message "common.toolbar.btn.export.text"/> 
				</a>
			</div>
			<div class="col-md-12">
				<table  id="sys_resource_lists_tb" class="easyui-treegrid" fit="false" fitColumns="true"    style="height:400px;width:auto"
				data-options="
				url: '${base}/system/resource/list.json',               
                rownumbers: true,
                pagination: false,
                pageSize: 15,
                pageList: [5,10,15,30],
                idField: 'id',
                treeField: 'nameZh'
            ">
				<thead>
					<tr>
						<th field="id"  width="10" align="center" halign="center"checkbox="true"sortable="true"order="asc">id</th>
				 		<th field="nameZh" width="80" align="left" halign="left"sortable="true"order="asc">资源名称(中文)</th>
				 		<th field="type" width="40" align="left" halign="left"sortable="true"order="asc" formatter="formatterCodeResource">资源类型</th>
				 		<th field="path"   width="200" align=""left"" halign="left"sortable="true"order="asc">路径</th>
				 		<th field="permission"  width="80" align="left" halign="center"sortable="true"order="asc" >权限</th>
				 		<th field="seqNum"  width="20" align="left" halign="center"sortable="true"order="asc">排序</th>
					</tr>
				</thead>
			</table>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
<!--Edit Dialog Start -->
<div class="modal fade bs-modal-lg" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog  modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i><span>资源添加</span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal" name="addform" id="addform" method="post">
											<input type="hidden" id="id" name="id" value="">
											<div class="form-body">
											
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">父级资源</label>
														<div class="col-md-4">
															<input  id="parentResourceId"  name="parentResourceId"  
						 	  	  placeholder="请选择父级资源…"  type="text" 
						 		  class="lion-combotree form-control"   data-loadurl="${base}/system/resource/combotree.json" data-width="230px" data-height="300px"/>
														</div>
													</div>
													<div class="form-filed">
														<label class="col-md-2 control-label">资源类型</label>
														<div class="col-md-4">
															<select  id="sysresourcetype"  name="type" placeholder="请选择参数列表..."   class="lion-combo form-control select2  " data-valuefield='codeValue'  data-textfield='nameZh' data-url="${base}/system/code/combox.htm?nameEn=ResourceType">
															</select>							 
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">资源名称(中文)</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text"  id="nameZh" name="nameZh" maxlength="128" class="form-control" placeholder="请输入资源名称（中文）" size="30"/>
															</div>
														</div>
													</div>
													<div class="form-filed">
														<label class="col-md-2 control-label">资源名称(英文)</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text"  id="nameEn" name="nameEn" maxlength="128" class="form-control" placeholder="请输入资源名称（英文）" size="30"/>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													
												</div>
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">路径</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text"  id="path" name="path" maxlength="1024" class="form-control" placeholder="请输入资源路径" size="30"/>
															</div>
														</div>
													</div>
													<div class="form-filed">
														<label class="col-md-2 control-label">Target</label>
														<div class="col-md-4">														 
																<select  id="sysresourcetarget"  name="target" placeholder="请选择参数列表..."   class="lion-combo form-control select2  " data-valuefield='codeValue'  data-textfield='nameZh' data-url="${base}/system/code/combox.htm?nameEn=Target">
																</select>	
															 
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">
															权限配置
														</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text"  id="permission" name="permission" maxlength="128" class="form-control" placeholder="请输入权限配置" size="30"/>
															</div>
														</div>
													</div>
													<div class="form-filed">
														<label class="col-md-2 control-label">显示顺序</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text"  id="seqNum" name="seqNum" maxlength="11" class="form-control" placeholder="请输入示顺序" size="30"/>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">资源描述</label>
														<div class="col-md-4">
															<div class="input-group">
																<input type="text" name="value" class="form-control" placeholder="请输入资源描述" maxlength="255" size="30"/>
															</div>
														</div>
													</div>
													<div class="form-filed">
														<label class="col-md-2 control-label">是否可编辑</label>
														<div class="col-md-4 control-label">
															<div class="input-group">
																<input type="checkbox" class="form-control "  name="editable" checked="true" />
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="form-filed">
														<label class="col-md-2 control-label">资源图标</label>
														
														<div class="col-md-3">

																<select  id="sys_resource_icon"  name="icon" placeholder="请选择资源图标..."   class="lion-combo form-control select2 "   data-valuefield='iconClass'  data-textfield='iconClass' data-url="${base}/system/code/icon.json?iconType=RESOURCE_ICON">
																</select>
														</div>
														<label class="control-label"><span id="span_resource_icon"></span></label>
													</div>

												</div>
											</div>
										</form>
									<!-- END FORM-->
							</div>
				 	</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"><i class="fa  fa-arrow-left"></i> 取 消 </button>
				<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	
	<!-- /.modal-dialog -->
<!--Edit Dialog End -->
</body>
</html>
