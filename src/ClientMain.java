import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    private static List<Produto> selectedItems;
    private static double totalAmount;
    private static FastFoodService fastFoodService;

    public static void main(String[] args) {
        try {
            // Localizar o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            // Obter a referência do serviço remoto
            fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            // Inicializar a lista de itens selecionados e o valor total
            selectedItems = new ArrayList<>();
            totalAmount = 0.0;

            // Criação da janela principal
            JFrame frame = new JFrame("Fast Food App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Criação dos componentes
            JButton selectItemButton = new JButton("Selecionar item");
            JButton viewSelectedItemsButton = new JButton("Ver itens selecionados");
            JButton closeOrderButton = new JButton("Fechar pedido");
            JButton exitButton = new JButton("Sair do aplicativo");

            // Configuração dos layouts
            frame.setLayout(new GridLayout(4, 1));
            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel1.add(selectItemButton);
            panel2.add(viewSelectedItemsButton);
            panel3.add(closeOrderButton);
            panel4.add(exitButton);
            frame.add(panel1);
            frame.add(panel2);
            frame.add(panel3);
            frame.add(panel4);

            // Ação do botão "Selecionar item"
            selectItemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectItem();
                }
            });

            // Ação do botão "Ver itens selecionados"
            viewSelectedItemsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewSelectedItems();
                }
            });

            // Ação do botão "Fechar pedido"
            closeOrderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeOrder();
                }
            });

            // Ação do botão "Sair do aplicativo"
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // Exibição da janela
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectItem() {
        try {
            // Obter os itens disponíveis do serviço remoto
            List<Produto> itensDisponiveis = fastFoodService.getItensDisponiveis();

            // Exibir uma caixa de diálogo com os itens disponíveis para seleção
            String[] options = new String[itensDisponiveis.size()];
            for (int i = 0; i < itensDisponiveis.size(); i++) {
                Produto produto = itensDisponiveis.get(i);
                options[i] = produto.getNome() + " - R$ " + produto.getPreco();
            }

            int choice = JOptionPane.showOptionDialog(null, "Selecione um item:", "Fast Food App - Seleção de Item", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choice != JOptionPane.CLOSED_OPTION) {
                Produto produtoEscolhido = itensDisponiveis.get(choice);
                fastFoodService.selectItem(produtoEscolhido);
                selectedItems.add(produtoEscolhido);
                totalAmount += produtoEscolhido.getPreco();
                JOptionPane.showMessageDialog(null, "Item selecionado: " + produtoEscolhido.getNome(), "Fast Food App - Item Selecionado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewSelectedItems() {
        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item selecionado.", "Fast Food App - Itens Selecionados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Itens selecionados:\n");
            for (Produto produto : selectedItems) {
                sb.append(produto.getNome()).append(" - R$ ").append(produto.getPreco()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Fast Food App - Itens Selecionados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void closeOrder() {
        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item selecionado. Por favor, selecione pelo menos um item antes de fechar o pedido.", "Fast Food App - Fechar Pedido", JOptionPane.WARNING_MESSAGE);
        } else {
            double amountPaid = 0.0;
            String input = JOptionPane.showInputDialog(null, "Total da compra: R$ " + totalAmount + "\nDigite o valor a ser pago:", "Fast Food App - Fechar Pedido", JOptionPane.QUESTION_MESSAGE);
            try {
                amountPaid = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um valor numérico.", "Fast Food App - Fechar Pedido", JOptionPane.ERROR_MESSAGE);
            }

            if (amountPaid >= totalAmount) {
                double change = amountPaid - totalAmount;
                JOptionPane.showMessageDialog(null, "Troco: R$ " + change + "\nObrigado pela compra!", "Fast Food App - Fechar Pedido", JOptionPane.INFORMATION_MESSAGE);
                selectedItems.clear();
                totalAmount = 0.0;
            } else {
                JOptionPane.showMessageDialog(null, "Valor insuficiente. Por favor, pague o valor total da compra.", "Fast Food App - Fechar Pedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
