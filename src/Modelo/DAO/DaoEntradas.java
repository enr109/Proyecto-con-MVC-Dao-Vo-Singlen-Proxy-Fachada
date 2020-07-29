package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoEntradas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author kike6
 */
public class DaoEntradas {
    public boolean registrar(VoEntradas ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblentrada(idtipopago,idempleado,idproductor)VALUES(?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIdpago());
            ps.setFloat(2,ent.getIdempleado());
            ps.setFloat(3,ent.getIdproduct());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoEntradas ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblentrada SET idtipopago=?,idempleado=?,idproductor=? WHERE identrada=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIdpago());
            ps.setFloat(2,ent.getIdempleado());
            ps.setFloat(3,ent.getIdproduct());
            ps.setFloat(4,ent.getIdentra());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoEntradas ent){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblentrada WHERE identrada=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ent.getIdentra());
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
        String sql = "SELECT tblentrada.identrada, tblentrada.vchfecha, tblentrada.totalen, tbltipopago.vchnompago, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblentrada INNER JOIN tbltipopago ON tblentrada.idtipopago = tbltipopago.idpago INNER JOIN tblproductor ON tblentrada.idproductor = tblproductor.idproductor INNER JOIN tblpersona ON tblproductor.idpersona = tblpersona.idpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
            
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public ResultSet getpago(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idpago,vchnompago FROM tbltipopago";
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
    
    
    public ResultSet getempleados(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblempleados.idempleado, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblempleados INNER JOIN tblpersona ON tblempleados.idpersona = tblpersona.idpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    public ResultSet getproductor(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblproductor.idproducto, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblproductor INNER JOIN tblpersona ON tblproductor.idpersona = tblpersona.idpersona";
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
