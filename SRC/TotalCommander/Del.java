package TotalCommander;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Del {
	private JFrame frame;
	private JLabel label;

	private JButton delete;
	private JButton cancel;
	private Dimension screen;
	private JPanel panel1;
	private JPanel panel2;
	private int x=400,y=160;
	private File fileOrDir;
	private boolean MultDel; //если false, то одиночное копирование, если true, то множественное копирование

	
	Del(File f,boolean mult){
		fileOrDir=f;
		MultDel=mult;
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x2=(int)screen.getWidth();
		int y2=(int)screen.getHeight();
		int x1=(int)((x2-x)/2);
		int y1=(int)((y2-y)/2);
		frame=new JFrame(totalCommander.VERSION);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel1=new JPanel();
		panel2=new JPanel();
		if(fileOrDir.isDirectory()) {
			if(!MultDel)
				label=new JLabel("<html><center>Вы действительно хотите удалить папку <br>"+fileOrDir.getName()+"?</center></html>");
			else if(MultDel)
				label=new JLabel("<html><center>Вы действительно хотите удалить папку <br>"+fileOrDir.getName()+", а также другие выделеные папки?</center></html>");
		}
		else if(fileOrDir.isFile()) {
			if(!MultDel)
				label=new JLabel("<html><center>Вы действительно хотите удалить файл <br>"+fileOrDir.getName()+"?</center></html>");
			else if(MultDel)
				label=new JLabel("<html><center>Вы действительно хотите удалить файл <br>"+fileOrDir.getName()+", а также другие выделенные файлы?</center></html>");
		}

		delete=new JButton("Удалить");
		delete.addActionListener(new btnListener());
		cancel=new JButton("Отмена");
		cancel.addActionListener(new btnListener());
		
		panel1.add(label);

		panel2.add(delete);
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
		if(event.getSource()==delete) {
			totalCommander.deleteDirOrFile(true,MultDel);//MultDel - если true, то множественное удаление
			frame.dispose();
			frame.setVisible(false);
	  }
		
	}//end actionPerformed
  }//end btnListener
}//end class