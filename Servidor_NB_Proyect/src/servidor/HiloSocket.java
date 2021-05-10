
package servidor;

import java.io.*;  
import java.net.*;  
import java.util.ArrayList;


public class HiloSocket extends Thread{
    
    
    private static ArrayList<Usuario> clientes = new ArrayList<Usuario>(); // todos los hilos tienen que conocer a todos los usuarios
    private static int id = 0;
    private int codigo; //el id de usuarios se crea en su hilo para que lo conoza sin hacer getters, lo crea y lo instancia aquí
    private String line;
    private DataInputStream is;
    private DataInputStream br;
    private PrintWriter os;
    private Socket sl;
    private String chat = "";

    public HiloSocket(Socket sl) {
        this.sl = sl;
    }
    
    public void run(){
        
        try{
        
            is = new DataInputStream(sl.getInputStream());
            os = new PrintWriter(sl.getOutputStream());
            
            //va ha hacer el primer envio del chat
            chat = is.readLine();//el primer envio de la conexion del clinete es el chat al que va a pertenecer
            //se recoge y se instancia con el grupo
            codigo = ++id;
            clientes.add(new Usuario(os, codigo, chat));
            line = is.readLine(); //se hace una primera lectura del mensaje
            
            while(line.compareTo("TcrYv12#2&") != 0){//cadena que indica el cierre del hilo, el cliente la envia antes de cerrar sesion
                
                for (int i = 0; i < clientes.size(); i++) {// procesado de la recepcion de un mensaje
                    /*
                    En cada envio los 4 primeros caracteres identifical al grupo del usuario.
                    El bucle recorre todos los usuarios comprovando si pertenecen al mismo grupo para mandarles el mensaje recibido.
                    */
                    if(clientes.get(i).getChat().equals(line.substring(0,4))){ 
                        clientes.get(i).getOs().println(line); //si el grupo cohincide reenvia el mensaje por el socket del usuario encontrado que pertenece al mismo grupo
                        clientes.get(i).getOs().flush(); //limpiamos el buffer
                    }
                    
                }
             
                System.out.println(line);
                line = is.readLine();//se lee el mensaje
            
            }
            System.out.println("Conexion cerrada"); //si sale del bucle elimino todos los objetos
            is.close();
            os.close();
            sl.close();
            
            //elimino al usuario de este hilo del array estático
            for (int i = clientes.size()-1; i >=0 ; i--) {
                if(clientes.get(i).getId() == codigo){
                    clientes.remove(i);
                }
            }
            
        }catch(Exception ext){
            
        }
        
    }
    
}
