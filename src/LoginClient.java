import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class LoginClient extends javax.swing.JFrame {

    /**
     * Creates new form LoginClient
     */
    public LoginClient() {
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textoLogin = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        textoSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        botaoLogin = new javax.swing.JButton();
        statusTexto = new javax.swing.JLabel();
        checkboxSenha = new javax.swing.JCheckBox();
        botaoLimpar = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        textoTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textoTitulo2 = new javax.swing.JLabel();
        textoTitulo3 = new javax.swing.JLabel();
        textoTitulo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        textoLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoLogin.setForeground(new java.awt.Color(0, 0, 0));
        textoLogin.setText("Login:");

        campoLogin.setBackground(new java.awt.Color(255, 255, 255));
        campoLogin.setForeground(new java.awt.Color(0, 0, 0));

        textoSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoSenha.setForeground(new java.awt.Color(0, 0, 0));
        textoSenha.setText("Senha:");

        campoSenha.setBackground(new java.awt.Color(255, 255, 255));
        campoSenha.setForeground(new java.awt.Color(0, 0, 0));

        botaoLogin.setBackground(new java.awt.Color(255, 204, 153));
        botaoLogin.setForeground(new java.awt.Color(0, 0, 0));
        botaoLogin.setText("Login");
        botaoLogin.setBorder(null);
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });

        statusTexto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusTexto.setForeground(new java.awt.Color(0, 0, 0));
        statusTexto.setText("Aguardando login...");

        checkboxSenha.setBackground(new java.awt.Color(255, 255, 255));
        checkboxSenha.setForeground(new java.awt.Color(0, 0, 0));
        checkboxSenha.setText("Mostrar senha");
        checkboxSenha.setBorder(null);
        checkboxSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxSenhaActionPerformed(evt);
            }
        });

        botaoLimpar.setBackground(new java.awt.Color(255, 204, 153));
        botaoLimpar.setForeground(new java.awt.Color(0, 0, 0));
        botaoLimpar.setText("Limpar");
        botaoLimpar.setBorder(null);
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoSair.setBackground(new java.awt.Color(255, 204, 153));
        botaoSair.setForeground(new java.awt.Color(0, 0, 0));
        botaoSair.setText("Sair");
        botaoSair.setBorder(null);
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });

        botaoCadastrar.setBackground(new java.awt.Color(255, 204, 153));
        botaoCadastrar.setForeground(new java.awt.Color(0, 0, 0));
        botaoCadastrar.setText("Cadastre-se");
        botaoCadastrar.setBorder(null);
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 204, 153));

        textoTitulo.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        textoTitulo.setForeground(new java.awt.Color(153, 51, 0));
        textoTitulo.setText("SISTEMA DE DELIVERY");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logo1.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("BurguerBOT");

        textoTitulo2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        textoTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        textoTitulo2.setText("All rights reserved for BurguerBOT FastFood Company LTDA");

        textoTitulo3.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        textoTitulo3.setForeground(new java.awt.Color(255, 255, 255));
        textoTitulo3.setText("TELA DE LOGIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoTitulo2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(textoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(textoTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(49, 49, 49)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textoTitulo2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addComponent(textoTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(463, Short.MAX_VALUE)))
        );

        textoTitulo1.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        textoTitulo1.setForeground(new java.awt.Color(0, 0, 0));
        textoTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoTitulo1.setText("BEM VINDO!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(statusTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textoSenha)
                                        .addComponent(textoLogin)
                                        .addComponent(campoSenha)
                                        .addComponent(campoLogin)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(checkboxSenha)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(14, 14, 14)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textoTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(textoTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(textoLogin)
                .addGap(41, 41, 41)
                .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(textoSenha)
                .addGap(45, 45, 45)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkboxSenha)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(statusTexto)
                .addContainerGap(68, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private FastFoodService connectToServerRMI() throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);
            return (FastFoodService) registry.lookup("FastFoodService");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Falha ao conectar-se ao servidor RMI.");
        }
    }

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {                                           


        
        String usuario = campoLogin.getText();
        String senha = String.valueOf(campoSenha.getPassword());
        try {
            FastFoodService fastFoodService = connectToServerRMI();
            boolean loginValido = fastFoodService.verificarLogin(usuario, senha);

            if (loginValido) {
                statusTexto.setText("Login realizado com sucesso...");

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ClientMain.createAndShowGUI();  // Chama o método createAndShowGUI da classe ClientMain
                    }
                });

                this.dispose();  // Fecha a tela de login
            } else {
                statusTexto.setText("Dados incorretos...");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            statusTexto.setText("Falha ao conectar-se ao servidor RMI.");
        }
    }

    private void checkboxSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxSenhaActionPerformed
        if(checkboxSenha.isSelected()){
            campoSenha.setEchoChar((char)0);
        }else{
            campoSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_checkboxSenhaActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        campoLogin.setText("");
        campoSenha.setText("");
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_botaoSairActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        CadastroClient cadastroClient = new CadastroClient();  // Cria uma instância da tela de cadastro
        cadastroClient.setVisible(true);  // Torna a tela de cadastro visível
        this.dispose();  // Fecha a tela de login
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JButton botaoSair;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JCheckBox checkboxSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel statusTexto;
    private javax.swing.JLabel textoLogin;
    private javax.swing.JLabel textoSenha;
    private javax.swing.JLabel textoTitulo;
    private javax.swing.JLabel textoTitulo1;
    private javax.swing.JLabel textoTitulo2;
    private javax.swing.JLabel textoTitulo3;
    // End of variables declaration//GEN-END:variables
}
