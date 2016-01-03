/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.awt.geom.Point2D;
import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author nmore_000
 */
public class Cidade {
    private Users[][] cidade;
    private int x; //comprimento
    private int y; //largura
    private Users users_inscritos;
    private Viajantes viajantes;
    
    
    
    //construtores
    public Cidade(int x,int y){
        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                this.cidade[i][j] = new Users();
            }
        }
        this.x=x;
        this.y=y;
        this.users_inscritos = new Users();
        this.viajantes = new Viajantes();
        
    }
    
    //gets e sets

    public Users[][] getCidade() {
        return cidade;
    }

    public void setCidade(Users[][] cidade) {
        this.cidade = cidade;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Users getUsers_inscritos() {
        return users_inscritos;
    }

    public void setUsers_inscritos(Users users_inscritos) {
        this.users_inscritos = users_inscritos;
    }

    public Viajantes getViajantes() {
        return viajantes;
    }

    public void setViajantes(Viajantes viajantes) {
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return true;
    }
    
    //toString
    @Override
    public String toString() {
        return "Cidade{" + "cidade=" + Arrays.toString(cidade) + '}';
    }
        
    //metodos
    //adicionar um user numa posicao
    public void add(User u, int x, int y){
        this.cidade[x][y].addUser(u);
    }
    
    public void remove(User u){
        for(int i=0; i<this.getX();i++){
            for(int j=0; j<this.getY();j++){
                if(this.cidade[i][j].containsUser(u)) this.cidade[i][j].removeUser(u);     
            }
        }
    }
    
    //numero de pessoas no sistema
    public int size(){
        int res = 0;
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
               res += this.cidade[i][j].size();
            }
        }
        return res;
    }
    
    //numero de condutores no sistema
    public int sizeC_Condutores(){
        int res = 0;
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
               res += this.cidade[i][j].sizeCondutor();
            }
        }
        return res;
    }
        
    //isEmpty
    public boolean isEmpty(){
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
               if(!this.cidade[i][j].isEmpty()) return false;
            }
        }
        return true;
    }
    
    //mudar localizacao de um user
    public boolean changeUserLocation(String nome, int x, int y){
        User a= new User();
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
               a = this.cidade[i][j].consulta(nome);
               if(!a.equals(new User())) 
                   changeUserLocation2(a,x,y);
                   return true;
            }
        }
        return false;
    }
    
    public void anunciarDisponibilidade(String nome, int x, int y,String marca,String matricula){
        User aux = new User();
        aux = this.users_inscritos.consulta(nome);
        aux.setMarca(marca);
        aux.setMatricula(matricula);
        aux.setTipo(1);
        if(aux.isLogado()) this.cidade[x][y].addUser(aux);        
    }
    
    public boolean addUserC3(String nome,String password, int tipo,String marca,String matricula){
         User aux = new User(nome,password, tipo, marca, matricula);
         this.users_inscritos.addUser(aux);
         return true;
    }
    
    public boolean addUserC(String nome, String password){
        User aux = new User(nome, password);
        this.users_inscritos.addUser(aux);
        return true;
    }
    
    
    public void changeUserLocation2(User a, int x, int y){
        
        User aux = new User(a);
        this.remove(a);
        this.cidade[x][y].addUser(aux);   
    }
  
    
    //encontrar posicao atual
    public Point2D position(User a){
           
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
                if(this.cidade[i][j].containsUser(a))
                    return new Point2D.Float(i,j);
            }
        }
        return new Point2D.Float(-1,-1);
    }
    
    public Point2D position2(String a){
        User aux = new User();   
        for(int i=0; i<this.getX(); i++){
            for(int j=0; j<this.getY(); j++){
                aux = this.cidade[i][j].consulta(a);
                    return new Point2D.Float(i,j);
            }
        }
        return new Point2D.Float(-1,-1);
    }
    
    public int distancia(Point2D condutor, Point2D cliente){
        int res;
        res = abs(abs((int)condutor.getX()-(int)cliente.getX()) + abs(((int)condutor.getY()-(int)cliente.getY())));
        return res;
    }
    
    public float tempo(Point2D condutor, Point2D cliente){
        return 5*distancia(condutor,cliente);
    }
    
    //funcao que retira condutor e cliente da matriz
    //encontrar um condutor mais proximo
     public User finder2(String nome_cliente, int ox, int oy) throws CloneNotSupportedException{
        int aux = 1;
        User user = new User();
               
         if((ox <this.getX()) && (oy<this.getY())){
            user = this.cidade[(int)(ox)][(int)(oy)].findCondutor();
            if(!user.equals(new User())) 
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
        }
        
    while(aux<this.getX() || aux<this.getY()){
        if((ox-aux <this.getX()) && (oy-aux<this.getY())){
            user = this.cidade[(int)(ox)-aux][(int)(oy-aux)].findCondutor();
            if(!user.equals(new User())) 
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
        }
        else if((ox-aux <this.getX()) && (oy<this.getY())){
            user = this.cidade[((ox))-aux][(int)(oy)].findCondutor();
            if(!user.equals(new User())) {
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;}
        }
        else if((ox-aux <this.getX()) && (oy+aux<this.getY())){
            user = this.cidade[(int)(ox)-aux][(int)(oy+aux)].findCondutor();
            if(!user.equals(new User())){ 
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;}
        }
        else if((ox <this.getX()) && (oy-aux<this.getY())){
            user = this.cidade[(ox)][(int)(oy-aux)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        else if((ox <this.getX()) && (oy+aux<this.getY())){
            user = this.cidade[(int)(ox)][(int)(oy+aux)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        else if((ox+aux <this.getX()) && (oy-aux<this.getY())){
            user = this.cidade[(int)(ox)+aux][(int)(oy-aux)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        else if((ox+aux <this.getX()) && (oy-aux<this.getY())){
            user = this.cidade[(int)(ox)+aux][(int)(oy-aux)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        else if((ox+aux <this.getX()) && (oy<this.getY())){
            user = this.cidade[(int)(ox)+aux][(int)(oy)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        else if((ox+aux <this.getX()) && (oy+aux<this.getY())){
            user = this.cidade[(int)(ox)+aux][(int)(oy+aux)].findCondutor();
            if(!user.equals(new User())){
                this.viajantes.addViajantes(new Viajante(user), new Viajante(this.users_inscritos.consulta(nome_cliente)));
                return user;
            }
        }
        aux++;
    }   
        
        return user;
        
        
    }
    
    public User finder(User a) throws CloneNotSupportedException{
        int aux = 1;
        User user = new User();
        Point2D d = new Point2D.Float();
        d = this.position(a);
        
         if((d.getX() <this.getX()) && (d.getY()<this.getY())){
            user = this.cidade[(int)(d.getX())][(int)(d.getY())].findCondutor();
            if(!user.equals(new User())) return user;
        }
        
    while(aux<this.getX() || aux<this.getY()){
        if((d.getX()-aux <this.getX()) && (d.getY()-aux<this.getY())){
            user = this.cidade[(int)(d.getX())-aux][(int)(d.getY()-aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()-aux <this.getX()) && (d.getY()<this.getY())){
            user = this.cidade[(int)(d.getX())-aux][(int)(d.getY())].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()-aux <this.getX()) && (d.getY()+aux<this.getY())){
            user = this.cidade[(int)(d.getX())-aux][(int)(d.getY()+aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX() <this.getX()) && (d.getY()-aux<this.getY())){
            user = this.cidade[(int)(d.getX())][(int)(d.getY()-aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX() <this.getX()) && (d.getY()+aux<this.getY())){
            user = this.cidade[(int)(d.getX())][(int)(d.getY()+aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()+aux <this.getX()) && (d.getY()-aux<this.getY())){
            user = this.cidade[(int)(d.getX())+aux][(int)(d.getY()-aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()+aux <this.getX()) && (d.getY()-aux<this.getY())){
            user = this.cidade[(int)(d.getX())+aux][(int)(d.getY()-aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()+aux <this.getX()) && (d.getY()<this.getY())){
            user = this.cidade[(int)(d.getX())+aux][(int)(d.getY())].findCondutor();
            if(!user.equals(new User())) return user;
        }
        else if((d.getX()+aux <this.getX()) && (d.getY()+aux<this.getY())){
            user = this.cidade[(int)(d.getX())+aux][(int)(d.getY()+aux)].findCondutor();
            if(!user.equals(new User())) return user;
        }
        aux++;
    }    
        return user;
        
    }
}
