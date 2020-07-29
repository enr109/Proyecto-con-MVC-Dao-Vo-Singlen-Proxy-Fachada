package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoTurno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class DaoTurno {
    public boolean registrar(VoTurno turn){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblturno(vchturno,horaentrada,horasalida)VALUES(?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,turn.getTurno());
            ps.setString(2,turn.getEntrada());
            ps.setString(3,turn.getSalida());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoTurno turn){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblturno SET vchturno=?,horaentrada=?,horasalida=? WHERE idturno=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,turn.getTurno());
            ps.setString(2,turn.getEntrada());
            ps.setString(3,turn.getSalida());
            ps.setFloat(4,turn.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoTurno turn){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblturno WHERE idturno=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,turn.getId());
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
        String sql = "SELECT * FROM tblturno";
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
