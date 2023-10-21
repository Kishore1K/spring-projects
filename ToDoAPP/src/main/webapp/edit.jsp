<%@page import="com.helper.FactoryProvider"%>
<%@page import="com.entites.Note"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page  isELIgnored="false"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="bootstrap.jsp" %>
<title>Update</title>
</head>
<body>
<div class="container">
	<%@include file="navbar.jsp" %>
	<h1>${title	 }</h1>
	<%	
			int id = Integer.parseInt(request.getParameter("id").trim());
			Session session2 = FactoryProvider.getFactory().openSession();
			
			Note note = (Note)session2.get(Note.class, id);
	
	
			
	%>
	
		
			<form action="UpdateServlet" method="post">
			  <div class="form-group">
			  	<input type="hidden" name="id" value="<%=note.getId() %>" > 
			    <label for="title">Note Title</label>
			    <input type="text" name="title" class="form-control" id="title"value="<%=note.getTitle() %>"  required>
			  </div>
			
			  <div class="form-group">
			    <label for="content">Notes</label>
			    <textarea class="form-control" name="content" id="content" rows="3" ><%=note.getContent() %></textarea>
			  </div>
			 <input type="hidden" name="date" value="<%=note.getAddedDate() %>" > 
			  
			  <div class="container text-center">
			  	<button type="submit" class="btn btn-primary ">Update Notes</button>
			  </div>
			  
			</form>			
			  
</div>

</body>
</html>