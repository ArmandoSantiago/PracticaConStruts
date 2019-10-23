package mx.edu.utez.domicilio.model;

import mx.edu.utez.servicios.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDao {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    private String queryTodoDomicilio = "exec todoDomicilio";

    public List<DomicilioBean> allDomicilio() {
        List<DomicilioBean> respuestas = new ArrayList<DomicilioBean>();
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoDomicilio);
            rs = ps.executeQuery();
            while (rs.next()) {
                DomicilioBean domicilio = new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero"));
                respuestas.add(domicilio);
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allDomicilio-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuestas;
    }

    private String queryGetDomicilio = "exec todoDomicilio";

    public DomicilioBean getDomicilio() {
        DomicilioBean respuesta = null;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoDomicilio);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero"));
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allDomicilio-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryNewDomicilio = "exec newDomicilio  ?,?,?,?,?";

    public boolean newDomicilio(DomicilioBean domicilioBean) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryNewDomicilio);
            ps.setString(1, domicilioBean.getEstado());
            ps.setString(2, domicilioBean.getMunicipio());
            ps.setString(3, domicilioBean.getColonia());
            ps.setString(4, domicilioBean.getCalle());
            ps.setString(5, domicilioBean.getNumero());
            respuesta = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error metodo newDomicilio-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryModDomicilio = "exec modDomicilio ?,?,?,?,?,?";

    public boolean modDomicilio(DomicilioBean domicilioBean) {
        boolean modificado = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryModDomicilio);
            ps.setInt(1, domicilioBean.getIdDomicilio());
            ps.setString(2, domicilioBean.getEstado());
            ps.setString(3, domicilioBean.getMunicipio());
            ps.setString(4, domicilioBean.getColonia());
            ps.setString(5, domicilioBean.getCalle());
            ps.setString(6, domicilioBean.getNumero());
            modificado = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error metodo modDomicilio-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return modificado;
    }

    private final String queryDelDomicilio = "exec delDomicilio ?";

    public boolean delDomicilio(int id) {
        boolean eliminado = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryDelDomicilio);
            ps.setInt(1, id);
            eliminado = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error en el método delDomicilio-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return eliminado;
    }

    private void cerrar() {
        try {
            if (conexion != null) {
                conexion.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexion -> " + e.getMessage());
        }
    }
}
