package ui.domotica.home;

import java.awt.Color;
import ui.Main;
import ui.Modelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class VistaHome extends javax.swing.JFrame {

    private ControladorHome controlador;
    private Modelo modelo;

    public VistaHome() {
        initComponents();
        modelo = Main.getStateMachineDomotica().getModelo();

        controlador = new ControladorHome(this, modelo);
        actualizaInformacion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabelTituloHome = new javax.swing.JLabel();
        jPanelInfo = new javax.swing.JPanel();
        jPanelCalefaccion = new javax.swing.JPanel();
        jLabelTituloCalefaccion = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabelCalefaccionEncendida = new javax.swing.JLabel();
        jLabelTemperatura = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelLuces = new javax.swing.JPanel();
        jLabelLuz2 = new javax.swing.JLabel();
        jLabelLuz3 = new javax.swing.JLabel();
        jLabelLuz4 = new javax.swing.JLabel();
        jLabelLuz5 = new javax.swing.JLabel();
        jLabelLuz1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelTituloLuces = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelPersianas = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabelTituloPersianas = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelPersiana1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabelPersiana2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelBotones = new javax.swing.JPanel();
        jButtonCalefaccion = new javax.swing.JButton();
        jButtonLuz = new javax.swing.JButton();
        jButtonPersiana = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabelTituloHome.setBackground(new java.awt.Color(138, 175, 248));
        jLabelTituloHome.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloHome.setText("Home");
        jLabelTituloHome.setOpaque(true);
        jLabelTituloHome.setPreferredSize(new java.awt.Dimension(51, 60));
        jPanel4.add(jLabelTituloHome, java.awt.BorderLayout.CENTER);

        jPanelInfo.setPreferredSize(new java.awt.Dimension(650, 358));
        jPanelInfo.setLayout(new java.awt.GridLayout(1, 0));

        jPanelCalefaccion.setMinimumSize(new java.awt.Dimension(220, 300));
        jPanelCalefaccion.setPreferredSize(new java.awt.Dimension(220, 300));

        jLabelTituloCalefaccion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTituloCalefaccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloCalefaccion.setText("Calefacción");
        jLabelTituloCalefaccion.setToolTipText("");

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabelCalefaccionEncendida.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelCalefaccionEncendida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCalefaccionEncendida.setText("OFF");

        jLabelTemperatura.setFont(new java.awt.Font("Dialog", 1, 72)); // NOI18N
        jLabelTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTemperatura.setText("32º");

        javax.swing.GroupLayout jPanelCalefaccionLayout = new javax.swing.GroupLayout(jPanelCalefaccion);
        jPanelCalefaccion.setLayout(jPanelCalefaccionLayout);
        jPanelCalefaccionLayout.setHorizontalGroup(
            jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalefaccionLayout.createSequentialGroup()
                .addGroup(jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCalefaccionLayout.createSequentialGroup()
                        .addGroup(jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTituloCalefaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCalefaccionLayout.createSequentialGroup()
                        .addGroup(jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCalefaccionEncendida, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(jLabelTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanelCalefaccionLayout.setVerticalGroup(
            jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalefaccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCalefaccionLayout.createSequentialGroup()
                        .addComponent(jSeparator10)
                        .addContainerGap())
                    .addGroup(jPanelCalefaccionLayout.createSequentialGroup()
                        .addComponent(jLabelTituloCalefaccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabelTemperatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jLabelCalefaccionEncendida)
                        .addGap(26, 26, 26))))
        );

        jPanelInfo.add(jPanelCalefaccion);

        jPanelLuces.setMinimumSize(new java.awt.Dimension(220, 300));
        jPanelLuces.setPreferredSize(new java.awt.Dimension(220, 300));
        jPanelLuces.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLuz2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLuz2.setText("jLabel7");
        jLabelLuz2.setPreferredSize(new java.awt.Dimension(220, 17));
        jPanelLuces.add(jLabelLuz2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 90, 50));

        jLabelLuz3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLuz3.setText("jLabel8");
        jLabelLuz3.setPreferredSize(new java.awt.Dimension(220, 17));
        jPanelLuces.add(jLabelLuz3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 90, 50));

        jLabelLuz4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLuz4.setText("jLabel9");
        jLabelLuz4.setPreferredSize(new java.awt.Dimension(220, 17));
        jPanelLuces.add(jLabelLuz4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 90, 50));

        jLabelLuz5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLuz5.setText("jLabel10");
        jLabelLuz5.setPreferredSize(new java.awt.Dimension(220, 17));
        jPanelLuces.add(jLabelLuz5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 200, 50));

        jLabelLuz1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLuz1.setText("jLabel6");
        jLabelLuz1.setPreferredSize(new java.awt.Dimension(220, 17));
        jPanelLuces.add(jLabelLuz1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 50));

        jSeparator7.setPreferredSize(new java.awt.Dimension(98, 10));
        jPanelLuces.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 125, 99, -1));
        jPanelLuces.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 40, 208, -1));

        jLabelTituloLuces.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTituloLuces.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloLuces.setText("Luces");
        jLabelTituloLuces.setToolTipText("");
        jPanelLuces.add(jLabelTituloLuces, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 12, 220, -1));

        jSeparator6.setPreferredSize(new java.awt.Dimension(98, 10));
        jPanelLuces.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 125, 99, -1));

        jSeparator4.setName(""); // NOI18N
        jSeparator4.setPreferredSize(new java.awt.Dimension(1, 1));
        jPanelLuces.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 215, 208, 10));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setPreferredSize(new java.awt.Dimension(2, 2));
        jPanelLuces.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 45, -1, 75));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setPreferredSize(new java.awt.Dimension(1, 1));
        jPanelLuces.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 10, 80));

        jLabel3.setText("Luz 1:");
        jPanelLuces.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setText("Luz 2:");
        jPanelLuces.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel5.setText("Luz 3:");
        jPanelLuces.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 135, -1, -1));

        jLabel6.setText("Luz 4:");
        jPanelLuces.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 135, -1, -1));

        jLabel7.setText("Luz 5:");
        jPanelLuces.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 225, 50, -1));

        jPanelInfo.add(jPanelLuces);

        jPanelPersianas.setMinimumSize(new java.awt.Dimension(220, 300));
        jPanelPersianas.setPreferredSize(new java.awt.Dimension(220, 300));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabelTituloPersianas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTituloPersianas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloPersianas.setText("Persianas");
        jLabelTituloPersianas.setToolTipText("");

        jLabelPersiana1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelPersiana1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPersiana1.setText("Subida");

        jLabelPersiana2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelPersiana2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPersiana2.setText("Subida");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Persiana 1:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Persiana 2:");

        javax.swing.GroupLayout jPanelPersianasLayout = new javax.swing.GroupLayout(jPanelPersianas);
        jPanelPersianas.setLayout(jPanelPersianasLayout);
        jPanelPersianasLayout.setHorizontalGroup(
            jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTituloPersianas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelPersianasLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPersianasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator5)
                    .addGroup(jPanelPersianasLayout.createSequentialGroup()
                        .addGroup(jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPersiana2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelPersiana1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelPersianasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 88, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPersianasLayout.createSequentialGroup()
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 218, Short.MAX_VALUE)))
        );
        jPanelPersianasLayout.setVerticalGroup(
            jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersianasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTituloPersianas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabelPersiana1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabelPersiana2)
                .addGap(26, 26, 26))
            .addGroup(jPanelPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersianasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanelInfo.add(jPanelPersianas);

        jPanelBotones.setMinimumSize(new java.awt.Dimension(650, 40));
        jPanelBotones.setPreferredSize(new java.awt.Dimension(650, 53));
        jPanelBotones.setLayout(new java.awt.GridLayout(1, 3, 12, 0));

        jButtonCalefaccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/assets/calefaccion.png"))); // NOI18N
        jButtonCalefaccion.setMaximumSize(new java.awt.Dimension(210, 40));
        jButtonCalefaccion.setPreferredSize(new java.awt.Dimension(210, 40));
        jButtonCalefaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalefaccionActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonCalefaccion);

        jButtonLuz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/assets/luz.png"))); // NOI18N
        jButtonLuz.setMaximumSize(new java.awt.Dimension(210, 40));
        jButtonLuz.setPreferredSize(new java.awt.Dimension(210, 40));
        jButtonLuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLuzActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonLuz);

        jButtonPersiana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/assets/persiana.png"))); // NOI18N
        jButtonPersiana.setToolTipText("");
        jButtonPersiana.setMaximumSize(new java.awt.Dimension(210, 40));
        jButtonPersiana.setPreferredSize(new java.awt.Dimension(210, 40));
        jButtonPersiana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPersianaActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonPersiana);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLuzActionPerformed
        controlador.luz();
    }//GEN-LAST:event_jButtonLuzActionPerformed

    private void jButtonPersianaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPersianaActionPerformed
        controlador.persiana();
    }//GEN-LAST:event_jButtonPersianaActionPerformed

    private void jButtonCalefaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalefaccionActionPerformed
        controlador.calefaccion();
    }//GEN-LAST:event_jButtonCalefaccionActionPerformed
    /*
    Actualiza todas las etiquetas del home.
     */
    private void actualizaInformacion() {
        /*Etiquetas calefacción*/
 /* Si está encendida o apagada */
        if (modelo.getcalEncendida()) {
            jLabelCalefaccionEncendida.setText("ON");
            jLabelTemperatura.setForeground(Color.BLACK);

        } else {
            jLabelCalefaccionEncendida.setText("OFF");
            jLabelTemperatura.setForeground(Color.LIGHT_GRAY);
        }
        /* La temperatura a la que está*/
        jLabelTemperatura.setText(modelo.getTemperatura() + "º");

        /*Etiquetas luz*/
        for (int i = 0; i < 5; i++) {
            etiquetaluz(i);
        }

        /*Etiqeuta persiana*/
        for (int i = 0; i < 2; i++) {
            etiquetaPersiana(i);
        }
    }

    /*
    Actualiza la etiqueta que indica el color y la intensidad de las bombillas
    persianas añadiendo tambien la imagen del color.
     */
    private void etiquetaluz(int i) {

        String apagada = "/ui/assets/luzApagada.png";

        switch (i) {
            case 0:
                if (modelo.bombilla[0].getEncendido()) {
                    jLabelLuz1.setText(Integer.toString(modelo.bombilla[0].getIntensidad()));
                    jLabelLuz1.setIcon(new javax.swing.ImageIcon(getClass().getResource(colorLuz(0))));
                } else {
                    jLabelLuz1.setText("OFF");
                    jLabelLuz1.setIcon(new javax.swing.ImageIcon(getClass().getResource(apagada)));
                }
                break;

            case 1:
                if (modelo.bombilla[1].getEncendido()) {
                    jLabelLuz2.setText(Integer.toString(modelo.bombilla[1].getIntensidad()));
                    jLabelLuz2.setIcon(new javax.swing.ImageIcon(getClass().getResource(colorLuz(1))));
                } else {
                    jLabelLuz2.setText("OFF");
                    jLabelLuz2.setIcon(new javax.swing.ImageIcon(getClass().getResource(apagada)));
                }
                break;
            case 2:
                if (modelo.bombilla[2].getEncendido()) {
                    jLabelLuz3.setText(Integer.toString(modelo.bombilla[2].getIntensidad()));
                    jLabelLuz3.setIcon(new javax.swing.ImageIcon(getClass().getResource(colorLuz(2))));
                } else {
                    jLabelLuz3.setText("OFF");
                    jLabelLuz3.setIcon(new javax.swing.ImageIcon(getClass().getResource(apagada)));
                }
                break;

            case 3:
                if (modelo.bombilla[3].getEncendido()) {
                    jLabelLuz4.setText(Integer.toString(modelo.bombilla[3].getIntensidad()));
                    jLabelLuz4.setIcon(new javax.swing.ImageIcon(getClass().getResource(colorLuz(3))));
                } else {
                    jLabelLuz4.setText("OFF");
                    jLabelLuz4.setIcon(new javax.swing.ImageIcon(getClass().getResource(apagada)));
                }
                break;

            case 4:
                if (modelo.bombilla[4].getEncendido()) {
                    jLabelLuz5.setText(Integer.toString(modelo.bombilla[4].getIntensidad()));
                    jLabelLuz5.setIcon(new javax.swing.ImageIcon(getClass().getResource(colorLuz(4))));
                } else {
                    jLabelLuz5.setText("OFF");
                    jLabelLuz5.setIcon(new javax.swing.ImageIcon(getClass().getResource(apagada)));
                }
                break;

        }

    }

    /*
    Actualiza la etiqueta que indica la posición de ambas persianas.
     */
    private void etiquetaPersiana(int i) {

        switch (i) {
            case 0:
                if (modelo.persiana[0].getAltura() == 0) {
                    jLabelPersiana1.setText("Bajada");
                } else if (modelo.persiana[0].getAltura() == 5) {
                    jLabelPersiana1.setText("Mitad");
                } else if (modelo.persiana[0].getAltura() == 10) {
                    jLabelPersiana1.setText("Subida");
                } else {
                    jLabelPersiana1.setText("Al "
                            + modelo.persiana[0].getAltura() * 10 + "%");
                }
                break;

            case 1:
                if (modelo.persiana[1].getAltura() == 0) {
                    jLabelPersiana2.setText("Bajada");
                } else if (modelo.persiana[1].getAltura() == 5) {
                    jLabelPersiana2.setText("Mitad");
                } else if (modelo.persiana[1].getAltura() == 10) {
                    jLabelPersiana2.setText("Subida");
                } else {
                    jLabelPersiana2.setText("Al "
                            + modelo.persiana[1].getAltura() * 10 + "%");
                }
                break;
        }

    }

    /*
    Dado el número de una bombilla devuelve la ruta al color de dicha bombilla.
     */
    private String colorLuz(int i) {

        String blanca = "/ui/assets/luzBlanca.png";
        String amarilla = "/ui/assets/luzAmarilla.png";
        String azul = "/ui/assets/luzAzul.png";
        String roja = "/ui/assets/luzRoja.png";
        String verde = "/ui/assets/luzVerde.png";
        switch (modelo.bombilla[i].getColor()) {
            case 0:
                return blanca;
            case 1:
                return verde;
            case 2:
                return azul;
            case 3:
                return amarilla;
            case 4:
                return roja;
            default:
                return null;

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalefaccion;
    private javax.swing.JButton jButtonLuz;
    private javax.swing.JButton jButtonPersiana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCalefaccionEncendida;
    private javax.swing.JLabel jLabelLuz1;
    private javax.swing.JLabel jLabelLuz2;
    private javax.swing.JLabel jLabelLuz3;
    private javax.swing.JLabel jLabelLuz4;
    private javax.swing.JLabel jLabelLuz5;
    private javax.swing.JLabel jLabelPersiana1;
    private javax.swing.JLabel jLabelPersiana2;
    private javax.swing.JLabel jLabelTemperatura;
    private javax.swing.JLabel jLabelTituloCalefaccion;
    private javax.swing.JLabel jLabelTituloHome;
    private javax.swing.JLabel jLabelTituloLuces;
    private javax.swing.JLabel jLabelTituloPersianas;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelCalefaccion;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelLuces;
    private javax.swing.JPanel jPanelPersianas;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}