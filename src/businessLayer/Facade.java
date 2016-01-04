/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import commands.Command;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Facade implements BusinessIO {
    private Cidade cidade;
    private PedidosPendentes pedidospendentes;
    private HashMap<String,Command> clients1 = new HashMap<String,Command>();
    private HashMap<String,Command> clients2 = new HashMap<String,Command>();
    private HashMap<String,Command> clients3 = new HashMap<String,Command>();
            
    public Facade()
    {
        this.cidade = new Cidade(5,5);
        this.pedidospendentes = new PedidosPendentes();
        //this.users_inscritos = new Users();
    }
    
    public Facade(int x, int y){
        this.cidade = new Cidade(x,y);
        this.pedidospendentes = new PedidosPendentes();
    }
    
 
    public boolean addUserC(String nome,String password){
    return this.cidade.addUserC(nome, password);
}
    
 
    public boolean addUserC3(String nome, String password, 
        int tipo,String marca,String matricula){
    return this.cidade.addUserC3(nome,password, tipo, marca, matricula);
}
    
  
    public boolean loginC(String nome, String pass){
        return cidade.loginC(nome,pass);
     }
    
    public boolean changeUserLocation(String nome, int x, int y){
        return this.cidade.changeUserLocation(nome, x, y);
    }
    
    public User finder2(String nome,int x, int y){
        User aux = new User();
        try {
            aux = cidade.finder2(nome, x, y);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public Point2D position2(String a){
        return cidade.position2(a);
    }
    
    public float tempo(Point2D condutor, Point2D cliente){
         return cidade.tempo(condutor, cliente);
     }
    
    public void anunciarDisponibilidade(String nome, int x, int y,String marca,String matricula){
        this.cidade.anunciarDisponibilidade(nome,x,y,marca,matricula);
    }
    
    public void removeViajante(String a){
        this.cidade.removeViajante(a);
    }

    public void addnotify1(String nome, Command cmd){
        this.clients1.put(nome, cmd);
    }
    public void addnotify2(String nome, Command cmd){
        this.clients2.put(nome, cmd);
    }
    public void addnotify3(String nome, Command cmd){
        this.clients3.put(nome, cmd);
    }
    
    public HashMap<String,Command> getNotify1(){
        return this.clients1;
    }
    public HashMap<String,Command> getNotify2(){
        return this.clients2;
    }
    public HashMap<String,Command> getNotify3(){
        return this.clients3;
    }
    
    public void clearNotes1(String key){
        this.clients1.remove(key);
    }
    
    public void clearNotes2(String key){
        this.clients2.remove(key);
    }
    
    public void clearNotes3(String key){
        this.clients3.remove(key);
    }
    
    public String devolvePassageiroAux(String a){
        return this.cidade.devolvePassageiroAux(a);
    }
}
