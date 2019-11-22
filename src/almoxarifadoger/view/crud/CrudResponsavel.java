/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.view.crud;

import almoxarifadoger.controller.DAOResponsavel;
import almoxarifadoger.model.Responsavel;
import almoxarifadoger.utils.StringUtils;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class CrudResponsavel extends CrudLayout {

    private DAOResponsavel daoR;

    public CrudResponsavel(String titulo) {
        super("Responsáveis");
        daoR = new DAOResponsavel();
        setTbModel();
        getItens();
    }

    @Override
    public void setTbModel() {
        tbResults.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Nome"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbResults);

        if (tbResults.getColumnModel().getColumnCount() > 0) {
            tbResults.getColumnModel().getColumn(0).setMinWidth(100);
            tbResults.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbResults.getColumnModel().getColumn(0).setMaxWidth(150);
            tbResults.getColumnModel().getColumn(1).setMinWidth(200);
            tbResults.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbResults.getColumnModel().getColumn(1).setMaxWidth(3000);
        }
    }

    @Override
    public void getItens() {
        List<Responsavel> listaResponsavel = daoR.read(" ");
        if (!listaResponsavel.isEmpty()) {
            DefaultTableModel m = (DefaultTableModel) tbResults.getModel();
            m.setRowCount(0);
            for (Responsavel r : listaResponsavel) {
                m.addRow(new Object[]{r.getId(), r});
            }
        }
    }

    @Override
    public void btnAdd() {
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Responsável!");
        if (StringUtils.stringValida(nome)) {
            Responsavel r = new Responsavel();
            r.setNome(nome);
            daoR.save(r);
        }
    }

    @Override
    public void btnEdit() {
        Responsavel r = (Responsavel) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Responsável!", r.getNome());
        if (StringUtils.stringValida(nome)) {
            r.setNome(nome);
            daoR.save(r);
        }
    }

    @Override
    public void btnRemove() {
        Responsavel r = (Responsavel) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        daoR.delete(r);
    }

}
