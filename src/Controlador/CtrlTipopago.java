package Controlador;

import Modelo.DAO.DaoTipopago;
import Modelo.VO.VoTipopago;
import Vista.Tipopagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enrique
 */
public class CtrlTipopago implements ActionListener{
    private VoTipopago Voti;
    private DaoTipopago Daoti;
    private Tipopagos frm;
    
    public CtrlTipopago(VoTipopago Voti,DaoTipopago Daoti,Tipopagos frm){
        this.Voti=Voti;
        this.Daoti=Daoti;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
    }
    public void iniciar(){
        frm.setTitle("Tipo de entregas");
        llenarGrid();
    }
    
    private void llenarGrid(){
        ResultSet rs = Daoti.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,tipo;
        try{
            while(rs.next()){
                Id=rs.getString("idpago");
                tipo=rs.getString("vchnompago");
                
                modelo.addRow(new Object []{Id,tipo});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void limpiar(){
        frm.txttipopago.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            if(frm.txttipopago.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                Voti.setTipopag(frm.txttipopago.getText());
             if(Daoti.registrar(Voti)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
            }
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txttipopago.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                int fila = frm.tabla.getSelectedRow();
             Voti.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             Voti.setTipopag(frm.txttipopago.getText());
             
             if(Daoti.modificar(Voti)){
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
             Voti.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(Daoti.eliminar(Voti)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
    }
    
}
