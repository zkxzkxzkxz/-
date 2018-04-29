<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户资料管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("table#contentTable").find("select").each(function(){
				$(this).val($(this).attr("data-value"));
				$(this).parent().find('span.select2-chosen').html($(this).attr("data-text"));

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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/crmCustomfollow/">客户资料跟踪</a></li>
		<%-- <shiro:hasPermission name="crm:crmCustomfollow:edit"><li><a href="${ctx}/crm/crmCustomfollow/form">客户资料添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="crmCustom" action="${ctx}/crm/crmCustomfollow/" method="post" class="breadcrumb form-search">
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<form:form id="batCustomModify"  action="${ctx}/crm/crmCustomfollow/batCustomFollow" method="post" class="form-horizontal">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th class="hide"></th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">客户姓名</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">手机号</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">QQ号</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">跟踪状态</th>
					<th style="width:500px;word-break:break-all; word-wrap:break-word;">备注</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">最近联系日期</th>
					<shiro:hasPermission name="crm:crmCustomfollow:edit">
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="custom" varStatus="idx">
				<tr>
					<td class="hide">
						<input id="customs${idx.index}_id" name="customs[${idx.index}].id" type="hidden" value="${custom.id}"/>
					</td>
					<td>
						${custom.customname}
					</td>
					<td>
						${custom.phone}
					</td>
					<td>
						${custom.qq}
					</td>
					<td>
						<select id="customs${idx.index}_followstate" name="customs[${idx.index}].followstate" data-value="${custom.followstate}"
							 data-text="${fns:getDictLabel(custom.followstate, 'FLLOWSTATE', '')}" style="width:95%;">
							<option value=""></option>
							<c:forEach items="${fns:getDictList('FLLOWSTATE')}" var="dict">
								<option value="${dict.value}">${dict.label}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input id="customs${idx.index}_remarks" name="customs[${idx.index}].remarks"
							 type="text" value="${custom.remarks}" 
							 class="input-small required" style="width:90%;"/>
					</td>
					<td>
						<fmt:formatDate value="${custom.lastdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<shiro:hasPermission name="crm:crmCustomfollow:edit">
					<td>
	    				
					</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
		<div class="form-actions">
			<shiro:hasPermission name="crm:crmCustomfollow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>