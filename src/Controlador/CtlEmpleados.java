package Controlador;
import Modelo.DAO.DaoEmpleados;
import Modelo.VO.VoEmpleados;
import Modelo.VO.VoComboT;
import Vista.Empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CtlEmpleados implements ActionListener{
    private VoEmpleados VoEmp;
    private DaoEmpleados DaoEm;
    private Empleados frm;
    
    
    
    public CtlEmpleados (VoEmpleados VoEmp,DaoEmpleados DaoEm,Empleados frm){
        this.VoEmp=VoEmp;
        this.DaoEm=DaoEm;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
    }
    
    
    
    /*public final void events(){
     frm.jcmbturno.addActionListener(this);
    }*/
    public void iniciar(){
        frm.setTitle("Empleados");
        llenarGrid();
        llenarpuesto();
        llenarturno();
        llenarsucursal();
        llenarpersona();
        
    }
    
    private void llenarpuesto(){
        ResultSet rs = DaoEm.getpuesto();
        try{
            while(rs.next()){
                frm.jcbpuesto.addItem(rs.getInt("idpuesto")+"  "+rs.getString("vchpuesto"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    private void llenarturno(){
        ResultSet rs = DaoEm.getturno();
        try{
            while(rs.next()){
                frm.jcmbturno.addItem(rs.getInt("idturno")+"  "+rs.getString("vchturno"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void llenarsucursal(){
        ResultSet rs = DaoEm.getsucursal();
        try{
            while(rs.next()){
                frm.jcmbsucursal.addItem(rs.getInt("idsucursal")+"  "+rs.getString("vchnomsucursal"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarpersona(){
        ResultSet rs = DaoEm.getpersona();
        try{
            while(rs.next()){
                frm.jcmbpersona.addItem(rs.getInt("idpersona")+"  "+rs.getString("vchPernomb")+"  "+rs.getString("vchapper")+"  "+rs.getString("vchamper")+"  "+rs.getString("vchcorreo"));
                
                
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
        String Id,us,nom,ap,am,pues,tur,sucu,direc;
        try{
            while(rs.next()){
                Id=rs.getString("idempleado");
                us=rs.getString("vchusuario");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                pues=rs.getString("vchpuesto");
                tur=rs.getString("vchturno");
                sucu=rs.getString("vchnomsucursal");
                direc=rs.getString("vchsucdireccion");
                
                
                modelo.addRow(new Object []{Id,us,nom,ap,am,pues,tur,sucu,direc});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    EmailSenderService email = new EmailSenderService();
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            if(frm.txtcontraseña.getText().isEmpty()|| frm.txtusuario.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }
            else{
                if(frm.txtcontraseña.getText().length()<8){
                JOptionPane.showMessageDialog(null, "la contraseña debe ser mayor a 8 digitos", "Alerta", 0);
                //JOptionPane.showMessageDialog(null,"la contraseña debe ser mayor a 8 digitos ", JOptionPane.DEFAULT_OPTION);
            }else{
                    //Combo de los turnos
                    String turn=(frm.jcmbturno.getSelectedItem()).toString();
                    String[] turnos=turn.split(" ");
                    for(int i = 0; i<turnos.length;i++){
                        System.out.println(turnos[i]);
                    }
                    String idtur;
                    idtur=turnos[0];
                    //combo personas
                    String pers=(frm.jcmbpersona.getSelectedItem()).toString();
                    String[] person=pers.split(" ");
                    for(int i = 0; i<person.length;i++){
                        System.out.println(person[i]);
                    }
                    String idper;
                    idper=person[0];
                    String correo,nom,ap,am;
                    nom=person[2];
                    ap=person[4];
                    am=person[6];
                    correo=person[8];
                    
                    //combo puesto
                    String pues=(frm.jcbpuesto.getSelectedItem()).toString();
                    String[] puest=pues.split(" ");
                    for(int i = 0; i<puest.length;i++){
                        System.out.println(puest[i]);
                    }
                    String idpues;
                    idpues=puest[0];
                    //combo sucursal
                    String suc=(frm.jcmbsucursal.getSelectedItem()).toString();
                    String[] sucur=suc.split(" ");
                    for(int i = 0; i<sucur.length;i++){                
                        System.out.println(sucur[i]);
                    }
                    String idsucu;
                    idsucu=sucur[0];
                    VoEmp.setUsuario(frm.txtusuario.getText());
                    VoEmp.setContraseña(frm.txtcontraseña.getText());
                    VoEmp.setPersona(Integer.parseInt(idper));
                    VoEmp.setPuesto(Integer.parseInt(idpues));
                    VoEmp.setTurno(Integer.parseInt(idtur));
                    VoEmp.setSucursal(Integer.parseInt(idsucu));
                    
                    if(DaoEm.registrar(VoEmp)){
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                        llenarGrid();
                        
                        email.inseremail(correo, nom, ap, am);
                        
                        //limpiar();
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al Guardar");
                    }
                }
            }
            
         }
        
        if(e.getSource()== frm.btnactualizar){
            if(frm.txtcontraseña.getText().isEmpty()|| frm.txtusuario.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Llenar los campos Vacios", "Alerta", 0);
            }else{
                if(frm.txtcontraseña.getText().length()<8){
                JOptionPane.showMessageDialog(null, "la contraseña debe ser mayor a 8 digitos", "Alerta", 0);
                //JOptionPane.showMessageDialog(null,"la contraseña debe ser mayor a 8 digitos ", JOptionPane.DEFAULT_OPTION);
            }else{
                int fila = frm.tabla.getSelectedRow();
                System.out.print("fila");
                System.out.print(fila);
             VoEmp.setEmpleados(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             
             //Combo de los turnos
            String turn=(frm.jcmbturno.getSelectedItem()).toString();
            String[] turnos=turn.split(" ");
            for(int i = 0; i<turnos.length;i++){
                System.out.println(turnos[i]);
            }
            String idtur;
            idtur=turnos[0];
            //combo personas
            String pers=(frm.jcmbpersona.getSelectedItem()).toString();
            String[] person=pers.split(" ");
            for(int i = 0; i<person.length;i++){
                System.out.println(person[i]);
            }
            String idper;
            idper=person[0];
            String correo,nom,ap,am;
            nom=person[2];
            ap=person[4];
            am=person[6];
            correo=person[8];
            //combo puesto
            String pues=(frm.jcbpuesto.getSelectedItem()).toString();
            String[] puest=pues.split(" ");
            for(int i = 0; i<puest.length;i++){
                System.out.println(puest[i]);
            }
            String idpues;
            idpues=puest[0];
            
            //combo sucursal
            String suc=(frm.jcmbsucursal.getSelectedItem()).toString();
            String[] sucur=suc.split(" ");
            for(int i = 0; i<sucur.length;i++){
                System.out.println(sucur[i]);
            }
            String idsucu;
            idsucu=sucur[0];
            
            
             VoEmp.setUsuario(frm.txtusuario.getText());
             VoEmp.setContraseña(frm.txtcontraseña.getText());
             VoEmp.setPersona(Integer.parseInt(idper));
             VoEmp.setPuesto(Integer.parseInt(idpues));
             VoEmp.setTurno(Integer.parseInt(idtur));
             VoEmp.setSucursal(Integer.parseInt(idsucu));
             if(DaoEm.modificar(VoEmp)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 email.updemail(correo, nom, ap, am);
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
            }
            }
            
         }
        
        
        if(e.getSource()== frm.btneliminar){
            int fila = frm.tabla.getSelectedRow();
            if(fila==00){
                JOptionPane.showMessageDialog(null, "Seleccione una fila", "Alerta", 0);
            }else{
                VoEmp.setEmpleados(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
                if(DaoEm.eliminar(VoEmp)){
                    JOptionPane.showMessageDialog(null, "Registro Eliminar");
                    llenarGrid();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                }
            }
         }
    }

    
    
}
