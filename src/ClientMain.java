import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ClientMain {
    private static List<String> selectedItems = new ArrayList<>();
    private static double totalAmount = 0.0;
    private static String clientName;

    public static void main(String[] args) {
        try {
            // Localizar o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            // Obter a referência do serviço remoto
            FastFoodService fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            // Login do cliente
            login(fastFoodService);

            // Loop principal do cliente
            boolean exitApp = false;
            while (!exitApp) {
                // Menu principal
                String[] options = {"Selecionar item", "Ver itens selecionados", "Fechar pedido", "Sair do aplicativo"};
                int choice = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Fast Food App", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        // Selecionar item
                        selectItem(fastFoodService);
                        break;
                    case 1:
                        // Ver itens selecionados
                        viewSelectedItems();
                        break;
                    case 2:
                        // Fechar pedido
                        closeOrder(fastFoodService);
                        break;
                    case 3:
                        // Sair do aplicativo
                        exitApp = true;
                        break;
                    default:
                        // Opção inválida
                        JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, selecione uma opção válida.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void login(FastFoodService fastFoodService) throws RemoteException {
        boolean validLogin = false;
        while (!validLogin) {
            String name = JOptionPane.showInputDialog("Digite seu nome de usuário:");
            String password = JOptionPane.showInputDialog("Digite sua senha:");

            try {
                validLogin = fastFoodService.login(name, password);
                if (!validLogin) {
                    JOptionPane.showMessageDialog(null, "Nome de usuário ou senha inválidos. Por favor, tente novamente.");
                } else {
                    clientName = name;
                }
            } catch (RemoteException e) {
                System.out.println("erro" + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro durante o login: " + e.getMessage());
            }
        }
    }

    private static void selectItem(FastFoodService fastFoodService) throws RemoteException {
        String[] options = {"Cheese Burger - R$ 10.00", "X-Burger - R$ 12.00", "X-Tudo - R$ 15.00", "X-Alcatra - R$ 14.00", "Batata Frita - R$ 8.00", "X-Camarão - R$ 18.00", "X-Salada - R$ 9.00"};
        int choice = JOptionPane.showOptionDialog(null, "Selecione um item:", "Fast Food App - Seleção de Item", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                selectedItems.add("Cheese Burger");
                totalAmount += fastFoodService.getPrice("Cheese Burger");
                break;
            case 1:
                selectedItems.add("X-Burger");
                totalAmount += fastFoodService.getPrice("X-Burger");
                break;
            case 2:
                selectedItems.add("X-Tudo");
                totalAmount += fastFoodService.getPrice("X-Tudo");
                break;
            case 3:
                selectedItems.add("X-Alcatra");
                totalAmount += fastFoodService.getPrice("X-Alcatra");
                break;
            case 4:
                selectedItems.add("Batata Frita");
                totalAmount += fastFoodService.getPrice("Batata Frita");
                break;
            case 5:
                selectedItems.add("X-Camarão");
                totalAmount += fastFoodService.getPrice("X-Camarão");
                break;
            case 6:
                selectedItems.add("X-Salada");
                totalAmount += fastFoodService.getPrice("X-Salada");
                break;
            default:
                // Opção inválida
                JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, selecione uma opção válida.");
                break;
        }
    }

    private static void viewSelectedItems() {
        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item selecionado.");
        } else {
            StringBuilder itemList = new StringBuilder();
            for (String item : selectedItems) {
                itemList.append(item).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Itens selecionados:\n" + itemList);
        }
    }

    private static void closeOrder(FastFoodService fastFoodService) throws RemoteException {
        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item selecionado. Por favor, selecione pelo menos um item antes de fechar o pedido.");
        } else {
            // Exibir o valor total e perguntar se o cliente deseja finalizar o pedido
            int confirm = JOptionPane.showConfirmDialog(null, "Valor total: R$ " + totalAmount + "\nDeseja finalizar o pedido?", "Confirmação de Pedido", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Realizar o pagamento
                    double amountPaid = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser pago:"));
                    double change = fastFoodService.processPayment(clientName, amountPaid);

                    // Exibir o troco
                   // JOptionPane.showMessageDialog(null, "Troco: R$ " + change);

                    // Reiniciar a lista de itens selecionados e o valor total
//                    selectedItems.clear();
//                    totalAmount = 0.0;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor pago inválido. Certifique-se de digitar um valor numérico válido.");
                } catch (RemoteException e) {
                    System.out.println("erro" + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Erro durante o processamento do pagamento: " + e.getMessage());
                }
            }
        }
    }
}
