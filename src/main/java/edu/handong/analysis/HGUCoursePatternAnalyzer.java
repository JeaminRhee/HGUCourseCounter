package edu.handong.analysis;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
//import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {
	int hop;
	public static Student[] sLis = new Student[253];
	public static Course[] cLis = new Course[17908];
	private HashMap<String,Student> students;

	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2) {
				throw new NotEnoughArgumentException("No CLI argument Exception! Please put a file path.");
			}
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		
		//System.out.println(args[0] +" "+ args[1]);
		
		Scanner inputStream =null;

		try {
			inputStream = new Scanner(new File(dataPath));
			
			}  catch (FileNotFoundException e) {
			System.out.println ("The file path does not exist. Please check your CLI argument!");
			System.exit (0);
		}
		
		
		ArrayList<String> lines = Utils.getLines(dataPath, true); //한줄씩 쳐 넣는거임.	
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by studentIds in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String, Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}

	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		//List<Student> stuList = new ArrayList<Student>();
		//List<Course> cList = new ArrayList<Course>();
		
		students = new HashMap<String,Student>();
		/*
		for(int num=0; num < lines.size(); num++) {
			String [] ary = lines.get(num).split(",");
			System.out.println(ary[0]);
			students.put(ary[0], sLis[num] = new Student(ary[0]));
			System.out.println("hi");
		}*/
		
		for(int i = 0 ; i < 253 ; i++) {
			//String [] ary = lines.get(i).split(",");
			sLis[i] = new Student(Integer.toString(i+1)); //sLis는 0~253 , stuId 1~253
			students.put(Integer.toString(i+1), sLis[i]);
		}
		
		for(int i = 0 ; i < lines.size(); i++) {
				cLis[i] = new Course(lines.get(i));
		}
		
		//System.out.println(cLis[0].getCourseCode());
		
		for(int j = 0 ; j < 253 ; j ++) {
			for(int i = 0 ; i < lines.size(); i++) {
				
				if(Integer.parseInt(sLis[j].getStudentId()) == Integer.parseInt(cLis[i].getStudentId())) {
					//System.out.println(cLis[i].getCourseCode());
					sLis[j].addCourse(cLis[i]);
				}
			}
		}
		
		//System.out.println(sLis[1].getCoursesTaken().get(0));
		
		return students; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		HashMap<String, Integer> [] semesterByYearAndSemester = new HashMap[253];
		ArrayList<String> result = new ArrayList<String>();
    	
		for(int i = 0 ; i < sLis.length; i++) {
			String please1="";
			if(i<=9) {
				please1 ="000" + sLis[i].getStudentId();
			}else if(i>=10 && i<=99) {
				please1 = "00" + sLis[i].getStudentId();
			}else {
				please1 ="0" + sLis[i].getStudentId();
			}
			//int a = Integer.parseInt(sLis[i].getStudentId());
				//ArrayList<Course> coco = new ArrayList<Course>();
			//System.out.println(sLis[i].getStudentId());
				
			int bb = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().get(0).getSemesterCourseTaken();
			int aa = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().get(0).getYearTaken();
			//System.out.println(aa);
			
			//int aa = sLis[i].getCoursesTaken().get(0).getYearTaken();
			//int bb = sLis[i].getCoursesTaken().get(0).getSemesterCourseTaken();
				
			int cnt = 0;
			for(int j = 1 ; j < sLis[i].getCoursesTaken().size() ; j++) {
				
				//System.out.println(sLis[i].getCoursesTaken().size());
				
				if( ( sLis[i].getCoursesTaken().get(j-1).getYearTaken()!= sLis[i].getCoursesTaken().get(j).getYearTaken() ) || ( sLis[i].getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis[i].getCoursesTaken().get(j).getSemesterCourseTaken() )) {
					cnt++;
					String okay = Integer.toString(aa) + "-" + Integer.toString(bb);
					sLis[i].getSemestersByYearAndSemester().put(okay, cnt);
					//System.out.println(cnt);
					int Nth = sLis[i].getNumCourseInNthSemester(cnt);
					//System.out.println(Nth);
					
				}else {
					continue;
				}
					
			}
			please1 = please1 +","+ Integer.toString(cnt+1);
			
			
			//System.out.println(sLis[0].getCoursesTaken().size());
			hop = 0;
			cnt = 0;
			for(int j = 1 ; j < sLis[i].getCoursesTaken().size() ; j++) {
				
				String please = please1;
				//System.out.println(sLis[i].getCoursesTaken().size());
				
				if( ( sLis[i].getCoursesTaken().get(j-1).getYearTaken()!= sLis[i].getCoursesTaken().get(j).getYearTaken() ) || ( sLis[i].getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis[i].getCoursesTaken().get(j).getSemesterCourseTaken() )) {
									
					cnt++;
					String okay = Integer.toString(aa) + "-" + Integer.toString(bb);
					sLis[i].getSemestersByYearAndSemester().put(okay, cnt);
					//System.out.println(cnt);
					
					int numOfClasses = sLis[i].getNumCourseInNthSemester(cnt);
					
					hop = hop + numOfClasses;
					
					please = please1 +","+Integer.toString(cnt)+","+Integer.toString(numOfClasses);
					
					result.add(please);
					System.out.println(please);
					
				}
			}
			
			String please = please1+","+Integer.toString(++cnt)+","+Integer.toString(sLis[i].getCoursesTaken().size()-hop); 
			System.out.println(please);
			result.add(please);
				
		}
		//result string sLis[i].getStudentId, cnt, semester, Nth.
		return result; // do not forget to return a proper variable.
	}
}