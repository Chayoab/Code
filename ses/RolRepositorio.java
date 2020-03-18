package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class RolRepositorio {
	Connection con = null;
	
	public RolRepositorio(){
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
		
		
	public List<Rol> getRoles() {
		List<Rol> roles = new ArrayList<>();
		String sql="Select * from infotec.rol";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Rol r = new Rol();
				
				r.setIdRol(rs.getInt("idRol"));
				r.setNombreRol(rs.getString("nombreRol"));
				r.setDescripcionRol(rs.getString("descripcionRol"));
				roles.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return roles;	
	}
	
	
	public List<Rol> getRolesName(String nombreRol) {
		List<Rol> roles = new ArrayList<>();
		String sql="Select * from infotec.rol where nombreRol like '"+nombreRol+"%'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Rol r = new Rol();
				
				r.setIdRol(rs.getInt("idRol"));
				r.setNombreRol(rs.getString("nombreRol"));
				r.setDescripcionRol(rs.getString("descripcionRol"));
				roles.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return roles;	
	}
	
	public List<Rol> getRolFuncion(int idFuncion, String aob) {
		List<Rol> roles = new ArrayList<>();
		String sql;
		
		if ( aob.contains("a") ) {  
			sql="SELECT rol.* FROM rol WHERE rol.idRol not in (SELECT rolxfun.idRol FROM rolxfun WHERE rolxfun.idFuncion="+Integer.toString(idFuncion)+")";
		}
		else {       
				sql="SELECT rol.* FROM rol WHERE rol.idRol in (SELECT rolxfun.idRol FROM rolxfun WHERE rolxfun.idFuncion="+Integer.toString(idFuncion)+")";
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Rol r = new Rol();
				
				r.setIdRol(rs.getInt("idRol"));
				r.setNombreRol(rs.getString("nombreRol"));
				r.setDescripcionRol(rs.getString("descripcionRol"));
			//	u.setUsr(rs.getString("usr"));
				roles.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return roles;	
	}	

	
	public Rol getRol(int idRol) {
		String sql="Select * from infotec.rol where idRol="+idRol;
		Rol r = new Rol();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				r.setIdRol(rs.getInt("idRol"));
				r.setNombreRol(rs.getString("nombreRol"));
				r.setDescripcionRol(rs.getString("descripcionRol"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return r;
	}

	public void create(Rol rol) {
		
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(rol.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
			
		    String sql="insert into infotec.rol (nombreRol, descripcionRol) values (?,?)";
	    	try {
		    	PreparedStatement st=con.prepareStatement(sql);
			    st.setString(1, rol.getNombreRol());
			    st.setString(2, rol.getDescripcionRol());
			    st.executeUpdate();
			
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		    }
		}
		else {
			rol.setId(0);
		}
	}

	public void update(Rol rol) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(rol.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 

		
    		String sql="update infotec.rol set nombreRol=?, descripcionRol=? where idRol=?";
    	    try {
    	            PreparedStatement st=con.prepareStatement(sql);
    	        	st.setInt(3, rol.getIdRol());
    	            st.setString(1, rol.getNombreRol());
    	            st.setString(2, rol.getDescripcionRol());
    	            st.executeUpdate();			
    	    } catch (SQLException e) {
			// TODO Auto-generated catch block
    	             e.printStackTrace();
    	             System.out.println(e);
    	    }        
		}
		else {
			rol.setId(0);
		}
	}

	public void delete(Rol r) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion( r.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
		
		   String sql="delete from infotec.rol where idRol=?";
	    	try {
		    	PreparedStatement st=con.prepareStatement(sql);
		    	st.setInt(1, r.getIdRol());
		    	st.executeUpdate();			
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			    e.printStackTrace();
			    System.out.println(e);
		    }	
	     }
		else {
			r.setId(0);
			r.setIdRol(0);
		}
		
	}	
}
