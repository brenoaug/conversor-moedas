import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
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
        System.out.print("""
                === CONVERSOR DE MOEDAS ===
                
                1 - Conversor de moedas
                2 - Como Funciona
                3 - Sair
                
                Escolha uma opção:""");
    }

    private static void exibirComoFunciona() {
        System.out.println("""
                COMO FUNCIONA:
                
                1. Escolha a moeda de origem
                2. Escolha a moeda de destino
                3. Digite o valor a ser convertido
                4. O sistema mostrará o resultado da conversão
                5. Pode repetir o processo ou voltar ao menu principal
                """);
    }

    private static void realizarConversao() throws IOException, InterruptedException, IllegalArgumentException {
        boolean continuar = true;
        while (continuar) {
            exibirMoedas();
            System.out.print("\nEscolha a moeda de origem (1-6): ");
            int moedaOrigem = scanner.nextInt() - 1;
            try {
                if (moedaOrigem < 0 || moedaOrigem > 5) {
                    throw new IllegalArgumentException("Opção inválida! Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("Escolha a moeda de destino (1-6): ");
            int moedaDestino = scanner.nextInt() - 1;
            try {
                if (moedaDestino < 0 || moedaDestino > 5) {
                    throw new IllegalArgumentException("Opção inválida! Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor;
            while (true) {
                try {
                    String input = scanner.next();
                    if (!input.matches("^\\d*[.,]?\\d{0,2}$")) {
                        throw new IllegalArgumentException("Por favor, digite um número com no máximo duas casas decimais.");
                    }
                    valor = new DecimalFormat().parse(input).doubleValue();
                    break;
                } catch (IllegalArgumentException | ParseException e) {
                    System.out.println(e.getMessage());
                    System.out.print("Digite o valor novamente: ");
                }
            }


            double resultado = apiClient.conversorMoedas(MOEDAS[moedaOrigem], MOEDAS[moedaDestino], valor);
            System.out.printf("%.2f %s = %.2f %s%n",
                    valor, MOEDAS[moedaOrigem], resultado, MOEDAS[moedaDestino]);

            System.out.println("\n1 - Converter novo valor");
            System.out.println("2 - Voltar ao menu principal\n");
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