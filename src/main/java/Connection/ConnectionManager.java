package Connection;

import java.net.http.HttpClient;
public class ConnectionManager {


    public static HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}