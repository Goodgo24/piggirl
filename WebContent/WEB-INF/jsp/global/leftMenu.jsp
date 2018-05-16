<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">代购管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">代购列表</a></dd>
            <dd><a href="javascript:;">客户列表</a></dd>
            <dd><a href="javascript:;">额外开销</a></dd>
            <dd><a href="javascript:;">快递管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">系统管理</a>
          <dl class="layui-nav-child">
            <dd><a href="${ctxPath}/user/list" >用户管理</a></dd>
            <dd><a href="javascript:;">参数管理</a></dd>
           <!--  <dd><a href=""></a></dd> -->
          </dl>
        </li>
       <!--  <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li> -->
      </ul>
    </div>
  </div>