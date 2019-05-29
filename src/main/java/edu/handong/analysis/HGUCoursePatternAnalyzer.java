package edu.handong.analysis;

import java.io.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.*;
import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {
	
	int hop,startyearr,endyearr,studentii1,studentii2,takenYearr1,takenYearr2,takenSemester1,takenSemester2;
	String a="", o="", i="", c="", sth1="";
	boolean h=false, dk=true, cif;
	
	int pTookTheCourse, numOfStudentInTheSem;
	public String[] bug;
	public String[] mola;
	String takenYearr="", takenSemesterr="", studentIDd="", courseNamee="", diff1="9999", diff2="9999" ;
	double sth=0.00;
	
	
	//public static Student[] sLis = new Student[253];
	public static ArrayList<Student> sLis = new ArrayList<Student>();
	//public static Course[] cLis = new Course[17908];		
	public static ArrayList<Course> cLis = new ArrayList<Course>();
	
	private HashMap<String,Student> students;

	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args) throws IOException {
		
		Options options = new Options();
		
		createOption(options); //creating options done.
		
		parseOption(options, args);
		
		if(h) {
			printHelp(options);
			System.exit(0);
			return;
		}
		
		else if(a.equals("2")) {  //this is the problem
			ArrayList<String> lines = Utils.getLines(i, true); //한줄씩 넣는거임.	
			ArrayList<String> result = new ArrayList<String>();
			for(String line: lines) {
				HashMap<Integer, Integer> hash = new HashMap<Integer,Integer>();
				if(line.contains(c)) {
					bug = line.split(",");
					takenYearr = bug[7];
					takenSemesterr = bug[8];
					studentIDd = bug[0];
					courseNamee = bug[5];
					pTookTheCourse = 0;
					numOfStudentInTheSem = 0;
					studentii1 = Integer.parseInt(studentIDd);
					takenYearr1 = Integer.parseInt(bug[7]);
					takenSemester1 = Integer.parseInt(bug[8]);
					sth=0.00;
					
					if( ((Integer.parseInt(bug[7])!=Integer.parseInt(diff1))) || ((Integer.parseInt(bug[8])!=(Integer.parseInt(diff2)))) ) {
						int count=0;
						for(String line1: lines) {
							
							mola = line1.split(",");
							studentii2 = Integer.parseInt(mola[0]);
							takenYearr2 = Integer.parseInt(mola[7]);
							takenSemester2 = Integer.parseInt(mola[8]);
							
							if( (c.equals(mola[4])) && (takenYearr.equals(mola[7])) && (takenSemesterr.equals(mola[8])) ) {
								
								pTookTheCourse++;
								//System.out.println(pTookTheCourse);
							}
							
							//이게 문제' //year sem 같을때만 hashmap에 key로 넣기//  count 하고 studentId중복 있으면 
							if( (takenYearr1==takenYearr2) && (takenSemester1==takenSemester2)) {
								
								//hashmap key는 duplicate 없으니까 key = studentii1, value = count;
								//count++;
								hash.put(studentii2, ++count);
								//numOfStudentInTheSem++;
								//System.out.println(numOfStudentInTheSem);
								diff1 = mola[7];
								diff2 = mola[8];
							}
						}
						
						numOfStudentInTheSem = hash.size();
						Double ya = new Double(hash.size());
						Double yo = new Double(pTookTheCourse);
						
						sth = (yo / ya) * 100;
						
						sth1 = String.format("%.1f", sth) + "%";
						
						//문제가 startYear endYear 고려해야됨. if( startYear(s) endYear(e) )
						//이게 문제
						if( (Integer.parseInt(bug[7]) >= startyearr) && ( Integer.parseInt(bug[7]) <= endyearr ) ) {
							
							//year semester 이전하고 다를 때만
							//bug[5]->coursename //bug[7]->takenYear, bug[8]=takenSemester
							String rst = bug[7]+","+bug[8]+","+c+","+courseNamee+","+Integer.toString(numOfStudentInTheSem)+","+Integer.toString(pTookTheCourse)+","+sth1;								
							result.add(rst);
							//System.out.println(rst);
						}
					}					
				}
			}

			
			HashMap<String,Integer> wattup = new HashMap<String,Integer>();
			ArrayList<String> yaya = new ArrayList<String>();
			
			//중복이 있으니까 hashmap에 다 key로 넣어버리면 중복은 자동삭제 얼쿠잘
			for(int q = 0 ; q < result.size() ; q++) {
				//System.out.println(result.get(q));
				wattup.put(result.get(q), q);
			}
			
			for(String key: wattup.keySet()) {
				yaya.add(key);
				//System.out.println(key);
			}
			
			/*System.out.println(yaya.size());
			
			for(int y = 0 ; y < yaya.size() ; y++) {
				System.out.println(yaya.get(y));
			}*/

			Collections.sort(yaya); //sorting the arraylist.
			
			Utils.writeAAFile(yaya, o);
			
			System.exit (0);
			
		}else if( (a.equals("1")) && ((cif))) {
			//this is generating hw5 result from startyear to endyear
			//if result a and c, then print printhelp
			
			printHelp(options);
			System.exit(0);
			return;
			
		}else if( (a.equals("1")) && (!(cif)) ) {
			
		}
		
		
		
		//System.out.println(i + " " + o + " ");
		/*
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2) {
				throw new NotEnoughArgumentException("No CLI argument Exception! Please put a file path.");
			}
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}*/

		String dataPath = i; // csv file to be analyzed
		String resultPath = o; // the file path where the results are saved.
		
		//System.out.println(args[0] +" "+ args[1]);
		
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new File(dataPath));
			
			}  catch (FileNotFoundException e) {
			System.out.println ("The file path does not exist. Please check your CLI argument!");
			System.exit (0);
			return;
		}
		
		
		ArrayList<String> lines = Utils.getLines(dataPath, true); //한줄씩 넣는거임.	
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by studentIds in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String, Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}

	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7//
	private void parseOption(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine commandLine = parser.parse(options, args);
			
			//if a is 1, then HW5 using apacheCSV
			//if a is 2, then it should figure out the rate
			
			a = commandLine.getOptionValue("a"); //analysis
			//System.out.println(a);
			
			i = commandLine.getOptionValue("i"); //input path
			
			cif = commandLine.hasOption("c");
			
			//System.out.println(a);
			o = commandLine.getOptionValue("o"); //output path

			c = commandLine.getOptionValue("c"); //course code
			
			startyearr = Integer.parseInt(commandLine.getOptionValue("s")); //start year
			
			endyearr = Integer.parseInt(commandLine.getOptionValue("e")); //end year
			
			h = commandLine.hasOption("h"); //if the arguments has h

		} catch(Exception e) {
			printHelp(options);
			System.exit(0);
		}
		
	}

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";//"\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7//	
	private void createOption(Options options) {
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg() //yes
				.argName("Input path")
				.required() //yes
				.build()
				);
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg() //yes
				.argName("Output path")
				.required() //yes
				.build()
				);		

		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg() //yes
				.argName("Analysis option")
				.required() //yes
				.build()
				);		
		
		options.addOption(Option.builder("c").longOpt("course code")
				.desc("Course code for '-a 2' option")
				.hasArg() 	//yes
				.argName("course code")
				//.required() //yes but// debatable.
				.build()
				);	
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg() 	//yes
				.argName("Start year for analysis")
				.required() //yes
				.build()
				);		
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg() 	//yes
				.argName("End year for analysis")
				.required() //yes
				.build()
				);			
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .build());		
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
		
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		
		students = new HashMap<String,Student>();
		
		/*
		for(int num=0; num < lines.size(); num++) {
			String [] ary = lines.get(num).split(",");
			System.out.println(ary[0]);
			students.put(ary[0], sLis[num] = new Student(ary[0]));
			System.out.println("hi");
		}*/
		for(int i = 0 ; i < 253 ; i++) { //for each
			//String [] ary = lines.get(i).split(",");
			//sLis[i] = new Student(Integer.toString(i+1)); //sLis는 0~252 , stuId 1~253
			sLis.add(new Student(Integer.toString(i+1)));
			students.put(Integer.toString(i+1), sLis.get(i));
		}
		
		for(int i = 0 ; i < lines.size(); i++) {
				cLis.add(new Course(lines.get(i)));
		}
		
		for(int j = 0 ; j < sLis.size() ; j ++) {
			for(int i = 0 ; i < lines.size(); i++) {
				if( Integer.parseInt(sLis.get(j).getStudentId())==Integer.parseInt(cLis.get(i).getStudentId())  ) {
					sLis.get(j).addCourse(cLis.get(i)); //putting address values into sLis
				}
			}
		}
		
		//System.out.println(sLis.get(10).getCoursesTaken().get(1).getCourseName()); //공동체리더십훈련1
		/*System.out.println(sLis.get(0).getCoursesTaken().get(65).getYearTaken() );
		
		
		for(int i = 0 ; i < sLis.size()-1 ; i ++) {
			int size = sLis.get(i).getCoursesTaken().size();
			//System.out.println(size);
			for(int j = 0 ; j < 10 ; j++) {
				if( (sLis.get(i).getCoursesTaken().get(j).getYearTaken() >= startyearr) && (sLis.get(i).getCoursesTaken().get(j).getYearTaken() <= endyearr) ) {
					
				}else {
					sLis.get(i).getCoursesTaken().get(1)
				}
			}
		}*/
		
		//System.out.println(sLis[1].getCoursesTaken().get(0));
		
		return students;
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
		
		HashMap<String, Integer> [] semesterByYearAndSemester = new HashMap[sLis.size()];
		ArrayList<String> result = new ArrayList<String>();
    	
		//for(int i = 0 ; i < sLis.length; i++) {
		for(int i = 0 ; i < sLis.size() ; i++) {
			
			//이 for loop 안에서 sLis.getCourseTaken.getSemesterCourseTaken 이런게 sYear eYear range안에 있으면 ㄱㄱ
			
			//int a = Integer.parseInt(sLis[i].getStudentId());
			//ArrayList<Course> coco = new ArrayList<Course>();
			//System.out.println(sLis[i].getStudentId());
			
			//여기서 ? sortedStudents.getCourseTaken sYear and eYear ㄱㄱ? 해야되나?
			int size12 = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().size();
			
			int bb = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().get(0).getSemesterCourseTaken();
			int aa = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().get(0).getYearTaken();
			
			int aa2 = sortedStudents.get(Integer.toString(i+1)).getCoursesTaken().get(size12-1).getYearTaken();
			
			//System.out.println(aa);
			
			//aa가 get(0) 가준이어서 그렇다. 그렇다면 ... 어떻게 해야할까...
			
			if( (aa >= startyearr) && (aa2 <= endyearr) ) {
				
				String please1 = "";
				if( i>=0 && i <=9 ) {
					please1 = "000"+ sLis.get(i).getStudentId();
				}else if( i>9 && i<100 ) {
					please1 = "00"+ sLis.get(i).getStudentId();					
				}else if( i>=100 ) {
					please1 = "0"+ sLis.get(i).getStudentId();
				}
					
				//int aa = sLis[i].getCoursesTaken().get(0).getYearTaken();
				//int bb = sLis[i].getCoursesTaken().get(0).getSemesterCourseTaken();
				
				int cnt = 0;
				//for(int j = 1 ; j < sLis[i].getCoursesTaken().size() ; j++) {
				for(int j = 1 ; j < sLis.get(i).getCoursesTaken().size() ; j++) {
					//System.out.println(sLis[i].getCoursesTaken().size());
					
					//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
					//여기 바로 아래랑 하나 아래 더  loop에서 sYear eYear 조건 추가 시키면 되려나??
					
					//if( ( sLis[i].getCoursesTaken().get(j-1).getYearTaken()!= sLis[i].getCoursesTaken().get(j).getYearTaken() ) || ( sLis[i].getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis[i].getCoursesTaken().get(j).getSemesterCourseTaken() )) {
					if( ( sLis.get(i).getCoursesTaken().get(j-1).getYearTaken()!= sLis.get(i).getCoursesTaken().get(j).getYearTaken() ) || ( sLis.get(i).getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis.get(i).getCoursesTaken().get(j).getSemesterCourseTaken() )) {
							//System.out.println(j);
							cnt++;
							String okay = Integer.toString(aa) + "-" + Integer.toString(bb);
							//sLis[i].getSemestersByYearAndSemester().put(okay, cnt);
							sLis.get(i).getSemestersByYearAndSemester().put(okay, cnt);
							
							//int Nth = sLis[i].getNumCourseInNthSemester(cnt);
							int Nth = sLis.get(i).getNumCourseInNthSemester(cnt);
							
						}else {
							continue;
						
						}
				
				}
				
				please1 = please1 +","+ Integer.toString(cnt+1);
				
				//System.out.println(sLis[0].getCoursesTaken().size());
				hop = 0;
				cnt = 0;
				//for(int j = 1 ; j < sLis[i].getCoursesTaken().size() ; j++) {
				for(int j = 1 ; j < sLis.get(i).getCoursesTaken().size() ; j++) {
					
					String please = please1;
					//System.out.println(sLis[i].getCoursesTaken().size());
					
					//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
					//여기 바로 아래랑 하나 위에  loop에서 sYear eYear 조건 추가 시키면 되려나??
					
					//if( ( sLis[i].getCoursesTaken().get(j-1).getYearTaken()!= sLis[i].getCoursesTaken().get(j).getYearTaken() ) || ( sLis[i].getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis[i].getCoursesTaken().get(j).getSemesterCourseTaken() )) {
					if( ( sLis.get(i).getCoursesTaken().get(j-1).getYearTaken()!= sLis.get(i).getCoursesTaken().get(j).getYearTaken() ) || ( sLis.get(i).getCoursesTaken().get(j-1).getSemesterCourseTaken()!= sLis.get(i).getCoursesTaken().get(j).getSemesterCourseTaken() )) {
							
							cnt++;
							String okay = Integer.toString(aa) + "-" + Integer.toString(bb);
							//sLis[i].getSemestersByYearAndSemester().put(okay, cnt);
							sLis.get(i).getSemestersByYearAndSemester().put(okay, cnt);
							//System.out.println(cnt);
							
							//int numOfClasses = sLis[i].getNumCourseInNthSemester(cnt);
							int numOfClasses = sLis.get(i).getNumCourseInNthSemester(cnt);
							
							hop = hop + numOfClasses;
							
							please = please1 +","+Integer.toString(cnt)+","+Integer.toString(numOfClasses);
							
							result.add(please);
						}
				}
				
				//if sLis.get(i).getcoursesTaken().get(j).getYearTaken() >= startyear || <= endyear 일 때만 add.
					String please = please1+","+Integer.toString(++cnt)+","+Integer.toString(sLis.get(i).getCoursesTaken().size()-hop); 
				
				//******System.out.println(please);
				result.add(please);
			/*}
			else
			{
				continue;
			}*/
				
		}
		}
		//result string sLis[i].getStudentId, cnt, semester, Nth.
		return result;
	}
}

