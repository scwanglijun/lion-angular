<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>DataTables Demo</title>
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

<!--lion UI JS Start-->

<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--demo-->
<script src="${base}/resources/admin/scripts/datatable/demo2.js" type="text/javascript"></script>
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
				<!--DataGrid start-->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box">
						<div class="portlet-title">
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
							  </div>
						</div>
						<div class="portlet-body">
							<table class="lion-datagrids table table-striped table-bordered table-hover" id="sample_1" data-singleselect="true",   data-loadurl="/admin/sys/dt/list.json" data-checkbox="true" data-pagesize="5">
								<thead>
									<tr>
										<th class="table-checkbox" data-field='id' data-checkbox="true">
									 		<input type="checkbox" class="group-checkable" data-set="#sample_1.checkboxes"  data-sortable="false" />
									 	</th>
										<th data-field='username' data-sortDir="asc">
											用户名
										</th>
										<th data-field="employeeCode" >
										 	员工号
										</th>
										<th data-field="department"  data-formatter="formatterDarptment">
											部门
										</th>
										<th data-field="realnameZh">
											真实姓名
										</th>
										<th  data-field="email">
											邮箱
										</th>
									</tr>
								</thead>
							</table>
						</div>


						<div class="portlet-body">
							<table class="lion-datagrids table table-striped table-bordered table-hover" id="sample_2" data-singleselect="false", data-scrollx="true" data-scrolly="100px" data-loadurl="/admin/sys/dt/list.json" data-scrollCollapse="true" data-jqueryui="true" data-checkbox="true" data-pagesize="5" data-paginate="true" >
								<thead>
									<tr>
										<th class="table-checkbox" data-field='id' data-checkbox="true">
									 		<input type="checkbox" class="group-checkable" data-set="#sample_2.checkboxes"  data-sortable="false" />
									 	</th>
										<th data-field='username' data-sortDir="asc">
											用户名
										</th>
										<th data-field="employeeCode" >
										 	员工号
										</th>
										<th data-field="department"  data-formatter="formatterDarptment">
											部门
										</th>
										<th data-field="realnameZh">
											真实姓名
										</th>
										<th  data-field="email">
											邮箱
										</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<!--DataGrid End-->
		</div>
	</div>
</div>
</body>
</html>
