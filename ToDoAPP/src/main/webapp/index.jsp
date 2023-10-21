<!doctype html>
<html lang="en">
  <head>
	<%@include file="bootstrap.jsp" %>
    <title>Notes Takes: ToDo App</title>
  </head>
  <body>
    <div class="container ">
        <%@include file="navbar.jsp" %>
        <br/>
        <h1> Please fill your note Form</h1>
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