/**
 * 
 */
package br.ufpi.id3Algorithm.facade;

import java.util.List;

import br.ufpi.id3Algorithm.algorithm.Id3Algorithm;
import br.ufpi.id3Algorithm.model.tree.Node;

/**
 * @author Ronyerison
 *
 */
public class Id3Facade {
	private Id3Algorithm id3Algorithm;
	
	public Id3Facade() {
		this.id3Algorithm = new Id3Algorithm();
	}


	public Node<String> buildTree(List<String[]> trainningSet){
		return id3Algorithm.buildTree(trainningSet);
	}
	
	/**
	 * @return the id3Algorithm
	 */
	public Id3Algorithm getId3Algorithm() {
		return id3Algorithm;
	}


	/**
	 * @param id3Algorithm the id3Algorithm to set
	 */
	public void setId3Algorithm(Id3Algorithm id3Algorithm) {
		this.id3Algorithm = id3Algorithm;
	}
	
}
