/**
 * 
 */
package br.ufpi.id3Algorithm.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author WermesonReis
 *
 */
public class ReadAndWriteCSV {
	
	public static List<String[]> readCSV(String pathName) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(pathName), ';');
		List<String[]> lines = reader.readAll();
		reader.close();
		return lines;
	}
	
	public static void writeCSV(String pathName, List<String[]> content) throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(pathName), ';');
		writer.writeAll(content);
		writer.close();
	}
}	
