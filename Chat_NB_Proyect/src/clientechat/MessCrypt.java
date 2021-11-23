
package clientechat;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;  
import java.net.*;  
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MessCrypt {
    

    public static void main(String[] args) throws IOException, InterruptedException {
        
        boolean correcto,repetir; 
        Panel ventana = null; //tipo estatico de ventana principal
        HiloActualizadorChat act = null; // tipo estatico de actualizador de chat
        BufferedImage imagen = null; //icono de ventanas
        
        try { //Instanciación del icono SOLO FUNCIONA EN EL PROYECTO DE NET BEANS si es el .JAR no se instanciará
            File  icono = new File("src/MCicon.png");
            imagen = ImageIO.read(icono);
        } catch (IOException e) {
            System.out.println("Icono no encontrado.");
        }
       
        do{ 
            /*
             primer bucle por si una vez completado todos los pasos de apertura del actor 
             decidise volver al menu inicial para ingresar a otro chat
             por medio del boton retroceso del Panel.
            */
            repetir = false;
            do{
                /*
                   segundo bucle por si la conexion con el servidor falla repetir el formulatio Inicio para otro intento
                */
                correcto = false;
                
                //creamos la ventana Inicio
                Inicio init = new Inicio();
                init.setTitle("MessCrypt");
                init.setIconImage(imagen);
                init.setImagen(imagen);
                ponerMedioPantalla(init);
                init.show();

                /*
                  esperamos a que termine de completar el formulario por medio del atributo continuar en Inicio
                  monitorizamos el estado del formulario Inicio
                */
                while(!init.isContinuar()){Thread.sleep(100);}


                /*
                    una vez terminado el formulario, recogemos todas las variables del formulario
                    ya nos hemos asegurado de que los formatos son correctos en la insercion en la clase Inicio
                */
                String host = init.getHost();
                int puerto = init.getPuerto();
                String nombre = init.getNombre();
                String chat = init.getChat();
                init.dispose(); //destruimos la ventana inicio

                //creacion de la ventana Panel
                ventana = new Panel(chat);
                ventana.setTitle("MessCrypt");
                ventana.setIconImage(imagen);
                ponerMedioPantalla(ventana);

                //referencias a todos los objetos relacionados con la comunicacion en el socket
                Socket sl = null;
                String line = null;
                DataInputStream is = null;
                DataInputStream br = null;
                PrintWriter os = null;

                try{
                    //intentamos establecer conexion con el servidor
                    System.out.println("Estableciendo conexión..");
                    sl = new Socket(host,puerto);
                    br = new DataInputStream(System.in);
                    is = new DataInputStream(sl.getInputStream());
                    os = new PrintWriter(sl.getOutputStream());

                    ventana.addOS(os,is,br,sl,nombre); //le pasamos todos los objetos de comunicacion al Panel
                    ventana.show();
                    
                    //creamos el objeto RefescoChat que es un hilo que su cometido es comprobar nuevos mensajes constantemente
                    act = new HiloActualizadorChat(is,ventana); //le enviamos la ventana y el mismo objeto del Input del socket por comodidad.
                    act.start(); //iniciamos la escucha del socket
                    

                }catch(IOException ext){
                    //si ocurre un error en la conexion
                    System.out.println("Conexión no establecida");
                    correcto = true; //se pone la flag en verdadero para probar otro intento
                    ventana.dispose(); //se destruye el Panel

                    ErrorConexion advertencia = new ErrorConexion(); //se crea la ventana de Warning que es un aviso de error de conexion
                    advertencia.setTitle("Error");
                    advertencia.setIconImage(imagen);
                    ponerMedioPantalla(advertencia);
                    advertencia.show();

                    //esperamos a que el usuario lea la advertencia y la acepte o la cierre
                    while(advertencia.isExistir()){
                        Thread.sleep(100);
                    }
                    //eliminamos la advertencia
                    advertencia.dispose();

                }

            }while(correcto);
            
            //si todo ha ido bien, esperamos comprobando los estados del objeto Panel ventana
            while(!ventana.isRetroceder() && !ventana.isEliminado()){Thread.sleep(100);}
            
            if(ventana.isRetroceder()){ //solo si el Panel se ha cerrado por retroceso se repite el bucle para ir al Inicio, sino se sale del programa
                ventana.dispose();
                act.close(); //se cierran las instancias multi hilo involucradas
                repetir = true;
            }
            
        }while(repetir);
        
        ventana.dispose();
        act.close(); //se cierran las instancias multi hilo involucradas
        System.out.println("Finalizado");
    }
    
    public static void ponerMedioPantalla(Container ventana) {
        
        int width, height;
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        width = (pantalla.width/2) - (ventana.getSize().width/2);
        
        height = (pantalla.height/2) - (ventana.getSize().height/2);
        
        ventana.setLocation(width, height);

    }
    
}
