import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
    @Override
    public List<Conteudo> extraiConteudos(String json) {
        JsonParser jsonParser = new JsonParser();
        var listaDeAtributos = jsonParser.parse(json);
        var conteudos = new ArrayList<Conteudo>();
        for (Map<String, String> atributos : listaDeAtributos) {
            String title = atributos.get("title");
            String urlImagem = atributos.get("image");
            var conteudo = new Conteudo(title, urlImagem);
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
