package mx.edu.utez.docente.model;

import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.servicios.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocenteDao {

    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    private String queryTodoDocente = "select * from Docente d inner join Persona p on d.idPersona = p.idPersona inner join Domicilio dom on dom.idDomicilio = p.idDomicilio; ";

    public List<DocenteBean> allDocente() {
        List<DocenteBean> respuestas = new ArrayList<DocenteBean>();

        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoDocente);
            rs = ps.executeQuery();
            while (rs.next()) {
                DocenteBean docente = new DocenteBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero")), rs.getInt("idDocente"), rs.getString("numeroCedula"), rs.getDouble("salarioDiario"));
                respuestas.add(docente);
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allDocente-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuestas;
    }

    private String querysearchDocente = "select * from Docente d inner join Persona p on d.idPersona = p.idPersona inner join Domicilio dom on dom.idDomicilio = p.idDomicilio where d.idDocente = ?; ";

    public DocenteBean searchDocente(int idDocente) {
        DocenteBean respuesta = null;

        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(querysearchDocente);
            ps.setInt(1, idDocente);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = new DocenteBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero")), rs.getInt("idDocente"), rs.getString("numeroCedula"), rs.getDouble("salarioDiario"));
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allDocente-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryNewDocente = "insert into Docente (numeroCedula, salarioDiario, idPersona) values (?,?,?);";
    private String queryNewPersona = "insert into Persona (nombre, apeidos, axoNacimiento, sexo, idDomicilio) values (?,?,?,?,?);";
    private String queryNewDomicilio = "insert into Domicilio (estado, municipio, colonia, calle, numero) values (?,?,?,?,?);";

    public boolean newDocente(DocenteBean docenteBean) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryNewDomicilio, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, docenteBean.getDomicilioBean().getEstado());
            ps.setString(2, docenteBean.getDomicilioBean().getMunicipio());
            ps.setString(3, docenteBean.getDomicilioBean().getColonia());
            ps.setString(4, docenteBean.getDomicilioBean().getCalle());
            ps.setString(5, docenteBean.getDomicilioBean().getNumero());
            if (ps.executeUpdate() == 1) {
                rs = ps.getGeneratedKeys();
                ps = conexion.prepareStatement(queryNewPersona, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, docenteBean.getNombre());
                ps.setString(2, docenteBean.getApeidos());
                ps.setString(3, docenteBean.getAxoNacimiento());
                ps.setString(4, docenteBean.getSexo());
                ps.setInt(5, rs.next() ? rs.getInt(1) : 0);
                if (ps.executeUpdate() == 1) {
                    rs = ps.getGeneratedKeys();
                    ps = conexion.prepareStatement(queryNewDocente, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, docenteBean.getNumeroCedula());
                    ps.setDouble(2, docenteBean.getSalarioDiario());
                    ps.setInt(3, rs.next() ? rs.getInt(1) : 0);
                    respuesta = ps.executeUpdate() == 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error metodo newDocente-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryModDocente = "update Docente set numeroCedula = ? , salarioDiario = ?  where idDocente = ?;";
    private String queryModPersona = "update Persona set nombre = ? , apeidos = ? , axoNacimiento = ? , sexo = ?  where idPersona = ?;";
    private String queryModDomicilio = "update Domicilio set estado = ? , municipio  = ? , colonia = ? , calle = ? , numero = ? where idDomicilio = ?;";

    public boolean modDocente(DocenteBean docenteBean) {
        System.out.println("Entra a mod docente");
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryModDomicilio);
            ps.setString(1, docenteBean.getDomicilioBean().getEstado());
            ps.setString(2, docenteBean.getDomicilioBean().getMunicipio());
            ps.setString(3, docenteBean.getDomicilioBean().getColonia());
            ps.setString(4, docenteBean.getDomicilioBean().getCalle());
            ps.setString(5, docenteBean.getDomicilioBean().getNumero());
            ps.setInt(6, docenteBean.getDomicilioBean().getIdDomicilio());
            ps.executeUpdate();
            ps = conexion.prepareStatement(queryModPersona);
            ps.setString(1, docenteBean.getNombre());
            ps.setString(2, docenteBean.getApeidos());
            ps.setString(3, docenteBean.getAxoNacimiento());
            ps.setString(4, docenteBean.getSexo());
            ps.setInt(5, docenteBean.getIdPersona());
            ps.executeUpdate();
            ps = conexion.prepareStatement(queryModDocente);
            ps.setString(1, docenteBean.getNumeroCedula());
            ps.setDouble(2, docenteBean.getSalarioDiario());
            ps.setInt(3, docenteBean.getId());
            respuesta = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error metodo modDocente-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private final String queryDelDocente = "delete from Domicilio where idDomicilio = ?;";

    public boolean delDocente(int id) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryDelDocente);
            ps.setInt(1, id);
            respuesta = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error en el método delDocente-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
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
