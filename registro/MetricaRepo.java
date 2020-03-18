package com.infotec.registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;


public class MetricaRepo {

	Connection con = null;
	
	public MetricaRepo(){
		/*      
    	String urldb="jdbc:mariadb://localhost:3306/ctldoc";
		String usrname="root";
		String paswd="rootiptv";
	
    	String urldb="jdbc:mariadb://172.17.0.3:3306/ctldoc";
		String usrname="crlconn";
		String paswd="welcome1";
*/

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
	
	public Metrica getMetrica( int idUsuario) {
		Metrica m = new Metrica();
		String sql;
	
		sql="Select ctldoc.metricas.* from ctldoc.metricas where  metricas.idUsuario="+ Integer.toString(idUsuario);  
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				m.setIdUsuario(rs.getInt("idUsuario"));
				m.setAsignado(rs.getInt("asignado"));
				m.setAtendido(rs.getInt("atendido"));
				m.setCancelado(rs.getInt("cancelado"));
				m.setCreado(rs.getInt("creado"));
				m.setTerminado(rs.getInt("terminado"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return m;	
	}
	
	public void create(Metrica metrica) {		
			/* Paso 1 crea el evento */
			String sql="insert into ctldoc.metricas (idUsuario,creado,asignado,atendido,terminado,cancelado) values (?,?,?,?,?,?)";
			try {
				PreparedStatement st=con.prepareStatement(sql);
				st.setInt (1, metrica.getIdUsuario() );
				st.setInt (2, metrica.getCreado());
				st.setInt(3, metrica.getAsignado() );
				st.setInt(4, metrica.getAtendido() );
				st.setInt(5, metrica.getTerminado() );
				st.setInt(6, metrica.getCancelado() );
				st.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}		
	}

	public void update(Metrica metrica) {
     		String sql="";

     		sql="update ctldoc.metricas set creado=?, asignado=?, atendido=?, terminado=?, cancelado=? where idUsuario=?";
	     	try {
	     		PreparedStatement st=con.prepareStatement(sql);
	     		st.setInt(1, metrica.getCreado() );
	     		st.setInt(2, metrica.getAsignado() );
	     		st.setInt(3, metrica.getAtendido() );
	     		st.setInt(4, metrica.getTerminado() );
	     		st.setInt(5, metrica.getCancelado() );
	     		st.setInt(6, metrica.getIdUsuario() );
	    		st.executeUpdate();			
	    	} catch (SQLException e) {
			// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		System.out.println(e);
	    	}	
	}

}
