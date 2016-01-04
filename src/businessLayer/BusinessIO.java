/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLayer;

import java.awt.geom.Point2D;

/**
 *
 * @author Daniel
 */
public interface BusinessIO {
    //cidade
//public void add(User u, int x, int y);
//public void remove(User u);
//public int size();
public boolean changeUserLocation(String a, int x, int y);
public User finder2(String a, int ox, int oy);
public Point2D position2(String a);
public float tempo(Point2D condutor, Point2D cliente);
public void anunciarDisponibilidade(String nome, int x, int y,String marca, String matricula);
    

public boolean addUserC(String nome,String password);
public boolean addUserC3(String nome,String password, int tipo,String marca,String matricula);
public void removeViajante(String a);
public boolean loginC(String nome, String pass);

public String devolvePassageiroAux(String a);
}
