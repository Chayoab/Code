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

@Path("rolesfunciones")

public class RolFuncionesResource {

	RolFuncionRepositorio repo = new RolFuncionRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<RolFuncion> getRolesFunciones() {
		System.out.println("Si corre!");		
		return repo.getRolesFunciones();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rolfuncion/{idFuncion}/{idRol}")
	public RolFuncion getRolFuncion(@PathParam("idFuncion") int idFuncion,@PathParam("idRol") int idRol) {
		System.out.println("Si corre para un id!");		
		return repo.getRolFuncion(idFuncion,idRol);
	}

	@POST
	@Path("crolfuncion")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public RolFuncion createRol(RolFuncion rolFuncion) {
		repo.create(rolFuncion);
		System.out.println(rolFuncion);		
		return rolFuncion;
	}
	
	@PUT
	@Path("mrolfuncion")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public RolFuncion updateRolFuncion(RolFuncion rf) {
		
		if (repo.getRolFuncion(rf.getIdFuncion(),rf.getIdRol()).getIdRol()==0) {
			repo.create(rf);
		}
		else {
	    	repo.update(rf);
		}	
		System.out.println(rf);	
		return rf;
	}

	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("drolfuncion/{idFuncion}/{idRol}/{id}")
	public RolFuncion destroyRol(@PathParam("idFuncion") int idFuncion,@PathParam("idRol") int idRol,@PathParam("id") int id) {

		RolFuncion r = repo.getRolFuncion(idFuncion,idRol);	
		r.setId(id);
		if (r.getIdRol()!=0) {
			repo.delete(r);
		}				
		return r;
	}

	
}
