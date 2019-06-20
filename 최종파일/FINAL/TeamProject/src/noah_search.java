import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class noah_search extends JFrame implements ActionListener{
	private String result;
	private Font a=new Font("Serif", Font.BOLD, 20);
	private JTextField input=new JTextField("input a funtion",30);
	public noah_search()
	{
		super();
		setLayout(new FlowLayout());
		setTitle("Search funtion");
		setSize(800,100);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		
		
		JLabel string=new JLabel("Search");
		string.setFont(a);
		JButton sear=new JButton();
		sear.add(string);
		sear.addActionListener(this);
		add(input);
		add(sear);
	}
	public static void main(String[] args) {
		
		noah_search a= new noah_search();
		a.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		result = input.getText();
		if(result.contentEquals("worldclock"))
		{	
			team_Window.gui.setVisible(true);
		}
		else if(result.contentEquals("text"))
		{	
			noah_txt a= new noah_txt();
			a.setVisible(true);
		}
		else if(result.contentEquals("window"))
		{
			new noah_MyKeyFrame();
		}	
		else if(result.contentEquals("drawpanel"))
		{
			team_Window.nor.setVisible(true);
		}
		else if(result.contentEquals("grade"))
		{
			 Kwon_GradeCalc myFrame;

		        myFrame = new Kwon_GradeCalc();
		        myFrame.setVisible(true);
		}
		else
		{
			input.setText("Sorry, not enrolled moment");
		}
	}
}
