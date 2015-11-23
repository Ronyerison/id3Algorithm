package br.ufpi.id3Algorithm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.commons.collections15.functors.ConstantTransformer;

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
public class GUI{
	private Forest<String, String> graph = createTree();
    private Layout<String, String> layout = new TreeLayout<String, String>(graph);
    private VisualizationViewer<String,String> vv;
    private RadialTreeLayout<String,String> radialLayout;
    private JPanel jPanel;
    
    public GUI(JPanel jPanel) {
    	this.graph = createTree();
    	this.jPanel = jPanel; 
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicVisualizationServer<String,String> drawTree(){
		
	    this.layout = new TreeLayout<String, String>(graph);
	    radialLayout = new RadialTreeLayout<String,String>(graph);
        radialLayout.setSize(new Dimension(600,600));
	    
	    vv =  new VisualizationViewer<String,String>(layout, new Dimension(600,600));
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

        JPanel controls = new JPanel();
        JPanel controls2 = new JPanel();
		controls.add(createJPanelTable());
		controls2.add(createJPanelTesteSet());
        content.add(controls, BorderLayout.AFTER_LINE_ENDS);
        content.add(controls2, BorderLayout.PAGE_START);
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
	 
	 private JPanel createJPanelTable(){
		JPanel panelMain = new JPanel();
		Panel dataPanel = new Panel();
		panelMain.setBorder(new TitledBorder(null, "Testing Set", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		dataPanel.add(scrollPane);
		Object[] colunas = new Object[]{"coluna 1", "coluna 2", "coluna 3", "coluna 4"};
		Object[][] valores = new Object[50][4];
		
		for (int i = 0; i < 50; i++) {
			valores[i][0] = "linha" + i + "coluna" + 0;
			valores[i][1] = "linha" + i + "coluna" + 1;
			valores[i][2] = "linha" + i + "coluna" + 2;
			valores[i][3] = "linha" + i + "coluna" + 3;
		}
		
		JTable table;
		table = new JTable(valores, colunas);
		scrollPane.setViewportView(table);
		panelMain.add(scrollPane);
		return panelMain;
	 }
	 
	 private JPanel createJPanelTesteSet(){
		 	JPanel panelMain = new JPanel();
			panelMain.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Trainning Set", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			
			JButton btnChooseFile = new JButton("Open");
			btnChooseFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnChooseFile.setBounds(332, 31, 76, 23);
			panelMain.add(btnChooseFile);
			
			JTextField fileINTextField;
			fileINTextField = new JTextField();
			fileINTextField.setBounds(66, 32, 256, 20);
			panelMain.add(fileINTextField);
			fileINTextField.setColumns(10);
			
			JLabel inFile = new JLabel("CSV File:");
			inFile.setBounds(10, 35, 46, 14);
			panelMain.add(inFile);
			return panelMain;
		 }
}
