

package servidor;

import java.io.*;  
import java.net.*;  

public class Servidor {


    
    public static void main(String[] args) throws Exception {
      
      
        Socket serv = null;
        ServerSocket serv2 = null;
        
        int puerto = 4449;
        
        System.out.println("Escuchando " + puerto + " ...");
        serv2 = new ServerSocket(puerto);
        
        while(true){ //el bucle no para
            
            try{ //acepta peticiones de clientes
                
                //creamos un socket por usuario
                serv = serv2.accept(); 
                System.out.println("Conexión establecida");
                HiloSocket hilo = new HiloSocket(serv); //creamos un hilo para escuchar ese socket
                hilo.start(); //iniciamos el hilo
                
                
                
            }catch(Exception exc){
                System.out.println("Error de conexión");
            }
        }
                
    }
    
}
