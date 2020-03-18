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

@Path("usuarios")

public class UsuarioResource {

	UsuarioRepositorio repo = new UsuarioRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Usuario> getUsuarios() {
		System.out.println("Si corre!");		
		return repo.getUsuarios();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("qusuarios/{nombre}/{paterno}")
	public List<Usuario> getUsuariosName(@PathParam("nombre") String nom, @PathParam("paterno") String pat ) {
		System.out.println("Si busco usuarios x nombre!-"+nom+" "+pat);	
		return repo.getUsuariosName(nom,pat);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("usuariosa/{nombre}/{paterno}")
	public List<Usuario> getUsuariosActivos(@PathParam("nombre") String nom, @PathParam("paterno") String pat ) {
		System.out.println("Si busco usuarios x nombre!-"+nom+" "+pat);	
		return repo.getUsuariosActivos(nom,pat);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("usuariosb/{nombre}/{paterno}")
	public List<Usuario> getUsuariosBloqueados(@PathParam("nombre") String nom, @PathParam("paterno") String pat ) {
		System.out.println("Si busco usuarios x nombre!-"+nom+" "+pat);	
		return repo.getUsuariosBloqueados(nom,pat);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("usuariosrol/{idRol}/{aob}")
	public List<Usuario> getUsuariosRol(@PathParam("idRol") int idRol, @PathParam("aob") String aob ) {
		System.out.println("Si busco usuarios x rol!-"+idRol+" "+aob);	
		return repo.getUsuariosRol(idRol,aob);
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("usuario/{usuarioId}")
	public Usuario getUsuario(@PathParam("usuarioId") int usuarioId) {
		System.out.println("Si corre para un id!");		
		return repo.getUsuario(usuarioId);
	}

	@POST
	@Path("cusuario")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Usuario createUsuario(Usuario usr) {
		repo.create(usr);
		System.out.println(usr);		
		return usr;
	}
	
	@PUT
	@Path("musuario")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Usuario updateUsuario(Usuario usr) {
		
		if (repo.getUsuario(usr.getIdUsuario()).getIdUsuario()==0) {
			repo.create(usr);
		}
		else {
	    	repo.update(usr);
		}	
		System.out.println(usr);	
		return usr;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("dusuario/{usuarioId}/{id}")
	public Usuario destroyUsuario(@PathParam("usuarioId") int usuarioId,@PathParam("id") int id) {
		
		Usuario u = repo.getUsuario(usuarioId);	
		u.setId(id);
		if (u.getIdUsuario()!=0) {
			repo.delete(u);
		}				
		return u;
	}

}
