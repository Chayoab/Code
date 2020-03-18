package com.infotec.ses;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("funciones")

public class FuncionResource {
	FuncionRepositorio repo = new FuncionRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Funcion> getFunciones() {
		System.out.println("Si corre!");		
		return repo.getFunciones();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("funciones/{name}")
	public List<Funcion> getFuncionesName(@PathParam("name") String nombreFuncion) {
		System.out.println("Si corre!");		
		return repo.getFuncionesName(nombreFuncion);
	}	

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("funcion/{id_funcion}")
	public Funcion getFuncion(@PathParam("id_funcion") int id_funcion) {
		System.out.println("Si corre para uno!");	
		return repo.getFuncion(id_funcion);
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("cfuncion")
	public Funcion createFuncion(Funcion funcion) {
		repo.create(funcion);
		System.out.println(funcion);
		return funcion;
	}
	
	@PUT
	@Path("mfuncion")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Funcion updateFuncion(Funcion funcion) {
		
		if (repo.getFuncion(funcion.getId()).getId()==0) {
			repo.create(funcion);
		}
		else {
	    	repo.update(funcion);
		}	
		System.out.println(funcion);		
		return funcion;
	}

	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("dfuncion/{idFuncion}/{idUsr}")
	public Funcion destroyFuncion(@PathParam("idFuncion") int idFuncion, @PathParam("idUsr") int idUsr) {
		
		Funcion f = repo.getFuncion(idFuncion);	
		f.setIdusr(idUsr);
		if (f.getId()!=0) {
			repo.delete(f);
		}				
		return f;
	}
}
