
package Facade;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class Registros {
    
    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public ResultSet getConsulta(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblventas.idventas, tblventas.totalven, tbltipopago.vchnompago, tblcliente.idcliente, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper, tblpersona.vchdomicilioper, tblventadetalle.precio_unit, tblventadetalle.cantidaddet FROM tblventas INNER JOIN tbltipopago ON tblventas.idtipopago = tbltipopago.idpago INNER JOIN tbltipoentrega ON tblventas.idtipoentrega = tbltipoentrega.idtientrega INNER JOIN tblcliente ON tblventas.idcliente = tblcliente.idcliente INNER JOIN tblpersona ON tblcliente.idpersona = tblpersona.idpersona INNER JOIN tblventadetalle ON tblventas.idventas = tblventadetalle.idventa INNER JOIN tblproductos ON tblventadetalle.idproducto = tblproductos.idproductos WHERE (tblventas.idventas = "+codigo+")";
        //String sql = "SELECT tblventas.idventas, tblventas.totalven, tbltipopago.vchnompago, tblcliente.idcliente, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper, tblpersona.vchdomicilioper, tblventadetalle.precio_unit, tblventadetalle.cantidaddet FROM tblventas INNER JOIN tbltipopago ON tblventas.idtipopago = tbltipopago.idpago INNER JOIN tbltipoentrega ON tblventas.idtipoentrega = tbltipoentrega.idtientrega INNER JOIN tblcliente ON tblventas.idcliente = tblcliente.idcliente INNER JOIN tblpersona ON tblcliente.idpersona = tblpersona.idpersona INNER JOIN tblventadetalle ON tblventas.idventas = tblventadetalle.idventa INNER JOIN tblproductos ON tblventadetalle.idproducto = tblproductos.idproductos";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public void llenarGrid(){
        BusquedaVentas frm = new BusquedaVentas();
        
        
        ResultSet rs = getConsulta();
        
        DefaultTableModel modelo=(DefaultTableModel)frm.tabla.getModel();
        frm.tabla.getSelectedRow();
        
        while(modelo.getRowCount()>0) modelo.removeRow(0);
        String Id,total,nom,ap,am,domi,pre,canti,pago;
        try{
            while(rs.next()){
                Id=rs.getString("idventas");
                total=rs.getString("totalven");
                pago=rs.getString("vchnompago");
                nom=rs.getString("vchPernomb");
                ap=rs.getString("vchapper");
                am=rs.getString("vchamper");
                domi=rs.getString("vchdomicilioper");
                pre=rs.getString("precio_unit");
                canti=rs.getString("cantidaddet");
                
                JOptionPane.showMessageDialog(null, "Total "+total+" "+"Tipo de pago "+pago+" "+"Nombre "+nom+" "+"Ap "+ap+" "+"am "+am+" "+"Domicilio "+domi+" "+" Precio"+pre+" "+"Cantidad "+canti, "Venta", JOptionPane.DEFAULT_OPTION);
                modelo.addRow(new Object []{Id,total,pago,nom,ap,am,domi,pre,canti});
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
