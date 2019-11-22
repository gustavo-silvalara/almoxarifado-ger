/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.controller;

import almoxarifadoger.AlmoxarifadoGER;
import almoxarifadoger.model.Almoxarifado;
import almoxarifadoger.model.Item;
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
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class DAOItem {
    
    private Connection connection;
    
    public DAOItem() {
        try {
            this.connection = ConnectionFactory.getConnectionFactory();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int save(Item item) {
        int i = 0;
        try {
            if (item == null) {
                return 0;
            }
            if (item.getId() == null) {
                item.setId(Long.MIN_VALUE);
            }
            PreparedStatement stmt = this.connection.prepareStatement("select count(id) as count from items where id = ?;");
            stmt.setLong(1, item.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Long count = rs.getLong("count");
            if (count > 0) {
                stmt = this.connection.prepareStatement("update items set descricao = ? where id = ?;");
                stmt.setString(1, item.getDescricao());
                stmt.setLong(2, item.getId());
            } else {
                stmt = this.connection.prepareStatement("insert into items(descricao) VALUES (?);");
                stmt.setString(1, item.getDescricao());
            }
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public List<Item> read(String where) {
        List<Item> listaItem = new ArrayList<>();
        try {
            String select = "select * from items (where);";
            select = select.replace("(where)", where);
            PreparedStatement stmt = this.connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item i = new Item();
                i.setId(rs.getLong("id"));
                i.setDescricao(rs.getString("descricao"));
                listaItem.add(i);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItem;
    }
    
    public int delete(Item item) {
        int i = 0;
        if (item == null) {
            return 0;
        }
        try {
            String select = "delete from items where id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(select);
            stmt.setLong(1, item.getId());
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
           if (ex.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(AlmoxarifadoGER.principal, "Não foi possível realizar a "
                        + "exclusão, há um movimento relacionado");
            } else {
                Logger.getLogger(DAOAlmoxarifado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }
}
