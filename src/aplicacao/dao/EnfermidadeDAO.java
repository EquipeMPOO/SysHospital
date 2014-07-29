package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.Funcionario;

/**
 * @author icaro
 * Classe que trata de conexao do sistema com a tabela de enfermidades do banco de dados
 */

public class EnfermidadeDAO {
	
	private static final String SQL_PESQUISA =	"SELECT * FROM enfermidade";	
	
	/**
	 * Metodo que retorna objetos com dados de todas as enfermidadas cadastradas no banco
	 * @return List<Enfermidade> - Coleção de instancias da classe Enfermidade que possuem todos os dados da tabela de enfermidade do banco de dados
	 */
	
	public List<Enfermidade> pesquisarTodos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Enfermidade> enfermidades = new ArrayList<Enfermidade>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Enfermidade enf = new Enfermidade();
        		
        		enf.setDescricao(rs.getString("descricao"));
        		enf.setIdEnfermidade(rs.getInt("idEnfermidade"));
        		enf.setNome(rs.getString("nome"));
        		enf.setTipo(rs.getString("tipo"));
    			        		
        		enfermidades.add(enf);
        	}
        }
        catch(SQLException e){
        	try{
        		if(conecxao != null){
        			conecxao.rollback();
        		}
        	}
        	catch(SQLException e1){
        		e1.printStackTrace();
        	}
        	finally{
        		ConexaoDAO.close(conecxao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return enfermidades;
	}
	
	/**
	 * Metodo que permite ao usuario adicionar uma nova enfermidade inexistente no banco de dados
	 * @param enfermidade - Instancia da classe enfermidade que será persistida na tabela de enfermidade do banco de dados
	 * @return enfermidade - Objeto com o ID gerado pelo metodo de cadastro
	 */
	
	public Enfermidade cadastrar(Enfermidade enfermidade){
		
		Connection conexao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        	
        	String comando = "INSERT INTO enfermidade(nome, tipo,descricao) VALUES ("+
					        "'"+enfermidade.getNome()+"'"+ ","+ 
						    "'"+enfermidade.getTipo()+"'"+","+ 
						    "'"+ enfermidade.getDescricao() +"'"+ ")" ;
        	
        	ps = conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				enfermidade.setIdEnfermidade(rs.getInt(1));
			}
			
						        	
        }
        catch(SQLException e){
        	try{
        		if(conexao != null){
        			conexao.rollback();
        		}
        	}
        	catch(SQLException e1){
        		e1.printStackTrace();
        	}
        	finally{
        		ConexaoDAO.close(conexao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return enfermidade;
	}


}
