/**
 * 
 */
package br.ufpi.id3Algorithm.model.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ronyerison
 *
 */
public class Node<T> {
	private Node<T> parent = null;
	private T data = null;
	private List<Node<T>> children = new ArrayList<Node<T>>();
	private List<String> edges = new ArrayList<String>();

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, Node<T> parent, String edgeValue) {
		this.data = data;
		this.parent = parent;
		this.parent.addEdge(edgeValue);
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setParent(Node<T> parent, String edgeValue) {
		parent.addChild(this, edgeValue);
		this.parent = parent;
	}

	public void addChild(T data, String edgeValue) {
		Node<T> child = new Node<T>(data);
		this.children.add(child);
		this.addEdge(edgeValue);
	}

	public void addChild(Node<T> child, String edgeValue) {
		this.children.add(child);
		this.edges.add(edgeValue);
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isRoot() {
		return (this.parent == null);
	}

	public boolean isLeaf() {
		if (this.children.size() == 0)
			return true;
		else
			return false;
	}

	public void removeParent() {
		this.parent = null;
	}

	public void addEdge(String edgeValue){
		this.edges.add(edgeValue);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [data=" + data + ",\n\t children="
				+ children + ",\n\t edges=" + edges + "]";
	}

	
}
