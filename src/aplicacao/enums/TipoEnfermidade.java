package aplicacao.enums;

public enum TipoEnfermidade {
	A("Anomalia"), D("Deficiencia"), AL("Alergia"), C("Cronica"), N("Comum");
	private String enf;
	
	TipoEnfermidade(String enf) {
		this.setTipoEnfermidade(enf);
	}

	public String getTipoEnfermidade() {
		return enf;
	}
	public void setTipoEnfermidade(String status) {
		this.enf = status;
	}

}
