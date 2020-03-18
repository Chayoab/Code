package com.infotec.registro;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Listadoc{
	
	private  List<Detalledoc> detdoc;

	public List<Detalledoc> getDetdoc() {
		return detdoc;
	}

	public void setDetdoc(List<Detalledoc> detdoc) {
		this.detdoc = detdoc;
	}


}
