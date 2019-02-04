package com.banque.entities;

public class Client {
	private long code;
	private String nom;
	private String prenom;
	private String ville;
	
	public Client() {}
	
	public Client(String nom, String prenom, String ville) {
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
	}
	
	public Client(long code, String nom, String prenom, String ville) {
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof Client) ) 
			return false;
		if( this.code > ((Client)obj).code  )
			return true;
		return false;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int) this.code;
	}

	@Override
	public String toString() {
		return "Code : "+this.code+" | Nom : "+this.nom+" | Prenom : "+this.prenom+" | Ville : "+this.ville;
	}
	
	
	
	

}
