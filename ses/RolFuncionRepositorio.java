package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class RolFuncionRepositorio {
	Connection con = null;
	
	public RolFuncionRepositorio(){
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
	
	public List<RolFuncion> getRolesFunciones() {
		List<RolFuncion> RolesFunciones = new ArrayList<>();
		String sql="Select * from infotec.rolxfun";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				RolFuncion r = new RolFuncion();
				
				r.setIdRol(rs.getInt("idRol"));
				r.setIdFuncion(rs.getInt("idFuncion"));
				r.setTipoPermiso(rs.getString("tipoPermiso"));
				RolesFunciones.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return RolesFunciones;	
	}
	
	public RolFuncion getRolFuncion(int idFuncion, int idRol) {
		String sql="Select * from infotec.rolxfun where idRol="+idRol+" and idFuncion="+idFuncion;
		RolFuncion r = new RolFuncion();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				r.setIdRol(rs.getInt("idRol"));
				r.setIdFuncion(rs.getInt("idFuncion"));
				r.setTipoPermiso(rs.getString("tipoPermiso"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return r;
	}

	public void create(RolFuncion rolFuncion) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(rolFuncion.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 
			
			String sql="insert into infotec.rolxfun values (?,?,?)";
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt(1, rolFuncion.getIdRol());
				st.setInt(2, rolFuncion.getIdFuncion());
				st.setString(3, rolFuncion.getTipoPermiso());
				st.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		}
    	else {
    		rolFuncion.setId(0);
	    }	
	}

	public void update(RolFuncion rf) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(rf.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 		
	  	  String sql="update infotec.rolxfun set tipoPermiso='"+rf.getTipoPermiso()+"' where idFuncion=? and idRol=?";
		  try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(2, rf.getIdRol());
			st.setInt(1, rf.getIdFuncion());
			st.executeUpdate();			
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		  }	
		}
	    else {
	    	rf.setId(0);
		}
	}

	public void delete(RolFuncion r) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion( r.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) {	
		   String sql="delete from infotec.rolxfun where idRol=? and idFuncion=?";
		   try {
		    	PreparedStatement st=con.prepareStatement(sql);
				st.setInt(1, r.getIdRol() );
				st.setInt(2, r.getIdFuncion() );
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
