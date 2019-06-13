<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.entity.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>安装包构建报表</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
  <style type="text/css">
    table.hovertable{
      font-family:verdana,arial.sans-serif;
      font-size:13px;
      color:#333;
      border-width:1px;
      border-color:#999;
      border_collapse:collapse;
    }
    table.hovertable th{
      background-color:#c3dde0;
      border-width:1px;
      padding:8px;
      border-style:solid;
      border-color:#a9c6c9;
    }
    table.hovertable tr{
      background-color:#d4e3e5;
    }
    table.hovertable td{
      border-width:1px;
      padding:8px;
      border-style:solid;
      border-color:#a9c6c9;
    }
  </style>
</head>

<body>
<table class="hovertable">
  <tr>
    <th  colspan="6">构建表</th>
  </tr>
  <tr>
    <th>构建编号</th>
    <th>版本号</th>
    <th>版本名</th>
    <th>大小(MB)</th>
    <th>构建时间</th>
    <th>详情</th>
  </tr>
  <%
    List list=null;
    SimpleDateFormat df = new SimpleDateFormat("yy年MM月dd日HH时mm分");

    if(session.getAttribute("buildList")!=null){
      list=(List)session.getAttribute("buildList");
      if(list.size()>0){
        Manifest pf;
        String bt;
        String hrefStr;
        for(int i=0;i<list.size();i++){
          pf=new Manifest();
          pf=(Manifest)list.get(i);
          hrefStr = basePath+"DetailServlet?buildNumber="+pf.buildNumber;
          bt = df.format(pf.buildTime);

  %>
  <tr>
    <td><%=pf.buildNumber %></td>
    <td><%=pf.versionCode %></td>
    <td><%=pf.versionName %></td>
    <td><%=pf.size %></td>
    <td><%=bt %></td>
    <td><a class="button" href="<%=hrefStr%>">查看</a></td>
  </tr>
  <%
    }
  %>
  <%
      }
    }

  %>
</table>
<div id="container" style="height: 100%"></div>

<script type="text/javascript">
  var dom = document.getElementById("container");
  var myChart = echarts.init(dom);
  var app = {};
  option = null;
  var arrBuildNumber = new Array();
  var arrSize = new Array();
  var index = 0;
  <c:forEach items="${buildListTop}" var="bl">
    arrBuildNumber[index] = ${bl.buildNumber};
    arrSize[index] = ${bl.size};
    index++;
  </c:forEach>

  option = {
    title: {
      text: '安装包大小对比图(MB)'
    },
    tooltip: {
      show: true
    },
    legend: {
      data:['MB']
    },
    xAxis: {
      type: 'category',
      data: arrBuildNumber
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      data: arrSize,
      type: 'bar'
    }]
  };
  ;
  if (option && typeof option === "object") {
    myChart.setOption(option, true);
  }
</script>

</body>
</html>