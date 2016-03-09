<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>JVM监控信息</title>
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
								<i class="fa fa-cogs"></i>JVM RumtimeInfo
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
									<th >Java 虚拟机的名称</th>
									<td >${runtime.name!}</td>
								</tr>
								<tr>
									<td>2</td>
									 <th >Java 虚拟机实现名称</th>
									 <td  >
									 	 ${runtime.vmName!}
									</td>
								</tr>
								</tr>
								<tr>
									<td>3</td>
									<th > Java 虚拟机实现供应商</th>
									<td >${runtime.vmVendor!}</td>
								</tr>
								<tr>
									<td>4</td>
									<th> Java 虚拟机实现版本</th>
								 <td >${runtime.vmVersion!}</td>
								</tr>
								<tr>
									<td>5</td>
								 	<th>Java 虚拟机规范名称</th>
								 	<td>${runtime.specName!}</td>
									</tr>
									<tr>
										 <td>6</td>
										 <th>Java 虚拟机规范供应商：</th>
										 <td>${runtime.specVendor!}</td>
									</tr>
									<tr>
										 <td>7</td>
										 <th>Java 虚拟机规范版本：</th>
										 <td>
										 	${runtime.specVersion!}
										 </td>
									</tr>
									<tr>
										 <td>8</td>
										 <th>Java虚拟机的启动时间：</th>
										 <td>${runtime.startTime?number_to_datetime!}</td>
									</tr>
									<tr>
										 <td>9</td>
										 <th>Java 虚拟机的正常运行时间：</th>
										 <td>${((runtime.upTime)/(1000*60))?string("0.#")}分钟</td>
									</tr>
									<tr>
										<td>10</td>
										 <th> Java 虚拟机的输入变量参数：</th>
										 <td>
										 <#list runtime.inputArguments as argument>
											${argument!}
										 	<br/>
										 </#list>
										</td>
									</tr>
									<tr>
										<td>11</td>
										 <th>Java 库路径：</th>
										 <td>${runtime.libraryPath!}</td>
									</tr>
									<tr>
										<td>12</td>
										 <th>类加载路径：</th>
										 <td>${runtime.classPath!}</td>
									</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
				 <div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>JVM OS Info
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
									<th >操作系统名称</th>
									<td >${osInfo.name!}  ${osInfo.version!} ${osInfo.arch!} </td>
								</tr>
								</tr>
								<tr>
									<td>2</td>
									<th >负载均衡信息</th>
									<td >${osInfo.systemLoadAverage}</td>
								</tr>
								<tr>
									<td>3</td>
									<th>CPU核数</th>
								 <td >${osInfo.availableProcessors!}</td>
								</tr>
								<tr>
									<td>4</td>
									<th>OS物理内存大小</th>
									 
								 <td >${(osInfo.totalPhysicalMemorySize/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>5</td>
									<th>OS空闲物理内存大小</th>
								 <td >${(osInfo.freePhysicalMemorySize/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>6</td>
									<th>OS虚拟内存大小</th>
								 <td >${(osInfo.totalSwapSpaceSize/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>7</td>
									<th>OS空闲虚拟内存大小</th>
								 <td >${(osInfo.freeSwapSpaceSize/1024/1024)?string("0.00")!}MB</td>
								</tr>
								<tr>
									<td>8</td>
									<th>OS SystemCpuLoad</th>
								 <td >${osInfo.systemCpuLoad}</td>
								</tr>
								<tr>
									<td>9</td>
									<th>OS ProcessCpuLoad</th>
								 <td >${osInfo.processCpuLoad}</td>
								</tr>
								<tr>
									<td>10</td>
									<th>OS ProcessCpuTime</th>
								 <td >${osInfo.processCpuTime}</td>
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