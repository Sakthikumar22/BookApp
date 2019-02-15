package com.chainsys.jdbc;

import java.util.ArrayList;
import java.util.Scanner;

public class TestBookDAO {
	
	public static void main(String[] args) throws Exception {
		BookDAO bookDAO = new BookDAO();
		Scanner scanner = new Scanner(System.in);
		Book book = null;
		int choice;
		System.out
				.println("1.ADD BOOK DETAILS \n2.UPDATE BOOK DETAILS\n3.DELETE BOOK DETAILS\n4.FIND BOOK BY ID\n5.DISPLAY ALL BOOKS");
		System.out.println("Enter your choice:");
		choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter id,name,price,date to add");
			book = new Book();
			book.id = scanner.nextInt();
			book.name = scanner.next();
			book.price = scanner.nextFloat();
			String date=scanner.next();
BookValidator bookValidator= new BookValidator();
bookValidator.validateAdd(book);
			bookDAO.addBook(book);
			bookDAO.findAll();
			break;
		case 2:
			System.out.println("Enter id,name to update");
			int id1 = scanner.nextInt();
			String name1 = scanner.next();

			bookDAO.updateBook(id1, name1);
			bookDAO.findAll();
			break;
		case 3:
			System.out.println("Enter id to delete");
			int id2 = scanner.nextInt();

			bookDAO.deleteBook(id2);
			bookDAO.findAll();
			break;

		case 4:
			System.out.println("Enter id to Find by Id");
			book=new Book();
			book.id=scanner.nextInt();
			Book book1=bookDAO.findById(book);
			if(book1==null)
			{
				System.out.println("No Record");
			}
			else
			{
				System.out.println(book1.id);
				System.out.println(book1.name);
				System.out.println(book1.price);
			}
			break;
		case 5:

		ArrayList<Book> bookList=bookDAO.findAll();
		if(bookList.isEmpty()){
			System.out.println("no rec");
		}
		else
		{
			for(Book b:bookList){

				System.out.println(b.name);
				System.out.println(b.price);
				
			}
		}

			break;
		}

		scanner.close();

	}
}
