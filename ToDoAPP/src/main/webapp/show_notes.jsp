<%@page import="javax.xml.crypto.NodeSetData"%>
<%@page import="org.hibernate.query.Query"%>
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
	<div class="col-12">
	<%
		Session session2 = FactoryProvider.getFactory().openSession();
		Query query = session2.createQuery("from Note");
		List<Note> noteList = query.list();
	%>
		  <%for(Note notes : noteList) {%>

			<div class="card">
			  <div class="card-body">
			    <img src="imgs/note.png" style="max-width:100px;"class="card-img-top m-4" alt="...">
			    <h5 class="card-title px-5"><%=notes.getTitle() %></h5>
			    <p class="card-text"><%=notes.getContent() %></p>
				<div class="container text-center">
				<a href="DeleteServlet?id=<%=notes.getId() %>" class="btn btn-danger">Delete</a>
			    <a href="edit.jsp?id=<%=notes.getId() %>" class="btn btn-primary">Update</a>
				</div>
			    
			    
			  </div>
			</div>
			<%}%>
		<%session2.close();%>
	</div>
		  
		

</div>

</body>
</html>