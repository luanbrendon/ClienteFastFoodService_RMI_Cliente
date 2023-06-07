import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;


public class ClientMain {
    private static List<Produto> selectedItems;
    private static double totalAmount;
    private static FastFoodService fastFoodService;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        try {
            // Localizar o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            // Obter a referência do serviço remoto
            fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            // Inicializar a lista de itens selecionados e o valor total
            selectedItems = new ArrayList<>();
            totalAmount = 0.0;

            // Criação da janela principal
            JFrame novaJanela = new JFrame("Fast Food App");
            novaJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            novaJanela.setSize(800, 600);
            novaJanela.setLocationRelativeTo(null); //centralizar a janela no centro

            //criação do painel principal
            JPanel mainPanel = new JPanel(new BorderLayout());

            //criação da lista de itens disponíveis
            List<Produto> itensDisponiveis = fastFoodService.getItensDisponiveis();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Produto produto : itensDisponiveis) {
                listModel.addElement(produto.getNome() + " - R$ " + produto.getPreco());
            }
            JList<String> itemList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(itemList);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // Criação dos componentes
            JButton selectItemButton = new JButton("Selecionar item");
            JButton viewSelectedItemsButton = new JButton("Ver itens selecionados");
            JButton closeOrderButton = new JButton("Fechar pedido");
            JButton exitButton = new JButton("Sair do aplicativo");

            // Configuração dos layouts
            JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
            buttonPanel.add(selectItemButton);
            buttonPanel.add(viewSelectedItemsButton);
            buttonPanel.add(closeOrderButton);
            buttonPanel.add(exitButton);
            mainPanel.add(buttonPanel, BorderLayout.EAST);

            // Ação do botão "Selecionar item"
            selectItemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectItem(itemList.getSelectedValue());
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

            //Adicionar o painel principal à janela
            novaJanela.add(mainPanel);

            // Exibição da janela
            novaJanela.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectItem(String selectedItem) {
        if (selectedItem != null) {
            try {
                // Obter os itens disponíveis do serviço remoto
                List<Produto> itensDisponiveis = fastFoodService.getItensDisponiveis();
                for (Produto produto : itensDisponiveis) {
                    if ((produto.getNome() + " - R$ " + produto.getPreco()).equals(selectedItem)) {
                        fastFoodService.selectItem(produto);
                        selectedItems.add(produto);
                        totalAmount += produto.getPreco();
                        JOptionPane.showMessageDialog(null, "Item selecionado: " + produto.getNome(), "Fast Food App - Item Selecionado", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                try {
                    fastFoodService.pay(amountPaid); // chamada do metodo remoto pay
                    double change = amountPaid - totalAmount;
                    JOptionPane.showMessageDialog(null, "Troco: R$ " + change + "\nObrigado pela compra!", "Fast Food App - Fechar Pedido", JOptionPane.INFORMATION_MESSAGE);
                    selectedItems.clear();
                    totalAmount = 0.0;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Valor insuficiente. Por favor, pague o valor total da compra.", "Fast Food App - Fechar Pedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}