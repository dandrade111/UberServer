/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author nmore_000
 */
public class PedidosPendentes {
    private ArrayList<Viajante> pedidospendentes;
    
    //construtores
    public PedidosPendentes(){
        this.pedidospendentes = new ArrayList<Viajante>();
    }
    
    public PedidosPendentes(ArrayList<Viajante> v){
        this.pedidospendentes = new ArrayList<Viajante>();
       
        for(int i = 0; i<(v.size());i++){
            this.pedidospendentes.add(v.get(i));
        }
    }

    //gets and sets
    public ArrayList<Viajante> getPedidospendentes() {
        return pedidospendentes;
    }

    public void setPedidospendentes(ArrayList<Viajante> pedidospendentes) {
        this.pedidospendentes = pedidospendentes;
    }
    
    //to string
    @Override
    public String toString() {
        return "PedidosPendentes{" + "PedidosPendentes=" + pedidospendentes + '}';
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
        final PedidosPendentes other = (PedidosPendentes) obj;
        if (!Objects.equals(this.pedidospendentes, other.pedidospendentes)) {
            return false;
        }
        return true;
    }
     
    //metodos
     //add
    public void add(Viajante v){
        this.pedidospendentes.add(v);
    }
    
    //remove
    public void remove(Viajante v){
        this.pedidospendentes.remove(v);
    }
    
    //contains
    public boolean contains(Viajante v){
        return this.pedidospendentes.contains(v);
    }
    
    //retira o primeiro elemento do arraylist
    public Viajante remove(){
        return this.pedidospendentes.remove(0);
    }
    
    //isEmpty
    public boolean isEmpty(){
        return this.pedidospendentes.isEmpty();
    }
    
}
