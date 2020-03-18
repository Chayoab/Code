package com.infotec.registro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement


public class Informacion {
	
    private int idEvento;
    private int idDocumento;
    private String documento;
    private String fechaAdd;
    private int idTipo;
    private String errorMsg;
    
    public Informacion() {
    	this.errorMsg="";
    }
	
	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String doc) {
		documento = doc;
	}

	public String getFechaAdd() {
		return fechaAdd;
	}

	public void setFechaAdd(String fechaAdd) {
		this.fechaAdd = fechaAdd;
	}
	
	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	@Override
	public String toString() {
		return "Rol [ idEvento= "+idEvento+", idDocumento= "+idDocumento+", documento= "+documento+", tipo= "+idTipo+", fechaAdd= "+fechaAdd+"]";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
