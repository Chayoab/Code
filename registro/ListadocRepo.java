package com.infotec.registro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("doc")

public class ListadocRepo {

	DetalledocRepo repo = new DetalledocRepo();

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("listadoc/{idUsr}")
	public  Listadoc getDetalleDoc(@PathParam("idUsr") int idUsr) {
		Listadoc lista = new Listadoc();
		System.out.println("Se buscan documentos de usuario");
		lista.setDetdoc(repo.getDetalleDoc(idUsr));
		return lista;
	}
	
	
}
