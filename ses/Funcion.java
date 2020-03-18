package com.infotec.ses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Funcion {

	private String nombreFuncion;
	private String descripcionFuncion;
	private int idFuncion;
	private int idUsr;  // es un id del usuario que generará la función
	
	public Funcion() {
		this.setId(0);
	}	
	public String getNombre() {
		return nombreFuncion;
	}
	
	public void setNombre(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}
		
	public String getDescripcion() {
		return descripcionFuncion;
	}
	
	public void setDescripcion(String descripcionFuncion) {
		this.descripcionFuncion = descripcionFuncion;
	}
		
	public int getId() {
		return idFuncion;
	}
	
	public void setId(int idFuncion) {
		this.idFuncion = idFuncion;
	}
	
	@Override
	public String toString() {
		return "Funcion [idUsr= "+idUsr+", idFuncion= "+idFuncion+", nombreFuncion= "+nombreFuncion+", descripcionFuncion= "+descripcionFuncion+"]";
	}

	public int getIdusr() {
		return idUsr;
	}

	public void setIdusr(int idUsr) {
		this.idUsr = idUsr;
	}
	
}
