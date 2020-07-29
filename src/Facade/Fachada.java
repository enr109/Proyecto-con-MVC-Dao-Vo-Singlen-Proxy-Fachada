/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class Fachada {
    private Registros registros;
    private BusquedaVentas frm;
    
    public Fachada(String codigo,BusquedaVentas frm){
        super();
        this.frm=frm;
        registros = new Registros();
        registros.setCodigo(Integer.parseInt(codigo));
    }
    
    public void mostrar(){
        registros.llenarGrid();
    }
    
    
    
    
}
