<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>系统概要信息</title>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/admin-common.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/systeminfo.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
				<!-- BEGIN PAGE CONTENT INNER -->
		 		<div class="row">
				<div class="col-md-6">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>系统信息
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
									<th>应用信息项</th>
									<th> 应用信息值</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>1</td>
									<th >应用程序名称</th>
									<td >${applicationInfo.name!}</td>
								</tr>
								<tr>
									<td>2</td>
									 <th >系统启动时间</th>
									 <td  >
									 	 ${applicationInfo.startDate!}
									</td>
								</tr>
								</tr>
								<tr>
									<td>3</td>
									<th >当前己登录用户数</th>
									<td >${applicationInfo.name!}</td>
								</tr>
								<tr>
									<td>4</td>
									<th  >是否是调试模式：</th>
								 <td >${applicationInfo.mode!}</td>
								</tr>
								<tr>
									<td>5</td>
								 	<th>操作系统名称：</th>
								 	<td>${applicationInfo.osName!}</td>
									</tr>
									<tr>
										 <td>6</td>
										 <th>操作系统版本：</th>
										 <td>${applicationInfo.version!}</td>
									</tr>
									<tr>
										  <td>7</td>
										 <th>操作系统补丁：</th>
										 <td>
										 	${applicationInfo.osArch!}
										 </td>
									</tr>
									<tr>
										 <td>8</td>
										 <th>JDK厂商：</th>
										 <td>${applicationInfo.jdkVendor!}</td>
									</tr>
									<tr>
										 <td>9</td>
										 <th>JDK版本：</th>
										 <td>${applicationInfo.jdkVersion!}</td>
									</tr>
									<tr>
										<td>10</td>
										 <th>JDK主目录：</th>
										 <td>${applicationInfo.jdkHomePath!}</td>
									</tr>
									<tr>
										<td>11</td>
										 <th>Web容器名称：</th>
										 <td>${applicationInfo.webContainerName!}</td>
									</tr>
									<tr>
										<td>12</td>
										 <th>启动Web容器的用户名：</th>
										 <td>${applicationInfo.startWebContainerUser!}</td>
									</tr>
									<tr>
										<td>13</td>
										 <th>JVM内存数/最大可用数(MB)：</th>
										 <td>${applicationInfo.maxMemory!}/${applicationInfo.maxUseMemory!} MB </td>
									</tr>
									<tr>
										 <td>14</td>
										 <th>系统编码：</th>
										 <td>${applicationInfo.encoding!}</td>
									</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END SAMPLE TABLE PORTLET-->
				</div>
				<div class="col-md-6">
					 <div class="row">
					 	 <div class="col-md-12">
					 	 	<!-- BEGIN BORDERED TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-coffee"></i>数据库配置信息
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										 #
									</th>
									<th>
										数据库配置项
									</th>
									<th>
										 数据库配置值
									</th>
								</tr>
								</thead>
								<tbody>
								<tr >
									<td>1</td>
									<th>类型：</th>
									<td>MySQL</td>
								</tr>
								<tr>
								     <td>2</td>
									<th>服务地址：</th>
									<td>localhost</td>
								</tr>
								<tr>
									<td>3</td>
									<th>端口：</th>
									<td>3306</td>
								</tr>
								<tr>
									<td>4</td>
									<th >SID：</th>
									<td>commerce</td>
								</tr>
								<tr>
									<td>5</td>
									<th>用户名：</th>
									<td>root</td>
								</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END BORDERED TABLE PORTLET-->
					</div>
					 </div>
					 <div class="row">
					 	 <div class="col-md-12">
					 	 	<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>授权信息
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										 #
									</th>
									<th>
										 授权项
									</th>
									<th>
										 授权值
									</th>
								</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<th>授权给：</th>
										<td>MySQL</td>
									</tr>
									<tr>
										<td>2</td>
										<th>有效期至：</th>
										<td>localhost</td>
									</tr>
									<tr>
										<td>3</td>
										<th>授权用户数：</th>
										<td>1000</td>
									</tr>
									<tr>
										<td>4</td>
										<th>授权产品代码：</th>
										<td>ecommerce</td>
									</tr>
									<tr>
										<td>5</td>
										<th>授权MAC地址：</th>
										<td>&nbsp;</td>
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
			</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
</body>
</html>