<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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