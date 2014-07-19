package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.ConecxaoBD;
import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.enums.StatusDeUsuario;

public class MedicoDAO implements IFuncionarioDAO {
	
	private static final String SQL_PESQUISA =	"SELECT * FROM medico";
	
	// ----x> temporário:
	int a = 1;
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){ //Os métodos de pesquisa tem que incluir uma cláusula pois nem sempre se quererá pesquisar uma pessoa...
		
		Connection conecxao = ConecxaoBD.getConnection();
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
        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		//m.setPessoa(pessoa);
        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		medico.setEspecialidade(rs.getString("especialidade"));
        		
        		funcionarios.add(medico);
        		
        		// ----x>  Apenas de teste;
        		//System.out.println(medico.getNumeroDeRegistro());
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
        		ConecxaoBD.close(conecxao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return funcionarios;
	}
	
	public List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa){
		
		Medico medicoDeParametro = (Medico) f;
		Connection conecxao = ConecxaoBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
    			Medico medicoBD = new Medico();
        		
        		medicoBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		medicoBD.setLogin(rs.getString("login"));
        		medicoBD.setSenha(rs.getString("senha"));
        		medicoBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		medicoBD.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		// ----0> m.setPessoa(pessoa);
        		medicoBD.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		medicoBD.setEspecialidade(rs.getString("especialidade"));
        		
        		Comparacao comparacao = new Comparacao();
        		if ( comparacao.eFiltro(medicoDeParametro, medicoBD) ){
	        		funcionarios.add(medicoBD);
        		}
        		else{
        			medicoBD = null;
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
        		ConecxaoBD.close(conecxao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return funcionarios;
	}
	
	public List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa){
		
		Connection conecxao = ConecxaoBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ComandoSQL codigo = new ComandoSQL();
        	String comandoSQL = codigo.gerarPesquisa(f, 1);
        	
    		// ----x>  Apenas de teste;
    		//System.out.println(comandoSQL);
        	
        	ps = conecxao.prepareStatement(comandoSQL);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Medico medico = new Medico();
        		
        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		//medico.setPessoa(pessoa);
        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		medico.setEspecialidade(rs.getString("especialidade"));
        		
        		funcionarios.add(medico);
        		
        		// ----x>  Apenas de teste;
        		//System.out.println(medico.getNumeroDeRegistro());
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
        		ConecxaoBD.close(conecxao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return funcionarios;
	}
	
	public int cadastrar(Funcionario funcionario) {
		return a;
	}
	
	
	public Funcionario alterar(Funcionario f, int iap){
		Connection conecxao = ConecxaoBD.getConnection();
        PreparedStatement ps = null;
        Medico medico = (Medico) f;
        
    	ComandoSQL codigo = new ComandoSQL();
    	String comandoSQL = codigo.gerarAtualizacao(medico, iap, 1);
        
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
            		ConecxaoBD.close(conecxao, ps, null);
            	}
            	e.printStackTrace();
            }
		
		medico.setIdFuncionario(resultado);
		medico.setLogin("");
		medico.setSenha("");
		medico.setIdentificadorInterno("");
		medico.setStatusDeUsuario("");
		medico.setPessoa(null);
		medico.setNumeroDeRegistro(-1);
		medico.setEspecialidade("");
        
		ArrayList<Funcionario> f1 = (ArrayList<Funcionario>) this.pesquisarAlgum(medico, false);
		ArrayList<Funcionario> vazio = new ArrayList<>();
		if (f1 == vazio){
			return null;
		} 
		else{
			return (Funcionario) f1.get(0);
		}
	}
	
	public Funcionario excluir(Funcionario f){
		Medico f1 = (Medico) f;
		f1.setStatusDeUsuario(StatusDeUsuario.MP.getStatus());
		Medico f2 = (Medico) this.alterar(f1, 1);
		return f2;
	}
	
	public Funcionario logar(Funcionario f){
		Medico f1 = (Medico) f;
		f1.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		Medico f2 = (Medico) this.alterar(f1, 1);
		return f2;
	}
	
	public int deslogar(Funcionario funcionario){
		return a;
	}
}
