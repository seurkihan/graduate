package ver1;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
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
	JPanel jp1, jp2;
	JTable t;
	private double[][] test;
	private JTextField[] jtf;
	String items[] = {"build information",
			"observed behavior",
			"expected behavior",
			"steps to reproduce",
			"stack trace",
			"code examples",
			"error reports",
			"test cases"};
	JLabel jl[] = new JLabel[9];
	JTextArea jt[] = new JTextArea[9];
	JScrollPane jsp[] = new JScrollPane[9];
	NeuralNet NN;
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
		NN = new NeuralNet();
		NN.setNN();
		test = new double[1][9];
		setLayout(null);
		setTitle("D-Maker");
		setSize(800,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	String[] labelTitle = {"Commit",
			"BugReport",
			"SourceFile",
			""};	

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
	jp1.setBounds(3,3,785,150);
	jp1.setLocation(3, 15);
	
	JButton jbSearch = new JButton("Search");
	jbSearch.setBounds(650,40,80,30);
	jbSearch.addActionListener(this);
	add(jbSearch);
	
	
	for(int i=0;i<4;i++){
		label[i].setBounds(3, 30+i*25, 120, 20);
		label[i].setOpaque(true);
		jp1.add(label[i]);
	}
	
	JButton pProcess = new JButton("전처리");
	JButton IR = new JButton("IR");
	JButton weight = new JButton("가중치");
	pProcess.setBounds(150,120,80,30);
	IR.setBounds(300,120,80,30);
	weight.setBounds(450,120,80,30);
	add(pProcess);
	add(IR);
	add(weight);
	
	jtf[0].setBounds(150, 30, 500, 20);
	jtf[0].setOpaque(true);
	jtf[0].setHorizontalAlignment(JTextField.LEFT);
	jp1.add(jtf[0]);
	
	for(int i=1;i<3;i++){
		jtf[i].setBounds(150, 30+i*25, 500, 20);
		jtf[i].setOpaque(true);
		jtf[i].setHorizontalAlignment(JTextField.LEFT);
		jp1.add(jtf[i]);
	}
	
	
	
//	JLabel line1 = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	line1.setBounds(5,150, 800, 15);
//	line1.setOpaque(true);
//	jp1.add(line1);
	TitledBorder tb1 = new TitledBorder(new LineBorder(Color.black),"Main");
	jp1.setBorder(tb1);
	add(jp1);

	
////////////////////////////////jp2	start
	jp2 = new JPanel();
	jp2.setLayout(null);
	jp2.setBounds(5,180,785,450);
	

	TitledBorder tb2 = new TitledBorder(new LineBorder(Color.black),"Description");
	jp2.setBorder(tb2);
	add(jp2);
	
	String data[][]={{"a","b","c"},
			{"b","c","d"},
			{"c","d","e"},
			{"d","e","f"}
	};
	String name[]={"aaa","bbb","ccc"};
	t=new JTable(data,name);
	JScrollPane sp = new JScrollPane(t);
	add(sp);
////////////////////////////////jp2 end	
	
	setVisible(true);
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Apply")){
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
							
					JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
			        chooser.setCurrentDirectory(new File("/")); // 현재 사용 디렉토리를 지정
			        chooser.setAcceptAllFileFilterUsed(true);   // Filter 모든 파일 적용 
			        chooser.setDialogTitle("찾기"); // 창의 제목
			        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
			        
			        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "cd11"); // filter 확장자 추가
			        chooser.setFileFilter(filter); // 파일 필터를 추가
			        
			        int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
			        
			        if(returnVal == JFileChooser.APPROVE_OPTION) { // 선택을 클릭 
			            jtf[0].setText(chooser.getSelectedFile().toString()); // 파일명 띄우기
                    }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
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
