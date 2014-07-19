package aplicacao.enums;

public enum TipoSanguineo {
	
	AP("A+"), AN("A-"), ABP("AB+"), ABN("AB-"), BP("B+"), BN("B-"), OP("O+"), ON("O-");
	private String tiposanguineo;
	
	TipoSanguineo(String tiposanguineo) {
		this.setTipoSanguineo(tiposanguineo);
	}

	public String getTipoSanguineo() {
		return tiposanguineo;
	}
	public void setTipoSanguineo(String tiposanguineo) {
		this.tiposanguineo = tiposanguineo;
	}
}
