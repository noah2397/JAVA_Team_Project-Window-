import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

public class draw extends JFrame implements ActionListener
{
 
	TextArea txtArea;
	private BufferedImage bi = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
    private ImageIcon CH_saveimg = new ImageIcon(this.getClass().getResource("save.png"));
    private ImageIcon CH_penimg = new ImageIcon(this.getClass().getResource("pen.png"));
    private ImageIcon CH_colorimg = new ImageIcon(this.getClass().getResource("color.png"));
    private ImageIcon CH_eraseimg = new ImageIcon(this.getClass().getResource("eraser.png"));
    private ImageIcon CH_rectimg = new ImageIcon(this.getClass().getResource("rectag.png"));
    private ImageIcon CH_circleimg = new ImageIcon(this.getClass().getResource("circle.png"));
    private JButton jbt_save = new JButton(CH_saveimg);
    private JButton CH_pen = new JButton(CH_penimg);
    private JButton CH_color = new JButton(CH_colorimg);
    private JButton CH_erase = new JButton(CH_eraseimg);
    private JButton CH_eraseall = new JButton("erase all");
    private JButton CH_rect = new JButton(CH_rectimg);
    private JButton CH_circle = new JButton(CH_circleimg);
    private JLabel jlb = new JLabel(" ");
    private JLabel  ndrawpanel = new JLabel(new ImageIcon(bi));
    private Graphics graphics;
    private Graphics2D g;
    private Color selectedColor;
    private JLabel thick_label;
    private JTextField thickControl_tf;
    private int tf = 0;
    private Graphics g2 = bi.getGraphics();
 

    private int thick = 10;
    private int x,y,x1,y1;
    
    public static void main(String[] args)
    {
    	draw nor = new draw();
        nor.setVisible(true);
    }


    
    
    
    
    public draw()
    {
        super( );
        setSize(1000,500);
        setTitle("drawpanel");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new FlowLayout());
        toolPanel.setBackground(Color.LIGHT_GRAY);
        ndrawpanel.setBackground(Color.white);
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 1000, 500);

        
        thick_label = new JLabel("도구굵기");
        thickControl_tf = new JTextField("10", 5);
        thickControl_tf.setHorizontalAlignment(JTextField.CENTER);
    	CH_pen.setBorderPainted(false);
    	CH_color.setBorderPainted(false);
    	CH_erase.setBorderPainted(false);
    	CH_rect.setBorderPainted(false);
    	CH_circle.setBorderPainted(false); 	
    	jbt_save.setBorderPainted(false); 	
    	CH_pen.setBackground(Color.white);
    	CH_color.setBackground(Color.white);
    	CH_erase.setBackground(Color.white);
    	CH_eraseall.setBackground(Color.white);
    	CH_rect.setBackground(Color.white);
    	CH_circle.setBackground(Color.white);
    	jbt_save.setBackground(Color.white);
        toolPanel.add(jbt_save);
        toolPanel.add(CH_pen);
        toolPanel.add(CH_color);
        toolPanel.add(CH_erase);
        toolPanel.add(CH_eraseall);
        toolPanel.add(CH_rect);
        toolPanel.add(CH_circle);
        toolPanel.add (thick_label);
        toolPanel.add (thickControl_tf);
        jbt_save.addActionListener(this);
        CH_pen.addActionListener(this);
        CH_color.addActionListener(this);
        CH_erase.addActionListener(this);
        CH_eraseall.addActionListener(this);
        CH_rect.addActionListener(this);
        CH_circle.addActionListener(this);
        add(jlb);
 
        
        ndrawpanel.setLayout(null);
       
        add(toolPanel, BorderLayout.NORTH);
        add(ndrawpanel, BorderLayout.CENTER);
        //setVisible(true); 이걸 지워야 한다!
        graphics = getGraphics();
        g=(Graphics2D)graphics;
        g=(Graphics2D) bi.getGraphics();
        g.setColor(selectedColor);
      
       
    		 ndrawpanel.addMouseListener(new MouseListener() {

    			 public void mousePressed(MouseEvent e) {
    				 x = e.getX();
    				 y=e.getY();
    				 x1=0;
    				 y1=0;
    		
    			 }
    				public void mouseClicked(MouseEvent arg0) {
    				}
    				public void mouseEntered(MouseEvent arg0) {
    				}
    				public void mouseExited(MouseEvent arg0) {
    				}
    				public void mouseReleased(MouseEvent arg0) {
    					
    					}
        
    		 
    	 });
    		 
    		 
    		 
    		 ndrawpanel.addMouseMotionListener(new PaintDraw());
    		 
    		 CH_color.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
					JColorChooser chooser = new JColorChooser();
					selectedColor = chooser.showDialog(null, "Color", selectedColor);
					g.setColor(selectedColor);
					
				}
    			 
    		 });
 

 
        
    }
    
    public class PaintDraw implements MouseMotionListener{
    	
    	public void mouseDragged(MouseEvent e) {
    		if(tf==1) {
    		thick = Integer.parseInt(thickControl_tf.getText());
    		
    		x1 = e.getX();
    		y1 = e.getY();
 
    	
    		g.setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));
    		g.drawLine(x+6, y+45, x1+6, y1+45);
    		repaint();
    		
    		
    		x = x1;
    		y = y1;
   
    	}
    		
    		if(tf==0) {
    		thick = Integer.parseInt(thickControl_tf.getText());
    		g.setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));
    		g.setColor(Color.white);
    		g.drawRect(x+5, y+25, x1+5-x, y1+25-y);
    		x1= e.getX();
			y1= e.getY();    
			g.setColor(selectedColor);
			g.drawRect(x+5, y+25, x1+5-x, y1+25-y);
			repaint();
    		}
    		
    	
    	
    	
    	if(tf==2) {
    		thick = Integer.parseInt(thickControl_tf.getText());
    		g.setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));
    		g.setColor(Color.white);
    		g.drawArc(x+6, y+45, x1+6-x, y1+45-y, 0, 360);
    		x1= e.getX();
			y1= e.getY();    
			g.setColor(selectedColor);
			g.drawArc(x+6, y+45, x1+6-x, y1+45-y, 0, 360);
			repaint();
    		}
    		
    	}
    	
    	
    	public void mouseMoved(MouseEvent e) {
    		
    	}
    	
    	
    	
    }
    	    	
    	
  
    
    public void actionPerformed(ActionEvent e){
    	
    	 if(e.getSource() == CH_pen){
     		tf = 1;
     		g.setColor(Color.black);
     		g.setColor(selectedColor);
     	
    	 }
    	 else if(e.getSource() == jbt_save){
    	FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
    	dialog.setDirectory(".");
    	dialog.setVisible(true);
    	
    	String dfName = dialog.getDirectory()+dialog.getFile();
    	
    	
    	try {
    		
    		ImageIO.write(bi, "PNG", new File (dialog.getDirectory() + dialog.getFile()+".PNG"));
    		
    		
    	
    	
    	
    	 } catch (Exception e2) {

				JOptionPane.showMessageDialog(this, "저장 오류");

			}
    	 
    	 }
    	 
    	 else if(e.getSource()==CH_erase) {
    		 g.setColor(Color.white);
    	 }
    	 
    	 else if(e.getSource()==CH_eraseall) {
    		 g.setColor(Color.white);
    		 g.fillRect(0, 0, 1000, 500);
    		 repaint();
    		 g.setColor(selectedColor);
    	 }

    	 else if(e.getSource()==CH_color) {
    		 
    	 }
    	 
    	 
    	 else if(e.getSource()==CH_rect) {
    		 tf=0;
    	 }
    	 
    	 else if(e.getSource()==CH_circle) {
    		 tf=2;
    	 }
    	 
    	 else
             System.out.println("Unexpected Error.");
    }
    
    
    
}






