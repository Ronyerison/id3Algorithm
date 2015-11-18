package br.ufpi.id3Algorithm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import java.awt.Panel;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		setBounds(100, 100, 464, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMain = new JPanel();
		panelMain.setToolTipText("Dados de Entrada");
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(null);
		
		dataPanel = new Panel();
		dataPanel.setBounds(340, 68, -174, -63);
		panelMain.add(dataPanel);
		dataPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 418, 76);
		panel_2.setBorder(new TitledBorder(null, "Entrada CSV", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelMain.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnChooseFile = new JButton("Abrir");
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
		
		JLabel inFile = new JLabel("Arquivo:");
		inFile.setBounds(10, 35, 46, 14);
		panel_2.add(inFile);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entrada Manual", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 98, 289, 91);
		panelMain.add(panel_3);
		
		Choice choice = new Choice();
		choice.setBounds(102, 56, 40, 20);
		panel_3.add(choice);
		
		Label label_1 = new Label("Nº de Linhas:");
		label_1.setBounds(10, 26, 86, 22);
		panel_3.add(label_1);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(102, 26, 40, 20);
		panel_3.add(choice_1);
		
		Label label = new Label("Nº de Colunas:");
		label.setBounds(10, 54, 86, 22);
		panel_3.add(label);
		
		JButton btnExecute = new JButton("Executar ");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExecute.setBounds(309, 151, 119, 38);
		panelMain.add(btnExecute);
		
		JButton btnGenerateTable = new JButton("Gerar tabela");
		btnGenerateTable.setEnabled(false);
		btnGenerateTable.setBounds(309, 102, 119, 38);
		panelMain.add(btnGenerateTable);
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
			JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo!");
		}
	}
}
