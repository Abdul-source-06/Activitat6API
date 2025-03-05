package Model;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DAO.User;

public class UserCRUD {

	private static MongoDatabase database;

	public UserCRUD(MongoClient mongoClient) {
		this.database = mongoClient.getDatabase("LlibCat");
	}
	
	public void addUser(User user) {
		
		MongoCollection<Document> collection = database.getCollection("Usuaris");
		
		// Crear documento con los datos del usuario
		Document newUser = new Document();
		
	    newUser.append("name", user.getName());
	    newUser.append("gmail", user.getGmail());
	    newUser.append("password", user.getPaswd());
		
		// Añadir el usuario en la colección
		
		collection.insertOne(newUser);
	
		
	}
	 
	 public static boolean checkLogin(String gmail, String password) {
		    MongoCollection<Document> collection = database.getCollection("Usuaris");

		    // Buscar un usuario con el correo electrónico y contraseña
		    Document query = new Document()
		            .append("gmail", gmail)
		            .append("password", password);

		    Document user = collection.find(query).first();

		    // Si se encuentra un usuario, devuelve true (login exitoso), de lo contrario, false
		    return user != null;
		}	
	 

}