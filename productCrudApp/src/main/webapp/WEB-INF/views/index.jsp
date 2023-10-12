<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center"> Welcome To Product CRUD Application</h1>
				<table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">S.No</th>
				      <th scope="col">Product Name</th>
				      <th scope="col">Description</th>
				      <th scope="col">Price</th>
				      <th scope="col">Action</th>
				    </tr>
				  </thead>
				  <tbody>
				  
					  <c:forEach items="${products}" var="s" varStatus="loop">
					  
					  	<tr>
					      <th scope="row">${loop.count }</th>
					      <td>${s.name }</td>
					      <td>${s.description }</td>
					      <td>${s.price }</td>
					      <td><a href="delete/${s.id}" ><i class="fas fa-trash fa-2x text-danger"></i></a>
					      &nbsp;&nbsp;&nbsp;&nbsp;
					      <a href="update/${s.id }"><i class="fa-solid fa-file-pen  fa-2x"> </i></a>
					      </td>
					    </tr>
				  
	    			  </c:forEach>
				    
				  </tbody>
				</table>
				<div class="container text-center">
					<a href="addProduct" class="btn btn-outline-success">Add Product</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>