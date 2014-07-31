package aplicacao.dao.util.geral;

import java.util.ArrayList;


class AtributoUsavel {
	
	private ArrayList<String> tipoInteiro = new ArrayList<String>();
	private ArrayList<String> tipoString = new ArrayList<String>();
	private ArrayList<String> tipoObjeto = new ArrayList<String>();
	private ArrayList<String> todos = new ArrayList<String>();
	private ArrayList<String> todosEmOrdemDeAdd = new ArrayList<String>();
	
	public ArrayList<String> getTipoInteiro() {
		return tipoInteiro;
	}
	public void setTipoInteiro(ArrayList<String> tipoInteiro) {
		this.tipoInteiro = (ArrayList<String>) tipoInteiro.clone();
		this.todosEmOrdemDeAdd.clear();
	}
	
	public ArrayList<String> getTipoString() {
		return tipoString;
	}
	public void setTipoString(ArrayList<String> tipoString) {
		this.tipoString = (ArrayList<String>) tipoString.clone();
		this.todosEmOrdemDeAdd.clear();
	}
	
	public ArrayList<String> getTipoObjeto() {
		return tipoObjeto;
	}
	public void setTipoObjeto(ArrayList<String> tipoObjeto) {
		this.tipoObjeto = (ArrayList<String>) tipoObjeto.clone();
		this.todosEmOrdemDeAdd.clear();
	}
	
	public ArrayList<String> getTodosEmOrdemDeAdd() {
		return todosEmOrdemDeAdd;
	}
	public void setTodosEmOrdemDeAdd(ArrayList<String> todosEmOrdemDeAdd) {
		this.todosEmOrdemDeAdd = (ArrayList<String>) todosEmOrdemDeAdd.clone();
	}
	public void limparAtributosEmOrdemDeAdd() {
		this.todosEmOrdemDeAdd.clear();
	}
	
	public ArrayList<String> getTodos() {
		this.setTodos();
		return todos;
	}
	private void setTodos() {
		todos = (ArrayList) this.tipoInteiro.clone();
		for (int i = 0; i < tipoString.size(); i++) {
			todos.add(tipoString.get(i));
		}
		for (int i = 0; i < tipoObjeto.size(); i++) {
			todos.add(tipoObjeto.get(i));
		}
	}
	
	public void addInteiro(String nome) {
		tipoInteiro.add(nome);
		todosEmOrdemDeAdd.add(nome);
	}
	public void addString(String nome) {
		tipoString.add(nome);
		todosEmOrdemDeAdd.add(nome);
	}
	public void addObjeto(String nome) {
		tipoObjeto.add(nome);
		todosEmOrdemDeAdd.add(nome);
	}
	
	public void addListaDeInteiro(ArrayList<String> inteiro) {
		for (int i = 0; i < inteiro.size(); i++) {
			tipoInteiro.add(inteiro.get(i));
			todosEmOrdemDeAdd.add(inteiro.get(i));
		}
	}
	public void addListaDeString(ArrayList<String> string) {
		for (int i = 0; i < string.size(); i++) {
			tipoString.add(string.get(i));
			todosEmOrdemDeAdd.add(string.get(i));
		}
	}
	public void addListaDeObjeto(ArrayList<String> objeto) {
		for (int i = 0; i < objeto.size(); i++) {
			tipoObjeto.add(objeto.get(i));
			todosEmOrdemDeAdd.add(objeto.get(i));
		}
	}
	
	public void limpar() {
		this.tipoInteiro.clear();
		this.tipoString.clear();
		this.tipoObjeto.clear();
		this.todos.clear();
		this.todosEmOrdemDeAdd.clear();
	}
}