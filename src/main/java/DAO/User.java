package DAO;

public class User {
	private String name;
	private String gmail;
	private String paswd;

	public User(String nombre, String apellido, String password) {
		this.name = nombre;
		this.gmail = apellido;
		this.paswd=(password);
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getGmail() {
		return gmail;
	}

	// Setters
	public void setName(String nombre) {
		this.name = nombre;
	}

	public void setGmail(String apellido) {
		this.gmail = apellido;
	}

	public String getPaswd() {
		return paswd;
	}

	public void setPaswd(String paswd) {
		this.paswd = paswd;
	}
}
