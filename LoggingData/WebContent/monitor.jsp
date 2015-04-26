<%@page import="com.vzwcoders.vo.MsgConsumerVO"%><%@page import="com.vzwcoders.jmx.client.MsgConsumerStatsClient"%>
<%@page import="com.vzwcoders.jmx.client.StatsClient"%><%@page import="com.vzwcoders.vo.LogProcessorVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head><META HTTP-EQUIV="refresh" CONTENT="2;url=<%=path%>/monitor.jsp">
<base href="<%=basePath%>"><title>Alerting Job Monitoring</title>
<meta http-equiv="pragma" content="no-cache"><meta http-equiv="cache-control" content="no-cache"><meta http-equiv="expires" content="0">
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
<br/>Log Processor Process Stopped or Not Running
<% }else{%>
<img width="100" height="100" alt="Process Stopped or Not Running" src="<%=path%>/images/Running.jpg"/>
<br/>Process is Running Succesfully
<% }%>
</td></tr>
<tr><td >Start Time :<%if(v!=null)out.print(v.getStartTime()); %></td><td >Current Time :<%=new Date() %></td><tr>
<tr><th>Log Message Count</th><th>Message count posted on Queue</th></tr>
<%if(v!=null){%>
<tr><td><%=v.getMsgCount() %></td><td><%=v.getqCount() %></td><tr>
<%} %>
</table><table border=1><%
MsgConsumerStatsClient.init();
System.out.println("MsgConsumerStats After jmx inti");
MsgConsumerVO m=MsgConsumerStatsClient.getBeanData();
System.out.println("MsgConsumerVO Bean object is "+m);
%>
<tr><td colspan=2><%if(m==null){%>
<img width="100" height="100" alt="Process Stopped or Not Running" src="<%=path%>/images/stop.jpg"/>
<br/>Queue Consumer Process Stopped or Not Running
<% }else{%>
<img width="100" height="100" alt="Process Stopped or Not Running" src="<%=path%>/images/Running.jpg"/>
<br/>Queue Consumer Process is Running Succesfully
<% }%></td></tr>
<tr><td >Start Time :<%if(m!=null)out.print(m.getStartTime()); %></td><td >Current Time :<%=new Date() %></td><tr>
<tr><th>Messages From Queue</th><th>Inserted in DB</th></tr>
<%if(m!=null){%>
<tr><td><%=m.getMsgReceiveCount() %></td><td><%=m.getDbInsertCount() %></td><tr>
<%} %></table></body></html>