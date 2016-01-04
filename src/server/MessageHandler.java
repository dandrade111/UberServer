/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import businessLayer.BusinessIO;
import businessLayer.Facade;
import businessLayer.User;
import businessLayer.Viajante;
import commands.Command;
import java.awt.geom.Point2D;
/**
 *
 * @author Daniel
 */
public class MessageHandler implements BusinessIO{
    private Facade facade;
    private Command cmd;
    public boolean hasResponse;
    public String name = "";
    
    public MessageHandler(Facade f){
        this.facade = f;
        this.hasResponse = false;
    }
    
    public MessageHandler(Facade f, Command cmd){
        this.facade = f;
        this.cmd = cmd;
        this.hasResponse = false;
    }
    
    public Command ResolveMessage(Command c) {
        this.cmd = c;
        this.hasResponse = false;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    if(cmd.args.size == 8){
                        cmd.result = addUserC3((String)cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(1),
                                               (int)cmd.args.listArgs.get(2),(String)cmd.args.listArgs.get(3),
                                               (String)cmd.args.listArgs.get(4));
                                              
                        hasResponse = true;
                    }
                    else if(cmd.args.size == 2){
                         cmd.result = addUserC((String)cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(1));
                         hasResponse = true;
                    }
                  return cmd;
                }
                case "login_cliente":{
                    if(cmd.args.size == 2){
                        cmd.result = loginC((String)cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(1));  
                        if((boolean)cmd.result){
                            this.name = (String)cmd.args.listArgs.get(0);
                        }
                        hasResponse = true;
                   }
                  return cmd;
                }
                case "changeUserLocation":{
                   if(cmd.args.size == 2){
                        cmd.result = changeUserLocation((String) cmd.args.listArgs.get(0),(int)cmd.args.listArgs.get(1),(int)cmd.args.listArgs.get(2));
                        hasResponse = true;
                    }
                  return cmd;
                }
                
                case "get_viagem":{ //falta ligar ao condutor (acordar thread)
                    if(cmd.args.size == 5){
                        User a= new User(finder2((String)cmd.args.listArgs.get(0), 
                               Integer.valueOf((String) cmd.args.listArgs.get(1)), 
                               Integer.valueOf((String) cmd.args.listArgs.get(2))));
                        Command tmp = cmd;
                        cmd = new Command("get_viagem", "", new Object[]{a.getMatricula(),a.getMarca(),tempo(position2(a.getNome()),position2((String) cmd.args.listArgs.get(0)))});
                        
                        cmd.result = true;
                               
                        hasResponse = true;
                        facade.addnotify1(a.getNome(), new Command("viagem_atribuida", "", new Object[]{(String) tmp.args.listArgs.get(1),(String) tmp.args.listArgs.get(2),(String) tmp.args.listArgs.get(3),(String) tmp.args.listArgs.get(4)}));
                       
                    }
                   return cmd;
                }
                case "is_available":{
                    if(cmd.args.size == 5){
                       anunciarDisponibilidade(
                               (String)cmd.args.listArgs.get(0), 
                               Integer.valueOf((String)cmd.args.listArgs.get(1)),
                               Integer.valueOf((String)cmd.args.listArgs.get(2)),
                               (String)cmd.args.listArgs.get(3),
                               (String)cmd.args.listArgs.get(4));
                        hasResponse = false;
                    }
                   return cmd;
                }
                case "at_pickup":{
                    if(cmd.args.size == 1){
                        //cmd.result = "Chegou";
                        hasResponse = false;
                        String condutor = (String)cmd.args.listArgs.get(0);
                        String passageiro = devolvePassageiroAux(condutor);
                        facade.addnotify2(passageiro,new Command("veiculo_chegou", condutor , new Object[]{}));
                    }
                   return cmd;
                }
                case "at_destination":{
                    if(cmd.args.size == 2){
                       //cmd.result = ((String)cmd.args.listArgs.get(0), (int)cmd.args.listArgs.get(1));
                       hasResponse = false;
                       String condutor = (String)cmd.args.listArgs.get(0);
                       String passageiro = devolvePassageiroAux(condutor);
                       facade.addnotify3(passageiro,new Command("chegou_destino", condutor , new Object[]{(String)cmd.args.listArgs.get(1)}));
                    }
                   return cmd;
                }
                
            }
        }else{
            cmd.result ="Comando Inválido!\n";
            return cmd;
        }
        return null;
    }
    
    public String getClientName(){
         return this.name;
    }
    
    @Override
    public boolean loginC(String nome, String pass){
        return facade.loginC(nome,pass);
     }
    
    @Override
    public boolean addUserC(String nome, String password){  
         return facade.addUserC(nome, password); 
    }
    
    @Override
    public boolean addUserC3(String nome, String password, int tipo, 
            String marca,String matricula){  
         return facade.addUserC3(nome, password, tipo, marca, matricula); 
    }
    
    @Override
    public boolean changeUserLocation(String nome, int x, int y){
       return facade.changeUserLocation(nome, x, y);
    }
    
    @Override
     public User finder2 (String nome,int x,int y){
        return facade.finder2(nome,x,y);
    }
     
    @Override
     public Point2D position2(String a){
        return facade.position2(a);
    }
     
    @Override
     public float tempo(Point2D condutor, Point2D cliente){
         return facade.tempo(condutor, cliente);
     }
     
    @Override
     public void anunciarDisponibilidade(String nome, int x, int y,String marca, String matricula){
         facade.anunciarDisponibilidade(nome, x, y, marca, matricula);
     }
    
    @Override
    public void removeViajante(String a){
        facade.removeViajante(a);
    }
     
    @Override
    public String devolvePassageiroAux(String a){
        return facade.devolvePassageiroAux(a);
    }
    
}
