<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>Hibernate 监控信息</title>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/admin-common.js"></script>
<script src="${base}/resources/admin/scripts/system/systeminfo.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<!-- BEGIN PROFILE CONTENT -->
					<div class="profile-content">
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light">
									<div class="portlet-title tabbable-line">
										<div class="caption caption-md">
											<i class="icon-globe theme-font hide"></i>
											<span class="caption-subject font-blue-madison bold uppercase">Hibernate统计</span>
										</div>
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#tab_1_1" data-toggle="tab">概要信息统计</a>
											</li>
											<li>
												<a href="#tab_1_2" data-toggle="tab">实体和集合CRUD统计</a>
											</li>
											<li>
												<a href="#tab_1_3" data-toggle="tab">二级缓存统计</a>
											</li>
											<li>
												<a href="#tab_1_4" data-toggle="tab">查询缓存统计</a>
											</li>
											<li>
												<a href="#tab_1_5" data-toggle="tab">JPA-Hibernate配置和实体类加载</a>
											</li>										
										</ul>
									</div>
									<div class="portlet-body">
										<div class="tab-content">
											<!-- PERSONAL INFO TAB -->
											<div class="tab-pane active" id="tab_1_1">
												 <div class="caption">
														<i class="fa"></i><strong>事务统计</strong>
												 </div>
												 <div class="table-scrollable">  	
												  	<table class="table   table-striped table-bordered  table-hover">
												  	<thead>
												  	<tr>
												  		<th>执行事务次数</th>
												  		<th>成功事务次数</th>	  		 
												  	</tr>
												  	</thead>
												  	<tbody>								  		 
												  		<tr>
												  			<td>${statistics.transactionCount!}</td>
												  			<td>
												  			${statistics.successfulTransactionCount!}
												  			</td>
												  		</tr>
												  		 
												  	</tbody>
												  	</table>
												  </div>
												  <div class="caption">
														<i class="fa"></i><strong>数据库连接统计</strong>
												  </div>
												  <div class="table-scrollable">  	
												  	<table class="table   table-striped table-bordered  table-hover">
												  	<thead>
												  	<tr>
												  		<th>连接次数</th>
												  		<th>平均连接次数</th>
												  	    <th>预编译SQL获取次数</th>
												  	    <th>预编译SQL关闭次数</th>
												  	    <th>乐观锁失败数</th>
												  	    <th>会话次数</th>
												  	    <th>平均会话次数</th>
												  	    <th>会话关闭次数</th>	
												  	    <th>Flush的次数</th>
												  	</tr>
												  	</thead>
												  	<tbody>								  		 
												  		<tr>
												  			<td>${statistics.connectCount!}</td>
												  			<td>
												  				${(statistics.connectCount/upSeconds)?string("0.##")} 次/秒
												  			</td>
												  			<td>${statistics.prepareStatementCount!}</td>
											  				<td>${statistics.closeStatementCount!}</td>
											  				<td>${statistics.optimisticFailureCount!}</td>
											  				<td> ${statistics.sessionOpenCount!}</td>
											  				<td>${(statistics.sessionOpenCount/upSeconds)?string("0.##")} 次/秒</td>
											  				<td> ${statistics.sessionCloseCount!}</td>
											  				<td> ${statistics.flushCount!}</td>
												  		</tr>
												  		 
												  	</tbody>
												  	</table>
												  </div>
												  <div class="caption">
														<i class="fa"></i><strong>二级缓存统计</strong>
												  </div>
												  <div class="table-scrollable">  	
												  	<table class="table   table-striped table-bordered  table-hover">
												  	<thead>
												  	<tr>
												  		<th>总命中率</th>
												  		<th>命中次数</th>
												  	    <th>平均命中次数</th>
												  	    <th>失效次数</th>
												  	    <th>平均失效次数</th>
												  	    <th>缓存数</th>
												  	</tr>
												  	</thead>
												  	<tbody>								  		 
												  		<tr>
												  			<td>${secondLevelCacheHitPercent!}</td>
												  			<td>
												  				${statistics.secondLevelCacheHitCount!}
												  			</td>
												  			<td>${(statistics.secondLevelCacheHitCount/upSeconds)?string("0.##")} 次/秒</td>
											  				<td>${statistics.secondLevelCacheMissCount!}</td>
											  				<td>${(statistics.secondLevelCacheMissCount/upSeconds)?string("0.##")} 次/秒</td>
											  				<td> ${statistics.secondLevelCachePutCount!}</td>
												  		</tr>
												  		 
												  	</tbody>
												  	</table>
												  </div>
											  	  <div class="caption">
											  			<i class="fa"></i>
											  			<strong>内存使用量</strong>
											  	  </div>
											  	  <div class="table-scrollable">  	
											  	  	<table class="table   table-striped table-bordered  table-hover">
											  	  	<thead>
											  	  	<tr>
											  	  		<th>内存总量</th>
											  	  		<th>已使用内存量</th>
											  	  	    <th>二级缓存使用内存量</th>
											  	  	    <th>内存中的总实体数</th>
											  	  	    <th>磁盘中的总实体数</th>
											  	  	</tr>
											  	  	</thead>
											  	  	<tbody>								  		 
											  	  		<tr>
											  	  			<td>${(maxMemory/1024/1024)?string("0.##")!}MB </td>
											  	  			<td>${(usedMemory/1024/1024)?string("0.##")!}MB
											  	  			</td>
											  	  			<td>
											  	  			${totalMemorySize/1024/1024!}MB
											  	  			</td>
										    				<td>${totalMemoryCount!}</td>
										    				<td>${totalDiskCount!}</td>
											  	  		</tr>
											  	  	</tbody>
											  	  	</table>
											  	  </div>
									  	 	  	  <div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>查询缓存统计</strong>
									  	 	  	  </div>
									  	 	  	  <div class="table-scrollable">  	
									  	 	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	 	  	  	<thead>
									  	 	  	  	<tr>
									  	 	  	  		<th>查询总执行次数</th>
									  	 	  	  		<th>最慢查询执行时间</th>
									  	 	  	  	    <th>最慢查询</th>
									  	 	  	  	    <th>总命中率</th>
									  	 	  	  	    <th>命中次数</th>
									  	 	  	  	    <th>平均命中次数</th>
									  	 	  	  	    <th>失效次数</th>
									  	 	  	  	    <th>平均失效次数</th>
									  	 	  	  	    <th>被缓存的查询个数</th>
									  	 	  	  	</tr>
									  	 	  	  	</thead>
									  	 	  	  	<tbody>								  		 
									  	 	  	  		<tr>
									  	 	  	  			<td>${statistics.queryExecutionCount!}</td>
									  	 	  	  			<td>	${statistics.queryExecutionMaxTime!} ms
									  	 	  	  			</td>
									  	 	  	  			<td> 
									  	 	  	  				${statistics.queryExecutionMaxTimeQueryString!}
									  	 	  	  			</td>
									  	 	  	  			<td>
									  	 	  	  			 	<#assign queryCacheCountTemp>
											${(statistics.queryCacheHitCount +statistics.queryCacheMissCount)}
									  	 	  	  			 	</#assign>
									  	 	  	  			 	<#assign  queryCacheCount>
									  	 	  	  			 		<#if queryCacheCountTemp gt 0>
									  	 	  	  			 			${queryCacheCountTemp}
									  	 	  	  			 		<#else>
									  	 	  	  			 			1
									  	 	  	  			 		</#if>
									  	 	  	  			 	</#assign>			 
        														${statistics.queryCacheHitCount/queryCacheCount!}

									  	 	  	  			</td>
									  	 	  	  			<td>${statistics.queryCacheHitCount!}</td>
									  	     				<td>${(statistics.queryCacheHitCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.queryCacheMissCount!}</td>
									  	     				<td>${(statistics.queryCacheMissCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.queryCachePutCount!}</td>
									  	 	  	  		</tr>
									  	 	  	  	</tbody>
									  	 	  	  	</table>
									  	 	  	  </div>
									  	 	  	   <div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>UpdateTimestamp缓存统计</strong>
									  	 	  	  </div>
									  	 	  	  <div class="table-scrollable">  	
									  	 	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	 	  	  	<thead>
									  	 	  	  	<tr>
									  	 	  	  	    <th>总命中率</th>
									  	 	  	  	    <th>命中次数</th>
									  	 	  	  	    <th>平均命中次数</th>
									  	 	  	  	    <th>失效次数</th>
									  	 	  	  	    <th>平均失效次数</th>			
									  	 	  	  	</tr>
									  	 	  	  	</thead>
									  	 	  	  	<tbody>								  		 
									  	 	  	  		<tr>
									  	 	  	  			<td>
									  	 	  	  			 	<#assign updateTimestampsCountTemp>
											${(statistics.updateTimestampsCacheHitCount +statistics.updateTimestampsCacheMissCount)}
									  	 	  	  			 	</#assign>
									  	 	  	  			 	<#assign  updateTimestampsCount>
									  	 	  	  			 		<#if updateTimestampsCountTemp gt 0>
									  	 	  	  			 			${updateTimestampsCountTemp}
									  	 	  	  			 		<#else>
									  	 	  	  			 			1
									  	 	  	  			 		</#if>
									  	 	  	  			 	</#assign>			 
        														${statistics.queryCacheHitCount/updateTimestampsCount!}

									  	 	  	  			</td>
									  	 	  	  			<td>${statistics.updateTimestampsCacheHitCount!}</td>
									  	     				<td>${(statistics.updateTimestampsCacheHitCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.updateTimestampsCacheMissCount!}</td>
									  	     				<td>${(statistics.updateTimestampsCacheMissCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				
									  	 	  	  		</tr>
									  	 	  	  	</tbody>
									  	 	  	  	</table>
									  	 	  	  </div>
											  	  <div class="caption">
											  			<i class="fa"></i>
											  			<strong>实体CRUD统计</strong>
											  	  </div>
											  	  <div class="table-scrollable">  	
											  	  	<table class="table   table-striped table-bordered  table-hover">
											  	  	<thead>
											  	  	<tr>
											  	  		<th>删除次数</th>
											  	  		<th>插入次数</th>
											  	  	    <th>更新次数</th>
											  	  	    <th>加载次数</th>
											  	  	    <th>抓取次数</th>
											  	  	</tr>
											  	  	</thead>
											  	  	<tbody>								  		 
											  	  		<tr>
											  	  			<td>${statistics.entityDeleteCount!}</td>
											  	  			<td>${statistics.entityInsertCount!}
											  	  			</td>
											  	  			<td>
											  	  			${statistics.entityUpdateCount!}
											  	  			</td>
										    				<td>${statistics.entityLoadCount!}</td>
										    				<td>${statistics.entityFetchCount!}</td>
											  	  		</tr>
											  	  	</tbody>
											  	  	</table>
									  	  		  </div>
									  	  	  	  <div class="caption">
									  	  	  			<i class="fa"></i>
									  	  	  			<strong>集合CRUD统计</strong>
									  	  	  	  </div>
									  	  	  	  <div class="table-scrollable">  	
									  	  	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	  	  	  	<thead>
									  	  	  	  	<tr>
									  	  	  	  		<th>删除次数</th>
									  	  	  	  		<th>插入次数</th>
									  	  	  	  	    <th>更新次数</th>
									  	  	  	  	    <th>加载次数</th>
									  	  	  	  	    <th>抓取次数</th>
									  	  	  	  	    <th>Recreated次数</th>
									  	  	  	  	</tr>
									  	  	  	  	</thead>
									  	  	  	  	<tbody>								  		 
									  	  	  	  		<tr>
									  	  	  	  			<td>${statistics.collectionRemoveCount!}</td>
									  	  	  	  			<td>${statistics.entityInsertCount!}
									  	  	  	  			</td>
									  	  	  	  			<td>
									  	  	  	  			${statistics.collectionUpdateCount!}
									  	  	  	  			</td>
									  	      				<td>${statistics.collectionLoadCount!}</td>
									  	      				<td>${statistics.collectionFetchCount!}</td>
									  	      				<td>${statistics.collectionRecreateCount!}</td>
									  	  	  	  		</tr>
									  	  	  	  	</tbody>
									  	  	  	  	</table>
									  	  	  	  </div>
									  	  	  	  <div class="caption">
									  	  	  			<i class="fa"></i>
									  	  	  			<strong>JPA-Hibernate配置</strong>
									  	  	  	  </div>
									  	  	  	  <div class="table-scrollable">  	
									  	  	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	  	  	  	<thead>
									  	  	  	  	<tr>
									  	  	  	  		<th>Key</th>
									  	  	  	  		<th>Value</th>
									  	  	  	  	</tr>
									  	  	  	  	</thead>
									  	  	  	  	<tbody>
									  	  	  	  		 <#if properties?exists>
									  	  	  	  		<#list properties?keys as key>	
									  	  	  	  			<#if key?contains('hibernate')|| key?contains('javax.')|| key?contains('net.')>
										  	  	  	  		<tr>
										  	  	  	  			<td>${key!}</td>
										  	  	  	  			<td>${properties[key]}</td>
										  	  	  	  		</tr>
										  	  	  	  		</#if>
									  	  	  	  		</#list>
									  	  	  	  		</#if>
									  	  	  	  	</tbody>
									  	  	  	  	</table>
									  	  	  	  </div>
							  	  	   	  	  	  <div class="caption">
							  	  	   	  	  			<i class="fa"></i>
							  	  	   	  	  			<strong>实体加载类</strong>
							  	  	   	  	  	  </div>
							  	  	   	  	  	  <div class="table-scrollable">  	
							  	  	   	  	  	  	<table class="table   table-striped table-bordered  table-hover">
							  	  	   	  	  	  	<thead>
							  	  	   	  	  	  	<tr>
							  	  	   	  	  	  		<th>Key</th>
							  	  	   	  	  	  		<th>实体类</th>
							  	  	   	  	  	  		<th>ID标识</th>
							  	  	   	  	  	  	</tr>
							  	  	   	  	  	  	</thead>
							  	  	   	  	  	  	<tbody>
							  	  	   	  	  	  		 <#if sessionFactory.allClassMetadata?exists>
							  	  	   	  	  	  		<#list sessionFactory.allClassMetadata?keys as key>	
							  	  	   	  	  	  			 
							  	  	 	  	  	  	  		<tr>
							  	  	 	  	  	  	  			<td>${key!}</td>
							  	  	 	  	  	  	  			<td>${sessionFactory.allClassMetadata[key].entityName}</td>
							  	  	 	  	  	  	  			<td>${sessionFactory.allClassMetadata[key].identifierPropertyName}</td>
							  	  	 	  	  	  	  		</tr>
							  	  	   	  	  	  		</#list>
							  	  	   	  	  	  		</#if>
							  	  	   	  	  	  	</tbody>
							  	  	   	  	  	  	</table>
							  	  	   	  	  	  </div>		
											</div>
											<!-- END PERSONAL INFO TAB -->
											<!-- CHANGE AVATAR TAB -->
											<div class="tab-pane" id="tab_1_2">
												  <div class="caption">
											  			<i class="fa"></i>
											  			<strong>实体CRUD统计</strong>
											  	  </div>
											  	  <div class="table-scrollable">  	
											  	  	<table class="table   table-striped table-bordered  table-hover">
											  	  	<thead>
											  	  	<tr>
											  	  		<th>删除次数</th>
											  	  		<th>插入次数</th>
											  	  	    <th>更新次数</th>
											  	  	    <th>加载次数</th>
											  	  	    <th>抓取次数</th>
											  	  	</tr>
											  	  	</thead>
											  	  	<tbody>								  		 
											  	  		<tr>
											  	  			<td>${statistics.entityDeleteCount!}</td>
											  	  			<td>${statistics.entityInsertCount!}
											  	  			</td>
											  	  			<td>
											  	  			${statistics.entityUpdateCount!}
											  	  			</td>
										    				<td>${statistics.entityLoadCount!}</td>
										    				<td>${statistics.entityFetchCount!}</td>
											  	  		</tr>
											  	  	</tbody>
											  	  	</table>
									  	  		  </div>
									  	  	  	  <div class="caption">
									  	  	  			<i class="fa"></i>
									  	  	  			<strong>集合CRUD统计</strong>
									  	  	  	  </div>
									  	  	  	  <div class="table-scrollable">  	
									  	  	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	  	  	  	<thead>
									  	  	  	  	<tr>
									  	  	  	  		<th>删除次数</th>
									  	  	  	  		<th>插入次数</th>
									  	  	  	  	    <th>更新次数</th>
									  	  	  	  	    <th>加载次数</th>
									  	  	  	  	    <th>抓取次数</th>
									  	  	  	  	    <th>Recreated次数</th>
									  	  	  	  	</tr>
									  	  	  	  	</thead>
									  	  	  	  	<tbody>								  		 
									  	  	  	  		<tr>
									  	  	  	  			<td>${statistics.collectionRemoveCount!}</td>
									  	  	  	  			<td>${statistics.entityInsertCount!}
									  	  	  	  			</td>
									  	  	  	  			<td>
									  	  	  	  			${statistics.collectionUpdateCount!}
									  	  	  	  			</td>
									  	      				<td>${statistics.collectionLoadCount!}</td>
									  	      				<td>${statistics.collectionFetchCount!}</td>
									  	      				<td>${statistics.collectionRecreateCount!}</td>
									  	  	  	  		</tr>
									  	  	  	  	</tbody>
									  	  	  	  	</table>
									  	  	  	  </div>
											</div>
											<!-- END CHANGE AVATAR TAB -->
											<!-- CHANGE PASSWORD TAB -->
											<div class="tab-pane" id="tab_1_3">									<div class="caption">
														<i class="fa"></i><strong>二级缓存统计</strong>
												  </div>
												  <div class="table-scrollable">  	
												  	<table class="table   table-striped table-bordered  table-hover">
												  	<thead>
												  	<tr>
												  		<th>总命中率</th>
												  		<th>命中次数</th>
												  	    <th>平均命中次数</th>
												  	    <th>失效次数</th>
												  	    <th>平均失效次数</th>
												  	    <th>缓存数</th>
												  	</tr>
												  	</thead>
												  	<tbody>								  		 
												  		<tr>
												  			<td>${secondLevelCacheHitPercent!}</td>
												  			<td>
												  				${statistics.secondLevelCacheHitCount!}
												  			</td>
												  			<td>${(statistics.secondLevelCacheHitCount/upSeconds)?string("0.##")} 次/秒</td>
											  				<td>${statistics.secondLevelCacheMissCount!}</td>
											  				<td>${(statistics.secondLevelCacheMissCount/upSeconds)?string("0.##")} 次/秒</td>
											  				<td> ${statistics.secondLevelCachePutCount!}</td>
												  		</tr>
												  		 
												  	</tbody>
												  	</table>
												  </div>
											  	  <div class="caption">
											  			<i class="fa"></i>
											  			<strong>内存使用量</strong>
											  	  </div>
											  	  <div class="table-scrollable">  	
											  	  	<table class="table   table-striped table-bordered  table-hover">
											  	  	<thead>
											  	  	<tr>
											  	  		<th>内存总量</th>
											  	  		<th>已使用内存量</th>
											  	  	    <th>二级缓存使用内存量</th>
											  	  	    <th>内存中的总实体数</th>
											  	  	    <th>磁盘中的总实体数</th>
											  	  	</tr>
											  	  	</thead>
											  	  	<tbody>								  		 
											  	  		<tr>
											  	  			<td>${(maxMemory/1024/1024)?string("0.##")!}MB </td>
											  	  			<td>${(usedMemory/1024/1024)?string("0.##")!}MB
											  	  			</td>
											  	  			<td>
											  	  			${totalMemorySize/1024/1024!}MB
											  	  			</td>
										    				<td>${totalMemoryCount!}</td>
										    				<td>${totalDiskCount!}</td>
											  	  		</tr>
											  	  	</tbody>
											  	  	</table>
											  	  </div>
											</div>
											<!-- END CHANGE PASSWORD TAB -->
											<!-- PRIVACY SETTINGS TAB -->
											<div class="tab-pane" id="tab_1_4">												 
												 <div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>查询缓存统计</strong>
									  	 	  	  </div>
									  	 	  	  <div class="table-scrollable">  	
									  	 	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	 	  	  	<thead>
									  	 	  	  	<tr>
									  	 	  	  		<th>查询总执行次数</th>
									  	 	  	  		<th>最慢查询执行时间</th>
									  	 	  	  	    <th>最慢查询</th>
									  	 	  	  	    <th>总命中率</th>
									  	 	  	  	    <th>命中次数</th>
									  	 	  	  	    <th>平均命中次数</th>
									  	 	  	  	    <th>失效次数</th>
									  	 	  	  	    <th>平均失效次数</th>
									  	 	  	  	    <th>被缓存的查询个数</th>
									  	 	  	  	</tr>
									  	 	  	  	</thead>
									  	 	  	  	<tbody>								  		 
									  	 	  	  		<tr>
									  	 	  	  			<td>${statistics.queryExecutionCount!}</td>
									  	 	  	  			<td>	${statistics.queryExecutionMaxTime!} ms
									  	 	  	  			</td>
									  	 	  	  			<td> 
									  	 	  	  				${statistics.queryExecutionMaxTimeQueryString!}
									  	 	  	  			</td>
									  	 	  	  			<td>
									  	 	  	  			 	<#assign queryCacheCountTemp>
											${(statistics.queryCacheHitCount +statistics.queryCacheMissCount)}
									  	 	  	  			 	</#assign>
									  	 	  	  			 	<#assign  queryCacheCount>
									  	 	  	  			 		<#if queryCacheCountTemp gt 0>
									  	 	  	  			 			${queryCacheCountTemp}
									  	 	  	  			 		<#else>
									  	 	  	  			 			1
									  	 	  	  			 		</#if>
									  	 	  	  			 	</#assign>			 
        														${statistics.queryCacheHitCount/queryCacheCount!}

									  	 	  	  			</td>
									  	 	  	  			<td>${statistics.queryCacheHitCount!}</td>
									  	     				<td>${(statistics.queryCacheHitCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.queryCacheMissCount!}</td>
									  	     				<td>${(statistics.queryCacheMissCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.queryCachePutCount!}</td>
									  	 	  	  		</tr>
									  	 	  	  	</tbody>
									  	 	  	  	</table>
									  	 	  	  </div>
									  	 	  	  <!--<div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>查询缓存详细统计</strong>
									  	 	  	  </div>-->
									  	 	  	  <div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>UpdateTimestamp缓存统计</strong>
									  	 	  	  </div>
									  	 	  	  <div class="table-scrollable">  	
									  	 	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	 	  	  	<thead>
									  	 	  	  	<tr>
									  	 	  	  	    <th>总命中率</th>
									  	 	  	  	    <th>命中次数</th>
									  	 	  	  	    <th>平均命中次数</th>
									  	 	  	  	    <th>失效次数</th>
									  	 	  	  	    <th>平均失效次数</th>			
									  	 	  	  	</tr>
									  	 	  	  	</thead>
									  	 	  	  	<tbody>								  		 
									  	 	  	  		<tr>
									  	 	  	  			<td>
									  	 	  	  			 	<#assign updateTimestampsCountTemp>
											${(statistics.updateTimestampsCacheHitCount +statistics.updateTimestampsCacheMissCount)}
									  	 	  	  			 	</#assign>
									  	 	  	  			 	<#assign  updateTimestampsCount>
									  	 	  	  			 		<#if updateTimestampsCountTemp gt 0>
									  	 	  	  			 			${updateTimestampsCountTemp}
									  	 	  	  			 		<#else>
									  	 	  	  			 			1
									  	 	  	  			 		</#if>
									  	 	  	  			 	</#assign>			 
        														${statistics.queryCacheHitCount/updateTimestampsCount!}

									  	 	  	  			</td>
									  	 	  	  			<td>${statistics.updateTimestampsCacheHitCount!}</td>
									  	     				<td>${(statistics.updateTimestampsCacheHitCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				<td>${statistics.updateTimestampsCacheMissCount!}</td>
									  	     				<td>${(statistics.updateTimestampsCacheMissCount/upSeconds)?string("0.##")!}秒/次</td>
									  	     				
									  	 	  	  		</tr>
									  	 	  	  	</tbody>
									  	 	  	  	</table>
									  	 	  	  </div>
												  <!--<div class="caption">
									  	 	  			<i class="fa"></i>
									  	 	  			<strong>UpdateTimestamp缓存详细统计</strong>
									  	 	  	  </div>-->
											</div>
											<!-- END PRIVACY SETTINGS TAB -->
											<div class="tab-pane" id="tab_1_5">
												 <div class="caption">
									  	  	  			<i class="fa"></i>
									  	  	  			<strong>JPA-Hibernate配置</strong>
									  	  	  	  </div>
									  	  	  	  <div class="table-scrollable">  	
									  	  	  	  	<table class="table   table-striped table-bordered  table-hover">
									  	  	  	  	<thead>
									  	  	  	  	<tr>
									  	  	  	  		<th>Key</th>
									  	  	  	  		<th>Value</th>
									  	  	  	  	</tr>
									  	  	  	  	</thead>
									  	  	  	  	<tbody>
									  	  	  	  		 <#if properties?exists>
									  	  	  	  		<#list properties?keys as key>	
									  	  	  	  			<#if key?contains('hibernate')|| key?contains('javax.')|| key?contains('net.')>
										  	  	  	  		<tr>
										  	  	  	  			<td>${key!}</td>
										  	  	  	  			<td>${properties[key]}</td>
										  	  	  	  		</tr>
										  	  	  	  		</#if>
									  	  	  	  		</#list>
									  	  	  	  		</#if>
									  	  	  	  	</tbody>
									  	  	  	  	</table>
									  	  	  	  </div>
							  	  	   	  	  	  <div class="caption">
							  	  	   	  	  			<i class="fa"></i>
							  	  	   	  	  			<strong>实体加载类</strong>
							  	  	   	  	  	  </div>
							  	  	   	  	  	  <div class="table-scrollable">  	
							  	  	   	  	  	  	<table class="table   table-striped table-bordered  table-hover">
							  	  	   	  	  	  	<thead>
							  	  	   	  	  	  	<tr>
							  	  	   	  	  	  		<th>Key</th>
							  	  	   	  	  	  		<th>实体类</th>
							  	  	   	  	  	  		<th>ID标识</th>
							  	  	   	  	  	  	</tr>
							  	  	   	  	  	  	</thead>
							  	  	   	  	  	  	<tbody>
							  	  	   	  	  	  		 <#if sessionFactory.allClassMetadata?exists>
							  	  	   	  	  	  		<#list sessionFactory.allClassMetadata?keys as key>	
							  	  	   	  	  	  			 
							  	  	 	  	  	  	  		<tr>
							  	  	 	  	  	  	  			<td>${key!}</td>
							  	  	 	  	  	  	  			<td>${sessionFactory.allClassMetadata[key].entityName}</td>
							  	  	 	  	  	  	  			<td>${sessionFactory.allClassMetadata[key].identifierPropertyName}</td>
							  	  	 	  	  	  	  		</tr>
							  	  	   	  	  	  		</#list>
							  	  	   	  	  	  		</#if>
							  	  	   	  	  	  	</tbody>
							  	  	   	  	  	  	</table>
							  	  	   	  	  	  </div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PROFILE CONTENT -->

<!-- END PAGE CONTENT INNER -->
</body>
</html>