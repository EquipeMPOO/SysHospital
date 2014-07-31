package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDePessoa;

/**
 * 
 * @author icaro
 * Classe que trata da persistencia dos dados das instancias da Classe Pessoa
 * É instanciado pelas classes PacienteDAO e classes que tratam da persistencia Funcionarios
 */

public class PessoaDAO{

	private static final String SQL_PESQUISA = "SELECT * FROM pessoa";
	
	/**
	 * Metodo que trata da inserção dos dados de um objeto da classe Pessoa na tabela pessoa
	 * @param novaPessoa - Parametro que conterá os dados a serem persistidos na tabela de Pessoa
	 * @return novaPessoa - Parametro instanciado que conterá agora a informação do ID da linha criada na tabela do banco de dados
	 */

	public Pessoa cadastrar(Pessoa novaPessoa) {

		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

        String comando = "INSERT INTO pessoa(nome, cpf, idade, tiposanguineo, sexo, statusdepessoa) VALUES ("+
        				"'"+novaPessoa.getNome()+"'"+ ","+ 
        				"'"+novaPessoa.getCpf()+"'"+","+ 
        				"'"+novaPessoa.getIdade()+"'"+","+ 
        				"'"+novaPessoa.getTipoSanguineo()+"'"+","+ 
        				"'"+novaPessoa.getSexo() +"'"+","+ 
        				"'"+novaPessoa.getStatusDePessoa()+"'" + ")";
                
        try {
			ps = conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next()){
				
				novaPessoa.setIdPessoa(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

			ConexaoDAO.close(conexao, ps, null);
		}
        
        return novaPessoa;
        
	}
	
	/**
	 * Metodo que altera os dados de uma linha da tabela pessoa cujo id seja o mesmo do objeto parametrizado
	 * @param pessoa - Instancia da classe pessoa que possuirá os dados que serão alterados na tabela pessoa
	 * @return 
	 */


	public Pessoa alterar(Pessoa pessoa) {		

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET nome = "+ "'" + pessoa.getNome() + "'" + 
        		          ", cpf = " + "'"+ pessoa.getCpf() + "'" +
        		          ", idade = " + "'"+ pessoa.getIdade() + "'" +
        				  ", tiposanguineo = "+ "'"+ pessoa.getTipoSanguineo() + "'"+ 
        		          ", sexo = " + "'"+ pessoa.getSexo() + "'"+
        				  ", statusdepessoa = " + "'"+ pessoa.getStatusDePessoa() + "'"+ 
        		          "WHERE idpessoa = " + "'" + pessoa.getIdPessoa() + "'" ;
                
        try {
			ps = conecxao.prepareStatement(comando);
			ps.executeUpdate();
        			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conecxao, ps, rs);
		}
        
        return pessoa;

 	}

	/**
	 * 
	 * Metodo que altera o dado da coluna statusdepessoa da tabela pessoa cujo ID é o mesmo do objeto parametrizado
	 * @param pessoa - Instancia da classe pessoa que possui o ID da linha a ser alterada na tabela pessoa
	 */
	public void excluir(Pessoa pessoa){

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET statusdepessoa = " + "'"+ StatusDePessoa.M.getStatus() + "'"+ 
		          "WHERE idpessoa = " + "'" + pessoa.getIdPessoa() + "'" ;
      
        try {
 			ps = conecxao.prepareStatement(comando);
 			ps.executeUpdate();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			ConexaoDAO.close(conecxao, ps, rs);
 		}
	}
	
	/**
	 * Metodo que retorna uma coleção de instancias da classe Pessoa com todos os dados persistidos na tabela pessoa
	 * @return ArrayList<Pessoa> - coleção com objetos que contém os dados persistidos no banco de dados
	 */

	public ArrayList<Pessoa> pesquisarTodos() {

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Pessoa pessoa = new Pessoa();
        		
        		pessoa.setIdPessoa(rs.getInt("idpessoa"));
        		pessoa.setNome(rs.getString("nome"));
        		pessoa.setCpf(rs.getString("cpf"));
        		pessoa.setIdade(rs.getInt("idade"));
        		pessoa.setTipoSanguineo(rs.getString("tiposanguineo"));
        		pessoa.setSexo(rs.getString("sexo"));
        		pessoa.setStatusDePessoa(rs.getString("statusdepessoa"));
        		
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

		return pessoas;

	}
	
	/**
	 * Metodo que realiza a busca de uma linha específica da tabela pessoa onde o ID for o mesmo do parametro passado
	 * @param idPessoa - inteiro que possui o mesmo ID de alguma linha persistida no banco de dados
	 * @return
	 */

	public Pessoa pesquisarporID(int idPessoa) {

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pessoa pessoa = new Pessoa();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		pessoa.setIdPessoa(rs.getInt("idpessoa"));
        		
        		if (pessoa.getIdPessoa() == idPessoa ){
            		pessoa.setNome(rs.getString("nome"));
            		pessoa.setCpf(rs.getString("cpf"));
            		pessoa.setIdade(rs.getInt("idade"));
            		pessoa.setTipoSanguineo(rs.getString("tiposanguineo"));
            		pessoa.setSexo(rs.getString("sexo"));
            		pessoa.setStatusDePessoa(rs.getString("statusdepessoa"));
        			break; 
        		} 		
        		
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

		return pessoa;
	}

}