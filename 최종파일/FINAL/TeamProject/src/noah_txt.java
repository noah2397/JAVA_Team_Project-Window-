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

public class noah_txt extends JFrame
                           implements ActionListener
{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int LINES = 15;
    public static final int CHAR_PER_LINE = 30;
    private int size=15;
    private Font style=new Font("πŸ≈¡", Font.PLAIN, size);
    private JTextArea memoDisplay;
    private String memo1;
    private String memo2;
    public static String noah_string;

    public static void main(String[] args)
    {
        noah_txt gui = new noah_txt( );
        gui.setVisible(true);
    }


    public noah_txt( )
    {
        super("txt");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JMenuBar first=new JMenuBar();
        JMenu txtmenu=new JMenu("File");
        first.add(txtmenu);
        JMenuItem memo1Button = new JMenuItem("Save Memo1");
        memo1Button.addActionListener(this);
        txtmenu.add(memo1Button);
        JMenuItem memo2Button = new JMenuItem("Save Memo2");
        memo2Button.addActionListener(this);
        txtmenu.add(memo2Button);
        JMenuItem clearButton = new JMenuItem("Clear"); 
        clearButton.addActionListener(this);
        txtmenu.add(clearButton);
        JMenuItem get1Button = new JMenuItem("Get Memo 1"); 
        get1Button.addActionListener(this);
        txtmenu.add(get1Button);
        JMenuItem get2Button = new JMenuItem("Get Memo 2"); 
        get2Button.addActionListener(this);
        txtmenu.add(get2Button);
        setJMenuBar(first); 
        memoDisplay = new JTextArea(LINES, CHAR_PER_LINE);
        memoDisplay.setBackground(Color.WHITE);
        JScrollPane scrolledText = new JScrollPane(memoDisplay);
        scrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrolledText); //ordinary ∏ﬁ¥∫ √ﬂ∞°
        JMenu Font=new JMenu("Font");
        first.add(Font);//∆˘∆Æ ∏ﬁ¥∫ √ﬂ∞°
        
        
        JMenuItem one=new JMenuItem("πŸ≈¡");
        Font.add(one);
        one.addActionListener(this);
        JMenuItem two=new JMenuItem("«‘√ ∑’πŸ≈¡");
        Font.add(two);
        two.addActionListener(this);
        JMenuItem three=new JMenuItem("«‘√ ∑’µ∏øÚ");
        Font.add(three);
        three.addActionListener(this);
        JMenuItem four=new JMenuItem("∏º¿∫ ∞ÌµÒ");
        Font.add(four);
        four.addActionListener(this);
        
        JMenuItem five = new JMenuItem("∆ƒ¿œø° ¿˙¿Â");
        txtmenu.add(five);
        five.addActionListener(this);
        
        
    }

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand( );

        if (actionCommand.equals("Save Memo1"))
            memo1 = memoDisplay.getText( );
        else if (actionCommand.equals("Save Memo2"))
            memo2 = memoDisplay.getText( );
        else if (actionCommand.equals("Clear"))
            memoDisplay.setText("");
        else if (actionCommand.equals("Get Memo 1"))
            memoDisplay.setText(memo1);
        else if (actionCommand.equals("Get Memo 2"))
            memoDisplay.setText(memo2);
        else if (actionCommand.equals("πŸ≈¡"))
            memoDisplay.setFont(style);
        else if (actionCommand.equals("«‘√ ∑’πŸ≈¡"))
        {
        	style=new Font("«‘√ ∑’πŸ≈¡", Font.PLAIN, size);
        	memoDisplay.setFont(style);
        } 
        else if (actionCommand.equals("«‘√ ∑’µ∏øÚ"))
        {
        	style=new Font("«‘√ ∑’µ∏øÚ", Font.PLAIN, size);
        	memoDisplay.setFont(style);
        }
        else if (actionCommand.equals("∏º¿∫ ∞ÌµÒ"))
        {
        	style=new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, size);
        	memoDisplay.setFont(style);
        }
        else if (actionCommand.equals("∆ƒ¿œø° ¿˙¿Â"))
        {	noah_string=memoDisplay.getText();
        	noah_Filesave a=new noah_Filesave();
			a.setVisible(true);
        }
        else
            memoDisplay.setText("Error in memo interface");
     } 
}
//√‚√≥: https://shonm.tistory.com/category/JAVA/java ¡ˆø¯ ∆˘∆Æ [¡§¿±¿Á¿« ¡§∏Æ≥Î∆Æ]