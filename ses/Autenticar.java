package com.infotec.ses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public final class Autenticar {
	private String usr;
	private String pwd;
	private int block; /* bandera utilizada únicamente para bloqueo de usuarios y reset de password */
	private int idUsuario;
	private int id; /* ID de la solicitud */
	private String muestra; /*Variable que almacena el código de autenticación temporal para cada sesión al momento de autenticar.*/
	
	public Autenticar() {
		this.setId(0);
	}
	
	public String getUsr() {
		return usr;
	}
	
	public void setUsr(String usr) {
		this.usr = usr;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getBlock() {
		return block;
	}
	
	public void setBlock(int block) {
		this.block = block;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
		
	public String getMuestra() {
		return muestra;
	}

	public void setMuestra(String muestra) {
		this.muestra = muestra;
	}
	
	@Override
	public String toString() {
		return "Usuario [id= "+id+", idUsruario= "+idUsuario+", usr= "+usr+", pwd= "+pwd+", block= "+block+", muestra= "+muestra+"]";
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
