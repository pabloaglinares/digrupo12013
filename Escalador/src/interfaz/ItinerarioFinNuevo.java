package interfaz;

import java.awt.Color;
import java.awt.Dialog;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import metodos.Metodos;

public class ItinerarioFinNuevo extends javax.swing.JDialog {

    Metodos metodos;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Date FechaActual = new Date();
    String fecha = dateFormat.format(FechaActual);
    Timestamp fech = new Timestamp(FechaActual.getTime()),oldFecha;
    boolean fechaModificada = false, edicion = false;
    int oldId;

    /**
     * Creates new form ItinerarioFinNuevo
     *
     * @param parent
     * @param modal
     * @param metodos
     */
    public ItinerarioFinNuevo(java.awt.Frame parent, boolean modal, Metodos metodos) {
        super(parent, modal);
        initComponents();
        this.metodos = metodos;
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        this.setTitle("Itinerario terminado");
        rellenarComboBox();
//        metodos.mostrarNombreIti(jComboBox1);
        jTextField1.setText(fecha);
        ponLaAyuda();
    }

    public ItinerarioFinNuevo(javax.swing.JDialog parent, boolean modal, Metodos metodos, String nombre, String fecha, int id) {
        super(parent, modal);
        initComponents();
        this.metodos = metodos;
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        this.setTitle("Itinerario terminado");
        rellenarComboBox();
//        FechaActual=
        jComboBox1.setSelectedItem(nombre);
        jTextField1.setText(fecha);
        oldId = id;
        oldFecha = Timestamp.valueOf(fecha);
        edicion = true;
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

            hb.enableHelpKey(getRootPane(), "itinerariorealizadonuevo", helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void rellenarComboBox() {
        List<String> nombresItinerarios = metodos.getListaNombresDeItinerarios();
        for (String nombre : nombresItinerarios) {
            jComboBox1.addItem(nombre);
        }
    }

    private void comprobarFecha() {
        try {
            Date fechafin = (Date) dateFormat.parse(fecha);
            if (jTextField1.getText().length() == 15) {
                fech = new Timestamp(fechafin.getTime());
                jTextField1.setForeground(Color.white);
            } else {
                jTextField1.setForeground(Color.red);
            }
        } catch (ParseException ex) {
            jTextField1.setForeground(Color.red);
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
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo itinerario realizado"));

        jLabel1.setText("Itinerario");

        jLabel2.setText("Fecha Fin");

        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnGuardar.setText("Alta");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        fechaModificada = true;
        fecha = jTextField1.getText();
        comprobarFecha();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked

    }//GEN-LAST:event_jTextField1MouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (jTextField1.getForeground() == Color.white || fechaModificada == false) {
            String id = jComboBox1.getSelectedItem().toString();
            int idItinerario = metodos.getIdItinerario(id);
            if (edicion == false) {
                metodos.insertItinerarioFin(idItinerario, fech);
            }else{
                metodos.updateItiFin(idItinerario, fech, oldId, oldFecha);
            }
            this.dispose();
        } else {
            jTextField1.setText(null);
            JOptionPane.showMessageDialog(this, "Error en los datos\nLa fecha tiene el formato:\n"
                    + "31-12-1999 23:59");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
