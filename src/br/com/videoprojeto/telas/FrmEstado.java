/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.videoprojeto.telas;

import br.com.videoprojeto.dal.ConectaBanco;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class FrmEstado extends javax.swing.JFrame {

    Connection conecta = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form FrmEstado
     */
    public FrmEstado() {
        initComponents();
        conecta = ConectaBanco.conexao();
    }

    public void adicionar() {
        String sql = "INSERT INTO estados(nome_estado, sigla_estado) VALUES (?, ?)";
        try {
           pst = conecta.prepareStatement(sql);
           pst.setString(1, txtNome.getText());
           pst.setString(2, txtSigla.getText());
            //Validação do campos
            if ((txtNome.getText().isEmpty() || txtSigla.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            }else{
                pst.executeUpdate();
                if(conecta != null){
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
                    limpaCampos();
                }else{
                     JOptionPane.showMessageDialog(null, "Erro ao realizar o cadastro.");
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void consultar(){
        String sql = "SELECT id_estado as id, nome_estado as nome, sigla_estado as sigla FROM estados WHERE lower(nome_estado) LIKE ?";
        try{
        pst = conecta.prepareStatement(sql);
        pst.setString(1, "%"+txtConsulta.getText()+"%");
        System.out.println(rs);
        rs = pst.executeQuery();
        tblEstado.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void alterar(){
        String sql = "UPDATE estados SET nome_estado = ?, sigla_estado = ? WHERE id_estado = ?::integer";
        try{
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSigla.getText());
            pst.setString(3, txtCodigo.getText());
            System.out.println(pst);
            if(txtNome.getText().isEmpty() || txtSigla.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }else{
                if(conecta != null){
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso.");
                    limpaCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao alterar os dados");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    public void apagar(){
        String sql = "DELETE FROM estados WHERE id_estado = ?::integer";
        try {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtCodigo.getText());
            System.out.println(pst);
            int confirma = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION){
                pst.executeUpdate();
                if(conecta != null){                    
                    JOptionPane.showMessageDialog(null, "Registro excluido com sucesso.");
                    limpaCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao excluir os dados");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setarCampos(){
        int setar = tblEstado.getSelectedRow();
        txtCodigo.setText(tblEstado.getModel().getValueAt(setar,0).toString());
        txtNome.setText(tblEstado.getModel().getValueAt(setar,1).toString());
        txtSigla.setText(tblEstado.getModel().getValueAt(setar,2).toString());
        //a linha abaixo desabilita o botão adicionar
        btnEstadoSalvar.setEnabled(false);
    }
    
    public void limpaCampos(){
        txtCodigo.setText(null);
        txtNome.setText(null);
        txtSigla.setText(null);
        txtConsulta.setText(null);
        ((DefaultTableModel)tblEstado.getModel()).setRowCount(0);
        btnEstadoSalvar.setEnabled(true);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtSigla = new javax.swing.JTextField();
        btnEstadoSalvar = new javax.swing.JButton();
        btnEstadoEditar = new javax.swing.JButton();
        btnEstadoExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtConsulta = new javax.swing.JTextField();
        txtLimpar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEstado = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Estados");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("* Nome:");

        jLabel4.setText("* Sigla:");

        txtNome.setEnabled(false);
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtSigla.setEnabled(false);

        btnEstadoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Save as.png"))); // NOI18N
        btnEstadoSalvar.setToolTipText("Salvar");
        btnEstadoSalvar.setEnabled(false);
        btnEstadoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoSalvarActionPerformed(evt);
            }
        });

        btnEstadoEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Edit page.png"))); // NOI18N
        btnEstadoEditar.setToolTipText("Alterar");
        btnEstadoEditar.setEnabled(false);
        btnEstadoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoEditarActionPerformed(evt);
            }
        });

        btnEstadoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Delete.png"))); // NOI18N
        btnEstadoExcluir.setToolTipText("Excluir");
        btnEstadoExcluir.setEnabled(false);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Exit.png"))); // NOI18N
        btnSair.setToolTipText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel5.setText("Id:");

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Pesquisa:");

        txtConsulta.setEnabled(false);
        txtConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtConsultaMouseClicked(evt);
            }
        });
        txtConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsultaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConsulta)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Clear.png"))); // NOI18N
        txtLimpar.setToolTipText("LImpas Campos");
        txtLimpar.setEnabled(false);
        txtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLimparActionPerformed(evt);
            }
        });

        tblEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "nome", "sigla"
            }
        ));
        tblEstado.setEnabled(false);
        tblEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstadoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblEstado);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/videoprojeto/icones/Add_32x32.png"))); // NOI18N
        btnAdicionar.setToolTipText("Inserir novo");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNome)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdicionar)
                                .addGap(18, 18, 18)
                                .addComponent(txtLimpar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEstadoSalvar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEstadoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEstadoExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(btnSair)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtNome)
                    .addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnEstadoEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addComponent(btnEstadoExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEstadoSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSair))
                            .addComponent(txtLimpar))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Formulário de Cadastro de Estados");

        jLabel6.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstadoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoSalvarActionPerformed
        adicionar();
    }//GEN-LAST:event_btnEstadoSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        int confirma = JOptionPane.showConfirmDialog(null, "Voce tem certeza que deseja sair da tela de cadastro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            System.exit(0);
        }        
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnEstadoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoEditarActionPerformed
        // Chama o método alterar():
        alterar();
    }//GEN-LAST:event_btnEstadoEditarActionPerformed

    private void tblEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstadoMouseClicked
        // TODO add your handling code here:
        setarCampos();
    }//GEN-LAST:event_tblEstadoMouseClicked

    private void txtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimparActionPerformed
        //Chama o método limparCampos()
        limpaCampos();
    }//GEN-LAST:event_txtLimparActionPerformed

    private void txtConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaKeyReleased
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_txtConsultaKeyReleased

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Habilita os campos
        txtNome.setEnabled(true);
        txtSigla.setEnabled(true);
        tblEstado.setEnabled(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtConsultaMouseClicked
        // TODO add your handling code here:
        txtConsulta.setEnabled(true);
    }//GEN-LAST:event_txtConsultaMouseClicked

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
            java.util.logging.Logger.getLogger(FrmEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEstadoEditar;
    private javax.swing.JButton btnEstadoExcluir;
    private javax.swing.JButton btnEstadoSalvar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tblEstado;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtConsulta;
    private javax.swing.JButton txtLimpar;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSigla;
    // End of variables declaration//GEN-END:variables
}
