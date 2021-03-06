/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import static formularios.Principal.desktop;

/**
 *
 * @author shark3000
 */
public class FrmPesquisaOrdemDeServico extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmPesquisaOrdemDeServico
     */
    public FrmPesquisaOrdemDeServico() {
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

        jButton1 = new javax.swing.JButton();
        btnPesquisaOsAdicionar = new javax.swing.JButton();
        btnPesquisaOsAlterar = new javax.swing.JButton();
        btnPesquisaOsCancelar = new javax.swing.JButton();
        btnPesquisaOsEntregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesquisaOs = new javax.swing.JTable();
        txtPesquisaOs = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pesquisa Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(1000, 740));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/034-tracking.png"))); // NOI18N

        btnPesquisaOsAdicionar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPesquisaOsAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/015-add.png"))); // NOI18N
        btnPesquisaOsAdicionar.setText("Adicionar Ordem de Serviço");
        btnPesquisaOsAdicionar.setToolTipText("Adicionar Ordem de Serviço");
        btnPesquisaOsAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaOsAdicionarActionPerformed(evt);
            }
        });

        btnPesquisaOsAlterar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPesquisaOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnPesquisaOsAlterar.setText("Alterar OS");
        btnPesquisaOsAlterar.setToolTipText("Alterar Cliente");

        btnPesquisaOsCancelar.setBackground(new java.awt.Color(204, 204, 204));
        btnPesquisaOsCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPesquisaOsCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancelar.png"))); // NOI18N
        btnPesquisaOsCancelar.setText("Cancelar");
        btnPesquisaOsCancelar.setToolTipText("Retornar a Página Anterior");
        btnPesquisaOsCancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPesquisaOsCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaOsCancelarActionPerformed(evt);
            }
        });

        btnPesquisaOsEntregar.setBackground(new java.awt.Color(204, 204, 204));
        btnPesquisaOsEntregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPesquisaOsEntregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/027-qualitycontrol.png"))); // NOI18N
        btnPesquisaOsEntregar.setText("Entregar Serviço");
        btnPesquisaOsEntregar.setToolTipText("Bloquear Cliente");
        btnPesquisaOsEntregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPesquisaOsEntregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaOsEntregarActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar pelo número da ordem de serviço");

        jLabel2.setText("Selecionar ordem de serviço");

        tblPesquisaOs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPesquisaOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Ordem de Serviço", "Nome", "Telefone", "Data", "Valor RECEBIDO", "Valor em ABERTO"
            }
        ));
        jScrollPane1.setViewportView(tblPesquisaOs);

        txtPesquisaOs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisaOs.setToolTipText("Pesquisar Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(491, 491, 491))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPesquisaOs, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPesquisaOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnPesquisaOsEntregar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnPesquisaOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnPesquisaOsCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(113, 113, 113))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPesquisaOsAlterar, btnPesquisaOsCancelar, btnPesquisaOsEntregar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisaOsCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaOsEntregar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaOsAdicionar)
                    .addComponent(btnPesquisaOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(txtPesquisaOs)))
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(290, 290, 290))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPesquisaOsAdicionar, btnPesquisaOsAlterar, btnPesquisaOsCancelar, btnPesquisaOsEntregar});

        setBounds(0, 0, 1000, 740);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisaOsCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaOsCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnPesquisaOsCancelarActionPerformed

    private void btnPesquisaOsEntregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaOsEntregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisaOsEntregarActionPerformed

    private void btnPesquisaOsAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaOsAdicionarActionPerformed
        // as linhas abaixo abre o form FrmOrdemDeServico
        FrmOrdemDeServico ordemServico = new FrmOrdemDeServico();
        ordemServico.setVisible(true);
        desktop.add(ordemServico);
        
        //a linha abaixo manda a tela para atrás da nova
        this.toBack();
        try {
            ordemServico.setSelected(true);
            ordemServico.setMaximizable(true);
            ordemServico.setMaximum(true);
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnPesquisaOsAdicionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisaOsAdicionar;
    private javax.swing.JButton btnPesquisaOsAlterar;
    private javax.swing.JButton btnPesquisaOsCancelar;
    private javax.swing.JButton btnPesquisaOsEntregar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPesquisaOs;
    private javax.swing.JTextField txtPesquisaOs;
    // End of variables declaration//GEN-END:variables
}
