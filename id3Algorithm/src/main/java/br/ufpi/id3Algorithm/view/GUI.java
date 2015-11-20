package br.ufpi.id3Algorithm.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


public class GUI{
	//Edita um grafo gerado pela fun��o criaGrafo
	
	private Forest<String, String> graph = createTree();
    private Layout<String, String> layout = new TreeLayout<String, String>(graph);
    private VisualizationViewer<String,String> vv;
    private VisualizationServer.Paintable rings;
    private RadialTreeLayout<String,String> radialLayout;
    private JPanel jPanel;
    
    public GUI(JPanel jPanel) {
    	this.graph = createTree();
    	this.jPanel = jPanel; 
    }
    
	public BasicVisualizationServer<String,String> desenhaGrafo(){
		
	    this.layout = new TreeLayout<String, String>(graph);
	    VisualizationViewer<String,String> vv;
	   
	    radialLayout = new RadialTreeLayout<String,String>(graph);
        radialLayout.setSize(new Dimension(600,600));
	    
//      layout.setSize(new Dimension(500,500));
//	    layout.setSize(new Dimension(500,500));
	    VisualizationServer.Paintable rings;
	    
	    vv =  new VisualizationViewer<String,String>(layout, new Dimension(600,600));
	    vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
        // add a listener for ToolTips
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
       
        rings = new Rings();
        
        Container content = jPanel;
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);
        
        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);
        
        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(graphMouse.getModeListener());
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        return vv;
	}
	
	 private Forest <String, String> createTree() {
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
	 
	 class Rings implements VisualizationServer.Paintable {
	    	
	    	Collection<Double> depths;
	    	
	    	public Rings() {
	    		depths = getDepths();
	    	}
	    	
	    	private Collection<Double> getDepths() {
	    		Set<Double> depths = new HashSet<Double>();
	    		Map<String,PolarPoint> polarLocations = radialLayout.getPolarLocations();
	    		for(String v : graph.getVertices()) {
	    			PolarPoint pp = polarLocations.get(v);
	    			depths.add(pp.getRadius());
	    		}
	    		return depths;
	    	}

			public void paint(Graphics g) {
				g.setColor(Color.lightGray);
			
				Graphics2D g2d = (Graphics2D)g;
				Point2D center = radialLayout.getCenter();

				Ellipse2D ellipse = new Ellipse2D.Double();
				for(double d : depths) {
					ellipse.setFrameFromDiagonal(center.getX()-d, center.getY()-d, 
							center.getX()+d, center.getY()+d);
					Shape shape = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).transform(ellipse);
					g2d.draw(shape);
				}
			}

			public boolean useTransform() {
				return true;
			}
	    }
}
