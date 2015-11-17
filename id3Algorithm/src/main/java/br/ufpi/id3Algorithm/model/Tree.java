/**
 * 
 */
package br.ufpi.id3Algorithm.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author PedroAlmir
 */
public class Tree {
	
	/** Root node */
	private TreeNode root;
	
	/** Default constructor */
	public Tree() {
		this.root = null;
	}
	
	/**
	 * @param title
	 */
	public Tree(String title){
		this.root = null;
	}
	
	/**
	 * @param node
	 */
	public Tree(TreeNode node){
		this.root = node;
	}
	
	/**
	 * @param treeType
	 */
	public Tree(Tree treeType) {
		this.root = treeType.getRoot();
	}
	
	public Tree(String title, Tree treeType) {
		this.root = treeType.getRoot();
	}

	/**
	 * @return true if tree is empty
	 */
	public boolean isEmpty(){
		return (this.root == null);
	}
	
	/**
	 * @param id
	 * @param label
	 */
	public void addRoot(long id, String label){
		this.root = new TreeNode(-1l, id, label);
	}
	
	/**
	 * @param parent
	 * @param node
	 */
	public void addNode(long parent, TreeNode node){
		TreeNode nodeParent = findNode(parent, WalkStrategy.BreadthFirstStrategy);
		if(nodeParent != null){
			nodeParent.addChild(node);
		}
	}
	
	/**
	 * @param parent
	 * @param node
	 */
	public void addEdge(long parent, Edge edge){
		TreeNode nodeParent = findNode(parent, WalkStrategy.BreadthFirstStrategy);
		if(nodeParent != null){
			nodeParent.addEdge(edge);
		}
	}
	
	public Edge addEdge(long parent, String label, TreeNode begin, TreeNode end){
		TreeNode nodeParent = findNode(parent, WalkStrategy.BreadthFirstStrategy);
		if(nodeParent != null){
			return nodeParent.addEdge(label,begin,end);
		}
		return null;
	}
	
	/**
	 * @param parent
	 * @param id
	 * @param label
	 */
	public TreeNode addNode(long parent, long id, String label){
		TreeNode nodeParent = findNode(parent, WalkStrategy.BreadthFirstStrategy);
		if(nodeParent != null){
			return nodeParent.addChild(new TreeNode(parent, id, label));
		}
		return null;
	}
	
	/**
	 * @param id
	 * @return tree node
	 */
	public TreeNode findNode(long id, WalkStrategy strategy){
		if(strategy.equals(WalkStrategy.BreadthFirstStrategy)){
			LinkedHashMap<Long,TreeNode> map = walkBreadthFirst();
			return map.get(id);
		}else if(strategy.equals(WalkStrategy.DepthFirstStrategy)){
			LinkedHashMap<Long,TreeNode> map = walkDepthFirst();
			return map.get(id);
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public ArrayList<TreeNode> walkBreadthFirstList(){
		if(this.root != null){
			ArrayList<TreeNode> map = new ArrayList<TreeNode>();
			map.add(this.root);
			
			List<TreeNode> children = new ArrayList<TreeNode>(this.root.getChildren());
			if(children != null && !children.isEmpty()){
				Queue<TreeNode> queue = new PriorityQueue<TreeNode>();
				queue.addAll(children);
				
				while(!queue.isEmpty()){
					TreeNode treeNode = queue.poll();
					map.add(treeNode);
					
					if(treeNode.getChildren() != null){
						queue.addAll(treeNode.getChildren());
					}
				}
			}
			return map;
		}
		return null;
	}
	
	/**
	 * @return breadth first list of elements
	 */
	public LinkedHashMap<Long, TreeNode> walkBreadthFirst(){
		if(this.root != null){
			LinkedHashMap<Long, TreeNode> map = new LinkedHashMap<Long, TreeNode>();
			map.put(this.root.getId(), this.root);
			
			List<TreeNode> children = new ArrayList<TreeNode>(this.root.getChildren());
			if(children != null && !children.isEmpty()){
				Queue<TreeNode> queue = new PriorityQueue<TreeNode>();
				queue.addAll(children);
				
				while(!queue.isEmpty()){
					TreeNode treeNode = queue.poll();
					map.put(treeNode.getId(), treeNode);
					
					if(treeNode.getChildren() != null){
						queue.addAll(treeNode.getChildren());
					}
				}
			}
			return map;
		}
		return null;
	}
	
	/**
	 * @return depth first list of elements
	 */
	public LinkedHashMap<Long, TreeNode> walkDepthFirst(){
		if(this.root != null){
			LinkedHashMap<Long, TreeNode> map = new LinkedHashMap<Long, TreeNode>();
			map.put(this.root.getId(), this.root);
			
			List<TreeNode> children = new ArrayList<TreeNode>(this.root.getChildren());
			if(children != null && !children.isEmpty()){
				Stack<TreeNode> stack = new Stack<TreeNode>();
				Collections.reverse(children);
				for(TreeNode n : children){
					stack.push(n);
				}
				
				while(!stack.isEmpty()){
					TreeNode treeNode = stack.pop();
					map.put(treeNode.getId(), treeNode);
					if(treeNode.getChildren() != null){
						Collections.reverse(treeNode.getChildren());
						for(TreeNode child : treeNode.getChildren()){
							stack.push(child);
						}
					}
				}
			}
			return map;
		}
		return null;
	} 


	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public static void main(String[] args) throws Exception {
		
		Tree tree = new Tree();
		tree.addRoot(1l, "A");
		TreeNode root = tree.getRoot();
		TreeNode no2 = tree.addNode(1l, 2l, "B"); tree.addEdge(1l, "A - B", root, no2);
		TreeNode no3 = tree.addNode(1l, 3l, "C"); tree.addEdge(1l, "A - C", root, no3);
		TreeNode no4 = tree.addNode(3l, 4l, "D"); tree.addEdge(3l, "C - D", no3, no4);
		TreeNode no5 = tree.addNode(3l, 5l, "E"); tree.addEdge(3l, "C - E", no3, no5);
		TreeNode no6 = tree.addNode(5l, 6l, "F"); tree.addEdge(5l, "E - F", no5, no6);
		TreeNode no7 = tree.addNode(5l, 7l, "G"); tree.addEdge(5l, "E - G", no5, no7);
		TreeNode no8 = tree.addNode(5l, 8l, "H"); tree.addEdge(5l, "E - H", no5, no8);
		TreeNode no9 = tree.addNode(2l, 9l, "I"); tree.addEdge(2l, "B - I", no2, no9);
		
		LinkedHashMap<Long, TreeNode> breadthFirst = tree.walkBreadthFirst();
		LinkedHashMap<Long, TreeNode> depthFirst = tree.walkDepthFirst();
		for(Long key : breadthFirst.keySet()){
			System.out.print(breadthFirst.get(key).getLabel() + ", ");
		}
		System.out.println();
		for(Long key : depthFirst.keySet()){
			System.out.print(depthFirst.get(key).getLabel() + ", ");
		}
	}
}
