package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import DAO.Llibres;

public class LlibresCRUD {

	private MongoDatabase database;

	public LlibresCRUD(MongoClient mongoClient) {
		this.database = mongoClient.getDatabase("LlibCat");
	}

	public void addBook(Llibres llibre) {

		MongoCollection<Document> collection = database.getCollection("Llibres");

		// Crear documento con los datos del usuario
		Document newBook = new Document();

		newBook.append("title", llibre.getTitol());
		newBook.append("autor", llibre.getAutor());
		newBook.append("anyPublicacio", llibre.getAny_Publicacio());
		newBook.append("descriptio", llibre.getDescripcio());
		newBook.append("category", llibre.getCategories());

		// Añadir el usuario en la colección

		collection.insertOne(newBook);

	}

	public ArrayList<Llibres> getAll() {

		ArrayList<Llibres> allBooks = new ArrayList<Llibres>();
		MongoCollection<Document> collection = database.getCollection("Llibres");
		for(Document dc : collection.find()) {
			Llibres l = new Llibres();
			l.setTitol(dc.getString("titol"));
			l.setAutor(dc.getString("autor"));
			l.setAny_Publicacio(dc.getInteger("anyPublicacio"));
			l.setDescripcio(dc.getString("descripcio"));
			l.setCategories(dc.getList("categories", String.class));
			
			allBooks.add(l);
		}
		return allBooks;
	}
	
	 public ArrayList<Llibres> getBooksByDateRange(int dataInici, int dataFi) {
	        ArrayList<Llibres> booksByDateRange = new ArrayList<>();
	        MongoCollection<Document> collection = database.getCollection("Llibres");

	        // Filtro para encontrar libros entre dataInici y dataFi
	        Bson filter = Filters.and(
	            Filters.gte("anyPublicacio", dataInici),
	            Filters.lte("anyPublicacio", dataFi)
	        );

	        for (Document dc : collection.find(filter)) {
	            Llibres l = new Llibres();
	            l.setTitol(dc.getString("titol"));
	            l.setAutor(dc.getString("autor"));
	            l.setAny_Publicacio(dc.getInteger("anyPublicacio"));
	            l.setDescripcio(dc.getString("descripcio"));
	            l.setCategories(dc.getList("categories", String.class));

	            booksByDateRange.add(l);
	        }

	        return booksByDateRange;
	    }


}
