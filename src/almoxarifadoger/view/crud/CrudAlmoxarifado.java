/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.view.crud;

import almoxarifadoger.controller.DAOAlmoxarifado;
import almoxarifadoger.model.Almoxarifado;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class CrudAlmoxarifado extends CrudRead {

    private DAOAlmoxarifado daoA;

    public CrudAlmoxarifado(String titulo) {
        super("Almoxarifados");
        daoA = new DAOAlmoxarifado();
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
        List<Almoxarifado> listaAlmoxarifado = daoA.read(" ");
        if (!listaAlmoxarifado.isEmpty()) {
            DefaultTableModel m = (DefaultTableModel) tbResults.getModel();
            m.setRowCount(0);
            for (Almoxarifado r : listaAlmoxarifado) {
                m.addRow(new Object[]{r.getId(), r});
            }
        }
    }

    @Override
    public void btnAdd() {
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Almoxarifado!");
        Almoxarifado a = new Almoxarifado();
        a.setNome(nome);
        daoA.save(a);
    }

    @Override
    public void btnEdit() {
        Almoxarifado a = (Almoxarifado) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Almoxarifado!", a.getNome());
        a.setNome(nome);
        daoA.save(a);
    }

    @Override
    public void btnRemove() {
        Almoxarifado a = (Almoxarifado) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        daoA.delete(a);
    }

}
