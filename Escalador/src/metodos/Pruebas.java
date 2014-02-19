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

    public double getRendimiento() {
        return getRendimientoEntrenamiento() + getRendimientoItinerariosRealizados();
    }
    
    public double getRendimientoItinerariosRealizados() {
        String sql = "SELECT COUNT(*) " +
                     "FROM fecha_itinerario fit, escalador esc " +
                     "WHERE fit.fecha BETWEEN esc.fecha_inicio AND esc.fecha_fin";
        conectar();
        double rendimiento = 0.0;
        try {
            resultSet = consulta.executeQuery(sql);
            while(resultSet.next()) {
                rendimiento = resultSet.getDouble(1) * 0.25 / 7;
            }
            if (rendimiento > 5) {
                rendimiento = 5;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rendimiento;
    }
    
    public double getRendimientoEntrenamiento() {
        String sql = "SELECT e.hora_comienzo, e.hora_fin " +
                     "FROM entrenamiento e, escalador esc " +
                     "WHERE e.fecha BETWEEN esc.fecha_inicio AND esc.fecha_fin";
        Date horaComienzo, horaFin;
        long horaInicioMilis, horaFinMilis, totalEntrenamiento;
        totalEntrenamiento = 0;
        double rendimiento = 0.0;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                horaComienzo = resultSet.getTime(1);
                horaFin = resultSet.getTime(2);
                horaInicioMilis = horaComienzo.getTime();
                horaFinMilis = horaFin.getTime();
                totalEntrenamiento += (horaFinMilis - horaInicioMilis);
            }
            rendimiento =  totalEntrenamiento * 0.5 / 7;
            if(rendimiento > 5) {
                rendimiento = 5;
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

            conexion = DriverManager.getConnection("jdbc:hsqldb:file:database/escalada", "sa", "");
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
