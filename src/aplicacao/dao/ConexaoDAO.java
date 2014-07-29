package aplicacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author icaro
 * Classe responsavel por fazer a conexão do sistema com o banco de dados de url syshospital_v2
 * A classe é instanciada por todos os objetos da camada de persistencia
 */


public class ConexaoDAO {
	
    private static final String URL_MYSQL = "jdbc:mysql://localhost/syshospital_v2";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "qwe123z0";
	
    /**
     * Cria a conexão com a tabela através do JDBC
     */
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    /**
     * Encerra a conexao criada com o banco
     * @param conecxao 
     * @param ps 
     * @param rs 
     */
    
    
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
