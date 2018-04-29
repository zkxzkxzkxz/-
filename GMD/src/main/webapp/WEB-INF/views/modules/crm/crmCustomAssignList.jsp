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
		
		function save() {
			if($('#usertype').val() == '') {
				alert('用户类型不能为空！');
				return;
			}
			if($("#ids:checked").length == 0) {
				alert('客户资料不能为空！');
				return;
			}
			if($('#useridId').val() == '') {
				alert('分配用户不能为空！');
				return;
			}
			$("#customAssignForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/crmCustomAssign/">客户资料分配</a></li>
		<%-- <shiro:hasPermission name="crm:crmCustomAssign:edit"><li><a href="${ctx}/crm/crmCustomAssign/form">客户资料添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="crmCustom" action="${ctx}/crm/crmCustomAssign/" method="post" class="breadcrumb form-search">
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
	<form:form id="customAssignForm" modelAttribute="crmCustom" action="${ctx}/crm/crmCustomAssign/customAssign" method="post" class="form-horizontal">
		<div class="control-group" style="margin-top:50px;">
			<label class="control-label" style="width:90px;">用户类型：</label>
			<div class="controls" style="margin-left:100px">
				<form:select path="usertype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('USERTYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="width:90px;">分配用户：</label>
			<div class="controls" style="margin-left:100px">
				 <sys:treeselect id="userid" name="userid" value="" labelName="userid" labelValue=""
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="width:50px;word-break:break-all; word-wrap:break-word;">选择</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">客户姓名</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">手机号</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">QQ号</th>
					<th style="width:200px;">所属员工</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">跟踪状态</th>
					<th style="width:200px;word-break:break-all; word-wrap:break-word;">最近联系日期</th>
					
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="custom" varStatus="idx">
				<tr>
					<td>
						<input type="checkbox" id="ids" name="ids" value="${custom.id}">
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

					 	${fns:getUserById(custom.userid).name}

					</td>
					<td>
						${fns:getDictLabel(custom.followstate, 'FLLOWSTATE', '')}
					</td>
					<td>
						<fmt:formatDate value="${custom.lastdate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
		<div class="form-actions">
			<shiro:hasPermission name="crm:crmCustomAssign:edit"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="save()" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>