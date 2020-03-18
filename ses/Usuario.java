package com.infotec.ses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Usuario {
	
	private String nombre;
	private String paterno;
	private String materno;
	private String calle;
	private String telefono;
	private String numero;
	private String interior;
	private String colonia;
	private String municipio;
	private String estado;
	private String cp;
	private int idUsuario;
	private int id;
	private String usr;	
	private String email;
	
	public Usuario(){
		this.setId(0);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPaterno() {
		return paterno;
	}
	
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	
	public String getMaterno() {
		return materno;
	}
	
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
		
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	@Override
	public String toString() {
		return "Usuario [id="+id+",usr="+usr+",idUsruario= "+idUsuario+", nombre= "+nombre+", paterno= "+paterno+", materno= "+materno+", telefono= "+telefono+",  email="+ email + ", calle= "+calle+", numero= "+numero+", interior= "+interior+", colonia= "+colonia+", municipio= "+municipio+", estado= "+estado+", cp= "+cp+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
