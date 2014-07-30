package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.StatusDeUsuario;

public class EnfermeiroDAO{
	
	private static final String SQL_PESQUISA = "SELECT * FROM enfermeiro";
	
	/**
	 * Retorna uma coleção de objetos que possuem dados de todas as linhas da tabela enfermeiro
	 * @param pesquisarPessoa - Boolena que trata a condição de pesquisar os dados pessoais relacionados ao atendente pesquisado
	 * @return List<Funcionario> - Coleção de instancias da classe Funcionario que possuem os dados das linhas da tabela enfermeiro
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
        		Enfermeiro enfermeiro = new Enfermeiro();
        		
        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				enfermeiro.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
    			}
    			        		
        		funcionarios.add(enfermeiro);
        		
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
	 * Procura dentro da tabela de enfermeiro uma linha que possua o mesmo dado nas colunas de login e senha que o do parametro informado
	 * @param parametro - Instancia da classe Funcionario responsavel por servir como parametro para comparação de Login e Senha
	 * @return Funcionario - Instancia da classe Funcionario que possui o mesmo Login e Senha do Funcionario parametrizado
	 */	
	
	public Funcionario pesquisarLogin(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Enfermeiro enfermeiro = new Enfermeiro();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
        		
        		if (enfermeiro.getSenha().equals(parametro.getSenha()) & enfermeiro.getLogin().equals(parametro.getLogin())){
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		System.out.println("aqui");
	        		return enfermeiro;
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
	
	public Enfermeiro pesquisarPorID(int ID){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Enfermeiro enfermeiro = new Enfermeiro();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * from enfermeiro WHERE idfuncionario =" + ID);
        	rs = ps.executeQuery();
        	
        	while(rs.next()){
        		PessoaDAO dbPessoa = new PessoaDAO();
        		enfermeiro.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));   
        			
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
	        	enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        	enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
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
		
		return enfermeiro;
		
	}
	
	
	/**
	 * 
	 * Procura dentro da tabela de enfermeiro uma linha que possua o mesmo dado na coluna de cpf que o do parametro informado
	 * @param parametro - Instancia da classe funcionario que servirá como parametro para comparação de Cpf com o objeto formado a partir dos dados presentes numa linha da tabela
	 * @return Funcionario - Instancia da classe funcionario que possui o mesmo cpf informado pelo funcionario parametrizado
	 */
	
	public Funcionario pesquisarCpf(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Enfermeiro enfermeiro = new Enfermeiro();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO dbPessoa = new PessoaDAO();
        		enfermeiro.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (enfermeiro.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			enfermeiro.setLogin(rs.getString("login"));
        			enfermeiro.setSenha(rs.getString("senha"));
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return enfermeiro;
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
	 * Retorna uma coleção de funcionarios que não estão logados dentro da tabela enfermeiro
	 * @return List<Funcionario> - Coleção de funcionarios inativos (que não estão logados) no sistema
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
        		Enfermeiro enfermeiro = new Enfermeiro();
        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (enfermeiro.getStatusDeUsuario().equals("Inativo")){
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setLogin(rs.getString("login"));
	        		enfermeiro.setSenha(rs.getString("senha"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		
	    			PessoaDAO a = new PessoaDAO();
	    			enfermeiro.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(enfermeiro);
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
	 * Recupera os dados do objeto parametrizado e os insere numa nova linha tabela enfermeiro
	 * @param atendente -Instancia da classe Funcionario que possui os dados que serão persistidos na tabela
	 */
	
	public void cadastrar(Funcionario enfermeiro) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(enfermeiro.getPessoa());
		
		String comando = "INSERT INTO enfermeiro(login, senha, statusdeusuario, pessoa, numeroderegistro) VALUES ("+
						     "'"+enfermeiro.getLogin()+"'"+ ","+ 
							"'"+enfermeiro.getSenha()+"'"+","+ 
							"'"+ "Inativo"+"'"+","+ 
							"'"+db.procurarId(enfermeiro.getPessoa())+"'"+ ","+
							"'"+((Enfermeiro) enfermeiro).getNumeroDeRegistro() +"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
	/**
	 * Altera os dados da coluna de um item da tabela de enfermeiro
	 * @param atendente - Instancia da classe Funcionario que contém os dados que serão atualizados na tabela de Id correspondente
	 */
	
	public void alterar(Funcionario atendente){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        PessoaDAO db = new PessoaDAO();
		db.alterar(atendente.getPessoa());
		
        String comando = "UPDATE enfermeiro SET login = " +"'"+atendente.getLogin()+"'"+ 
		        		", senha = " +"'"+atendente.getSenha()+"'"+
		        		", statusdeusuario = " +"'"+atendente.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+db.procurarId(atendente.getPessoa())+"'"+ 
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
	
	/**
	 * Altera o status de usuario de um funcionario da tabela para Inativo Permanente (IP)
	 * @param atendente - Instancia que servirá como parametro alterar a linha de ID correspondente
	 */
	
	public void inativar(Funcionario atendente){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE enfermeiro SET statusdeusuario = " + "'"+ "IP" + "'"+ 
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
