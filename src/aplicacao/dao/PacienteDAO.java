package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Paciente;

public class PacienteDAO {

	private static final String SQL_PESQUISA = "SELECT * FROM paciente";
	
	public List<Paciente> pesquisarTodos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<Paciente>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Paciente paciente = new Paciente();
        		
        		paciente.setIdPaciente(rs.getInt("idpaciente"));
        		paciente.setHistorico(this.procurarEntradas(paciente));        		
        		PessoaDAO db = new PessoaDAO();
    			paciente.setPessoa(db.pesquisarporID(rs.getInt("pessoa")));    			
    			
    			pacientes.add(paciente);
    			
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
		
		return pacientes;
	}
	
	public void cadastrar(Paciente paciente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(paciente.getPessoa());
		
		String comando = "INSERT INTO paciente(pessoa) VALUES ('" +  db.procurarId(paciente.getPessoa())+"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
	public ArrayList<Entrada> procurarEntradas(Paciente paciente){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		ArrayList<Entrada> entradasPesquisadas = new ArrayList<Entrada>();
		
		//antes de mais nada ele irá criar o comando para pegar dentro da tabela historico, todas as linhas onde a coluna "paciente" for igual ao id informado em paciente.getIdPaciete()
		String comando = "SELECT * FROM historico WHERE paciente = " + paciente.getIdPaciente();	
		
        try {
			ps = conexao.prepareStatement(comando);
			ResultSet rs = ps.executeQuery();
			
			EntradaDAO databaseEntrada = new EntradaDAO();
			
			//enquanto houverem querys com a coluna paciente igual ao id do objeto passado ele fará uma busca 
			while(rs.next()){
				
				//em seguida ele irá consultar a tabela de ENTRADAS e irá fazer uma pesquisa pelo ID que consta na tabela de historico
				Entrada ent = databaseEntrada.pesquisarPorId(rs.getInt("entrada"));				
				entradasPesquisadas.add(ent); //Por fim ele irá adicionar a entrada selecionada na lista de Entradas
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}
        
        return entradasPesquisadas;
		
	}
	
	public void internar(Paciente paciente){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		EntradaDAO databaseEntrada = new EntradaDAO();
		int tamanho = paciente.getHistorico().size(); //recupera a quantidade de elementos da Array
		Entrada entrada = paciente.getHistorico().get(tamanho-1); //recupera o ultimo objeto entrada da lista
				
		String comando = "INSERT INTO historico (paciente, entrada) VALUES ('" + paciente.getIdPaciente() + "'" + "," + "'" + databaseEntrada.inserir(entrada) + "')";	
		
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}
	}
	
	public void liberar(Paciente paciente){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		EntradaDAO databaseEntrada = new EntradaDAO();
		int tamanho = paciente.getHistorico().size(); //recupera a quantidade de elementos da Array
		Entrada entrada = paciente.getHistorico().get(tamanho-1); //recupera o ultimo objeto entrada da lista
		
		databaseEntrada.remover(entrada);
		
	}
	
	
}
