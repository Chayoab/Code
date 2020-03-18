package com.infotec.ses;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Rol {
	private String nomreRol;
	private String descripcionRol;
	private int idRol;
	private int id; // es un id del usuario que generará el Rol
	
	public Rol() {
		this.setId(0);
	}
	public String getNombreRol() {
		return nomreRol;
	}
	
	public void setNombreRol(String nomreRol) {
		this.nomreRol = nomreRol;
	}
	
	public String getDescripcionRol() {
		return descripcionRol;
	}
	
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
		
	@Override
	public String toString() {
		return "Rol [id="+id+", idRol= "+idRol+", nomreRol= "+nomreRol+", descripcionRol= "+descripcionRol+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
