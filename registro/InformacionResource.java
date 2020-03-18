package com.infotec.registro;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("docu")

public class InformacionResource {

	InformacionRepo repo = new InformacionRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("informacion/{idEvento}")
	public List<Informacion> getInfo(@PathParam("idEvento") int idEvento) {
		System.out.println("Si corre!");		
		return repo.getInformacion(idEvento);
	}	

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("info/{idDocumento}")
	public Informacion getInformacion(@PathParam("idDocumento") int idDocumento) {
		System.out.println("Si corre!");		
		return repo.getInfo(idDocumento);
	}	


	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("cinformacion")
	public Informacion createInformacion(Informacion informacion) {
		repo.create(informacion);
		System.out.println(informacion);
		return informacion;
	}
	
	
}
