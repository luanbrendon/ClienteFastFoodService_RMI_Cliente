import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            //Localizar o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            //obter a referência do serviço remoto
            FastFoodService fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            // Loop principal do aplicativo
            boolean exitApp = false;
            while (!exitApp) {
                // Exibir o menu principal
                System.out.println("=== Fast Food App ===");
                System.out.println("Selecione uma opção:");
                System.out.println("1. Ver itens disponíveis");
                System.out.println("2. Selecionar item");
                System.out.println("3. Ver itens selecionados");
                System.out.println("4. Fechar pedido");
                System.out.println("5. Sair do aplicativo");

                // Ler a opção escolhida pelo usuário
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Ver itens disponíveis
                        List<Produto> itensDisponiveis = fastFoodService.getItensDisponiveis();
                        System.out.println("Itens disponíveis:");
                        for (int i = 0; i < itensDisponiveis.size(); i++) {
                            Produto produto = itensDisponiveis.get(i);
                            System.out.println((i + 1) + ". " + produto.getNome() + " - R$ " + produto.getPreco());
                        }
                        break;
                    case 2:
                        // Selecionar item
                        List<Produto> itensDisponiveis2 = fastFoodService.getItensDisponiveis();
                        System.out.println("Selecione um item:");
                        for (int i = 0; i < itensDisponiveis2.size(); i++) {
                            Produto produto = itensDisponiveis2.get(i);
                            System.out.println((i + 1) + ". " + produto.getNome() + " - R$ " + produto.getPreco());
                        }
                        int itemChoice = scanner.nextInt();
                        Produto produtoEscolhido = itensDisponiveis2.get(itemChoice - 1);
                        fastFoodService.selectItem(produtoEscolhido);
                        break;
                    case 3:
                        // Ver itens selecionados
                        List<Produto> selectedItems = fastFoodService.getSelectedItems();
                        if (selectedItems.isEmpty()) {
                            System.out.println("Nenhum item selecionado.");
                        } else {
                            System.out.println("Itens selecionados:");
                            for (Produto produto : selectedItems) {
                                System.out.println(produto.getNome() + " - R$ " + produto.getPreco());
                            }
                        }
                        break;
                    case 4:
                        // Fechar pedido
                        List<Produto> selectedItems2 = fastFoodService.getSelectedItems();
                        if (selectedItems2.isEmpty()) {
                            System.out.println("Nenhum item selecionado. Por favor, selecione pelo menos um item antes de fechar o pedido.");
                        } else {
                            double totalAmount = fastFoodService.getTotalAmount();
                            System.out.println("Total da compra: R$ " + totalAmount);
                            System.out.println("Digite o valor a ser pago:");
                            double amountPaid = scanner.nextDouble();
                            fastFoodService.pay(amountPaid);
                        }
                        break;
                    case 5:
                        // Sair do aplicativo
                        exitApp = true;
                        break;
                    default:
                        // Opção inválida
                        System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
