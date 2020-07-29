/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DaoProductor;
import Modelo.VO.VoProductores;
import Vista.Productores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlProductor implements ActionListener{
    private VoProductores VoPro;
    private DaoProductor Daopro;
    private Productores frm;
    
    public CtrlProductor(VoProductores VoPro,DaoProductor Daopro,Productores frm){
        this.VoPro=VoPro;
        this.Daopro=Daopro;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        
        
    }
    
    public void iniciar(){
        frm.setTitle("Productores");
        llenarGrid();
        llenarprodu();
        llenarperso();
        
    }
    
    private void llenarprodu(){
        ResultSet rs = Daopro.getprodu();
        try{
            while(rs.next()){
                frm.jcmbproductos.addItem(rs.getInt("idproductos")+"  "+rs.getString("vchnomproduct"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarperso(){
        ResultSet rs = Daopro.getpersona();
        try{
            while(rs.next()){
                frm.jcmbpersona.addItem(rs.getInt("idpersona")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = Daopro.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,us,nom,ap,am;
        try{
            while(rs.next()){
                Id=rs.getString("idproductor");
                us=rs.getString("vchnomproduct");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                
                
                modelo.addRow(new Object []{Id,us,nom,ap,am});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            //Combo de los produc
            String prod=(frm.jcmbproductos.getSelectedItem()).toString();
            String[] produc=prod.split(" ");
            for(int i = 0; i<produc.length;i++){
                System.out.println(produc[i]);
            }
            String idpro;
            idpro=produc[0];
            //combo personas
            String pers=(frm.jcmbpersona.getSelectedItem()).toString();
            String[] person=pers.split(" ");
            for(int i = 0; i<person.length;i++){
                System.out.println(person[i]);
            }
            String idper;
            idper=person[0];
            
            
            
             VoPro.setIdproducto(Integer.parseInt(idpro));
             VoPro.setIdpersona(Integer.parseInt(idper));
             
             

             if(Daopro.registrar(VoPro)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
         }
        
        if(e.getSource()== frm.btnactualizar){
            int fila = frm.tabla.getSelectedRow();
             VoPro.setIdproductor(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
            //Combo de los produc
            String prod=(frm.jcmbproductos.getSelectedItem()).toString();
            String[] produc=prod.split(" ");
            for(int i = 0; i<produc.length;i++){
                System.out.println(produc[i]);
            }
            String idpro;
            idpro=produc[0];
            //combo personas
            String pers=(frm.jcmbpersona.getSelectedItem()).toString();
            String[] person=pers.split(" ");
            for(int i = 0; i<person.length;i++){
                System.out.println(person[i]);
            }
            String idper;
            idper=person[0];
            
            
            
             VoPro.setIdproducto(Integer.parseInt(idpro));
             VoPro.setIdpersona(Integer.parseInt(idper));
             
             
             if(Daopro.modificar(VoPro)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
         }
        
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             VoPro.setIdproductor(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(Daopro.eliminar(VoPro)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
