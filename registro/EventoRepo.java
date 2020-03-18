package com.infotec.registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.infotec.registro.Evento;



public class EventoRepo {

	Connection con = null;
	
	public EventoRepo(){
		/*      
    	String urldb="jdbc:mariadb://localhost:3306/ctldoc";
		String usrname="root";
		String paswd="rootiptv";
	
    	String urldb="jdbc:mariadb://172.17.0.3:3306/ctldoc";
		String usrname="crlconn";
		String paswd="welcome1";
*/

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
	
	public List<Evento> getEventos( int idProceso, int idUsuario, String status) {
		List<Evento> eventos = new ArrayList<>();
		String sql;
		if (  status.contains("Todos")==true  ) {
			sql="Select ctldoc.evento.* from ctldoc.evento, ctldoc.detalle where  evento.idevento=detalle.idevento AND evento.idproceso="+ Integer.toString(idProceso)  +" AND (detalle.idusro ="+ Integer.toString(idUsuario) +" OR detalle.idusrd="+Integer.toString(idUsuario)+")";  
		}   
		else {
			sql="Select ctldoc.evento.* from ctldoc.evento, ctldoc.detalle where  evento.idevento=detalle.idevento AND evento.idproceso="+ Integer.toString(idProceso)  +" AND evento.estado like '"+status+"%' AND (detalle.idusro ="+idUsuario+" OR detalle.idusrd="+idUsuario+")";
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Evento e = new Evento();
				
				e.setIdEvento(rs.getInt("idevento"));
				e.setIdProceso(idProceso);
				e.setIdUsr(idUsuario);
				e.setStatus(rs.getString("estado"));
				e.setFechaIni(rs.getString("fecha_ini"));
				e.setFechaFin(rs.getString("fecha_fin"));
				eventos.add(e);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		if (eventos.isEmpty()) {
			Evento e = new Evento();
			    e.setErrorMsg("No fue posible encontrar un evento con los criterios de búsqueda");
			eventos.add(e);
		}
		return eventos;	
	}

	
	public int getmax() {
		
		int maxEvento=-1;
		String sql="select max(idevento) maximo from ctldoc.evento";
		try {
	    	Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery(sql);
		    while (rs.next()) {			
		    	maxEvento = rs.getInt("maximo");
		    }
		}    
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}	
		maxEvento++;
        return maxEvento;

}
	
	public void create(Evento evento) {
			int idEvento=0;
			
			/* Paso 1 crea el evento */
			String sql="insert into ctldoc.evento (idproceso,idevento,estado,fecha_ini) values (?,?,'Abierto',?)";
			idEvento=this.getmax();
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt (1, evento.getIdProceso() );
				st.setInt (2, idEvento);
				st.setString(3, evento.getFechaIni() );
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}	
			
			/* Paso 2 valida que exista un nuevo evento creado */
			sql="Select ctldoc.evento.idevento from ctldoc.evento where evento.idproceso="+Integer.toString(evento.getIdProceso())+" AND evento.estado like 'Abierto' AND fecha_ini like '"+evento.getFechaIni()+"' ";
			try {
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while (rs.next()) {
					evento.setIdEvento(rs.getInt("idevento"));
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			
			/* Paso 3 si se creó el evento, se genera el detalle del evento */
			if (evento.getIdEvento()!=0) {
			    sql="insert into ctldoc.detalle (idevento,idtarea,idusro,fecha_ini,fecha_act) VALUES (?,1,?,?,?)";
				
				try {
					PreparedStatement st=con.prepareStatement(sql);
					st.setInt (1, evento.getIdEvento() );
					st.setInt(2, evento.getIdUsr());
					st.setString(3, evento.getFechaIni() );
					st.setString(4, evento.getFechaIni() );
					st.executeUpdate();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e);
				}	
				
			}
			else {
				evento.setErrorMsg("No fué posible crear debidamente el evento");
			}

	}

	public void update(Evento evento) {
		    String fechaf=evento.getFechaFin();
     		String sql="";
     		String status=evento.getStatus();
     		Date objDate = new Date();
     		String strDateFormat = "yyyy/MM/dd hh:mm:ss";
     		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

     		
     		if (fechaf.isEmpty()) {
     			if (status.contains("Cancelado") || status.contains("Terminado")) {
     				fechaf=objSDF.format(objDate);
     				sql="update ctldoc.evento set estado='"+evento.getStatus()+"', fecha_fin='"+fechaf+"' where idevento=?";
     			}else {
     			    sql="update ctldoc.evento set estado='"+evento.getStatus()+"' where idevento=?";
     			}    
     		}
     		else {
     			if (status.contains("Suspendido") || status.contains("Abierto")) {
     				sql="update ctldoc.evento set estado='"+evento.getStatus()+"' where idevento=?";
     			}
     			else {
     			    sql="update ctldoc.evento set estado='"+evento.getStatus()+"', fecha_fin='"+evento.getFechaFin()+"' where idevento=?";
     			}
     		}
	     	try {
	     		PreparedStatement st=con.prepareStatement(sql);
	     		st.setInt(1, evento.getIdEvento() );
	    		st.executeUpdate();			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		System.out.println(e);
	    	}	
	     	// Se actualizan las métricas para el caso de que sea una cancelación.
	     	if ( status.contains("Cancelado") ) {
	     		Detalle detalle = new Detalle();
	     		DetalleRepo detRepo = new DetalleRepo();
	    	    Metrica meta = new Metrica();
	    	    Metrica metb = new Metrica();
	    		MetricaResource metRes = new MetricaResource();
	    		
	    		detalle=detRepo.getDetalle(1, evento.getIdEvento());
	    		
				meta.setCancelado(1);
				metb.setCancelado(1);
				if (detalle.getIdTarea()==2) {
				   meta.setAsignado(-1);
				   metb.setAsignado(-1);
				}
				if (detalle.getIdTarea()==3) {
					meta.setAtendido(-1);
					metb.setAtendido(-1);
				}
				meta.setIdUsuario(detalle.getIdUsro());
				metRes.updateMetricas(meta);
				metb.setIdUsuario(detalle.getIdUsrd());
				metRes.updateMetricas(metb);
	     	}
	     	
	}
 
	

}
