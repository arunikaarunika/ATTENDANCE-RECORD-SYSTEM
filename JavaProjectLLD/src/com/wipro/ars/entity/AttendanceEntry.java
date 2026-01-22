package com.wipro.ars.entity;

public class AttendanceEntry {
	private String entryId;
	private String personId;
	private String date;
	private String status;
	public AttendanceEntry(String entryId,String personId,String date,String status) {
		this.entryId=entryId;
		this.personId=personId;
		this.date=date;
		this.status=status;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
