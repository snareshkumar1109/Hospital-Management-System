package com.hosptial.model;

public class Message {
  private int id;
  private int fromid;
  private String fromname;
  private int toid;
  private String toname;
  private String subject;
  private String body;
  private String status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getFromid() {
	return fromid;
}
public void setFromid(int i) {
	this.fromid = i;
}
public String getFromname() {
	return fromname;
}
public void setFromname(String fromname) {
	this.fromname = fromname;
}
public int getToid() {
	return toid;
}
public void setToid(int toid) {
	this.toid = toid;
}
public String getToname() {
	return toname;
}
public void setToname(String toname) {
	this.toname = toname;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
} 
}
