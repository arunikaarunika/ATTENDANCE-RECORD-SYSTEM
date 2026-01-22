package com.wipro.ars.main;

import java.util.ArrayList;

import com.wipro.ars.entity.AttendanceEntry;
import com.wipro.ars.entity.Person;
import com.wipro.ars.service.AttendanceService;

public class Main {
	 public static void main(String[] args) { 
		 
	        ArrayList<Person> persons = new ArrayList<>(); 
	        persons.add(new Person("ST001", "Ravi Kumar")); 
	        persons.add(new Person("ST002", "Priya Sharma")); 
	        ArrayList<AttendanceEntry> entries = new ArrayList<>(); 
	 
	        AttendanceService service = new AttendanceService(persons, entries); 
	 
	        try { 
	            
	            service.markAttendance(new AttendanceEntry("A001", "ST001", "2025-08-10", "PRESENT")); 
	            service.markAttendance(new AttendanceEntry("A002", "ST001", "2025-08-11", "ABSENT")); 
	            service.markAttendance(new AttendanceEntry("A003", "ST002", "2025-08-10", "PRESENT"));  
	            service.updateAttendance("A002", "LEAVE"); 
	 
	            
	            System.out.println("--- Attendance History for ST001 ---"); 
	            for (AttendanceEntry e : service.getAttendanceHistory("ST001")) { 
	                System.out.println(e.getDate() + " - " + e.getStatus()); 
	            } 
	 
	            
	            System.out.println("\n--- Attendance Summary ---"); 
	            System.out.println(service.generateAttendanceSummary("ST001")); 
	 
	        } catch (Exception ex) { 
	            System.out.println(ex); 
	        } 
	    } 


}
