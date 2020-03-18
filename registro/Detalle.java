package com.infotec.registro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement


public class Detalle { 
	
	private int idTarea;
	private int idEvento;
	private int idUsro;
	private int idUsrd;
	private String asunto;
	private String comentarios;
	private String folio;
	private String fecha_ini;
	private String fecha_act;
	private String fecha_fin;
	private String errorMsg;
	
	
	public Detalle() {
		this.errorMsg="";
	}

	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getIdUsro() {
		return idUsro;
	}
	public void setIdUsro(int idUsro) {
		this.idUsro = idUsro;
	}
	public int getIdUsrd() {
		return idUsrd;
	}
	public void setIdUsrd(int idUsrd) {
		this.idUsrd = idUsrd;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public String getFecha_act() {
		return fecha_act;
	}
	public void setFecha_act(String fecha_act) {
		this.fecha_act = fecha_act;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}	
	public String geterrorMsg() {
		return errorMsg;
	}
	public void seterrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "Rol [idTarea="+idTarea+", idEvento= "+idEvento+", idUsro= "+idUsro+", idUsrd= "+idUsrd+", asunto= "+asunto+", folio= "+folio+", comentarios= "+comentarios+", errorMsg= "+errorMsg+", fecha_ini= "+fecha_ini+", fecha_act= "+fecha_act+", fecha_fin= "+fecha_fin+"]";
	}
}
