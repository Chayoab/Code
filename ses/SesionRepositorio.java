package com.infotec.ses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SesionRepositorio {
	
	Connection con = null;
	
	public SesionRepositorio(){
        
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
	
	public Sesion getSesionado(int usuarioId) {
		String sql="Select * from infotec.sesion where id_usr="+usuarioId;
		Sesion s = new Sesion();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				s.setId_session(rs.getInt("id_sesion"));
				s.setId_usr(rs.getInt("id_usr")); 
				s.setStatus_ses(rs.getString("status_ses"));
				s.setInit_date(rs.getString("init_date"));
				s.setLast_event(rs.getString("last_event"));
				s.setUsr_key(rs.getString("usr_key"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return s;
	}
	
	
	public void delete(int id) {
		String sql="delete from infotec.sesion where id_usr=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}	
	}
	
	public Sesion getVal(String idUsr, String usrKey) {
		String sql="Select * from infotec.sesion where id_usr="+idUsr+" and usr_key like \""+usrKey+"%\"";
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha = sdf.format(date);
		String id_sesion;
		Sesion s = new Sesion();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				s.setStatus_ses(rs.getString("status_ses"));
				id_sesion=rs.getString("id_sesion");
		    	if (s.getStatus_ses().compareTo("a")==0) { //El usuario es un usuario activo, se procede a actualizar su actividad. 
					String sql2="UPDATE sesion SET last_event=\""+fecha+"\" where id_usr="+idUsr+" and id_sesion="+id_sesion;
					PreparedStatement stupd=con.prepareStatement(sql2);
					stupd.executeUpdate();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return s;
	}
	
	public Sesion updateSesion(int idUsr){
	    String sql="Select * from infotec.sesion where id_usr="+idUsr;
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha = sdf.format(date);
		String id_sesion;
		Sesion s = new Sesion();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				s.setStatus_ses(rs.getString("status_ses"));
				s.setUsr_key(rs.getString("usr_key"));
				id_sesion=rs.getString("id_sesion");
		    	if (s.getStatus_ses().compareTo("a")==0) { //El usuario es un usuario activo, se procede a actualizar su actividad. 
					String sql2="UPDATE sesion SET last_event=\""+fecha+"\" where id_usr="+idUsr+" and id_sesion="+id_sesion;
					PreparedStatement stupd=con.prepareStatement(sql2);
					stupd.executeUpdate();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return s;
	}		

}
