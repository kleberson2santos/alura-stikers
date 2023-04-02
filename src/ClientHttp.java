import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {
    public String buscaDados(String url){

        try {
            URI uri = URI.create(url);
            var httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
            HttpResponse<String> httpResponse = httpClient.send(request, responseBodyHandler);
            return httpResponse.body();
        } catch (IOException | InterruptedException ex)  {
            throw new RuntimeException(ex);
        }

    }
}
