package Model;

import DAO.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserCRUD {

	private static final String BASE_URL = "http://localhost:3030";

	// Método para añadir un usuario
	public static void addUser(User user) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + "/addUser"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(user.toJson()))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Respuesta del servidor: " + response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Método para verificar el login
	public static boolean checkLogin(String gmail, String password) {
		HttpClient client = HttpClient.newHttpClient();
		String uri = String.format(BASE_URL + "/checkLogin?gmail=%s&password=%s", gmail, password);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonResponse = new JSONObject(response.body());
			return jsonResponse.getBoolean("success");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
}
