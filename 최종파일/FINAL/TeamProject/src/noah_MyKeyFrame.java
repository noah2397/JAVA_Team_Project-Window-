import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class noah_MyKeyFrame extends JFrame{
	private int count=0;
	public noah_MyKeyFrame(){
		KeyPanel p = new KeyPanel();
		setContentPane(p); // KeyPanel����
  
		setSize(900,400); // size
		setVisible(true); // ǥ�ÿ���
		p.requestFocus(); // ���� ��û
  
	}
	class KeyPanel extends JPanel {
		
		Font newfont=new Font("�������", Font.BOLD, 20);
		JLabel la = new JLabel("� Ű�� �������� �����ݴϴ�!"); 
		JLabel text = new JLabel("��ҹ��� ������� ���������� END�� ġ��(��,�ҹ��� �������) ����");
		JPanel first=new JPanel();
		JPanel second=new JPanel();
		JPanel third=new JPanel();
		
		public KeyPanel(){
			la.setFont(newfont);
			text.setFont(newfont);
			setLayout(null);
			text.setBounds(120,0,900,330);
			la.setBounds(300, 40, 340, 330);
			first.setBounds(350,90,40,40);
			second.setBounds(410,90,40,40);
			third.setBounds(470,90,40,40);
			first.setBackground(Color.WHITE);
			second.setBackground(Color.WHITE);
			third.setBackground(Color.WHITE);
			add(first);
			add(second);
			add(third);
			add(la);
			add(text);
			this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){ 
			String s = e.getKeyText(e.getKeyCode()); 
			la.setText(s + "�� ���Ƚ��ϴ�.");
			if(count==3)
			{
				System.exit(0);
			}
			//if(e.getKeyCode() == KeyEvent.VK_F1) f1�� �̷��� ����Ѵ�
		
       
			if(e.getKeyChar() == 'e'||e.getKeyChar() == 'E')
			{ 
				count=1;
				System.out.println(""+count);
				first.setBackground(Color.RED);
			}
			else if(e.getKeyChar() == 'n'||e.getKeyChar() == 'N')
			{ 
				if(count==1)
				{
					count=2;
					System.out.println(""+count);
					second.setBackground(Color.YELLOW);
				}
				else
				{
					System.out.println(""+count);
					count=0;
					first.setBackground(Color.WHITE);
					second.setBackground(Color.WHITE);
					third.setBackground(Color.WHITE);
				}
			}
			else if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D'){ // %������
				if(count==2)
				{
					count=3;
					System.out.println(""+count);
					text.setBounds(250,0,900,330);
					text.setText("���� �ƹ� Ű�� ������ ����˴ϴ�!");
					third.setBackground(Color.GREEN);
				}
				else
				{
					System.out.println(""+count);
					count=0;
					first.setBackground(Color.WHITE);
					second.setBackground(Color.WHITE);
					third.setBackground(Color.WHITE);
				}

			}
			else
			{
				count=0;
				System.out.println(""+count);
				first.setBackground(Color.WHITE);
				second.setBackground(Color.WHITE);
				third.setBackground(Color.WHITE);
			}
			
			}
			}
			);
		}
	}
	
	public static void main(String args[]){
		new noah_MyKeyFrame();
	}

}
//[��ó] �ڹ� Swing - �̺�Ʈ - KeyEvent,KeyListener,Focus |�ۼ��� ������

