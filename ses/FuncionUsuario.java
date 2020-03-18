package com.infotec.ses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class FuncionUsuario {
	private int id_usuario;
	private String nombre;
	private String paterno;
	private String materno;
	private String Rol;
	private int idRol;
    private List<Funcion> funciones = new ArrayList<>();
    
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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
	public String getRol() {
		return Rol;
	}
	public void setRol(String rol) {
		Rol = rol;
	}
	public List<Funcion> getFunciones() {
		return funciones;
	}
	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
    
}
