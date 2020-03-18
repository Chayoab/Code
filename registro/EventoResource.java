package com.infotec.registro;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("eventos")

public class EventoResource {
	
	EventoRepo repo = new EventoRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("evento/{idProceso}/{idUsuario}/{status}")
	public List<Evento> getEventos(@PathParam("idProceso") int idProceso,@PathParam("idUsuario") int idUsuario,@PathParam("status") String status ) {
		System.out.println("Si corre!");		
		return repo.getEventos(idProceso,idUsuario,status);
	}	


	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("cevento")
	public Evento createEvento(Evento evento) {
		repo.create(evento);
		System.out.println(evento);
		return evento;
	}
	
	@PUT
	@Path("mevento")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Evento updateEvento(Evento evento) {
	    repo.update(evento);	
		System.out.println(evento);		
		return evento;
	}

}
