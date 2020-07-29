
package Controlador;

import Modelo.DAO.DaoEntradadetalles;
import Modelo.VO.VoEntradaDetalles;
import Vista.EntradaDetalle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlEntradadetalles implements ActionListener{
    private VoEntradaDetalles VoED;
    private DaoEntradadetalles DaoED;
    private EntradaDetalle frm;
    
    
    public CtrlEntradadetalles(VoEntradaDetalles VoED,DaoEntradadetalles DaoED,EntradaDetalle frm){
        this.VoED=VoED;
        this.DaoED=DaoED;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        
    }
    
    public void iniciar(){
        frm.setTitle("Entradas");
        llenarGrid();
        llenarproductos();
        llenarinvent();
        llenarentrada();
    }
    
    private void llenarproductos(){
        ResultSet rs = DaoED.getproductos();
        try{
            while(rs.next()){
                frm.jcmbproducto.addItem(rs.getInt("idproductos")+"  "+rs.getString("vchnomproduct"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    private void llenarinvent(){
        ResultSet rs = DaoED.getinventario();
        try{
            while(rs.next()){
                frm.jcmbinventario.addItem(rs.getInt("idinven")+"  "+rs.getString("vchnomsucursal"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarentrada(){
        ResultSet rs = DaoED.getentrad();
        try{
            while(rs.next()){
                frm.jcmbentrada.addItem(rs.getInt("identrada")+" "+rs.getString("vchPernomb")+" "+rs.getString("vchapper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = DaoED.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,entr,cost,cant,nopro;
        try{
            while(rs.next()){
                Id=rs.getString("idendetalle");
                entr=rs.getString("identrada");
                cost=rs.getString("costo");
                cant=rs.getString("cantidad");
                nopro=rs.getString("vchnomproduct");
                modelo.addRow(new Object []{Id,entr,cost,cant,nopro});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            
            if(frm.txtcantidad.getText().isEmpty() || frm.txtcosto.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                //Combo de las entradas
            String ent=(frm.jcmbentrada.getSelectedItem()).toString();
            String[] entr=ent.split(" ");
            for(int i = 0; i<entr.length;i++){
                System.out.println(entr[i]);
            }
            String ident;
            ident=entr[0];
            //combo productos
            String pro=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] prod=pro.split(" ");
            for(int i = 0; i<prod.length;i++){
                System.out.println(prod[i]);
            }
            String idpro;
            idpro=prod[0];
            //combo inventario
            String inv=(frm.jcmbinventario.getSelectedItem()).toString();
            String[] invent=inv.split(" ");
            for(int i = 0; i<invent.length;i++){
                System.out.println(invent[i]);
            }
            String idinv;
            idinv=invent[0];
            
             VoED.setIdentrada(Integer.parseInt(ident));
             VoED.setCosto(Float.parseFloat(frm.txtcosto.getText()));
             VoED.setCantidad(Float.parseFloat(frm.txtcantidad.getText()));
             VoED.setIdproducto(Integer.parseInt(idpro));
             VoED.setIdventa(Integer.parseInt(idinv));
             

             if(DaoED.registrar(VoED)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
            }
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txtcantidad.getText().isEmpty() || frm.txtcosto.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
             int fila = frm.tabla.getSelectedRow();
             VoED.setIddetall(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             //Combo de los pago
            //Combo de las entradas
            String ent=(frm.jcmbentrada.getSelectedItem()).toString();
            String[] entr=ent.split(" ");
            for(int i = 0; i<entr.length;i++){
                System.out.println(entr[i]);
            }
            String ident;
            ident=entr[0];
            //combo productos
            String pro=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] prod=pro.split(" ");
            for(int i = 0; i<prod.length;i++){
                System.out.println(prod[i]);
            }
            String idpro;
            idpro=prod[0];
            //combo inventario
            String inv=(frm.jcmbinventario.getSelectedItem()).toString();
            String[] invent=inv.split(" ");
            for(int i = 0; i<invent.length;i++){
                System.out.println(invent[i]);
            }
            String idinv;
            idinv=invent[0];
            
             VoED.setIdentrada(Integer.parseInt(ident));
             VoED.setCosto(Float.parseFloat(frm.txtcosto.getText()));
             VoED.setCantidad(Float.parseFloat(frm.txtcantidad.getText()));
             VoED.setIdproducto(Integer.parseInt(idpro));
             VoED.setIdventa(Integer.parseInt(idinv));
             
            
             
             if(DaoED.modificar(VoED)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }   
            }
         }
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             VoED.setIddetall(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(DaoED.eliminar(VoED)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
