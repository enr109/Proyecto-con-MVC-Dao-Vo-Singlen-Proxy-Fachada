
package Controlador;

import Modelo.DAO.DaoInventario;
import Modelo.VO.VoInventario;
import Vista.Inventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class CtrlInventario implements ActionListener{
    private VoInventario VoIn;
    private DaoInventario Daoin;
    private Inventario frm;
    
    public CtrlInventario(VoInventario VoIn,DaoInventario Daoin,Inventario frm){
        this.VoIn=VoIn;
        this.Daoin=Daoin;
        this.frm=frm;
        this.frm.btnagregar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        
    }
    
    public void iniciar(){
        frm.setTitle("Inventario");
        llenarGrid();
        llenarsucursal();
        llenarproductos();
    }
    
     private void llenarsucursal(){
        ResultSet rs = Daoin.getsucursal();
        try{
            while(rs.next()){
                frm.jcmbsucursal.addItem(rs.getInt("idsucursal")+"  "+rs.getString("vchnomsucursal"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    private void llenarproductos(){
        ResultSet rs = Daoin.getproductos();
        try{
            while(rs.next()){
                frm.jcmbproducto.addItem(rs.getInt("idproductos")+"  "+rs.getString("vchnomproduct"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void llenarGrid(){
        ResultSet rs = Daoin.getConsulta();
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,nom,exis,am,nomp;
        try{
            while(rs.next()){
                Id=rs.getString("idinven");
                nom=rs.getString("vchnomsucursal");
                exis=rs.getString("existencia");
                nomp=rs.getString("vchnomproduct");
                modelo.addRow(new Object []{Id,nom,exis,nomp});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== frm.btnagregar){
            //Combo de los sucursal
            String suc=(frm.jcmbsucursal.getSelectedItem()).toString();
            String[] sucur=suc.split(" ");
            for(int i = 0; i<sucur.length;i++){
                System.out.println(sucur[i]);
            }
            String idsu;
            idsu=sucur[0];
            //combo producto
            String prod=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] produc=prod.split(" ");
            for(int i = 0; i<produc.length;i++){
                System.out.println(produc[i]);
            }
            String idpro;
            idpro=produc[0];
            
            
            
            
            
             VoIn.setIdsucursal(Integer.parseInt(idsu));
             VoIn.setIdproducto(Integer.parseInt(idpro));
             

             if(Daoin.registrar(VoIn)){
                 JOptionPane.showMessageDialog(null, "Registro Guardado");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Guardar");
             }
         }
        
        if(e.getSource()== frm.btnactualizar){
            int fila = frm.tabla.getSelectedRow();
             VoIn.setIdinevntario(Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString()));
             //Combo de los sucursal
            String suc=(frm.jcmbsucursal.getSelectedItem()).toString();
            String[] sucur=suc.split(" ");
            for(int i = 0; i<sucur.length;i++){
                System.out.println(sucur[i]);
            }
            String idsu;
            idsu=sucur[0];
            //combo producto
            String prod=(frm.jcmbproducto.getSelectedItem()).toString();
            String[] produc=prod.split(" ");
            for(int i = 0; i<produc.length;i++){
                System.out.println(produc[i]);
            }
            String idpro;
            idpro=produc[0];
            
            
            
            
            
             VoIn.setIdsucursal(Integer.parseInt(idsu));
             VoIn.setIdproducto(Integer.parseInt(idpro));
             
            
             
             if(Daoin.modificar(VoIn)){
                 JOptionPane.showMessageDialog(null, "Registro Modificar");
                 llenarGrid();
                 //limpiar();
             }else{
                 JOptionPane.showMessageDialog(null, "Error al Modificar");
             }
         }
    }
    
    
    
}
