<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>Cache 监控信息</title>
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css" />
<!--lion UI JS Start-->
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/admin-common.js"></script>
<script src="${base}/resources/admin/scripts/monitor/cache.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
				<!-- BEGIN PAGE CONTENT INNER -->
		 		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>缓存概要统计信息
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
									<th>名称</th>
									<th>总的缓存数</th>
									<th>缓存状态</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<#list managerModels as item>
									<tr>
										<th>${item.name!}</th>
										<th>${item.size!}</th>
										<td>${item.statusName!}</td>
										<td>
											<a id="btnManagerClear" class="btn btn-sm red btn-manager-clear" data-ehcachename="${item.name!}">
                                            <i class="fa fa-times"></i>
										 		清除
                                        	</a>
										</td>
									</tr>
								</#list>
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
								<i class="fa fa-cogs"></i>缓存详细信息
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
									<th>名称</th>
									<th>缓存数量</th>
									<th>内存使用量</th>
									<th>磁盘使用量</th>
									<th>内存命中次数</th>
									<th>磁盘命中次数</th>
									<th>缓存策略</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<#list managerModels as managerModel>
										<#list managerModel.cacheModels as item>
											<tr class="${managerModel.name!}">
												<td>${item.name!}</td>
												<th>${item.size}</th>
												<td>${item.memoryStoreSize}</td>
												<td>${item.diskStoreSize}</td>
												<td>${item.cacheHits}</td>
												<td>${item.inMemoryHits}</td>
												<td>${item.memoryStoreEvictionPolicy}</td>
                                                <td>
                                                    <a id="btnCacheClear" class="btn btn-sm red btn-cache-clear" data-cachename="${item.name!}" data-ehcachename="${managerModel.name!}">
                                                        <i class="fa fa-times"></i>
                                                        清除
                                                    </a>
                                                </td>
											</tr>
											</tr>
									 </#list>
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