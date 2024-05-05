<%@page import="com.sathya.servlet.ProductDAO" import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CDN(Content Delivery Network) link to get support of BootStrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />

<title>Product List</title>
</head>
<body>
	<div class="form-group">
		<h1 class="font-italic font-weight-bold text-primary text-center">List
			of Products...</h1>
	</div>

	<div class="form-group">
		<a href="addProduct.html" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">Add More Product</a>
	</div>

	<div class="form-group">
		<input type="search" placeholder="search products">
	</div>

	<div class="form-group">
		<c:if test="${saveResult==1}">
			<h1 class="font-italic font-weight-bold text-success text-center">Data
				Inserted Successfully....</h1>
		</c:if>
	</div>

	<div class="form-group">
		<c:if test="${deleteResult==1}">
			<h1 class="font-italic font-weight-bold text-danger text-center">Data
				Deleted Successfully....</h1>
		</c:if>
	</div>

	<div class="form-group">
		<c:if test="${deleteResult==0}">
			<h1 class="font-italic font-weight-bold text-danger text-center">Data
				Deletion Failed....</h1>
		</c:if>
	</div>

	<div class="form-group">
		<c:if test="${updateResult==1}">
			<h1 class="font-italic font-weight-bold text-success text-center">Data
				Updated Successfully....</h1>
		</c:if>
	</div>

	<div class="form-group">
		<c:if test="${updateResult==0}">
			<h1 class="font-italic font-weight-bold text-danger text-center">Data
				Updation Failed....</h1>
		</c:if>
	</div>

	<table class="table table-bordered table-striped">
		<thead class="thead-dark">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Product Brand</th>
				<th>Made In</th>
				<th>Manufacturing date</th>
				<th>Expire date</th>
				<th>Product Image</th>
				<th class="text-center">Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="product" items="<%=new ProductDAO().findAll()%>">
				<tr>
					<td>${product.proId}</td>
					<td>${product.proName}</td>
					<td>${product.proPrice}</td>
					<td>${product.proBrand}</td>
					<td>${product.proMadeIn}</td>
					<td>${product.proMfgDate}</td>
					<td>${product.proExpDate}</td>
					<td style="text-align: center;"><img
						src="data:image/jpeg; base64, ${Base64.getEncoder().encodeToString(product.proImage)}"
						alt="Product Image" width="100px"></td>
					<td><a class="btn btn-primary"
						href="EditProductServlet?proId=${product.proId}">Edit</a> <a
						class="btn btn-danger"
						href="DeleteProductServlet?proId=${product.proId}">Delete</a></td>

				</tr>
			</c:forEach>

		</tbody>

	</table>
</body>
</html>