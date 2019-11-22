/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.view.crud;

import almoxarifadoger.AlmoxarifadoGER;
import almoxarifadoger.controller.DAOMovimento;
import almoxarifadoger.model.MovimentoItemAlmox;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class CrudMovimento extends CrudLayout {

    DAOMovimento daoM;

    public CrudMovimento(String titulo) {
        super("Movimentar Produtos");
        daoM = new DAOMovimento();
        setTbModel();
        getItens();
    }

    @Override
    public void setTbModel() {
        tbResults.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Data", "Almoxarifado", "Item", "Tipo", "Responsável"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbResults);

        if (tbResults.getColumnModel().getColumnCount() > 0) {
            tbResults.getColumnModel().getColumn(0).setMinWidth(80);
            tbResults.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbResults.getColumnModel().getColumn(0).setMaxWidth(100);
            tbResults.getColumnModel().getColumn(1).setMinWidth(160);
            tbResults.getColumnModel().getColumn(1).setPreferredWidth(160);
            tbResults.getColumnModel().getColumn(1).setMaxWidth(160);
            tbResults.getColumnModel().getColumn(2).setMinWidth(100);
            tbResults.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbResults.getColumnModel().getColumn(2).setMaxWidth(400);
            tbResults.getColumnModel().getColumn(3).setMinWidth(100);
            tbResults.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbResults.getColumnModel().getColumn(3).setMaxWidth(400);
            tbResults.getColumnModel().getColumn(4).setMinWidth(100);
            tbResults.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbResults.getColumnModel().getColumn(4).setMaxWidth(100);
            tbResults.getColumnModel().getColumn(5).setMinWidth(150);
            tbResults.getColumnModel().getColumn(5).setPreferredWidth(150);
            tbResults.getColumnModel().getColumn(5).setMaxWidth(400);
        }
    }

    @Override
    public void getItens() {
        List<MovimentoItemAlmox> listaMov = daoM.read(" ");
        if (!listaMov.isEmpty()) {
            DefaultTableModel m = (DefaultTableModel) tbResults.getModel();
            m.setRowCount(0);
            for (MovimentoItemAlmox mov : listaMov) {
                Date date = new java.sql.Date(mov.getData());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                m.addRow(new Object[]{mov, df.format(date), mov.getItemAlmox().getAlmoxarifado(),
                    mov.getItemAlmox().getItem(), (mov.getTipo() == 0) ? "Entrada" : "Saída", mov.getResponsavel()});
            }
        }
    }

    @Override
    public void btnAdd() {
        DialogCrudMovimento dCrud = new DialogCrudMovimento(AlmoxarifadoGER.principal, true);
        dCrud.setVisible(true);
    }

    @Override
    public void btnEdit() {

    }

    @Override
    public void btnRemove() {
        MovimentoItemAlmox mov = (MovimentoItemAlmox) tbResults.getValueAt(tbResults.getSelectedRow(), 0);
        daoM.delete(mov);
    }

}
