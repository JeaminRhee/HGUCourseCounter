package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
 
public class Student{
    private String studentId;
    private ArrayList<Course> coursesTaken;
    private HashMap<String, Integer> semesterByYearAndSemester;
    
    public Student(String studentId) 
    {
        this.studentId = studentId;
    }
    
    public void addCourse(Course newRecord) {
    	coursesTaken.add(newRecord);
    }
    
    public HashMap<String,Integer> getSemestersByYearAndSemester(){
    	
    	return 
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