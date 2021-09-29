package TotalCommander;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mkDir {
	private JFrame frame;
	private JLabel label;
	private JTextField text;
	private JButton ok;
	private JButton cancel;
	private Dimension screen;
	private JPanel panel1;
	private JPanel panel2;
	private int x=400,y=160;

	mkDir(){
		
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x2=(int)screen.getWidth();
		int y2=(int)screen.getHeight();
		int x1=(int)((x2-x)/2);
		int y1=(int)((y2-y)/2);
		frame=new JFrame(totalCommander.VERSION);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel1=new JPanel();
		panel2=new JPanel();
		label=new JLabel("Введите имя новой директории.");
		text=new JTextField(30);
		ok=new JButton("Создать");
		ok.addActionListener(new btnListener());
		cancel=new JButton("Отмена");
		cancel.addActionListener(new btnListener());
		
		panel1.add(label);
		panel1.add(text);
		panel2.add(ok);
		panel2.add(cancel);
		frame.getContentPane().add(BorderLayout.CENTER,panel1);
		frame.getContentPane().add(BorderLayout.SOUTH,panel2);
		frame.setBounds(x1, y1, x, y);
		frame.setVisible(true);
		frame.setResizable(false);
	}

private class btnListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==cancel) {
			frame.dispose();
			frame.setVisible(false);
		}
		if(event.getSource()==ok) {
			if(text.getText().equals("")) {
				label.setText("Введите имя директории!");
			}
			else if(!text.getText().equals("")) {
				totalCommander.makeNewDir(text.getText());
				frame.dispose();
				frame.setVisible(false);
		 }
	  }
		
	}//end actionPerformed
  }//end btnListener
}//end class
