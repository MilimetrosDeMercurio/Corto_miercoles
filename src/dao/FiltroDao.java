/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import com.sun.istack.internal.logging.Logger;
import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pelicula;
//import sun.util.logging.PlatformLogger;
/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Pelicula> {

    private static final String SQL_INSERT = "INSERT INTO Movie (director, clasificacion, pais, año) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE Movie SET clasificacion = ?, pais = ?, año = ? WHERE director = ?";
    private static final String SQL_DELETE = "DELETE FROM Movie WHERE director = ?";
    private static final String SQL_READ = "SELECT * FROM Movie WHERE director = ?";
    private static final String SQL_READALL = "SELECT * FROM Movie";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Pelicula g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getDirector());
            ps.setString(2, g.getClasificacion());
            ps.setString(3, g.getPais());
            ps.setInt(4, g.getAño());
            ps.setBoolean(5, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Pelicula c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getDirector());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getClasificacion());
            ps.setString(2, c.getPais());
            ps.setInt(3, c.getAño());
            ps.setString(4, c.getDirector());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Pelicula read(Object key) {
        Pelicula f = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                f = new Pelicula(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;

    }

    @Override
    public ArrayList<Pelicula> readAll() {
        ArrayList<Pelicula> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Pelicula(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

}
