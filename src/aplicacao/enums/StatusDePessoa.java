package aplicacao.enums;

public enum StatusDePessoa {
	
	V("Viva"), M("Morta");
	private String status;
	
	StatusDePessoa(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
