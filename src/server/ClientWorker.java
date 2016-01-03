/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import commands.Command;
import businessLayer.Facade;

/**
 *
 * @author Daniel
 */
public class ClientWorker implements Runnable {
    private Socket client;
    private Facade facade;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MessageHandler messageHandler;
    //Constructor
    ClientWorker(Socket client, Facade facade) 
    {
        this.client = client;
        this.facade = facade;
    }
    
    public void run()
    {
        Command cmd = null;
        //Object res = null;
        
        try{
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            System.out.println("Falha a criar ObjectStream (In/Out)");
            System.exit(-1);
        }

        messageHandler = new MessageHandler(facade);
        
        while(true){
            try{
                cmd = (Command) in.readObject();
                System.out.println("Cliente -> Servidor:" + cmd.type);
                messageHandler.hasResponse = false;
                cmd = messageHandler.ResolveMessage(cmd);
                
                if(messageHandler.hasResponse){
                    System.out.println("Servidor -> Cliente:" + cmd.type);
                    out.writeObject(cmd);
                    out.flush();
                }
                
            }catch (Exception e) {
                System.out.println("Falha na Leitura: " + e.getMessage())   ;
                break;
            }
        }
        
        finalize();
    }
    
    public void sendNotification(String noteType){
        try{
            switch (noteType){
                case "":break;
                case "note1":{
                    Command temp = new Command("Alerta","",new Object[]{""});
                    out.writeObject(temp);
                    out.flush();
                    break;
                }
                case "note2":{
                    Command temp = new Command("Alerta","",new Object[]{""});
                    out.writeObject(temp);
                    out.flush();
                    break;
                }
                case "note3":{
                    Command temp = new Command("Alerta","",new Object[]{""});
                    out.writeObject(temp);
                    out.flush();
                    break;
                }
            }
            
        }catch(Exception e){
            System.out.println("Falha na Leitura do alerta: " + e.getMessage());
        }
        
    }
    
    protected void finalize(){
    //Objects created in run method are finalized when
    //program terminates and thread exits
        try{
            client.close();
        } catch (IOException e) {
            System.out.println("Erro ao Fechar o Socket!");
            System.exit(-1);
        }
    }
    
    public String getUserName(){
       return messageHandler.getClientName();
    }
}
