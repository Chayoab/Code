package com.infotec.registro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Metrica {
	private int creado;
	private int asignado;
	private int idUsuario;
	private int atendido;
	private int terminado;
	private int cancelado;
	private String errorMsg;
	
	public Metrica() {
		this.creado=0;
		this.asignado=0;
		this.atendido=0;
		this.terminado=0;
		this.cancelado=0;
		this.errorMsg="";
	}

	public int getCreado() {
		return creado;
	}
	public void setCreado(int creado) {
		this.creado = creado;
	}	
		
	public int getAsignado() {
		return asignado;
	}
	public void setAsignado(int asignado) {
		this.asignado = asignado;
	}	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public int getAtendido() {
		return atendido;
	}
	public void setAtendido(int atendido) {
		this.atendido = atendido;
	}
	
	public int getTerminado() {
		return terminado;
	}
	public void setTerminado(int terminado) {
		this.terminado = terminado;
	}
	
	public int getCancelado() {
		return cancelado;
	}
	public void setCancelado(int cancelado) {
		this.cancelado = cancelado;
	}
	
	
	@Override
	public String toString() {
		return "Rol [idUsuario="+idUsuario+", creado= "+creado+", asignado= "+asignado+", atendido= "+atendido+", terminado= "+terminado+", cancelado= "+cancelado+"]";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
