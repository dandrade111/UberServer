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
                    if(name != ""){
                        if(facade.getNotify1().contains(name))
                            cw.sendNotification("note1");
                        
                        if(facade.getNotify1().contains(name))
                            cw.sendNotification("note2");
                                    
                        if(facade.getNotify1().contains(name))
                            cw.sendNotification("note3");
                    }
                    facade.clearNotes1();
                    facade.clearNotes2();
                    facade.clearNotes3();
                        
                }
                
            } catch (InterruptedException ex) {
                
            }
        }
    }

    void addClient(ClientWorker w) {
        clientes.add(w);
    }
    
}
