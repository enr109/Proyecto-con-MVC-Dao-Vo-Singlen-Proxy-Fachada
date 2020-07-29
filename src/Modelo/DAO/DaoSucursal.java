/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import static Modelo.Conexion.getConnection;
import Modelo.VO.VoSucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class DaoSucursal extends Conexion{
    public boolean registrar(VoSucursal sucu){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tblsucursal(vchnomsucursal,vchsucdireccion)VALUES(?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,sucu.getNombsu());
            ps.setString(2,sucu.getDireccion());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoSucursal sucu){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tblsucursal SET vchnomsucursal=?,vchsucdireccion=? WHERE idsucursal=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,sucu.getNombsu());
            ps.setString(2,sucu.getDireccion());
            ps.setFloat(3,sucu.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoSucursal sucu){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblsucursal WHERE idsucursal=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,sucu.getId());
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
        String sql = "SELECT * FROM tblsucursal";
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
