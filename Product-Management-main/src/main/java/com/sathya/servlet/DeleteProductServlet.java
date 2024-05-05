package com.sathya.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read the Product Id
		String proId = request.getParameter("proId");

		// Create the ProductDAO object
		ProductDAO productDAO = new ProductDAO();

		// Give the proId to deleteById() method of ProductDAO layer using productDAO
		// object and store the result
		int result = productDAO.deleteById(proId);

		// Store the result to request object and sent to jsp layer
		request.setAttribute("deleteResult", result);
		
		// Forward the page to productList.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
		dispatcher.forward(request, response);
	}

}
