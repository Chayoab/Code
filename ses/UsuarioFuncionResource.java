package com.infotec.ses;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("UsrFun")

public class UsuarioFuncionResource {
	UsuarioFuncionRepo repo = new UsuarioFuncionRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("usrFuncion/{nombreFuncion}")
	public List<UsuarioFuncion>getUsuarioFuncion(@PathParam("nombreFuncion") String nombreFuncion) {
		System.out.println("Si corre para un id!");		
		return repo.getUsuarioFuncion(nombreFuncion);
	}
}
