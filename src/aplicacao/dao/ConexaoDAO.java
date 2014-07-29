package aplicacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoDAO {
	
    private static final String URL_MYSQL = "jdbc:mysql://localhost/syshospital_v2";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "qwe123z0";
	
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Conectando ao Banco de Dados");
        return null;
    }
    
    public static void close(Connection conecxao, PreparedStatement ps, ResultSet rs) {
        try {
            if (conecxao != null) {
            	conecxao.close();
            }
 
            if (ps != null) {
                ps.close();
            }
 
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
