package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author kike6
 */
public class Conexion {
    public static Connection connection;
    public static Connection getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection("jdbc:sqlserver://KIKE6PC\\KIKE:1433;database=bdProyectoPro;integratedSecurity=true;");
            //connection=DriverManager.getConnection("jdbc:sqlserver://KIKE\\KIKE:1433;database=bdProyectoPro;integratedSecurity=true;");
            
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException("Conexion fallida",e);
        }
    }
    
    static class getClose extends Thread{
        public void run(){
            try{
                Connection conn = Conexion.getConnection();
                conn.close();
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
