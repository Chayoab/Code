package com.infotec.ses;

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class AutenticarRepositorio {
	Connection con = null;
	
	public AutenticarRepositorio(){
        
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
	
	public List<Autenticar> getAutenticados() {
		List<Autenticar> autenticados = new ArrayList<>();
		String sql="Select * from infotec.autenticar";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Autenticar a = new Autenticar();
				
				a.setIdUsuario(rs.getInt("id_usuario"));
				a.setUsr(rs.getString("usr"));
				a.setPwd(rs.getString("pwd"));
				a.setBlock(rs.getInt("block"));
				autenticados.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return autenticados;	
	}
	
	public Autenticar getAutenticado(int usuarioId) {
		String sql="Select * from infotec.autenticar where id_usuario="+usuarioId;
		Autenticar a = new Autenticar();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				a.setIdUsuario(rs.getInt("id_usuario"));
				a.setUsr(rs.getString("usr"));
				a.setPwd(rs.getString("pwd"));
				a.setBlock(rs.getInt("block"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return a;
	}
	
	public Autenticar getOut(String usr, String pwd) {
		String sql="Select * from infotec.autenticar where usr='"+usr+"' and pwd='"+pwd+"'";
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha = sdf.format(date);
        Codifica codi=new Codifica();
        String resultadoCode=codi.codifica(fecha);
        int maximo=resultadoCode.length();
        int minimo=maximo-8;
        String codetrunco=resultadoCode.substring(minimo, maximo);
        

		Autenticar a = new Autenticar();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if (rs.next()) {
				a.setIdUsuario(rs.getInt("id_usuario"));
				a.setBlock(rs.getInt("block"));
				if(a.getBlock()!=0) {
					a.setIdUsuario(0);
				}
				else {
					if ( pwd.contentEquals("temporal")==true ) { //El password es el de default
						a.setBlock(-1);
					}
					else { //El usuario es un usuario validado, se procede a generar y enviar llave de codificación.
	         			a.setMuestra(codetrunco);
						String sql2="REPLACE into sesion (id_usr,status_ses,init_date,last_event,usr_key) VALUES (?,'a',?,?,?)";
						PreparedStatement stupd=con.prepareStatement(sql2);
						stupd.setInt(1,a.getIdUsuario());
						stupd.setString(2,fecha);
						stupd.setString(3, fecha);
						stupd.setString(4, codetrunco);
						stupd.executeUpdate();
					}	
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return a;
	}

	public void create(Autenticar aut) {
		String sql="insert into infotec.autenticar values (?,?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, aut.getIdUsuario());
			st.setString(2, aut.getUsr());
			st.setString(3, aut.getPwd());
			st.setInt(4, aut.getBlock());
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void update(Autenticar aut) {
		Sesion ses=new Sesion();

	    String status_ses;
	    SesionRepositorio reposes= new SesionRepositorio();
	    String pwd;
	    int block;
	    String sql;
		ses=reposes.updateSesion(aut.getId());
		status_ses=ses.getStatus_ses();
		if (status_ses.equals("a")) {
			pwd=aut.getPwd();
			if (pwd.length()>1) {  
				 sql="update infotec.autenticar set pwd='"+pwd+"' where id_usuario=? and usr=?";
			}
			else {
				block=aut.getBlock();
				if (block==1) {
					reposes.delete(aut.getIdUsuario()); // Si 
				}
     		    sql="update infotec.autenticar set block="+Integer.toString(block)+" where id_usuario=? and usr=?";
			}
     		try {
	     		PreparedStatement st=con.prepareStatement(sql);
     			st.setInt(1, aut.getIdUsuario());
     			st.setString(2, aut.getUsr());
     			st.executeUpdate();			 
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			System.out.println(e);
    		}
		}
        else {
		    aut.setId(0);
	    }	
	}

	
	public void updatePass(Autenticar aut) {
	    int block;
	    String sql="";
	    String pwd;
	    
	    block=aut.getBlock();
	    
	    if (block < 0) {
			pwd=aut.getPwd();
			if (pwd.length()>1) {  
				 sql="update infotec.autenticar set pwd='"+pwd+"' where id_usuario=?";
			}
     		try {
	     		PreparedStatement st=con.prepareStatement(sql);
     			st.setInt(1, aut.getIdUsuario());
     			st.executeUpdate();			 
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			System.out.println(e);
    		}
		}
        else {
		    aut.setId(0);
	    }	
	}	
	
	public void delete(int id) {
		String sql="delete from infotec.autenticar where id_usuario=?";
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
}
