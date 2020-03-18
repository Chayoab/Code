package com.infotec.registro;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InformacionRepo {
	
	Connection con = null;
	
	public InformacionRepo(){

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

	
	public Informacion getInfo( int idDocumento) {
		Informacion i = new Informacion();
		String sql="Select ctldoc.informacion.* from ctldoc.informacion where  iddocumento="+ Integer.toString(idDocumento);  

	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {		
				i.setIdEvento(rs.getInt("idevento"));
				i.setIdDocumento(rs.getInt("iddocumento"));
				i.setDocumento(rs.getString("nomdocumento"));
				i.setFechaAdd(rs.getString("fechaadd"));
				i.setIdTipo(rs.getInt("idtipo"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (i.getIdEvento()==0) {
	    	i.setErrorMsg("No se encontraron elementos con el criterio de búsqueda indicado");
	    }
		return i;	
	}

	
	
	public List<Informacion> getInformacion( int idevento) {
		List<Informacion> info = new ArrayList<>();
		String sql="Select ctldoc.informacion.* from ctldoc.informacion where  idevento="+ Integer.toString(idevento); ;
	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Informacion i = new Informacion();
				
				i.setIdEvento(rs.getInt("idevento"));
				i.setIdDocumento(rs.getInt("iddocumento"));
				i.setDocumento(rs.getString("nomdocumento"));
				i.setFechaAdd(rs.getString("fechaadd"));
				i.setIdTipo(rs.getInt("idtipo"));
				info.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	    if (info.isEmpty()) {
	    	Informacion i = new Informacion();
	    	i.setErrorMsg("No se encontraron elementos con el criterio de búsqueda indicado.");
	    	info.add(i);
	    }
		return info;	
	}
	
	
	public int getmax() {
		
		int maxDoc=-1;
		String sql="select max(iddocumento) maximo from ctldoc.informacion";
		try {
	    	Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery(sql);
		    while (rs.next()) {			
		    	maxDoc = rs.getInt("maximo");
		    }
		}    
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}	
		maxDoc++;
        return maxDoc;

}
	
	public void create(Informacion info) {

		String sql="insert into ctldoc.informacion (idevento,iddocumento,nomdocumento,fechaadd,idtipo) values (?,?,?,?,?)";
		int idDoc;
		idDoc=this.getmax();
		
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt (1, info.getIdEvento() );
				st.setInt (2, idDoc );
				st.setString(3, info.getDocumento() );
				st.setString(4, info.getFechaAdd() );
				st.setInt (5, info.getIdTipo() );
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			info.setIdDocumento(idDoc);
	}

}
