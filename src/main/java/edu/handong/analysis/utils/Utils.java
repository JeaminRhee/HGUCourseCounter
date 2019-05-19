package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Utils {
	public static ArrayList<String> getLines(String dataPath, boolean removeHeader){
		//첫번째줄은 필요없어서 지우라고
		//어레이리스트 한줄이 파일의 한 줄을 읽음.
		Scanner inputStream = null;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			inputStream = new Scanner(new File(dataPath));
		} catch(FileNotFoundException e) {
			System.exit(0);
		}
		
		while(inputStream.hasNextLine()) {
			if(removeHeader==true) {
				removeHeader = false;
				continue;
			}else {
				String line = inputStream.nextLine();
				System.out.println(line);
				lines.add(line);
			}
		}
		inputStream.close();
		
		return lines;
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//아웃풋 출력을 함.
		//아웃풋을 작성할 파일이 없으면 filenotfoundexception 해야됨
	}
}