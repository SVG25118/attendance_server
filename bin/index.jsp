<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="server.*" %>
<%@ page import="java.util.Map" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Attendance Demo Server</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>

<body>
    
<% 
	String course = request.getParameter("course");
	String action = request.getParameter("action");
	
	// Validate that the group parameter is sane
	if (!DataStore.containsCourse(course))
	{
		// Bad group provided, so pretend none was provided.
		course = null;	
	}
	
	// Check if this is a reset request.
	if ("reset".equals(action))
	{
		DataStore.reset(course);
		response.sendRedirect("index.jsp?action=query&course="+ course);
	}
	// Check if this is a query request
	else if ("query".equals(action) && course != null)
	{
%>
<div class = "page-header">
<h1>Check-in listing: <%=course %></h1>
</div>

<table class="table">
<thead>
<tr>
<th>Uid</th><th>Key</th><th>Timestamp</th>
</tr>
</thead>
<tbody>
<%
	for (Checkin checkin : DataStore.getDevices(course).getStudents().values())
	{
		%>
        	<tr>
        	<td><%=checkin.getStudentID() %></td>
        	<td><%=checkin.getKey() %></td>
        	<td><%=checkin.getTime().getTime() %></td>
        	</tr>
        <% 
	}
	
%>
</tbody>
</table>
<p>
<a class="btn btn-sm btn-default" href="index.jsp?course=<%=course %>&action=reset">RESET CHECKINS</a>
<a class="btn btn-sm btn-default" href="index.jsp">HOME</a>

</p>
<%
	}
	// Otherwise it is an index page request
	else
	{
%>
<div class = "page-header">
<h1>Attendance Demo Server</h1>
</div>
<p>
<ul>
<% 
	for (Map.Entry<String,Course> c : DataStore.getCourses())
	{
	%>
		<li>
			<a href="index.jsp?action=query&course=<%=c.getValue().getName()%>"><%=c.getValue().getName()%></a><br />
			&emsp;<%=c.getValue().getLocation()%><br />
			&emsp;<%=c.getValue().getTime().getTime()%><br />
			&emsp;<%=c.getValue().getDescription()%><br />
			&emsp;<%=c.getValue().getQuestions().toString()%><br />
			&nbsp;
		</li>
	<%
	}
%>
</ul>
</p>

<% 
}
%>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
