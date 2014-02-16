package metodos;

import datos.Entrenamiento;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
            
            conexion = DriverManager.getConnection("jdbc:hsqldb:file:/database/escalada","sa","");//aqui
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Entrenamiento> obtenerListaEntrenamientos() throws SQLException {
        
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
     * @param tipo
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param descripcion
     * @return true si pudo insertar el nuevo entrenamiento, o false si no pudo.
     */
    public boolean insertarEntrenamientoEnDB(String tipo, String fecha, String horaInicio, String horaFin, String descripcion) {
        boolean pudoInsertarse = false;
        conectar();
        String sql = "INSERT INTO entrenamiento (tipo, fecha, hora_inicio, hora_fin, descripcion) "
                + "VALUES ('" + tipo + "', '" + fecha + "', '" + horaInicio
                + "', '" + horaFin + "', '" + descripcion + "')";
        try {
            consulta.executeUpdate(sql);
            pudoInsertarse = true;
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pudoInsertarse;
    }

    //falta itinerarios
//public List<Entrenamiento> obtenerListaItinerarios() {
//        String sql = "select * from entrenamiento";
//        ResultSet resultSet;
//        conectar();
//        Entrenamiento entrenamiento;
//        List<Itinerario> listaIter = new ArrayList<>();
//        try {
//            resultSet = consulta.executeQuery(sql);
//            while (resultSet.next()) {
//                entrenamiento = new Entrenamiento(
//                        resultSet.getInt(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6)
//                );
//                listaEntrenamientos.add(entrenamiento);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error SQL.");
//        } finally {
//            try {
//                conexion.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return listaEntrenamientos;
//    }
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

    //Comprueba que las cajas de textos de las fechas solo tengan fechas en formato dd/MM/yyyy y que no esten vacios
    public boolean comprobarFecha(String texto) {
        boolean valido = false;
        if (texto.length() == 0) {
            valido = false;
        } else {
            Pattern patron = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
            Matcher m = patron.matcher(texto);
            if (m.matches()) {
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Comprueba que las palabras sólo contienen letras.
     *
     * @param texto
     * @return
     */
    public boolean comprobarLetras(String texto) {
        if (texto.length() == 0) {
            return false;
        }
        return texto.matches("[a-zA-Z]*");
    }

    // Comprueba que la hora se componga de dos pares de dígitos separados por el caracter dos puntos.
    public boolean comprobarHora(String hora) {
        return hora.matches("\\d\\d:\\d\\d:\\d\\d");
        //para meter los segundos añadir +00 tras el getText();
    }
    
    public void rellenarTablaEntrenamiento(JTable tablaEntrenamientos) {
        DefaultTableModel model = (DefaultTableModel) tablaEntrenamientos.getModel();
        String sql = "select * from ENTRENAMIENTO";
        ResultSet res;
        conectar();
        try {
            res = consulta.executeQuery(sql);
            
            while (res.next()) {
                model.addRow(new Object[]{
                    null,//res.getDate(1),
                    null,//res.getTime(2),
                    null,//res.getTime(3),
                    res.getString(4),
                    res.getString(5)
                });
            }            
        } catch (SQLException e) {
            System.out.println("Error");
        }
        
    }
    
    public void insertarConfigEnDB(String nombre, String apellido, String fecha, String fechaFin) {
        
    }

}
