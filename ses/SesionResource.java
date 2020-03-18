package com.infotec.ses;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sesion")

public class SesionResource {

	SesionRepositorio repo = new SesionRepositorio();
	

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("sesin/{usr}/{usrkey}")
	public Sesion getOut(@PathParam("usr") String usr, @PathParam("usrkey") String usrKey) {
		System.out.println("Si corre para validar!");	
		return repo.getVal(usr,usrKey);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("sesiona/{usuarioId}")
	public Sesion getSesionado(@PathParam("usuarioId") int usuarioId) {
		System.out.println("Si busca id para sesionar!");		
		return repo.getSesionado(usuarioId);
	}	
	
	@DELETE
	@Path("sesout/{usr}")
	public Sesion destroySesion(@PathParam("usr") int usuarioId) {

		Sesion ses = repo.getSesionado(usuarioId);	
		if (ses.getId_usr()!=0) {
			repo.delete(usuarioId);
		}				
		return ses;
	}
}
