<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page  isELIgnored="false"  %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="bootstrap.jsp" %>

<title>Add Notes</title>
</head>
<body>
 <div class="container ">
        <%@include file="navbar.jsp" %>
        <br/>
        <h1> Please fill your note Form</h1>
        	<h1>${msg }</h1>
        	<form action="SaveNoteServlet" method="post">
			  <div class="form-group">
			    <label for="title">Note Title</label>
			    <input type="text" name="title" class="form-control" id="title" placeholder="Enter Title..." required>
			  </div>
			
			  <div class="form-group">
			    <label for="content">Notes</label>
			    <textarea class="form-control" name="content" id="content" rows="3" placeholder="Enter Content Here..."></textarea>
			  </div>
			  <div class="container text-center">
			  	<button type="submit" class="btn btn-primary ">Add Notes</button>
			  </div>
			  
			</form>
 		
    </div>

</body>
</html>