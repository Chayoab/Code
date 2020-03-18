package com.infotec.ses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class RolFuncion {

	private int idFuncion;
	private int idRol;
	private String tipoPermiso;
	private int id;
	
	public RolFuncion(){
		this.setId(0);
	}
	
	public int getIdFuncion() {
		return idFuncion;
	}
	
	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	
	public String getTipoPermiso() {
		return tipoPermiso;
	}
	
	public void setTipoPermiso(String tipoPermiso) {
		this.tipoPermiso = tipoPermiso;
	}
		
	@Override
	public String toString() {
		return "RolxFuncion [id= "+id+",idRol= "+idRol+", idFuncion= "+idFuncion+", tipoPermiso= "+tipoPermiso+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
