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

@Path("roles")
public class RolResource {
	RolRepositorio repo = new RolRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Rol> getRoles() {
		System.out.println("Si corre!");		
		return repo.getRoles();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("roles/{name}")
	public List<Rol> getRolesName(@PathParam("name") String nombreRol) {
		System.out.println("Si corre!");		
		return repo.getRolesName(nombreRol);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("rol/{idRol}")
	public Rol getRol(@PathParam("idRol") int idRol) {
		System.out.println("Si corre para un id!");		
		return repo.getRol(idRol);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rolesfun/{idFuncion}/{aob}")
	public List<Rol> getRolFuncion(@PathParam("idFuncion") int idFuncion, @PathParam("aob") String aob ) {
		System.out.println("Si busco rol x funcion!-"+idFuncion+" "+aob);	
		return repo.getRolFuncion(idFuncion,aob);
	}
	
	@POST
	@Path("crol")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Rol createRol(Rol rol) {
		repo.create(rol);
		System.out.println(rol);		
		return rol;
	}
	
	@PUT
	@Path("mrol")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Rol updateRol(Rol rol) {
		
		if (repo.getRol(rol.getIdRol()).getIdRol()==0) {
			repo.create(rol);
		}
		else {
	    	repo.update(rol);
		}	
		System.out.println(rol);	
		return rol;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("drol/{idRol}/{idUsr}")
	public Rol destroyRol(@PathParam("idRol") int idRol, @PathParam("idUsr") int idUsr) {

		Rol r = repo.getRol(idRol);	
		r.setId(idUsr);
		if (r.getIdRol()!=0) {
			repo.delete(r);
		}				
		return r;
	}

	
	
}
