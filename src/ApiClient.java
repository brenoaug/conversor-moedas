import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();

    private final String apiKey;

    public ApiClient() {
        this.apiKey = System.getenv("EXCHANGE_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Variável de ambiente EXCHANGE_API_KEY não definida");
        }
    }

    public String conversorMoedas(String moedaBase, String moedaFinal) throws IOException, InterruptedException {

        String baseUrl = "https://v6.exchangerate-api.com/v6/";
        String url = baseUrl + apiKey + "/pair/" + moedaBase + "/" +  moedaFinal;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .build();

        System.out.println("Enviando requisição para: " + url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Falha na requisição. Código: " + response.statusCode());
        }
    }
}

