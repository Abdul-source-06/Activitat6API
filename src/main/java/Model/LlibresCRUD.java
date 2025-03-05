package Model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import DAO.Llibres;

public class LlibresCRUD {

	private final HttpClient client;
	private static final String BASE_URL = "https://llibcat-zeta.vercel.app";

	public LlibresCRUD(HttpClient clientHttp) {
		this.client = clientHttp;
	}

	// Función para agregar un libro (POST /add)
	public static void addBook(Llibres llibre) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + "/add"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(llibre.toJson()))  // Convertimos el libro a JSON
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Función para obtener todos los libros (GET /list)
	public static ArrayList<Llibres> listAll() {
		ArrayList<Llibres> llibresList = new ArrayList<>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(BASE_URL + "/list"))  // Endpoint para obtener todos los libros
					.GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String responseBody = response.body();

			System.out.println("Response Body: " + responseBody);
			// Parseamos la respuesta JSON
			if (responseBody.trim().startsWith("[")) {
				JSONArray jsonArray = new JSONArray(responseBody);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Llibres llibre = new Llibres();
					llibre.setAutor(jsonObject.getString("autor"));
					llibre.setTitol(jsonObject.getString("titol"));
					llibre.setAny_Publicacio(jsonObject.getInt("anyPublicacio"));
					llibre.setDescripcio(jsonObject.getString("descripcio"));
					JSONArray categoriesArray = jsonObject.getJSONArray("categories");

					// Convertimos JSONArray a List<String>
					List<String> categories = new ArrayList<>();
					for (int j = 0; j < categoriesArray.length(); j++) {
						categories.add(categoriesArray.getString(j));
					}
					llibre.setCategories(categories);
					llibresList.add(llibre);
				}
			}else {
				System.err.println("La respuesta no es un JSONArray. Respuesta: " + responseBody);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return llibresList;
	}

	// Función para obtener libros entre un rango de fechas (GET /list/año/año)
	public static ArrayList<Llibres> listByDateRange(int dataInici, int dataFi) {
		ArrayList<Llibres> llibresList = new ArrayList<>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			String uri = String.format(BASE_URL + "/list/%d/%d", dataInici, dataFi);  // Endpoint con fechas

			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(uri))
					.GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String responseBody = response.body();
			// Parseamos la respuesta JSON
			JSONArray jsonArray = new JSONArray(responseBody);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Llibres llibre = new Llibres();
				llibre.setAutor(jsonObject.getString("autor"));
				llibre.setTitol(jsonObject.getString("titol"));
				llibre.setAny_Publicacio(jsonObject.getInt("anyPublicacio"));
				llibre.setDescripcio(jsonObject.getString("descripcio"));
				JSONArray categoriesArray = jsonObject.getJSONArray("categories");

				// Convertimos JSONArray a List<String>
				List<String> categories = new ArrayList<>();
				for (int j = 0; j < categoriesArray.length(); j++) {
					categories.add(categoriesArray.getString(j));
				}
				llibre.setCategories(categories);
				llibresList.add(llibre);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return llibresList;
	}
}
