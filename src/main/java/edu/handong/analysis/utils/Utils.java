package edu.handong.analysis.utils;
 
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Utils {
	public static ArrayList<String> getLines(String dataPath, boolean removeHeader){
		//첫번째줄은 필요없어서 지우라고
		//어레이리스트 한줄이 파일의 한 줄을 읽음.
		ArrayList<String> lines = new ArrayList<String>();
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
	}

	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//아웃풋 출력을 함.
		//아웃풋을 작성할 파일이 없으면 filenotfoundexception 해야됨
	}
}