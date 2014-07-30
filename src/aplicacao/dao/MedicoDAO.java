package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aplicacao.dao.ConexaoDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDeUsuario;

public class MedicoDAO{
	
	private static final String SQL_PESQUISA =	"SELECT * FROM medico";
	
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
        		Medico medico = new Medico();
        		
        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				medico.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			        		
        		funcionarios.add(medico);
        		
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
	
	
	public Medico pesquisarPorID(int ID){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Medico medico = new Medico();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * from enfermeiro WHERE idfuncionario =" + ID);
        	rs = ps.executeQuery();
        	
        	while(rs.next()){
        		PessoaDAO dbPessoa = new PessoaDAO();
        		medico.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));     		
        			
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
	        	medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        	medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
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
		
		return medico;
		
	}
	
	
	/**
	 * Procura dentro da tabela de medico uma linha que possua o mesmo dado nas colunas de login e senha que o do parametro informado
	 * @param parametro - Instancia da classe Funcionario responsavel por servir como parametro para comparação de Login e Senha
	 * @return Funcionario - Instancia da classe Funcionario que possui o mesmo Login e Senha do Funcionario parametrizado
	 */
	
	public Funcionario pesquisarLogin(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Medico medico = new Medico();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
        		
        		if (medico.getSenha().equals(parametro.getSenha()) & medico.getLogin().equals(parametro.getLogin())){
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return medico;
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
	 * Procura dentro da tabela de medico uma linha que possua o mesmo dado na coluna de cpf que o do parametro informado
	 * @param parametro - Instancia da classe funcionario que servirá como parametro para comparação de Cpf com o objeto formado a partir dos dados presentes numa linha da tabela
	 * @return Funcionario - Instancia da classe funcionario que possui o mesmo cpf informado pelo funcionario parametrizado
	 */
	
	public Funcionario pesquisarCpf(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Medico medico = new Medico();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO dbPessoa = new PessoaDAO();
        		medico.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (medico.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			medico.setLogin(rs.getString("login"));
        			medico.setSenha(rs.getString("senha"));
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return medico;
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
	 * Retorna uma coleção de funcionarios que não estão logados dentro da tabela medico
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
        		Medico medico = new Medico();
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (medico.getStatusDeUsuario().equals("Inativo")){
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setLogin(rs.getString("login"));
	        		medico.setSenha(rs.getString("senha"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		
	    			PessoaDAO a = new PessoaDAO();
	    			medico.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(medico);
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
	 * Recupera os dados do objeto parametrizado e os insere numa nova linha tabela medico
	 * @param atendente -Instancia da classe Funcionario que possui os dados que serão persistidos na tabela
	 */
	
	public void cadastrar(Funcionario medico) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(medico.getPessoa());
		
		String comando = "INSERT INTO medico(login, senha, statusdeusuario, pessoa, numeroderegistro, especialidade) VALUES ("+
						     "'"+medico.getLogin()+"'"+ ","+ 
							"'"+medico.getSenha()+"'"+","+ 
							"'"+ "Inativo" +"'"+","+ 
							"'"+ db.procurarId(medico.getPessoa()) + "'"+ ","+ 
							"'"+((Medico) medico).getNumeroDeRegistro() +"'"+","+
							"'"+((Medico) medico).getEspecialidade()+"'" +")" ;
        
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
	 * Altera os dados da coluna de um item da tabela de medico
	 * @param atendente - Instancia da classe Funcionario que contém os dados que serão atualizados na tabela de Id correspondente
	 */
	
	public void alterar(Funcionario medico){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
		PessoaDAO db = new PessoaDAO();
		db.alterar(medico.getPessoa());
        
        String comando = "UPDATE medico SET login = " +"'"+medico.getLogin()+"'"+
		        		", senha = " +"'"+medico.getSenha()+"'"+
		        		", statusdeusuario = " +"'"+medico.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+ db.procurarId(medico.getPessoa()) +"'"+ 
		        		"WHERE idfuncionario = " + "'" + medico.getIdFuncionario() + "'";
		                
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
        
        String comando = "UPDATE medico SET statusdeusuario = " + "'"+ "IP" + "'"+ 
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
