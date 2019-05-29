package edu.handong.analysis;

import java.io.IOException;


/**@author 21600544 JeaminRhee
 * ex#1) -i hw5data.csv -o output.csv -a 1 -s 2002 -e 2008
 * ex#1 prints out the data of students who enrolled and graduated in the range between 2002 and 2008
 * 		so if there's a student who enrolled in the year of 2001 and graduated in 2007, the data of this student won't be printed.
 * 		and if there's a student who enrolled in the year of 2003 and graduated in 2009, the data of this student won't be printed too.
 * 		It will print out the data of students if and only if there's at least a student who enrolled and graduated in the range between the years. 
 * 
 * ex#2) -i hw5data.csv -o output.csv -a 2 -s 2002 -e 2010 -c ITP20003
 * ex#2 prints out 'the number of students who took the course called ITP20003' and 'the number of students who were attending to the school at the same semester'
 * 		It shows the ratio; (the number of students who took the course / the number of whole students who were attending to the school at the time)
 *	
 * ex#3) -i hw5data.csv -o output.csv -a 1 -s 1995 -e 2011
 * 		It will print out all the data of students who enrolled and graduated in between years.
 * 
 * ex#4) -i hw5data.csv -o output.csv -a 1 -s 1996 -e 2012 -c ITP20003
 * 		It will print out Help description because '-a 1' doesn't need course code input.
 * 
 * ex#5) -i hw1data.csv -o output.csv -a 1 -s 1996 -e 2012 -c ITP20003
 * 		It might print out "The file path does not exist. Please check your CLI argument!" since the correct name of the input file is 'hw5data.csv'.
 */


public class Main {
	public static void main(String[] args) throws IOException {
		HGUCoursePatternAnalyzer analyzer = new HGUCoursePatternAnalyzer();
		analyzer.run(args);
	}
}