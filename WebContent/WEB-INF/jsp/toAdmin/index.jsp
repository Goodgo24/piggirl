<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>猪猪女孩后台管理页面</title>
<%@ include file="../global/taglibs.jsp"%>
<%@ include file="../global/resCss.jsp" %><%@ include file="../global/resJs.jsp" %> 
</head>
<body>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <%@ include file="../global/top.jsp"%>
  <%@ include file="../global/leftMenu.jsp"%>
  
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">代购列表</div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
  </div>
</div>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>