package aplicacao.dao.util.colecoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aplicacao.dao.ConexaoDAO;
import aplicacao.dao.EnfermidadePessoalDAO;
import aplicacao.dominio.EnfermidadePessoal;
import aplicacao.dominio.Paciente;

public class DoencaDePacienteDAO {
	
	public ArrayList<EnfermidadePessoal> pesquisarTodas(Paciente paciente){
		ArrayList<EnfermidadePessoal> doencaARetornar = new ArrayList<EnfermidadePessoal>();
		
		String comandoSQL = "SELECT * FROM doencadepaciente WHERE paciente=" + paciente.getIdPaciente();
		doencaARetornar = this.pesquisarTodas(comandoSQL);
		
		return doencaARetornar;
	}
	
	public ArrayList<EnfermidadePessoal> adicionar(Paciente paciente){
		ArrayList<EnfermidadePessoal> doencaARetornar = new ArrayList<EnfermidadePessoal>();
		ArrayList<EnfermidadePessoal> doenca = (ArrayList<EnfermidadePessoal>) paciente.getDoenca();
		
		if (doenca.size() > 0){
			doencaARetornar = this.cadastrarTodas(paciente);
		}
		
		return doencaARetornar;
	}
	
	
// ---------------------------------------------------------------- Métodos privados ----------------------------------------------------------------
	
	
	private ArrayList<EnfermidadePessoal> pesquisarTodas(String comandoSQL){
		
        ArrayList<EnfermidadePessoal> enfermidadeARetornar = new ArrayList<EnfermidadePessoal>();
    	EnfermidadePessoalDAO enfermidadePessoalDAO = new EnfermidadePessoalDAO();
    	EnfermidadePessoal enfermidadePessoalVazia = new EnfermidadePessoal();
        
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        	
        	ps = conecxao.prepareStatement(comandoSQL);
        	rs = ps.executeQuery();
        	EnfermidadePessoal enfermidadePessoalBD = new EnfermidadePessoal();
        	
        	while(rs.next()) {
        		
        		enfermidadePessoalBD.setIdEnfermidadePessoal( rs.getInt("enfermidadepessoal") );
        		enfermidadePessoalBD = enfermidadePessoalDAO.pesquisarId(enfermidadePessoalBD);
        		
        		if ( !(enfermidadePessoalBD.equals(enfermidadePessoalVazia)) ){
        			enfermidadeARetornar.add(enfermidadePessoalBD);
        		}
        		else{
        			enfermidadeARetornar.clear();
        			return enfermidadeARetornar;
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
		
		return enfermidadeARetornar;
	}
	
	private ArrayList<EnfermidadePessoal> cadastrarTodas(Paciente paciente) {
		
		ArrayList<EnfermidadePessoal> doencaARetornar = new ArrayList<EnfermidadePessoal>();
		String inserirParteInicial = "INSERT INTO doencadepaciente WHERE paciente=" + paciente.getIdPaciente();
			
		for (EnfermidadePessoal i : paciente.getDoenca()){
			String inserir = inserirParteInicial + ", enfermidadepessoal=" + i.getIdEnfermidadePessoal();
			
			Connection conecxao = ConexaoDAO.getConnection();
	        PreparedStatement ps = null;
	        int resultado = 0;
	        
	        try{
	            ps = conecxao.prepareStatement(inserir);
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
			
	        if (resultado != 0){
	        	doencaARetornar.add(i);
	        }
	        else{
	        	return doencaARetornar;
	        }
		}
		
	    return doencaARetornar;
	}
}
