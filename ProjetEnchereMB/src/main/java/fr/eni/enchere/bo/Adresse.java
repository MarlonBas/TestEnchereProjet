package fr.eni.enchere.bo;

public class Adresse {
	
	private int id_adresse;
	private String rue;
	private int codePostal;
	private String ville;
	
	
	public Adresse() {
		super();
	}

	


	public Adresse(int id_adresse, String rue, int codePostal, String ville) {
		super();
		this.id_adresse = id_adresse;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Adresse(String rue, int codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	public int getId_adresse() {
		return id_adresse;
	}


	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}
}
