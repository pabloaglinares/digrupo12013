package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pruebas {

    Connection conexion;
    Statement consulta;
    ResultSet resultSet;

    public static void main(String[] args) {
        
    }

    public void conectar() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {

            conexion = DriverManager.getConnection("jdbc:hsqldb:file:database/escalada", "sa", "");
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
