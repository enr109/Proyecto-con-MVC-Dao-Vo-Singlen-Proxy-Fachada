/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;
import static Modelo.Conexion.getConnection;
import Modelo.VO.VoPersonas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class DaoPersonas {
    public boolean registrar(VoPersonas pers){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblpersona(vchrfc,vchPernomb,vchapper,vchamper,vchdomicilioper,vchtelefono,vchcorreo)VALUES(?,?,?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pers.getRfc());
            ps.setString(2,pers.getNombre());
            ps.setString(3,pers.getAp());
            ps.setString(4,pers.getAm());
            ps.setString(5,pers.getDomicilio());
            ps.setString(6,pers.getTel());
            ps.setString(7,pers.getCorreo());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoPersonas pers){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblpersona SET vchrfc=?,vchPernomb=?,vchapper=?,vchamper=?,vchdomicilioper=?,vchtelefono=?,vchcorreo=? WHERE idpersona=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pers.getRfc());
            ps.setString(2,pers.getNombre());
            ps.setString(3,pers.getAp());
            ps.setString(4,pers.getAm());
            ps.setString(5,pers.getDomicilio());
            ps.setString(6,pers.getTel());
            ps.setString(7,pers.getCorreo());
            ps.setFloat(8,pers.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoPersonas pers){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblpersona WHERE idpersona=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,pers.getId());
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
        String sql = "SELECT * FROM tblpersona";
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
