package aplicacao.enums;

public enum StatusDeEntrada {

	A("Atendendo"), F("Finalizado");
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
