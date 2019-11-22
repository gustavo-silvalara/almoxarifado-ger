/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.controller;

import almoxarifadoger.AlmoxarifadoGER;
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
public class DAOResponsavel {

    private Connection connection;

    public DAOResponsavel() {
        try {
            this.connection = ConnectionFactory.getConnectionFactory();
        } catch (SQLException ex) {
            Logger.getLogger(DAOResponsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int save(Responsavel responsavel) {
        int i = 0;
        try {
            if (responsavel == null) {
                return 0;
            }
            if (responsavel.getId() == null) {
                responsavel.setId(Long.MIN_VALUE);
            }
            PreparedStatement stmt = this.connection.prepareStatement("select count(id) as count from responsaveis where id = ?;");
            stmt.setLong(1, responsavel.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Long count = rs.getLong("count");
            if (count > 0) {
                stmt = this.connection.prepareStatement("update responsaveis set nome = ? where id = ?;");
                stmt.setString(1, responsavel.getNome());
                stmt.setLong(2, responsavel.getId());
            } else {
                stmt = this.connection.prepareStatement("insert into responsaveis(nome) VALUES (?);");
                stmt.setString(1, responsavel.getNome());
            }
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOResponsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public List<Responsavel> read(String where) {
        List<Responsavel> listaResponsavel = new ArrayList<>();
        try {
            String select = "select * from responsaveis (where);";
            select = select.replace("(where)", where);
            PreparedStatement stmt = this.connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Responsavel r = new Responsavel();
                r.setId(rs.getLong("id"));
                r.setNome(rs.getString("nome"));
                listaResponsavel.add(r);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOResponsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaResponsavel;
    }

    public int delete(Responsavel responsavel) {
        int i = 0;
        if (responsavel == null) {
            return 0;
        }
        try {
            String select = "delete from responsaveis where id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(select);
            stmt.setLong(1, responsavel.getId());
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
