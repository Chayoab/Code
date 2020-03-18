package com.infotec.ses;

public class Cp {
  private String cp;
  private String colonia;
  private String municipio;
  private String estado;
  
public String getCp() {
	return cp;
}
public void setCp(String cp) {
	this.cp = cp;
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

@Override
public String toString() {
	return "Cp [cp="+cp+",colonia="+colonia+",municipio= "+municipio+", estado= "+estado+"]";
}
  
}
