package TotalCommander;
import javax.swing.*;
import java.awt.*; //Dimension

public class messages {
		private JFrame frame;
		private Dimension screen;
		private JTextArea areaError;
		private int x=300;
		private int y=200;
		private JPanel panel;
		private String msg;
		messages(String s){
			msg=s;
			screen=Toolkit.getDefaultToolkit().getScreenSize();
			int x1=(int)(screen.getWidth());
			int y1=(int)(screen.getHeight());
			int x0=(x1-x)/2;
			int y0=(y1-y)/2;
			frame=new JFrame("messages robot! " +totalCommander.VERSION);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panel=new JPanel();
			
			areaError=new JTextArea(8,22);
			areaError.setText(msg);
			areaError.setFont(new Font("arial",Font.BOLD,15));
			areaError.setLineWrap(true);
			panel.add(areaError);
			
			frame.setBounds(x0, y0, x, y);
			frame.getContentPane().add(BorderLayout.CENTER,panel);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.toFront();
		}
		public void disposeMessages() {
			frame.dispose();
			frame.setVisible(false);	
		}
		public synchronized void msg(String s) { //Синхронизированный метод
			areaError.setText(s);
		}

}//end class
