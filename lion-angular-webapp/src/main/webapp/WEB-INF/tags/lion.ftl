<!-- Freemarker自定义标签 -->
<#assign contextPath = request.contextPath/>
<!--ComboBox 标签定义 Start-->
<#--
combobox 用户下拉列表显示控件
id 表示该控件的ID，
codeName:表示IM的Name
dataClass:表该控件的样式
title:显示提示信息
dataSize:显示下拉列表记录数，大于此记录数则显示流动条
multipleDataMaxOptions：是否支持多项选择，等于1表示只能选择一项
-->
<#macro combobox id,codeName,name,dataClass,title,dataSize, multipleDataMaxOptions>
<@ui.comboboxs codeName="${codeName}">
<select <#if ui.strIsNotEmpty(id)>id="${id}"</#if>
		<#if ui.strIsNotEmpty(name)>id="${name}"</#if>
	    <#if ui.strIsNotEmpty(dataClass)>class="${dataClass}"</#if>
	    <#if ui.strIsNotEmpty(title)>title="${title}"</#if>
	    <#if ui.strIsNotEmpty(dataSize)>data-size="${dataSize}"</#if>
	    <#if ui.strIsNotEmpty(multipleDataMaxOptions)>multiple data-max-options="${multipleDataMaxOptions}"</#if>
>
<#if codes?? &&codes?size gt 0>
	<#list codes as code>
		<option value="${code.codeValue}">${code.nameZh}</option>
	</#list>
</#if>
</select>
</@ui.comboboxs>
</#macro>
<!--ComboBox 标签定义 End-->
<!--DataGrid 工具栏标签 Start-->

<!--DataGrid 工具栏标签 End-->
<!--DataGrid 数据表格 Start-->
<#--
 * datagrid
 * 用于显示DataGrid表格根据数据库配置；
 * name  表格名称 
 * load  是否加载数据 ,默认为加载数据
 * tableClass  CSS样式  默认样式为：easyui-datagrid
 * toolbar 工具栏
 * load 是否加载数据 默认为 true
 * url 加载数据的URL 
 * dataOptions jQuery easy UI data-options
 * title 表格标题 title
 * style TML style 样式 
 -->
<#macro datagrids name,tableClass,toolbar,load,url,dataOptions,style>
<@ui.datagrid id='${name}'>
<table id="${name}" 
<#if ui.strIsNotEmpty(tableClass)>class="${tableClass}"</#if> 
<#if ui.strIsNotEmpty(toolbar)>toolbar="#${toolbar}"</#if> 
<#if ui.strIsNotEmpty(load)&&load=='true'>
<#if ui.strIsNotEmpty(url)>url="${url}"<#elseif ui.strIsNotEmpty(datagrids.url)>url="${datagrids.url}"</#if>
</#if><#if ui.strIsNotEmpty(dataOptions)>dataOptions="${dataOptions}"</#if> 
<#if ui.strIsNotEmpty(style)> style="${style}"</#if>
fit="${datagrids.fit?string("true","false")}"
<#if datagrids.fitColumns?if_exists> fitColumns="${datagrids.fitColumns?string("true","false")}" </#if>
<#if datagrids.pagination?if_exists&&datagrids.pagination> 
pagination="true" 
<#if ui.strIsNotEmpty(datagrids.pagePosition)>pagePosition="${datagrids.pagePosition}"</#if>
<#if (datagrids.pageNumber gt 1)> pageNumber="${datagrids.pageNumber}" </#if> 
<#if (datagrids.pageSize gt 1) > pageSize="${datagrids.pageSize!}" <#else>pageSize="15"</#if>
<#if ui.strIsNotEmpty(datagrids.pageList)>pageList="${datagrids.pageList}"</#if>
</#if>
<#if ui.strIsNotEmpty(datagrids.sortName)>  
sortName="${datagrids.sortName}" 
<#if ui.isNotEmpty(datagrids.sortOrder())&& datagrids.sortOrder()!="asc">
sortOrder="${dataGrid.sortOrder}"
</#if></#if>
<#if datagrids.remoteSort?if_exists&&!datagrids.remoteSort >
remoteSort="false"
</#if>
<#if datagrids.showHeader?if_exists&&!datagrids.showHeader>
showHeader="false"
</#if>
<#if datagrids.showTitle?if_exists&&datagrids.showTitle>
title="${datagrids.title}"
</#if>
<#if datagrids.showFooter?if_exists&&datagrids.showFooter>showFooter="true"</#if>
<#if ui.strIsNotEmpty(datagrids.rowStyler)>rowStyler="${datagrids.rowStyler}"</#if>
<#if ui.strIsNotEmpty(datagrids.loader)>loader="${datagrids.loader}"</#if>
<#if datagrids.striped?if_exists&&datagrids.striped>striped="true"</#if>
<#if ui.strIsNotEmpty(datagrids.method)>method="${datagrids.method}"</#if>
<#if datagrids.nowrap?if_exists&&datagrids.nowrap>nowrap="true"</#if>
<#if ui.strIsNotEmpty(datagrids.data)>data="${datagrids.data}"</#if>
<#if ui.strIsNotEmpty(datagrids.loadMsg)>loadMsg="${datagrids.loadMsg}"</#if>
<#if datagrids.rownumbers?if_exists&&datagrids.rownumbers>rownumbers="true"</#if>
<#if datagrids.singleSelect?if_exists&&datagrids.singleSelect>singleSelect="true"</#if>
<#if datagrids.checkOnSelect?if_exists&&datagrids.checkOnSelect>checkOnSelect="true"</#if>
<#if datagrids.selectOnCheck?if_exists&&datagrids.selectOnCheck>selectOnCheck="true"</#if>
<#if ui.strIsNotEmpty(datagrids.loadFilter)>loadFilter="${datagrids.loadFilter}"</#if>
><thead><tr>
	 <#if datagrids.sortColumns?exists>
	  <#list datagrids.sortColumns as dataColumn>
		<th field="${dataColumn.field}"  width="${dataColumn.width}" align="${dataColumn.align}" <#if dataColumn.headerAlign??>halign="${dataColumn.headerAlign}"</#if><#if dataColumn.checkbox?if_exists&&dataColumn.checkbox>checkbox="true"</#if><#if dataColumn.sortable?if_exists&&dataColumn.sortable>sortable="true"</#if><#if dataColumn.order??>order="${dataColumn.order}"</#if><#if dataColumn.hidden?if_exists&&dataColumn.hidden>hidden="true"</#if><#if dataColumn.formatter??&&dataColumn.formatter!="">formatter="${dataColumn.formatter}"</#if><#if dataColumn.styler??&&dataColumn.styler!=""> styler="${dataColumn.styler}"</#if>>${dataColumn.name}</th>
	  </#list>
	 </#if>	 
	 </tr></thead></table>
</@ui.datagrid>
</#macro>
<!--DataGrid 数据表格 Start-->

<!--菜单标签 Start-->
<#macro menuTree menus>
	<#if menus?? && menus?size gt 0>
		<ul class="sub-menu">
			<#list menus as menu>
				<#if  menu.icon??&&menu.icon?length gt 0>
				<li  <#if menu.selected>class="open active" </#if> >
					<a href="${contextPath!}${menu.path!}" <#if menu.target?length gt 0>target=${menu.target!}</#if> >
						<i class="${menu.icon!}"></i>
						<span class="title">${menu.nameZh!}</span>
						<#if menu.menus?? && menu.menus?size gt 0>
							<span class="arrow <#if menu.selected>open</#if>"></span>
						</#if>
					</a>					
					<@menuTree menu.menus/>
				</li>
				<#else>
					<li <#if menu.selected>class="active" </#if> >
					<a href="${contextPath!}${menu.path!}" <#if menu.target?length gt 0>target=${menu.target!}</#if>>
						<i class="${menu.icon!}"></i>
						<span class="title">${menu.nameZh!}</span>
						<#if menu.menus?? && menu.menus?size gt 0>
							<span class="arrow"></span>
						</#if>
					</a>					
					<@menuTree menu.menus/>
					</li>
				</#if>
			</#list>
		</ul>
	</#if>
</#macro>
<#macro menus>
	<@ui.menu>
		<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
		<#if menus?? && menus?size gt 0>
			<#list menus as menu>
				<!--开始第一个菜单显示样式-->
				<#if menu_index==0>
				<li class="start <#if menu.selected>active</#if>">
					<a href="${contextPath}${menu.path!}" <#if menu.target?length gt 0>target=${menu.target!}</#if>>
					<#if  menu.icon??&&menu.icon?length gt 0>
						<i class="${menu.icon!}"></i>
					</#if>
					<span class="title">${menu.nameZh!}</span>
					</a>
				</li>
			    <#else>
				    <li <#if menu.selected>class="active open" </#if> >
						<a href="javascript:;">
							<#if  menu.icon??&&menu.icon?length gt 0>
							<i class="${menu.icon!}"></i>
							</#if>
							<span class="title">${menu.nameZh!}</span>
							<#if menu.menus?? && menu.menus?size gt 0>
								<span class="arrow <#if menu.selected>open</#if>"></span>
							</#if>
						</a>
						<@menuTree menu.menus/>
					</li>
				</#if>
			</#list>
		</#if>
	</ul>	
</@ui.menu>
</#macro>
<!--菜单标签 End-->