package com.infotec.registro;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class Detalledoc {

    private int idTarea;
    private int idEvento;
    private String asunto;
    private String estadoProceso;
    private String estadoTarea;
    private String folio;
    private String comentarios;
	private String errorMsg;    
    
    private List<Informacion> documentos = new ArrayList<>();

    public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public List<Informacion> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Informacion> documentos) {
		this.documentos = documentos;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public String getEstadoTarea() {
		return estadoTarea;
	}
	public void setEstadoTarea(String estadoTarea) {
		this.estadoTarea = estadoTarea;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
    
	@Override
	public String toString() {
		return "Rol [idTarea="+idTarea+", idEvento= "+idEvento+", folio= "+folio+", estadoProceso= "+estadoProceso+", estadoTarea= "+estadoTarea+", asunto= "+asunto+", comentarios= "+comentarios+", errorMsg= "+errorMsg+"]";
	}
	
}
