package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Util.Result;
import Util.Util;
import calculator.CountOprerator;
import calculator.HalsteadCalculator;
import filereader.ReadFile;
import recommendation.RecommendationOutputer;

public class MainScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//private MainScreen frame;
	
	private JPanel contentPane;
	
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	private JTextField textField_3;
//	private JTextField textField_4;
	
	private JLabel headerLabel;

	private JLabel basicParamLabel;	
	private JLabel param_n1_Label;
	private JLabel param_n2_Label;
	private JLabel param_N1_Label;
	private JLabel param_N2_Label;
	
	private JTextField param_n1_TextField;
	private JTextField param_n2_TextField;
	private JTextField param_N1_TextField;
	private JTextField param_N2_TextField;
	
	private JLabel chooseFileLabel;
	private JTextField chooseFileTextField;
	
	private JButton chooseButton;
	private JButton calculateButton;
	
	private JLabel metricLabel;
	private JTextArea metricTextArea;
	
	private JLabel recommendationLabel;	
	private JTextArea recommendationTextArea;
	
	public int value_n1 = 8;
	public int value_n2 = 16;
	public int value_N1 = 50;
	public int value_N2 = 70;
	
	private File chosenClass;
	public String fileName;
	public String fileDirectoryPath;
	
	private float result_N;
	private float result_n;
	private float result_H;
	private float result_V;
	private float result_D;
	private float result_L;
	private float result_E;
	private float result_T;
	private float result_B;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		headerLabel = new JLabel("HALSTEAD CALCULATOR");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		basicParamLabel = new JLabel("Basic Parameters");
		basicParamLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		param_n1_Label = new JLabel("n1 (seperate operators)");
		param_n2_Label = new JLabel("n2 (seperate operands)");
		param_N1_Label = new JLabel("N1 (total amount of operators)");
		param_N2_Label = new JLabel("N2 (total amount of operands)");
		
		param_n1_TextField = new JTextField();
		param_n1_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_n1_TextField.setEditable(false);
		param_n1_TextField.setColumns(10);
		
		param_n2_TextField = new JTextField();
		param_n2_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_n2_TextField.setEditable(false);
		param_n2_TextField.setColumns(10);
		
		param_N1_TextField = new JTextField();
		param_N1_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_N1_TextField.setEditable(false);
		param_N1_TextField.setColumns(10);
		
		param_N2_TextField = new JTextField();
		param_N2_TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		param_N2_TextField.setEditable(false);
		param_N2_TextField.setColumns(10);
		
		chooseFileLabel = new JLabel("Choose a Java file");
		chooseFileLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		chooseFileTextField = new JTextField();
		chooseFileTextField.setEditable(false);
		chooseFileTextField.setColumns(10);
		
		chooseButton = new JButton("Choose");
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		
		metricLabel = new JLabel("Metrics");
		metricLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		recommendationLabel = new JLabel("RECOMMENDATION =>");
		recommendationLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		recommendationTextArea = new JTextArea();
		recommendationTextArea.setEditable(false);
		
		metricTextArea = new JTextArea();
		metricTextArea.setEditable(false);
		
		calculateButton = new JButton("CALCULATE HALSTEAD");
		calculateButton.setEnabled(false);
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateHalstead();
				displayResult();
			}
		});
		calculateButton.setBackground(UIManager.getColor("Button.shadow"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(386)
					.addComponent(calculateButton)
					.addContainerGap(355, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(recommendationLabel)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(recommendationTextArea, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(metricTextArea, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
											.addComponent(metricLabel)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(basicParamLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
													.addComponent(chooseFileLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
												.addGap(60)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(param_n1_Label)
															.addComponent(param_N1_Label))
														.addGap(25)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(param_n1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(param_N1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGap(37)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(param_n2_Label)
															.addComponent(param_N2_Label))
														.addGap(36)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(param_N2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(param_n2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(chooseFileTextField, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
														.addGap(20)
														.addComponent(chooseButton))))))
									.addGap(72))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
					.addGap(60))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(headerLabel)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chooseFileLabel)
						.addComponent(chooseFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chooseButton))
					.addGap(21)
					.addComponent(calculateButton)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(basicParamLabel)
						.addComponent(param_n1_Label)
						.addComponent(param_n2_Label)
						.addComponent(param_n1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(param_n2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(param_N1_Label)
						.addComponent(param_N2_Label)
						.addComponent(param_N1_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(param_N2_TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(metricLabel)
					.addGap(18)
					.addComponent(metricTextArea, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(recommendationLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(recommendationTextArea, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	// Handle events
	
	private void chooseFile() {
		resetData();
		JFileChooser filePicker = new JFileChooser();
		
		int returnVal = filePicker.showOpenDialog(MainScreen.this);
		
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			resetData();
		}
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			chosenClass = filePicker.getSelectedFile();
			fileName = chosenClass.getName();
			fileDirectoryPath = chosenClass.getAbsolutePath();
			
			String extension = "";
			if (fileName.lastIndexOf(".") > 0) {
				extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			}
			if (extension.equals("java")) {
				chooseFileTextField.setText(fileDirectoryPath);
				calculateButton.setEnabled(true);
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(fileDirectoryPath));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ReadFile fileReader = new ReadFile();
				String fileString = fileReader.convertFileToString(br);
				

				Util util = new Util();
				Result result= util.Split(fileString);
				
				
				CountOprerator countOprerator = new CountOprerator(result.words);
				value_N1 = countOprerator.countTotalOperator() + result.blockCmts.size() + result.lineCmts.size();
				
				int countCmt = 0;
				if(result.blockCmts.size()>0){
					countCmt++;
				}
				if(result.lineCmts.size()>0){
					countCmt++;
				}
				int countString = 0;
				if(result.strs.size() > 0){
					countString++;
				}
				value_n1 = countOprerator.countOperator() + countCmt;							
				value_N2 = countOprerator.countTotalOperand() + result.number.size() +result.strs.size();
				value_n2 = countOprerator.countOperand()+ result.number.size()+countString;			
			} else {
				JOptionPane.showMessageDialog(MainScreen.this, "Not a java file");
				calculateButton.setEnabled(false);
				return;
			}	
		}
	}
	
	private void resetData() {
		// Reset data
		chosenClass = null;
		fileName = "";
		fileDirectoryPath = "";
		chooseFileTextField.setText("");
		
	}
	
	private void calculateHalstead() {
		HalsteadCalculator cal = new HalsteadCalculator();
		
		result_N = cal.calculate_N(value_N1, value_N2);
		result_n = cal.calculate_n(value_n1, value_n2);
		result_H = cal.calculate_H(value_n1, value_n2);
		result_V = cal.calculate_V(result_N, result_n);
		result_D = cal.calculate_D(value_n1, value_n2, value_N2);
		result_L = cal.calculate_L(result_D);
		result_E = cal.calculate_E(result_D, result_V);
		result_T = cal.calculate_T(result_E);
		result_B = cal.calculate_B_SecondWay(result_V);
	}
	
	private void displayResult() {
		param_n1_TextField.setText(String.valueOf(value_n1));
		param_n2_TextField.setText(String.valueOf(value_n2));
		param_N1_TextField.setText(String.valueOf(value_N1));
		param_N2_TextField.setText(String.valueOf(value_N2));
		
		String metricString = String.format("Program Length: N = %.0f", result_N) + " \r\n"
							+ String.format("Program Vocabulary: n = %.0f", result_n) + " \r\n"
							+ String.format("Halstead Program Length: H = %.2f", result_H) + " \r\n"
							+ String.format("Program Volume: V = %.2f", result_V) + " \r\n"
							+ String.format("Program Difficulty: D = %.2f", result_D) + " \r\n"
							+ String.format("Program Level: L = %.2f", result_L) + " \r\n"
							+ String.format("Effort: E = %.2f", result_E) + " \r\n"
							+ String.format("Time required to program: T = %.2f", result_T) + " \r\n"
							+ String.format("Number of delivered bugs: B = %.2f", result_B) + " \r\n";

		metricTextArea.setText(metricString);
		
		
		RecommendationOutputer outputer = new RecommendationOutputer(result_V, result_B, result_N, result_H);
		String result = outputer.checkClass();
		recommendationTextArea.setText(result);
		
				
	}
}
