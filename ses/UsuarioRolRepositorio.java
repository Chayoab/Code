package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class UsuarioRolRepositorio {

	Connection con = null;
	
	public UsuarioRolRepositorio(){
            
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
	
	public List<UsuarioRol> getUsuariosRoles() {
		List<UsuarioRol> usuariosRoles = new ArrayList<>();
		String sql="Select * from infotec.usrxrol";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				UsuarioRol r = new UsuarioRol();
				
				r.setIdRol(rs.getInt("idRol"));
				r.setIdUsuario(rs.getInt("id_usuario"));
				usuariosRoles.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuariosRoles;	
	}
	
	public UsuarioRol getUsuarioRol(int idUsuario, int idRol) {
		String sql="Select * from infotec.usrxrol where idRol="+idRol+" and id_usuario="+idUsuario;
		UsuarioRol r = new UsuarioRol();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				r.setIdRol(rs.getInt("idRol"));
				r.setIdUsuario(rs.getInt("id_usuario"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return r;
	}

	public void create(UsuarioRol usuarioRol) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(usuarioRol.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
    		String sql="insert into infotec.usrxrol values (?,?)";
    		try {
			   PreparedStatement st=con.prepareStatement(sql);
			   st.setInt(2, usuarioRol.getIdRol());
			   st.setInt(1, usuarioRol.getIdUsuario());
			   st.executeUpdate();
			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
			   System.out.println(e);
		    }
		}
    	else {
    		usuarioRol.setId(0);
	    }
	}


	public void delete(UsuarioRol r) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion( r.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) {			
    		String sql="delete from infotec.usrxrol where idRol=? and id_usuario=?";
	    	try {
		    	PreparedStatement st=con.prepareStatement(sql);
				st.setInt(1, r.getIdRol() );
				st.setInt(2, r.getIdUsuario() );
		    	st.executeUpdate();			
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
		    	e.printStackTrace();
		    	System.out.println(e);
		    }	
	    }
		else {
			r.setId(0);
			r.setIdUsuario(0);
		}
	}
	
}
