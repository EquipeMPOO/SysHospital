package aplicacao.enums;

public enum StatusDeEntrada {
	
	F("Finalizado"), N("Não finalizado");
	private String status;
	
	StatusDeEntrada(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
