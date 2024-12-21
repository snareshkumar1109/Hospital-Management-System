package com.hosptial.model;
import java.sql.*;
public class Appointment {
	private int id;
    private String name;
    private String email;
    private String Dname;
    private int DId;
    private Date Date;
    private Time Time;
    private String status;
    public void setName(String name) {
   	 this.name=name;
    }
    public void setId(int id) {
   	 this.id=id;
    }
    public void setEmail(String email) {
   	 this.email=email;
    }
    public void setDate(Date date) {
   	 this.Date=date;
    }
    public String getName() {
   	 return this.name;
    }
    public int getID() {
   	 return this.id;
    }
    public Date getDate() {
   	 return this.Date;
    }
    public String getEmail() {
   	 return this.email;
    }
	
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDId() {
		return DId;
	}
	public void setDId(int dId) {
		DId = dId;
	}
	public Time getTime() {
		return Time;
	}
	public void setTime(Time time) {
		Time = time;
	}
}
