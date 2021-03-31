
package ar.com.indigo.web.cuadernosindigo.repositories.rest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Service {
    
    public String response(String url){
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println("***********************************************");
            e.printStackTrace();
            System.out.println("***********************************************");
            return "";
        }
    }
    
}
