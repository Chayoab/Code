package com.infotec.ses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class UsuarioRol {
	
	private int id_usuario;
	private int idRol;
	private int id;
	
	public UsuarioRol() {
		this.setId(0);
	}
	
	public int getIdUsuario() {
		return id_usuario;
	}
	
	public void setIdUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
		
	@Override
	public String toString() {
		return "UdustioxRol [idRol= "+idRol+", id_usuario= "+id_usuario+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
