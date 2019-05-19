package edu.handong.analysis.datamodel;
 
public class Course{
    private String courseName; 
    private String studentId;
    private String yearMonthGraduated;
    private String firstMajor;
    private String secondMajor;
    private String courseCode;
    private String courseCredit;
    private int yearTaken;
    private int semesterCourseTaken;
    
    //Constructor of the class Course
    public Course(String line)
    {
    	String[] cName = line.split(",");
    	this.yearMonthGraduated= cName[1];
    	this.firstMajor = cName[2];
    	this.secondMajor = cName[3];
    	this.courseCode = cName[4];
    	this.courseName = cName[5];
    	this.courseCredit = cName[6];
    	this.yearTaken = Integer.parseInt(cName[7]);
    	this.semesterCourseTaken = Integer.parseInt(cName[8]);
    }
    
    //getter method of the Course class
    public String getCourseName()
    {
        return this.courseName;
    }
}