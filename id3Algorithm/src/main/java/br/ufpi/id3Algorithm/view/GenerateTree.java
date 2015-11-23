package br.ufpi.id3Algorithm.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.apache.commons.collections15.functors.ConstantTransformer;

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
	public BasicVisualizationServer<String,String> drawTree(Node<String> tree){
		Forest<String, String> graph  = createTree(tree);
	    Layout<String, String> layout = new TreeLayout<String, String>(graph);
	    VisualizationViewer<String,String> vv;
	    RadialTreeLayout<String,String> radialLayout = new RadialTreeLayout<String,String>(graph);
        
	    radialLayout.setSize(new Dimension(800,800));
	    
	    vv =  new VisualizationViewer<String,String>(layout, new Dimension(800,650));
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
	
	 private Forest <String, String> createTree(Node<String> tree) {
		DelegateTree <String, String> graph = new DelegateTree<String, String>();
		graph.setRoot("Montante");
		graph.addChild("Baixo", "Montante", "Sim");
		graph.addChild("Alto", "Montante", "Conta");
		graph.addChild("Medio", "Montante", "Salario");
		graph.addChild("Sim", "Conta", "SimC");
		graph.addChild("Nao", "Conta", "NaoC");
		graph.addChild("BaixoS", "Salario", "NaoS");
		graph.addChild("AltoS", "Salario", "SimS");		
		return graph;
	 }
}
