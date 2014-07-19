package aplicacao.enums;

public enum StatusDeUsuario {
	
	A("Ativo"), I("Inativo"), IP("Inativo Permanente"), MP("Marcado Para Exclus�o");
	private String status;
	
	StatusDeUsuario(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
