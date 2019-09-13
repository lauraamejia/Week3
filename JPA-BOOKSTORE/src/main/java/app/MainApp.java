package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Book;

public class MainApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int option = 0;
		Scanner scanner = new Scanner(System.in);
		Book books;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (option != 5) {
			System.out.println("1. Add book");
			System.out.println("2. Look up book");
			System.out.println("3. Edit book");
			System.out.println("4. Delete book");
			System.out.println("5. Exit");
			System.out.println("Welcome to the bookstore! Please choose an option:");

			option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("Write the title of the book:");
				books = new Book();
				books.setIdbooks(null);
				scanner.nextLine();
				books.setTitle(scanner.nextLine());

				System.out.println("Write the price of the book:");
				books.setPrice(scanner.nextDouble());
				System.out.println(books);
				entity.getTransaction().begin();
				entity.persist(books);
				entity.getTransaction().commit();
				System.out.println("Book registered..");
				System.out.println();
				break;

			case 2:
				System.out.println("Write the ID of the book:");
				books = new Book();
				books = entity.find(Book.class, scanner.nextLong());
				if (books != null) {
					System.out.println(books);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Book was not found... List of books complete");
					List<Book> listOfBooks = new ArrayList<Book>();
					Query query = entity.createQuery("SELECT p FROM Book p");
					listOfBooks = query.getResultList();
					for (Book p : listOfBooks) {
						System.out.println(p);
					}

					System.out.println();
				}

				break;
			case 3:
				System.out.println("Write the ID of the book:");
				books = new Book();

				books = entity.find(Book.class, scanner.nextLong());
				if (books != null) {
					System.out.println(books);
					System.out.println("Write the name of the book:");
					scanner.nextLine();
					books.setTitle(scanner.nextLine());
					System.out.println("Write the price of the book:");
					books.setPrice(scanner.nextDouble());
					entity.getTransaction().begin();
					entity.merge(books);
					entity.getTransaction().commit();
					System.out.println("Book updated!");
					System.out.println();
				} else {
					System.out.println("Book was not found.......");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Write the ID of the book to be deleted:");
				books = new Book();

				books = entity.find(Book.class, scanner.nextLong());
				if (books != null) {
					System.out.println(books);
					entity.getTransaction().begin();
					entity.remove(books);
					entity.getTransaction().commit();
					System.out.println("Book deleted!");
				} else {
					System.out.println("Book was not found...");
				}
				break;
			case 5:
				entity.close();
				System.out.println("Goodbye!");
				JPAUtil.shutdown();
				break;

			default:
				System.out.println("Not a valid option\n");
				break;
			}
		}
	}
}
