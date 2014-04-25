<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page  import="com.lq.web01.platform.util.SpringContextUtils"%>
<%@page  import="com.lq.web01.module.security.service.OnlineUserService"%>
<%@page  import="com.lq.web01.module.security.service.SpringSecurityService"%>
<%@page  import="com.lq.web01.module.security.service.UserDetailsServiceImpl"%>
<%@page  import="com.lq.web01.module.system.service.PropertyHolder"%>
<%@page  import="java.util.List"%>
<%@page  import="com.lq.web01.platform.util.FileUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.addHeader("login", "true");

//供记录用户登录日志使用
String userAgent = request.getHeader("User-Agent");
request.getSession().setAttribute("userAgent", userAgent);
if(!SpringSecurityService.isSecurity()){
	// 如果没有启用安全机制则直接进入主界面
	response.sendRedirect("platform/index.jsp");
	return;
}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>应用系统</title>
		<link rel="stylesheet" type="text/css"
		href="extjs/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<script type="text/javascript" src="extjs/ext-all-dev.js"></script>
		<script type="text/javascript" src="js/BuyModel.js"></script>
	</head>
	<body>
		<div id="loading-mask">
			<div id="loading">
				<div style="text-align: center; padding-top: 26%">
					<img alt="Loading..." src="images/extanim32.gif" width="32"
					height="32" style="margin-right: 8px;" />Loading...
				</div>
			</div>
		</div>

		<script>
			Ext.onReady(function() {
				BuyModel.render("loading-mask");
				BuyModel.center();
			});
		</script>
	</body>
</html>