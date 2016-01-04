/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author nmore_000
 */
public class Users {
    private HashSet<User> users;
    
    public Users(){
        this.users = new HashSet<>();
    }
    
    public Users(Users c) throws CloneNotSupportedException{
        for(User a: c.users){
            this.users.add(a);
        }
    }
    
    //gets e sets

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = (HashSet<User>) users.clone();
    }
    
    //metodos
    //add
          
    public void addUser(User a){
        if(!(this.users.contains(a)))
            this.users.add(a);
    }
    
    //remove
    public void removeUser(User a){
        this.users.remove(a);
    }
    
    //contains
    public boolean containsUser(User a){
        return (this.users.contains(a));
    }
    
    public User consulta(String nome){
        User aux = new User();
        for(User a: this.users){
            if(a.getNome().equals(nome)){
                aux = new User(a) ;
            }
        }
        return aux;
    }
    
    //isEmpty
    public boolean isEmpty(){
        return this.users.isEmpty();
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
        final Users other = (Users) obj;
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }
     
     //size
     public int size(){
         return this.users.size();
     }
     
     //sizeCondutores
     public int sizeCondutor(){
         int res = 0;
         int s = this.users.size();
         for (User obj : this.users) {
         if (obj.getTipo() == 1) 
          res++;
      } 
         return res;
     }
     
     //sizeClientes
     public int sizeCliente(){
         int res = 0;
         int s = this.users.size();
         for (User obj : this.users) {
         if (obj.getTipo() == 2) 
          res++;
      } 
         return res;
     }
     
     //encontrar condutor disponivel
     public User findCondutor() throws CloneNotSupportedException{
         User user = new User();
         for (User obj : this.users) {
         if (obj.getTipo()==1) 
          user = obj;
          this.users.remove(obj);
          return user;
      } 
         return user;
     }
     
     public boolean login(String nome, String pass){
        for(User u : this.users){
            if(u.getNome().equals(nome)){
                if(u.getPassword().equals(pass)){
                    
                        u.setLogado(true);
                        return true;
                        
                    
                    
                }
            }
        }
        return false;
        
     }
     
    //toString
     @Override
    public String toString() {
         return "Users{" + "users=" + users + '}';  
    }
     
    
}
