package aplicacao.enums;

public enum StatusDeDoenca {
	
	C("Curada"), D("Doente"), E("Em tratamento");
	private String status;
	
	StatusDeDoenca(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
