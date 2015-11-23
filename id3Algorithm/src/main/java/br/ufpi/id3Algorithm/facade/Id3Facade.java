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
	private Node<String> tree;
	
	public Id3Facade() {
		this.id3Algorithm = new Id3Algorithm();
	}

	/**
	 * @param trainningSet
	 * @return
	 */
	public Node<String> buildTree(List<String[]> trainningSet){
		this.tree = buildTree(trainningSet);
		return this.tree;
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

	/**
	 * @return the tree
	 */
	public Node<String> getTree() {
		return tree;
	}

	/**
	 * @param tree the tree to set
	 */
	public void setTree(Node<String> tree) {
		this.tree = tree;
	}
	
	/**
	 * @param testSet
	 * @return
	 */
	public List<String> classifyTestSet(List<String[]> testSet){
		if(this.tree != null){
			return id3Algorithm.classificationTestSet(testSet, tree);
		}
		return null;
	}
	
}
