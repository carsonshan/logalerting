<%@page import="com.vzwcoders.jmx.client.StatsClient"%>
<%@page import="com.vzwcoders.vo.LogProcessorVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <META HTTP-EQUIV="refresh" CONTENT="2;url=<%=path%>/monitor.jsp">
    <base href="<%=basePath%>">
    
    <title>Alerting Job Monitoring</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table border=1>
    <%
   StatsClient.init();
   System.out.println("After jmx inti");
   LogProcessorVO v=StatsClient.getBeanData();
   System.out.println("Bean object is "+v);
    %>
   <tr><td colspan=3><%if(v==null){%>
     <img width="100" height="100" alt="Process Stopped or Not Running" src="<%=path%>/images/stop.jpg"/>
     <br/>Process Stopped or Not Running
      <% }else{%>
      <img width="100" height="100" alt="Process Stopped or Not Running" src="<%=path%>/images/Running.jpg"/>
      <br/>Process is Running Succesfully
      <% }%>
      </td></tr>
   <tr>
   <th>Queue Size</th>
   <th>Alerts Inserted in DB</th>
   <th>Time</th>
   </tr>
  
     <tr><td colspan=3>Log Processor Status</td><tr>
     
     <%if(v!=null){%>
   <tr><td><%=v.getMsgCount() %></td><td><%=v.getqCount() %></td><td></td><tr>
   <%} %>
   </table>
  </body>
</html>
