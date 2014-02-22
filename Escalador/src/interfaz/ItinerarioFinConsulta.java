package interfaz;

import datos.ItinerarioFin;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metodos.Metodos;
import org.jvnet.substance.SubstanceLookAndFeel;

public class ItinerarioFinConsulta extends javax.swing.JDialog {

    Metodos metodos;
    DefaultTableModel defaultTableModel;

    public ItinerarioFinConsulta(java.awt.Frame parent, boolean modal, Metodos metodos) {
        super(parent, modal);
        initComponents();
        this.metodos = metodos;
        SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceKatakanaWatermark");
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        this.setTitle("Consulta itinerarios realizados");
        tabla();
        rellenarTabla();
    }

    private void tabla() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Fecha");
        tablaItinerarioFin.setModel(defaultTableModel);

    }

    private void rellenarTabla() {
        List<ItinerarioFin> listaItinerarioFin = null;

        try {
            listaItinerarioFin = metodos.getListaItinerFin();

            String nombre, fecha;

            for (ItinerarioFin e : listaItinerarioFin) {
                nombre = e.getNombre();
                fecha = e.getFecha().toString();
                String[] fila = {nombre, fecha};
                defaultTableModel.addRow(fila);
            }
            //formatear todos los datos a String

            //defaultTableModel.addRow(fila);
        } catch (SQLException ex) {
            System.out.println("Error SQL.");
            Logger.getLogger(EntrenamientoConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vaciarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaItinerarioFin.getModel();
        int filas = tablaItinerarioFin.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaItinerarioFin = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        buttonFoto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaItinerarioFin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaItinerarioFin);

        jButton1.setText("salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonFoto.setText("ver foto");
        buttonFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFotoActionPerformed(evt);
            }
        });

        jButton3.setText("borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3)
                        .addGap(35, 35, 35)
                        .addComponent(jButton4)
                        .addGap(35, 35, 35)
                        .addComponent(buttonFoto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(buttonFoto)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFotoActionPerformed
//        int filaSeleccionada=tablaItinerarioFin.getSelectedRow();
//        String nombre = tablaItinerarioFin.getValueAt(filaSeleccionada,0).toString();
//        String URL = metodos.imagenURL(nombre);
        String URL="\\awesome.png";//Esto se quita, es solo de prueba :D
        
        ImagenDialog ImagenDialog = new ImagenDialog(null, true, URL);
        ImagenDialog.setVisible(true);
        
    }//GEN-LAST:event_buttonFotoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int i = tablaItinerarioFin.getSelectedRow();
            int id = metodos.getIdItinerario(tablaItinerarioFin.getValueAt(i, 0).toString());
            Timestamp fecha = Timestamp.valueOf(tablaItinerarioFin.getValueAt(i, 1).toString());
            metodos.deleteItinerarioFin(id, fecha);
            vaciarTabla();
            rellenarTabla();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Seleciona la fila de la tabla que desees borrar");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int i = tablaItinerarioFin.getSelectedRow();
            int id = metodos.getIdItinerario(tablaItinerarioFin.getValueAt(i, 0).toString());
            Timestamp fecha = Timestamp.valueOf(tablaItinerarioFin.getValueAt(i, 1).toString());
            ItinerarioFinNuevo itfin = new ItinerarioFinNuevo(this, rootPaneCheckingEnabled, metodos,
                    tablaItinerarioFin.getValueAt(i, 0).toString(),
                    tablaItinerarioFin.getValueAt(i, 1).toString(),
                    id);
            itfin.setVisible(true);
            vaciarTabla();
            rellenarTabla();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Seleciona la fila de la tabla que desees borrar");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaItinerarioFin;
    // End of variables declaration//GEN-END:variables
}
