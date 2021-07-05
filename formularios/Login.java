/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.sql.*;
import dal.ModuloConexao;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author shark3000
 */
public class Login extends javax.swing.JFrame {

    //variáveis para conexão
    //usando a variável de conexão DAL
    Connection conexao = null;
    //criando variáveis especiais para conexão com o banco
    //PreparedStatement e ResulSet são framework do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {

        String sql = "select * from tbusuarios where loginUsuario=? and senhaUsuario=?";
        try {
            //as linhas abaixo preparam a consulta ao banco em função 
            //do que foi digitado nas caixas de texto
            //o "?" é substituido pelos conteúdos das variáveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLoginUsuario.getText());

            //modo seguro de capturar senhas
            String captura = new String(txtLoginSenha.getPassword());
            pst.setString(2, captura);

            //a linha abaixo executa a query
            rs = pst.executeQuery();

            //se existir usuario e senha abre a tela principal
            if (rs.next()) {
                //a linha abaixo obtem o conteúdo do campo perfil da tabela tbusuarios
                String perfil = rs.getString(7);
                //a linha abaixo obtem o conteúdo do campo status da tabela tbusuarios
                String status = rs.getString(9);

                if (status.equals("USUÁRIO INATIVO")) {
                    JOptionPane.showMessageDialog(null, "Acesso bloqueado");
                    conexao.close();
                    this.dispose();
                } //System.out.println(perfil);
                //a estrutura abaixo faz o tratamento do perfil do usuário
                else if (perfil.equals("administrador")) {

                    //a linha abaixo exibe a tela Principal
                    Principal principal = new Principal();
                    principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    principal.setVisible(true);

                    //as linhas abaixo fazem as telas ficarem enabled
                    Principal.btnPrincipalRelatorios.setEnabled(true);
                    Principal.btnPrincipalUsuarios.setEnabled(true);

                    //a linha abaixo set a lblPrincipalUsuario com o nome do usuário
                    Principal.lblPrincipalUsuario.setText(rs.getString(2));
                    //a linha abaixo fecha a tela de login
                    this.dispose();
                    //a linha abaixo fecha a conexão com o banco
                    conexao.close();
                } else {
                    Principal principal = new Principal();
                    principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    principal.setVisible(true);

                    //a linha abaixo set a lblPrincipalUsuario com o nome do usuário
                    Principal.lblPrincipalUsuario.setText(rs.getString(2));

                    this.dispose();
                    conexao.close();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();

        //chama o método conector criado na classe dal
        conexao = ModuloConexao.conector();
        //a linha abaixo serve de apoio ao status da conexão
        //System.out.println(conexao);
        if (conexao != null) {
            lblLoginStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/027-qualitycontrol.png")));
        } else {
            lblLoginStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/028-processing.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtLoginUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLoginSenha = new javax.swing.JPasswordField();
        lblLoginStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Sapataria");
        setPreferredSize(new java.awt.Dimension(480, 200));
        setResizable(false);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loginimage.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("Usuário");

        txtLoginUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Senha");

        lblLoginStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/027-qualitycontrol.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblLoginStatus)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntrar)
                        .addGap(37, 37, 37)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtLoginUsuario)
                        .addComponent(txtLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblLoginStatus))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEntrar)
                                    .addComponent(btnSair))
                                .addGap(7, 7, 7))))
                    .addComponent(jLabel3))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(496, 227));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        //chamando o método logar
        logar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtLoginUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblLoginStatus;
    private javax.swing.JPasswordField txtLoginSenha;
    private javax.swing.JTextField txtLoginUsuario;
    // End of variables declaration//GEN-END:variables
}
