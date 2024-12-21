package com.hosptial.model;

public class Count {
	private int id;
    private String Dname;
    private int BookCount;
    private int PendingCount;
    private int RejectCount;
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public int getPendingCount() {
		return PendingCount;
	}
	public void setPendingCount(int pendingCount) {
		PendingCount = pendingCount;
	}
	public int getBookCount() {
		return BookCount;
	}
	public void setBookCount(int bookCount) {
		BookCount = bookCount;
	}
	public int getRejectCount() {
		return RejectCount;
	}
	public void setRejectCount(int rejectCount) {
		RejectCount = rejectCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
}
