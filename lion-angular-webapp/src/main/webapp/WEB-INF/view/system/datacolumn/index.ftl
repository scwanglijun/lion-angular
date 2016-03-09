<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title><@spring.message "sys.datacolumn.html.title"/></title>
<!--DataTable css Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!--DataTable css End-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<!--lion UI css Start-->
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/dialog/lion.dialog.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combo/lion.combo.css" rel="stylesheet" type="text/css">
<style>
tfoot tr td{
  border-left:none !important;
}
</style>
<!-- DataTables js Start -->
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- DataTables js End -->
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2_locale_zh-CN.js"></script>
<!--lion UI JS Start-->
<!--lion UI css End-->
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript" ></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combonew.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--lang-->
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/datacolumn.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/casc.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-1" for="name" >
						列名
					</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="name" id="name"  placeholder="<@spring.message "sys.datacolumn.query.name.missing.message"/>"/>					
					</div>
					<label class="control-label col-md-2" for="type" >
						表格类型
					</label>
					<div class="col-md-2">
						<select  id="dataGridListType1"   name="type" 
							placeholder="<@spring.message "sys.datacolumn.query.datagrid.type.missing.message"/>..."  
							class="lion-combo form-control select2" 
							data-valuefield='codeValue'
							data-textfield='nameZh'
							data-url="${base}/system/code/combox.json?nameEn=datagrid_type">
						</select>	
					</div>
					<label class="control-label col-md-2" for="dataGridId" >
						 表格名称
					</label>
					<div class="col-md-2">
						<select  id="dataGridListType2"   name="dataGridId" 
							placeholder="<@spring.message "sys.datacolumn.query.datagrid.missing.message"/>..."  
							class="lion-combo form-control select2" 
							data-valuefield='id'
							data-textfield='title'
							data-url="${base}/system/datagrid/combox.json">
						</select>	
					</div>
					<div class="col-md-1">
						<a href="javascript:void(0)" id="btnQuery" class="btn blue"><i class="fa fa-search"></i>
							<@spring.message "common.query.btn.text"/>
						</a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i> <@spring.message "common.toolbar.btn.add.text"/>  </a>
				<a id="btnEdit" class="btn btn-sm red"><i class="fa fa-edit"></i> <@spring.message "common.toolbar.btn.edit.text"/></a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> <@spring.message "common.toolbar.btn.delete.text"/> </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> <@spring.message "common.toolbar.btn.reload.text"/>   </a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> <@spring.message "common.toolbar.btn.export.text"/> </a>
			</div>
			<div class="col-md-12"> 
				  <table class="lion-datagrids table table-striped table-bordered table-hover" 
				  		 id="datacolumn_tb" data-singleselect="true"
				  		 data-loadurl="${base}/system/datacolumn/list.json" data-checkbox="true" data-pagesize="10">
					<thead>
						<tr>
							<th class="table-checkbox" data-field="id" data-checkbox="true">
						 		<input type="checkbox" class="group-checkable" data-set="#datacolumn_tb.checkboxes"  data-sortable="false" />
						 	</th>
							<th data-field="dataGridId" style="width:90px;" data-formatter="formatterDataGridTitle">
								表格名称
							</th>
							<th data-field="name" style="width:90px;">
								列名称
							</th>
							<th data-field="field" style="width:90px;">
							  	列字段名
							</th>
							<th data-field="align" style="width:30px;">
								对齐
							</th>
							<th data-field="width" style="width:50px;" >
								宽度
							</th>
							<th data-field="showOrder" style="width:30px;" >
								顺序
							</th>
						</tr>
					</thead>
					<tfoot>
					    <tr>
					        <td class="table-checkbox" data-field="id" data-checkbox="true">
						 		
						 	</td>
							<td data-field="dataGridId" style="width:90px;" data-formatter="formatterDataGridTitle">
								
							</td>
							<td data-field="name" style="width:90px;">
								
							</td>
							<td data-field="field" style="width:90px;">
							  	
							</td>
							<td data-field="align" style="width:30px;">
								Total
							</td>
							<td data-field="width" style="width:50px;" >
								
							</td>
							<td data-field="showOrder" style="width:30px;" >
								
							</td>
					    </tr>
				    </tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
<!--Dialog Start -->
<div class="modal fade " id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i> <span><@spring.message "sys.datacolumn.form.adddialog.text"/></span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
					 	<div class="col-md-12 portlet-body form">
					 		<!-- BEGIN FORM-->
									<form action="#" class="form-horizontal" name="sysDataColumnForm" id="sysDataColumnForm" method="post">
										<input type="hidden" id='id' name='id' value="">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-3 control-label">
													 表格类型
												</label>
												<div class="col-md-5">
													 <select  id="dataGridListType"   name="type" 
													 	class="lion-casc1 form-control input-medium select2" 
													 	>
													 </select>
												</div>
												
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">
													 表格名称
												</label>
												<div class="col-md-5">
													 <select  id="dataGridList"   name="dataGridId" 
														class="lion-casc2 input-medium form-control select2" 
														>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">
													<@spring.message "sys.datacolumn.form.field.text"/>
												</label>
												<div class="col-md-5">
													<div class="input-group">
														<input type="text"  name="field"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datacolumn.form.field.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>								 
											<div class="form-group">
												<label class="col-md-3 control-label">
													<@spring.message "sys.datacolumn.form.name.text"/>
												</label>
												<div class="col-md-5">
													<div class="input-group">
														<input type="text"  id="name" name="name" maxlength="100" class="form-control" placeholder="<@spring.message "sys.datacolumn.form.name.missing.message"/>" size="30"/>
													</div>
												</div>		
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">
													显示顺序
												</label>
												<div class="col-md-5">
													<div class="input-group">
														<input type="text"  id="showOrder" name="showOrder" maxlength="100" class="form-control" placeholder="请输入顺序" size="30"/>
													</div>
												</div>
												
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">
													<@spring.message "sys.datacolumn.form.width.text"/>
												</label>
												<div class="col-md-5">
													<div class="input-group">
														<input type="text"  name="width"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datacolumn.form.width.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">
													<@spring.message "sys.datacolumn.form.align.text"/>
												</label>
												<div class="col-md-9">
													<div class="input-group radio-list">
														<label class="radio-inline">
															<input type="radio" name="align" id="align0" value="left" checked>
															left
														</label>
														<label class="radio-inline">
															<input type="radio" name="align" id="align1" value="center">
															 center
													    </label>
														<label class="radio-inline">
															<input type="radio" name="align" id="align2" value="right">
															 right
													    </label>
													</div>
												</div>
											</div>										 
										</div>
									</form>
									<!-- END FORM-->
								</div>
					 	</div>
				 	<div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"><i class="fa  fa-arrow-left"></i> <@spring.message "common.diaglog.btn.cancel"/> </button>
				<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> <@spring.message "common.diaglog.btn.save"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!--Dialog End -->
</body>
</html>
