/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import static Modelo.Conexion.getConnection;
import Modelo.VO.VoTipoentrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class DaoTipoentrega {
    public boolean registrar(VoTipoentrega tipo){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO tbltipoentrega(vchentrega)VALUES(?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,tipo.getTipoen());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoTipoentrega tipo){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE tbltipoentrega SET vchentrega=? WHERE idtientrega=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,tipo.getTipoen());
            ps.setFloat(2,tipo.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoTipoentrega tipo){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tbltipoentrega WHERE idtientrega=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,tipo.getId());
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
        String sql = "SELECT * FROM tbltipoentrega";
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
