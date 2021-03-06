<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>博纳云管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${basePath}bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${basePath}css/login.css" />
<link rel="shortcut icon" href="fav.ico" />
<script src="${basePath}js/common/jquery-1.11.1.min.js"></script>
</head>
<body class="login-body">
	<div class="home">
		<div class="logo"></div>
		    <div id="warnbox" class="alert alert-warning text-center" style="margin-top:50px;" role="alert">您的浏览器对Html5的支持性不是很好！为了您拥有最佳的用户体验，请您更换以下浏览器访问:
		  <br/>
		   <br/>
		  <ul class="text-left" style="margin-left:400px;">
		  <li>IE浏览器,我们仅支持IE9+,最好使用IE10及以上版本</li>
		  <li>Firefox浏览器,我们建议使用 Firefox 30+ 版本</li>
		  <li>Chrome浏览器,我们建议使用 Chrome 35+ 版本</li>
		  </ul>
		     <br/>
		  推荐使用新版Chrome  <a href="/file/Chorme.rar">点击下载</a></div>
		<div class="login-box" id="loginbox" style="display:none">
			<form id="login-form"  action="${basePath}backdoor" autocomplete="off"
				method="post" class="login-form">
				<div style="margin: 12px 0">
					<input type="text" name="user" class="login-input"
						placeholder="用户名">
				</div>
				<div style="margin: 12px 0">
					<input type="password" name="password" class="login-input"
						placeholder="密码">
				</div>
				<div style="margin: 12px 0">
					<input type="text" name="vercode" class="login-input" placeholder="验证码">
					<div style="margin-top: 12px">
						<img alt="验证码" id="authImg">
						<a id="ver-change" class="ver-change" onclick="refresh()">换一个</a>
					</div>
				</div>
				<div>
					<input type="submit" class='btn btn-primary' value="登录">
				</div>
			</form>
		</div>
		<div class="footer">
			<!--			<a>版权所有：中国科学院软件研究所</a>-->
			<a>&nbsp;&nbsp;</a>
		</div>
	</div>
	<script>
		refresh();
		
		function refresh() {
			$("#authImg").attr("src", "${basePath}captcha?" + Math.random());
		}
		
		 $(function(){
			if (window.File && 'WebSocket' in window)
		    {
				$("#loginbox").show();
				$("#warnbox").hide();
				
		    } else
	    	{
		    	$("#loginbox").hide();
				$("#warnbox").show();
	    	}
		}) 
	</script>
</body>
</html>