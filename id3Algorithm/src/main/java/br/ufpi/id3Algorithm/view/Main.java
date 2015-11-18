/**
 * 
 */
package br.ufpi.id3Algorithm.view;

import java.io.IOException;
import java.util.List;

import br.ufpi.id3Algorithm.algorithm.Id3Algorithm;
import br.ufpi.id3Algorithm.model.Tree;
import br.ufpi.id3Algorithm.util.ReadAndWriteCSV;

/**
 * @author WermesonReis
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Id3Algorithm id3Algorithm = new Id3Algorithm();
		try {
			List<String[]> trainningSet = ReadAndWriteCSV
					.readCSV("src/main/resources/LoanTrainning.csv");
			List<String[]> testSet = ReadAndWriteCSV
					.readCSV("src/main/resources/LoanTest.csv");
			Tree tree = id3Algorithm.run(trainningSet, testSet);
			System.out.println(tree.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
