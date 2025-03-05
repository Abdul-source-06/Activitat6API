package Controller;

import Model.LlibresCRUD;
import DAO.Llibres;
import Connection.ConnectionManager;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Scanner;
import View.view;

public class Conntroler {

	public static void main(String[] args) {
		HttpClient clientHttp = ConnectionManager.getHttpClient();
		LlibresCRUD llibre = new LlibresCRUD(clientHttp); // CRUD de libros
		Scanner sc = new Scanner(System.in);
		view View = new view(sc);

		boolean exit = false;

		while (!exit) {

			View.secondMenu();
			int option = sc.nextInt();
			sc.nextLine();

			switch (option) {
				case 1:
					// Añadir un nuevo libro
					Llibres newLlibre = View.addNewBook();
					LlibresCRUD.addBook(newLlibre);
					break;

				case 2:
					// Listar todos los libros
					ArrayList<Llibres> llibres = LlibresCRUD.listAll();
					View.showBooks(llibres);
					break;

				case 3:
					// Buscar libros por fecha
					int startYear = View.getYearFromUser("Enter start year: ");
					int endYear = View.getYearFromUser("Enter end year: ");
					ArrayList<Llibres> booksByYear = LlibresCRUD.listByDateRange(startYear, endYear);
					View.showBooks(booksByYear);
					break;

				case 4:
					// Opción para salir del programa
					System.out.println("Do you want to exit? (y/n)");
					String exitOption = sc.nextLine();
					if (exitOption.equalsIgnoreCase("y")) {
						exit = true;
					}
					break;

				default:
					System.err.println("Invalid option. Please try again.");
					break;
			}
		}

		sc.close();
	}
}
