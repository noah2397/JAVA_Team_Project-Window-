import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WorldClockDemo extends JFrame implements ActionListener
{
 

    private JTextField time;
    private JLabel [] label = new JLabel[6];
    private static JLabel [] label_time = new JLabel[6]; 
    private static String [] continent = {"Asia","Asia","America","Europe","Asia","Asia"};
    private static String [] country = {"Korea","China","USA","Europe","Japan","Vietnam"};
    private static String [] city = {"Seoul","Shanghai","New York","Paris","Tokyo","Hanoi"};
    Font [] f1 = new Font[6];
    Font [] f2 = new Font[6];
    Font [] f3 = new Font[6];

    public static void main(String[] args)
    {
    	WorldClockDemo gui = new WorldClockDemo();
        gui.setVisible(true);
        timeget2();
    }

    public WorldClockDemo()
    {
        super( );
        setSize(620,500);
        setTitle("World Clock Demonstration");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setBackground(Color.WHITE);
        JLabel show = new JLabel("Calculate your time: ");
        timePanel.add(show);
        time = new JTextField("This text will show the time difference between Korea and the other country you select.");
        time.setEditable(false);
        timePanel.add(time);
        add(timePanel, BorderLayout.NORTH);
        
        JPanel worldPanel = new JPanel();
        worldPanel.setLayout(new GridLayout(4, 3));

        for(int i=0;i<3;i++)
        {
        	f1[i] = new Font("San Serif", Font.BOLD, 20);
        	label[i] = new JLabel(country[i]+"/"+city[i]);
        	label[i].setFont(f1[i]);
        	label[i].setHorizontalAlignment(JLabel.CENTER);
            worldPanel.add(label[i]);
        }
        
        for(int i=0;i<3;i++) 
        {
        	f2[i] = new Font("Dialog", Font.BOLD, 16);
        	label_time[i] = new JLabel();
        	label_time[i].setFont(f2[i]);
        	label_time[i].setHorizontalAlignment(JLabel.CENTER);
        	worldPanel.add(label_time[i]);
        }

        for(int i=3;i<6;i++)
        {
        	f1[i] = new Font("San Serif", Font.BOLD, 20);
        	label[i] = new JLabel(country[i]+"/"+city[i]);
        	label[i].setFont(f1[i]);
        	label[i].setHorizontalAlignment(JLabel.CENTER);
            worldPanel.add(label[i]);
        }
        
        for(int i=3;i<6;i++) 
        {
        	f2[i] = new Font("Dialog", Font.BOLD, 16);
        	label_time[i] = new JLabel();
        	label_time[i].setFont(f2[i]);
        	label_time[i].setHorizontalAlignment(JLabel.CENTER);
        	worldPanel.add(label_time[i]);
        }
        
        add(worldPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));
        
        JButton [] button  = new JButton[6];
        for(int i=0;i<6;i++) 
        {
        	f3[i] = new Font("Monospaced", Font.ITALIC, 13);
        	button[i] = new JButton(country[i]+"/"+city[i]);
        	button[i].setFont(f3[i]);
        	button[i].addActionListener(this);
            buttonPanel.add(button[i]);
        }        
        add(buttonPanel, BorderLayout.SOUTH);
        
    }
    
    public static void timeget2()
    {
    	
    	while(true) {
    		Calendar t=Calendar.getInstance();
    		int year9 = t.get(Calendar.YEAR);
    		int month9 = t.get(Calendar.MONTH)+1;
    		int date9 = t.get(Calendar.DATE)+1;
    		int amPm9 = t.get(Calendar.AM_PM);
    		int hour9 =t.get(Calendar.HOUR);
    		int min9 =t.get(Calendar.MINUTE);
    		int sec9 =t.get(Calendar.SECOND);
    		int day9=t.get(Calendar.DAY_OF_WEEK)+1;
    		String ampm9=amPm9==Calendar.AM? "AM ":"PM ";
			team_Window.noah_clock1.setText(year9 + "." + month9 +"." + day9);
    		team_Window.noah_clock2.setText(ampm9 + hour9+"."+min9+"."+sec9);
    		
    		
    		for(int i=0;i<6;i++)
    		{
    			Calendar t_= Calendar.getInstance(TimeZone.getTimeZone(continent[i]+"/"+city[i]));
    			int year = t_.get(Calendar.YEAR);
    			int month = t_.get(Calendar.MONTH)+1;
    			int date = t_.get(Calendar.DATE)+1;
    			int amPm = t_.get(Calendar.AM_PM);
    			int hour=t_.get(Calendar.HOUR);
    			int min=t_.get(Calendar.MINUTE);
    			int sec=t_.get(Calendar.SECOND);
    			int day=t_.get(Calendar.DAY_OF_WEEK)+1;
    			String ampm=amPm==Calendar.AM? "AM ":"PM ";
    			label_time[i].setText(ampm + hour+":"+min+":"+sec);
    		}
    		try {
    			Thread.sleep(1000);
    		} catch(Exception e) {
    			System.out.println("Error");
    		}
        }
    }
    
    public void actionPerformed(ActionEvent e) {
    	 String actionCommand = e.getActionCommand();
    	 int hour[] = new int[6];
    	 int min[] = new int[6];
    	 int sec[] = new int[6];
    	 for(int i=0;i<6;i++)
  		{
  			Calendar t= Calendar.getInstance(TimeZone.getTimeZone(continent[i]+"/"+city[i]));
  			int amPm = t.get(Calendar.AM_PM);
  			String ampm=amPm==Calendar.AM? "AM":"PM";
  			hour[i]=t.get(Calendar.HOUR);
  			if(ampm.equals("PM"))
				hour[i] += 12;
  	 		min[i]=t.get(Calendar.MINUTE);
  	 		sec[i]=t.get(Calendar.SECOND);
  		}
    	 
    	 if (actionCommand.equals("Korea/Seoul"))
    	 {
    		 time.setText("You're already living in Korea.");
    	 }
    	 else if (actionCommand.equals("China/Shanghai"))
    	 {
    		 time.setText("It takes time to go to Shanghai for "
    	 +Integer.toString(Math.abs(hour[1]-hour[0]))+" hours.");
    	 }
    	 else if (actionCommand.equals("USA/New York"))
    	 {
    		 time.setText("It takes time to go to New York for "
    	 +Integer.toString(Math.abs(hour[2]-hour[0]))+" hours.");
    	 }
    	 else if (actionCommand.equals("Europe/Paris"))
    	 {
    		 time.setText("It takes time to go to Paris for "
    	 +Integer.toString(Math.abs(hour[3]-hour[0]))+" hours.");
    	 }
    	 else if (actionCommand.equals("Vietnam/Hanoi"))
    	 {
    		 time.setText("It takes time to go to Hanoi for "
    	 +Integer.toString(Math.abs(hour[5]-hour[0]))+" hours.");
    	 }
    	 else if (actionCommand.equals("Japan/Tokyo"))
    	 {
    		 time.setText("Fortunately, Japan uses the same time zone as Korea.");
    	 }
    	 else
             System.out.println("Unexpected Error.");
    }
}

