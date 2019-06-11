/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FRANCISCO
 */
public class BaseDatosPG {

    Connection con = null;
    Statement st = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String sql;

    public BaseDatosPG() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/pedidos?useUnicode"
                            + "=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=fals"
                            + "e&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "admin");
        } catch (ClassNotFoundException ex) {
            System.out.println("No encontro el Driver" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("mal SQL" + ex.getMessage());
        }

    }
    
    public Connection getConnection(){
    return this.con;
    }

    public void desconectarBD() {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("cerro mal"+ex.getMessage());
            }
        }

    }

}
