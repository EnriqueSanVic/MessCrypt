
package clientechat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
Esta clase sirve para escuchar el socket por otro hilo independiente de la ejecucion del Panel, si se crea un Panel se crea un hilo de este tipo
*/

public class HiloActualizadorChat extends Thread{
    
    private DataInputStream is;
    private Panel ventana;
    public HiloActualizadorChat(DataInputStream is,Panel ventana) {
        this.is = is;
        this.ventana = ventana;
    }
    
    @Override
    public void run(){ //funcionamiento cuando se inicia el hilos
         String mensaje;
         while(true){
            
            try {//intenta leer entrada del socket
                mensaje = decodificar(is.readLine()); //recibe el mensaje del socket y lo decodifica
                System.out.println(mensaje + "\n"); //si hay una entrada saca por consola el mensaje
                ventana.addMensaje(mensaje); //añade el mensaje en la lista con scroll de mensajes del Panel
            } catch (SocketException ex) {
                
            } catch (IOException ex) {
                
            }
        }
    }
    
    
    private String decodificar (String cadena){ //decodificación de mensajes
        
        cadena = cadena.substring(13,cadena.length());  //cadena que se decodificara
        //lo primero es quitar los 13 caracteres que solo tienen informacion del grupo y relleno para despistar
        
        char[] lista = cadena.toCharArray(); //pasa la String a un Array de char
        
        for (int i = 0; i < lista.length; i++) {
            lista[i] -= 3; //resta tres en el Unicode de todos los caracteres
        }
        
        return new String(lista); //lo retorna como objeto String
       
    }
    
    
    public void close() throws IOException{ //proceso de cierre
        is.close(); 
    }
}
