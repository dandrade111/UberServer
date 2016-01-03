/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import businessLayer.BusinessIO;
import businessLayer.Facade;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author Daniel
 */
public class Server {
    private static ServerSocket server;
    private static final int PORT = 1200;
    private static Facade business = new Facade();
    private static WaitHandler waitHandler;
    
    public static void main(String[] args){
        
       // Console console= new Console(business);
        //Thread consoleThread = new Thread(console);
        //consoleThread.start();
        //waitHandler = new WaitHandler(business);
        //Thread noteThread = new Thread(waitHandler);
        //noteThread.start();
        
        try
        {
           server = new ServerSocket(PORT);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        WaitHandler notify;
        notify = new WaitHandler(business);
        Thread t1 = new Thread(notify);
        t1.start();
            
        while(true){
            handler(notify);
        }
    }
    public static void handler(WaitHandler notify){
        
        ClientWorker w;
        try
        {
           
            w = new ClientWorker(server.accept(),business);
            notify.addClient(w);
            Thread t = new Thread(w);
            t.start();
            
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
    
    
    protected void finalize(){
        try{
            server.close();
        } catch (IOException e) {
            System.out.println("Falha ao fechar o Socket!");
            System.exit(-1);
        }
    }
    
}
