package com.sathya.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read the from data
		String proId = request.getParameter("proId");
		String proName = request.getParameter("proName");
		double proPrice = Double.parseDouble(request.getParameter("proPrice"));
		String proBrand = request.getParameter("proBrand");
		String proMadeIn = request.getParameter("proMadeIn");
		Date proMfgDate = Date.valueOf(request.getParameter("proMfgDate"));
		Date proExpDate = Date.valueOf(request.getParameter("proExpDate"));

		Part part = request.getPart("newProImage");

		// Using above details create Product object
		Product product = new Product();
		product.setProId(proId);
		product.setProName(proName);
		product.setProPrice(proPrice);
		product.setProBrand(proBrand);
		product.setProMadeIn(proMadeIn);
		product.setProMfgDate(proMfgDate);
		product.setProExpDate(proExpDate);

		if (part.getSize() != 0) {
			InputStream inputStream = part.getInputStream();
			byte[] proImage = IOUtils.toByteArray(inputStream);
			product.setProImage(proImage);
		} else {
			String s = request.getParameter("oldProImage");
			byte[] proImage = Base64.getDecoder().decode(s);
			product.setProImage(proImage);
		}
		// Giving the product object to ProductDAO layer save method to save the data
		// into database
		ProductDAO productDAO = new ProductDAO();
		int result = productDAO.updateProduct(product);

		request.setAttribute("updateResult", result);

		if (result == 1) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("editForm.jsp");
			dispatcher1.include(request, response);
		}
	}

}
