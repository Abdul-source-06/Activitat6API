package Connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ConnectionManager {
    
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String URI = "mongodb+srv://Abdul:abdul1234@cluster1.8tcez.mongodb.net/LlibCat?retryWrites=true&w=majority&appName=Cluster1";
    private static MongoClient mongoClient = null;

    public static MongoClient getConnection() {
        if (mongoClient == null) { 
            try {
                LOGGER.info("Intentando conectar a MongoDB...");
                mongoClient = MongoClients.create(URI);
                LOGGER.info("Conexión establecida correctamente.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al conectar con MongoDB", e);
            }
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return getConnection().getDatabase("LlibCat");
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            LOGGER.info("Cerrando conexión con MongoDB...");
            mongoClient.close();
            mongoClient = null;
            LOGGER.info("Conexión cerrada.");
        }
    }
}
