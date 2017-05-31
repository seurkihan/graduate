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
import javax.swing.table.DefaultTableModel;

import common.BugReport;
import common.StructuredBugReport;


public class SWING extends JFrame implements ActionListener{
	JPanel jp1, jp2, jpPre, jpIR, jpWeight, jpPdays, jpBottom,jpBottom2;
	JTable table1, table2;
	JScrollPane sp1, sp2;
	
	private double[][] test;
	private JTextField[] jtf;
	String items[] = {"build information",
			"observed behavior"
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
	
	String[] bottomTitle = {"top1", "top5", "top10", "MAP", "MRR"};
	JTextField top1 = new JTextField(10);
	JTextField top5 = new JTextField(10);
	JTextField top10 = new JTextField(10);
	JTextField map = new JTextField(10);
	JTextField mrr = new JTextField(10);
	
	String[] bottom2Title = {"top1", "top5", "top10", "MAP", "MRR"};
	JTextField top1_2 = new JTextField(10);
	JTextField top5_2 = new JTextField(10);
	JTextField top10_2 = new JTextField(10);
	JTextField map_2 = new JTextField(10);
	JTextField mrr_2 = new JTextField(10);
	
	JLabel clickedSC;
	JTextField jtClicked = new JTextField(10);
	
	String[] bugID;

	

	static ArrayList<String> bugIDList;
	static DB db;
	static BugReport bugReport;
	public SWING() throws Exception{		
		db = new DB();
		bugIDList = db.getBugIDs();

		for(int i=0; i < bugIDList.size(); i++){
			bugID[i] = bugIDList.get(i);
			System.out.print(bugID[i]);
		}
		
		test = new double[1][9];
		setLayout(null);
		setTitle("Eval TOOL");
		setSize(800,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	String[] labelTitle = {"Commit",
			"BugReport Set",
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
	jpPdays.setBounds(700, 105, 70, 50);
	jpPdays.setLocation(700, 105);
	
	TitledBorder tbPdays = new TitledBorder(new LineBorder(Color.black),"days");
	jpPdays.setBorder(tbPdays);
	jp1.add(jpPdays);
	
	pdays.setBounds(3, 18, 63, 25);
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
	
	//bottom 시작
	//왼쪽 시작
	jpBottom = new JPanel();
	jpBottom.setLayout(null);
	jpBottom.setBounds(12, 360, 370, 60);
	jpBottom.setLocation(12, 360);
	
	TitledBorder tbBottom = new TitledBorder(new LineBorder(Color.black));
	jpBottom.setBorder(tbBottom);
	
	jp2.add(jpBottom);
	
	

	JLabel[] bottomLabel = new JLabel[bottomTitle.length];
	
	for(int i=0; i<bottomTitle.length;i++){
		bottomLabel[i] = new JLabel(bottomTitle[i]);
	}
	
	bottomLabel[0].setBounds(5, 7, 35, 20);
	bottomLabel[0].setOpaque(false);
	jpBottom.add(bottomLabel[0]);
	top1.setBounds(40, 7, 60, 20);
	top1.setLocation(40, 7);
	jpBottom.add(top1);
	
	bottomLabel[1].setBounds(130, 7, 40, 20);
	bottomLabel[1].setOpaque(false);
	jpBottom.add(bottomLabel[1]);
	top5.setBounds(170, 7, 60, 20);
	top5.setLocation(170, 7);
	jpBottom.add(top5);
	
	bottomLabel[2].setBounds(260, 7, 40, 20);
	bottomLabel[2].setOpaque(false);
	jpBottom.add(bottomLabel[2]);
	top10.setBounds(300, 7, 60, 20);
	top10.setLocation(300, 7);
	jpBottom.add(top10);
	
	bottomLabel[3].setBounds(5, 35, 40, 20);
	bottomLabel[3].setOpaque(false);
	jpBottom.add(bottomLabel[3]);
	map.setBounds(40, 35, 60, 20);
	map.setLocation(40, 35);
	jpBottom.add(map);
	
	bottomLabel[4].setBounds(130, 35, 40, 20);
	bottomLabel[4].setOpaque(false);
	jpBottom.add(bottomLabel[4]);
	mrr.setBounds(170, 35, 60, 20);
	mrr.setLocation(170, 35);
	jpBottom.add(mrr);
	//왼쪽 끝
	
	//오른쪽 시작
	jpBottom2 = new JPanel();
	jpBottom2.setLayout(null);
	jpBottom2.setBounds(400, 360, 370, 60);
	jpBottom2.setLocation(400, 360);
	
	TitledBorder tbBottom2 = new TitledBorder(new LineBorder(Color.black));
	jpBottom2.setBorder(tbBottom2);
	
	jp2.add(jpBottom);
	
	jp2.add(jpBottom2);
	
	JLabel[] bottom2Label = new JLabel[bottomTitle.length];
	
	for(int i=0; i<bottomTitle.length;i++){
		bottom2Label[i] = new JLabel(bottomTitle[i]);
	}
	
	bottom2Label[0].setBounds(5, 7, 35, 20);
	bottom2Label[0].setOpaque(false);
	jpBottom2.add(bottom2Label[0]);
	top1_2.setBounds(40, 7, 60, 20);
	top1_2.setLocation(40, 7);
	jpBottom2.add(top1_2);
	
	bottom2Label[1].setBounds(130, 7, 40, 20);
	bottom2Label[1].setOpaque(false);
	jpBottom2.add(bottom2Label[1]);
	top5_2.setBounds(170, 7, 60, 20);
	top5_2.setLocation(170, 7);
	jpBottom2.add(top5_2);
	
	bottom2Label[2].setBounds(260, 7, 40, 20);
	bottom2Label[2].setOpaque(false);
	jpBottom2.add(bottom2Label[2]);
	top10_2.setBounds(300, 7, 60, 20);
	top10_2.setLocation(300, 7);
	jpBottom2.add(top10_2);
	
	bottom2Label[3].setBounds(5, 35, 40, 20);
	bottom2Label[3].setOpaque(false);
	jpBottom2.add(bottom2Label[3]);
	map_2.setBounds(40, 35, 60, 20);
	map_2.setLocation(40, 35);
	jpBottom2.add(map_2);
	
	bottom2Label[4].setBounds(130, 35, 40, 20);
	bottom2Label[4].setOpaque(false);
	jpBottom2.add(bottom2Label[4]);
	mrr_2.setBounds(170, 35, 60, 20);
	mrr_2.setLocation(170, 35);
	jpBottom2.add(mrr_2);
	
	//오른쪽 끝	
	//bottom 끝
	

	
	sp1 = new JScrollPane();
	sp1.setBounds(12, 30, 370, 320);
    jp2.add(sp1);


    sp2 = new JScrollPane();
	sp2.setBounds(400, 60, 370, 290);
    jp2.add(sp2);

    clickedSC = new JLabel("Source Code : ");
    clickedSC.setBounds(400, 30, 90, 20);
	clickedSC.setOpaque(true);
	jp2.add(clickedSC);
	
	jtClicked.setBounds(490, 30, 280, 20);
	jtClicked.setLocation(490, 30);
	jp2.add(jtClicked);

	
	table1 = new JTable();
    table1.setModel(new DefaultTableModel(
            new Object[][] {
                   {" ", " ", " "},
            },
            new String[] {
                   "Bug Report", "Source Code", "순위"
            }
    ) {
            boolean[] columnEditables = new boolean[] {
                   false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                   return columnEditables[column];
            }
    });
   
    sp1.setViewportView(table1);
    
    
    
    table2 = new JTable();
    table2.setModel(new DefaultTableModel(
            new Object[][] {
                   {" ", " ", " "},
            },
            new String[] {
                   "순위", "Method", "점수"
            }
    ) {
            boolean[] columnEditables = new boolean[] {
                   false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                   return columnEditables[column];
            }
    });
   
    sp2.setViewportView(table2);




	
////////////////////////////////jp2 end	
	
	setVisible(true);
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		
		if(b.getText().equals("run")){
			try {
	

				Object[][] arrAdd = new Object[1][3];

				arrAdd[0][0] = "1";
                arrAdd[0][1] = "2";
                arrAdd[0][2] = "3";

               
                table1.setModel(new DefaultTableModel(
                        arrAdd,
                     new String[] {
                    		 "Bug Report", "Source Code", "순위"
                     }
             ) {
                     boolean[] columnEditables = new boolean[] {
                            false, false, false
                     };
                     public boolean isCellEditable(int row, int column) {
                            return columnEditables[column];
                     }
             });


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
