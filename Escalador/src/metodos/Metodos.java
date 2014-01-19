package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    public void rellenarTabla(JTable jTable1) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String sql = "select * from entrenamiento";
        ResultSet res;
        conectar();
        try {
            res = consulta.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2)});
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

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
        conectar();
        String sql = "insert into escalador(nombre,apellido,fecha_inicio,fecha_fin) values('" + nombre + "','" + apellido
                + "','" + fecha + "','" + fechaf + "')";
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
