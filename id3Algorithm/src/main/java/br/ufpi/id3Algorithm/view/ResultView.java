package br.ufpi.id3Algorithm.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.ArrayUtils;

import br.ufpi.id3Algorithm.algorithm.Id3Algorithm;
import br.ufpi.id3Algorithm.model.tree.Node;
import br.ufpi.id3Algorithm.util.ReadAndWriteCSV;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * @author Vanderson Moura
 *
 */
public class ResultView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	GenerateTree gui; 
	private JTextField textField;
	private JButton btnClassify;
	JTable table;

	/**
	 * Create the frame.
	 */
	public ResultView(Node<String> tree, String discriminator) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1378, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tree View", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(5, 5, 828, 694);
		contentPane.add(panel);
//		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Test Set", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(838, 5, 514, 76);
		contentPane.add(panel_2);
		
		JButton button = new JButton("Open");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		button.setBounds(352, 31, 66, 23);
		panel_2.add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(66, 32, 276, 20);
		panel_2.add(textField);
		
		JLabel label = new JLabel("CSV File:");
		label.setBounds(10, 35, 52, 14);
		panel_2.add(label);
		
		btnClassify = new JButton("Classify");
		btnClassify.setBounds(428, 31, 76, 23);
		btnClassify.setEnabled(false);
		panel_2.add(btnClassify);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Results", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(838, 92, 514, 607);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 600, 504, -598);
		panel_1.add(scrollPane);
		scrollPane.setViewportView(table);
		
		GenerateTree gui = new GenerateTree(panel);
		gui.drawTree(tree);
		
		btnClassify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Id3Algorithm id3Algorithm = new Id3Algorithm();
				List<String[]> testSet = null;
				try {
					testSet = ReadAndWriteCSV.readCSV(textField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				List<String> result = id3Algorithm.classificationTestSet(testSet, tree);
				
				testSet.set(0, ArrayUtils.add(testSet.get(0), discriminator));
				
				Object[][] values = new Object[testSet.size()-1][testSet.get(0).length];
				for (int i = 1; i < testSet.size(); i++) {
					testSet.set(i, ArrayUtils.add(testSet.get(i), result.get(i-1)));
					values[i-1] = ArrayUtils.add(testSet.get(i), result.get(i-1));
				}
				String[] columns = testSet.get(0); 
				testSet.remove(0);
				table = new JTable(values, columns);
				
			}
		});
	}
	
	/**
	 * 
	 */
	public void openFile(){
		JFileChooser c = new JFileChooser();
		c.showOpenDialog(this);
		File file = c.getSelectedFile();
		try {
			Path path = Paths.get(file.getAbsolutePath());
			String path_ = new String(path.toString());
			textField.setText(path_);
			this.btnClassify.setEnabled(true);
		} catch (Exception error){
			JOptionPane.showMessageDialog(this, "Error opening file!");
		}
	}
}
