
package Proxylo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static Modelo.Conexion.getConnection;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author kike6
 */
public class loginn implements Iproxy {
    String usuario,clave,texsinen,textoencrip;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTexsinen() {
        return texsinen;
    }

    public void setTexsinen(String texsinen) {
        this.texsinen = texsinen;
    }

    public String getTextoencrip() {
        return textoencrip;
    }

    public void setTextoencrip(String textoencrip) {
        this.textoencrip = textoencrip;
    }
    

    @Override
    public int validar_ingresar() {
        int resultado =0;
        Connection con = getConnection();
        texsinen=clave;
        textoencrip=DigestUtils.sha1Hex(texsinen);
        
        String sql="SELECT * FROM tblempleados WHERE vchusuario='"+usuario+"' AND vchpassword='"+textoencrip+"'";
//        System.out.println(sql);
        ResultSet rs = null;
        
        
        try{
            Statement stm;
            stm= con.createStatement();
            rs = stm.executeQuery(sql);
            if(rs.next()){
                resultado=1;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex,"Error de",JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
        
    }
    
}
