package aplicacao.dominio;

import java.util.List;


public class Paciente {
		
		private int idPaciente;
		private List<EnfermidadePessoal> enfermidadePessoal;
		private List<Entrada> historico;
		private Pessoa pessoa;
		
		public int getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}
		
		public List<EnfermidadePessoal> getDoenca() {
			return enfermidadePessoal;
		}
		public void setDoenca(List<EnfermidadePessoal> enfermidadePessoal) {
			this.enfermidadePessoal = enfermidadePessoal;
		}
		
		public List<Entrada> getHistorico() {
			return historico;
		}
		public void setHistorico(List<Entrada> historico) {
			this.historico = historico;
		}
		
		public Pessoa getPessoa() {
			return pessoa;
		}
		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}
}
 
