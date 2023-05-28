import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    private static FastFoodService fastFoodService;
    private static boolean loggedIn;
    private static JTextArea itemListTextArea;
    private static List<String> selectedItems;
    private static double totalAmount;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);
            fastFoodService = (FastFoodService) registry.lookup("FastFoodService");

            JFrame frame = new JFrame("Fast Food - Sistema de Pedidos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new BorderLayout());

            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new GridLayout(3, 2));

            JLabel usernameLabel = new JLabel("Nome de usuário:");
            JTextField usernameField = new JTextField();

            JLabel passwordLabel = new JLabel("Senha:");
            JPasswordField passwordField = new JPasswordField();

            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    try {
                        loggedIn = fastFoodService.login(username, password);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (loggedIn) {
                        JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                        selectedItems = new ArrayList<>();
                        totalAmount = 0.0;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Falha no login. Verifique suas credenciais.");
                    }
                }
            });

            loginPanel.add(usernameLabel);
            loginPanel.add(usernameField);
            loginPanel.add(passwordLabel);
            loginPanel.add(passwordField);
            loginPanel.add(loginButton);

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new GridLayout(3, 2));

            JButton cheeseBurgerButton = createItemButton("Cheese Burger", 10.0);
            JButton xBurgerButton = createItemButton("X-Burger", 12.0);
            JButton xTudoButton = createItemButton("X-Tudo", 15.0);
            JButton xAlcatraButton = createItemButton("X-Alcatra", 13.0);
            JButton batataFritaButton = createItemButton("Batata Frita", 6.0);
            JButton xCamaraoButton = createItemButton("X-Camarão", 18.0);
            JButton xSaladaButton = createItemButton("X-Salada", 11.0);

            itemPanel.add(cheeseBurgerButton);
            itemPanel.add(xBurgerButton);
            itemPanel.add(xTudoButton);
            itemPanel.add(xAlcatraButton);
            itemPanel.add(batataFritaButton);
            itemPanel.add(xCamaraoButton);
            itemPanel.add(xSaladaButton);

            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            JButton checkoutButton = new JButton("Fechar Pedido");
            checkoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!loggedIn) {
                        JOptionPane.showMessageDialog(frame, "Faça login antes de fechar o pedido.");
                        return;
                    }

                    if (selectedItems.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Seu pedido está vazio. Adicione itens antes de fechar o pedido.");
                        return;
                    }

                    double total = 0.0;
                    try {
                        total = fastFoodService.checkout();
                        JOptionPane.showMessageDialog(frame, "Pedido fechado! Total a pagar: R$ " + total);
                        int option = JOptionPane.showConfirmDialog(frame, "Deseja fazer o pagamento?", "Pagamento", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            performPayment(total);
                        }
                        int exitOption = JOptionPane.showConfirmDialog(frame, "Deseja sair do aplicativo?", "Sair", JOptionPane.YES_NO_OPTION);
                        if (exitOption == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            clearSelection();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao fechar o pedido: " + ex.getMessage());
                    }
                }
            });

            controlPanel.add(checkoutButton);

            frame.add(loginPanel, BorderLayout.NORTH);
            frame.add(itemPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }

    private static JButton createItemButton(String itemName, double price) {
        JButton itemButton = new JButton(itemName + " - R$ " + price);
        itemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!loggedIn) {
                    JOptionPane.showMessageDialog(null, "Faça login antes de adicionar itens ao pedido.");
                    return;
                }

                try {
                    fastFoodService.addItem(itemName);
                    selectedItems.add(itemName);
                    totalAmount += price;
                    updateItemListTextArea();
                    JOptionPane.showMessageDialog(null, "Item adicionado ao pedido!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar o item: " + ex.getMessage());
                }
            }
        });
        return itemButton;
    }

    private static void performPayment(double totalAmount) {
        // Lógica para realizar o pagamento
        JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
    }

    private static void updateItemListTextArea() {
        StringBuilder itemList = new StringBuilder();
        for (String item : selectedItems) {
            itemList.append(item).append("\n");
        }
        itemList.append("\nTotal: R$ ").append(totalAmount);
        itemListTextArea.setText(itemList.toString());
    }

    private static void clearSelection() {
        selectedItems.clear();
        totalAmount = 0.0;
        updateItemListTextArea();
    }
}
