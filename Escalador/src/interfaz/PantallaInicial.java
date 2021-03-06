package interfaz;

import datos.Configuracion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import metodos.Metodos;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

public class PantallaInicial extends javax.swing.JFrame {

    Metodos metodos = new Metodos();

    public PantallaInicial() {
        initComponents();

        //this.
        //this.setResizable(false);
        setLocationRelativeTo(null);//abre la ventana en el centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/fotos/icono.png")).getImage());
        this.menuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/buscar.png")));
       // this.menuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/buscar.png")));
        mostrarUsuario();
        ponLaAyuda();
        ponElRendimiento();
        ponEnMarchaElHiloRendimiento();
    }
    
    private void ponElRendimiento() {
        double rendimiento = metodos.getRendimientoConfigurado();
        double redondeado = metodos.redondeoDosDecimales(rendimiento);
        etiquetaRendimiento.setText(Double.toString(redondeado));
    }

    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help" + File.separator + "help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // principal y secundaria.
            hb.enableHelpKey(getRootPane(), "principal", helpset);
            hb.enableHelpOnButton(this.btnAyuda, "configuracionnueva", helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarUsuario() {
        List<Configuracion> usuarioList = null;
        try {
            usuarioList = metodos.getUsuario();
            for (Configuracion c : usuarioList) {
                etiquetaEscalador.setText(c.getNombre() + " " + c.getApellido());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConfiguracionConsulta.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        etiquetaEscalador = new javax.swing.JLabel();
        etiquetaRendimiento = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuItemNuevo = new javax.swing.JMenu();
        menuItemNuevaConfiguracion = new javax.swing.JMenuItem();
        menuItemNuevoEntrenamiento = new javax.swing.JMenuItem();
        menuItemNuevoItinerario = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuConsulta = new javax.swing.JMenu();
        menuItemConsultaConfiguracion = new javax.swing.JMenuItem();
        menuItemConsultaEntrenamientos = new javax.swing.JMenuItem();
        menuItemConsultaItinerarios = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        INFORMES = new javax.swing.JMenu();
        btnINFORME1 = new javax.swing.JMenuItem();
        btnInformeEntrenamiento = new javax.swing.JMenuItem();
        EntrenamientoAgrupado = new javax.swing.JMenuItem();
        btnGraficoIti = new javax.swing.JMenuItem();
        btnGraficoEntrenamiento = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        btnAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rendimiento"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Escalador:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rendimiento:");

        etiquetaEscalador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiquetaEscalador.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaEscalador.setText(" ");

        etiquetaRendimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiquetaRendimiento.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaRendimiento.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaEscalador, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(etiquetaRendimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(etiquetaEscalador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(etiquetaRendimiento))
                .addContainerGap())
        );

        menuItemNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/anadir-lapiz.png"))); // NOI18N
        menuItemNuevo.setText("Nuevo            ");

        menuItemNuevaConfiguracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemNuevaConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/anadir-usuario1.png"))); // NOI18N
        menuItemNuevaConfiguracion.setText("Configuración");
        menuItemNuevaConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevaConfiguracionActionPerformed(evt);
            }
        });
        menuItemNuevo.add(menuItemNuevaConfiguracion);

        menuItemNuevoEntrenamiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemNuevoEntrenamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/editar-mapa.png"))); // NOI18N
        menuItemNuevoEntrenamiento.setText("Entrenamiento");
        menuItemNuevoEntrenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevoEntrenamientoActionPerformed(evt);
            }
        });
        menuItemNuevo.add(menuItemNuevoEntrenamiento);

        menuItemNuevoItinerario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemNuevoItinerario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/mapa-anadir.png"))); // NOI18N
        menuItemNuevoItinerario.setText("Itinerario");
        menuItemNuevoItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevoItinerarioActionPerformed(evt);
            }
        });
        menuItemNuevo.add(menuItemNuevoItinerario);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/fecha.png"))); // NOI18N
        jMenuItem1.setText("Itinerario Realizado");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuItemNuevo.add(jMenuItem1);

        jMenuBar1.add(menuItemNuevo);

        menuConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/file-search.png"))); // NOI18N
        menuConsulta.setText(" Consulta       ");

        menuItemConsultaConfiguracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemConsultaConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/anadir-la-tabla.png"))); // NOI18N
        menuItemConsultaConfiguracion.setText("Configuración");
        menuItemConsultaConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaConfiguracionActionPerformed(evt);
            }
        });
        menuConsulta.add(menuItemConsultaConfiguracion);

        menuItemConsultaEntrenamientos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuItemConsultaEntrenamientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/guardar-imagen.png"))); // NOI18N
        menuItemConsultaEntrenamientos.setText("Entrenamientos");
        menuItemConsultaEntrenamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaEntrenamientosActionPerformed(evt);
            }
        });
        menuConsulta.add(menuItemConsultaEntrenamientos);

        menuItemConsultaItinerarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuItemConsultaItinerarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/mostrar-la-lista.png"))); // NOI18N
        menuItemConsultaItinerarios.setText("Itinerarios");
        menuItemConsultaItinerarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConsultaItinerariosActionPerformed(evt);
            }
        });
        menuConsulta.add(menuItemConsultaItinerarios);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/mundo.png"))); // NOI18N
        jMenuItem2.setText("Itinerarios Realizados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuConsulta.add(jMenuItem2);

        jMenuBar1.add(menuConsulta);

        INFORMES.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        INFORMES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/documento-grafico.png"))); // NOI18N
        INFORMES.setText("Informes                 ");

        btnINFORME1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/el-informe.png"))); // NOI18N
        btnINFORME1.setText("Lista Itinerarios");
        btnINFORME1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnINFORME1ActionPerformed(evt);
            }
        });
        INFORMES.add(btnINFORME1);

        btnInformeEntrenamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/el-informe.png"))); // NOI18N
        btnInformeEntrenamiento.setText("Lista Entrenamiento");
        btnInformeEntrenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeEntrenamientoActionPerformed(evt);
            }
        });
        INFORMES.add(btnInformeEntrenamiento);

        EntrenamientoAgrupado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/el-informe.png"))); // NOI18N
        EntrenamientoAgrupado.setText("Entrenamiento por tipo");
        EntrenamientoAgrupado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrenamientoAgrupadoActionPerformed(evt);
            }
        });
        INFORMES.add(EntrenamientoAgrupado);

        btnGraficoIti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/el-informe.png"))); // NOI18N
        btnGraficoIti.setText("Grafico Itinerario por Dificultad");
        btnGraficoIti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoItiActionPerformed(evt);
            }
        });
        INFORMES.add(btnGraficoIti);

        btnGraficoEntrenamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/el-informe.png"))); // NOI18N
        btnGraficoEntrenamiento.setText("Grafico Entrenamiento Semanal/horas");
        btnGraficoEntrenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoEntrenamientoActionPerformed(evt);
            }
        });
        INFORMES.add(btnGraficoEntrenamiento);

        jMenuBar1.add(INFORMES);

        ayuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/ayuda-cursor.png"))); // NOI18N
        ayuda.setText("ayuda        ");

        btnAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/contenido-de-ayuda.png"))); // NOI18N
        btnAyuda.setText("ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        ayuda.add(btnAyuda);

        jMenuBar1.add(ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemNuevoItinerarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevoItinerarioActionPerformed

        ItinerarioNuevo itinerario = new ItinerarioNuevo(this, true, metodos);
        itinerario.setVisible(true);
    }//GEN-LAST:event_menuItemNuevoItinerarioActionPerformed

    private void menuItemNuevaConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevaConfiguracionActionPerformed

        ConfiguracionNueva configuracionNueva = new ConfiguracionNueva(null, true, metodos);
        configuracionNueva.setVisible(true);
    }//GEN-LAST:event_menuItemNuevaConfiguracionActionPerformed

    private void menuItemConsultaEntrenamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaEntrenamientosActionPerformed
        EntrenamientoConsulta entrenamientoConsulta = null;
        try {
            entrenamientoConsulta = new EntrenamientoConsulta(this, true, metodos);
        } catch (SQLException ex) {
            Logger.getLogger(PantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        entrenamientoConsulta.setVisible(true);
    }//GEN-LAST:event_menuItemConsultaEntrenamientosActionPerformed

    private void menuItemConsultaConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaConfiguracionActionPerformed
        ConfiguracionConsulta configuracion = new ConfiguracionConsulta(this, false, metodos);
        configuracion.setVisible(true);
    }//GEN-LAST:event_menuItemConsultaConfiguracionActionPerformed

    private void menuItemNuevoEntrenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevoEntrenamientoActionPerformed
        EntrenamientoNuevo entrenamientoNuevo = new EntrenamientoNuevo(this, true, metodos);
        entrenamientoNuevo.setVisible(true);
    }//GEN-LAST:event_menuItemNuevoEntrenamientoActionPerformed

    private void menuItemConsultaItinerariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConsultaItinerariosActionPerformed
        ItinerarioConsulta itinerarioConsulta = new ItinerarioConsulta(this, false, metodos);
        itinerarioConsulta.setVisible(true);
    }//GEN-LAST:event_menuItemConsultaItinerariosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ItinerarioFinNuevo itinerarioFinNuevo = new ItinerarioFinNuevo(this, true, metodos);
        itinerarioFinNuevo.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        ItinerarioFinConsulta itinerarioFinConsulta = new ItinerarioFinConsulta(this, false, metodos);
        itinerarioFinConsulta.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnINFORME1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnINFORME1ActionPerformed
        ListaItinerario info1 = new ListaItinerario(this, false, metodos);
        // SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceBinaryWatermark");
        info1.setVisible(true);
    }//GEN-LAST:event_btnINFORME1ActionPerformed

    private void btnInformeEntrenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeEntrenamientoActionPerformed
        ListaEntrenamiento info2 = new ListaEntrenamiento(this, false, metodos);
        info2.setVisible(true);
    }//GEN-LAST:event_btnInformeEntrenamientoActionPerformed

    private void EntrenamientoAgrupadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrenamientoAgrupadoActionPerformed
        ListaEntrenamientoAgrupado info4 = new ListaEntrenamientoAgrupado(this, false, metodos);
        info4.setVisible(true);
    }//GEN-LAST:event_EntrenamientoAgrupadoActionPerformed

    private void btnGraficoItiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoItiActionPerformed
        metodos.informe5();
        File path = new File("GraficoItinerario.pdf");//referencia compruebo q existe lo puedo abrir en cualquier parete del proyecto
        try {

            Desktop.getDesktop().open(path);//abre ese pdf
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "No exite el archivo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnGraficoItiActionPerformed

    private void btnGraficoEntrenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoEntrenamientoActionPerformed
        GraficoEntreamientoSemanal info3 = new GraficoEntreamientoSemanal(this, true, metodos);
        info3.setVisible(true);
    }//GEN-LAST:event_btnGraficoEntrenamientoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        JFrame.setDefaultLookAndFeelDecorated(true);//permite a la libreria substance decorar
        // SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin");//sentencia q aplica skin
        //skins;BusinessBlackSteelSkin,
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteSkin");//sentencia q aplica skin
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceEbonyTheme");
        //        //"./src/fotos/climbing1.jpg"

        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("./fotos/climbing1.jpg"));//marca de agua
        SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.4));//opacidad de marca de agua

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaInicial().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EntrenamientoAgrupado;
    private javax.swing.JMenu INFORMES;
    private javax.swing.JMenu ayuda;
    private javax.swing.JMenuItem btnAyuda;
    private javax.swing.JMenuItem btnGraficoEntrenamiento;
    private javax.swing.JMenuItem btnGraficoIti;
    private javax.swing.JMenuItem btnINFORME1;
    private javax.swing.JMenuItem btnInformeEntrenamiento;
    private javax.swing.JLabel etiquetaEscalador;
    private javax.swing.JLabel etiquetaRendimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuConsulta;
    private javax.swing.JMenuItem menuItemConsultaConfiguracion;
    private javax.swing.JMenuItem menuItemConsultaEntrenamientos;
    private javax.swing.JMenuItem menuItemConsultaItinerarios;
    private javax.swing.JMenuItem menuItemNuevaConfiguracion;
    private javax.swing.JMenu menuItemNuevo;
    private javax.swing.JMenuItem menuItemNuevoEntrenamiento;
    private javax.swing.JMenuItem menuItemNuevoItinerario;
    // End of variables declaration//GEN-END:variables

    private void ponEnMarchaElHiloRendimiento() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ponElRendimiento();
                        ponElNombreDelEscalador();
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        };
        t.start();
    }

    private void ponElNombreDelEscalador() {
        this.etiquetaEscalador.setText(metodos.getNombreYApellidoEscalador());
    }
}
