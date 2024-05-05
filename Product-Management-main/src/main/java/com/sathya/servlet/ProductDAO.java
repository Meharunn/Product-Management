package com.sathya.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
//import java.sql.Statement;

public class ProductDAO {

	// This method will save the products to Database
	public int saveProduct(Product prod) {
		int count = 0;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into product_details values(?,?,?,?,?,?,?,?)");) {
			preparedStatement.setString(1, prod.getProId());
			preparedStatement.setString(2, prod.getProName());
			preparedStatement.setDouble(3, prod.getProPrice());
			preparedStatement.setString(4, prod.getProBrand());
			preparedStatement.setString(5, prod.getProMadeIn());
			preparedStatement.setDate(6, prod.getProMfgDate());
			preparedStatement.setDate(7, prod.getProExpDate());
			preparedStatement.setBytes(8, prod.getProImage());

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	// This method will return all the Product details
	public List<Product> findAll() {
		List<Product> prodList = new ArrayList<Product>();
		try (Connection connection = DBConnection.createConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("select * from product_details ORDER BY rowId DESC");
			while (resultSet.next()) {
				Product product = new Product();
				product.setProId(resultSet.getString(1));
				product.setProName(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadeIn(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setProImage(resultSet.getBytes(8));

				prodList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}

	// This method will delete the Product based on id
	public int deleteById(String prodId) {
		int result = 0;

		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("delete from product_details where proId=?")) {

			preparedStatement.setString(1, prodId);
			result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("Deleted");
			} else {
				System.out.println("Not Deleted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// This method will give the product details by Id
	public Product getProductById(String proId) {
		Product product = null;

		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from product_details where proId=?")) {

			preparedStatement.setString(1, proId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setProId(resultSet.getString(1));
				product.setProName(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadeIn(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setProImage(resultSet.getBytes(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	// This method will update product 
	public int updateProduct(Product product) {
		int count = 0;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"update product_details set proName=?, proPrice=?, proBrand=?, proMadeIn=?, proMfgDate=?, proExpDate=?, proImage=? "
						+ "where proId=?")) {

			preparedStatement.setString(1, product.getProName());
			preparedStatement.setDouble(2, product.getProPrice());
			preparedStatement.setString(3, product.getProBrand());
			preparedStatement.setString(4, product.getProMadeIn());
			preparedStatement.setDate(5, product.getProMfgDate());
			preparedStatement.setDate(6, product.getProExpDate());
			preparedStatement.setBytes(7, product.getProImage());
			preparedStatement.setString(8, product.getProId());

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
