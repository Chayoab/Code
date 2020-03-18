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

public class FuncionUsuarioRepo {
	Connection con = null;
	
	public FuncionUsuarioRepo(){
        
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
	
	public FuncionUsuario getFuncionUsuario(int usuarioId) {
		List<Funcion> funciones = new ArrayList<>();
		String sql="SELECT funcion.nombreFuncion, funcion.idFuncion, rol.nombreRol, rol.idRol, usuario.nombre, usuario.paterno, usuario.materno FROM " + 
				"usuario, usrxrol, rol, rolxfun, funcion " + 
				"WHERE usuario.id_usuario= "+ Integer.toString(usuarioId) + " AND usuario.id_usuario=usrxrol.id_usuario " + 
				"AND usrxrol.idRol=rol.idRol AND rol.idRol=rolxfun.idRol AND funcion.idFuncion=rolxfun.idFuncion";
		FuncionUsuario f = new FuncionUsuario();
		boolean primero=true;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next())  {
				if (primero==true) {
			       f.setId_usuario(usuarioId);
				   f.setNombre(rs.getString("nombre"));
				   f.setPaterno(rs.getString("paterno"));
				   f.setMaterno(rs.getString("materno"));
				   f.setRol(rs.getString("nombreRol"));
				   f.setIdRol(rs.getInt("idRol"));
				} 
				Funcion fun = new Funcion();
				fun.setId(rs.getInt("idFuncion"));
				fun.setNombre(rs.getString("nombreFuncion"));
				fun.setIdusr(usuarioId);
				funciones.add(fun);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		f.setFunciones(funciones);
		return f;
	}
	

}
