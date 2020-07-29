/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;
import static Modelo.Conexion.getConnection;
import Modelo.VO.VoProductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class DaoProductos {
    public boolean registrar(VoProductos pro){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblproductos(vchnomproduct,Proprecio,Procosto)VALUES(?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pro.getNompro());
            ps.setString(2,String.valueOf(pro.getPrecio()));
            ps.setString(3,String.valueOf(pro.getCosto()));
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoProductos pro){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblproductos SET vchnomproduct=?,Proprecio=?,Procosto=? WHERE idproductos=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,pro.getNompro());
            ps.setString(2,String.valueOf(pro.getPrecio()));
            ps.setString(3,String.valueOf(pro.getCosto()));
            ps.setFloat(4,pro.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoProductos pro){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblproductos WHERE idproductos=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,pro.getId());
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
        String sql = "SELECT * FROM tblproductos";
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
