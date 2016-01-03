/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Facade implements BusinessIO {
    private Cidade cidade;
    private PedidosPendentes pedidospendentes;
    private Users users_inscritos;
    
            
    public Facade()
    {
        this.cidade = new Cidade(0,0);
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
    
  
    public boolean login(String nome, String pass){
        return users_inscritos.login(nome,pass);
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

    public void notifyFoundClient(String nome){
        //WaitHandle
    }
    
    
}
