package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoProductores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoProductor {
    public boolean registrar(VoProductores produc){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblproductor(idproducto,idpersona)VALUES(?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,produc.getIdproducto());
            ps.setFloat(2,produc.getIdpersona());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoProductores produc){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblproductor SET idproducto=?,idpersona=? WHERE idproductor=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,produc.getIdproducto());
            ps.setFloat(2,produc.getIdpersona());
            ps.setFloat(3,produc.getIdproductor());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoProductores produc){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblproductor WHERE idproductor=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,produc.getIdproductor());
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
        String sql = "SELECT tblproductor.idproductor, tblproductos.vchnomproduct, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblproductor INNER JOIN tblproductos ON tblproductor.idproducto = tblproductos.idproductos INNER JOIN tblpersona ON tblproductor.idpersona = tblpersona.idpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public ResultSet getprodu(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idproductos, vchnomproduct FROM tblproductos";
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
    
    
    public ResultSet getpersona(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idpersona, vchPernomb, vchapper, vchamper FROM tblpersona";
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
