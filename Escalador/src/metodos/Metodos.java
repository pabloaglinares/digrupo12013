package metodos;

import datos.Configuracion;
import datos.Entrenamiento;
import datos.Itinerario;
import datos.ItinerarioFin;
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
            
            conexion = DriverManager.getConnection("jdbc:hsqldb:file:database/escalada", "sa", "");//aqui
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Itinerario> obtenerListaItinerarios() {
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
    
    public List<ItinerarioFin> obtenerListaItinerFin() throws SQLException {
        ItinerarioFin itinerarioFin;
        List<ItinerarioFin> listaItinerarioFin = new ArrayList<>();
        conectar();
        resultSet = consulta.executeQuery("select IT.NOMBRE,FI.FECHA from FECHA_ITINERARIO FI,ITINERARIO IT ORDER BY FECHA");
        
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
        return texto.matches("[a-zA-Z]*");
    }
    
    public boolean comprobarNoEsCadenaVacia(String texto) {
        return "".equals(texto);
    }

    //Este método se encarga de validar que el texto contenga sólo letras o números.
    public boolean validarTextoNumeros(String texto) {
        return texto.matches("[a-zA-Z\\d]*");
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
    
    public List<Configuracion> obtenerUsuario() throws SQLException {
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
        String sql = "UPDATE PUBLIC.ESCALADOR SET NOMBRE = '" + nombre + "',APELLIDO = '" + apellido + "',"
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
    
    public boolean insertarItinerarioEnDb(String nombre, String localizacion, String tipo, String dificultad, String fecha, String foto) {
        boolean pudoInsertarse;
        String sql = "INSERT INTO ITINERARIO";
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
    
}
