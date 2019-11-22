/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.view.crud;

import almoxarifadoger.controller.DAOItem;
import almoxarifadoger.model.Item;
import almoxarifadoger.utils.StringUtils;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class CrudItem extends CrudLayout {

    private DAOItem daoI;

    public CrudItem(String titulo) {
        super("Itens");
        daoI = new DAOItem();
        setTbModel();
        getItens();
    }

    @Override
    public void setTbModel() {
        tbResults.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Descrição"
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
        List<Item> listaItem = daoI.read(" ");
        if (!listaItem.isEmpty()) {
            DefaultTableModel m = (DefaultTableModel) tbResults.getModel();
            m.setRowCount(0);
            for (Item i : listaItem) {
                m.addRow(new Object[]{i.getId(), i});
            }
        }
    }

    @Override
    public void btnAdd() {
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Item!");
        if (StringUtils.stringValida(nome)) {
            Item i = new Item();
            i.setDescricao(nome);
            daoI.save(i);
        }
    }

    @Override
    public void btnEdit() {
        Item i = (Item) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        String nome = JOptionPane.showInputDialog(this, "Insira o Nome do Item!", i.getDescricao());
        if (StringUtils.stringValida(nome)) {
            i.setDescricao(nome);
            daoI.save(i);
        }
    }

    @Override
    public void btnRemove() {
        Item i = (Item) tbResults.getValueAt(tbResults.getSelectedRow(), 1);
        daoI.delete(i);
    }

}
