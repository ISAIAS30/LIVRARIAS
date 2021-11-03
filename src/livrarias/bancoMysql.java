/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author sandr
 */
public class bancoMysql {
    
    public static Connection conectaBanco () {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/db_livraria?" +
                                   "user=root&password=root");
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao conectar no Banco de dados: ", ex);
        }
        
    }
    
}
