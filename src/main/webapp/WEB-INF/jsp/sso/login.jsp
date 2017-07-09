<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理系统</title>

    <link href="${basePath}/static/js/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/js/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/js/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/js/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/js/plugins/checkbix/css/checkbix.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/js/css/login.css" rel="stylesheet"/>
</head>
<body>
<div id="login-window">
    <div class="error">${error}</div>
    <form action="" method="post">
        用户名：<input type="text" name="username" value="<shiro:principal/>"><br/>
        密码：<input type="password" name="password"><br/>
        <input type="submit" value="登录">
    </form>
    <a id="login-bt" href="javascript:;" class="waves-effect waves-button waves-float"><i class="zmdi zmdi-arrow-forward"></i></a>
</div>
<script src="${basePath}/static/js/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/static/js/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/static/js/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/static/js/plugins/checkbix/js/checkbix.min.js"></script>
<script>var BASE_PATH = '${basePath}';</script>
<script>var BACK_URL = '${param.backurl}';</script>
<script src="${basePath}/static/js/common/login.js"></script>
<script>
<c:if test="${param.forceLogout == 1}">
alert('您已被强制下线！');
top.location.href = '${basePath}/sso/login';
</c:if>
//解决iframe下系统超时无法跳出iframe框架的问题
if (window != top){
    top.location.href = location.href;
}
</script>
</body>
</html>
