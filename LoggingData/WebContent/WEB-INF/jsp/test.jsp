<%@page import="com.vo.LogEvent"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Verizon Log Analyzer and Alerting</title>
<META HTTP-EQUIV="refresh" CONTENT="2;url=/LoggingData">
<style type="text/css">
body
{
padding:0 20px;
}
#wrapper
{
margin: auto;
width: 800px;
}
.contents
{
width: 91%; /*height: 150px;*/
margin: 0;
}
.contents > p
{
padding: 8px;
}
.table

{

width: 100%;

border-right: solid 1px #5f9000;

}

.table th, .table td

{

width: 16%;

height: 20px;

padding: 4px;

text-align: left;

}

.table th

{

border-left: solid 1px #5f9000;

}

.table td

{

border-left: solid 1px #5f9000;

border-bottom: solid 1px #5f9000;

}

.header

{

background-color: #4f7305;

color: White;

}

#divs

{

margin: 0;

height: 200px;

font: verdana;

font-size: 14px;

background-color: White;

}

#divs > div

{

width: 98%;

padding: 8px;

}

#divs > div p

{

width: 95%;

padding: 8px;

}

ul.tab

{

list-style: none;

margin: 0;

padding: 0;

}

ul.tab li

{

display: inline;

padding: 10px;

color: White;

cursor: pointer;

}

#container

{

width: 100%;

border: solid 1px red;

}

</style>
</head>
<body>
<%
List<LogEvent> eventList = (List<LogEvent>)session.getAttribute("eventList");
%>
<center><h1>Security Monitoring and Alerting System</h1></center>
<div id="green-contents" class="contents" style="border: solid 1px #5f9000;">
<table id="mt" cellpadding="0" cellspacing="0" border="0" class="table">
<tr class="header">
<th>Id</th>
<th>Date</th>

<th>Logger Statement</th>
<th>Words Matcheds</th>
<th>Alert Time</th>
</tr>
<%
for(LogEvent event:eventList)
{
%>
<tr>
<td><%=event.getId()%></td>
<td><%=event.getCreatedTimeStamp()%></td>
<td><%=event.getData()%></td>
<td><%=event.getKeyWords()%></td>
<td><%=event.getCreatedTimeStamp()%></td>
</tr>
<%
}
%>
</table></div>
</body>
</html>
