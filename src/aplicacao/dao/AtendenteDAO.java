package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDeUsuario;

public class AtendenteDAO implements IFuncionarioDAO {
	
	private static final String SQL_PESQUISA =	"SELECT * FROM atendente";
	
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
        		atendenteBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		atendenteBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				atendenteBD.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
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
	
	public List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa){
		
		Atendente atendenteDeParametro = (Atendente) f;
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
        		atendenteBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		atendenteBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				atendenteBD.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			
        		atendenteBD.setCargo(rs.getString("cargo"));
        		
        		Comparacao comparacao = new Comparacao();
        		if ( comparacao.eFiltro(atendenteDeParametro, atendenteBD) ){
	        		funcionarios.add(atendenteBD);
        		}
        		else{
        			atendenteBD = null;
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
	
	public List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa){
		
		return null;
	}
	
	public void cadastrar(Funcionario atendente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		String comando = "INSERT INTO atendente(login, senha, identificadorinterno, statusdeusuario, pessoa) VALUES ("+
						     "'"+atendente.getLogin()+"'"+ ","+ 
							"'"+atendente.getSenha()+"'"+","+ 
							"'"+atendente.getIdentificadorInterno()+"'"+","+ 
							"'"+atendente.getStatusDeUsuario()+"'"+","+ 
							"'"+atendente.getPessoa().getIdPessoa()+"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
	public void alterar(Funcionario atendente){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET login = " +"'"+atendente.getLogin()+"'"+ ","+ 
		        		", senha = " +"'"+atendente.getSenha()+"'"+","+ 
		        		", identificadorinterno = " +"'"+atendente.getIdentificadorInterno()+"'"+","+ 
		        		", statusdeusuario = " +"'"+atendente.getStatusDeUsuario()+"'"+","+ 
		        		", pessoa = " +"'"+atendente.getPessoa().getIdPessoa()+"'"+ ")" ;
		                
        try {
			ps = conecxao.prepareStatement(comando);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conecxao, ps, rs);
		}
	}
	
	public Funcionario excluir(Funcionario f){
		Atendente f1 = (Atendente) f;
		f1.setStatusDeUsuario(StatusDeUsuario.MP.getStatus());
		Atendente f2 = (Atendente) this.alterar(f1, 1);
		return f2;
	}
}
