package aplicacao.enums;

public enum Sexo {
	
	M("Masculino"), F("Feminino");
	private String sexo;
	
	Sexo(String sexo) {
		this.setSexo(sexo);
	}

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
