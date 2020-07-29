package Modelo.DAO;

import Modelo.Conexion;
import Modelo.VO.VoPuestos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kike6
 */
public class DaoPuestos extends Conexion{
    
    public boolean registrar(VoPuestos pues){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblpuesto(vchpuesto)VALUES(?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pues.getNompuesto());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoPuestos pues){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblpuesto SET vchpuesto=? WHERE idpuesto=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pues.getNompuesto());
            ps.setFloat(2,pues.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoPuestos pues){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblpuesto WHERE idpuesto=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,pues.getId());
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
        String sql = "SELECT * FROM tblpuesto";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public boolean mostr(VoPuestos pues){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection();
        
        String sql = "SELECT * FROM tblpuesto WHERE idpuesto=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,pues.getId());
            rs= ps.executeQuery();
            
            if(rs.next()){
                pues.setId(Integer.parseInt(rs.getString("idpuesto")));
                pues.setNompuesto(rs.getString("vchpuesto"));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
