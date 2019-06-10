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
  <title>安装包详情报表</title>

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
<div id="component" style="width: 600px;height:400px;"></div>
<div id="unZipFile" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
  var componentDom = document.getElementById("component");
  var componentChart = echarts.init(componentDom);
  componentOption = {
    title : {
      text: 'apk成份占比图',
      x:'center'
    },
    tooltip : {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: [
        <c:forEach items="${componentList}" var="c">
        ["${c.suffix}"],
        </c:forEach>
      ]
    },
    series : [
      {
        name: '大小(MB)',
        type: 'pie',
        radius : '55%',
        center: ['50%', '60%'],
        data:[
          <c:forEach items="${componentList}" var="component">
          {value:"${component.totalSize}", name:"${component.suffix}"},
          </c:forEach>
        ],
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };
  if (componentOption && typeof componentOption === "object") {
    componentChart.setOption(componentOption, true);
  }

  var unZipFileDom = document.getElementById("unZipFile");
  var unZipFileChart = echarts.init(unZipFileDom);
  unZipFileOption = {
    title : {
      text: 'apk成份占比图',
      x:'center'
    },
    tooltip : {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: [
        <c:forEach items="${unZipFileList}" var="c">
        ["${c.suffix}"],
        </c:forEach>
      ]
    },
    series : [
      {
        name: '大小(byte)',
        type: 'pie',
        radius : '55%',
        center: ['50%', '60%'],
        data:[
          <c:forEach items="${unZipFileList}" var="unZipFile">
          {value:"${unZipFile.totalSize}", name:"${unZipFile.suffix}"},
          </c:forEach>
        ],
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };
  if (unZipFileOption && typeof unZipFileOption === "object") {
    unZipFileChart.setOption(unZipFileOption, true);
  }
</script>
<table class="hovertable">
  <tr>
    <th  colspan="2">不含alpha通道的png文件列表</th>
  </tr>
  <tr>
    <th>文件名</th>
    <th>大小(byte)</th>
  </tr>
  <%
    List<PNGFile> pNGFileList=null;
    if(session.getAttribute("pNGFileList")!=null){
      pNGFileList=(List)session.getAttribute("pNGFileList");
      if(pNGFileList.size()>0){
        PNGFile pf;
        for(int i=0;i<pNGFileList.size();i++){
          pf=pNGFileList.get(i);
  %>
  <tr>
    <td><%=pf.entryName %></td>
    <td><%=pf.entrySize %></td>
  </tr>
  <%
    }
  %>
  <%
      }
    }

  %>
</table>
<table class="hovertable">
  <tr>
    <th  colspan="2">大文件列表</th>
  </tr>
  <tr>
    <th>文件名</th>
    <th>大小(byte)</th>
  </tr>
  <%
    List<BigFile> bigFileList=null;
    if(session.getAttribute("bigFileList")!=null){
      bigFileList=(List)session.getAttribute("bigFileList");
      if(bigFileList.size()>0){
        BigFile pf;
        for(int i=0;i<bigFileList.size();i++){
          pf=bigFileList.get(i);
  %>
  <tr>
    <td><%=pf.entryName %></td>
    <td><%=pf.entrySize %></td>
  </tr>
  <%
    }
  %>
  <%
      }
    }

  %>
</table>
</body>
</html>