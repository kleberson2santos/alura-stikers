import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);

        var httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();

        HttpResponse<String> httpResponse = httpClient.send(request, responseBodyHandler);

        System.out.println(httpResponse);
        String body = httpResponse.body();
        System.out.println( body);

        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.printf("Filme: \u001b[1m\u001b[30m\u001b[43m%s\u001b[m\n",filme.get("title"));
            System.out.printf("Capa: ");
            System.out.println(filme.get("image"));
            System.out.printf("Classificação: ");
            int rating = Double.valueOf(filme.get("imDbRating")).intValue();
            for (int i = 0; i < rating; i++) {
                System.out.printf("\u2B50");
            }
            System.out.printf("\n");
        }
    }
}