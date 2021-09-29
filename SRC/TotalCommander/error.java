package TotalCommander;
//Класс GUI ошибок
import javax.swing.*;
import java.awt.*; //Dimension

public class error {
	private JFrame frame;
	private Dimension screen;
	private JTextArea areaError;
	private int x=500;
	private int y=200;
	private JPanel panel;
	error(String s){
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x1=(int)(screen.getWidth());
		int y1=(int)(screen.getHeight());
		int x0=(x1-x)/2;
		int y0=(y1-y)/2;
		frame=new JFrame("Error msg!  "+totalCommander.VERSION);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel=new JPanel();
		areaError=new JTextArea(9,40);
		areaError.setText(s);
		areaError.setFont(new Font("arial",Font.BOLD,14));
		areaError.setLineWrap(true);
		panel.add(areaError);
		
		frame.setBounds(x0, y0, x, y);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.toFront();
		
	}
}
