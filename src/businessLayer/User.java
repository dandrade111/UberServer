/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.util.Objects;

/**
 *
 * @author nmore_000
 */
public class User {
    private String nome;
    private String password;
    private int tipo;       //1-condutor, 2-cliente
    private String marca;
    private String matricula;
    private boolean logado;
    
    
    public User(){
        this.nome = new String();
        this.password = new String();
        this.tipo = -1;
        this.marca = new String();
        this.matricula = new String();
        this.logado = false;
    }
    
    public User(String nome, String password, int tipo,String marca,String matricula){
        this.nome = nome;
        this.password = password;
        this.tipo = tipo;
        this.marca = marca;
        this.matricula = matricula;
        this.logado = false;
    }
    
    //para so quem e cliente
    public User(String nome, String password){
        this.nome = nome;
        this.password = password;
        this.tipo = 2;
        this.marca = new String();
        this.matricula = new String();
        this.logado = false;
    }
    
    public User(User c){
        this.nome = c.nome;
        this.password = c.password;
        this.tipo = c.tipo;
        this.marca = c.marca;
        this.matricula = c.matricula;
        this.logado = c.logado;
    }
    
    
    //sets e gets
    public String getNome(){
         return nome;
   }


    public String getPassword() {
        return password;
    }

    public int getTipo() {
        return tipo;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public String getMatricula(){
        return matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    
    
    //equals
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final User other = (User) o;
        
        if (!Objects.equals(this.getNome(), other.getNome())) {
            return false;
        }
        if (!Objects.equals(this.getPassword(),other.getPassword())){
            return false;
        }
        if (!Objects.equals(this.getTipo(), other.getTipo())){
            return false;
        }
        if (!Objects.equals(this.getMarca(), other.getMarca())){
            return false;
        }
        if (!Objects.equals(this.getMatricula(), other.getMatricula())){
            return false;
        }
        if (!Objects.equals(this.isLogado(), other.isLogado())){
            return false;
        }
                return true;
    }
    
    //toString
    @Override
    public String toString(){
        return "{User:" + "nome=" + this.getNome()
                           + ", password=" + this.getPassword()
                           + ", tipo=" + this.getTipo()
                           + ", marca=" + this.getMarca()
                           + ", matricula=" + this.getMatricula()
                           + ", logado=" + this.isLogado()
                           + "}";
    }
    
    @Override
    public User clone() throws CloneNotSupportedException{
        return new User(this);
    }
}
