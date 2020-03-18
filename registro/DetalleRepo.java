package com.infotec.registro;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DetalleRepo {
	
	Connection con = null;
	
	public DetalleRepo(){
/*        
		String urldb="jdbc:mariadb://localhost:3306/ctldoc";
		String usrname="root";
		String paswd="rootiptv";
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

	
	public Detalle getDetalle( int idProceso, int idEvento) {
		Detalle d = new Detalle();
		String sql="Select ctldoc.detalle.* from ctldoc.evento, ctldoc.detalle where detalle.idevento=evento.idevento AND evento.idproceso="+ Integer.toString(idProceso) +" AND detalle.idevento="+ Integer.toString(idEvento)+" order by detalle.idTarea desc";  

	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {		
				d.setIdEvento(rs.getInt("idevento"));
				d.setIdTarea(rs.getInt("idtarea"));
				d.setIdUsro(rs.getInt("idusro"));
				d.setIdUsrd(rs.getInt("idusrd"));
				d.setAsunto(rs.getString("asunto"));
				d.setFolio(rs.getString("folio"));
                d.setComentarios(rs.getString("comentarios"));
				d.setFecha_act(rs.getString("fecha_act"));
				d.setFecha_fin(rs.getString("fecha_fin"));
				d.setFecha_ini(rs.getString("fecha_ini"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (d.getIdEvento()==0) {
	    	d.seterrorMsg("No existe informacón con los datos solicitados.");
	    }
		return d;	
	}


	
	public List<Detalle> getDetalles( int idProceso, int idUsuario, String status, String fecha_ini, String asunto, String folio) {
		List<Detalle> detalles = new ArrayList<>();
		String sql;
		if (  status.contentEquals("Todos")==true  ) { 
			sql="Select ctldoc.detalle.* from ctldoc.evento, ctldoc.detalle where detalle.idevento=evento.idevento AND idproceso="+ Integer.toString(idProceso)  +" AND (idusro ="+ Integer.toString(idUsuario) +" OR idusrd="+Integer.toString(idUsuario)+")";  
		}   
		else {
			sql="Select ctldoc.detalle.* from ctldoc.evento, ctldoc.detalle where detalle.idevento=evento.idevento AND idproceso="+ Integer.toString(idProceso)  +" AND estado like '"+status+"%' AND (idusro ="+idUsuario+" OR idusrd="+idUsuario+")";
		}
		if ( fecha_ini.contentEquals("0") == false) { // se solicito con fecha válida 0 indica default no fecha
			sql=sql+" AND evento.fecha_ini like '"+fecha_ini.substring(0,4) +"/"+fecha_ini.substring(4,6)+"/"+fecha_ini.substring(6,8)+"%'";
		}
		if ( asunto.contentEquals("0") == false ) { // se solicito para un asunto particular 0 indica default no asunto
			sql=sql+" AND detalle.asunto like '"+asunto+"%'";
		}
		if ( folio.contentEquals("0") == false ) { // se solicito para un asunto particular 0 indica default no asunto
			sql=sql+" AND detalle.folio like '"+folio+"%'";
		}
	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Detalle d = new Detalle();
				
				d.setIdEvento(rs.getInt("idevento"));
				d.setIdTarea(rs.getInt("idtarea"));
				d.setIdUsro(rs.getInt("idusro"));
				d.setIdUsrd(rs.getInt("idusrd"));
				d.setAsunto(rs.getString("asunto"));
				d.setFolio(rs.getString("folio"));
                d.setComentarios(rs.getString("comentarios"));
				d.setFecha_act(rs.getString("fecha_act"));
				d.setFecha_fin(rs.getString("fecha_fin"));
				d.setFecha_ini(rs.getString("fecha_ini"));
				detalles.add(d);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (detalles.isEmpty()) {
	    	Detalle d = new Detalle();
	    	d.seterrorMsg("No existen elementos con el criterio de búsqueda");
			detalles.add(d);
	    }
	
		return detalles;	
	}
	
	public void create(Detalle detalle) {

		String sql="insert into ctldoc.detalle (idevento,idtarea,idusro,fecha_ini,fecha_act) VALUES (?,?,?,?,?)";
		
		
		if (detalle.getIdTarea()<2) {
					
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt (1, detalle.getIdEvento() );
				st.setInt (2, detalle.getIdTarea() );
				st.setInt(3, detalle.getIdUsro() );
				st.setString(4, detalle.getFecha_ini() );
				st.setString(5, detalle.getFecha_act() );
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				detalle.seterrorMsg("No fué posible hacer creación del detalle del evento");
				e.printStackTrace();
				System.out.println(e);
			}
			
		}
		else {
			sql="insert into ctldoc.detalle (idevento,idtarea,idusro,idusrd,fecha_ini,fecha_act,folio,asunto,comentarios) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt (1, detalle.getIdEvento() );
				st.setInt (2, detalle.getIdTarea() );
				st.setInt(3, detalle.getIdUsro() );
				st.setInt(4, detalle.getIdUsrd() );
				st.setString(5, detalle.getFecha_ini() );
				st.setString(6, detalle.getFecha_act() );
				st.setString(7, detalle.getFolio() );
				st.setString(8, detalle.getAsunto() );
				st.setString(9, detalle.getComentarios() );
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				detalle.seterrorMsg("No fue posible hacer creación de la nueva etapa del detalle del proyecto");
				e.printStackTrace();
				System.out.println(e);
			}
		}

	}

	public void update(Detalle detalle) {
			Date objDate = new Date();
   	 		String strDateFormat = "yyyy/MM/dd hh:mm:ss";
   	 		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
   	 		String fecha=objSDF.format(objDate);

     		String sql="update ctldoc.detalle set ";
		    
		    if (detalle.getIdUsrd() !=0) {
		    	sql=sql+"idusrd="+Integer.toString(detalle.getIdUsrd())+", ";
		    }
		    if (detalle.getAsunto().isEmpty()==false) {
		    	sql=sql+"asunto='"+detalle.getAsunto()+"', ";
		    }
		    if (detalle.getFolio().isEmpty()==false) {
		    	sql=sql+"folio='"+detalle.getFolio()+"', ";
		    }
		    if (detalle.getComentarios().isEmpty()==false) {
		    	sql=sql+"comentarios='"+detalle.getComentarios()+"', ";
		    }
       		if (detalle.getFecha_fin().isEmpty()==false ) {
       			sql=sql+"fecha_fin='"+fecha+"', ";
     		}
       		sql=sql+"fecha_act='"+fecha+"' where idevento="+Integer.toString(detalle.getIdEvento());
       		System.out.println(sql);
	     	try {
	     		PreparedStatement st=con.prepareStatement(sql);
	    		st.executeUpdate();			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
	    		detalle.seterrorMsg("No fué posible hacer la actualizacón de datos, contacte al administrador del sistema");
	    		e.printStackTrace();
	    		System.out.println(e);
	    	}	
	}
 
}
