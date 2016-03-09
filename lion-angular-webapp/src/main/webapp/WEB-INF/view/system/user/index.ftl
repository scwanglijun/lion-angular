<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>用户管理</title>
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--DataTable css Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!--DataTable css End-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!--datepicker Csss Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<!--datepicker Csss End-->
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
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<!--ztree js-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/lang/<@spring.message "browser.language"/>.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combo.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--lang-->
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/admin/scripts/system/user.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-1" for="username" >用户名</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="username" id="username"  placeholder="请输入用户名称"/>					
					</div>
					<label class="control-label col-md-1" for="queryEmployeeCode" >员工号</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="employeeCode" id="queryEmployeeCode"  placeholder="请输入员工号称"/>					
					</div>
					<label class="control-label col-md-1" for="queryemail" >邮箱</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="email" id="queryemail"  placeholder="请输入邮箱称"/>					
					</div>	
					<div class="col-md-1">
						<a href="javascript:void(0)" class="btn blue" id="btnQuery"><i class="fa fa-search"></i>
						 <@spring.message "common.query.btn.text"/> 
						</a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12" id="toolbar">
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
				<a href="javascript:void(0)" id="btnAuth"  class="btn btn-sm  btn-primary">
					<i class="fa  fa-gear"></i> 
					<@spring.message "common.toolbar.btn.auth.text"/>  
				</a>
				<a href="javascript:void(0)" id="btnLokced" class="btn btn-sm red" >
				    <i class="fa fa-lock"></i> 
					<@spring.message "common.toolbar.btn.locked.text"/>
				</a>
				<a href="javascript:void(0)" id="btnUnlock" class="btn btn-sm blue-hoki">
					<i class="fa fa-unlock-alt"></i> 
					<@spring.message "common.toolbar.btn.unlock.text"/>
				</a>
				<a href="javascript:void(0)" id="btnResetPwd" class="btn btn-sm purple">
					<i class="fa fa-unlock-alt"></i> 
					<@spring.message "common.toolbar.btn.reset.pwd.text"/>
				</a>
				<a href="javascript:void(0)" id="btnDetails" class="btn btn-sm default">
					<i class="fa  fa-th"></i> 
					<@spring.message "common.toolbar.btn.details.text"/>
				</a> 
			</div>
			<div class="col-md-12">
				<table class="lion-datagrids table table-striped table-bordered table-hover" id="sys_user_list_tb" data-singleselect="true",  
				 data-loadurl="${base}/system/user/list.json" data-checkbox="true" data-pagesize="10">
					<thead>
						<tr>
						  <th class="table-checkbox" data-field='id' data-checkbox="true"  style="width:30px;">
						 		<input type="checkbox" class="group-checkable" data-set="#sys_user_list_tb.checkboxes"  data-sortable="false" />
						 	</th>
							<th data-field='username' data-sortDir="asc"    style="width:100px;">
								用户名
							</th>
							<th data-field="realnameZh" style="width:100px;">
							 	姓名(中文)
							</th>
							<th data-field="realnameEn" style="width:100px;">
								姓名(英文)
							</th>
							<th data-field="employeeCode" style="width:100px;" align="center">
								员工号
							</th>
							<th data-field="department" style="width:100px;" data-formatter="formatterDepartment">
								所属部门
							</th>
							<th data-field="accountLocked" style="width:50px;"  data-formatter="formatterAccountLocked">
								锁定状态
							</th>
							<th data-field="accountExpired" style="width:50px;" data-formatter="formatterEidtable">
								账户状态
							</th>
							<th data-field="accountExpiredDate" style="width:100px;" data-formatter="formatterDate">
								账户有效日期
							</th>							
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->

<!--Modal Auth Start-->
<div class="modal fade bs-modal-lg" id="modalUserAuth" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-gear"></i> <span>用户授权</span></h4>
			</div>
			<div class="modal-body">
				<div class="row">
						<div  class="col-md-12">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#tab_3_1" data-toggle="tab">已授权信息 </a>
								</li>
								<li>
									<a href="#tab_3_2" data-toggle="tab">关联用户组 </a>
								</li>
								<li>
									<a href="#tab_3_3" data-toggle="tab">关联角色</a>
								</li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="tab_3_1">
									<div class="col-md-12">
										<table class="table table-bordered table-hover" width="853px" style="display:none">
											<thead>
												<tr>
													<th>用户名</th>
													<th>工号</th>
													<th>账户锁定状态</th>
													<th>账户有效日期</th>
													<th>账户有效状态</th>
													<th>密码有效日期</th>
													<th>密码有效状态</th>
													<th>所属部门</th>
												</tr>
											</thead>
											<tbody>
											  <tr>
											  	<td><span id="auth_username"></span></td>
												<td><span id="auth_employeeCode"></span></td>
												<td><span id="auth_accountLocked"></span></td>
												<td><span id="auth_accountExpiredDate"></span></td>
												<td><span id="auth_accountExpired"></span></td>
												<td><span id="auth_credentialExpiredDate"></span></td>
												<td><span id="auth_credentialExpired"></span></td>
												<td><span id="auth_department"></span></td>
											  </tr>								 
											</tbody>
										</table>
				  					</div>
									<div class="col-md-12">
											<div class="caption">
												<i class="fa"></i><strong>已关联用户组</strong>
											</div>									 
												<table  id="usergroup_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",    data-loadurl="${base}/system/user/authgroup.json"  data-checkbox="true" data-pagesize="3" cellspacing="0" data-loading="false">
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true" >
													 		<input type="checkbox" class="group-checkable" data-set="#usergroup_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='nameEn' data-sortDir="asc"    style="width:200px;">
															用户组名称(英文)
														</th>
														<th data-field="nameZh" style="width:200px;">
														 	用户组名称(中文)
														</th>
														<th data-field="description" style="width:200px;">
															描述
														</th>
													</tr>
												</thead>
											</table>
									</div>
									<div class="col-md-12">
										<div class="caption">
											<i class="fa"></i><strong>已关联角色</strong>
										</div>
										 <table  id="userrole_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",     data-loadurl="${base}/system/user/authroles.json"   data-checkbox="true" data-pagesize="3"     cellspacing="0"   data-loading="false" >
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true" >
													 		<input type="checkbox" class="group-checkable" data-set="#userrole_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='nameEn' data-sortDir="asc"    style="width:200px;">
															角色名称(英文)
														</th>
														<th data-field="nameZh" style="width:200px;">
														 	角色组名称(中文)
														</th>
														<th data-field="description" style="width:200px;">
															描述
														</th>
													</tr>
												</thead>
											</table>
									</div>
								</div>
								<div  role="tabpanel" class="tab-pane fade" id="tab_3_2">
									 <table  id="authgroup_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",     data-loadurl="${base}/system/user/groups.json"   data-checkbox="true" data-pagesize="5"  data-loading="false" >
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true"   >
													 		<input type="checkbox" class="group-checkable" data-set="#authgroup_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='nameEn' data-sortDir="asc"    style="width:200px;">
															用户组名称(英文)
														</th>
														<th data-field="nameZh" style="width:200px;">
														 	用户组名称(中文)
														</th>
														<th data-field="description" style="width:200px;">
															描述
														</th>
													</tr>
												</thead>
											</table>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="tab_3_3">
									<table  id="authrole_list" class="lion-datagrids table table-striped table-bordered table-hover" data-singleselect="false",   data-loadurl="${base}/system/user/roles.json"   data-checkbox="true" data-pagesize="5"   data-loading="false">
												<thead>
													<tr>
														<th class="table-checkbox" data-field='id' data-checkbox="true"   >
													 		<input type="checkbox" class="group-checkable" data-set="#authrole_list.checkboxes"  data-sortable="false"  />
													 	</th>
														<th data-field='nameEn' data-sortDir="asc"    style="width:200px;">
															角色名称(英文)
														</th>
														<th data-field="nameZh" style="width:200px;">
														 	角色组名称(中文)
														</th>
														<th data-field="description" style="width:200px;">
															描述
														</th>
													</tr>
												</thead>
											</table>
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
<!--Modal Auth End-->

<!--Modal UserInfo Start-->
<div class="modal fade bs-modal-lg" id="modaluserinfo" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-th"></i> <span>用户明细信息</span></h4>
			</div>
			<div class="modal-body">
					<div class="caption">
							<i class="fa"></i><strong>基本信息</strong>
					</div>
			 		<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>用户名</th>
									<th>员工号</th>
									<th>性别</th>							
									<th>真实姓名(中文)</th>
									<th>真实姓名(英文)</th>
									<th>联系电话</th>
									<th>手机</th>
									<th>邮箱</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td><span id="user_username"></span></td>
									<td><span id="user_employeeCode"></span></td>
									<th><span id="user_gender"></span></th>	
									<td><span id="user_realnameZh"></span></td>
									<td><span id="user_realnameEn"></span></td>
									<td><span id="user_telephone"></span></td>
									<td><span id="user_mobile"></span></td>
									<td><span id="user_email"></span></td>
								</tr>								 
							</tbody>
						</table>
					</div>
					<div class="caption">
							<i class="fa"></i><strong>账户状态</strong>
					</div>
					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>账户锁定状态</th>
									<th>账户有效日期</th>
									<th>账户有效状态</th>
									<th>密码有效日期</th>
									<th>密码有效状态</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td><span id="user_accountLocked"></span></td>
									<td><span id="user_accountExpiredDate"></span></td>
									<td><span id="user_accountExpired"></span></td>
									<td><span id="user_credentialExpiredDate"></span></td>
									<td><span id="user_credentialExpired"></span></td>
								</tr>								 
							</tbody>
						</table>
				   </div>
				   	<div class="caption">
							<i class="fa"></i><strong>其它信息</strong>
					</div>
					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>所属门部</th>
									<th>办公室电话</th>
									<th>传真</th>
									<th>邮编</th>
									<th>地址</th>
									<th>描述</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td><span id="user_department"></span></td>
									<td><span id="user_officePhone"></span></td>
									<td><span id="user_fax"></span></td>
									<td><span id="user_postcode"></span></td>
									<td><span id="user_location"></span></td>
									<td><span id="user_description"></span></td>
								</tr>					 
							</tbody>
						</table>
				  </div>
			</div>
		</div>
	</div>
</div>
<!--Modal UserInfo End-->

<!--Dialog Start -->
<div class="modal fade bs-modal-lg " id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i><span>添加用户</span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
					 	<div class="col-md-12 portlet-body form">
					 		<!-- BEGIN FORM-->
									<form action="#" id="addForm" class="form-horizontal">
									    <input type="hidden" id='id' name='id' value="">
										<div class="form-body">
											<div class="form-group">
												<div class="form-filed">
													<label class="col-md-2 control-label" for="username">用户名<i class="fa required">*</i></label>
													<div class="col-md-3">
														<div class="input-group">
															<input type="text"  id="username" name="username"  maxlength="100" class="form-control" placeholder="请输入用户名" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
													<label class="col-md-2 control-label" for="employeeCode">员工号</label>
													<div class="col-md-2">
														<div class="input-group">
															<input   type="text"  id="employeeCode" name="employeeCode" maxlength="100" class="form-control" placeholder="请输入员工号" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
												<label class="col-md-1 control-label">性别</label>
													<div class="col-md-2">
														<div class="input-group radio-list">	
																<label class="radio-inline">
																	<input type="radio" name="gender" id="gender0" value="0" checked>
																	男
																</label>
																<label class="radio-inline">
																	<input type="radio" name="gender" id="gender1" value="1">
																	 女 
															    </label>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="form-filed">
													<label class="col-md-2 control-label" for="email">邮箱</label>
													<div class="col-md-4">
														<div class="input-group">
															<input type="text"  id="email" name="email" maxlength="100" class="form-control" placeholder="请输入邮箱" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
													<label class="col-md-2 control-label" for="departmentId">所属部门</label>
													<div class="col-md-4">
														<div class="input-group">
															 <input  id="departmentId"  name="departmentId"  
					 	  	  placeholder="请选择部门…"  type="text" 
					 		  class="lion-combotree form-control"   data-loadurl="${base}/system/department/comboxtree.json" data-width="200px" data-height="300px"/>
														</div>
													</div>
												</div>												
											</div>								 
											<!--<div class="form-group">
												<label class="col-md-2 control-label">密码</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control"  placeholder="请输入密码" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">确认密码</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="请输入输入确认密码" size="30"/>
													</div>
												</div>												
											</div>-->
											<div class="form-group">									
												<label class="col-md-2 control-label">密码有效期</label>
												<div class="col-md-2">

												 <div id="1"  class="input-group input-small date date-picker" data-date-format="yyyy-mm-dd" data-date-start-date="+0d">
														<input type="text"   id='credentialExpiredDate' name="credentialExpiredDate" class="form-control" placeholder="请选择密码有效期" maxlength="25" size="20" readonly="true" value="${credentialExpiredDate!}" />
													<span class="input-group-btn">
														<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</div>
												<label class="col-md-2 control-label">
													密码是否有效 
													<input type="checkbox" id="credentialExpired" class="form-control"  name="credentialExpired" checked="true" value="1"/>						 
												</label>
												<label class="col-md-2 control-label">账户有效期</label>
												<div class="col-md-2">

													 <div  id="2" class="input-group input-small date date-picker" data-date-format="yyyy-mm-dd" data-date-start-date="+0d">
														<input id="accountExpiredDate"  name="accountExpiredDate" type="text" class="form-control"  placeholder="请选择账户有效期"  maxlength="25" size="20"  readonly="true"  value="${accountExpiredDate!}" />
														<span class="input-group-btn">
														<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</div>
												<label class="col-md-2 control-label">
													账户状态：
													<input type="checkbox" class="form-control" id="accountExpired" name="accountExpired" checked="true"  value="1" />  
												</label>
											</div>			
											<div class="form-group">
												<label class="col-md-2 control-label">真实姓名(中文)</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="realnameZh" name="realnameZh" maxlength="128" class="form-control" placeholder="请输入真实姓名（中文）" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">真实姓名(英文)</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="realnameEn" name="realnameEn" maxlength="128" class="form-control" placeholder="请输入真实姓名（英文）" size="30"/>
													</div>
												</div>
											</div>										 
											<div class="form-group">
												<label class="col-md-2 control-label">电话</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="telephone" class="form-control" placeholder="请输入电话" maxlength="30" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">手机</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="mobile" class="form-control" placeholder="请输入手机" maxlength="30" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">										
												<label class="col-md-2 control-label">办公电话</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="officePhone" class="form-control" placeholder="请输入办公电话" maxlength="30" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">传真</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="fax" class="form-control" placeholder="请输入传真" maxlength="30" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">										
												<label class="col-md-2 control-label">地址</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="location" class="form-control" placeholder="请输入地址" maxlength="256" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">邮编</label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text" name="postcode" class="form-control" placeholder="请输入邮编" maxlength="6" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">
													是否可编辑
													<input type="checkbox" class="form-control"  name="editable" checked="true" />
													</label>
												</div>
											</div>
																			 
											<div class="form-group">
												<label class="col-md-2 control-label">描述</label>
												<div class="col-md-10">
													<div class="input-group">
														<input type="text" name="description" class="form-control input-xlarge" placeholder="请输入描述" maxlength="256" size="30"/>
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
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal">
					<i class="fa  fa-arrow-left"></i> 取 消 </button>
				<button type="button" id="btnSave" class="btn blue">
					<i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!--Dialog End -->
</body>
</html>
