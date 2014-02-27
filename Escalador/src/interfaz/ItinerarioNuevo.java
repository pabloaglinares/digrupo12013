package interfaz;

import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import metodos.Metodos;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;

public class ItinerarioNuevo extends javax.swing.JDialog {

    Metodos metodos;
    boolean edicion = false;
    int id;

    public ItinerarioNuevo(java.awt.Frame parent, boolean modal, Metodos metodos) {
        super(parent, modal);
        this.setResizable(false);
        this.metodos = metodos;
        //this.alta.setEnabled(false);
        initComponents();
        this.alta.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.setTitle("Nuevo itinerario");
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        ponLaAyuda();
    }

    //constructor editar
    public ItinerarioNuevo(javax.swing.JDialog parent, boolean modal, Metodos metodos, String nombre, String loca, String iti, String dific, String foto, int id) {
        super(parent, modal);
        initComponents();
        this.metodos = metodos;
        this.nombre.setText(nombre);
        this.localizacion.setText(loca);
        this.itinerario.setSelectedItem(iti);
//        String d= dific;
//        this.dificultadnumero.setSelectedItem(d.subSequence(WIDTH, WIDTH));
        this.rutafoto.setText(foto);
        this.id = id;
        this.edicion = true;
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

            hb.enableHelpKey(getRootPane(), "itinerarionuevo", helpset);

        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        localizacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        itinerario = new javax.swing.JComboBox();
        dificultadnumero = new javax.swing.JComboBox();
        dificultadletra = new javax.swing.JComboBox();
        dificultadsimbolo = new javax.swing.JComboBox();
        rutafoto = new javax.swing.JTextField();
        cargafoto = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        salir = new javax.swing.JButton();
        alta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Nombre Itinerario:");

        jLabel3.setText("Localizacion:");

        jLabel4.setText("Tipo Itinerario:");

        jLabel5.setText("Dificultad:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fotografia");

        itinerario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Via de Escalada", "Boulder" }));

        dificultadnumero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        dificultadnumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificultadnumeroActionPerformed(evt);
            }
        });

        dificultadletra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "b", "c" }));

        dificultadsimbolo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "+", "-" }));

        cargafoto.setText("Cargar");
        cargafoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargafotoActionPerformed(evt);
            }
        });

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/salir-8.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        alta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/guardar.png"))); // NOI18N
        alta.setText("Alta");
        alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rutafoto)
                        .addGap(18, 18, 18)
                        .addComponent(cargafoto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(localizacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itinerario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(dificultadnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dificultadletra, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dificultadsimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(itinerario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dificultadnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dificultadletra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dificultadsimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rutafoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargafoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(alta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargafotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargafotoActionPerformed

        JFileChooser foto = new JFileChooser();
        foto.setDialogTitle("Selecciona una foto");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        foto.setFileFilter(filter);
        int returnVal = foto.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //rutafoto.setText(foto.getSelectedFile().getName());
            rutafoto.setText(foto.getSelectedFile().getAbsolutePath());
//            File origen = foto.getSelectedFile();
//            File destino = new File("fotos//" + foto.getSelectedFile().getName());
//            metodos.copiarFotografia(origen, destino);

        }

    }//GEN-LAST:event_cargafotoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed

        this.dispose();

    }//GEN-LAST:event_salirActionPerformed

    private void altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaActionPerformed
        String nombreIti = nombre.getText();
        String loca = localizacion.getText();
        String iti = itinerario.getSelectedItem().toString();
        String dific = dificultadnumero.getSelectedItem().toString() + dificultadletra.getSelectedItem().toString() + dificultadsimbolo.getSelectedItem().toString();
        String foto = rutafoto.getText();

        boolean insercionCorrecta = false;

        if (edicion == false) {
            insercionCorrecta = metodos.insertarItinerarioEnDb(nombreIti, loca, iti, dific, foto);
        } else {
            insercionCorrecta = metodos.updateItinerario(nombreIti, loca, iti, dific, foto, id);
        }

        if (insercionCorrecta) {
            this.dispose();
        } else {

            JOptionPane.showMessageDialog(this, "Error: No se ha insertado el itinerario.");
        }

    }//GEN-LAST:event_altaActionPerformed

    private void dificultadnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dificultadnumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dificultadnumeroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alta;
    private javax.swing.JButton cargafoto;
    private javax.swing.JComboBox dificultadletra;
    private javax.swing.JComboBox dificultadnumero;
    private javax.swing.JComboBox dificultadsimbolo;
    private javax.swing.JComboBox itinerario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField localizacion;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField rutafoto;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
