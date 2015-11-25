package br.ufpi.id3Algorithm.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

import org.apache.commons.collections15.functors.ConstantTransformer;

import br.ufpi.id3Algorithm.model.tree.Edge;
import br.ufpi.id3Algorithm.model.tree.Node;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


/**
 * @author Vanderson Moura
 *
 */
public class GenerateTree{
    private JPanel jPanel;
    
    public GenerateTree(JPanel jPanel) {
    	this.jPanel = jPanel; 
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicVisualizationServer<Node<String>,Edge<String, String>> drawTree(Node<String> tree){
		Forest<Node<String>, Edge<String, String>> graph  = createTree(tree);
	    Layout<Node<String>, Edge<String, String>> layout = new TreeLayout<Node<String>, Edge<String, String>>(graph);
	    VisualizationViewer<Node<String>,Edge<String, String>> vv;
	    RadialTreeLayout<Node<String>,Edge<String, String>> radialLayout = new RadialTreeLayout<Node<String>,Edge<String, String>>(graph);
        
	    radialLayout.setSize(new Dimension(800,800));
	    
	    vv =  new VisualizationViewer<Node<String>,Edge<String, String>>(layout, new Dimension(800,650));
	    vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
       
        Container content = jPanel;
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);
        
        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);
        return vv;
	}
	
	 private Forest <Node<String>, Edge<String, String>> createTree(Node<String> tree) {
		DelegateTree <Node<String>, Edge<String, String>> graph = new DelegateTree<Node<String>, Edge<String, String>>();
		graph.setRoot(tree);
		Queue<Edge<String, String>> queue = new LinkedList<Edge<String, String>>();
		
		queue.clear();
		for (Edge<String, String> edge : tree.getEdges()) {
			queue.add(edge);
		}
		
		while(!queue.isEmpty()){
			Edge<String, String> edge = queue.remove();
			graph.addChild(edge, edge.getParent(), edge.getChild());
			if(edge.getChild().getEdges().size() > 0){
				for (Edge<String, String> e : edge.getChild().getEdges()){
					queue.add(e);
				}
			}
		}
			
		
//		Node<String> montante = new Node<String>("Montante");
//		Node<String> salario = new Node<String>("Salario");
//		Node<String> conta = new Node<String>("Conta");
//	
//		graph.addChild(new Edge<String, String>("Baixo", montante, new Node<String>("Sim")), montante, new Node<String>("SimM"));
//		graph.addChild(new Edge<String, String>("Alto", montante, conta), montante, conta);
//		graph.addChild(new Edge<String, String>("Medio", montante, salario), montante, salario);
//		graph.addChild(new Edge<String, String>("Sim",  conta, new Node<String>("SimC")), conta, new Node<String>("SimC"));
//		graph.addChild(new Edge<String, String>("Não",  conta, new Node<String>("NaoC")), conta, new Node<String>("NaoC"));
//		graph.addChild(new Edge<String, String>("Baixo",  salario, new Node<String>("Nao")), salario, new Node<String>("NaoS"));
//		graph.addChild(new Edge<String, String>("Alto",  salario, new Node<String>("SimC")), salario, new Node<String>("SimS"));		
//		
		return graph;
	 }
}
