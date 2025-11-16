import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String[] MOEDAS = {"EUR", "USD", "BRL", "JPY", "GBP", "ARS"};
    private static final String[] NOMES_MOEDAS = {"Euro", "Dólar Americano", "Real", "Iene", "Libra", "Peso Argentino"};
    private static final Scanner scanner = new Scanner(System.in);
    private static final ApiClient apiClient = new ApiClient();

    public static void main(String[] args) {
        try {
            boolean executar = true;
            while (executar) {
                exibirMenuPrincipal();
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        realizarConversao();
                        break;
                    case 2:
                        exibirComoFunciona();
                        break;
                    case 3:
                        executar = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (IllegalStateException e) {
            System.err.println("Erro de configuração: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== CONVERSOR DE MOEDAS ===");
        System.out.println("1 - Conversor de moedas");
        System.out.println("2 - Como Funciona");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirComoFunciona() {
        System.out.println("\nCOMO FUNCIONA:");
        System.out.println("1. Escolha a moeda de origem");
        System.out.println("2. Escolha a moeda de destino");
        System.out.println("3. Digite o valor a ser convertido");
        System.out.println("4. O sistema mostrará o resultado da conversão");
        System.out.println("5. Pode repetir o processo ou voltar ao menu principal");
    }

    private static void realizarConversao() throws IOException, InterruptedException {
        boolean continuar = true;
        while (continuar) {
            exibirMoedas();
            System.out.print("\nEscolha a moeda de origem (1-6): ");
            int moedaOrigem = scanner.nextInt() - 1;

            System.out.print("Escolha a moeda de destino (1-6): ");
            int moedaDestino = scanner.nextInt() - 1;

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double resultado = apiClient.conversorMoedas(MOEDAS[moedaOrigem], MOEDAS[moedaDestino], valor);
            System.out.printf("%.2f %s = %.2f %s%n",
                    valor, MOEDAS[moedaOrigem], resultado, MOEDAS[moedaDestino]);

            System.out.println("\n1 - Converter novo valor");
            System.out.println("2 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            continuar = scanner.nextInt() == 1;
        }
    }

    private static void exibirMoedas() {
        System.out.println("\nMOEDAS DISPONÍVEIS:");
        for (int i = 0; i < MOEDAS.length; i++) {
            System.out.printf("%d - %s (%s)%n", i + 1, NOMES_MOEDAS[i], MOEDAS[i]);
        }
    }
}