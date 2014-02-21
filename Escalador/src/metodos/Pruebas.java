package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pruebas {

    Connection conexion;
    Statement consulta;
    ResultSet resultSet;

    public static void main(String[] args) {
        Pruebas p = new Pruebas();
        p.ejecutaPrueba();
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

    private void ejecutaPrueba() {
        conectar();
        try {
            resultSet = consulta.executeQuery("SELECT  FROM ");
            while(resultSet.next()) {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
