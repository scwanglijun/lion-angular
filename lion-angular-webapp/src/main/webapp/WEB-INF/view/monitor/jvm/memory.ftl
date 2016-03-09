<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>JVM内存信息</title>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/admin-common.js"></script>
<script src="${base}/resources/admin/scripts/system/systeminfo.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
				<!-- BEGIN PAGE CONTENT INNER -->
		 		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>JVM Memory Info
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table  table-hover">
								<thead>
								<tr>
									<th>#</th>
									<th>项</th>
									<th>值</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>1</td>
									<th >已提交给 Java 虚拟机使用的内存量</th>
									<td >${(memory.committed/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>2</td>
									 <th >初始化内存量</th>
									 <td  >
									 	 ${(memory.init/1024/1024)?string("0.00")!}MB
									</td>
								</tr>
								</tr>
								<tr>
									<td>3</td>
									<th >最大内存量</th>
									<td >${(memory.max/1024/1024)?string("0.00")!}MB </td>
								</tr>
								<tr>
									<td>4</td>
									<th>已使用内存量</th>
								 <td >${(memory.used/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>5</td>
								 	<th>是否启动GC回收日志输出</th>
								 	<td>${memory.verbose?string('true','false')}</td>
									</tr>
									<tr>
										 <td>6</td>
										 <th>被挂起的对象：</th>
										 <td>${memory.objectPendingFinalizationCount!}</td>
									</tr>									 
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
				 
			</div>
			</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
</body>
</html>