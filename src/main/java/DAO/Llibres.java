
package DAO;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Llibres {

	// private String id;
	private String titol;
	private String autor;
	private int any_Publicacio;
	private String descripcio;
	private List<String> categories;

	public Llibres(String titol, String autor, int any_publicacio, String descripcio, List<String> categories) {
		this.titol = (titol);
		this.autor = (autor);
		this.any_Publicacio = (any_publicacio);
		this.descripcio = (descripcio);
		this.categories = (categories);
	}

	public Llibres() {

	}

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 */
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public int getAny_Publicacio() {
		return any_Publicacio;
	}

	public void setAny_Publicacio(int any_Publicacio) {
		this.any_Publicacio = any_Publicacio;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String toJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Autor", this.autor);
		jsonObject.put("Titol", this.titol);
		jsonObject.put("Any_publicacio", this.any_Publicacio);
		jsonObject.put("Categories", this.categories);
		jsonObject.put("Descripcio", this.descripcio);

		return jsonObject.toString();
	}
}
