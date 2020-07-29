/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;
import static Modelo.Conexion.getConnection;
import Modelo.VO.VoEmpleados;
import Modelo.VO.VoComboT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.JOptionPane;
/**
 *
 * @author kike6
 */
public class DaoEmpleados {
    public boolean registrar(VoEmpleados empl){
        PreparedStatement ps = null;
        Connection con = getConnection();
        String texSinEnc=empl.getContraseña(); 
        String textoEncrip=DigestUtils.sha1Hex(texSinEnc);
        System.out.println("Texto Encriptado con SHA : "+textoEncrip);
        
        String sql = "INSERT INTO tblempleados(vchusuario,vchpassword,idpuesto,idturno,idsucursal,idpersona)VALUES(?,?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,empl.getUsuario());
            ps.setString(2,textoEncrip);
            ps.setFloat(3,empl.getPuesto());
            ps.setFloat(4,empl.getTurno());
            ps.setFloat(5,empl.getSucursal());
            ps.setFloat(6,empl.getPersona());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean modificar(VoEmpleados empl){
        PreparedStatement ps = null;
        Connection con = getConnection();
        String texSinEnc=empl.getContraseña(); 
        String textoEncrip=DigestUtils.sha1Hex(texSinEnc);
        System.out.println("Texto Encriptado con SHA : "+textoEncrip);
        
        String sql = "UPDATE tblempleados SET vchusuario=?,vchpassword=?,idpuesto=?,idturno=?,idsucursal=?,idpersona=? WHERE idempleado=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,empl.getUsuario());
            ps.setString(2,textoEncrip);
            ps.setFloat(3,empl.getPuesto());
            ps.setFloat(4,empl.getTurno());
            ps.setFloat(5,empl.getSucursal());
            ps.setFloat(6,empl.getPersona());
            ps.setFloat(7,empl.getEmpleados());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminar(VoEmpleados empl){
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "DELETE FROM tblempleados WHERE idempleado=?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1,empl.getEmpleados());
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
        String sql = "SELECT tblempleados.idempleado, tblempleados.vchusuario, tblpersona.vchPernomb, tblpersona.vchapper, tblpersona.vchamper, tblpuesto.vchpuesto, tblturno.vchturno, tblsucursal.vchnomsucursal, tblsucursal.vchsucdireccion FROM tblempleados INNER JOIN tblpuesto ON tblempleados.idpuesto = tblpuesto.idpuesto INNER JOIN tblturno ON tblempleados.idturno = tblturno.idturno INNER JOIN tblsucursal ON tblempleados.idsucursal = tblsucursal.idsucursal INNER JOIN tblpersona ON tblempleados.idpersona = tblpersona.idpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    
    public ResultSet getpuesto(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idpuesto,vchpuesto FROM tblpuesto";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    public ResultSet getcorreo(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "select vchPernomb,vchapper,vchamper,vchcorreo from tblpersona";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
    }
    /*public ArrayList<VoComboT> getturno(){
        Connection con = getConnection();
        PreparedStatement pst;
        ResultSet rs;
        VoComboT VoCT;
        
        ArrayList list = new ArrayList();
        try{
            String sql = "SELECT idturno,vchturno FROM tblturno";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                VoCT = new VoComboT();
                VoCT.setIdturno(rs.getInt("idturno"));
                VoCT.setTurno(rs.getString("vchturno"));
                list.add(VoCT);
                
                
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e , " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
            
        }
        return list;
        
    }*/
    
    public ResultSet getturno(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idturno,vchturno FROM tblturno";
        try{
            Statement stm;
            stm=(Statement)con.createStatement();
            rs=stm.executeQuery(sql);
        }catch(Exception e){
            System.out.println("Error al hacer la consulta");
        }
        return rs;
        
    }
    
    public ResultSet getsucursal(){
        Connection con = getConnection();
        ResultSet rs = null;
        String sql = "SELECT idsucursal,vchnomsucursal FROM tblsucursal";
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
        String sql = "SELECT idpersona, vchPernomb, vchapper, vchamper,vchcorreo FROM tblpersona";
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
