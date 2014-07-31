package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Atendente;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.StatusDeUsuario;

/**
 * 
 * @author icaro
 * Classe que trata de conexao do sistema com a tabela atendente do banco de dado
 * � instanciado nas classes de PesquisaControle e GerenciamentoControle
 */
public class AtendenteDAO{
	
	private static final String SQL_PESQUISA =	"SELECT * FROM atendente";
	
	/**
	 * Retorna uma cole��o de objetos que possuem dados de todas as linhas da tabela atendente
	 * @param pesquisarPessoa - Boolena que trata a condi��o de pesquisar os dados pessoais relacionados ao atendente pesquisado
	 * @return List<Funcionario> - Cole��o de instancias da classe Funcionario que possuem os dados das linhas da tabela Atendente
	 */
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Atendente atendenteBD = new Atendente();
        		
        		atendenteBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		atendenteBD.setLogin(rs.getString("login"));
        		atendenteBD.setSenha(rs.getString("senha"));
        		atendenteBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				atendenteBD.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
    			}
    			
        		atendenteBD.setCargo(rs.getString("cargo"));
        		
        		funcionarios.add(atendenteBD);
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
		
		return funcionarios;
	}
	
	/**
	 * Procura dentro da tabela de atendente uma linha que possua o mesmo dado nas colunas de login e senha que o do parametro informado
	 * @param parametro - Instancia da classe Funcionario responsavel por servir como parametro para compara��o de Login e Senha
	 * @return Funcionario - Instancia da classe Funcionario que possui o mesmo Login e Senha do Funcionario parametrizado
	 */
	
	public Funcionario pesquisarLogin(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Atendente atendente = new Atendente();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		atendente.setLogin(rs.getString("login"));
        		atendente.setSenha(rs.getString("senha"));
        		
        		if (atendente.getSenha().equals(parametro.getSenha()) & atendente.getLogin().equals(parametro.getLogin())){
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		atendente.setCargo(rs.getString("cargo"));
	        		return atendente;
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
		
		return null;
	}
	
	/**
	 * 
	 * Procura dentro da tabela de atendente uma linha que possua o mesmo dado na coluna de cpf que o do parametro informado
	 * @param parametro - Instancia da classe funcionario que servir� como parametro para compara��o de Cpf com o objeto formado a partir dos dados presentes numa linha da tabela
	 * @return Funcionario - Instancia da classe funcionario que possui o mesmo cpf informado pelo funcionario parametrizado
	 */
		
	public Funcionario pesquisarCpf(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Atendente atendente = new Atendente();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO dbPessoa = new PessoaDAO();
        		atendente.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (atendente.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			atendente.setLogin(rs.getString("login"));
        			atendente.setSenha(rs.getString("senha"));
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setCargo(rs.getString("cargo"));
	        		return atendente;
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
		
		return null;
	}
	
	/**
	 * Retorna uma cole��o de funcionarios que n�o est�o logados dentro da tabela atendente
	 * @return List<Funcionario> - Cole��o de funcionarios inativos (que n�o est�o logados) no sistema
	 */
	
	public List<Funcionario> pesquisarInativos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Atendente atendente = new Atendente();
        		atendente.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (atendente.getStatusDeUsuario().equals("Inativo")){
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setLogin(rs.getString("login"));
	        		atendente.setSenha(rs.getString("senha"));
	        		atendente.setCargo(rs.getString("cargo"));        	

	        		
	    			PessoaDAO a = new PessoaDAO();
	    			atendente.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(atendente);
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
		
		return funcionarios;
		
	}
	
	/**
	 * Recupera os dados do objeto parametrizado e os insere numa nova linha tabela atendente
	 * @param atendente -Instancia da classe Funcionario que possui os dados que ser�o persistidos na tabela
	 */
	
	public Funcionario cadastrar(Funcionario atendente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		Pessoa pessoaAtendente = db.cadastrar(atendente.getPessoa());
		
		String comando = "INSERT INTO atendente(login, senha, statusdeusuario, pessoa, cargo) VALUES ("+
						     "'"+atendente.getLogin()+"'"+ ","+ 
							"'"+atendente.getSenha()+"'"+","+ 
							"'"+ "Inativo" +"'"+","+ 
							"'"+ pessoaAtendente.getIdPessoa() + "'" +","+ 
							"'"+ "atendente" +"'" + ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}
        
        return atendente;


	}
	
	/**
	 * Altera os dados da coluna de um item da tabela de atendente
	 * @param atendente - Instancia da classe Funcionario que cont�m os dados que ser�o atualizados na tabela de Id correspondente
	 */
	
	public Funcionario alterar(Funcionario atendente){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
		PessoaDAO db = new PessoaDAO();
		Pessoa pessoaAtendente = db.cadastrar(atendente.getPessoa());
		
        String comando = "UPDATE atendente SET login = " +"'"+atendente.getLogin()+"'"+ 
		        		", senha = " +"'"+atendente.getSenha()+"'" +
		        		", statusdeusuario = " +"'"+atendente.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+ pessoaAtendente.getIdPessoa() +"'"+ 
		        		"WHERE idfuncionario = " + "'" + atendente.getIdFuncionario() + "'";
		                
        try {
			ps = conecxao.prepareStatement(comando);
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conecxao, ps, rs);
		}
        
        return atendente;
	}
	
	/**
	 * Altera o status de usuario de um funcionario da tabela para Inativo Permanente (IP)
	 * @param atendente - Instancia que servir� como parametro alterar a linha de ID correspondente
	 */
	
	public void inativar(Funcionario atendente){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE atendente SET statusdeusuario = " + "'"+ "IP" + "'"+ 
        				 "WHERE idfuncionario = " + "'" + atendente.getIdFuncionario() + "'";
      
        try {
 			ps = conecxao.prepareStatement(comando);
 			ps.executeUpdate();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			ConexaoDAO.close(conecxao, ps, rs);
 		}
		
	}
}
