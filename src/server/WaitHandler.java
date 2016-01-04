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
    private ArrayList<ClientWorker> clientes = new ArrayList<>();
    
    public WaitHandler(Facade f){
        this.facade = f;
    }
    
    @Override
    public void run() {
        
        ArrayList<String> notes;
        while(true){
            try {
                Thread.sleep(2000);
                for(ClientWorker cw : clientes)
                {
                    String name = cw.getUserName();
                    if(!"".equals(name)){
                        if(facade.getNotify1().containsKey(name)){
                            cw.sendNotification(facade.getNotify1().get(name));
                            facade.clearNotes1(name);
                        }
                            
                        if(facade.getNotify2().containsKey(name)){
                            cw.sendNotification(facade.getNotify2().get(name));
                            facade.clearNotes2(name);
                        }
                            
                        if(facade.getNotify3().containsKey(name)){
                            cw.sendNotification(facade.getNotify3().get(name));
                            facade.clearNotes3(name);
                        }
                        
                    }
                        
                }
                
            } catch (InterruptedException ex) {
                
            }
        }
    }

    void addClient(ClientWorker w) {
        clientes.add(w);
    }
    
}
