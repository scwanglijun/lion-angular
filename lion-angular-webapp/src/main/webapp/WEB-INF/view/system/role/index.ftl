<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title> <@spring.message "sys.role.html.title"/></title>
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<!--DataTable css Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!--DataTable css End-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!-- DataTables js Start -->
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
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
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--ztree js-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<!--lang-->
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<!--role-->
<script src="${base}/resources/admin/scripts/system/role.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-2" for="nameZh" >
						<@spring.message "sys.role.query.namezh.text"/>
					</label>
					<div class="col-md-3">
						<input class="form-control input-small" type="text" size="30" name="nameZh" id="nameZh"  placeholder="<@spring.message "sys.role.query.namezh.missing.message"/>"/>					
					</div>
					<div class="col-md-7">
						<a href="javascript:void(0)" id="btnQuery" class="btn blue"><i class="fa fa-search"></i> <@spring.message "common.query.btn.text"/> </a>
					</div>
				</form>
			</div>
			<!--DataGrid start-->
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic">
					<i class="fa fa-plus"></i> 
					<@spring.message "common.toolbar.btn.add.text"/>
				</a>
				<a id="btnEdit" class="btn btn-sm red">
					<i class="fa fa-edit"></i>
					<@spring.message "common.toolbar.btn.edit.text"/>
				</a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple">
					<i class="fa fa-times"></i>
					<@spring.message "common.toolbar.btn.delete.text"/>
				</a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue">
					<i class="fa fa-refresh"></i>
					<@spring.message "common.toolbar.btn.reload.text"/>
				</a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green">
					<i class="fa  fa-file-excel-o"></i>
					<@spring.message "common.toolbar.btn.export.text"/>
				</a>
				<a href="javascript:void(0)" id="btnAuth"  class="btn btn-sm  btn-primary">
					<i class="fa  fa-gear"></i> 
					<@spring.message "common.toolbar.btn.auth.text"/>  
				</a>
			</div>
			<div class="col-md-12">
				<table class="lion-datagrids table table-striped table-bordered table-hover" id="sys_rolelist_tb" data-singleselect="true",   data-loadurl="${base}/system/role/list.json" data-checkbox="true" data-pagesize="10">
					<thead>
						<tr>
							<th class="table-checkbox" data-field='id' data-checkbox="true">
						 		<input type="checkbox" class="group-checkable" data-set="#sys_group_list_tb.checkboxes"  data-sortable="false" />
						 	</th>
							<th data-field='nameEn' data-sortDir="asc" style="width:100px;">
								角色名称(英文)
							</th>
							<th data-field="nameZh" style="width:100px;">
							 	角色名称(中文)
							</th>
							<th data-field="description" style="width:100px;">
								描述
							</th>
							<th data-field="editable" style="width:30px;" align="center"  data-formatter="formatterEidtable">
								可编辑
							</th>							
							<th data-field="createdDate" style="width:100px;">
								创建时间
							</th>
							<th data-field="updatedDate" style="width:100px;">
								更新时间
							</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--DataGrid End-->
	</div>
</div>
<!-- END PAGE CONTENT INNER -->

<!--Auth Modal Dialog Start-->
<div class="modal fade bs-modal-lg" id="modalRoleAuth" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-gear"></i> <span>角色授权</span></h4>
				<input type="hidden" id='roleId' name='id' value="">
			</div>
			<div class="modal-body">
				<div class="row">
						<div  class="col-md-12">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#tab_3_1"  data-toggle="tab">已授权信息 </a>
								</li>								
								<li>
									<a href="#tab_3_2"  data-toggle="tab">关联用户组</a>
								</li>
								<li>
									<a href="#tab_3_3"  data-toggle="tab">关联用户</a>
								</li>
								<li>
									<a href="#tab_3_4"  data-toggle="tab">关联资源</a>
								</li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="tab_3_1" style="height:450px;width:868px;">
								 
										<div class="col-md-12">	
												<i class="fa"></i><strong>已关联用户组</strong>
												<table  id="rolegroup_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",    data-loadurl="${base}/system/role/authgroups.json"   data-checkbox="true" data-pagesize="3" cellspacing="0"   data-loading="false">
													<thead>
														<tr>
															<th class="table-checkbox" data-field='id' data-checkbox="true" width="19px">
														 		<input type="checkbox" class="group-checkable" data-set="#rolegroup_list.checkboxes"  data-sortable="false"  />
														 	</th>
															<th data-field='nameEn' width="200px;" data-sortDir="asc" data-formatter="formatterCheckBox">
																用户组名称(英文)
															</th>
															<th data-field="nameZh"  width="200px;" >
															 	用户组名称(中文)
															</th>
															<th data-field="description"  width="200px;"  >
																描述
															</th>
														</tr>
													</thead>
												</table>						 						
												<i class="fa"></i><strong>已关联用户</strong>
												<table  id="roleuser_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",    data-loadurl="${base}/system/role/authusers.json"   data-checkbox="true" data-pagesize="3" cellspacing="0"   data-loading="false" >
													<thead>
														<tr>
															<th class="table-checkbox" data-field='id' data-checkbox="true" width="19px">
														 		<input type="checkbox" class="group-checkable" data-set="#roleuser_list.checkboxes"  data-sortable="false"  />
														 	</th>
															<th data-field='username' width="200px;" data-sortDir="asc" >
																用户名
															</th>
															<th data-field="realnameZh"  width="200px;" >
															 	用户姓名(中文)
															</th>
															<th data-field="employeeCode"  width="200px;">
																员工
															</th>
														</tr>
													</thead>
												</table>
										</div>				 
								</div>
								
								<div  role="tabpanel" class="tab-pane fade" id="tab_3_2">
									 <div class="row">
										<div class="col-md-12">
											<table  id="authgroup_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",   data-loadurl="${base}/system/role/groups.json"  data-loading="false"  data-checkbox="true" data-pagesize="5">
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true"   >
													 		<input type="checkbox" class="group-checkable" data-set="#authgroup_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='nameEn' width="200px;" data-sortDir="asc" data-formatter="formatterCheckBox">
															用户组名称(英文)
														</th>
														<th data-field="nameZh"  width="200px;" >
														 	用户组名称(中文)
														</th>
														<th data-field="description"  width="200px;"  >
															描述
														</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="tab_3_3">
									<div class="row">
										<div class="col-md-12">
											<table  id="authuser_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",   data-loadurl="${base}/system/role/users.json"  data-loading="false"  data-checkbox="true" data-pagesize="5">
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true"   >
													 		<input type="checkbox" class="group-checkable" data-set="#authuser_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='username' width="200px;" data-sortDir="asc" >
															用户名
														</th>
														<th data-field="realnameZh"  width="200px;" >
														 	用户姓名(中文)
														</th>
														<th data-field="employeeCode"  width="200px;">
															员工
														</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								<div  role="tabpanel" class="tab-pane fade" id="tab_3_4">
									 <div class="row">
										<div class="col-md-12" style="width:868px;height:350px;overflow-y:auto;margin-top:0;">
											<ul id="resourcetree" class="ztree" style="width:848px; overflow-y:auto;height:340px;margin-top:0;border:solid 1px #cccccc;"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal">
					<i class="fa  fa-arrow-left"></i> 取 消 </button>
				<button type="button" id="btnAuthSave" class="btn blue">
					<i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
	</div>
</div>
<!--Auth Modal Dialog End-->

<!--Edit Dialog Start -->
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">
					<i class="fa fa-plus"></i>
					<span>
						<@spring.message "sys.role.form.adddialog.text"/>
					</span>
				</h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal" name="sysRoleForm" id="sysRoleForm" method="post">
								<input type="hidden" id='id' name='id' value="">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.role.form.namezh.text"/>
										</label>
										<div class="col-md-9">
											<div class="input-group">
												<input type="text"  name="nameZh"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.role.form.namezh.missing.message"/>" size="30"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.role.form.nameen.text"/>
										</label>
										<div class="col-md-9">
											<div class="input-group">
												<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="<@spring.message "sys.role.form.nameen.missing.message"/>" size="30"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.role.form.description.text"/>
										</label>
										<div class="col-md-9">
											<div class="input-group">
												<input type="text" name="description" id="description" class="form-control" placeholder="<@spring.message "sys.role.form.description.missing.message"/>" maxlength="255" size="30"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.role.form.editable.text"/>
										</label>
										<div class="col-md-9 control-label">
											<div class="input-group">
												<input type="checkbox" class="form-control" name="editable" checked="true" value="1"/>
											</div>
										</div>
									</div>
								</div>
							</form>
						<!-- END FORM-->
				 	</div>
				 	</div>
			</div>
			<!--modal body end-->
			<!--footer start-->
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal">
					<i class="fa  fa-arrow-left"></i> 
					<@spring.message "common.diaglog.btn.cancel"/> 
				</button>
				<button type="button" id="btnSave" class="btn blue">
					<i class="fa fa-save"></i>
					<@spring.message "common.diaglog.btn.save"/>
				</button>
			</div>
			<!--footer end-->
		</div>
	</div>
</div>
<!--Edit Dialog End -->
</body>
</html>
