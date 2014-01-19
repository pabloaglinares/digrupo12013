package metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void vaciarTabla(JTable jTable1) {

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        int filas = jTable1.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

}
