<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>ZTree Demo</title>
<!--zTree css Start-->
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--zTree css End--> 
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!--lion UI JS Start-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/ztree/ztree.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-20">
			      <input  id="departmentId"  name="departmentId"  
				 	  	  placeholder="请选择部门…"  type="text" 
				 		  class="lion-combotree form-control input-small" value="1" data-loadurl="${base}/system/department/comboxtree.json" data-width="200px" data-height="300px">
				 </select>
			</div>
			<div class="col-md-12 margin-bottom-10 margin-top-20">
				 <ul id="ztree" class="ztree" style="width:560px; overflow:auto;"></ul>
			</div> 
			<div class="col-md-12 margin-bottom-10">
				<div class="btn-group lion-combotree bootstrap-select form-control  input-small  show-tick">
					<button id="citySel"   class=" btn  input-small form-control dropdown-toggle"  placeholder="请选择部门…">
					    <span class="pull-left">请选择部门…</span>
						<i class="caret"></i>
					</button>				 
					<div id="menuContent" class="content" style="">
						<ul id="treeDemo1" class="ztree" style="margin-top:0;width:200px;"></ul>
					</div>
				</div>				 
			</div>
			<div class="col-md-12 margin-bottom-10" style="display:none">
				<ul id="treeDemo" class="ztree"></ul> 
			</div>
		</div>
		</div>
	</div>
</div>
</body>
</html>
