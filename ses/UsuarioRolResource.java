package com.infotec.ses;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("usrsxroles")

public class UsuarioRolResource {

	UsuarioRolRepositorio repo = new UsuarioRolRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<UsuarioRol> getUsuariosRoles() {
		System.out.println("Si corre!");		
		return repo.getUsuariosRoles();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("usuariorol/{idUsuario}/{idRol}")
	public UsuarioRol getUsuarioRol(@PathParam("idUsuario") int idUsuario,@PathParam("idRol") int idRol) {
		System.out.println("Si corre para un id!");		
		return repo.getUsuarioRol(idUsuario,idRol);
	}

	@POST
	@Path("cusuariorol")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UsuarioRol createRol(UsuarioRol usuariorol) {
		repo.create(usuariorol);
		System.out.println(usuariorol);		
		return usuariorol;
	}
	

	@DELETE
	@Path("dusuariorol/{idUsuario}/{idRol}/{id}")
	public UsuarioRol destroyRol(@PathParam("idUsuario") int idUsuario, @PathParam("idRol") int idRol, @PathParam("id") int id) {

		UsuarioRol r = repo.getUsuarioRol(idUsuario,idRol);	
		r.setId(id);
		if (r.getIdRol()!=0) {
			repo.delete(r);
		}				
		return r;
	}

}
