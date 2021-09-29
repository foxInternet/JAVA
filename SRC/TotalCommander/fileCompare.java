package TotalCommander;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class fileCompare {
	private JFrame frame;
	private JTextField text1;
	private JTextField text2;
	private JLabel label1;
	private JLabel label2;
	private JLabel chklabel;
	private JLabel result;
	private JCheckBox chkbox;
	private Dimension Screen;
	private int x=400,y=220; //размеры фрейма
	private JButton btn;
	private File file1;
	private File file2;
	private boolean checkBox;
	
	fileCompare(){
		Screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x1=(int)(Screen.getWidth());
		int y1=(int)(Screen.getHeight());
		int x0=(int)((x1-x)/2);
		int y0=(int)((y1-y)/2);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
	
		frame=new JFrame("Сравнение файлов.(вер 1.01-18.06.21)");
		
		frame.setSize(x,y);
		frame.setLayout(new FlowLayout());
		label1=new JLabel("Первый файл:");
		label2=new JLabel("Второй файл: ");
		text1=new JTextField(20);
		text2=new JTextField(20);
	
		result=new JLabel("*************Пока нечего сравнивать.*************");
		chkbox=new JCheckBox();
		chkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(chkbox.isSelected()) checkBox=true;
			}
		});
		chklabel=new JLabel("Показывать номер байта расхождения файлов.        ");
		btn=new JButton("Сравнить");
		btn.addActionListener(new btnListener());
		p1.add(label1);
		p1.add(text1);
		p2.add(label2);
		p2.add(text2);
		p3.add(chklabel);
		p3.add(chkbox);
		p4.add(btn);
	
	
		
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.add(p4);
		frame.add(result);
		frame.setBounds(x0, y0, x, y);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
	
public void setFile(File f1,File f2) {
	file1=f1;
	file2=f2;
	text1.setText(file1.getPath());
	text2.setText(file2.getPath());
}

private class btnListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		int i=0,j=0,count=0;
		if(text1.getText().equals("") && text2.getText().equals("")) result.setText("***************Не выбраны оба файла***************");
		else if(text1.getText().equals("")) result.setText("***************Не выбран первый файл***************");
		else if(text2.getText().equals("")) result.setText("***************Не выбран второй файл***************");
		
		else {
			try(FileInputStream fis1=new FileInputStream(text1.getText());
					BufferedInputStream bis1=new BufferedInputStream(fis1);
				FileInputStream fis2=new FileInputStream(text2.getText());
					BufferedInputStream bis2=new BufferedInputStream(fis2))
			{	do{
				i=bis1.read();
				 j=bis2.read();
				 count++;
				 if(i!=j) break;
			}while(i!=-1 && j!=-1);
				if(i!=j) {
					if(checkBox) {
						result.setText("Файлы отличаются, начиная с: "+count+" байта");
						result.setForeground(Color.red);
					}else {
						result.setText("Файлы разные");
						result.setForeground(Color.red);
					}
				}else {
					result.setText("Файлы одинаковые");
					result.setForeground(Color.green);
				}
				
				
				
			}catch(FileNotFoundException errata) {errata.printStackTrace(); result.setText("Возможно нет прав доступа к файлу");}
			catch(IOException error) {error.printStackTrace();}
		}
		
	}
}
}
