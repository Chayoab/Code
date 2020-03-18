package com.infotec.registro;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("generacion")

public class GeneradorResource {
	
	GeneradorRepo repo = new GeneradorRepo();
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("cgenera")
	public Generador createEvento(Generador generador) {
		
		Date objDate = new Date();
 		String strDateFormat = "yyyy/MM/dd hh:mm:ss";
 		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
 		String fecha=objSDF.format(objDate);
 		
 		System.out.println(fecha);
 		
 		generador.getEvento().setFechaIni(fecha);
 		generador.getEvento().setIdProceso(1);
 		generador.getEvento().setStatus("Abierto");
 		generador.getDetalle().setFecha_act(fecha);
 		generador.getDetalle().setFecha_fin(fecha);
 		generador.getDetalle().setFecha_ini(fecha);
 		generador.getDetalle().setIdTarea(1);
 		generador.getDetalle().setIdUsro(generador.getEvento().getIdUsr());
 		generador.getInformacion().setFechaAdd(fecha);
 		generador.getInformacion().setIdTipo(1);
    	
		
		repo.create(generador);
		System.out.println(generador);
		return generador;
	}

}
