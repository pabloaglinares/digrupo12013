package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    
//    double puntuacionEntrenamiento = (SELECT SUM(e.hora_fin - e.hora_inicio) / 7
//  FROM entrenamientos e, configuracion c
//  WHERE e.fecha BETWEEN c.fecha_desde AND c.fecha_hasta) * 0,5; // obtiene las hora medias semanales
//  // y multiplico por 0,5 puntos de rendimiento que da cada hora semanal media entrenada
//  // La tabla 'configuración' creo que se llama 'escalador' en la BD.
//
//if(puntuacionEntrenamiento > 5) {
//  puntuacionEntrenamiento = 5; // la máxima puntuación por entrenar
//}
//
//double puntuacionItinerariosRealizados = (SELECT COUNT(*)
//  FROM itinerarios_realizados i, configuracion c
//  WHERE i.fecha_fin BETWEEN c.fecha_desde AND c.fecha_hasta) * 0,25;
//
//if(puntuacionItinerariosRealizados > 5) {
//  puntuacionItinerariosRealizados = 5; // la máxima puntuación resolver itinerarios
//}
//
//double rendimiento = puntuacionEntrenamiento + puntuacionItinerariosRealizados;
    
    public int getRendimiento() {
        String sql = "SELECT e.hora_comienzo, e.hora_fin FROM entrenamiento e";
        Date horaComienzo, horaFin;
//        Calendar calendario = new ;
        int rendimiento = 0;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                horaComienzo =  resultSet.getDate(1);
                horaFin = resultSet.getDate(2);
//                calendario
//                System.out.println();
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
