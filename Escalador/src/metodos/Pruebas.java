package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
        
        System.out.println(getRendimientoConfigurado());

    }
    
    /**
     * Redondea a dos decimales con método científico.
     * @param d
     * @return 
     */
    public double redondeoDosDecimales(double d) {
        return Math.rint(d * 100) / 100;
    }
    
    public double getRendimiento(double horasEntrenadas, int numItinerariosFin, double numSemanas) {
        return getRendimientoEntrenamiento(horasEntrenadas, numSemanas) + getRendimientoItinerariosRealizados(numItinerariosFin, numSemanas);
    }
    
    public int getNumeroItinerariosRealizados(Date desdeFecha, Date hastaFecha) {
        conectar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaInicio = dateFormat.format(desdeFecha);
        String fechaFin = dateFormat.format(hastaFecha);
        int numeroItinerarios = 0;
        String sql = "SELECT COUNT(*) "
                + "FROM fecha_itinerario "
                + "WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
        try {
            resultSet = consulta.executeQuery(sql);
            while(resultSet.next()) {
                numeroItinerarios = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroItinerarios;
    }

    public double getRendimientoItinerariosRealizados(int numItinerariosFin, double numSemanas) {
        double rendimiento = numItinerariosFin * 0.25 / numSemanas;
        if (rendimiento > 5) {
            rendimiento = 5;
        }
        return rendimiento;
    }
    
    public double getNumeroDeHorasEntrenadas(Date desdeFecha, Date hastaFecha) {
        conectar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaInicio = dateFormat.format(desdeFecha);
        String fechaFin = dateFormat.format(hastaFecha);
        double horasEntrenadas = 0.0;
        String sql = "SELECT hora_comienzo, hora_fin "
                + "FROM entrenamiento "
                + "WHERE fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
        Date horaComienzo, horaFin;
        long horaInicioMilis, horaFinMilis, milisegundosEntrenados;
        milisegundosEntrenados = 0;
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                horaComienzo = resultSet.getTime(1);
                horaFin = resultSet.getTime(2);
                horaInicioMilis = horaComienzo.getTime();
                horaFinMilis = horaFin.getTime();
                milisegundosEntrenados += (horaFinMilis - horaInicioMilis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        horasEntrenadas = milisegundosEntrenados / 1000 / 60 / 60;
        return horasEntrenadas;
    }

    public double getRendimientoEntrenamiento(double horasEntrenadas, double numeroSemanas) {
        double rendimiento = horasEntrenadas * 0.5 / numeroSemanas;
        if (rendimiento > 5) {
            rendimiento = 5;
        }
        return rendimiento;
    }
    
    public double getNumeroDeSemanasConfiguradas () {
        String sql = "SELECT fecha_inicio, fecha_fin FROM escalador";
        long intervalo;
        double semanasIntervalo = 0.0;
        Date fechaInicio = null, fechaFin = null;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                fechaInicio = resultSet.getTimestamp(1);
                fechaFin = resultSet.getTimestamp(2);
            }
            if(null == fechaFin) {
                fechaFin = new Date();
            }
            intervalo = fechaFin.getTime() - fechaInicio.getTime();
            semanasIntervalo = intervalo / 1000.0 / 60.0 / 60.0 / 24.0 / 7.0;
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semanasIntervalo;
    }
    
    
    public Date getFechaInicioConfigurada() {
        String sql = "SELECT fecha_inicio FROM escalador";
        Date fechaInicio = null;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while(resultSet.next()) {
                fechaInicio = resultSet.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaInicio;
    }
    
    public Date getFechaFinConfigurada() {
        String sql = "SELECT fecha_fin FROM escalador";
        Date fechaFin = null;
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while(resultSet.next()) {
                fechaFin = resultSet.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaFin;
    }
    
    public double getRendimientoConfigurado() {
        Date fechaInicio = getFechaInicioConfigurada();
        Date fechaFin = getFechaFinConfigurada();
        double horasEntrenadas = getNumeroDeHorasEntrenadas(fechaInicio, fechaFin);
        int numItinerariosFin = getNumeroItinerariosRealizados(fechaInicio, fechaFin);
        double numSemanas = getNumeroDeSemanasConfiguradas();
        double rendimientoConfigurado = getRendimiento(horasEntrenadas, numItinerariosFin, numSemanas);
        return rendimientoConfigurado;
    }
}
