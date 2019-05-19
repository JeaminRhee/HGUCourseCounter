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
		
    	semesterByYearAndSemester = new HashMap<String, Integer>();

    	return this.semesterByYearAndSemester;
    }
    
    public int getNumCourseInNthSemester(int semester) {
    	
    	int comp=0, rCount=0;
    	
    	for(int j = 1 ; j < this.getCoursesTaken().size() ; j++) {
    		rCount++;
			if( ( this.getCoursesTaken().get(j-1).getYearTaken()!= this.getCoursesTaken().get(j).getYearTaken() ) || ( this.getCoursesTaken().get(j-1).getSemesterCourseTaken()!= this.getCoursesTaken().get(j).getSemesterCourseTaken() )) {
				comp++;
				if(comp==semester) {
					return rCount;
				}
				rCount=0;
				
			}else {
				continue;
			}
		}
    	
    	return rCount;
    }
    //String okay = Integer.toString(aa) + "-" + Integer.toString(bb);
	//this.getSemestersByYearAndSemester().put(okay, semester);
	//int Nth = this.getNumCourseInNthSemester(semester);
	
    
    
    
    
    public void setStudentId(String studentId1) {
    	this.studentId = studentId1;
    }
    
    public String getStudentId() {
    	return this.studentId;
    }
}