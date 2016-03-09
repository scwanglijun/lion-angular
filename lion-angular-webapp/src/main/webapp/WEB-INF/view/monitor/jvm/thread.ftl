<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>JVM 线程信息</title>
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
								<i class="fa fa-cogs"></i>JVM Thread Info
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable margin-bottom-20">
								<table class="table  table-hover">
								<thead>
								<tr>
									<th>总线程数</th>
									<th>活动线程数</th>
									<th>活动守护线程数</th>
									<th>峰值活动线程计数</th>
									<th>线程争用监视状态</th>
									<th>是否支持线程争用监视</th>
									<th>线程CPU时间测量状态</th>
									<th>是否支持线程CPU时间测量</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>${thread.totalStartedThreadCount!}</td>
									<td>${thread.threadCount!}</td>
									<th>${thread.daemonThreadCount!}</th>
									<td>${thread.peakThreadCount!}</td>
									<td>${thread.threadContentionMonitoringEnabled?string('开启','关闭')!}</td>
									<td>${thread.threadContentionMonitoringSupported?string('是','否')!}</td>
									<td>${thread.threadCpuTimeEnabled?string('开启','关闭')!}</td>
									<td>${thread.threadCpuTimeSupported?string('是','否')!}</td>
								</tr> 							 								 
								</tbody>
								</table>
							</div>
							<div class="caption">
									<i class="fa"></i><strong>线程详细列表</strong>
							</div>
							<div class="table-scrollable">
								
								<table class="table  table-hover">
								<thead>
								<tr>
									<th>ID</th>
									<th>名称</th>
									<th>消耗的时间</th>
									<th>等待时间</th>
									<th>等待次数</th>
									<th>阻塞时间</th>
									<th>阻塞次数</th>
									<th>挂起状态</th>
									<th>锁对象名称</th>
									<th>关联锁对象名称</th>
									<th>堆栈信息</th>									
								</tr>
								</thead>
								<tbody>
									<#list thread.threadDetails as item>
									<tr>
										<td>${item.id!}</td>
										<td>${item.name!}</td>
										<th>${item.cupCostTime!}</th>
										<td>${item.waitedTime!}</td>
										<td>${item.waitedCount!}</td>
										<td>${item.blockedTime!}</td>
										<td>${item.blockedCount!}</td>
										<td>${item.suspended?string('true','false')!!}</td>
										<td>${item.lockName!}</td>
										<td>${item.lockOwnerName!}</td>
										<td>${item.stackInfo!}</td>
									</tr>
									</#list> 
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