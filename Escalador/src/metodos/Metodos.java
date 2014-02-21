package metodos;

import datos.Configuracion;
import datos.Entrenamiento;
import datos.Itinerario;
import datos.ItinerarioFin;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Metodos {

    Connection conexion;
    Statement consulta;
    ResultSet resultSet;
    public String ruta;
    List<Entrenamiento> listaEntrenamientos;

    /**
     * Constructor for objects of class Conexion
     */
    public Metodos() {
        ruta = System.getProperty("user.dir");
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

    public List<Itinerario> getListaItinerarios() {
        String sql = "select * from ITINERARIO";
        ResultSet resultSet;
        conectar();
        Itinerario itinerario;
        List<Itinerario> listaItinerarios = new ArrayList<>();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                itinerario = new Itinerario(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                listaItinerarios.add(itinerario);
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
        return listaItinerarios;
    }

    public List<String> getListaNombresDeItinerarios() {
        List<String> nombresDeItinerarios = new ArrayList<>();
        String sql = "SELECT nombre FROM itinerario";
        conectar();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                nombresDeItinerarios.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombresDeItinerarios;
    }

    public List<ItinerarioFin> getListaItinerFin() throws SQLException {
        ItinerarioFin itinerarioFin;
        List<ItinerarioFin> listaItinerarioFin = new ArrayList<>();
        conectar();
        resultSet = consulta.executeQuery("select IT.NOMBRE,FI.FECHA from FECHA_ITINERARIO FI,ITINERARIO IT "
                + "where IT.P_ITINERARIO=FI.A_ITINERARIO  ORDER BY FECHA");

        try {

            while (resultSet.next()) {
                itinerarioFin = new ItinerarioFin(
                        resultSet.getString(1),
                        resultSet.getTimestamp(2));

                listaItinerarioFin.add(itinerarioFin);

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

        return listaItinerarioFin;
    }

    public List<Entrenamiento> getListaEntrenamientos() throws SQLException {

        Entrenamiento entrenamiento;
        List<Entrenamiento> listaEntrenamientos = new ArrayList<>();
        conectar();

        resultSet = consulta.executeQuery("select * from ENTRENAMIENTO");
        try {

            while (resultSet.next()) {
                entrenamiento = new Entrenamiento(
                        resultSet.getDate(1),
                        resultSet.getTime(2),
                        resultSet.getTime(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                listaEntrenamientos.add(entrenamiento);

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
        return listaEntrenamientos;
        //CATCH SQL EXCEPTION
    }

    /**
     * Inserta un nuevo entrenamiento en la BD y lo confirma con un booleano.
     *
     * @param fecha
     * @param horaIni
     * @param horaFin
     * @param tipo
     * @param descripcion
     * @return true si pudo insertar el nuevo entrenamiento, o false si no pudo.
     */
    public boolean insertarEntrenamientoEnDB(String fecha, String horaIni, String horaFin, String tipo, String descripcion) {
        boolean pudoInsertarse;
        conectar();
        String sql = "INSERT INTO entrenamiento (FECHA,HORA_COMIENZO,HORA_FIN,TIPO,DESCRIPCION) "
                + "VALUES ('" + fecha + "', '" + horaIni + "', '" + horaFin
                + "', '" + tipo + "', '" + descripcion + "')";
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            pudoInsertarse = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;
    }

    public boolean updateEntrenamiento(String fecha, String horaIni, String horaFin, String tipo, String descripcion, String update) {
        boolean pudoInsertarse;
        conectar();
        String sql = "UPDATE entrenamiento SET FECHA='" + fecha + "', HORA_COMIENZO='" + horaIni + "',"
                + "HORA_FIN='" + horaFin + "',TIPO='" + tipo + "', DESCRIPCION='" + descripcion + "' WHERE DESCRIPCION='" + update + "'";
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            pudoInsertarse = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;

    }

    public boolean updateItinerario(String nombre, String loca, String tipo, String dific, String foto, int id) {
        boolean pudoInsertarse;
        conectar();
        String sql = "UPDATE ITINERARIO SET NOMBRE='" + nombre + "', LOCALIZACION='" + loca + "',"
                + "TIPO='" + tipo + "',DIFICULTAD='" + dific + "', FOTO='" + foto + "' WHERE P_ITINERARIO=" + id;
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            pudoInsertarse = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;

    }

    public void updateItiFin(int id, Timestamp fecha, int oldid, Timestamp oldFecha) {
        conectar();
        String sql = "UPDATE FECHA_ITINERARIO SET A_ITINERARIO=" + id + ", FECHA='" + fecha + "' "
                + "WHERE A_ITINERARIO=" + oldid + " and fecha='" + oldFecha + "'";
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void copiarFotografia(File archivoOrigen, File archivoDestino) {

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(archivoOrigen));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            out = new BufferedOutputStream(new FileOutputStream(archivoDestino));
        } catch (FileNotFoundException ex) {

        }
        byte buffer[] = new byte[1024];
        int leidos;
        try {
            while ((leidos = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, leidos);
            }
            in.close();
            out.close();
        } catch (IOException ex) {

        }

    }


    public List<Configuracion> getUsuario() throws SQLException {
        Configuracion config;
        List<Configuracion> usuarioList = new ArrayList<>();
        conectar();
        resultSet = consulta.executeQuery("select * from ESCALADOR");
        try {

            while (resultSet.next()) {
                config = new Configuracion(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4),
                        resultSet.getTimestamp(5));

                usuarioList.add(config);

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

        return usuarioList;
    }

    public boolean insertarConfigEnDB(String nombre, String apellido, Timestamp fecha, Timestamp fechaFin) {
        boolean pudoInsertarse;
        String sql = "UPDATE ESCALADOR SET NOMBRE = '" + nombre + "',APELLIDO = '" + apellido + "',"
                + "FECHA_INICIO ='" + fecha + "' ,"
                + "FECHA_FIN ='" + fechaFin + "' WHERE P_ESCALADOR=1";

        conectar();
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            pudoInsertarse = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;
    }

    public boolean insertarItinerarioEnDb(String nombre, String localizacion, String tipo, String dificultad, String foto) {
        boolean pudoInsertarse;
        String sql = "INSERT INTO ITINERARIO (NOMBRE,LOCALIZACION,TIPO,DIFICULTAD,FOTO) values ('" + nombre + "','" + localizacion + "','" + tipo + "','" + dificultad + "','" + foto + "')";
        conectar();
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            pudoInsertarse = false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;
    }

    public void deleteEntrenamiento(String texto) {
        String sql = "DELETE FROM ENTRENAMIENTO WHERE DESCRIPCION='" + texto + "'";
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteItinerario(String nombre, int id) {
        String sql = "DELETE FROM ITINERARIO WHERE P_ITINERARIO=" + id;
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteItinerarioFin(int id, Timestamp fecha) {
        String sql = "DELETE FROM FECHA_ITINERARIO WHERE A_ITINERARIO=" + id + " AND FECHA='" + fecha + "'";
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getIdItinerario(String nombre) {
        String sql = "SELECT P_ITINERARIO FROM ITINERARIO WHERE NOMBRE='" + nombre + "'";
        conectar();
        int id = 0;
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                id = resultSet.getInt("P_ITINERARIO");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public void insertItinerarioFin(int id, Timestamp fecha) {
        String sql = "INSERT INTO FECHA_ITINERARIO VALUES(" + id + ",'" + fecha + "')";
        conectar();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        double horasEntrenadas = 0.0;
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
    
    public void informe1(Timestamp fecha, Timestamp fecha2) {

        conectar();
        String archivojasper = "src/informes/ListaItinerario.jasper";//ruta
        Map parametros = new HashMap();

        parametros.put("fecha1", fecha);
        parametros.put("fecha2", fecha2);
        try {
            JasperPrint print = JasperFillManager.fillReport(archivojasper, parametros, conexion);

            JasperExportManager.exportReportToPdfFile(print, "ListaItinerario.pdf");

            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);

            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//informe1

    public void informe2(Date fecha, Date fecha2) {

        conectar();
        String archivojasper = "src/informes/FechasSesiones.jasper";//ruta
        Map parametros = new HashMap();

        parametros.put("date1", fecha);
        parametros.put("date2", fecha2);
        try {
            JasperPrint print = JasperFillManager.fillReport(archivojasper, parametros, conexion);

            JasperExportManager.exportReportToPdfFile(print, "FechasSesiones.pdf");

            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);

            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//informe2
public void informe3(Integer mes, Integer ano) {

        conectar();
        String archivojasper = "src/informes/GraficoEntrenamiento.jasper";//ruta
        Map parametros = new HashMap();

        parametros.put("mes", mes);
        parametros.put("ano", ano);
        try {
            JasperPrint print = JasperFillManager.fillReport(archivojasper, parametros, conexion);

            JasperExportManager.exportReportToPdfFile(print, "GraficoEntrenamiento.pdf");

            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);

            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//informe4
    public void informe4(Date fecha, Date fecha2) {

        conectar();
        String archivojasper = "src/informes/TipoDeSesionGrupo.jasper";//ruta
        Map parametros = new HashMap();

        parametros.put("date1", fecha);
        parametros.put("date2", fecha2);
        try {
            JasperPrint print = JasperFillManager.fillReport(archivojasper, parametros, conexion);

            JasperExportManager.exportReportToPdfFile(print, "TipoDeSesionGrupo.pdf");

            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);

            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//informe4

    public void informe5() {

        conectar();
        String archivojasper = "src/informes/GraficoItinerario.jasper";//ruta

        try {
            JasperPrint print = JasperFillManager.fillReport(archivojasper, new HashMap(), conexion);

            JasperExportManager.exportReportToPdfFile(print, "GraficoItinerario.pdf");

           
        } catch (JRException | NoClassDefFoundError | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "ERROR", JOptionPane.WARNING_MESSAGE);

            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//informe5
    //metodo para conseguir el directorio de la imagen
    public String imagenURL(String URL){
        String imagenURL=null;
        conectar();
        System.out.println(URL);
        String sql="SELECT FOTO FROM ITINERARIO  "
                + "WHERE '"+URL+"'=FOTO";
        try{
            resultSet = consulta.executeQuery(sql);      
           
            while(resultSet.next()){                  
                imagenURL= resultSet.getString(1);
            }
                    
            conexion.close();
            
        }catch(SQLException e){     
         
            JOptionPane.showMessageDialog(null, e.toString(), "No se puede encontrar la imagen en la ruta especificada.", JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
            return  imagenURL;
       
    }
}
