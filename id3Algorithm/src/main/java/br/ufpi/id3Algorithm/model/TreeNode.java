/**
 * 
 */
package br.ufpi.id3Algorithm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PedroAlmir
 */
public class TreeNode implements Comparable<TreeNode>{
	private Long id;
	private Long parentId;
	private String label;
	private List<TreeNode> children;
	private List<Edge> edges;
	
	/**
	 * @param id
	 * @param label
	 */
	public TreeNode(Long parentId, Long id, String label) {
		this.id = id;
		this.label = label;
		this.parentId = parentId;
		this.children = new ArrayList<TreeNode>();
		this.edges = new ArrayList<Edge>();
	}
	
	/**
	 * @param node
	 */
	public TreeNode addChild(TreeNode node){
		if(this.children != null){
			this.children.add(node);
			return node;
		}
		return null;
	}
	
	/**
	 * @param edge
	 */
	public Edge addEdge(Edge edge){
		if(this.edges != null){
			edge.setBegin(this);
			this.edges.add(edge);
			return edge;
		}
		return null;
	}
	
	public Edge addEdge(String label, TreeNode begin, TreeNode end){
		Edge edge = new Edge(Long.valueOf(begin.getId()+""+end.getId()), label, begin, end);
		if(this.edges != null){
			this.edges.add(edge);
			return edge;
		}
		return null;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", label=" + label + ", children=" + children + "]";
	}

	public int compareTo(TreeNode o) {
		return this.getId().compareTo(o.getId());
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	
}
