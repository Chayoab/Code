package com.infotec.ses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsuarioFuncionRepo {
	Connection con = null;
	
	public UsuarioFuncionRepo(){
        
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
	
	public List<UsuarioFuncion> getUsuarioFuncion(String nombreFuncion) {
	List<UsuarioFuncion> usrFun = new ArrayList<>();
		String sql="SELECT  usuario.nombre, usuario.paterno, usuario.materno, usuario.id_usuario FROM " + 
				"usuario, usrxrol, rol, rolxfun, funcion " + 
				"WHERE funcion.nombreFuncion LIKE '"+nombreFuncion+"%' AND usuario.id_usuario=usrxrol.id_usuario\r\n" + 
				"AND usrxrol.idRol=rol.idRol AND rol.idRol=rolxfun.idRol AND funcion.idFuncion=rolxfun.idFuncion";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				UsuarioFuncion uf = new UsuarioFuncion();
				
				uf.setId_usuario(rs.getInt("id_usuario"));
				uf.setNombre(rs.getString("nombre"));
				uf.setPaterno(rs.getString("paterno"));
				uf.setMaterno(rs.getString("materno"));
				
				usrFun.add(uf);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}		
		return usrFun;			
	}

}
