/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proxylo;

/**
 *
 * @author kike6
 */
public class Proxy implements Iproxy{
    loginn x;
    
    public Proxy(){
        x= new loginn();
    }

    @Override
    public int validar_ingresar() {
        int resul;
        return resul = x.validar_ingresar();
        
    }
    
}
