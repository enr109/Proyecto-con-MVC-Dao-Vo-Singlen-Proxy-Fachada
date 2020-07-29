
package Controlador;

import Modelo.DAO.DaoVentas;
import Modelo.VO.VoVentas;
import Vista.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlVentas implements ActionListener{
    private VoVentas VoVe;
    private DaoVentas DaoVe;
    private Ventas frm;
    
    public CtrlVentas(VoVentas VoVe,DaoVentas DaoVe,Ventas frm){
        this.VoVe=VoVe;
        this.DaoVe=DaoVe;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        
    }
    public void iniciar(){
        frm.setTitle("Ventas");
        llenarGrid();
        llenarpago();
        llenaremple();
        llenarentrega();
        llenarclient();
    }
    
    private void llenarpago(){
        ResultSet rs = DaoVe.getpago();
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
        ResultSet rs = DaoVe.getempleados();
        try{
            while(rs.next()){
                frm.jcmbempleado.addItem(rs.getInt("idempleado")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarentrega(){
        ResultSet rs = DaoVe.getentrega();
        try{
            while(rs.next()){
                frm.jcmbtipentrega.addItem(rs.getInt("idtientrega")+"  "+rs.getString("vchentrega"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarclient(){
        ResultSet rs = DaoVe.getcliente();
        try{
            while(rs.next()){
                frm.jcmclientes.addItem(rs.getInt("idcliente")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = DaoVe.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,nom,ap,am,total,pago,ent;
        try{
            while(rs.next()){
                Id=rs.getString("idventas");
                total=rs.getString("totalven");
                pago=rs.getString("vchnompago");
                ent=rs.getString("vchentrega");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper"); 
                modelo.addRow(new Object []{Id,total,pago,ent,nom,ap,am});
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
            //combo entrega
            String ent=(frm.jcmbtipentrega.getSelectedItem()).toString();
            String[] entre=ent.split(" ");
            for(int i = 0; i<entre.length;i++){
                System.out.println(entre[i]);
            }
            String iden;
            iden=entre[0];
            
            //combo cliente
            String cli=(frm.jcmbtipentrega.getSelectedItem()).toString();
            String[] client=cli.split(" ");
            for(int i = 0; i<client.length;i++){
                System.out.println(client[i]);
            }
            String idcli;
            idcli=client[0];
            
            
            
            
             VoVe.setIdoago(Integer.parseInt(idpag));
             VoVe.setIdemple(Integer.parseInt(idem));
             VoVe.setIdtipentrega(Integer.parseInt(iden));
             VoVe.setIdcliente(Integer.parseInt(idcli));
             VoVe.setIdpedido(Integer.parseInt("1"));
             

             if(DaoVe.registrar(VoVe)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
         }
        
        if(e.getSource()== frm.btnactualizar){
            int fila = frm.tabla.getSelectedRow();
             VoVe.setIdvent(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
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
            //combo entrega
            String ent=(frm.jcmbtipentrega.getSelectedItem()).toString();
            String[] entre=ent.split(" ");
            for(int i = 0; i<entre.length;i++){
                System.out.println(entre[i]);
            }
            String iden;
            iden=entre[0];
            
            //combo cliente
            String cli=(frm.jcmbtipentrega.getSelectedItem()).toString();
            String[] client=cli.split(" ");
            for(int i = 0; i<client.length;i++){
                System.out.println(client[i]);
            }
            String idcli;
            idcli=client[0];
            
            
            
            
             VoVe.setIdoago(Integer.parseInt(idpag));
             VoVe.setIdemple(Integer.parseInt(idem));
             VoVe.setIdtipentrega(Integer.parseInt(iden));
             VoVe.setIdcliente(Integer.parseInt(idcli));
             VoVe.setIdpedido(Integer.parseInt("1"));
             
            
             
             if(DaoVe.modificar(VoVe)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
         }
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             VoVe.setIdvent(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(DaoVe.eliminar(VoVe)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
