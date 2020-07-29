
package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoEntradaDetalles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoEntradadetalles {
    public boolean registrar(VoEntradaDetalles ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblentrada_detalles(identrada,costo,cantidad,idproducto,idinventa)VALUES(?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIdentrada());
            ps.setFloat(2,ent.getCosto());
            ps.setFloat(3,ent.getCantidad());
            ps.setFloat(4,ent.getIdproducto());
            ps.setFloat(5,ent.getIdventa());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoEntradaDetalles ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblentrada_detalles SET identrada=?,costo=?,cantidad=?,idproducto=?,idinventa=? WHERE idendetalle=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIdentrada());
            ps.setFloat(2,ent.getCosto());
            ps.setFloat(3,ent.getCantidad());
            ps.setFloat(4,ent.getIdproducto());
            ps.setFloat(5,ent.getIdventa());
            ps.setFloat(6,ent.getIddetall());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoEntradaDetalles ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblentrada_detalles WHERE idendetalle=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIddetall());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public ResultSet getConsulta(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblentrada_detalles.idendetalle, tblentrada.identrada, tblentrada_detalles.costo, tblentrada_detalles.cantidad, tblproductos.vchnomproduct FROM tblentrada_detalles INNER JOIN tblentrada ON tblentrada_detalles.identrada = tblentrada.identrada INNER JOIN tblproductos ON tblentrada_detalles.idproducto = tblproductos.idproductos";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public ResultSet getproductos(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idproductos, vchnomproduct FROM tblproductos";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    public ResultSet getentrad(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblentrada.identrada, tblproductor.idproductor, tblpersona.vchPernomb, tblpersona.vchapper FROM tblentrada INNER JOIN tblproductor ON tblentrada.idproductor = tblproductor.idproductor INNER JOIN tblpersona ON tblproductor.idpersona = tblpersona.idpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    
    public ResultSet getinventario(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblinventario.idinven, tblsucursal.vchnomsucursal FROM tblinventario INNER JOIN tblsucursal ON tblinventario.idsucursal = tblsucursal.idsucursal";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
}
