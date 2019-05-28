package edu.handong.analysis.utils;
 
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.util.Scanner;


public class Utils {

	public static int i;
	public static ArrayList<String> getLines(String dataPath, boolean removeHeader) throws IOException{
		//첫번째줄은 필요없어서 지우라고 / removeHeader's initial value is true;
		//어레이리스트 한줄이 파일의 한 줄을 읽음.
		i=0;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(dataPath));
			
	        if(removeHeader == true) {
	        	CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	        			.withHeader("StudentID", " YearMonthGraduated", " FistMajor"," SecondMajor", " CourseCode", " CourseName", " CourseCredit", " YearTaken", " SemesterTaken")
	        			.withIgnoreHeaderCase()
	        			.withTrim()
	        			);


	        	//CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
	        	for (CSVRecord csvRecord: csvParser) {
	        		if(i==0) {
			            String t1 = csvRecord.get(0);
			            String t2 = csvRecord.get(1);
			            String t3 = csvRecord.get(2);
			            String t4 = csvRecord.get(3);
			            String t5 = csvRecord.get(4);
			            String t6 = csvRecord.get(5);
			            String t7 = csvRecord.get(6);
			            String t8 = csvRecord.get(7);
			            String t9 = csvRecord.get(8);
	        			
	        			i++;
	        		}else {
	        			// Accessing Values by Column Index
			            String StudentID = csvRecord.get(0).trim();
			            String YearMonthGraduated = csvRecord.get(1).trim();
			            String FirstMajor = csvRecord.get(2).trim();
			            String SecondMajor = csvRecord.get(3).trim();
			            String CourseCode = csvRecord.get(4).trim();
			            String CourseName = csvRecord.get(5).trim();
			            String CourseCredit = csvRecord.get(6).trim();
			            String YearTaken = csvRecord.get(7).trim();
			            String SemesterTaken = csvRecord.get(8).trim();
			            
			            String line = StudentID+","+YearMonthGraduated+","+FirstMajor+","+SecondMajor+","+CourseCode+","+CourseName+","+CourseCredit+","+YearTaken+","+SemesterTaken;
			            //System.out.println(line);
			            lines.add(line);
	        		}
			    }
				csvParser.close();
			}
				
		} catch(FileNotFoundException e) {
				System.out.println("Cannot find file " + dataPath);
		}
        return lines;   
	}
		
		
		/*
		String line;
		
		try {
			Scanner inputStream = new Scanner(new File(dataPath));
			
			//if second argument is true, skip the headline
			if (removeHeader == true)
				line = inputStream.nextLine(); 
			
			while (inputStream.hasNextLine()) {
				line = inputStream.nextLine(); 
				lines.add(line); 
			}
			inputStream.close(); 
		}//end of try
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file " + dataPath);
		}
		return lines;
	}*/

	//when a is 1 
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//아웃풋 출력을 함.
		//아웃풋을 작성할 파일이 없으면 filenotfoundexception 해야됨
		try {

			FileWriter fw = new FileWriter(targetFileName);
			Writer output = new BufferedWriter(fw);
			
			int sz = lines.size();
			output.write("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester\n");
			/*
			for(int i = 0 ; i < sz ; i ++) {
				
				
				System.out.println(lines.get(i));
				String [] bug = lines.get(i).split(",");
				
				// error 
				if((Integer.parseInt(bug[7]) >= startyear) || (Integer.parseInt(bug[7]) <= endyear)) { // print liens only in the range between startyear and endyear	
					output.write(lines.get(i).toString()+"\n");
				}
					
			}*/
			for(int i = 0 ; i < sz ; i ++) {
				output.write(lines.get(i).toString()+"\n");
			}
			output.flush();
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file " + targetFileName);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//when a is 2 
	public static void writeAAFile(ArrayList<String> lines, String targetFileName) {
		//-a 2 -c something 아웃풋 출력을 함.
		//아웃풋을 작성할 파일이 없으면 filenotfoundexception 해야됨
		try {

			FileWriter fw = new FileWriter(targetFileName);
			Writer output = new BufferedWriter(fw);
			
			int sz = lines.size();
			output.write("Year, Semester, CourseCode, CourseName, TotalStudents, StudentsTaken, Rate\n");
			for(int i = 0 ; i < sz ; i ++) {
				output.write(lines.get(i).toString()+"\n");
			}
			output.flush();
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file " + targetFileName);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
}