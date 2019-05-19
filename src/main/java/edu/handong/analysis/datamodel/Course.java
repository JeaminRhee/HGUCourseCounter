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
    	this.studentId = cName[0];
    	this.yearMonthGraduated= cName[1];
    	this.firstMajor = cName[2];
    	this.secondMajor = cName[3];
    	this.courseCode = cName[4];
    	this.courseName = cName[5];
    	this.courseCredit = cName[6];
    	this.yearTaken = Integer.parseInt(cName[7].trim());
    	this.semesterCourseTaken = Integer.parseInt(cName[8].trim());
    }
    
    //getter method of the Course class
    public String getCourseName()
    {
        return this.courseName;
    }
    public String getStudentId()
    {
        return this.studentId;
    }
    
    public String getYearMonthGraduated()
    {
        return this.yearMonthGraduated;
    }
    
    public String getFirstMajor()
    {
        return this.firstMajor;
    }
    
    public String getSecondMajor()
    {
        return this.secondMajor;
    }
    
    public String getCourseCode()
    {
        return this.courseCode;
    }
    
    public String getCourseCredit()
    {
        return this.courseCredit;
    }
    
    public int getYearTaken()
    {
        return this.yearTaken;
    }
    
    public int getSemesterCourseTaken()
    {
        return this.semesterCourseTaken;
    }
}