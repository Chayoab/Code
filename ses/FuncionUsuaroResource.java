package com.infotec.ses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("funcionU")

public class FuncionUsuaroResource {
	FuncionUsuarioRepo repo = new FuncionUsuarioRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("funcionUsr/{usuarioId}")
	public FuncionUsuario getFuncionUsuario(@PathParam("usuarioId") int usuarioId) {
		System.out.println("Si corre para un id!");		
		return repo.getFuncionUsuario(usuarioId);
	}

}
