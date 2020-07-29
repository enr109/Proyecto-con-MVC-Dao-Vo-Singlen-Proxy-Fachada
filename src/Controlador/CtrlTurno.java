package Controlador;

import Modelo.DAO.DaoTurno;
import Modelo.VO.VoTurno;
import Vista.Turno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enrique
 */
public class CtrlTurno implements ActionListener{
    private VoTurno Voturno;
    private DaoTurno Daturno;
    private Turno frm;
    
    public CtrlTurno(VoTurno Voturno,DaoTurno Daturno,Turno frm){
        this.Voturno=Voturno;
        this.Daturno=Daturno;
        this.frm=frm;
        this.frm.btnregistrar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        llenarGrid();
    }
    
    public void iniciar(){
        frm.setTitle("Turnos");
        llenarGrid();
    }
    
    private void llenarGrid(){
        ResultSet rs = Daturno.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,Turno,horaentrada,horasalida;
        try{
            while(rs.next()){
                Id=rs.getString("idturno");
                Turno=rs.getString("vchturno");
                horaentrada=rs.getString("horaentrada");
                horasalida=rs.getString("horasalida");
                
                modelo.addRow(new Object []{Id,Turno,horaentrada,horasalida});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void limpiar(){
        frm.txtentrada.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnregistrar){
            if(frm.txtentrada.getText().isEmpty()||frm.txtsalida.getText().isEmpty()||frm.txtturno.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                Voturno.setTurno(frm.txtturno.getText());
                Voturno.setEntrada(frm.txtentrada.getText());
                Voturno.setSalida(frm.txtsalida.getText());
                if(Daturno.registrar(Voturno)){
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    llenarGrid();
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            }
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txtentrada.getText().isEmpty()||frm.txtsalida.getText().isEmpty()||frm.txtturno.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                int fila = frm.tabla.getSelectedRow();
                Voturno.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
                Voturno.setTurno(frm.txtturno.getText());
                Voturno.setEntrada(frm.txtentrada.getText());
                Voturno.setSalida(frm.txtsalida.getText());
                if(Daturno.modificar(Voturno)){
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
             Voturno.setId(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //mod.setPrecio(Float.parseFloat(frm.txtprecio.getText()));
             
             if(Daturno.eliminar(Voturno)){
                 JOptionPane.showMessageDialog(null, "Registro Eliminar");
                 llenarGrid();
                 limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Eliminar");
             }
         }
        
        
    }
    
    
}
