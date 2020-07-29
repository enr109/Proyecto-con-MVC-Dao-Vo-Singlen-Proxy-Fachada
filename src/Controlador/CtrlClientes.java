
package Controlador;

import Modelo.DAO.DaoClientes;
import Modelo.VO.VoClientes;
import Vista.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlClientes implements ActionListener{
    private VoClientes VoCli;
    private DaoClientes DaoCli;
    private Clientes frm;
    
    public CtrlClientes(VoClientes VoCli,DaoClientes DaoCli,Clientes frm){
        this.VoCli=VoCli;
        this.DaoCli=DaoCli;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        
    }
    
    public void iniciar(){
        frm.setTitle("Empleados");
        llenarGrid();
        llenarperso();
        
    }
    
    private void llenarperso(){
        ResultSet rs = DaoCli.getpersona();
        try{
            while(rs.next()){
                frm.jcmbpersona.addItem(rs.getInt("idpersona")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = DaoCli.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,nom,ap,am;
        try{
            while(rs.next()){
                Id=rs.getString("idcliente");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                
                
                modelo.addRow(new Object []{Id,nom,ap,am});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            
            //combo personas
            String pers=(frm.jcmbpersona.getSelectedItem()).toString();
            String[] person=pers.split(" ");
            for(int i = 0; i<person.length;i++){
                System.out.println(person[i]);
            }
            String idper;
            idper=person[0];
            
            
            
             
             VoCli.setIdpersona(Integer.parseInt(idper));
             
             

             if(DaoCli.registrar(VoCli)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
         }
        
        if(e.getSource()== frm.btnactualizar){
            int fila = frm.tabla.getSelectedRow();
             VoCli.setIdcliente(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
            //combo personas
            String pers=(frm.jcmbpersona.getSelectedItem()).toString();
            String[] person=pers.split(" ");
            for(int i = 0; i<person.length;i++){
                System.out.println(person[i]);
            }
            String idper;
            idper=person[0];
            
            
            
             
             VoCli.setIdpersona(Integer.parseInt(idper));
             
             
             if(DaoCli.modificar(VoCli)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
         }
        
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             VoCli.setIdcliente(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(DaoCli.eliminar(VoCli)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
