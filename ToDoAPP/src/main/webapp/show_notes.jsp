<%@page import="com.entites.Note"%>
<%@page import="java.util.List"%>
<%@page import="com.helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="bootstrap.jsp" %>
<title>Show Notes</title>
</head>
<body>
<div class="container">
	<%@include file="navbar.jsp" %>
	
	<%
		Session session2 = FactoryProvider.getFactory().openSession();
	
		List<Note> noteList = session2.createCriteria(Note.class).list();
		
	%>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">N.No</th>
		      <th scope="col">title</th>
		      <th scope="col">Note</th>
		      <th scope="col">Date</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%for(Note notes : noteList) {%>

			    <tr>
			      <th scope="row"><%=notes.getId()%></th>
			      <td><%=notes.getTitle() %></td>
			      <td><%=notes.getContent() %></td>
			      <td><%=notes.getAddedDate() %></td>
			    </tr>
			 	<%}%>
			   </tbody>
			  
		 </table>
		 <%session2.close();%>
		  
		

</div>

</body>
</html>