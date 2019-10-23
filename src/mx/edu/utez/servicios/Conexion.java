package mx.edu.utez.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static String ip = "localhost";
    private static String nombreBase = "Escuela";
    private static String usuario = "root";
    private static String contrasena = "root";

//    SET GLOBAL time_zone = '-3:00'

    public static Connection getConexion() throws SQLException {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        con = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + nombreBase + "?user=" + usuario + "&password=" + contrasena + "&useSSL=false");
        return con;
    }

    public static void main(String[] args) {
        Conexion c = new Conexion();
        try {
            Connection con = c.getConexion();
            System.out.println("Conexion Exitosa...");
        } catch (SQLException e) {
            System.out.println("Error en la conexion... " + e);
        }
    }


}
