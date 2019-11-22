/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxarifadoger.controller;

import almoxarifadoger.AlmoxarifadoGER;
import almoxarifadoger.model.Almoxarifado;
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
public class DAOAlmoxarifado {

    private Connection connection;

    public DAOAlmoxarifado() {
        try {
            this.connection = ConnectionFactory.getConnectionFactory();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlmoxarifado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int save(Almoxarifado almoxarifado) {
        int i = 0;
        try {
            if (almoxarifado == null) {
                return 0;
            }
            if (almoxarifado.getId() == null) {
                almoxarifado.setId(Long.MIN_VALUE);
            }
            PreparedStatement stmt = this.connection.prepareStatement("select count(id) as count from almoxarifados where id = ?;");
            stmt.setLong(1, almoxarifado.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Long count = rs.getLong("count");
            if (count > 0) {
                stmt = this.connection.prepareStatement("update almoxarifados set nome = ? where id = ?;");
                stmt.setString(1, almoxarifado.getNome());
                stmt.setLong(2, almoxarifado.getId());
            } else {
                stmt = this.connection.prepareStatement("insert into almoxarifados(nome) VALUES (?);");
                stmt.setString(1, almoxarifado.getNome());
            }
            i = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlmoxarifado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public List<Almoxarifado> read(String where) {
        List<Almoxarifado> listaAlmoxarifado = new ArrayList<>();
        try {
            String select = "select * from almoxarifados (where);";
            select = select.replace("(where)", where);
            PreparedStatement stmt = this.connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Almoxarifado a = new Almoxarifado();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                listaAlmoxarifado.add(a);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAlmoxarifado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlmoxarifado;
    }

    public int delete(Almoxarifado almoxarifado) {
        int i = 0;
        if (almoxarifado == null) {
            return 0;
        }
        try {
            String select = "delete from almoxarifados where id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(select);
            stmt.setLong(1, almoxarifado.getId());
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
