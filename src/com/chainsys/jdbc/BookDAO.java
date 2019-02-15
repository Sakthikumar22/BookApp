package com.chainsys.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public void addBook(Book book) throws SQLException {
		connection=ConnectionUtil.getConnection();
		String sql = "insert into books(id,name,price,publishDate)values(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,book.id);
		preparedStatement.setString(2,book.name);
		preparedStatement.setFloat(3,book.price);
		preparedStatement.setDate(4,Date.valueOf(book.publishDate));

		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + " row affected");
		ConnectionUtil.close(connection, preparedStatement, resultSet);

	}

	public void updateBook(int id, String name) throws SQLException {
		connection=ConnectionUtil.getConnection();
		String sql1 = "update books set name=?,price=? where id=?";

		preparedStatement = connection.prepareStatement(sql1);

		preparedStatement.setString(1, name);

		preparedStatement.setInt(2, id);
		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + "row updated");

	}

	public void deleteBook(int id) throws SQLException {
		connection=ConnectionUtil.getConnection();
		String sql1 = "delete from books where id=?";

		preparedStatement = connection.prepareStatement(sql1);

		preparedStatement.setInt(1, id);
		int rows1 = preparedStatement.executeUpdate();
		System.out.println(rows1 + "row updated");

	}

	public ArrayList<Book> findAll() throws SQLException {
		connection=ConnectionUtil.getConnection();
		System.out.println("\n\n*******BOOK DETAILS************");
		String sql4 = "select id,name,price from books order by name asc";

		preparedStatement = connection.prepareStatement(sql4);

		System.out.println("ID\tNAME\tPRICE");
		resultSet = preparedStatement.executeQuery();
		ArrayList<Book> list= new ArrayList<Book>();
		while (resultSet.next()) {

			Book book =new Book();
			book.id=resultSet.getInt("id");
			book.name=resultSet.getString("name");
			book.price=resultSet.getFloat("price");
			list.add(book);
			
		}
		return list;
	}

	public Book findById(Book book) throws SQLException {
		Book bookobj=null;
		connection=ConnectionUtil.getConnection();
		String sql3 = "select id,name,price from books where id=?";

		System.out.println(sql3);
		preparedStatement = connection.prepareStatement(sql3);

		preparedStatement.setInt(1,book.id);
		resultSet = preparedStatement.executeQuery();
		 

		if (resultSet.next()) {
			bookobj=new Book();
			bookobj.id=resultSet.getInt("id");
			bookobj.name=resultSet.getString("name");
			bookobj.price=resultSet.getFloat("price");
		}
		return bookobj;
	}
}


	


