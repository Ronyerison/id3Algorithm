/**
 * 
 */
package br.ufpi.id3Algorithm.model;

/**
 * @author Ronyerison
 *
 */
public class Edge {
	private Long id;
	private String label;
	private TreeNode begin;
	private TreeNode end;
	
	/**
	 * @param id
	 * @param label
	 * @param begin
	 * @param end
	 */
	public Edge(Long id, String label, TreeNode begin, TreeNode end) {
		this.id = id;
		this.label = label;
		this.begin = begin;
		this.end = end;
		end.setParentId(begin.getId());
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
	 * @return the begin
	 */
	public TreeNode getBegin() {
		return begin;
	}

	/**
	 * @param begin the begin to set
	 */
	public void setBegin(TreeNode begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public TreeNode getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(TreeNode end) {
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [id=" + id + ", label=" + label + ", begin=" + begin
				+ ", end=" + end + "]";
	}
	
	

}
