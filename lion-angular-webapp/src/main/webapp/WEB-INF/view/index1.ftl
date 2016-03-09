<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#assign context = request.contextPath/>
<#import "/common/spring.ftl" as spring/>
<script type="text/javascript">
	var b=233;
</script>
</head>
<body>
<@shiro.guest>
未登录
</@shiro.guest>
<@shiro.user>
 欢迎<@shiro.principal name="name"/>登录，<a href="${context}/logout.htm">点击退出</a><br/>
</@shiro.user>
Hello,<@shiro.principal name="name"/>, how are you today? 
<br/>
<br/>
<a href="${context}/logout.htm">点击退出</a><br/>
</body>
</html>