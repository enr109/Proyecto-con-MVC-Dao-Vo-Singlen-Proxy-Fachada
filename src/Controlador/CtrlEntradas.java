package Controlador;

import Modelo.DAO.DaoEntradas;
import Modelo.VO.VoEntradas;
import Vista.Entradas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlEntradas implements ActionListener{
    private VoEntradas VoEmp;
    private DaoEntradas DaoEm;
    private Entradas frm;
    
    public CtrlEntradas(VoEntradas VoEmp,DaoEntradas DaoEm,Entradas frm){
        this.VoEmp=VoEmp;
        this.DaoEm=DaoEm;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        
    }
    
    public void iniciar(){
        frm.setTitle("Entradas");
        llenarGrid();
        llenarpago();
        llenaremple();
        llenarprodu();
    }
    
    private void llenarpago(){
        ResultSet rs = DaoEm.getpago();
        try{
            while(rs.next()){
                frm.jcmbpago.addItem(rs.getInt("idpago")+"  "+rs.getString("vchnompago"));
                System.out.println("consulta");
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    private void llenaremple(){
        ResultSet rs = DaoEm.getempleados();
        try{
            while(rs.next()){
                frm.jcmbempleado.addItem(rs.getInt("idempleado")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarprodu(){
        ResultSet rs = DaoEm.getproductor();
        try{
            while(rs.next()){
                frm.jcmbproductor.addItem(rs.getInt("idproducto")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = DaoEm.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,fech,nom,ap,am,total,pago;
        try{
            while(rs.next()){
                Id=rs.getString("identrada");
                fech=rs.getString("vchfecha");
                total=rs.getString("totalen");
                pago=rs.getString("vchnompago");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                modelo.addRow(new Object []{Id,fech,total,pago,nom,ap,am});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            //Combo de los pago
            String pag=(frm.jcmbpago.getSelectedItem()).toString();
            String[] pagos=pag.split(" ");
            for(int i = 0; i<pagos.length;i++){
                System.out.println(pagos[i]);
            }
            String idpag;
            idpag=pagos[0];
            //combo empleados
            String empl=(frm.jcmbempleado.getSelectedItem()).toString();
            String[] empleado=empl.split(" ");
            for(int i = 0; i<empleado.length;i++){
                System.out.println(empleado[i]);
            }
            String idem;
            idem=empleado[0];
            //combo product
            String produ=(frm.jcmbproductor.getSelectedItem()).toString();
            String[] product=produ.split(" ");
            for(int i = 0; i<product.length;i++){
                System.out.println(product[i]);
            }
            String idpro;
            idpro=product[0];
            
            
            
            
             VoEmp.setIdpago(Integer.parseInt(idpag));
             VoEmp.setIdempleado(Integer.parseInt(idem));
             VoEmp.setIdproduct(Integer.parseInt(idpro));
             

             if(DaoEm.registrar(VoEmp)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
         }
        
        if(e.getSource()== frm.btnactualizar){
            int fila = frm.tabla.getSelectedRow();
             VoEmp.setIdentra(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             //Combo de los pago
            String pag=(frm.jcmbpago.getSelectedItem()).toString();
            String[] pagos=pag.split(" ");
            for(int i = 0; i<pagos.length;i++){
                System.out.println(pagos[i]);
            }
            String idpag;
            idpag=pagos[0];
            //combo empleados
            String empl=(frm.jcmbempleado.getSelectedItem()).toString();
            String[] empleado=empl.split(" ");
            for(int i = 0; i<empleado.length;i++){
                System.out.println(empleado[i]);
            }
            String idem;
            idem=empleado[0];
            //combo product
            String produ=(frm.jcmbproductor.getSelectedItem()).toString();
            String[] product=produ.split(" ");
            for(int i = 0; i<product.length;i++){
                System.out.println(product[i]);
            }
            String idpro;
            idpro=product[0];
            
            
            
            
             VoEmp.setIdpago(Integer.parseInt(idpag));
             VoEmp.setIdempleado(Integer.parseInt(idem));
             VoEmp.setIdproduct(Integer.parseInt(idpro));
             
            
             
             if(DaoEm.modificar(VoEmp)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
         }
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             VoEmp.setIdentra(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(DaoEm.eliminar(VoEmp)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
        
    }
    
}
