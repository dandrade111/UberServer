/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Facade implements BusinessIO {
    private Cidade cidade;
    private PedidosPendentes pedidospendentes;
    private ArrayList<String> clients1 = new ArrayList<String>();
    private ArrayList<String> clients2 = new ArrayList<String>();
    private ArrayList<String> clients3 = new ArrayList<String>();
            
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

    public void addnotify1(String nome){
        this.clients1.add(nome);
    }
    public void addnotify2(String nome){
        this.clients2.add(nome);
    }
    public void addnotify3(String nome){
        this.clients3.add(nome);
    }
    
    public ArrayList<String> getNotify1(){
        return this.clients1;
    }
    public ArrayList<String> getNotify2(){
        return this.clients2;
    }
    public ArrayList<String> getNotify3(){
        return this.clients3;
    }
    
    public void clearNotes1(){
        this.clients1.clear();
    }
    
    public void clearNotes2(){
        this.clients2.clear();
    }
    
    public void clearNotes3(){
        this.clients3.clear();
    }
}
