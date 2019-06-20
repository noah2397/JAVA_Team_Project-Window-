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
		setContentPane(p); // KeyPanel설정
  
		setSize(900,400); // size
		setVisible(true); // 표시여부
		p.requestFocus(); // 권한 요청
  
	}
	class KeyPanel extends JPanel {
		
		Font newfont=new Font("맑은고딕", Font.BOLD, 20);
		JLabel la = new JLabel("어떤 키를 눌렀는지 보여줍니다!"); 
		JLabel text = new JLabel("대소문자 상관없이 연속적으로 END를 치면(대,소문자 상관없이) 종료");
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
			la.setText(s + "가 눌렸습니다.");
			if(count==3)
			{
				System.exit(0);
			}
			//if(e.getKeyCode() == KeyEvent.VK_F1) f1은 이렇게 사용한다
		
       
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
			else if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D'){ // %눌를시
				if(count==2)
				{
					count=3;
					System.out.println(""+count);
					text.setBounds(250,0,900,330);
					text.setText("이제 아무 키나 누르면 종료됩니다!");
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
//[출처] 자바 Swing - 이벤트 - KeyEvent,KeyListener,Focus |작성자 공돌이

