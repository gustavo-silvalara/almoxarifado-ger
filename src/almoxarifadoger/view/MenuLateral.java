/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.view;

import almoxarifadoger.AlmoxarifadoGER;
import almoxarifadoger.view.crud.CrudAlmoxarifado;
import almoxarifadoger.view.crud.CrudResponsavel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 *
 * @author gustavo
 */
public class MenuLateral extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public MenuLateral() {
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

        btnCrudItem = new javax.swing.JLabel();
        btnMovItem = new javax.swing.JLabel();
        btnCrudResp = new javax.swing.JLabel();
        btnCrudAlmox = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(255, 255, 255));

        btnCrudItem.setBackground(new java.awt.Color(153, 153, 153));
        btnCrudItem.setForeground(new java.awt.Color(255, 255, 255));
        btnCrudItem.setText(" Cadastrar Item");
        btnCrudItem.setOpaque(true);
        btnCrudItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrudItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrudItemMouseExited(evt);
            }
        });

        btnMovItem.setBackground(new java.awt.Color(153, 153, 153));
        btnMovItem.setForeground(new java.awt.Color(255, 255, 255));
        btnMovItem.setText(" Movimentar Item");
        btnMovItem.setOpaque(true);
        btnMovItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMovItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMovItemMouseExited(evt);
            }
        });

        btnCrudResp.setBackground(new java.awt.Color(153, 153, 153));
        btnCrudResp.setForeground(new java.awt.Color(255, 255, 255));
        btnCrudResp.setText(" Cadastrar Responsável");
        btnCrudResp.setOpaque(true);
        btnCrudResp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrudRespMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrudRespMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrudRespMouseExited(evt);
            }
        });

        btnCrudAlmox.setBackground(new java.awt.Color(153, 153, 153));
        btnCrudAlmox.setForeground(new java.awt.Color(255, 255, 255));
        btnCrudAlmox.setText(" Cadastrar Almoxarifado");
        btnCrudAlmox.setOpaque(true);
        btnCrudAlmox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrudAlmoxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrudAlmoxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrudAlmoxMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCrudItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMovItem, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
            .addComponent(btnCrudResp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCrudAlmox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMovItem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCrudItem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCrudAlmox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCrudResp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 381, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMovItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMovItemMouseEntered
        btnMovItem.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnMovItemMouseEntered

    private void btnCrudItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudItemMouseEntered
        btnCrudItem.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnCrudItemMouseEntered

    private void btnCrudAlmoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudAlmoxMouseEntered
        btnCrudAlmox.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnCrudAlmoxMouseEntered

    private void btnCrudRespMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudRespMouseEntered
        btnCrudResp.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnCrudRespMouseEntered

    private void btnCrudRespMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudRespMouseExited
        btnCrudResp.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_btnCrudRespMouseExited

    private void btnCrudAlmoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudAlmoxMouseExited
        btnCrudAlmox.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_btnCrudAlmoxMouseExited

    private void btnCrudItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudItemMouseExited
        btnCrudItem.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_btnCrudItemMouseExited

    private void btnMovItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMovItemMouseExited
        btnMovItem.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_btnMovItemMouseExited

    private void btnCrudRespMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudRespMouseClicked
        CrudResponsavel crudResponsavel = new CrudResponsavel("Responsáveis");
        AlmoxarifadoGER.principal.pnMain.removeAll();
        AlmoxarifadoGER.principal.pnMain.add(crudResponsavel, BorderLayout.CENTER);
        AlmoxarifadoGER.atualizaTela();
    }//GEN-LAST:event_btnCrudRespMouseClicked

    private void btnCrudAlmoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudAlmoxMouseClicked
        CrudAlmoxarifado crudAlmoxarifado = new CrudAlmoxarifado("Almoxarifados");
        AlmoxarifadoGER.principal.pnMain.removeAll();
        AlmoxarifadoGER.principal.pnMain.add(crudAlmoxarifado, BorderLayout.CENTER);
        AlmoxarifadoGER.atualizaTela();
    }//GEN-LAST:event_btnCrudAlmoxMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCrudAlmox;
    private javax.swing.JLabel btnCrudItem;
    private javax.swing.JLabel btnCrudResp;
    private javax.swing.JLabel btnMovItem;
    // End of variables declaration//GEN-END:variables
}
