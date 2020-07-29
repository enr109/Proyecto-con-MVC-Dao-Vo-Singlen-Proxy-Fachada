package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoVentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoVentas {
    
    public boolean registrar(VoVentas ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblventas(idtipopago,idpedido,idempleado,idtipoentrega,idcliente)VALUES(?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIdoago());
            ps.setFloat(2,ven.getIdpedido());
            ps.setFloat(3,ven.getIdemple());
            ps.setFloat(4,ven.getIdtipentrega());
            ps.setFloat(5,ven.getIdcliente());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoVentas ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblventas SET idtipopago=?,idpedido=?,idempleado=?,idtipoentrega=?,idcliente=? WHERE idventas=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIdoago());
            ps.setFloat(2,ven.getIdpedido());
            ps.setFloat(3,ven.getIdemple());
            ps.setFloat(4,ven.getIdtipentrega());
            ps.setFloat(5,ven.getIdcliente());
            ps.setFloat(6,ven.getIdvent());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoVentas ven){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblventas WHERE idventas=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,ven.getIdvent());
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
        String sql = "SELECT tblventas.idventas, tblventas.totalven, tbltipopago.vchnompago, tbltipoentrega.vchentrega, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblventas INNER JOIN tbltipopago ON tblventas.idtipopago = tbltipopago.idpago INNER JOIN tbltipoentrega ON tblventas.idtipoentrega = tbltipoentrega.idtientrega INNER JOIN tblcliente ON tblventas.idcliente = tblcliente.idcliente INNER JOIN tblpersona ON tblcliente.idpersona = tblpersona.idpersona";
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
    
    public ResultSet getentrega(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idtientrega, vchentrega FROM tbltipoentrega";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    public ResultSet getcliente(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT tblcliente.idcliente, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper FROM tblcliente INNER JOIN tblpersona ON tblcliente.idpersona = tblpersona.idpersona";
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
