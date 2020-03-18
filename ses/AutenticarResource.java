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

@Path("aut")

public final class AutenticarResource {

	AutenticarRepositorio repo = new AutenticarRepositorio();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Autenticar> getAutenticados() {
		System.out.println("Si corre!");		
		return repo.getAutenticados();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("autentica/{usuarioId}")
	public Autenticar getAutenticado(@PathParam("usuarioId") int usuarioId) {
		System.out.println("Si corre para un id!");		
		return repo.getAutenticado(usuarioId);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("aain/{usr}/{pwd}")
	public Autenticar getOut(@PathParam("usr") String usr, @PathParam("pwd") String pwd) {
		System.out.println("Si corre para validar!");	
		return repo.getOut(usr,pwd);
	}

	@POST
	@Path("cautentica")
	@Consumes({MediaType.APPLICATION_JSON})
	public Autenticar createAutenticado(Autenticar aut) {
		repo.create(aut);
		System.out.println(aut);		
		return aut;
	}
	
	@PUT
	@Path("mautentica")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Autenticar updateUsuario(Autenticar aut) {
		
		if (repo.getAutenticado(aut.getIdUsuario()).getIdUsuario()!=0) {
	    	repo.update(aut);
		}	
		System.out.println(aut);	
		return aut;
	}
	
	@PUT
	@Path("mpas")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Autenticar updatePass(Autenticar aut) {
		
    	repo.updatePass(aut);
		System.out.println(aut);	
		return aut;
	}
	
	@DELETE
	@Path("dautentica/{usuarioId}")
	public Autenticar destroyUsuario(@PathParam("usuarioId") int usuarioId) {

		Autenticar aut = repo.getAutenticado(usuarioId);	
		if (aut.getIdUsuario()!=0) {
			repo.delete(usuarioId);
		}				
		return aut;
	}
	
}
