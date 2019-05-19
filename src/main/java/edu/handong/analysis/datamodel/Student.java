package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
 
public class Student{
    private String studentId;
    private ArrayList<Course> coursesTaken;
    private HashMap<String, Integer> semesterByYearAndSemester;
    
    //Constructor of the Student class
    public Student(String studentId) 
    {
        this.studentId = studentId;
    }
    
    public void addCourse(Course newRecord) {
    	
    }
    /*
    public HashMap<String,Integer> getSemestersByYearAndSemester(){
    	
    	return 
    }
    
    public int getNumCourseInNthSemester(int semester) {
    	
    	return
    }*/
    
}