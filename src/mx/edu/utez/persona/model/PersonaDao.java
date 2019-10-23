package mx.edu.utez.persona.model;

import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.servicios.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    private String queryTodoPersona = "exec todoPersona";

    public List<PersonaBean> allPersona() {
        List<PersonaBean> respuestas = new ArrayList<PersonaBean>();
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoPersona);
            rs = ps.executeQuery();
            while (rs.next()) {
                DomicilioBean domicilioBean = new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero"));
                PersonaBean persona = new PersonaBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), domicilioBean);
                respuestas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allPersona-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuestas;
    }

    private String queryGetPersona = "exec todoPersona";

    public PersonaBean getPersona() {
        PersonaBean respuesta = null;

        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoPersona);
            rs = ps.executeQuery();
            while (rs.next()) {
                DomicilioBean domicilioBean = new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero"));
                respuesta = new PersonaBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), domicilioBean);
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allPersona-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryNewPersona = "exec newPersona  ?,?,?,?";

    public boolean newPersona(PersonaBean personaBean) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryNewPersona);
            ps.setString(1, personaBean.getNombre());
            ps.setString(2, personaBean.getApeidos());
            ps.setString(3, personaBean.getAxoNacimiento());
            ps.setString(4, personaBean.getSexo());
            ps.setInt(5, personaBean.getDomicilioBean().getIdDomicilio());
            respuesta = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error metodo newPersona-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryModPersona = "exec modPersona ?, ?,?,?,?,?";

    public boolean modPersona(PersonaBean personaBean) {
        boolean modificado = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryModPersona);
            ps.setInt(1, personaBean.getIdPersona());
            ps.setString(2, personaBean.getNombre());
            ps.setString(3, personaBean.getApeidos());
            ps.setString(4, personaBean.getAxoNacimiento());
            ps.setString(5, personaBean.getSexo());
            ps.setInt(6, personaBean.getDomicilioBean().getIdDomicilio());
            modificado = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error metodo modPersona-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return modificado;
    }

    private final String queryDelPersona = "exec delPersona ?";

    public boolean delPersona(int id) {
        boolean eliminado = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryDelPersona);
            ps.setInt(1, id);
            eliminado = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error en el método delPersona-> " + e.getMessage());
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
