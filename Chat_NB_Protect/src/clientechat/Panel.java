/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientechat;


import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ####
 */
public class Panel extends javax.swing.JFrame {
    
    private PrintWriter os; //objeto output del socket
    private Socket sl; //socket
    private DataInputStream br;
    private DataInputStream is;
    private String nombre; //nombre del usuario
    private String chat; //chat actual de la instancia
    private boolean retroceder = false; //variables de estado
    private boolean eliminado = false;
    private Color anterior;
    
    public Panel(String chat) {
        this.chat = chat;//por razones tecnicas solo paso el chat en el constructor y el resto de objetos se pasan en el metodo addOS() que actua de constructor secundario para recepcionar argumentos
        initComponents();
    }
    
    private String aleatorio(){ //este metodo simplemente retorna un String de longitud 9 de caracteres aleatorios, son para rellenar y confundir en la codificaion
        
        
        char[] ALPHANUMERICOS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
                                 'U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
                                 'o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8',
                                 '9','0','!','@','#','$','%','&','?','*','+','-','1','2','3','4','5','6',
                                 '7','8','9','0','!','@','#','$','%','&','?','*','+','-'};
        int longitud = 9;
        char[] lista = new char[longitud];
        int posicion;
        Random random = new Random();
            
        
        for (int i = 0; i < lista.length; i++) {
            posicion = random.ints(0, ALPHANUMERICOS.length-1).findFirst().getAsInt();
            lista[i] = ALPHANUMERICOS[posicion];
        }
        
        //System.out.println("Aleatorio " + new String(lista));
        return new String(lista);
        
    }

    
    private String codificar(String cadena){ //codificacion de mensajes
        
        //sencillamente le suma tres al Unicode de todos los caracteres del mensaje, es una codificación sencilla pero funcional
        char[] lista = cadena.toCharArray();
        
        for (int i = 0; i < lista.length; i++) {
            lista[i] += 3;
        }
        
        return aleatorio() + new String(lista); //retorna el aleatorio mas el mensaje que se ha entregado para codificar
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new java.awt.Label();
        list1 = new java.awt.List();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        titulo1 = new java.awt.Label();
        label1 = new java.awt.Label();
        button1 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        titulo.setAlignment(java.awt.Label.CENTER);
        titulo.setBackground(new java.awt.Color(102, 153, 255));
        titulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        titulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        titulo.setText(chat);

        list1.setBackground(new java.awt.Color(204, 255, 255));
        list1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jTextField1.setBackground(new java.awt.Color(153, 255, 255));
        jTextField1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Enviar:");

        titulo1.setAlignment(java.awt.Label.CENTER);
        titulo1.setBackground(new java.awt.Color(0, 204, 204));
        titulo1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        titulo1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        titulo1.setText("MessCrypt");

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(51, 102, 255));
        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Arial Black", 1, 27)); // NOI18N
        label1.setText("\u2794");
        label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label1MouseExited(evt);
            }
        });

        button1.setBackground(new java.awt.Color(102, 204, 255));
        button1.setLabel("Enviar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        titulo.getAccessibleContext().setAccessibleParent(titulo);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        String mensaje;
        if(evt.getKeyCode() == evt.VK_ENTER) { //evento cuando se presiona ENTER
            
            mensaje = jTextField1.getText(); //se manda el mensaje de la barra del textfield1
            jTextField1.setText("");//se pone en vacio
            os.println(chat + codificar(nombre + ": " + mensaje)); //se manda el mensaje
            os.flush(); // se limia el buffer
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MouseClicked
        protocoloCierre(); //se procede al procedimiento de cierre
        retroceder = true; //cuando se presiona el boton de retroceso se cambia el estado que monitoriza el main
    }//GEN-LAST:event_label1MouseClicked

    private void label1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MouseExited
        label1.setBackground(anterior); //cuando sale del area del boton de retroceso,cambia colores
    }//GEN-LAST:event_label1MouseExited

    private void label1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MouseEntered
        anterior = label1.getBackground(); //cuando entra en el area del boton de retroceso
        label1.setBackground(new Color(255,102,102));// cambia colores
    }//GEN-LAST:event_label1MouseEntered

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
         String mensaje;
        mensaje = jTextField1.getText(); //se manda el mensaje de la barra del textfield1
        jTextField1.setText("");//se pone en vacio
        os.println(chat + codificar(nombre + ": " + mensaje)); //se manda el mensaje
        os.flush(); // se limia el buffer
    }//GEN-LAST:event_button1ActionPerformed

    
    private void protocoloCierre(){ //procedimientos antes de destruir un Panel ventana
        os.println(chat + codificar(nombre + " ha salido del chat.")); //se manda un ultimo mensaje
        os.flush();
        os.println("TcrYv12#2&"); //se envia codigo para que el servidor cierre el socket
        os.flush();
        os.close(); //destruccion de todos los objetos relacionados con la comunicacion
        try { 
            is.close();
            br.close();
            sl.close();
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel("").setVisible(true); //le paso a new panel unsa string vacia, no se que consecuencias puede tener
                
                
            }
        });
    }
    
    
    public void addOS(PrintWriter os, DataInputStream is, DataInputStream br, Socket sl,String nombre){
        this.os = os; //es una especie de pseudo-constructor para pasar los objetos de entrada-salida junto al socket y el nombre de usuario
        this.is = is;
        this.br = br;
        this.sl = sl;
        this.nombre = nombre; //tambien el nombre
        
        
        super.addWindowListener(new WindowAdapter () {public void windowClosing (WindowEvent we) { //se añade aqui el window listenerp porque hay que asegurarse de que los objetos de arriba existen
            protocoloCierre(); //activa los procedimientos de cierre
            eliminado = true;
            System.exit(0); //cierre definitivo
            }
        });
        
        
        os.println(chat); // se realiza el primer envio para darle asignar el chat en el servidor 
        os.flush();
        os.println(chat + codificar(nombre + " se ha unido al chat.")); //se manda el mensaje de aparicion en el chat
        os.flush();
        
    }
    
    
    public void addMensaje(String mensaje){
        list1.add(mensaje);//se añade un mensaje a la lista de mensajes.
        list1.makeVisible(list1.getItemCount()-1); //mueve el scroll al nuevo mensaje para que baje de forma automática
        
    }

    public boolean isRetroceder() {
        return retroceder;
    }

    public boolean isEliminado() {
        return eliminado;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.List list1;
    private java.awt.Label titulo;
    private java.awt.Label titulo1;
    // End of variables declaration//GEN-END:variables
}
