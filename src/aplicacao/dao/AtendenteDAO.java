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
	
	private static final String SQL_PESQUISA =
		"SELECT * FROM atendente";
	
	// ----x> temporário:
	int a = 1;
	
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
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ComandoSQL codigo = new ComandoSQL();
        	String comandoSQL = codigo.gerarPesquisa(f, 2);
        	
        	ps = conecxao.prepareStatement(comandoSQL);
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
	
	public int cadastrar(Funcionario funcionario) {
		return a;
	}
	
	public Funcionario alterar(Funcionario f, int iap){
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        Atendente atendente = (Atendente) f;
        
    	ComandoSQL codigo = new ComandoSQL();
    	String comandoSQL = codigo.gerarAtualizacao(atendente, iap, 2);
        
        int resultado = 0;
        try{
            ps = conecxao.prepareStatement(comandoSQL);
            resultado = ps.executeUpdate();
            
            }catch(SQLException e){
            	try{
            		if(conecxao!=null){
            			conecxao.rollback();
            		}
            	}catch(SQLException e1){
            		e1.printStackTrace();
            	}finally{
            		ConexaoDAO.close(conecxao, ps, null);
            	}
            	e.printStackTrace();
            }
        
		atendente.setIdFuncionario(resultado);
		atendente.setLogin("");
		atendente.setSenha("");
		atendente.setIdentificadorInterno("");
		atendente.setStatusDeUsuario("");
		atendente.setPessoa(null);
		atendente.setCargo("");
		
		ArrayList<Funcionario> f1 = (ArrayList<Funcionario>) this.pesquisarAlgum(atendente, false);
		ArrayList<Funcionario> vazio = new ArrayList<>();
		if (f1 == vazio){
			return null;
		} 
		else{
			return (Funcionario) f1.get(0);
		}
	}
	
	public Funcionario excluir(Funcionario f){
		Atendente f1 = (Atendente) f;
		f1.setStatusDeUsuario(StatusDeUsuario.MP.getStatus());
		Atendente f2 = (Atendente) this.alterar(f1, 1);
		return f2;
	}
	
	public Funcionario logar(Funcionario f){
		Atendente f1 = (Atendente) f;
		f1.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		Atendente f2 = (Atendente) this.alterar(f1, 1);
		return f2;
	}
	
	public int deslogar(Funcionario funcionario){
		return a;
	}
}
