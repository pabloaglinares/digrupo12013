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
    
    /**
     * Calcula el rendimiento y lo devuelve con precisión de dos decimales.
     *
     * @return
     */
    public double getRendimiento() {
        double rendimiento = getRendimientoEntrenamiento() + getRendimientoItinerariosRealizados();
        rendimiento *= 100;
        rendimiento = (Math.rint(rendimiento)) / 100;
        return rendimiento;
    }

    public double getRendimientoItinerariosRealizados() {
        String sql = "SELECT COUNT(*) "
                + "FROM fecha_itinerario fit, escalador esc "
                + "WHERE fit.fecha BETWEEN esc.fecha_inicio AND esc.fecha_fin";
        conectar();
        double rendimiento = 0.0;
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                rendimiento = resultSet.getDouble(1) * 0.25 / getNumeroDeSemanasConfiguradas();
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
        String sql = "SELECT e.hora_comienzo, e.hora_fin "
                + "FROM entrenamiento e, escalador esc "
                + "WHERE e.fecha BETWEEN esc.fecha_inicio AND esc.fecha_fin";
        
        String sql2 = "SELECT fecha_inicio, fecha_fin FROM escalador";
        
        Date horaComienzo, horaFin;
        long horaInicioMilis, horaFinMilis, milisegundosEntrenados;
        milisegundosEntrenados = 0;
        double rendimiento = 0.0;
        double horasEntrenadas;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                horaComienzo = resultSet.getTime(1);
                horaFin = resultSet.getTime(2);
                horaInicioMilis = horaComienzo.getTime();
                horaFinMilis = horaFin.getTime();
                milisegundosEntrenados += (horaFinMilis - horaInicioMilis);
            }

            horasEntrenadas = milisegundosEntrenados / 1000 / 60 / 60;
            rendimiento = horasEntrenadas * 0.5 / getNumeroDeSemanasConfiguradas();
            if (rendimiento > 5) {
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
    
    public double getNumeroDeSemanasConfiguradas () {
        String sql2 = "SELECT fecha_inicio, fecha_fin FROM escalador";
        long intervalo;
        double semanasIntervalo = 0.0;
        Date fechaInicio = null, fechaFin = null;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql2);
            while (resultSet.next()) {
                fechaInicio = resultSet.getTimestamp(1);
                fechaFin = resultSet.getTimestamp(2);
            }
            
            if(null == fechaFin) {
                fechaFin = new Date();
            }
            
            intervalo = fechaFin.getTime() - fechaInicio.getTime();
            semanasIntervalo = intervalo / 1000 / 60 / 60 / 24 / 7;
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semanasIntervalo;
    }
}
