package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pruebas {
    
    Connection conexion;
    Statement consulta;
    ResultSet resultSet;

    public static void main(String[] args) {
        Pruebas prueba = new Pruebas();
        System.out.println(prueba.getRendimiento());
    }
    
    public int getRendimiento() {
        String sql = "SELECT week(fecha) FROM entrenamiento";
        conectar();
        int rendimiento = 0;
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                Date fecha =  resultSet.getDate(1);
                System.out.println(fecha);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL.");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rendimiento;
    }
    
    public void conectar() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            
            conexion = DriverManager.getConnection("jdbc:hsqldb:file:database/escalada", "sa", "");//aqui
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
