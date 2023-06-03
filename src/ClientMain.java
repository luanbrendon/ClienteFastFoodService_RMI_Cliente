import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMain {
    private static FastFoodService fastFoodService;
    private static JFrame frame;
    private static JTextArea outputTextArea;

    public static void main(String[] args) {
        try {
            // Localizar o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            // Obter a referência do serviço remoto
            fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            // Configurar a interface gráfica
            SwingUtilities.invokeLater(() -> {
                createAndShowGUI();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowGUI() {
        // Configurar a janela
        frame = new JFrame("Fast Food App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // area de texto para exibir a saída
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botão para ver itens disponíveis
        JButton viewItemsButton = new JButton("Ver Itens Disponíveis");
        viewItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAvailableItems();
            }
        });
        buttonPanel.add(viewItemsButton);

        // Botão para selecionar item
        JButton selectItemButton = new JButton("Selecionar Item");
        selectItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectItem();
            }
        });
        buttonPanel.add(selectItemButton);

        // Botão para ver itens selecionados
        JButton viewSelectedItemsButton = new JButton("Ver Itens Selecionados");
        viewSelectedItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewSelectedItems();
            }
        });
        buttonPanel.add(viewSelectedItemsButton);

        // Botão para fechar pedido
        JButton closeOrderButton = new JButton("Fechar Pedido");
        closeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeOrder();
            }
        });
        buttonPanel.add(closeOrderButton);

        // Adicionar o painel de botões à janela
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Exibir a janela
        frame.setVisible(true);
    }

    private static void viewAvailableItems() {
        try {
            List<Produto> availableItems = fastFoodService.getItensDisponiveis();
            StringBuilder itemsText = new StringBuilder("Itens disponíveis:\n");
            for (int i = 0; i < availableItems.size(); i++) {
                Produto produto = availableItems.get(i);
                itemsText.append((i + 1)).append(". ").append(produto.getNome()).append(" - R$ ").append(produto.getPreco()).append("\n");
            }
            outputTextArea.setText(itemsText.toString());
        } catch (Exception e) {
            outputTextArea.setText("Erro ao obter itens disponíveis: " + e.getMessage());
        }
    }

    private static void selectItem() {
        try {
            List<Produto> availableItems = fastFoodService.getItensDisponiveis();
            String[] itemNames = availableItems.stream().map(Produto::getNome).toArray(String[]::new);
            String selectedItem = (String) JOptionPane.showInputDialog(frame, "Selecione um item:", "Selecionar Item", JOptionPane.QUESTION_MESSAGE, null, itemNames, itemNames[0]);

            if (selectedItem != null) {
                int selectedIndex = -1;
                for (int i = 0; i < itemNames.length; i++) {
                    if (itemNames[i].equals(selectedItem)) {
                        selectedIndex = i;
                        break;
                    }
                }
                if (selectedIndex != -1) {
                    Produto produtoEscolhido = availableItems.get(selectedIndex);
                    fastFoodService.selectItem(produtoEscolhido);
                    outputTextArea.setText("Item selecionado: " + produtoEscolhido.getNome());
                }
            }
        } catch (Exception e) {
            outputTextArea.setText("Erro ao selecionar item: " + e.getMessage());
        }
    }


    private static void viewSelectedItems() {
        try {
            List<Produto> selectedItems = fastFoodService.getSelectedItems();
            if (selectedItems.isEmpty()) {
                outputTextArea.setText("Nenhum item selecionado.");
            } else {
                StringBuilder itemsText = new StringBuilder("Itens selecionados:\n");
                for (Produto produto : selectedItems) {
                    itemsText.append(produto.getNome()).append(" - R$ ").append(produto.getPreco()).append("\n");
                }
                outputTextArea.setText(itemsText.toString());
            }
        } catch (Exception e) {
            outputTextArea.setText("Erro ao obter itens selecionados: " + e.getMessage());
        }
    }

    private static void closeOrder() {
        try {
            List<Produto> selectedItems = fastFoodService.getSelectedItems();
            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum item selecionado. Por favor, selecione pelo menos um item antes de fechar o pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                double totalAmount = fastFoodService.getTotalAmount();
                String amountPaidString = JOptionPane.showInputDialog(frame, "Total da compra: R$ " + totalAmount + "\nDigite o valor a ser pago:");

                if (amountPaidString != null && !amountPaidString.isEmpty()) {
                    try {
                        double amountPaid = Double.parseDouble(amountPaidString);
                        fastFoodService.pay(amountPaid); // Chamada do método pay() no serviço remoto
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "Valor pago inválido. Certifique-se de digitar um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao fechar o pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
