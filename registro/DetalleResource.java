package com.infotec.registro;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("det")

public class DetalleResource {
	
	DetalleRepo repo = new DetalleRepo();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("detalles/{idProceso}/{idUsuario}/{status}/{fecha_ini}/{asunto}/{folio}")
	public List<Detalle> getDetalles(@PathParam("idProceso") int idProceso,@PathParam("idUsuario") int idUsuario, @PathParam("status") String status, @PathParam("fecha_ini") String fecha_ini, @PathParam("asunto") String asunto, @PathParam("folio") String folio  ) {
		System.out.println("Si corre!");		
		return repo.getDetalles(idProceso, idUsuario, status, fecha_ini, asunto,folio);
	}	

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("detalle/{idProceso}/{idEvento}")
	public Detalle getDetalle(@PathParam("idProceso") int idProceso,@PathParam("idEvento") int idEvento) {
		System.out.println("Si corre!");		
		return repo.getDetalle(idProceso, idEvento);
	}	


	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("cdetalle")
	public Detalle createDetalle(Detalle detalle) {
		repo.create(detalle);
		System.out.println(detalle);
		return detalle;
	}
	
	@PUT
	@Path("mdetalle")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Detalle updateDetalle(Detalle detalle) {
	    String fechaIni="";
	    String fechaFin=" ";
	    String errorMsg=" ";
	    int tarea=detalle.getIdTarea();
	    Metrica meta = new Metrica();
	    Metrica metb = new Metrica();
		MetricaResource metRes = new MetricaResource();
	    
	    
		repo.update(detalle);	
	    System.out.println(detalle);
	    fechaFin+=detalle.getFecha_fin();
	    errorMsg+=detalle.geterrorMsg();
	    /* Si ya se asignó el destino, cambia de estado y se crea nueva tarea */
			
		if (detalle.getIdUsrd()>0 && errorMsg.length()<5 && fechaFin.length()>5 ) { // Si existe fecha final entonces terminó la etapa.
			if (tarea!=3) {
		    	tarea++;
		    	detalle.setIdTarea(tarea);
				fechaIni=detalle.getFecha_fin();
				detalle.setFecha_ini(fechaIni);
				repo.create(detalle);
				if (tarea==3) {
					meta.setAtendido(1);
					meta.setAsignado(-1);
					meta.setIdUsuario(detalle.getIdUsro());
					metRes.updateMetricas(meta);
					metb.setAtendido(1);
					metb.setAsignado(-1);
					metb.setIdUsuario(detalle.getIdUsrd());
					metRes.updateMetricas(metb);
				}
				
			}											
			else { // se tiene que hacer el cierre del proceso.
			   Evento ev = new Evento();
			   EventoRepo repoev = new EventoRepo();
               
			   ev.setFechaFin(detalle.getFecha_fin());
			   ev.setIdEvento(detalle.getIdEvento());
			   ev.setStatus("Terminado");
			   ev.setIdProceso(1);
			   repoev.update(ev);
			   meta.setAtendido(-1);
			   meta.setTerminado(1);
			   meta.setIdUsuario(detalle.getIdUsro());
			   metRes.updateMetricas(meta);
			   metb.setAtendido(-1);
			   metb.setTerminado(1);
			   metb.setIdUsuario(detalle.getIdUsrd());
			   metRes.updateMetricas(metb);
			}   
		}
		return detalle;
	}

}
