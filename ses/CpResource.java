package com.infotec.ses;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("cp")

public class CpResource {


	CpRepositorio repo = new CpRepositorio();
	
	
    	@GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("cpscol/{colonia}")
		public List<Cp> getCpColonia(@PathParam("colonia") String colonia) {
			System.out.println("Si corre!");		
			return repo.getCpColonia(colonia);
		}
		
		@GET
		@Produces({MediaType.APPLICATION_JSON})
		@Path("cpscp/{cp}")
		public List<Cp> getCpInfo(@PathParam("cp") String cp) {
			System.out.println("Si corre!");		
			return repo.getCpInfo(cp);
		}	
}
