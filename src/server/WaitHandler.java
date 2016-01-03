/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import businessLayer.BusinessIO;
import businessLayer.Facade;
import commands.Command;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class WaitHandler implements Runnable {

    private Facade facade;
    private ArrayList<ClientWorker> clientes;
    
    public WaitHandler(Facade f){
        this.facade = f;
        this.clientes = new ArrayList<ClientWorker>();
    }
    
    public void addClient(ClientWorker client){
        clientes.add(client);
    }
    
    @Override
    public void run() {
        
        ArrayList<String> notes;
        while(true){
            try {
                Thread.sleep(2000);
                //cenas...
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
