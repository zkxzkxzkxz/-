<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="/crm/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="/crm/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="/crm/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="/crm/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="/crm/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="/crm/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="/crm/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="/crm/static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="/crm/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="/crm/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="/crm/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="/crm/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="/crm/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="/crm/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/crm/static/common/mustache.min.js" type="text/javascript"></script>
<link href="/crm/static/common/oaeccrm.css" type="text/css" rel="stylesheet" />
<script src="/crm/static/common/oaeccrm.js" type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	
	<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
	<form id="registerForm" class="form-signin" action="${pageContext.request.contextPath }/a/register" method="post">
		<input type="hidden" name="roleList" id="roleIdList"/>
		<label class="input-label" for="password">姓名</label>
		<input type="text" id="name" name="name" class="input-block-level required"><br>
		<label class="input-label" for="loginName">登录名</label>
		<input type="text" id="loginName" name="loginName" class="input-block-level required" value="${username}"><br>
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required"><br>
		<label class="input-label" for="password">确认密码</label>
		<input type="password" id="confirmPassword" name="confirmPassword" class="input-block-level required"><br>
		
		<label class="input-label" for="password">邮箱</label>
		<input type="text" id="email" name="email" class="input-block-level required"><br>
		<label class="input-label" for="password">电话</label>
		<input type="text" id="phone" name="phone" class="input-block-level required"><br>
		<label class="input-label" for="password">用户身份</label>
		<select name="userType" onchange="userTypeChange()">
			<option value="1">超级管理员</option>
			<option value="2">品牌商</option>
			<option value="3">借卖方</option>
		</select>
		<input class="btn btn-large btn-primary" type="submit" value="注册"/>&nbsp;&nbsp;	
	</form>
	<div class="footer">
		
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>