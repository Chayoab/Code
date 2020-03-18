package com.infotec.registro;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("doc")

public class DetalledocResource {
	
	DetalledocRepo repo = new DetalledocRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("detalledoc/{idUsr}")
	public  List<Detalledoc> getDetalleDoc(@PathParam("idUsr") int idUsr) {
		System.out.println("Se buscan documentos de usuario");
		return repo.getDetalleDoc(idUsr);
	}
		
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("detalleone/{idUsr}/{idEvento}")
	public  Detalledoc getOneDetalleDoc(@PathParam("idUsr") int idUsr,@PathParam("idEvento") int idEvento) {
		System.out.println("Se buscan documentos de usuario");
		return repo.getOneDetalleDoc(idUsr,idEvento);
	}

	
	
}
