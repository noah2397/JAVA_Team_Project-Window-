import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.File;
public class noah_Filesave extends JFrame implements ActionListener{
	private String in;
	private JTextField input;
	
	public static void main(String[] args) {
		noah_Filesave a=new noah_Filesave();
		a.setVisible(true);

	}

	
	public noah_Filesave()
	{
		super();
		setSize(400,100);
		setLayout(new FlowLayout());
		input=new JTextField("input filename",10);
		add(input);
		JButton click=new JButton("save");
		click.addActionListener(this);
		add(click);
		
		
		
	}
	public void actionPerformed(ActionEvent e){
		String action=e.getActionCommand();
		in=input.getText();
		if(action.contentEquals("save"))
		{
			PrintWriter output = null;
			try
	        {
	            output = 
	                 new PrintWriter(new FileOutputStream(in+".txt"));
	        }
	        catch(FileNotFoundException a)
	        {
	            System.out.println("Error opening the file stuff.txt.");
	            System.exit(0);
	        }
			System.out.println("Writing to file.");

	        output.println(noah_txt.noah_string+"");

	        output.close( );

	        System.out.println("End of program.");
		}
		
	}
}
