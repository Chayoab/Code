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

public class CpRepositorio {
	Connection con = null;

	public CpRepositorio(){
        
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
	
	
	public List<Cp> getCpInfo(String cp) {
		List<Cp> cps = new ArrayList<>();
		String sql="Select * from infotec.cp where cp like '"+cp+"%'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Cp c = new Cp();
				
				c.setColonia(rs.getString("colonia"));
				c.setMunicipio(rs.getString("mun"));
				c.setEstado(rs.getString("estado"));
				c.setCp(rs.getString("cp"));
				cps.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return cps;	
	}
	
	
	public List<Cp> getCpColonia(String colonia) {
		List<Cp> cps = new ArrayList<>();
		String sql="Select * from infotec.cp where colonia like '"+colonia+"%'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Cp c = new Cp();
				
				c.setCp(rs.getString("cp"));
				c.setColonia(rs.getString("colonia"));
				c.setMunicipio(rs.getString("mun"));
				c.setEstado(rs.getString("estado"));
				cps.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return cps;	
	}	
	
}
