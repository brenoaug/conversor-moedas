import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            ApiClient apiClient = new ApiClient();

            String jsonRespostaUsd = apiClient.conversorMoedas("USD", "BRL");
            System.out.println("Taxas (USD):");
            System.out.println(jsonRespostaUsd);

            System.out.println("---");

            String jsonRespostaBrl = apiClient.conversorMoedas("BRL", "USD");
            System.out.println("Taxas (BRL):");
            System.out.println(jsonRespostaBrl);

        } catch (IllegalStateException e) {
            System.err.println("Erro de configuração: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar dados da API: " + e.getMessage());
        }
    }
}