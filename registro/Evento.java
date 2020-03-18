package com.infotec.registro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Evento {
	private int idProceso;
	private int idEvento;
	private int idUsr;
	private String status;
	private String fechaIni;
	private String fechaFin;
	private String errorMsg;
	
	public Evento() {
		this.errorMsg="";
	}
	public int getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String estado) {
		status = estado;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaInicial) {
		fechaIni = fechaInicial;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFinal) {
		fechaFin = fechaFinal;
	}
	public int getIdUsr() {
		return idUsr;
	}
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	@Override
	public String toString() {
		return "Rol [idProceso="+idProceso+", idEvento= "+idEvento+", idUsr= "+idUsr+", Status= "+status+", fechaIni= "+fechaIni+", fechaFin= "+fechaFin+"]";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
