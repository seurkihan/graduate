package ver1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import common.BugReport;


public class SWING extends JFrame implements ActionListener{
	JPanel jp1, jp2, jpPre, jpIR, jpWeight, jpPdays;
	JTable t;
	private double[][] test;
	private JTextField[] jtf;
	String items[] = {"build information",
			"observed behavior",
			"expected behavior",
			};
	String stem[] = {"stem", "1", "2", "3"};
	String token[] = {"token", "1", "2", "3", "4"};
	String sword[] = {"sword", "1", "2", "3"};
	String IR[] = {"IR", "1", "2", "3"};
	
	JLabel jl[] = new JLabel[9];
	JTextArea jt[] = new JTextArea[9];
	JScrollPane jsp[] = new JScrollPane[9];
	
	String[] weightTitle = {"alpha", "beta", "gamma"};	
	JTextField alpha = new JTextField(10);
	JTextField beta = new JTextField(10);
	JTextField gamma = new JTextField(10);
	JTextField pdays = new JTextField(10);
	
	String[] etc = {"구조적 정보", "Stack trace", "comments"};
	JCheckBox[] checkbox = new JCheckBox[3];
	
//	NeuralNet NN;
	JLabel jlcom, jlScore;
	
	static int index = 0;
	static ArrayList<String> bugIDList;
	static DB db;
	static BugReport bugReport;
	public SWING() throws Exception{		
		db = new DB();
		bugIDList = db.getBugIDs();
		bugReport = db.getBugReport("45184");
	
		jlcom = new JLabel();
		jlScore = new JLabel();
//		NN = new NeuralNet();
//		NN.setNN();
		test = new double[1][9];
		setLayout(null);
		setTitle("FL TOOL");
		setSize(800,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	String[] labelTitle = {"Commit",
			"BugReport",
			"SourceFile",
			"Options"};	

	JLabel[] label = new JLabel[labelTitle.length];
	
	for(int i=0; i<labelTitle.length;i++){
		label[i] = new JLabel(labelTitle[i]);
	}
		
	jtf = new JTextField[4];
	
	for(int i=0;i<jtf.length;i++){
		jtf[i] = new JTextField();
		jtf[i].setHorizontalAlignment(JTextField.RIGHT);
		jtf[i].setText("");
	}
	
	jp1 = new JPanel();
	jp1.setLayout(null);
	jp1.setBounds(3,3,785,200);
	jp1.setLocation(3, 15);
	
	
	
	for(int i=0;i<3;i++){
		JButton jbSearch = new JButton("Search");
		jbSearch.setBounds(700, 40+i*25, 80, 30);
		jbSearch.addActionListener(this);
		add(jbSearch);
	}

	for(int i=0;i<4;i++){
		label[i].setBounds(10, 30+i*25, 120, 20);
		label[i].setOpaque(true);
		jp1.add(label[i]);
	}
	
	jtf[0].setBounds(150, 30, 550, 20);
	jtf[0].setOpaque(true);
	jtf[0].setHorizontalAlignment(JTextField.LEFT);
	jp1.add(jtf[0]);
	
	for(int i=1;i<3;i++){
		jtf[i].setBounds(150, 30+i*25, 550, 20);
		jtf[i].setOpaque(true);
		jtf[i].setHorizontalAlignment(JTextField.LEFT);
		jp1.add(jtf[i]);
	}
	
	JButton jbRun = new JButton("run");
	jbRun.setBounds(700,175,80,30);
	jbRun.addActionListener(this);
	add(jbRun);
	
//	JLabel line1 = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	line1.setBounds(5,150, 800, 15);
//	line1.setOpaque(true);
//	jp1.add(line1);
	TitledBorder tb1 = new TitledBorder(new LineBorder(Color.black),"Information");
	jp1.setBorder(tb1);
	add(jp1);

	
	//전처리 시작
	jpPre = new JPanel();
	jpPre.setLayout(null);
	jpPre.setBounds(150, 105, 270, 50);
	jpPre.setLocation(150, 105);
	
	TitledBorder tbPre = new TitledBorder(new LineBorder(Color.black),"전처리");
	jpPre.setBorder(tbPre);
	jp1.add(jpPre);
	
	
	JComboBox stemCombo = new JComboBox(stem);
	add(stemCombo);
	setLocationRelativeTo(null);
	stemCombo.setBounds(3, 8, 90, 50);
	stemCombo.setLocation(3, 8);
	jpPre.add(stemCombo);

	JComboBox tokenCombo = new JComboBox(token);
	add(tokenCombo);
	setLocationRelativeTo(null);
	tokenCombo.setBounds(90, 8, 90, 50);
	tokenCombo.setLocation(90, 8);
	jpPre.add(tokenCombo);
	
	JComboBox swordCombo = new JComboBox(sword);
	add(swordCombo);
	setLocationRelativeTo(null);
	swordCombo.setBounds(180, 8, 90, 50);
	swordCombo.setLocation(178, 8);
	jpPre.add(swordCombo);
	//전처리 끝
	
	//IR 시작
	jpIR = new JPanel();
	jpIR.setLayout(null);
	jpIR.setBounds(420, 105, 80, 50);
	jpIR.setLocation(420, 105);
	
	TitledBorder tbIR = new TitledBorder(new LineBorder(Color.black),"IR");
	jpIR.setBorder(tbIR);
	jp1.add(jpIR);
	
	JComboBox IRCombo = new JComboBox(IR);
	add(IRCombo);
	setLocationRelativeTo(null);
	IRCombo.setBounds(3, 8, 70, 50);
	IRCombo.setLocation(3, 8);
	jpIR.add(IRCombo);
	//IR 끝
	
	//가중치 시작
	jpWeight = new JPanel();
	jpWeight.setLayout(null);
	jpWeight.setBounds(500, 105, 200, 50);
	jpWeight.setLocation(500, 105);
	
	TitledBorder tbWeight = new TitledBorder(new LineBorder(Color.black),"가중치");
	jpWeight.setBorder(tbWeight);
	jp1.add(jpWeight);

	
	
	JLabel[] weightLabel = new JLabel[weightTitle.length];
	
	for(int i=0; i<weightTitle.length;i++){
		weightLabel[i] = new JLabel(weightTitle[i]);
	}
	
	weightLabel[0].setBounds(5, 20, 40, 20);
	weightLabel[0].setOpaque(false);
	jpWeight.add(weightLabel[0]);
	alpha.setBounds(37, 18, 30, 25);
	alpha.setLocation(37, 18);
	jpWeight.add(alpha);
	
	weightLabel[1].setBounds(67, 20, 40, 20);
	weightLabel[1].setOpaque(false);
	jpWeight.add(weightLabel[1]);
	beta.setBounds(92, 18, 30, 25);
	beta.setLocation(92, 18);
	jpWeight.add(beta);
	
	weightLabel[2].setBounds(124, 20, 47, 20);
	weightLabel[2].setOpaque(false);
	jpWeight.add(weightLabel[2]);
	gamma.setBounds(168, 18, 30, 25);
	gamma.setLocation(168, 18);
	jpWeight.add(gamma);
	//가중치 끝
	
	//past days 시작
	jpPdays = new JPanel();
	jpPdays.setLayout(null);
	jpPdays.setBounds(710, 105, 60, 50);
	jpPdays.setLocation(710, 105);
	
	TitledBorder tbPdays = new TitledBorder(new LineBorder(Color.black),"days");
	jpPdays.setBorder(tbPdays);
	jp1.add(jpPdays);
	
	pdays.setBounds(3, 18, 55, 25);
	pdays.setLocation(3, 18);
	jpPdays.add(pdays);
	//past days 끝

	//check box 시작
	JLabel[] etcLabel = new JLabel[etc.length];
	
	for(int i=0; i<etc.length;i++){
		etcLabel[i] = new JLabel(etc[i]);
	}

	for(int i=0; i<3; i++){
		checkbox[i] = new JCheckBox(etc[i]);
		checkbox[i].addActionListener(this);
		checkbox[i].setBounds(150+i*100, 145, 150, 60);
		checkbox[i].setLocation(150+i*100, 145);
		jp1.add(checkbox[i]);
	}
	
	
	
	//check box 끝
	
////////////////////////////////jp2	start
	jp2 = new JPanel();
	jp2.setLayout(null);
	jp2.setBounds(5,220,785,430);
	
	TitledBorder tb2 = new TitledBorder(new LineBorder(Color.black),"Result");
	jp2.setBorder(tb2);
	add(jp2);
	
	
////////////////////////////////jp2 end	
	
	setVisible(true);
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		
		if(b.getText().equals("run")){
			try {
				
				jt[1].setText(bugReport.getDescription());
				repaint();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(b.getText().equals("Search")){
			try {
							
					JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // �뵒�젆�넗由� �꽕�젙
			        chooser.setCurrentDirectory(new File("/")); 
			        chooser.setAcceptAllFileFilterUsed(true);    
			        chooser.setDialogTitle("search"); 
			        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
			        
			        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "cd11"); 
			        chooser.setFileFilter(filter); 
			        
			        int returnVal = chooser.showOpenDialog(null); 
			        
			        if(returnVal == JFileChooser.APPROVE_OPTION) {  
			            jtf[0].setText(chooser.getSelectedFile().toString()); 
                    }else if(returnVal == JFileChooser.CANCEL_OPTION){ 
			            System.out.println("cancel");
			        }		       
			        repaint();
			        				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public double[][] getTest(){
		return test;
	}


}
