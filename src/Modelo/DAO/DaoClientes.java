package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kike6
 */
public class DaoClientes {
    public boolean registrar(VoClientes clie){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblCliente(idpersona)VALUES(?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,clie.getIdpersona());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoClientes clie){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblCliente SET idpersona=? WHERE idcliente=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,clie.getIdpersona());
            ps.setFloat(2,clie.getIdcliente());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoClientes clie){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblCliente WHERE idcliente=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,clie.getIdcliente());
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
