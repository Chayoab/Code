package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


public class UsuarioRepositorio {
	Connection con = null;
	
	public UsuarioRepositorio(){
        
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
	
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql="Select * from infotec.usuario";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setColonia(rs.getString("colonia"));
				u.setInterior(rs.getString("interior"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setEmail(rs.getString("email"));
				usuarios.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuarios;	
	}
	
	public List<Usuario> getUsuariosName(String nom, String pat) {
		List<Usuario> usuarios = new ArrayList<>();
		String sql;
		
		if (nom.contains("-") && pat.contains("-")) {
			sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario";
		}
		else {
			if (nom.contains("-")) {
				sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and usuario.paterno LIKE '"+pat+"%'";
			}
			else {
				if (pat.contains("-")) {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and usuario.nombre LIKE '"+nom+"%'";
				}
				else {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and usuario.nombre LIKE '"+nom+"%' and usuario.paterno LIKE '"+pat+"%'";
				}
			}
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setColonia(rs.getString("colonia"));
				u.setInterior(rs.getString("interior"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setUsr(rs.getString("usr"));
				u.setEmail(rs.getString("email"));
				usuarios.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuarios;	
	}
	
	public List<Usuario> getUsuariosRol(int idRol, String aob) {
		List<Usuario> usuarios = new ArrayList<>();
		String sql;
		
		if ( aob.contains("a") ) {  
			sql="SELECT usuario.* FROM usuario WHERE usuario.id_usuario not in (SELECT usrxrol.id_usuario FROM usrxrol WHERE usrxrol.idRol="+Integer.toString(idRol)+")";
		}
		else {       
				sql="SELECT usuario.* FROM usuario WHERE usuario.id_usuario in (SELECT usrxrol.id_usuario FROM usrxrol WHERE usrxrol.idRol="+Integer.toString(idRol)+")";
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setColonia(rs.getString("colonia"));
				u.setInterior(rs.getString("interior"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setEmail(rs.getString("email"));
			//	u.setUsr(rs.getString("usr"));
				usuarios.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuarios;	
	}	
	
	public List<Usuario> getUsuariosActivos(String nom, String pat) {
		List<Usuario> usuarios = new ArrayList<>();
		String sql;
		
		if (nom.contains("-") && pat.contains("-")) {
			sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=0";
		}
		else {
			if (nom.contains("-")) {
				sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=0 and usuario.paterno LIKE '"+pat+"%'";
			}
			else {
				if (pat.contains("-")) {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=0 and usuario.nombre LIKE '"+nom+"%'";
				}
				else {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=0 and usuario.nombre LIKE '"+nom+"%' and usuario.paterno LIKE '"+pat+"%'";
				}
			}
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setColonia(rs.getString("colonia"));
				u.setInterior(rs.getString("interior"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setUsr(rs.getString("usr"));
				u.setEmail(rs.getString("email"));
				usuarios.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuarios;	
	}	
	
	public List<Usuario> getUsuariosBloqueados(String nom, String pat) {
		List<Usuario> usuarios = new ArrayList<>();
		String sql;
		
		
		if (nom.contains("-") && pat.contains("-")) {
			sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=1";
		}
		else {
			if (nom.contains("-")) {
				sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=1 and usuario.paterno LIKE '"+pat+"%'";
			}
			else {
				if (pat.contains("-")) {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=1 and usuario.nombre LIKE '"+nom+"%'";
				}
				else {
					sql="SELECT usuario.*, autenticar.usr FROM usuario, autenticar WHERE autenticar.id_usuario=usuario.id_usuario and autenticar.block=1 and usuario.nombre LIKE '"+nom+"%' and usuario.paterno LIKE '"+pat+"%'";
				}
			}
		}
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setColonia(rs.getString("colonia"));
				u.setInterior(rs.getString("interior"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setUsr(rs.getString("usr"));
				u.setEmail(rs.getString("email"));
				usuarios.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return usuarios;	
	}
	
	public Usuario getUsuario(int usuarioId) {
		String sql="Select * from infotec.usuario where id_usuario="+usuarioId;
		Usuario u = new Usuario();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setPaterno(rs.getString("paterno"));
				u.setMaterno(rs.getString("materno"));
				u.setCalle(rs.getString("calle"));
				u.setNumero(rs.getString("numero"));
				u.setInterior(rs.getString("interior"));
				u.setColonia(rs.getString("colonia"));
				u.setMunicipio(rs.getString("municipio"));
				u.setEstado(rs.getString("estado"));
				u.setCp(rs.getString("cp"));
				u.setTelefono(rs.getString("telefono"));
				u.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return u;
	}

	public void create(Usuario usr) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(usr.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 		
		  String sql="insert into infotec.usuario (nombre,paterno,materno,calle,numero,interior,colonia,municipio,estado,cp,telefono,email) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		  try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, usr.getNombre());
			st.setString(2, usr.getPaterno());
			st.setString(3, usr.getMaterno());
			st.setString(4, usr.getCalle());
			st.setString(5, usr.getNumero());
			st.setString(6, usr.getInterior());
			st.setString(7, usr.getColonia());
			st.setString(8, usr.getMunicipio());
			st.setString(9, usr.getEstado());
			st.setString(10, usr.getCp());
			st.setString(11, usr.getTelefono());
			st.setString(12, usr.getEmail());
			st.executeUpdate();
			
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		  }
		  sql="Select id_usuario from infotec.usuario where nombre=\""+usr.getNombre()+"\" and paterno=\""+usr.getPaterno()+"\" and materno=\""+usr.getMaterno()+"\"";
		  try {
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				if (rs.next()) {			
					usr.setIdUsuario(rs.getInt("id_usuario"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		  sql="INSERT INTO autenticar (id_usuario,usr,pwd) VALUES (?,?,\"temporal\")";
		  try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, usr.getIdUsuario());
			st.setString(2, usr.getUsr());
			st.executeUpdate();
			
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		  }
		}
		else {
			usr.setId(0);
		}		  
	}

	public void update(Usuario usr) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion(usr.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) { 		
		   String sql="update infotec.usuario set nombre=?, paterno=?, materno=?, calle=?, numero=?, interior=?, colonia=?, municipio=?, estado=?, cp=?, telefono=?, email=? where id_usuario=?";
		   try {
		    	PreparedStatement st=con.prepareStatement(sql);
		    	st.setInt(13, usr.getIdUsuario());
		    	st.setString(1, usr.getNombre());
	    		st.setString(2, usr.getPaterno());
		    	st.setString(3, usr.getMaterno());
	    		st.setString(4, usr.getCalle());
	    		st.setString(5, usr.getNumero());
	    		st.setString(6, usr.getInterior());
	    		st.setString(7, usr.getColonia());
		    	st.setString(8, usr.getMunicipio());
		    	st.setString(9, usr.getEstado());
	    		st.setString(10, usr.getCp());
	    		st.setString(11, usr.getTelefono());
	    		st.setString(12, usr.getEmail());
	    		st.executeUpdate();			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
		    	e.printStackTrace();
		    	System.out.println(e);
		    }	
		}
		else {
			usr.setId(0);
		}		   
	}

	public void delete(Usuario u) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
		ses=reposes.updateSesion( u.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) {		
		  String sql="delete from infotec.usuario where id_usuario=?";
	 	  try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, u.getIdUsuario());
			st.executeUpdate();			
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		  }	
	    }
		else {
			u.setId(0);
			u.setIdUsuario(0);
		}	 	  
	}
}