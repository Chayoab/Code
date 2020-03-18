package com.infotec.ses;

public class Sesion {
	
	private int id_usr;
	private int id_session;
	private String status_ses;
	private String init_date;
	private String last_event;
	private String usr_key;
	
	public Sesion() {
		setStatus_ses("n");
	}
	public int getId_usr() {
		return id_usr;
	}
	public void setId_usr(int id_usr) {
		this.id_usr = id_usr;
	}
	public int getId_session() {
		return id_session;
	}
	public void setId_session(int id_session) {
		this.id_session = id_session;
	}
	public String getStatus_ses() {
		return status_ses;
	}
	public void setStatus_ses(String status_ses) {
		this.status_ses = status_ses;
	}
	public String getInit_date() {
		return init_date;
	}
	public void setInit_date(String init_date) {
		this.init_date = init_date;
	}
	public String getLast_event() {
		return last_event;
	}
	public void setLast_event(String last_event) {
		this.last_event = last_event;
	}
	public String getUsr_key() {
		return usr_key;
	}
	public void setUsr_key(String usr_key) {
		this.usr_key = usr_key;
	}
	
	

}
