package br.ufpi.id3Algorithm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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

/**
 * @author Vanderson Moura
 *
 */
public class InitialView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Panel dataPanel;
	private JTextField fileINTextField;
	GUI gui; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialView frame = new InitialView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InitialView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMain = new JPanel();
		panelMain.setToolTipText("Trainning Data");
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(null);
		
		dataPanel = new Panel();
		dataPanel.setBounds(340, 68, -174, -63);
		panelMain.add(dataPanel);
		dataPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 418, 76);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Trainning Set", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnChooseFile = new JButton("Open");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		btnChooseFile.setBounds(332, 31, 76, 23);
		panel_2.add(btnChooseFile);
		
		fileINTextField = new JTextField();
		fileINTextField.setBounds(66, 32, 256, 20);
		panel_2.add(fileINTextField);
		fileINTextField.setColumns(10);
		
		JLabel inFile = new JLabel("CSV File:");
		inFile.setBounds(10, 35, 46, 14);
		panel_2.add(inFile);
		
		JButton btnExecute = new JButton("Generate Tree");
		btnExecute.setBounds(163, 98, 119, 38);
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelMain.add(btnExecute);
		gui = new GUI(contentPane);
		gui.drawTree();
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
			fileINTextField.setText(path_);
		} catch (Exception error){
			JOptionPane.showMessageDialog(this, "Error opening file!");
		}
	}
}
