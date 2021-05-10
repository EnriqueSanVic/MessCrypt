/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.PrintWriter;

/**
 *
 * @author ####
 */
public class Usuario {
    
    private PrintWriter os; //cada usuario tiene guardado su objeto de entrada-salida de su socket
    private final int id;  //guarda un identificador que realmente ahora no se usa, considero que viene bien en el futuro
    private String chat; //guardamos el grupo al que pertenece un usuario

    public Usuario(PrintWriter os, int id,String chat) {
        this.os = os;
        this.id = id;
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }
    

    public PrintWriter getOs() {
        return os;
    }

    public int getId() {
        return id;
    }
    
    
    
}
