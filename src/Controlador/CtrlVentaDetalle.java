
package Controlador;

import Modelo.DAO.DaoVentaDetalle;
import Modelo.VO.VoVentaDetalle;
import Vista.VentaDetalle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlVentaDetalle implements ActionListener{
    private VoVentaDetalle VoVD;
    private DaoVentaDetalle DaoVD;
    private VentaDetalle frm;
    
    public CtrlVentaDetalle(VoVentaDetalle VoVD,DaoVentaDetalle DaoVD,VentaDetalle frm){
        this.VoVD=VoVD;
        this.DaoVD=DaoVD;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
    }
    
    public void iniciar(){
        frm.setTitle("Venta Detalles");
        llenarGrid();
        llenarproductos();
        llenarinvent();
        llenarventa();
    }
    
    private void llenarproductos(){
        ResultSet rs = DaoVD.getproductos();
        try{
            while(rs.next()){
                frm.jcmbproducto.addItem(rs.getInt("idproductos")+"  "+rs.getString("vchnomproduct"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    private void llenarinvent(){
        ResultSet rs = DaoVD.getinventario();
        try{
            while(rs.next()){
                frm.jcminventario.addItem(rs.getInt("idinven")+"  "+rs.getString("vchnomsucursal"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarventa(){
        ResultSet rs = DaoVD.getventa();
        try{
            while(rs.next()){
                frm.jcmbventa.addItem(rs.getInt("idventas")+" "+rs.getString("vchPernomb")+" "+rs.getString("vchapper")+" "+rs.getString("vchamper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = DaoVD.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,vent,prec,cant,nopro;
        try{
            while(rs.next()){
                Id=rs.getString("iddetallesven");
                vent=rs.getString("idventas");
                prec=rs.getString("precio_unit");
                cant=rs.getString("cantidaddet");
                nopro=rs.getString("vchnomproduct");
                modelo.addRow(new Object []{Id,vent,prec,cant,nopro});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            if(frm.txtcantidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                //Combo de las ventas
            String ven=(frm.jcmbventa.getSelectedItem()).toString();
            String[] vent=ven.split(" ");
            for(int i = 0; i<vent.length;i++){
                System.out.println(vent[i]);
            }
            String idven;
            idven=vent[0];
            //combo productos
            String pro=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] prod=pro.split(" ");
            for(int i = 0; i<prod.length;i++){
                System.out.println(prod[i]);
            }
            String idpro;
            idpro=prod[0];
            //combo inventario
            String inv=(frm.jcminventario.getSelectedItem()).toString();
            String[] invent=inv.split(" ");
            for(int i = 0; i<invent.length;i++){
                System.out.println(invent[i]);
            }
            String idinv;
            idinv=invent[0];
            
             VoVD.setIdventa(Integer.parseInt(idven));
             VoVD.setCantidad(Float.parseFloat(frm.txtcantidad.getText()));
             VoVD.setIdprod(Integer.parseInt(idpro));
             VoVD.setIdinvent(Integer.parseInt(idinv));
             
             

             if(DaoVD.registrar(VoVD)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
            }
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txtcantidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                int fila = frm.tabla.getSelectedRow();
             VoVD.setIddetalle(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
            //Combo de las ventas
            String ven=(frm.jcmbventa.getSelectedItem()).toString();
            String[] vent=ven.split(" ");
            for(int i = 0; i<vent.length;i++){
                System.out.println(vent[i]);
            }
            String idven;
            idven=vent[0];
            //combo productos
            String pro=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] prod=pro.split(" ");
            for(int i = 0; i<prod.length;i++){
                System.out.println(prod[i]);
            }
            String idpro;
            idpro=prod[0];
            //combo inventario
            String inv=(frm.jcminventario.getSelectedItem()).toString();
            String[] invent=inv.split(" ");
            for(int i = 0; i<invent.length;i++){
                System.out.println(invent[i]);
            }
            String idinv;
            idinv=invent[0];
            
             VoVD.setIdventa(Integer.parseInt(idven));
             VoVD.setCantidad(Float.parseFloat(frm.txtcantidad.getText()));
             VoVD.setIdprod(Integer.parseInt(idpro));
             VoVD.setIdinvent(Integer.parseInt(idinv));
             
             
            
             
             if(DaoVD.modificar(VoVD)){
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
             VoVD.setIddetalle(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(DaoVD.eliminar(VoVD)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
    
}
