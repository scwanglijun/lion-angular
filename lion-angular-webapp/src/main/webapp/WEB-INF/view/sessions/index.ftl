<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>用户在线管理</title>
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
<script src="${base}/resources/global/js/combo/combo.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/admin/scripts/admin-common.js"></script>
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/session/session.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">	
				<form id="queryform" class="form-horizontal">
					<div class="col-md-12 margin-bottom-5" id="toolbar">
						<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue">
						<i class="fa fa-refresh"></i><@spring.message "common.toolbar.btn.reload.text"/> 
						</a>				
						<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green">
						<i class="fa  fa-file-excel-o"></i><@spring.message "common.toolbar.btn.export.text"/>
						</a>
						<a id="btnUserExit" class="btn btn-sm red" ><i class="fa fa-plus"></i> 强制退出 </a>
					</div>
				</form>			 
			</div>
		
			<!--<div class="col-md-12 margin-bottom-5" id="toolbar">
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue">
				<i class="fa fa-refresh"></i><@spring.message "common.toolbar.btn.reload.text"/> 
				</a>				
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green">
				<i class="fa  fa-file-excel-o"></i> Excel </a>
				<a id="btnUserExit" class="btn btn-sm red" ><i class="fa fa-plus"></i> 强制退出 </a>
			</div>-->
			<div class="col-md-12">
				  <!---<@lion.datagrids name="sys_parameter_lists_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/parameter/list.json" dataOptions="" style="height:400px;"/>-->
				  <table class="lion-datagrids table table-striped table-bordered table-hover" id="sys_session_tb" data-singleselect="true" data-sort="false" data-loadurl="${base}/sessions/actives.json" data-checkbox="true"  data-paginate="false" width="100%">
					<thead>
						<tr>
						  <th class="table-checkbox" data-field='id'style="width:30px;"  data-checkbox="true">
						 		<input type="checkbox" class="group-checkable" data-set="#sys_session_tb.checkboxes"  data-sortable="false" />
						 	</th>
								<th data-field='id'  style="width:220px;" >
								会话ID
							</th>
							<th data-field="username" style="width:80px;">
							 	用户名
							</th>
							<th data-field="host" style="width:50px;">
							 	登录IP
							</th>
							<th data-field="valid" style="width:30px;" data-formatter="formatterEidtable">
								状态
							</th>
							<th data-field="timeout" style="width:50px;" align="center" data-formatter="formatterTimeout">
								TimeOut
							</th>
							<th data-field="lastAccessTime" style="width:110px;">
								最后访问时间
							</th>						
							<th data-field="startTimestamp" style="width:110px;">
								开始时间
							</th>
							<th data-field="expired" style="width:50px;" data-formatter="formatterEidtable">
								是否过期
							</th>				
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
</body>
</html>
