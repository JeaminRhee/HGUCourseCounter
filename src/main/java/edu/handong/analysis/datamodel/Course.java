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
        this.courseName = cName[4];
    }
    
    //getter method of the Course class
    public String getCourseName()
    {
        return this.courseName;
    }
}