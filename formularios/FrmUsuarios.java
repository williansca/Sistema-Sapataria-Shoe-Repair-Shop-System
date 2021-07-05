/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

// 
import java.sql.*;
import dal.ModuloConexao;
import dal.UsuarioDao;
import javafx.beans.property.FloatProperty;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//a linha abaixo importa recursos da biblioteca para preencher a tabela 
import net.proteanit.sql.DbUtils;

/**
 *
 * @author shark3000
 */
public class FrmUsuarios extends javax.swing.JInternalFrame {
    
    private boolean newUser;

    //variáveis para conexão
    //usando a variável de conexão DAL
    Connection conexao = null;

    //criando variáveis especiais para conexão com o banco
    //PreparedStatement e ResulSet são framework do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    // método construtor abaixo
    public FrmUsuarios() {
        //a linha abaixo inicializa os componentes
        initComponents();

        //a linha abaixo chama o método conector que está dentro do ModuloConexao
        conexao = ModuloConexao.conector();
        
    }

    //o método abaixo salva o usuário novo
    private void salvar() {
        if (((txtUsuariosNome.getText().isEmpty()) || (txtUsuariosSobrenome.getText().isEmpty()) || (txtUsuariosTelefone.getText().isEmpty())
                || (txtUsuariosLogin.getText().isEmpty()) || (txtUsuariosSenha.getText().isEmpty())
                || (txtUsuariosRepetirSenha.getText().isEmpty()) || (cboUsuariosPerfil.getSelectedItem().toString().equals("Selecione um perfil")))) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            return;
        } //o código abaixo verifica se as senhas conferem
        if (!txtUsuariosSenha.getText().equals(txtUsuariosRepetirSenha.getText())) {
            JOptionPane.showMessageDialog(null, "Senhas não conferem");
            return;
        }
        
        if (newUser && UsuarioDao.isLoginAvailable(txtUsuariosLogin.getText())) {
            JOptionPane.showMessageDialog(null, "Esse usuário já existe, escolha outro usuário");
            return;
        }
        
        try {
            UsuarioDao.insertOrUpdate(txtUsuariosNome.getText(), txtUsuariosSobrenome.getText(), txtUsuariosTelefone.getText(), txtUsuariosLogin.getText(), txtUsuariosSenha.getText(), cboUsuariosPerfil.getSelectedItem().toString(), Float.parseFloat(txtUsuariosComissao.getText()), cboUsuariosStatus.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso");
            
            limpar();

            //o método abaixo desativa os campos
            desativar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    // o método abaixo é responsável pela exclusão ou desativação do usuário
    private void remover() {
        
        int confirm = JOptionPane.showConfirmDialog(null, "Usuários que tiverem sido utilizados serão somente desativados. Tem certeza que deseja excluir/desativar este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (!UsuarioDao.isUsedUser(Integer.parseInt(txtUsuariosCodigo.getText()))) {
                UsuarioDao.remover(Integer.parseInt(txtUsuariosCodigo.getText()));
            } else {
                UsuarioDao.inativarUsuario(Integer.parseInt(txtUsuariosCodigo.getText()));
            }
        }
        limpar();
        btnUsuariosAlterar.setEnabled(false);
        btnUsuariosDesativar.setEnabled(false);
        btnUsuariosAdicionar.setEnabled(true);
    }

    //método para pesquisar clientes pelo nome
    private void pesquisar_usuarios() {
        String sql = "select codigoUsuario as Código, nomeUsuario as Nome, sobrenomeUsuario as Sobrenome,telefoneUsuario as Telefone,loginUsuario as Usuário,perfilUsuario as Perfil,comissaoUsuario as Comissão,statusUsuario as Status from tbUsuarios where nomeUsuario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o "?"
            //continuação da string sql
            pst.setString(1, txtUsuariosPesquisar.getText() + "%");
            rs = pst.executeQuery();

            //para a tabela mostrar no momento da digitação selecionar evento key released e chamar o método la 
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // método para setar os campos do formulário com os conteúdos da tabela
    private void setar_campos() {

        //variável criada para pegar os campos da tabela
        int setar = tblUsuarios.getSelectedRow();

        //as linhas abaixo setam os campos de acordo com os conteúdos da tabela por cada row
        txtUsuariosCodigo.setText(tblUsuarios.getModel().getValueAt(setar, 0).toString());
        txtUsuariosNome.setText(tblUsuarios.getModel().getValueAt(setar, 1).toString());
        txtUsuariosSobrenome.setText(tblUsuarios.getModel().getValueAt(setar, 2).toString());
        txtUsuariosTelefone.setText(tblUsuarios.getModel().getValueAt(setar, 3).toString());
        txtUsuariosLogin.setText(tblUsuarios.getModel().getValueAt(setar, 4).toString());
        cboUsuariosPerfil.setSelectedItem(tblUsuarios.getModel().getValueAt(setar, 5));
        txtUsuariosComissao.setText(tblUsuarios.getModel().getValueAt(setar, 6).toString());
        cboUsuariosStatus.setSelectedItem(tblUsuarios.getModel().getValueAt(setar, 7).toString());
        
    }

    //método para limpar os campos do formulário
    private void limpar() {
        ((DefaultTableModel) tblUsuarios.getModel()).setRowCount(0);
        txtUsuariosPesquisar.setText(null);
        txtUsuariosCodigo.setText(null);
        txtUsuariosNome.setText(null);
        txtUsuariosSobrenome.setText(null);
        txtUsuariosTelefone.setText(null);
        txtUsuariosLogin.setText(null);
        txtUsuariosSenha.setText(null);
        txtUsuariosRepetirSenha.setText(null);
        cboUsuariosPerfil.setSelectedItem("Selecione um perfil");
        txtUsuariosPesquisar.setText(null);
        txtUsuariosComissao.setText(null);
        cboUsuariosStatus.setSelectedItem("Status do usuário");
    }
    
    private void desativar() {
        //as linhas abaixo ativam/desativam os campos
        btnUsuariosSalvar.setEnabled(false);
        btnUsuariosAdicionar.setEnabled(true);
        txtUsuariosNome.setEnabled(false);
        txtUsuariosSobrenome.setEnabled(false);
        txtUsuariosTelefone.setEnabled(false);
        txtUsuariosLogin.setEnabled(false);
        txtUsuariosSenha.setEnabled(false);
        txtUsuariosRepetirSenha.setEnabled(false);
        cboUsuariosPerfil.setEnabled(false);
        txtUsuariosPesquisar.setEnabled(true);
        txtUsuariosComissao.setEnabled(false);
    }
    
    private void cancelar() {
        limpar();
        btnUsuariosAdicionar.setEnabled(true);
        desativar();
        btnUsuariosCancelar.setEnabled(false);
        btnUsuariosAlterar.setEnabled(false);
        btnUsuariosDesativar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tblUsu = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtUsuariosPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuariosCodigo = new javax.swing.JTextField();
        txtUsuariosNome = new javax.swing.JTextField();
        txtUsuariosLogin = new javax.swing.JTextField();
        cboUsuariosPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnUsuId = new javax.swing.JLabel();
        txtUsuariosTelefone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnUsuariosDesativar = new javax.swing.JButton();
        btnUsuariosSalvar = new javax.swing.JButton();
        btnUsuariosAlterar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtUsuariosSenha = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtUsuariosRepetirSenha = new javax.swing.JPasswordField();
        txtUsuariosComissao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnUsuariosAdicionar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtUsuariosSobrenome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboUsuariosStatus = new javax.swing.JComboBox<>();
        btnUsuariosCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(1000, 740));

        tblUsuarios = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tblUsuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Sobrenome", "Telefone", "Usuário", "Perfil", "Comissão", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setFocusable(false);
        tblUsuarios.getTableHeader().setResizingAllowed(false);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        tblUsu.setViewportView(tblUsuarios);

        txtUsuariosPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuariosPesquisar.setToolTipText("Pesquisar Usuário");
        txtUsuariosPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuariosPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("* Campos obrigatórios");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("* Senha");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("* Perfil");

        txtUsuariosCodigo.setEnabled(false);
        txtUsuariosCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuariosCodigoActionPerformed(evt);
            }
        });

        txtUsuariosNome.setEnabled(false);

        txtUsuariosLogin.setEnabled(false);

        cboUsuariosPerfil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboUsuariosPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um perfil", "administrador", "usuário", "técnico" }));
        cboUsuariosPerfil.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Telefone");

        btnUsuId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUsuId.setText("* Código");

        txtUsuariosTelefone.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("* Nome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("* Usuário");

        btnUsuariosDesativar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUsuariosDesativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/041-download.png"))); // NOI18N
        btnUsuariosDesativar.setText("Excluir/Desativar");
        btnUsuariosDesativar.setToolTipText("Desativar Usuário");
        btnUsuariosDesativar.setEnabled(false);
        btnUsuariosDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosDesativarActionPerformed(evt);
            }
        });

        btnUsuariosSalvar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUsuariosSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/020-save.png"))); // NOI18N
        btnUsuariosSalvar.setText("Salvar");
        btnUsuariosSalvar.setToolTipText("Adicionar Usuário");
        btnUsuariosSalvar.setEnabled(false);
        btnUsuariosSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosSalvarActionPerformed(evt);
            }
        });

        btnUsuariosAlterar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUsuariosAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnUsuariosAlterar.setText("Alterar");
        btnUsuariosAlterar.setToolTipText("Editar Usuário");
        btnUsuariosAlterar.setEnabled(false);
        btnUsuariosAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosAlterarActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/034-tracking.png"))); // NOI18N

        txtUsuariosSenha.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("* Repetir Senha");

        txtUsuariosRepetirSenha.setEnabled(false);

        txtUsuariosComissao.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("* Comissão");

        btnUsuariosAdicionar.setBackground(new java.awt.Color(204, 204, 204));
        btnUsuariosAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/037-add.png"))); // NOI18N
        btnUsuariosAdicionar.setText("Adicionar");
        btnUsuariosAdicionar.setToolTipText("Inserir Cliente Novo");
        btnUsuariosAdicionar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnUsuariosAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosAdicionarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("* Sobrenome");

        txtUsuariosSobrenome.setEnabled(false);

        jLabel10.setText("Ex: 30% colocar 0.30");

        cboUsuariosStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboUsuariosStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status do usuário", "USUÁRIO ATIVO", "USUÁRIO INATIVO" }));
        cboUsuariosStatus.setEnabled(false);

        btnUsuariosCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUsuariosCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancelar.png"))); // NOI18N
        btnUsuariosCancelar.setText("Cancelar");
        btnUsuariosCancelar.setToolTipText("Cancelar");
        btnUsuariosCancelar.setEnabled(false);
        btnUsuariosCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(btnUsuId))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsuariosTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsuariosLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(txtUsuariosCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cboUsuariosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtUsuariosNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtUsuariosSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(txtUsuariosSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(318, 318, 318)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsuariosRepetirSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboUsuariosPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)
                                    .addComponent(txtUsuariosComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(97, 97, 97)))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUsuariosAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuariosSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuariosAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuariosDesativar)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuariosCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuariosPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsuariosAdicionar, btnUsuariosAlterar, btnUsuariosCancelar, btnUsuariosDesativar, btnUsuariosSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuariosDesativar)
                    .addComponent(btnUsuariosAlterar)
                    .addComponent(btnUsuariosSalvar)
                    .addComponent(btnUsuariosAdicionar)
                    .addComponent(btnUsuariosCancelar))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(txtUsuariosPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(tblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuariosCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuId)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUsuariosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuariosNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsuariosSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuariosTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtUsuariosLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboUsuariosPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuariosSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuariosRepetirSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuariosComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUsuariosAdicionar, btnUsuariosAlterar, btnUsuariosCancelar, btnUsuariosDesativar, btnUsuariosSalvar});

        cboUsuariosStatus.getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 975, 740);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuariosCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuariosCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuariosCodigoActionPerformed

    private void btnUsuariosAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosAdicionarActionPerformed
        // as linhas abaixo habilitam os campos após o usuário clicar no Adicionar Usuario
        this.newUser = true;
        txtUsuariosNome.setEnabled(true);
        txtUsuariosSobrenome.setEnabled(true);
        txtUsuariosTelefone.setEnabled(true);
        txtUsuariosLogin.setEnabled(true);
        txtUsuariosSenha.setEnabled(true);
        txtUsuariosRepetirSenha.setEnabled(true);
        cboUsuariosPerfil.setEnabled(true);
        txtUsuariosComissao.setEnabled(true);
        btnUsuariosAdicionar.setEnabled(false);
        btnUsuariosSalvar.setEnabled(true);
        txtUsuariosPesquisar.setEnabled(false);
        cboUsuariosStatus.setSelectedItem("USUÁRIO ATIVO");
        btnUsuariosCancelar.setEnabled(true);
        
        txtUsuariosComissao.setText("0.00");
        

    }//GEN-LAST:event_btnUsuariosAdicionarActionPerformed

    private void btnUsuariosSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosSalvarActionPerformed
        // chamando o método salvar
        salvar();
        btnUsuariosCancelar.setEnabled(false);
    }//GEN-LAST:event_btnUsuariosSalvarActionPerformed

    private void txtUsuariosPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuariosPesquisarKeyReleased
        // chamar o método pesquisar usuários
        pesquisar_usuarios();
    }//GEN-LAST:event_txtUsuariosPesquisarKeyReleased

    // evento que será usado para setar os campos da tblUsuarios (clicando na tabela)
    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        // chamar o método setar_campos
        btnUsuariosAdicionar.setEnabled(false);
        btnUsuariosAlterar.setEnabled(true);
        btnUsuariosDesativar.setEnabled(true);
        btnUsuariosCancelar.setEnabled(true);
        setar_campos();

    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnUsuariosAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosAlterarActionPerformed
        // o comando abaixo enable os campos
        this.newUser = false;
        txtUsuariosNome.setEnabled(true);
        txtUsuariosSobrenome.setEnabled(true);
        txtUsuariosTelefone.setEnabled(true);
        txtUsuariosLogin.setEnabled(false);
        txtUsuariosSenha.setEnabled(true);
        txtUsuariosRepetirSenha.setEnabled(true);
        cboUsuariosPerfil.setEnabled(true);
        txtUsuariosComissao.setEnabled(true);
        
        btnUsuariosAlterar.setEnabled(false);
        btnUsuariosSalvar.setEnabled(true);
    }//GEN-LAST:event_btnUsuariosAlterarActionPerformed

    private void btnUsuariosDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosDesativarActionPerformed
        //
        remover();
    }//GEN-LAST:event_btnUsuariosDesativarActionPerformed

    private void btnUsuariosCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosCancelarActionPerformed
        // 
        cancelar();
    }//GEN-LAST:event_btnUsuariosCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnUsuId;
    private javax.swing.JButton btnUsuariosAdicionar;
    private javax.swing.JButton btnUsuariosAlterar;
    private javax.swing.JButton btnUsuariosCancelar;
    private javax.swing.JButton btnUsuariosDesativar;
    private javax.swing.JButton btnUsuariosSalvar;
    private javax.swing.JComboBox<String> cboUsuariosPerfil;
    private javax.swing.JComboBox<String> cboUsuariosStatus;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane tblUsu;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtUsuariosCodigo;
    private javax.swing.JTextField txtUsuariosComissao;
    private javax.swing.JTextField txtUsuariosLogin;
    private javax.swing.JTextField txtUsuariosNome;
    private javax.swing.JTextField txtUsuariosPesquisar;
    private javax.swing.JPasswordField txtUsuariosRepetirSenha;
    private javax.swing.JPasswordField txtUsuariosSenha;
    private javax.swing.JTextField txtUsuariosSobrenome;
    private javax.swing.JTextField txtUsuariosTelefone;
    // End of variables declaration//GEN-END:variables
}
