package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import DAO.Llibres;
import DAO.User;
import Model.UserCRUD;

public class view {

	static Scanner sc;
	ArrayList<User> users = new ArrayList<>();

	// Constructor con parámetro Scanner
	public view(Scanner sc) {
		this.sc = sc;
	}

	public boolean login() {
		System.out.print("Gmail: ");
		String gmail = sc.next().toLowerCase();
		System.out.print("Password: ");
		String contraseña = sc.next().toLowerCase();

		// Verificar si las credenciales son correctas
		if (UserCRUD.checkLogin(gmail, contraseña)) {
			System.out.println("Welcome!");
			return true;
		} else {
			return false;
		}
	}

	public User register() {
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Gmail: ");
		String gmail = sc.next();
		System.out.print("Password: ");
		String password = sc.next();

		if (name.length() < 2 || gmail.length() < 2 || password.length() < 4) {
			System.out.println("Invalid data. Minimum 2 characters for first/last name and 4 for password.");
			return null;
		}

		System.out.println("User registered correctly in the database.");
		return new User(name, gmail, password);
	}

	public static Llibres addNewBook() {
		Llibres l = new Llibres();

		String newBook = "";
		while (newBook.isEmpty()) {
			System.out.println("Enter the title of the book: ");
			newBook = sc.next();
			if (newBook.isEmpty()) {
				System.err.println("Title cannot be empty.");
			}
		}
		l.setTitol(newBook);

		String newAutor = "";
		while (newAutor.isEmpty()) {
			System.out.println("Enter the author of the book:");
			newAutor = sc.next();
			if (newAutor.isEmpty()) {
				System.err.println("Author cannot be empty.");
			}
		}
		l.setAutor(newAutor);

		int newYearOfPubli = 0;
		while (newYearOfPubli <= 0) {
			System.out.println("Enter the year of publication of the book: ");
			if (sc.hasNextInt()) {
				newYearOfPubli = sc.nextInt();
				if (newYearOfPubli <= 0) {
					System.err.println("Year must be a positive number.");
				}
			} else {
				System.err.println("Invalid format, please enter a valid year:");
				sc.next();
			}
		}
		l.setAny_Publicacio(newYearOfPubli);

		String newDescription = "";
		while (newDescription.isEmpty()) {
			System.out.println("Enter the description of the book: ");
			newDescription = sc.next();
			if (newDescription.isEmpty()) {
				System.err.println("Description cannot be empty.");
			}
		}
		l.setDescripcio(newDescription);

		List<String> newCategory = new ArrayList<>();
		while (newCategory.isEmpty()) {
			System.out.println("Enter the categories of the book separated by commas (ex. Drama, Science fiction):");
			String input = sc.nextLine();
			if (!input.trim().isEmpty()) {
				String[] categoriesArray = input.split(",");
				for (String category : categoriesArray) {
					newCategory.add(category.trim());
				}
			} else {
				System.err.println("Category cannot be empty.");
			}
		}
		l.setCategories(newCategory);

		System.out.println("!Book added correctly!");
		return l;
	}

	public void getAll(ArrayList<Llibres> find) {

		System.out.println("All books; ");
		for (Llibres get : find) {
			System.out.println(get.toString());
		}

	}

	public int getYearFromUser(String message) {
	    System.out.print(message);
	    while (!sc.hasNextInt()) {
	        System.err.println("Invalid input. Enter a valid year:");
	        sc.next();
	    }
	    return sc.nextInt();
	}

	public void showBooks(List<Llibres> books) {
	    if (books.isEmpty()) {
	        System.out.println("No books found for the given year.");
	    } else {
	        System.out.println("\nBooks found:");
	        for (Llibres book : books) {
	            System.out.println("Title: " + book.getTitol());
	            System.out.println("Author: " + book.getAutor());
	            System.out.println("Year: " + book.getAny_Publicacio());
	            System.out.println("Description: " + book.getDescripcio());
	            System.out.println("Category: " + book.getCategories());
	            System.out.println("-----------------------------");
	        }
	    }
	}


	public void mainMenu() {
		System.out.println("-----------------------------");
		System.out.println("=== Welcome to LlibCat ===");
		System.out.println("-----------------------------");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("\nChoose an option: ");
		System.out.println("-----------------------------");

		while (!sc.hasNextInt()) {
			System.err.println("Invalid option, enter a number 1/2:");
			sc.next();
		}

	}

	public void secondMenu() {
		System.out.println("-----------------------------");
		System.out.println("=== LLIBCAT - Library ===");
		System.out.println("1. Add book");
		System.out.println("2. See all books");
		System.out.println("3. Search book by date");
		System.out.println("4. Exit");
		System.out.println("\nSelect an option: ");
		System.out.println("-----------------------------");

		while (!sc.hasNextInt()) {
			System.err.println("Invalid option, enter a number between 1-4:");
			sc.next();
		}

	}
}
