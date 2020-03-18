package com.infotec.registro;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DetalledocRepo {
	
	Connection con = null;
	
	public DetalledocRepo(){

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

	
	public  List<Detalledoc> getDetalleDoc( int idUsr) {
		List<Detalledoc> detdoc = new ArrayList<>();
				
		String idUsuario=Integer.toString(idUsr);
		
		String sql="SELECT  detalle.*, tarea.nomtarea, evento.estado FROM detalle, evento, tarea WHERE detalle.fecha_fin IS NULL AND detalle.idtarea=tarea.idtarea AND detalle.idevento=evento.idevento AND ( detalle.idusro = " + idUsuario + " OR detalle.idusrd = " + idUsuario + " )";
	    try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			InformacionRepo irepo = new InformacionRepo();
			while (rs.next()) {	
				int idEventoQuery=rs.getInt("idevento");
	      		Detalledoc d = new Detalledoc();
	      		d.setIdEvento(idEventoQuery);
	      		d.setAsunto(rs.getString("asunto"));
	      		d.setEstadoProceso(rs.getString("estado"));
	      		d.setEstadoTarea(rs.getString("nomtarea"));
	      		d.setFolio(rs.getString("folio"));
	      		d.setComentarios(rs.getString("comentarios"));
	      		d.setIdTarea (rs.getInt("idtarea"));
	      		d.setDocumentos(irepo.getInformacion(idEventoQuery));
	      		detdoc.add(d);
			}
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (detdoc.isEmpty()) {
	    	Detalledoc d = new Detalledoc();
	    	d.setErrorMsg("No hay elementos que cumplan con los criterios de búsqueda");
	    	detdoc.add(d); 
	    }
		return detdoc;	
	}

	public  Detalledoc getOneDetalleDoc( int idUsr, int idEvento) {
				
		String idUsuario=Integer.toString(idUsr);
		String idEve=Integer.toString(idEvento);
		Detalledoc d = new Detalledoc();
		
		String sql="SELECT  detalle.*, tarea.nomtarea, evento.estado FROM detalle, evento, tarea WHERE detalle.fecha_fin IS NULL AND detalle.idtarea=tarea.idtarea AND detalle.idevento=evento.idevento AND ( detalle.idusro = " + idUsuario + " OR detalle.idusrd = " + idUsuario + " ) AND detalle.idevento=" + idEve;
	    try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			InformacionRepo irepo = new InformacionRepo();
			while (rs.next()) {	
				int idEventoQuery=rs.getInt("idevento");
				d.setIdEvento(idEventoQuery);
	      		d.setAsunto(rs.getString("asunto"));
	      		d.setEstadoProceso(rs.getString("estado"));
	      		d.setEstadoTarea(rs.getString("nomtarea"));
	      		d.setFolio(rs.getString("folio"));
	      		d.setComentarios(rs.getString("comentarios"));
	      		d.setIdTarea (rs.getInt("idtarea"));
	      		d.setDocumentos(irepo.getInformacion(idEventoQuery));
	    
			}
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (d.getIdTarea()==0) {
	    	d.setErrorMsg("No existe informacón con los criterios de búsqueda seleccionados");
	    }
		return d;	
	}

	
	
}
