package aplicacao.dominio;

import java.util.ArrayList;


public class Paciente {
		
		private int idPaciente;
		private ArrayList<EnfermidadePessoal> enfermidadePessoal;
		private ArrayList<Entrada> historico;
		private Pessoa pessoa;
		
		public int getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}
		
		public ArrayList<EnfermidadePessoal> getDoenca() {
			return enfermidadePessoal;
		}
		public void setDoenca(ArrayList<EnfermidadePessoal> enfermidadePessoal) {
			this.enfermidadePessoal = enfermidadePessoal;
		}
		
		public ArrayList<Entrada> getHistorico() {
			return historico;
		}
		public void setHistorico(ArrayList<Entrada> historico) {
			this.historico = historico;
		}
		
		public Pessoa getPessoa() {
			return pessoa;
		}
		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}
}
 
