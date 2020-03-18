package com.infotec.registro;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("metrica")

public class MetricaResource {
	
	MetricaRepo repo = new MetricaRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("unametrica/{idUsuario}")
	public Metrica getMetrica(@PathParam("idUsuario") int idUsuario) {
		System.out.println("Intenta consultar metricas!");		
		return repo.getMetrica(idUsuario);
	}	


	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("cmetricas")
	public Metrica createMetrica(Metrica metrica) {
		repo.create(metrica);
		System.out.println(metrica);
		return metrica;
	}
	
	
	public Metrica addMetricas(Metrica a, Metrica b) {
		Metrica m=new Metrica();
		int asignado=a.getAsignado()+b.getAsignado();
		int atendido=a.getAtendido()+b.getAtendido();
		int cancelado=a.getCancelado()+b.getCancelado();
		int creado=a.getCreado()+b.getCreado();
		int terminado=a.getTerminado()+b.getTerminado();
		
		m.setAsignado(asignado);
		m.setAtendido(atendido);
		m.setCancelado(cancelado);
		m.setCreado(creado);
		m.setTerminado(terminado);
		m.setIdUsuario(a.getIdUsuario());
		
		return m;
	}
	
	@PUT
	@Path("mmetrica")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Metrica updateMetricas(Metrica metrica) {
		int idUsuario=metrica.getIdUsuario();
		Metrica qmetrica=repo.getMetrica(idUsuario);
		Metrica umetrica= new Metrica();
		
	    if (qmetrica.getIdUsuario()==0) { // Es una metrica de un usuario nuevo, se crea.
	    	qmetrica.setIdUsuario(idUsuario);
	    	repo.create(qmetrica);
	    }
	    umetrica=addMetricas(metrica,qmetrica);
	    repo.update(umetrica);	
	    System.out.println(metrica);
	    System.out.println(qmetrica);
		System.out.println(umetrica);		
		return umetrica;
	}

}
