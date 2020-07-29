package Controlador;
import Modelo.DAO.DaoPersonas;

import Modelo.VO.VoPersonas;

import Vista.Persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enrique
 */
public class CtrlPersonas implements ActionListener{
    private VoPersonas Vope;
    private DaoPersonas Daope;
    private Persona frm;
    
    public CtrlPersonas(VoPersonas Vope,DaoPersonas Daope,Persona frm){
       this.Vope=Vope;
       this.Daope=Daope;
       this.frm=frm;
       this.frm.btnagregar.addActionListener(this);
       this.frm.btnactualizar.addActionListener(this);
       this.frm.btneliminar.addActionListener(this);
       
    }
    
    public void iniciar(){
        frm.setTitle("Personas");
        llenarGrid();
    }
    
    private void llenarGrid(){
        ResultSet rs = Daope.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,rfc,nomp,ap,am,dom,tel,corr;
        try{
            while(rs.next()){
                Id=rs.getString("idpersona");
                rfc=rs.getString("vchrfc");
                nomp=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                dom=rs.getString("vchdomicilioper");
                tel=rs.getString("vchtelefono");
                corr=rs.getString("vchcorreo");
                
                modelo.addRow(new Object []{Id,rfc,nomp,ap,am,dom,tel,corr});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void limpiar(){
        frm.txtrfc.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            if(frm.txtam.getText().isEmpty() || frm.txtap.getText().isEmpty()|| frm.txtcorreo.getText().isEmpty()|| frm.txtdomicilio.getText().isEmpty()|| frm.txtnombre.getText().isEmpty()|| frm.txtrfc.getText().isEmpty()|| frm.txttelefono.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                Vope.setRfc(frm.txtrfc.getText());
             Vope.setNombre(frm.txtnombre.getText());
             Vope.setAp(frm.txtap.getText());
             Vope.setAm(frm.txtam.getText());
             Vope.setDomicilio(frm.txtdomicilio.getText());
             Vope.setTel(frm.txttelefono.getText());
             Vope.setCorreo(frm.txtcorreo.getText());
             if(Daope.registrar(Vope)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
            }
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txtam.getText().isEmpty() || frm.txtap.getText().isEmpty()|| frm.txtcorreo.getText().isEmpty()|| frm.txtdomicilio.getText().isEmpty()|| frm.txtnombre.getText().isEmpty()|| frm.txtrfc.getText().isEmpty()|| frm.txttelefono.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                int fila = frm.tabla.getSelectedRow();
             Vope.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             Vope.setRfc(frm.txtrfc.getText());
             Vope.setNombre(frm.txtnombre.getText());
             Vope.setAp(frm.txtap.getText());
             Vope.setAm(frm.txtam.getText());
             Vope.setDomicilio(frm.txtdomicilio.getText());
             Vope.setTel(frm.txttelefono.getText());
             Vope.setCorreo(frm.txtcorreo.getText());
             
             if(Daope.modificar(Vope)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
            }
         }
        
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
             Vope.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(Daope.eliminar(Vope)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
