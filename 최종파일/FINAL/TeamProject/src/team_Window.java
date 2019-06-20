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

public class team_Window extends JFrame implements ActionListener{
	public static JLabel noah_clock1=new JLabel();
	public static JLabel noah_clock2=new JLabel();
	private JLabel imageLabel = new JLabel();
	private ImageIcon noah_background;
	public static draw nor = new draw();
	public static WorldClockDemo gui = new WorldClockDemo();
	public team_Window()
	{
		super();
		//setSize(1100,600);
		setSize(700,450);
		setTitle("Window");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 

		setUndecorated(true);

		setVisible(true);


		
		noah_background = new ImageIcon(this.getClass().getResource("sky.png")); //배경화면 할당
       
        
		setLayout(new BorderLayout());
		
		
		JPanel noah_bottom = new JPanel();
		noah_bottom.setLayout(new GridLayout(1,11));
		JPanel noah_clockPanel=new JPanel();
		noah_clockPanel.setLayout(new GridLayout(2,1));
		JLabel application=new JLabel(noah_background);
		application.setLayout(new FlowLayout());
		
		ImageIcon J_image=new ImageIcon(this.getClass().getResource("worldK.png"));
		ImageIcon noah_txt=new ImageIcon(this.getClass().getResource("txtK.png"));
		ImageIcon noah_image=new ImageIcon(this.getClass().getResource("search-engine.png"));
		ImageIcon noah_window=new ImageIcon(this.getClass().getResource("windowsS.png"));
		ImageIcon CH_image = new ImageIcon(this.getClass().getResource("paint-paletteK.png"));
		ImageIcon SOL_image=new ImageIcon(this.getClass().getResource("testK.png"));
		JButton noah_search = new JButton("search",noah_image);
		JButton noah_windowb = new JButton("window",noah_window);
		JButton noah_txtb = new JButton("text",noah_txt);
		JButton J_clock=new JButton("worldclock", J_image);
		JButton CH_drawpanel = new JButton("drawpanel", CH_image);
		
		JButton SOL_grade=new JButton("grade", SOL_image);//wkrdjq
		SOL_grade.setBackground(Color.white);
		SOL_grade.setBorderPainted(false);
		SOL_grade.addActionListener(this);
		SOL_grade.setOpaque(false);
		application.add(SOL_grade);
		
		
		noah_search.setBackground(Color.white);
		noah_windowb.setBackground(Color.white);
		noah_txtb.setBackground(Color.white);
		J_clock.setBackground(Color.white);
		CH_drawpanel.setBackground(Color.white);
		noah_search.setBorderPainted(false);
		noah_windowb.setBorderPainted(false);
		noah_txtb.setBorderPainted(false);
		J_clock.setBorderPainted(false);
		CH_drawpanel.setBorderPainted(false);
		noah_txtb.setOpaque(false);
		J_clock.setOpaque(false);
		CH_drawpanel.setOpaque(false);
		noah_txtb.addActionListener(this);
		noah_search.addActionListener(this);
		noah_windowb.addActionListener(this);//요번에 추가
		J_clock.addActionListener(this);
		CH_drawpanel.addActionListener(this);
		noah_clockPanel.setBackground(Color.white);
		noah_clockPanel.add(noah_clock1);
		noah_clockPanel.add(noah_clock2);
		noah_bottom.add(noah_windowb);
		noah_bottom.add(noah_search); //맨 밑에 넣기
		noah_bottom.add(noah_clockPanel);
		application.add(noah_txtb);
		application.add(J_clock);
		application.add(CH_drawpanel);
		add(application, BorderLayout.NORTH);
		
	
        
        
        add(noah_bottom, BorderLayout.SOUTH);//밑에 넣기
	}
	public static void main(String[] args) {
        team_Window test=new team_Window();
		test.setVisible(true);
		WorldClockDemo.timeget2();
	}

	
	public void actionPerformed(ActionEvent e) {
		String actionCommand=e.getActionCommand();
		if(actionCommand.equals("text"))
		{
			noah_txt noah_txt=new noah_txt();
			noah_txt.setVisible(true);
		}
		if(actionCommand.contentEquals("search"))
		{
			noah_search noah_search= new noah_search();
			noah_search.setVisible(true);
		}
		if(actionCommand.contentEquals("worldclock"))
		{
			gui.setVisible(true);
		}
		if(actionCommand.contentEquals("window"))
		{
			new noah_MyKeyFrame();
		}
		
		if(actionCommand.contentEquals("drawpanel"))
	
		{
			nor.setVisible(true);
		}
		if(actionCommand.contentEquals("grade"))
		{
			 Kwon_GradeCalc myFrame;

		        myFrame = new Kwon_GradeCalc();
		        myFrame.setVisible(true);
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(noah_background.getImage(), 0, 0, null);
	}
}
