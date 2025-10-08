

import java.awt.Color;



import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener{
	
	JPanel panel1,panel2,resultspanel;
	//-----PANEL1 COMPONENTS------------------------------
	
	JButton button,submit,homepage;
	JLabel label1,label2,label3,label4,label5;
	JTextField textfield1,textfield2,textfield3,textfield4;
	ImageIcon image;
	
	//-----PANEL2 COMPONENTS------------------------------
	
	JComboBox combobox1,combobox2,combobox3,combobox4,combobox5,combobox6,combobox7,combobox8,combobox9,combobox10;
    JLabel Cafe,Sea,Museums,Reastaurant,Stadium,Bars,Park,Shopping_Center,Zoo,Galleries;
    JButton okbutton;
    
    //----PANEL3 COMPONENTS-------------------------------
    
    JLabel name1,name2,traveller1,traveller2,timestamp1,timestamp2,visit1,visit2,city1,city2,city3,city4,city5,city6;
	
    String substrings[] ;
	
	Frame(){
		panel1 = new JPanel();
		image = new ImageIcon("reallogo.png");
		
		panel2 = new JPanel();
   	    String[] termschoices = {"0","1","2","3","4","5","6","7","8","9"};
		
   	    resultspanel=new JPanel();
   	    
   	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 1000);
		this.setLayout(null);
		
		this.add(panel1);
		this.add(panel2);
		this.add(resultspanel);
		
		panel1.setLayout(null);
		panel1.setBackground(Color.white);
		panel1.setBounds(100, 100, 700, 800);
		panel1.setVisible(true);
		
		panel2.setLayout(null);
		panel2.setBackground(Color.white);
		panel2.setBounds(100, 100, 700, 800);
		panel2.setVisible(false);
		
   	    resultspanel.setLayout(null);
   	    resultspanel.setBackground(Color.white);
   	    resultspanel.setBounds(100, 100, 700, 700);
   	    resultspanel.setVisible(false);
		
		
		//-----PANEL1 COMPONENTS------------------------------
		
		label4 = new JLabel();
		label4.setIcon(image);
		label4.setBounds(275, -72, 300, 300);
		panel1.add(label4);
		
		label1 = new JLabel("Name");
		label1.setBounds(150, 200, 100, 50);
		label1.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel1.add(label1);
		
		textfield1 = new JTextField(20);
		textfield1.setBounds(200, 200, 300, 30);
	    textfield1.addActionListener(this);
		panel1.add(textfield1);
		
		
		
		label2 = new JLabel("Age");
		label2.setBounds(150, 300, 100, 50);
		label2.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel1.add(label2);
		
		textfield2 = new JTextField(20);
		textfield2.setBounds(200, 300, 300, 30);
		textfield2.addActionListener(this);
		
		textfield2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if((input < '0' || input > '9') && input!='\b') {
					e.consume();
					System.out.println("invalid character");
				}
				
			}
		});
		
		panel1.add(textfield2);
		
		
		label3 = new JLabel("City");
		label3.setBounds(150, 400, 100, 50);
		label3.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel1.add(label3);
		
		
		textfield3 = new JTextField(20);
		textfield3.setBounds(200, 400, 300, 30);
		textfield3.addActionListener(this);
		panel1.add(textfield3);
		
		label5 = new JLabel("Cities");
		label5.setBounds(150, 500, 50, 50);
		label5.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel1.add(label5);
		
		
		textfield4 = new JTextField();
		textfield4.setBounds(200,500,300,50);
		textfield4.addActionListener(this);
		panel1.add(textfield4);
		
		button = new JButton("Next");
		button.setBounds(400, 600, 100, 30);
		button.setFont(new Font("MV Boli",Font.BOLD,15));
		button.setBackground(Color.lightGray);
		button.addActionListener(this);
		panel1.add(button);
		
		
		
		//-----PANEL2 COMPONENTS------------------------------
		
		Cafe = new JLabel("Cafe");
 		Cafe.setBounds(250, 100, 100, 50);
 		Cafe.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Cafe);
 		 
		combobox1 = new JComboBox(termschoices);
 		combobox1.setBounds(370, 110, 50, 25);
 		combobox1.setBackground(Color.white);
 		combobox1.addActionListener(this);
 		panel2.add(combobox1);
 		
 		
    	 
 		Sea = new JLabel("Sea");
 		Sea.setBounds(250, 150, 100, 50);
 		Sea.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Sea);
		
		combobox2 = new JComboBox(termschoices);
 		combobox2.setBounds(370, 160, 50, 25);
 		combobox2.setBackground(Color.white);
 		combobox2.addActionListener(this);
 		panel2.add(combobox2);
		
 		Museums = new JLabel("Museums");
 		Museums.setBounds(250, 200, 100, 50);
 		Museums.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Museums);
		
		combobox3 = new JComboBox(termschoices);
 		combobox3.setBounds(370, 210, 50, 25);
 		combobox3.setBackground(Color.white);
 		combobox3.addActionListener(this);
 		panel2.add(combobox3);
 		
 		Reastaurant = new JLabel("Reastaurant");
 		Reastaurant.setBounds(250, 250, 100, 50);
 		Reastaurant.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Reastaurant);
		
		combobox4 = new JComboBox(termschoices);
 		combobox4.setBounds(370, 260, 50, 25);
 		combobox4.setBackground(Color.white);
 		combobox4.addActionListener(this);
 		panel2.add(combobox4);
 		
 		Stadium = new JLabel("Stadium");
 		Stadium.setBounds(250, 300, 100, 50);
 		Stadium.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Stadium);
 		
		combobox5 = new JComboBox(termschoices);
 		combobox5.setBounds(370, 310, 50, 25);
 		combobox5.setBackground(Color.white);
 		combobox5.addActionListener(this);
 		panel2.add(combobox5);
 		
 		Bars = new JLabel("Bars");
 		Bars.setBounds(250, 350, 100, 50);
 		Bars.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Bars);
		
		combobox6 = new JComboBox(termschoices);
 		combobox6.setBounds(370, 360, 50, 25);
 		combobox6.setBackground(Color.white);
 		combobox6.addActionListener(this);
 		panel2.add(combobox6);
 		
 		Park = new JLabel("Park");
 		Park.setBounds(250, 400, 100, 50);
 		Park.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Park);
		
		combobox7 = new JComboBox(termschoices);
 		combobox7.setBounds(370, 410, 50, 25);
 		combobox7.setBackground(Color.white);
 		combobox7.addActionListener(this);
 		panel2.add(combobox7);
 		
 		Shopping_Center = new JLabel("Shopping_Center");
 		Shopping_Center.setBounds(250, 450, 100, 50);
 		Shopping_Center.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Shopping_Center);
		
		combobox8 = new JComboBox(termschoices);
 		combobox8.setBounds(370, 460, 50, 25);
 		combobox8.setBackground(Color.white);
 		combobox8.addActionListener(this);
 		panel2.add(combobox8);
 		
 		Zoo = new JLabel("Zoo");
 		Zoo.setBounds(250, 500, 100, 50);
 		Zoo.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Zoo);
 		
		combobox9 = new JComboBox(termschoices);
 		combobox9.setBounds(370, 510, 50, 25);
 		combobox9.setBackground(Color.white);
 		combobox9.addActionListener(this);
 		panel2.add(combobox9);
		
 		Galleries = new JLabel("Galleries");
 		Galleries.setBounds(250, 550, 100, 50);
 		Galleries.setFont(new Font("MV Boli",Font.PLAIN,18));
		panel2.add(Galleries);
 		
		combobox10 = new JComboBox(termschoices);
 		combobox10.setBounds(370, 560, 50, 25);
 		combobox10.setBackground(Color.white);
 		combobox10.addActionListener(this);
 		panel2.add(combobox10);
		
		
 		okbutton = new JButton("ok");
 		okbutton.setBounds(350, 620, 100, 30);
 		okbutton.setFont(new Font("MV Boli",Font.BOLD,15));
 		okbutton.setBackground(Color.LIGHT_GRAY);
 		okbutton.addActionListener(this);
		panel2.add(okbutton);
		
		//----RESULTSPANEL COMPONENTS-------------
		
		
		traveller1 = new JLabel("Traveller:");
		traveller1.setBounds(250, 250, 100, 50);
		traveller1.setFont(new Font("MV Boli",Font.PLAIN,18));
		resultspanel.add(traveller1);
  		
		traveller2 = new JLabel();
		traveller2.setBounds(350, 250, 100, 50);
		traveller2.setFont(new Font("MV Boli",Font.PLAIN,18));
		resultspanel.add(traveller2);
		
	
		
		visit1 = new JLabel("Visit:");
		visit1.setBounds(250, 300, 100, 50);
		visit1.setFont(new Font("MV Boli",Font.PLAIN,18));
		resultspanel.add(visit1);
		
		visit2 = new JLabel();
		visit2.setBounds(350, 300, 100, 50);
		visit2.setFont(new Font("MV Boli",Font.PLAIN,18));
		resultspanel.add(visit2);
		
        
		homepage = new JButton("Homepage");
		homepage.setBounds(290, 400, 120, 30);
		homepage.setFont(new Font("MV Boli",Font.BOLD,15));
		homepage.setBackground(Color.LIGHT_GRAY);
		homepage.addActionListener(this);
		resultspanel.add(homepage);
		
		this.setVisible(true);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	  
		
		if((e.getSource()==okbutton)) {
			
			int age = Integer.parseInt(textfield2.getText());
			
			
		 
			if(age>=16 && age<= 25) {
				 Traveller t = new YoungTraveller(textfield1.getText(),age,textfield3.getText());
				  
				 t.setCandidateCities(textfield4.getText());
				
				   t.setPreferencesVector(combobox1.getSelectedIndex(),0);
				   t.setPreferencesVector(combobox2.getSelectedIndex(),1);
				   t.setPreferencesVector(combobox3.getSelectedIndex(),2);
				   t.setPreferencesVector(combobox4.getSelectedIndex(),3);
				   t.setPreferencesVector(combobox5.getSelectedIndex(),4);
				   t.setPreferencesVector(combobox6.getSelectedIndex(),5);
				   t.setPreferencesVector(combobox7.getSelectedIndex(),6);
				   t.setPreferencesVector(combobox8.getSelectedIndex(),7);
				   t.setPreferencesVector(combobox9.getSelectedIndex(),8);
				   t.setPreferencesVector(combobox10.getSelectedIndex(),9);
				   
				   traveller2.setText(t.getName());
				   visit2.setText(t.getVisit());
				   
				   
			}else if(age>25 && age <=60) {
				Traveller t = new MiddleTraveller(textfield1.getText(),age,textfield3.getText());
			       
				   t.setCandidateCities(textfield4.getText());
				  
				   t.setPreferencesVector(combobox1.getSelectedIndex(),0);
				   t.setPreferencesVector(combobox2.getSelectedIndex(),1);
				   t.setPreferencesVector(combobox3.getSelectedIndex(),2);
				   t.setPreferencesVector(combobox4.getSelectedIndex(),3);
				   t.setPreferencesVector(combobox5.getSelectedIndex(),4);
				   t.setPreferencesVector(combobox6.getSelectedIndex(),5);
				   t.setPreferencesVector(combobox7.getSelectedIndex(),6);
				   t.setPreferencesVector(combobox8.getSelectedIndex(),7);
				   t.setPreferencesVector(combobox9.getSelectedIndex(),8);
				   t.setPreferencesVector(combobox10.getSelectedIndex(),9);
				   
				  
				   traveller2.setText(t.getName());
				   visit2.setText(t.getVisit());
				
			}else {
				if(age>60 && age <=115) {
				Traveller t = new ElderTraveller(textfield1.getText(),age,textfield3.getText());
				
				   t.setCandidateCities(textfield4.getText());
			         
				   t.setPreferencesVector(combobox1.getSelectedIndex(),0);
				   t.setPreferencesVector(combobox2.getSelectedIndex(),1);
				   t.setPreferencesVector(combobox3.getSelectedIndex(),2);
				   t.setPreferencesVector(combobox4.getSelectedIndex(),3);
				   t.setPreferencesVector(combobox5.getSelectedIndex(),4);
				   t.setPreferencesVector(combobox6.getSelectedIndex(),5);
				   t.setPreferencesVector(combobox7.getSelectedIndex(),6);
				   t.setPreferencesVector(combobox8.getSelectedIndex(),7);
				   t.setPreferencesVector(combobox9.getSelectedIndex(),8);
				   t.setPreferencesVector(combobox10.getSelectedIndex(),9);
				
				   
				   traveller2.setText(t.getName());
				   visit2.setText(t.getVisit());
				
				}
				
			}
			panel2.setVisible(false);
			resultspanel.setVisible(true);
			
		}	
		
	    if(e.getSource()==button) {
	    	panel1.setVisible(false);
			panel2.setVisible(true);
	    	
	    }
	    if(e.getSource()==homepage) {
	    	resultspanel.setVisible(false);
	    	panel1.setVisible(true);
	    	textfield1.setText("");        //clear textfields.
	    	textfield2.setText("");
	    	textfield3.setText("");
	    	textfield4.setText("");
	    	
	    	combobox1.setSelectedIndex(0);   //clear comboboxes.
	    	combobox2.setSelectedIndex(0);
	    	combobox3.setSelectedIndex(0);
	    	combobox4.setSelectedIndex(0);
	    	combobox5.setSelectedIndex(0);
	    	combobox6.setSelectedIndex(0);
	    	combobox7.setSelectedIndex(0);
	    	combobox8.setSelectedIndex(0);
	    	combobox9.setSelectedIndex(0);
	    	combobox10.setSelectedIndex(0);
	    }
		
		
		

  
   }
}