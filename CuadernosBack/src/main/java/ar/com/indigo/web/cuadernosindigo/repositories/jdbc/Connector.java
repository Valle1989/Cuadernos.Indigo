
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connector {
    
    private static String driver = "com.mysql.cj.jdbc.Driver";
    
    private static String url = "jdbc:mysql://localhost:3306/Indigo?serverTimezone=UTC";
    
    private static String user = "root";
    
    private static String pass = "fede";
    
    private static Connection conn = null;
    
    private Connector(){}
    
    public static synchronized Connection getConnection(){
        if(conn == null){
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            }catch(Exception e){
                System.out.println("************************");
                System.out.println(e);
                System.out.println("************************");
            }
                    
        }
        return conn;
    }
 
}
