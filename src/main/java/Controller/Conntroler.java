package Controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;
import java.util.logging.Level;

import Connection.ConnectionManager;
import DAO.Llibres;
import DAO.User;
import Model.LlibresCRUD;
import Model.UserCRUD;
import View.view;

public class Conntroler {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MongoClient db = ConnectionManager.getConnection();
		view View = new view(sc);

		UserCRUD user = new UserCRUD(db);
		LlibresCRUD bookCRUD = new LlibresCRUD(db);

		boolean exit = false;

		while (!exit) {
			View.mainMenu();
			int option = sc.nextInt();

			switch (option) {
			case 1: // Login
				if (View.login()) {
					boolean loggedIn = true;

					while (loggedIn) {
						View.secondMenu();
						int option2 = sc.nextInt();
						switch (option2) {
						case 1:
							Llibres llib = View.addNewBook();
							bookCRUD.addBook(llib);
							break;

						case 2:
							ArrayList<Llibres> llibres = bookCRUD.getAll();
							View.getAll(llibres);
							break;

						case 3:
							int dataInici = View.getYearFromUser("Enter start year: ");
							int dataFi = View.getYearFromUser("Enter end year: ");

							ArrayList<Llibres> booksByYear = bookCRUD.getBooksByDateRange(dataInici, dataFi);
							View.showBooks(booksByYear);
							break;

						case 4:
							loggedIn = false; // Solo sale del segundo menú y regresa al login
							break;

						default:
							System.err.println("Invalid option. Please try again.");
							break;
						}
					}
				} else {
					System.err.println("Login failed. Please try again.");
				}
				break;

			case 2: // Registro de usuario
				User newUser = View.register();
				if (newUser != null) {
					user.addUser(newUser);
				}
				break;

			case 3: // Salir del programa
				exit = true;
				break;

			default:
				System.err.println("Invalid option. Please try again.");
				break;
			}
		}

		sc.close();
	}
}
