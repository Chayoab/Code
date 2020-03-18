package com.infotec.registro;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class GeneradorRepo {
	
	Connection con = null;
	
	
	public GeneradorRepo() {
		Properties prop= new Properties();
		String propFilename="config.properties";
		String urldb="";
		String usrname="";
		String paswd="";
	
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFilename);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		urldb= prop.getProperty("db");
		usrname= prop.getProperty("usr");
		paswd= prop.getProperty("pwd");

 		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(urldb, usrname, paswd);
			System.out.println("Conexion exitosa");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}  			

	}
	
	public void create(Generador generador) {
		
		/* Paso 1 se crea el evento */
		
		EventoRepo evrepo= new EventoRepo();
		DetalleRepo detrepo=new DetalleRepo();
		InformacionRepo inforepo=new InformacionRepo();
		Metrica metCreado = new Metrica();
		Metrica metAsignado = new Metrica();
		MetricaResource metResource= new MetricaResource();
		
		evrepo.create(generador.getEvento());
		
		if ( generador.getEvento().getIdEvento() > 0  ) {
			generador.getDetalle().setIdEvento(generador.getEvento().getIdEvento());
			generador.getInformacion().setIdEvento(generador.getEvento().getIdEvento());
			detrepo.update(generador.getDetalle());
		}
		else {
			generador.setErrorMsg("No fué posible crear el evento, favor de notificarlo al administrador");
			return;
		}
		generador.getDetalle().setIdTarea(2);
		detrepo.create(generador.getDetalle());
		inforepo.create(generador.getInformacion());
		if ( generador.getInformacion().getIdDocumento() < 1) {
			generador.setErrorMsg("No fué posible hacer el registro del documento en la base de datos");	
			return;
		}
		else { // Se actualizan las métricas
			metCreado.setCreado(1);
			metCreado.setAsignado(1);
			metCreado.setIdUsuario(generador.getEvento().getIdUsr());
			metResource.updateMetricas(metCreado);
			metAsignado.setAsignado(1);
			metAsignado.setIdUsuario(generador.getDetalle().getIdUsrd());
			metResource.updateMetricas(metAsignado);
		}
			
	}
	

}
