package TotalCommander;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;//Runtime,Process,StringBuilder,BufferedReader,InputStreamReader
public class SysInfo {
	private static JFrame frame;
	private static JButton btnExit;
	private static Dimension screen;
	private static JTextArea text;
	private static JScrollPane scroll;
	private static int x0=800,y0=700; //Размер отцентрованного фрейма
	SysInfo(){
		frame=new JFrame("Информация о вашей системе.   Вер. 1.01-19.06.21");
		screen=Toolkit.getDefaultToolkit().getScreenSize(); //Центрование фрейма
		int x=(int)(screen.getWidth());
		int y=(int)(screen.getHeight());
		int x1=(int)((x-x0)/2);
		int y1=(int)((y-y0)/2);
		text=new JTextArea(38,65);
		frame.setLayout(new FlowLayout());
		scroll=new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		btnExit=new JButton("Выход");
		btnExit.addActionListener((ActionEvent event)->
				{
						frame.dispose();
						frame.setVisible(false);
				});
		
		frame.add(scroll);
		frame.add(btnExit);
		frame.setBounds(x1,y1,x0,y0);
		frame.setVisible(true);
		frame.setResizable(false);
		try {
			SysInfo.getSysInfo();
		}catch(IOException err) {err.printStackTrace();}
		
	}
	private	static void getSysInfo() throws IOException{
			Process shellCMD=Runtime.getRuntime().exec("systeminfo");
//Важный момент - кодировка 866 в MS Windows 10.
//Если в BufferedReader не сделать считывание потока в кодировке 866, то русская кодировка не считается
			BufferedReader bf=new BufferedReader(new InputStreamReader(shellCMD.getInputStream(),"Cp866"));
			StringBuilder sb=new StringBuilder();
			String line=null;
			while((line=bf.readLine())!=null) {
				sb.append(line);
				sb.append(System.lineSeparator());//Делаем перенос строк
				text.setText(sb.toString().trim());
			}
			bf.close();
			
			//return sb.toString().trim();
	}
}
