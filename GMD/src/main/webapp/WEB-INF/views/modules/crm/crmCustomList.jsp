<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户资料管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出客户资料数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/crmCustom/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/crm/crmCustom/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/crm/crmCustom/import/template">下载模板</a>
		</form>
	</div>	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/crmCustom/">客户资料列表</a></li>
		<shiro:hasPermission name="crm:crmCustom:edit">
		<li><a href="${ctx}/crm/crmCustom/form">客户资料添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="crmCustom" action="${ctx}/crm/crmCustom/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>跟踪状态：</label>
				<form:select path="followstate" style="width:120px;">
					<form:options items="${fns:getDictList('FLLOWSTATE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customname" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户姓名</th>
				<th>手机号</th>
				<th>QQ号</th>
				<th>所属员工</th>
				<th>跟踪状态</th>
				
				<shiro:hasPermission name="crm:crmCustom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="crmCustom">
			<tr>
				<td><a href="${ctx}/crm/crmCustom/form?id=${crmCustom.id}">
					${crmCustom.customname}
				</a></td>
				<td>
					${crmCustom.phone}
				</td>
				<td>
					${crmCustom.qq}
				</td>
				<td>
					${crmCustom.realname}
				</td>
				<td>
					${fns:getDictLabel(crmCustom.followstate, 'FLLOWSTATE', '')}
				</td>
				
				<shiro:hasPermission name="crm:crmCustom:edit"><td>
    				<a href="${ctx}/crm/crmCustom/form?id=${crmCustom.id}">修改</a>
					<a href="${ctx}/crm/crmCustom/delete?id=${crmCustom.id}" onclick="return confirmx('确认要删除该客户资料吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>