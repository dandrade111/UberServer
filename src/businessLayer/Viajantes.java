/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author nmore_000
 */
public class Viajantes {
   private HashMap<Viajante,Viajante> viajantes;
   
   //construtores

    public Viajantes(Viajantes viajantes) {
        this.viajantes = new HashMap<>();
        this.viajantes.putAll((Map<? extends Viajante, ? extends Viajante>) viajantes);
              
    }
    
   
    public Viajantes(){
        this.viajantes = new HashMap<>();
    }
    
    //gets e sets

    public HashMap<Viajante, Viajante> getViajantes() {
        return viajantes;
    }

    public void setViajantes(HashMap<Viajante, Viajante> viajantes) {
        this.viajantes = viajantes;
    }
    
    //equals 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viajantes other = (Viajantes) obj;
        if (!Objects.equals(this.viajantes, other.viajantes)) {
            return false;
        }
        return true;
    }
    
    //toString
    @Override
    public String toString() {
        return "Viajantes{" + "histAero=" + viajantes + '}';
    }
    
    //clone
    private Viajantes clone(Viajantes v){
        return new Viajantes(v);
    }
    
    //metodos
    //contains
    public boolean containsCondutor(Viajante a){
        return this.viajantes.containsKey(a);
    }
    
    public boolean containsPassageiro(Viajante b){
        return this.viajantes.containsValue(b);
    }
    
    //add
    public void addViajantes(Viajante a, Viajante b){
        this.viajantes.put(a,b);
    }
    
    //remove
    public void removeViajantes(Viajante a,Viajante b){
        this.viajantes.remove(a, b);
    }
    
    public void removeViajantes(String a){
        Set<Viajante> viajante = this.viajantes.keySet();
        for(Viajante v: viajante){
            if(v.getNome().equals(a)){
                this.viajantes.remove(v);
            }
        }
           
    }
    public String devolvePassageiroAux(String a){
        Viajante aux = new Viajante();
         Set<Viajante> viajante = this.viajantes.keySet();
        for(Viajante v: viajante){
            if(v.getNome().equals(a)){
               return this.viajantes.get(v).getNome();
               
            }
        }
       
        
        return "";
        
    }
    
}
