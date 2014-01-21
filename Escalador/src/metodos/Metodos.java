package metodos;

import datos.Entrenamiento;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metodos {

    Connection conexion;
    Statement consulta;
    public String ruta;

    /**
     * Constructor for objects of class Conexion
     */
    public Metodos() {
        ruta = System.getProperty("user.dir");
    }

    public void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + ruta + "/escalada.db");//aqui
            consulta = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Entrenamiento> obtenerListaEntrenamientos() {
        String sql = "SELECT * FROM entrenamiento";
        ResultSet resultSet;
        conectar();
        Entrenamiento entrenamiento;
        List<Entrenamiento> listaEntrenamientos = new ArrayList<>();
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                entrenamiento = new Entrenamiento(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
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
    }
    
    /**
     * Inserta un nuevo entrenamiento en la BD y lo confirma con un booleano.
     * @param tipo
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param descripcion
     * @return  true si pudo insertar el nuevo entrenamiento, o false si no pudo.
     */
    public boolean insertarEntrenamientoEnDB(String tipo, String fecha, String horaInicio, String horaFin, String descripcion) {
        boolean pudoInsertarse = false;
        conectar();
        String sql = "INSERT INTO entrenamiento (tipo, fecha, hora_inicio, hora_fin, descripcion) " +
                     "VALUES ('" + tipo + "', '" + fecha + "', '" + horaInicio + 
                     "', '" + horaFin + "', '" + descripcion + "')";
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

    
    /*
    
    public void rellenarTablaEscalador(JTable tablaEscalador) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Apellidos");
        defaultTableModel.addColumn("fecha inicio");
        defaultTableModel.addColumn("fecha fin");
        tablaEscalador.setModel(defaultTableModel);
        String sql = "select * from escalador";
        ResultSet res;
        conectar();
        try {
            res = consulta.executeQuery(sql);

            while (res.next()) {
                defaultTableModel.addRow(new Object[]{res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5)});
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public void vaciarTabla(JTable jTable1) {

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        int filas = jTable1.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    public void insertarTablaEscalador(String nombre, String apellido, String fecha, String fechaf) {

        if (comprobarTextos(nombre) && comprobarTextos(apellido) && comprobarFecha(fecha) && comprobarFecha(fechaf)) {

//             SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");//dia/mes/año
//             try {
//             Date fechaInicio = formatoFecha.parse(fecha);
//             Date FechaFin = formatoFecha.parse(fechaf);
//                
//             } catch (ParseException ex) {
//               
//             }
            conectar();
            String sql = "insert into escalador(nombre,apellido,fecha_inicio,fecha_fin) values('" + nombre + "','" + apellido
                    + "','" + fecha + "','" + fechaf + "')";
            try {
                consulta.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente", "Escalador", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.getMessage();
                JOptionPane.showMessageDialog(null, "Fallo al insertar registro", "Escalador", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } //fin if
    }// fin InsertarTablaEscalador

    */
    
    

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
            Pattern patron = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d");
            Matcher m = patron.matcher(texto);
            if (m.matches()) {
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Comprueba que las palabras sólo contienen letras.
     * @param texto
     * @return 
     */
    public boolean comprobarLetras(String texto) {
        if(texto.length() == 0) {
            return false;
        }
        return texto.matches("[a-zA-Z]*");
    }
    
    // Comprueba que la hora se componga de dos pares de dígitos separados por el caracter dos puntos.
    public boolean comprobarHora(String hora) {
        return hora.matches("\\d\\d:\\d\\d");
    }
}
