/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Lakmina
 */
public class DB_Connection {
    
    static Connection c;
    
    private static void setConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cis_gameapp","root","");
    }
    
    public static ResultSet search(String sql) throws Exception {
        
        if(c==null){
            setConnection();
        }
        return c.createStatement().executeQuery(sql);
    }
    
    
    public static void iud(String sql)throws Exception{
        
        if(c==null){
            setConnection();
        }
        c.createStatement().execute(sql);
    }
    public static Connection getConnection(){
        try{
        if(c == null){
            setConnection();
        }    
        }catch(Exception ex){}
        
        
        return c;
    }

 

    
    
}
