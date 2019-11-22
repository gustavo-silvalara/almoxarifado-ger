/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.controller;

import almoxarifadoger.model.Almoxarifado;
import almoxarifadoger.model.ItemAlmoxarifado;
import almoxarifadoger.model.MovimentoItemAlmox;
import almoxarifadoger.model.Responsavel;
import almoxarifadoger.utils.StringUtils;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class DAOMovimento {

    private Connection connection;

    public DAOMovimento() {
        try {
            this.connection = ConnectionFactory.getConnectionFactory();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int save(MovimentoItemAlmox mov) {
        int i = 0;
        try {
            if (mov == null) {
                return 0;
            }
            if (mov.getId() == null) {
                mov.setId(Long.MIN_VALUE);
            }
            PreparedStatement stmt = this.connection.prepareStatement("select count(id) as count from MOVIMENTO_ITENS where id = ?;");
            stmt.setLong(1, mov.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Long count = rs.getLong("count");
            if (count > 0) {
                stmt = this.connection.prepareStatement("update MOVIMENTO_ITENS\n"
                        + "set ID_ITEMS_ALMOX = ?, id_responsavel = ?, data = ?, tipo = ? where id = ?;");
                stmt.setLong(1, mov.getItemAlmox().getId());
                stmt.setLong(2, mov.getResponsavel().getId());
                stmt.setLong(3, mov.getData());
                stmt.setInt(4, mov.getTipo());
                stmt.setLong(5, mov.getId());
            } else {
                if (mov.getItemAlmox() != null) {
                    saveItemAlmox(mov.getItemAlmox());
                }
                stmt = this.connection.prepareStatement("insert into MOVIMENTO_ITENS(ID_ITEMS_ALMOX,"
                        + "id_responsavel,data,tipo) VALUES (?,?,?,?);");
                stmt.setLong(1, mov.getItemAlmox().getId());
                stmt.setLong(2, mov.getResponsavel().getId());
                stmt.setLong(3, mov.getData());
                stmt.setInt(4, mov.getTipo());
            }
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public int saveItemAlmox(ItemAlmoxarifado iA) {
        int i = 0;
        try {
            if (iA == null) {
                return 0;
            }
            if (iA.getId() == null) {
                iA.setId(Long.MIN_VALUE);
            }
            PreparedStatement stmt = this.connection.prepareStatement("select count(id) "
                    + "as count from ITEMS_ALMOXARIFADO where id_item = ? and id_almox = ?;");
            stmt.setLong(1, iA.getItem().getId());
            stmt.setLong(2, iA.getAlmoxarifado().getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Long count = rs.getLong("count");
            if (count > 0) {
                stmt = this.connection.prepareStatement("update ITEMS_ALMOXARIFADO\n"
                        + "set ID_ITEM = ?, ID_ALMOX = ? where id = ?;");
                stmt.setLong(1, iA.getItem().getId());
                stmt.setLong(2, iA.getAlmoxarifado().getId());
                stmt.setLong(3, iA.getId());
            } else {
                stmt = this.connection.prepareStatement("insert into "
                        + "ITEMS_ALMOXARIFADO(ID_ITEM,"
                        + "ID_ALMOX) VALUES (?,?);");
                stmt.setLong(1, iA.getItem().getId());
                stmt.setLong(2, iA.getAlmoxarifado().getId());
            }
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        iA.setId(readIA("where id_item = " + iA.getItem().getId()
                + " and id_almox = " + iA.getAlmoxarifado().getId() + ";").get(0).getId());
        return i;
    }

    public List<MovimentoItemAlmox> read(String where) {
        List<MovimentoItemAlmox> listaMov = new ArrayList<>();
        try {
            String select = "select * from MOVIMENTO_ITENS (where);";
            select = select.replace("(where)", where);
            PreparedStatement stmt = this.connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            DAOResponsavel daoR = new DAOResponsavel();
            while (rs.next()) {
                MovimentoItemAlmox mov = new MovimentoItemAlmox();
                mov.setId(rs.getLong("id"));
                mov.setItemAlmox(readIA("where id = " + rs.getLong("id_items_almox"))
                        .get(0));
                mov.setResponsavel(daoR.read("where id = " + rs.getLong("id_responsavel"))
                        .get(0));
                mov.setData(rs.getLong("data"));
                mov.setTipo(rs.getInt("tipo"));
                listaMov.add(mov);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMov;
    }

    public List<ItemAlmoxarifado> readIA(String where) {
        List<ItemAlmoxarifado> listaIA = new ArrayList<>();
        try {
            String select = "select * from ITEMS_ALMOXARIFADO (where);";
            select = select.replace("(where)", where);
            PreparedStatement stmt = this.connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            DAOAlmoxarifado daoA = new DAOAlmoxarifado();
            DAOItem daoI = new DAOItem();
            while (rs.next()) {
                ItemAlmoxarifado iA = new ItemAlmoxarifado();
                iA.setId(rs.getLong("id"));
                iA.setItem(daoI.read("where id = " + rs.getLong("id_item"))
                        .get(0));
                iA.setAlmoxarifado(daoA.read("where id = " + rs.getLong("id_almox"))
                        .get(0));
                listaIA.add(iA);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaIA;
    }

    public int delete(MovimentoItemAlmox mov) {
        int i = 0;
        if (mov == null) {
            return 0;
        }
        try {
            String select = "delete from MOVIMENTO_ITENS where id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(select);
            stmt.setLong(1, mov.getId());
            deleteIA(mov.getItemAlmox());
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public int deleteIA(ItemAlmoxarifado iA) {
        int i = 0;
        if (iA == null) {
            return 0;
        }
        try {
            String select = "delete from ITEMS_ALMOXARIFADO where id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(select);
            stmt.setLong(1, iA.getId());
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
}
