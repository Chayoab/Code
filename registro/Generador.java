package com.infotec.registro;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Generador {
	
	private Evento evento;
	private Detalle detalle;
	private Informacion informacion;
	private String errorMsg;
	
	
    public Generador() {
    	  	
    	this.setErrorMsg(" ");    	  	
    }
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Detalle getDetalle() {
		return detalle;
	}
	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}
	public Informacion getInformacion() {
		return informacion;
	}
	public void setInformacion(Informacion informacion) {
		this.informacion = informacion;
	}
		
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		return "Rol [evento="+evento+", detalle= "+detalle+", informacion= "+informacion+"]";
	}
	
}
