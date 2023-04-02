import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

    @Override
    public List<Conteudo> extraiConteudos(String json) {
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributos : listaDeAtributos) {
            String title = atributos.get("title");
            String urlImagem = atributos.get("url");
            var conteudo = new Conteudo(title, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
