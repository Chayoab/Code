package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class FuncionRepositorio {
	Connection con = null;
	
	public FuncionRepositorio(){
        
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
	
	public List<Funcion> getFunciones() {
	List<Funcion> funciones = new ArrayList<>();
		String sql="Select * from infotec.funcion";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Funcion f = new Funcion();
				
				f.setId(rs.getInt("idFuncion"));
				f.setNombre(rs.getString("nombreFuncion"));
				f.setDescripcion(rs.getString("descripcionFuncion"));
				
				funciones.add(f);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}		
		return funciones;			
	}
	
	public List<Funcion> getFuncionesName(String nombreFuncion) {
		List<Funcion> funciones = new ArrayList<>();
		String sql="Select * from infotec.funcion where nombreFuncion like '"+nombreFuncion+"%'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Funcion f = new Funcion();
				
				f.setId(rs.getInt("idFuncion"));
				f.setNombre(rs.getString("nombreFuncion"));
				f.setDescripcion(rs.getString("descripcionFuncion"));
				funciones.add(f);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return funciones;	
	}
	
	public Funcion getFuncion(int funcionId) {
		String sql="Select * from infotec.funcion where idFuncion="+funcionId;
		Funcion f = new Funcion();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				f.setId(rs.getInt("idFuncion"));
				f.setNombre(rs.getString("nombreFuncion"));
				f.setDescripcion(rs.getString("descripcionFuncion"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		return f;
	}
	
	public void create(Funcion funcion) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(funcion.getIdusr());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
			
			String sql="insert into infotec.funcion (nombreFuncion,descripcionFuncion) values (?,?)";
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setString(1, funcion.getNombre());
				st.setString(2, funcion.getDescripcion());
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}	
		}
		else {
			funcion.setId(0);
		}	
	}

	public void update(Funcion funcion) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(funcion.getIdusr());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
		
     		String sql="update infotec.funcion set nombreFuncion=?, descripcionFuncion=? where idFuncion=?";
	     	try {
	     		PreparedStatement st=con.prepareStatement(sql);
	     		st.setInt(3, funcion.getId());
	    		st.setString(1, funcion.getNombre());
	    		st.setString(2, funcion.getDescripcion());
	    		st.executeUpdate();			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		System.out.println(e);
	    	}	
		}
		else {
			funcion.setId(0);
		}	     	
	}
 
	public void delete(Funcion f) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion( f.getIdusr());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 		
			String sql="delete from infotec.funcion where idFuncion=?";
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt(1, f.getId() );
				st.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}	
		}
		else {
			f.setId(0);
			f.setIdusr(0);
		}
	}	
}
