/**
 * 
 */
package br.ufpi.id3Algorithm.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.ufpi.id3Algorithm.algorithm.Id3Algorithm;
import br.ufpi.id3Algorithm.model.tree.Node;
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
					.readCSV("src/main/resources/playTennisTrainning.csv");
			Node<String> tree = id3Algorithm.buildTree(trainningSet);
			
			List<String[]> testSet = ReadAndWriteCSV
					.readCSV("src/main/resources/playTennisTest.csv");
			List<String> result = id3Algorithm.classificationTestSet(testSet, tree);
			
			System.out.println(tree.toString());
			
			System.out.println(Arrays.toString(result.toArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
