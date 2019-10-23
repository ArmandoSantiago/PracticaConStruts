package mx.edu.utez.estudiante.model;

import mx.edu.utez.domicilio.model.DomicilioBean;
import mx.edu.utez.servicios.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    private String queryTodoEstudiante = "select * from Estudiante d inner join Persona p on d.idPersona = p.idPersona inner join Domicilio dom on dom.idDomicilio = p.idDomicilio;";

    public List<EstudianteBean> allEstudiante() {
        List<EstudianteBean> respuestas = new ArrayList<EstudianteBean>();

        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryTodoEstudiante);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstudianteBean estudiante = new EstudianteBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero")), rs.getInt("idEstudiante"), rs.getString("matricula"), rs.getDouble("promedio"));
                respuestas.add(estudiante);
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allEstudiante-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuestas;
    }

    private String querysearchEstudiante = "select * from Estudiante d inner join Persona p on d.idPersona = p.idPersona inner join Domicilio dom on dom.idDomicilio = p.idDomicilio where d.idEstudiante = ? ;";

    public EstudianteBean searchEstudiante(int idEstudiante) {
        EstudianteBean respuesta = null;

        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(querysearchEstudiante);
            ps.setInt(1, idEstudiante);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = new EstudianteBean(rs.getInt("idPersona"), rs.getString("nombre"), rs.getString("apeidos"), rs.getString("axoNacimiento"), rs.getString("sexo"), new DomicilioBean(rs.getInt("idDomicilio"), rs.getString("estado"), rs.getString("municipio"), rs.getString("colonia"), rs.getString("calle"), rs.getString("numero")), rs.getInt("idEstudiante"), rs.getString("matricula"), rs.getDouble("promedio"));
            }
        } catch (SQLException e) {
            System.err.println("Error en el metodo allEstudiante-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryNewEstudiante = "insert into estudiante (matricula, promedio, idPersona) values (?,?,?);";
    private String queryNewPersona = "insert into persona (nombre, apeidos, axoNacimiento, sexo, idDomicilio) values (?,?,?,?,?);";
    private String queryNewDomicilio = "insert into domicilio (estado, municipio, colonia, calle, numero) values (?,?,?,?,?);";

    public boolean newEstudiante(EstudianteBean estudianteBean) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryNewDomicilio, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, estudianteBean.getDomicilioBean().getEstado());
            ps.setString(2, estudianteBean.getDomicilioBean().getMunicipio());
            ps.setString(3, estudianteBean.getDomicilioBean().getColonia());
            ps.setString(4, estudianteBean.getDomicilioBean().getCalle());
            ps.setString(5, estudianteBean.getDomicilioBean().getNumero());
            if (ps.executeUpdate() == 1) {
                rs = ps.getGeneratedKeys();
                ps = conexion.prepareStatement(queryNewPersona, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, estudianteBean.getNombre());
                ps.setString(2, estudianteBean.getApeidos());
                ps.setString(3, estudianteBean.getAxoNacimiento());
                ps.setString(4, estudianteBean.getSexo());
                ps.setInt(5, rs.next() ? rs.getInt(1) : 0);
                if (ps.executeUpdate() == 1) {
                    rs = ps.getGeneratedKeys();
                    ps = conexion.prepareStatement(queryNewEstudiante, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, estudianteBean.getNumeroMatricula());
                    ps.setDouble(2, estudianteBean.getPromedio());
                    ps.setInt(3, rs.next() ? rs.getInt(1) : 0);
                    respuesta = ps.executeUpdate() == 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error metodo newEstudiante-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private String queryModEstudiante = "update Estudiante set matricula = ? , promedio = ?  where idEstudiante = ?;";
    private String queryModPersona = "update Persona set nombre = ? , apeidos = ? , axoNacimiento = ? , sexo = ?  where idPersona = ?;";
    private String queryModDomicilio = "update Domicilio set estado = ? , municipio  = ? , colonia = ? , calle = ? , numero = ? where idDomicilio = ?;";

    public boolean modEstudiante(EstudianteBean estudianteBean) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryModDomicilio);
            ps.setString(1, estudianteBean.getDomicilioBean().getEstado());
            ps.setString(2, estudianteBean.getDomicilioBean().getMunicipio());
            ps.setString(3, estudianteBean.getDomicilioBean().getColonia());
            ps.setString(4, estudianteBean.getDomicilioBean().getCalle());
            ps.setString(5, estudianteBean.getDomicilioBean().getNumero());
            ps.setInt(6, estudianteBean.getDomicilioBean().getIdDomicilio());
            if (ps.executeUpdate() == 1) {
                ps = conexion.prepareStatement(queryModPersona);
                ps.setString(1, estudianteBean.getNombre());
                ps.setString(2, estudianteBean.getApeidos());
                ps.setString(3, estudianteBean.getAxoNacimiento());
                ps.setString(4, estudianteBean.getSexo());
                ps.setInt(5, estudianteBean.getIdPersona());
                if (ps.executeUpdate() == 1) {
                    ps = conexion.prepareStatement(queryModEstudiante);
                    ps.setString(1, estudianteBean.getNumeroMatricula());
                    ps.setDouble(2, estudianteBean.getPromedio());
                    ps.setInt(3, estudianteBean.getIdEstudiante());
                    respuesta = ps.executeUpdate() == 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error metodo newEstudiante-> " + e.getMessage());
        } finally {
            cerrar();
        }
        return respuesta;
    }

    private final String queryDelEstudiante = "delete from Domicilio where idDomicilio = ?;";

    public boolean delEstudiante(int id) {
        boolean respuesta = false;
        try {
            conexion = Conexion.getConexion();
            ps = conexion.prepareStatement(queryDelEstudiante);
            ps.setInt(1, id);
            respuesta = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error en el método delEstudiante-> " + e.getMessage());
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
