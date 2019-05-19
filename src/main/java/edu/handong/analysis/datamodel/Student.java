package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import edu.handong.analysis.*;
 
public class Student{
    private String studentId;
    private ArrayList<Course> coursesTaken = new ArrayList<Course>(254);
    private HashMap<String, Integer> semesterByYearAndSemester;
    
    
    
    
    public ArrayList<Course> getCoursesTaken(){
    	
    	return this.coursesTaken;
    }
    
    public Student(String studentId) 
    {
        this.studentId = studentId;
    }
    
    
    
    public void addCourse(Course newRecord) {
    	coursesTaken.add(newRecord);
    }
    
    public HashMap<String,Integer> getSemestersByYearAndSemester(){
		HashMap<String, Integer> [] semesterByYearAndSemester = new HashMap[253];

    
    	
    	
    	
    	
    	
    	
    	
    	return null;
    }
    
    public int getNumCourseInNthSemester(int semester) {
    	
    	
    	
    	return 
    }
    
    
    
    
    
    public void setStudentId(String studentId1) {
    	this.studentId = studentId1;
    }
    
    public String getStudentId() {
    	return this.studentId;
    }
}