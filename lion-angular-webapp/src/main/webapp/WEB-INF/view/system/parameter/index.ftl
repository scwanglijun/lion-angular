<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>系统参数管理</title>
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<!--DataTable css Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!--DataTable css End-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!-- DataTables js Start -->
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2_locale_zh-CN.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- DataTables js End -->
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combonew.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--lion UI JS End-->
<!--lang-->
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<!--parameter-->
<script src="${base}/resources/admin/scripts/system/parameter.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-2" for="nameZh" >名称(中文)</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="nameZh" id="nameZh"  placeholder="请输入参数名称"/>					
					</div>
					<label class="control-label col-md-2" for="sys_parameter_type" >参数类型</label>
					<div class="col-md-3">
					     <select  id="parameterCodeList" name="type"  placeholder="请选择参数列表..."  
						 	class="lion-combo form-control select2 input-small" data-valuefield='codeValue' 
						 	data-textfield='nameZh' data-url="${base}/system/code/combox.htm?nameEn=SystemParamter">
						 </select>
					</div>
					<div class="col-md-1 ">
						<a href="javascript:void(0)" id="btnQuery" class="btn blue"><i class="fa fa-search"></i> 查 询 </a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i> 新增  </a>
				<a id="btnEdit" class="btn btn-sm red"><i class="fa fa-edit"></i> 编辑</a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> 删除 </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 刷新  </a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> Excel </a>
			</div>
			<div class="col-md-12">
				  <!---<@lion.datagrids name="sys_parameter_lists_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/parameter/list.json" dataOptions="" style="height:400px;"/>-->
				  <table class="lion-datagrids table table-striped table-bordered table-hover" id="sys_parameter_lists_tb" 
				  data-singleselect="true",   
				  data-loadurl="${base}/system/parameter/list.json" 
				  data-checkbox="true" 
				  data-pagesize="10" width="100%">
					<thead>
						<tr>
						  <th class="table-checkbox"  style="width:30px;" data-field='id' data-checkbox="true">
						 		<input type="checkbox"  class="group-checkable" data-set="#sys_parameter_lists_tb.checkboxes"  data-sortable="false" />
						 	</th>
							<th data-field='type'  style="width:80px;" data-formatter="formatterCodeList">
								参数类型
							</th>
							<th data-field="nameZh" style="width:80px;">
							 	名称(中文)
							</th>
							<th data-field="nameEn" style="width:80px;">
								名称(英文)
							</th>
							<th data-field="value" style="width:150px;" align="center">
								值
							</th>
							<th data-field="description" style="width:100px;">
								描述
							</th>						
							<th data-field="editable" style="width:30px;" data-formatter="formatterEidtable">
								可编辑
							</th>
							 					
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
<!--Edit Dialog Start -->
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i><span>系统参数添加</span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal" name="sysParameterForm" id="sysParameterForm" method="post">
											<input type="hidden" id='id' name='id' value="">
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label">参数类型</label>
													<div class="col-md-5">
									<select  id="addParameterCodeList"  name="type" placeholder="请选择参数列表..."   class="lion-combo form-control select2  " data-valuefield='codeValue'  data-textfield='nameZh' data-url="${base}/system/code/combox.htm?nameEn=SystemParamter"></select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">参数名称(中文)</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text"  name="nameZh"  maxlength="100" class="form-control" placeholder="请输入参数名称（中文）" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">参数名称(英文)</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="请输入参数名称（英文）" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">参数值</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text" name="value" class="form-control" placeholder="请输入参数值" maxlength="255" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">描述</label>
													<div class="col-md-5">
														<div class="input-group ">
															<input type="text" class="form-control" name="description" placeholder="请输入参数描述" maxlength="255" size="40"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">是否可编辑</label>
													<div class="col-md-5 control-label">
														<div class="input-group">
															<input type="checkbox" class="form-control "  name="editable" checked="true" />
														</div>
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
				<button type="submit" id="btnSave" class="btn blue"><i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</body>
</html>
