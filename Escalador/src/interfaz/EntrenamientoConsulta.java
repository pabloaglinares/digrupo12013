package interfaz;

import datos.Entrenamiento;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metodos.Metodos;
import org.jvnet.substance.SubstanceLookAndFeel;

public class EntrenamientoConsulta extends javax.swing.JDialog {

    Metodos metodos;
    DefaultTableModel defaultTableModel;
    TableRowSorter<TableModel> filtro;

    public EntrenamientoConsulta(java.awt.Frame parent, boolean modal, Metodos metodos) throws SQLException {
        super(parent, modal);
        this.setResizable(false);
        this.metodos = metodos;
        SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark");
        initComponents();
        tabla();
        rellenarTabla();
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        this.setTitle("Consulta entrenamientos realizados");
        //vaciarTabla();  
        ponLaAyuda();
    }

    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help" + File.separator + "help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            hb.enableHelpKey(getRootPane(), "entrenamientoconsulta", helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tabla() throws SQLException {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Fecha");
        defaultTableModel.addColumn("Hora inicio");
        defaultTableModel.addColumn("Hora fin");
        defaultTableModel.addColumn("Tipo");
        defaultTableModel.addColumn("Descripcion");
        filtro = new TableRowSorter<TableModel>(defaultTableModel);
        tablaEntrenamientos.setModel(defaultTableModel);

    }

    private void rellenarTabla() {
        List<Entrenamiento> listaEntrenamientos = null;
        try {
            listaEntrenamientos = metodos.getListaEntrenamientos();

            String tipo, descripcion, horaInicio, horaFin, fecha;

            for (Entrenamiento e : listaEntrenamientos) {
                fecha = e.getFecha().toString();
                tipo = e.getTipo();
                descripcion = e.getDescripcion();
                horaInicio = e.getHoraComienzo().toString();
                horaFin = e.getHoraFin().toString();
                String[] fila = {fecha, horaInicio, horaFin, tipo, descripcion};

                defaultTableModel.addRow(fila);
            }

        } catch (SQLException ex) {
            System.out.println("Error SQL.");
            Logger.getLogger(EntrenamientoConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vaciarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaEntrenamientos.getModel();
        int filas = tablaEntrenamientos.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    private void rellenarTablaFiltrada() {

        List<Entrenamiento> listaEntrenamientos = null;
        Date fechai = null, fechaf = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (!"".equals(textFecha_i.getText()) && textFecha_i.getText()!=null) {
                fechai = (Date) sdf.parse(textFecha_i.getText());
            }
            if(!"".equals(textFecha_F.getText()) && textFecha_F.getText()!=null){
                fechaf= (Date) sdf.parse(textFecha_F.getText());
            }

            listaEntrenamientos = metodos.pasarFiltro(comboTipo.getSelectedItem().toString(), fechai, fechaf);;

            String tipo, descripcion, horaInicio, horaFin, fecha;

            for (Entrenamiento e : listaEntrenamientos) {
                fecha = e.getFecha().toString();
                tipo = e.getTipo();
                descripcion = e.getDescripcion();
                horaInicio = e.getHoraComienzo().toString();
                horaFin = e.getHoraFin().toString();
                String[] fila = {fecha, horaInicio, horaFin, tipo, descripcion};

                defaultTableModel.addRow(fila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "error en el formato de la fecha\n29/12/2014");
            rellenarTabla();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntrenamientos = new javax.swing.JTable();
        botonSalir = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        comboTipo = new javax.swing.JComboBox();
        textFecha_i = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textFecha_F = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buttonFiltro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrenamientos"));

        tablaEntrenamientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaEntrenamientos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipo", "Físico", "Rocódromo", "Roca" }));

        jLabel1.setText("Desde");

        jLabel2.setText("Hasta");

        buttonFiltro.setText("Filtrar");
        buttonFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFecha_i, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFecha_F, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFecha_i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(textFecha_F, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFiltro))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(botonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed

        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        try {

            int i = tablaEntrenamientos.getSelectedRow();
            EntrenamientoNuevo entrenaNew = new EntrenamientoNuevo(
                    this,
                    true,
                    metodos,
                    tablaEntrenamientos.getValueAt(i, 4).toString(),
                    tablaEntrenamientos.getValueAt(i, 0).toString(),
                    tablaEntrenamientos.getValueAt(i, 2).toString(),
                    tablaEntrenamientos.getValueAt(i, 1).toString(),
                    tablaEntrenamientos.getValueAt(i, 3).toString(),
                    true,
                    tablaEntrenamientos.getValueAt(i, 4).toString());
            entrenaNew.setVisible(true);
            vaciarTabla();
            rellenarTabla();

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Seleciona la fila de la tabla que desees editar");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        try {
            int i = tablaEntrenamientos.getSelectedRow();
            metodos.deleteEntrenamiento(tablaEntrenamientos.getValueAt(i, 4).toString());
            vaciarTabla();
            rellenarTabla();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Seleciona la fila de la tabla que desees borrar");
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void buttonFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFiltroActionPerformed

        vaciarTabla();
        rellenarTablaFiltrada();

    }//GEN-LAST:event_buttonFiltroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton buttonFiltro;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEntrenamientos;
    private javax.swing.JTextField textFecha_F;
    private javax.swing.JTextField textFecha_i;
    // End of variables declaration//GEN-END:variables
}
