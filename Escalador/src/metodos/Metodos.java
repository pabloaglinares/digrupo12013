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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;

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

    public void deleteItinerario(String nombre, String localizacion) {
        String sql = "DELETE FROM ITINERARIO WHERE NOMBRE='" + nombre + "', LOCALIZACION='" + localizacion + "'";
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

    public void mostrarNombreIti(JComboBox j) {
        conectar();
        String sql = "select NOMBRE FROM ITINERARIO";
        try {
            resultSet = consulta.executeQuery(sql);
            while (resultSet.next()) {
                j.addItem(resultSet.getString(1));
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
}
