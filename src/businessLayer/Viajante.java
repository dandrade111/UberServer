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
public class Viajante {
    private String nome;
    private String password;
    private int tipo;       //1-condutor, 2-cliente
    private String marca;
    private String modelo;
    private String matricula;
   
    
    public Viajante(){
        this.nome = new String();
        this.password = new String();
        this.tipo = -1;
        this.marca = new String();
        this.modelo = new String();
        this.matricula = new String();
       
    }
    
    public Viajante(String nome, String morada, String email, String password, 
                    int tipo,String marca,String matricula
                   ){
        this.nome = nome;
        this.password = password;
        this.tipo = tipo;
        this.marca = marca;
        this.matricula = matricula;
      
    }
    
    public Viajante(User u){
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.tipo = u.getTipo();
        this.marca = u.getMarca();
        this.matricula = u.getMatricula();
        
    }
    
    public Viajante(Viajante v){
        this.nome = v.nome;
        this.password = v.password;
        this.tipo = v.tipo;
        this.marca = v.marca;
        this.matricula = v.matricula;
       
    }
    
    //gets e sets

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

   
    
    //toString
    @Override
    public String toString(){
        return "{Viajante:" + "nome=" + this.getNome()
                            + ", password=" + this.getPassword()
                            + ", tipo=" + this.getTipo()
                            + ", marca=" + this.getMarca()
                            + ", matricula=" + this.getMatricula()
                            + "}";
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
        final Viajante other = (Viajante) o;
        
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
        
                return true;
    }
    
    
    //clone
    @Override
    public Viajante clone() throws CloneNotSupportedException{
        return new Viajante(this);
    }
    
   }
