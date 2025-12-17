package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	
	public static Iterator<User> readCSVFile(String filename) {
		File csvFile = new File(System.getProperty("user.dir") +"//testData//"+ filename);
		FileReader fileReader = null;
		CSVReader csvreader = null;
		String[] line;
		User userData;
		List<User> userList = null;
		try {
			fileReader = new FileReader(csvFile);
			csvreader = new CSVReader(fileReader);
			csvreader.readNext(); // Reading the column names ---to skip the header
			
			userList = new ArrayList<>();
			
			while((line = csvreader.readNext())!= null) {
				userData = new User(line[0], line[1]);
				userList.add(userData);
			}
			
		}catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();
	}

}
