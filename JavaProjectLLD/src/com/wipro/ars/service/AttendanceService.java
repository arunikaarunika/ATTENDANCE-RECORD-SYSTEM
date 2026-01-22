package com.wipro.ars.service;

import java.util.ArrayList;

import com.wipro.ars.entity.AttendanceEntry;
import com.wipro.ars.entity.Person;
import com.wipro.ars.util.AttendanceNotFoundException;
import com.wipro.ars.util.InvalidAttendanceException;
import com.wipro.ars.util.PersonNotFoundException;

public class AttendanceService {
	 ArrayList<Person> persons;
	    ArrayList<AttendanceEntry> entries;
	    public AttendanceService(ArrayList<Person> persons,
	                             ArrayList<AttendanceEntry> entries) {
	        this.persons = persons;
	        this.entries = entries;
	    }
	    public void addPerson(Person p) {
	        persons.add(p);
	    }

	    
	    public Person findPerson(String personId)
	            throws PersonNotFoundException {

	        for (int i = 0; i < persons.size(); i++) {
	            if (persons.get(i).getPersonId().equals(personId)) {
	                return persons.get(i);
	            }
	        }
	        throw new PersonNotFoundException();
	    }

	   
	    public void markAttendance(AttendanceEntry entry)
	            throws PersonNotFoundException, InvalidAttendanceException {

	        findPerson(entry.getPersonId());

	        if (entry.getDate() == null) {
	            throw new InvalidAttendanceException();
	        }

	        String status = entry.getStatus();
	        if (!(status.equals("PRESENT")
	           || status.equals("ABSENT")
	           || status.equals("LEAVE"))) {
	            throw new InvalidAttendanceException();
	        }

	        entries.add(entry);
	    }

	    
	    public AttendanceEntry findAttendance(String entryId)
	            throws AttendanceNotFoundException {

	        for (int i = 0; i < entries.size(); i++) {
	            if (entries.get(i).getEntryId().equals(entryId)) {
	                return entries.get(i);
	            }
	        }
	        throw new AttendanceNotFoundException();
	    }

	    
	    public void updateAttendance(String entryId, String newStatus)
	            throws AttendanceNotFoundException, InvalidAttendanceException {

	        if (!(newStatus.equals("PRESENT")
	           || newStatus.equals("ABSENT")
	           || newStatus.equals("LEAVE"))) {
	            throw new InvalidAttendanceException();
	        }

	        AttendanceEntry entry = findAttendance(entryId);
	        entry.setStatus(newStatus);
	    }

	    
	    public ArrayList<AttendanceEntry> getAttendanceHistory(String personId)
	            throws PersonNotFoundException {

	        findPerson(personId);
	        ArrayList<AttendanceEntry> history = new ArrayList<>();

	        for (int i = 0; i < entries.size(); i++) {
	            if (entries.get(i).getPersonId().equals(personId)) {
	                history.add(entries.get(i));
	            }
	        }
	        return history;
	    }

	    
	    public double calculateAttendancePercentage(String personId)
	            throws PersonNotFoundException {

	        ArrayList<AttendanceEntry> history =
	                getAttendanceHistory(personId);

	        if (history.size() == 0) {
	            return 0;
	        }

	        int presentCount = 0;
	        for (int i = 0; i < history.size(); i++) {
	            if (history.get(i).getStatus().equals("PRESENT")) {
	                presentCount++;
	            }
	        }

	        return (presentCount * 100.0) / history.size();
	    }

	    
	    public String generateAttendanceSummary(String personId) {

	        try {
	            Person p = findPerson(personId);
	            ArrayList<AttendanceEntry> history =
	                    getAttendanceHistory(personId);

	            int present = 0, absent = 0, leave = 0;

	            for (int i = 0; i < history.size(); i++) {
	                String status = history.get(i).getStatus();
	                if (status.equals("PRESENT")) present++;
	                else if (status.equals("ABSENT")) absent++;
	                else leave++;
	            }

	            return "Name: " + p.getName()
	                 + "\nTotal Days: " + history.size()
	                 + "\nPresent: " + present
	                 + "\nAbsent: " + absent
	                 + "\nLeave: " + leave
	                 + "\nAttendance Percentage: "
	                 + calculateAttendancePercentage(personId);

	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	}