
package ar.com.indigo.web.cuadernosindigo.test;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class TestClient {
    
    public static void main(String[] args) throws Exception {
        String url = "";
        
        url = "http://localhost:8086/CuadernosBack/resources/cuadernos/v1/all";
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println(response.body());
    }
    
}
