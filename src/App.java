import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
//        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();

//        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        String url = "http://localhost:8080/linguagens";
//        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClientHttp();
        String json = http.buscaDados(url);

        List<Conteudo> listaDeConteudos = extrator.extraiConteudos(json);

        for (Conteudo conteudo : listaDeConteudos) {
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
            String fileName = conteudo.getTitulo().concat(".png");
            geradora.cria(inputStream, fileName);

            System.out.printf("Titulo: \u001b[1m\u001b[30m\u001b[43m%s\u001b[m\n",conteudo.getTitulo());
//            System.out.print("Classificação: ");
//            int rating = Double.valueOf(conteudo.get("imDbRating")).intValue();
//            for (int i = 0; i < rating; i++) {
//                System.out.print("⭐");
//            }
            System.out.println();
        }

    }
}