package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoInventario {
    public boolean registrar(VoInventario inve){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblinventario(idsucursal,idprodu)VALUES(?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,inve.getIdsucursal());
            ps.setFloat(2,inve.getIdproducto());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoInventario inve){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblinventario SET idsucursal=?,idprodu=? WHERE idinven=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,inve.getIdsucursal());
            ps.setFloat(2,inve.getIdproducto());
            ps.setFloat(3,inve.getIdinevntario());
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
        String sql = "SELECT tblinventario.idinven, tblsucursal.vchnomsucursal, tblinventario.existencia, tblproductos.vchnomproduct FROM tblinventario INNER JOIN tblsucursal ON tblinventario.idsucursal = tblsucursal.idsucursal INNER JOIN tblproductos ON tblinventario.idprodu = tblproductos.idproductos";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public ResultSet getsucursal(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idsucursal, vchnomsucursal FROM tblsucursal";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            System.out.println("consulta");
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
    
}
