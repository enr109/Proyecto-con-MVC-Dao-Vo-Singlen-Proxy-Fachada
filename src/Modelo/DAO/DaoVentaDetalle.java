package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoVentaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoVentaDetalle {
    public boolean registrar(VoVentaDetalle ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblventadetalle(idventa,cantidaddet,idproducto,idinventario)VALUES(?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIdventa());
            ps.setFloat(2,ven.getCantidad());
            ps.setFloat(3,ven.getIdprod());
            ps.setFloat(4,ven.getIdinvent());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoVentaDetalle ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblventadetalle SET idventa=?,cantidaddet=?,idproducto=?,idinventario=? WHERE iddetallesven=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIdventa());
            ps.setFloat(2,ven.getCantidad());
            ps.setFloat(3,ven.getIdprod());
            ps.setFloat(4,ven.getIdinvent());
            ps.setFloat(5,ven.getIddetalle());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoVentaDetalle ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblventadetalle WHERE iddetallesven=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIddetalle());
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
        String sql = "SELECT tblventadetalle.iddetallesven, tblventas.idventas, tblventadetalle.precio_unit, tblventadetalle.cantidaddet, tblproductos.vchnomproduct FROM tblventadetalle INNER JOIN tblventas ON tblventadetalle.idventa = tblventas.idventas INNER JOIN tblproductos ON tblventadetalle.idproducto = tblproductos.idproductos";
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
    
    public ResultSet getventa(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblventas.idventas, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblventas INNER JOIN tblcliente ON tblventas.idcliente = tblcliente.idcliente INNER JOIN tblpersona ON tblcliente.idpersona = tblpersona.idpersona";
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
