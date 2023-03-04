package BenchMark;
import javax.management.MBeanServer;					//********************************
import javax.management.ObjectName;						//ТУТ БИБЛИОТЕКИ ДЛЯ ОПРЕДЕЛЕНИЯ
import javax.management.InstanceNotFoundException;		//ОПЕРАТИВНОЙ ПАМЯТИ
import javax.management.AttributeNotFoundException;		//	
import javax.management.MalformedObjectNameException;	//
import javax.management.ReflectionException;			//
import javax.management.MBeanException;					//********************************

import java.lang.management.ManagementFactory;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class start {
	private final static String VER="BenchMark CPU (SingleCore and MultiCore test).              ver.1.0.03.03RC1 (9.02.23)    Developer: Blinov Vladimir";
	static start obj=new start();
	
	static String model,model2;
	static String osName,osName2;
	static String osArch,osArch2;
	static String vendor,vendor2;
	static CpuInfo cpu;
	static int cores,cores2;
	static double mhz,mhz2;
	static int totalCores,totalCores2;
	static long totalRAM,freeRAM,totalRAM2,freeRAM2;
	static String CpuInfo="";
	private MBeanServer mbserver;
	static JButton exit,BtnStart;
	private JComboBox<String> comboBox;
	private File[] listPC;
	private long[] fromFileMSec,fromFileNSec;
	static JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14, tf15, tf16, tf17, tf18, tf19, tf20;
	static JTextField tfm1,tfm2,tfm3,tfm4,tfm5,tfm6,tfm7,tfm8,tfm9,tfm10,tfm11,tfm12,tfm13,tfm14,tfm15,tfm16,tfm17,tfm18,tfm19,tfm20;
	static JTextField fileTF11,fileTF12,fileTF13,fileTF14,fileTF15,fileTF16,fileTF17,fileTF18,fileTF19,fileTF20;
	static JTextField fileTFm11,fileTFm12,fileTFm13,fileTFm14,fileTFm15,fileTFm16,fileTFm17,fileTFm18,fileTFm19,fileTFm20;
	private JTextField[] JTF=new JTextField[20];
	private JTextField[] JTFM=new JTextField[20];
	private JTextField[] fileJTF=new JTextField[10];
	private JTextField[] fileJTFM=new JTextField[10];
	JFrame frame;
	private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
	private int screenX=(int)dim.getWidth();
	private int screenY=(int)dim.getHeight();
	private final int sizeX=1100;
	private final int sizeY=700;
	private JTextArea textCPU,textCPU2;
	private int x=(screenX-sizeX)/2;
	private int y=(screenY-sizeY)/2;
	private JPanel pCenter,pSouth,pNorth,pEast,pEastCenter1,pEastCenter2,pWest,pCenter1,pCenter2;
	private Font big=new Font("arial",Font.BOLD,12);
	private Font big2=new Font("arial",Font.BOLD,16);
	private JLabel cpuSingle=new JLabel("Тест в Одноядерном режиме.");
	private JLabel cpuMulti=new JLabel("Тест в многоядерном режиме");
	private Thread thrd;
	private JMenuBar mbar;
	private JMenu file,about;
	private JMenuItem save,exitMenu,help;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				obj.GUI();
			}
		});
	}//end main
	private void RAM() {
		try{
			//Object javax.management.MBeanServer.getAttribute(ObjectName name, String attribute) 
			//throws InstanceNotFoundException, ReflectionException, AttributeNotFoundException, MBeanException
			mbserver=ManagementFactory.getPlatformMBeanServer();
			//javax.management.ObjectName.ObjectName(String domain, String key, String value) throws MalformedObjectNameException
			Object attr1=mbserver.getAttribute(new ObjectName("java.lang","type","OperatingSystem"), "TotalPhysicalMemorySize");
			Object attr2=mbserver.getAttribute(new ObjectName("java.lang","type","OperatingSystem"), "FreePhysicalMemorySize");
			totalRAM=Long.parseLong(attr1.toString())/1024/1024; //В МегаБайтах
			freeRAM=Long.parseLong(attr2.toString())/1024/1024;  //В МегаБайтах	
		}catch(InstanceNotFoundException e1) {
		}catch(AttributeNotFoundException e2) {
		}catch(MalformedObjectNameException e3) {
		}catch(ReflectionException e4) {
		}catch(MBeanException e5) {}
		
	}
	void listPCs() {
		String[] str;
		listPC=DataBase.fPatch.listFiles();
		if(listPC.length==0) {
			str=new String[1];
			str[0]="Пока еще не было тестов";
		}else {
			str=new String[listPC.length];
			for(int i=0;i<listPC.length;i++)
				str[i]=listPC[i].getName().substring(0, listPC[i].getName().lastIndexOf(".") );
			}
		if(comboBox!=null) {
			comboBox.removeAllItems();
			for(int i=0;i<str.length;i++)
				comboBox.addItem(str[i]);
		}else {
			comboBox=new JComboBox<String>(str);
			comboBox.setPreferredSize(new Dimension(250,21));
		}
	//тут выберем по умолчанию, строку другого PC
		int otherPCs=0;
		for(int i=0;i<str.length;i++)
			if(!model.equals(str[i])) {
				otherPCs=i;
				break;
			}
		comboBox.setSelectedIndex(otherPCs);
	}
	private void detectArchitecture() {
		String CpuInfo="";
		osName=System.getProperty("os.name");
		osArch=System.getProperty("os.arch");
		try {
		cpu=new Sigar().getCpuInfoList()[0];
		vendor=cpu.getVendor();
		model=cpu.getModel();
		cores=cpu.getTotalCores();
		mhz=cpu.getMhz();
		totalCores=cpu.getTotalCores();
		//int coresPersocket=cpu.getCoresPerSocket();
		//int socket=cpu.getTotalSockets();
		textCPU=new JTextArea(8,49);	
		textCPU.setBackground(Color.BLACK);
		textCPU.setForeground(Color.GREEN);
		textCPU.setFont(big);
		RAM();//Вычислили TotalRAM and FreeRAM
		CpuInfo=String.format(
				  "\t\tИнформация о системе:\n"
				+ "OS:\t\t"+osName+"  "+osArch+"\nПроцессор:\t\t%1$s %2$s\n"
				+ " Количество ядер:\t%3$d\n"
				+ " Тактовая частота:\t%4$,.1f Мгц\n"
				+ " Всего ядер:\t\t%5$d\n"
				+ " Всего RAM:\t\t%6$,d  MBytes\n"
				+ " Свободно RAM:\t%7$,d  MBytes"
					,vendor,model,cores,mhz,totalCores,totalRAM,freeRAM);
				//" Socket: "+socket+"\n"+
				//" Cores per Socket: "+coresPersocket;
		}catch(SigarException err) {err.printStackTrace();JOptionPane.showMessageDialog(frame, err);}
		textCPU.append(CpuInfo);
		//теперь будем каждую секунду обновлять данные о freeRAM
		endLessValueFreeRAM obj=new endLessValueFreeRAM(mbserver,textCPU,cpu);
		Thread thrdFreeRam=new Thread(obj,"Thread endless FreeRAM");
		thrdFreeRam.start();
		
		pNorth.add(BorderLayout.WEST,textCPU);
		textCPU2=new JTextArea(8,50);
		textCPU2.setBackground(Color.BLACK);
		textCPU2.setForeground(Color.GREEN);
		textCPU2.setFont(big);
		textCPU2.setText("\t\tИнформация о сравниваемой системе:\n"
						+ "\tOS:\n"
						+ "\tПроцессор:\n"
						+ "\tКоличество ядер:\n"
						+ "\tТактовая частота:\n"
						+ "\tВсего ядер:\n"
						+ "\tВсего RAM:\n"
						+ "\tСвободно RAM:");
		pNorth.add(BorderLayout.EAST,textCPU2);
	}
	private void startTest() {
//запуск теста#1
		if(thrd!=null)
			thrd.interrupt();
		try {Thread.sleep(500);}catch(InterruptedException e) {e.printStackTrace();}
		thrd=new Thread(new sequential_exec());
		thrd.start();
}
	private void GUI() {
		frame=new JFrame(VER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//********************BORDER LAYOUT********************
		cpuSingle.setFont(big2);
		cpuMulti.setFont(big2);
		pCenter=new JPanel(new GridLayout(2,1));
		pCenter1=new JPanel(new BorderLayout());
		pCenter2=new JPanel(new BorderLayout());
		pCenter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		pCenter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//***************textAreaLEFT & textAreaRIGHT***********
		tf1=new JTextField(30);tfm1=new JTextField(30);JTF[0]=tf1;JTFM[0]=tfm1;
		tf2=new JTextField(30);tfm2=new JTextField(30);JTF[1]=tf2;JTFM[1]=tfm2;
		tf3=new JTextField(30);tfm3=new JTextField(30);JTF[2]=tf3;JTFM[2]=tfm3;
		tf4=new JTextField(30);tfm4=new JTextField(30);JTF[3]=tf4;JTFM[3]=tfm4;
		tf5=new JTextField(30);tfm5=new JTextField(30);JTF[4]=tf5;JTFM[4]=tfm5;
		tf6=new JTextField(30);tfm6=new JTextField(30);JTF[5]=tf6;JTFM[5]=tfm6;
		tf7=new JTextField(30);tfm7=new JTextField(30);JTF[6]=tf7;JTFM[6]=tfm7;
		tf8=new JTextField(30);tfm8=new JTextField(30);JTF[7]=tf8;JTFM[7]=tfm8;
		tf9=new JTextField(30);tfm9=new JTextField(30);JTF[8]=tf9;JTFM[8]=tfm9;
		tf10=new JTextField(30);tfm10=new JTextField(30);JTF[9]=tf10;JTFM[9]=tfm10;
		tf11=new JTextField(30);tfm11=new JTextField(30);JTF[10]=tf11;JTFM[10]=tfm11;
		tf12=new JTextField(30);tfm12=new JTextField(30);JTF[11]=tf12;JTFM[11]=tfm12;
		tf13=new JTextField(30);tfm13=new JTextField(30);JTF[12]=tf13;JTFM[12]=tfm13;
		tf14=new JTextField(30);tfm14=new JTextField(30);JTF[13]=tf14;JTFM[13]=tfm14;
		tf15=new JTextField(30);tfm15=new JTextField(30);JTF[14]=tf15;JTFM[14]=tfm15;
		tf16=new JTextField(30);tfm16=new JTextField(30);JTF[15]=tf16;JTFM[15]=tfm16;
		tf17=new JTextField(30);tfm17=new JTextField(30);JTF[16]=tf17;JTFM[16]=tfm17;
		tf18=new JTextField(30);tfm18=new JTextField(30);JTF[17]=tf18;JTFM[17]=tfm18;
		tf19=new JTextField(30);tfm19=new JTextField(30);JTF[18]=tf19;JTFM[18]=tfm19;
		tf20=new JTextField(30);tfm20=new JTextField(30);JTF[19]=tf20;JTFM[19]=tfm20;
		fileTF11=new JTextField(30);fileTFm11=new JTextField(30);fileJTF[0]=fileTF11;fileJTFM[0]=fileTFm11;
		fileTF12=new JTextField(30);fileTFm12=new JTextField(30);fileJTF[1]=fileTF12;fileJTFM[1]=fileTFm12;
		fileTF13=new JTextField(30);fileTFm13=new JTextField(30);fileJTF[2]=fileTF13;fileJTFM[2]=fileTFm13;
		fileTF14=new JTextField(30);fileTFm14=new JTextField(30);fileJTF[3]=fileTF14;fileJTFM[3]=fileTFm14;
		fileTF15=new JTextField(30);fileTFm15=new JTextField(30);fileJTF[4]=fileTF15;fileJTFM[4]=fileTFm15;
		fileTF16=new JTextField(30);fileTFm16=new JTextField(30);fileJTF[5]=fileTF16;fileJTFM[5]=fileTFm16;
		fileTF17=new JTextField(30);fileTFm17=new JTextField(30);fileJTF[6]=fileTF17;fileJTFM[6]=fileTFm17;
		fileTF18=new JTextField(30);fileTFm18=new JTextField(30);fileJTF[7]=fileTF18;fileJTFM[7]=fileTFm18;
		fileTF19=new JTextField(30);fileTFm19=new JTextField(30);fileJTF[8]=fileTF19;fileJTFM[8]=fileTFm19;
		fileTF20=new JTextField(30);fileTFm20=new JTextField(30);fileJTF[9]=fileTF20;fileJTFM[9]=fileTFm20;
		for(JTextField i:JTF) {
			i.setBackground(Color.black);
			i.setForeground(Color.green);
		}
		for(JTextField i:JTFM) {
			i.setBackground(Color.black);
			i.setForeground(Color.green);
		}
		tf1.setText("#1.Сортировка методом пузырька:");
		tf2.setText("#2.Вычисления Collections.swap");
		tf3.setText("#3.Сортировка Arrays.sort");
		tf4.setText("#4.Потоковая сортировка IntStream");
		tf5.setText("#5.Вычисления с плавающей запятой 1.");
		tf6.setText("#6.Вычисления с плавающей запятой 2.");
		tf7.setText("#7.Вычисления с плавающей запятой 3.");
		tf8.setText("#8.Потоковое вычисление Stream<Long>reduce");
		tf9.setText("#9.Потоковое вычисление Stream<Double>reduce");
		tf10.setText("#10.Сортировка QuickSort.Метод Хоара");
		tfm1.setText("#1.Сортировка методом пузырька.Fork/Join");
		tfm2.setText("#2.Fork/Join Вычисления Collections.swap");
		tfm3.setText("#3.Сортировка Arrays.sort");
		tfm4.setText("#4.Потоковая сортировка IntStream");
		tfm5.setText("#5.Fork/Join Вычисления с плавающей запятой 1.");
		tfm6.setText("#6.Fork/Join Вычисления с плавающей запятой 2.");
		tfm7.setText("#7.Fork/Join Вычисления с плавающей запятой 3.");
		tfm8.setText("#8.Потоковое вычисление Stream<Long>reduce");
		tfm9.setText("#9.Потоковое вычисление Stream<Double>reduce");
		tfm10.setText("#10.Fork/Join сортировка quickSort. Метод Хоара.");
		JPanel p1=new JPanel();				//Для надписей cpuSingle
		JPanel p2=new JPanel();				//Для надписей cpuMulti
		JPanel panelLeft=new JPanel(new GridLayout(10,1));
		JPanel panelRight=new JPanel(new GridLayout(10,1));
		p1.add(cpuSingle);
		p2.add(cpuMulti);
		panelLeft.add(tf1);panelLeft.add(tf2);panelLeft.add(tf3);panelLeft.add(tf4);panelLeft.add(tf5);
		panelLeft.add(tf6);panelLeft.add(tf7);panelLeft.add(tf8);panelLeft.add(tf9);panelLeft.add(tf10);
		panelRight.add(tf11);panelRight.add(tf12);panelRight.add(tf13);panelRight.add(tf14);panelRight.add(tf15);
		panelRight.add(tf16);panelRight.add(tf17);panelRight.add(tf18);panelRight.add(tf19);panelRight.add(tf20);
		
		JPanel panelLeftMulti=new JPanel(new GridLayout(10,1));
		JPanel panelRightMulti=new JPanel(new GridLayout(10,1));
		panelLeftMulti.add(tfm1);panelLeftMulti.add(tfm2);panelLeftMulti.add(tfm3);panelLeftMulti.add(tfm4);panelLeftMulti.add(tfm5);
		panelLeftMulti.add(tfm6);panelLeftMulti.add(tfm7);panelLeftMulti.add(tfm8);panelLeftMulti.add(tfm9);panelLeftMulti.add(tfm10);
		panelRightMulti.add(tfm11);panelRightMulti.add(tfm12);panelRightMulti.add(tfm13);panelRightMulti.add(tfm14);panelRightMulti.add(tfm15);
		panelRightMulti.add(tfm16);panelRightMulti.add(tfm17);panelRightMulti.add(tfm18);panelRightMulti.add(tfm19);panelRightMulti.add(tfm20);
		
		pCenter1.add(BorderLayout.NORTH,p1);//Для надписей cpuSingle
		pCenter2.add(BorderLayout.NORTH,p2);//Для надписей cpuMulti
		pCenter1.add(BorderLayout.WEST,panelLeft);
		pCenter1.add(BorderLayout.EAST,panelRight);
		pCenter2.add(BorderLayout.WEST,panelLeftMulti);
		pCenter2.add(BorderLayout.EAST,panelRightMulti);
//***************textAreaLEFT & textAreaRIGHT***********				
		pSouth=new JPanel();
		pNorth=new JPanel(new BorderLayout());
		
		pEast=new JPanel(new GridLayout(2,1));
		pEastCenter1=new JPanel(new BorderLayout());
		pEastCenter2=new JPanel(new BorderLayout());
		pEastCenter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		pEastCenter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		pEast.setPreferredSize(new Dimension(400,500));
		
		pWest=new JPanel();
		pNorth.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pNorth.setBorder(BorderFactory.createRaisedBevelBorder());
		pCenter.add(pCenter1);//Внутренняя верхняя основная панель
		pCenter.add(pCenter2);//Внутренняя нижняя основная панель
		frame.add(BorderLayout.CENTER,pCenter);
		frame.add(BorderLayout.SOUTH,pSouth);
		frame.add(BorderLayout.NORTH,pNorth);
		frame.add(BorderLayout.WEST,pWest);
		frame.add(BorderLayout.EAST,pEast);
//*******************/BORDER LAYOUT********************
//********************panelNorth***********************
				detectArchitecture();
//*******************/panelNorth***********************
//*******************Восточная часть pEAST*************
		JLabel tmpLab=new JLabel("Сравнить с ");
		tmpLab.setFont(big2);
		listPCs();
		comboBox.addActionListener(new comboBoxAction());
		//pEastCenter1.add(BorderLayout.NORTH,tmpLab);
		//pEastCenter1.add(BorderLayout.CENTER,comboBox);
		pEast.add(pEastCenter1);
		pEast.add(pEastCenter2);
		JPanel panelEastCenter1=new JPanel(new GridLayout(10,1));
		JPanel panelEastCenter2=new JPanel(new GridLayout(10,1));
		panelEastCenter1.add(fileTF11);panelEastCenter1.add(fileTF12);panelEastCenter1.add(fileTF13);panelEastCenter1.add(fileTF14);panelEastCenter1.add(fileTF15);
		panelEastCenter1.add(fileTF16);panelEastCenter1.add(fileTF17);panelEastCenter1.add(fileTF18);panelEastCenter1.add(fileTF19);panelEastCenter1.add(fileTF20);
		panelEastCenter2.add(fileTFm11);panelEastCenter2.add(fileTFm12);panelEastCenter2.add(fileTFm13);panelEastCenter2.add(fileTFm14);panelEastCenter2.add(fileTFm15);
		panelEastCenter2.add(fileTFm16);panelEastCenter2.add(fileTFm17);panelEastCenter2.add(fileTFm18);panelEastCenter2.add(fileTFm19);panelEastCenter2.add(fileTFm20);
		pEastCenter1.add(BorderLayout.CENTER,panelEastCenter1);
		pEastCenter2.add(BorderLayout.CENTER,panelEastCenter2);
		JPanel comboBoxPanel=new JPanel();
		comboBoxPanel.add(tmpLab);comboBoxPanel.add(comboBox);
		pEastCenter1.add(BorderLayout.NORTH,comboBoxPanel);
		//пустая панель
		JPanel emptyPanel=new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400,31));
		pEastCenter2.add(BorderLayout.NORTH,emptyPanel);
//******************/Восточная часть pEAST*************		
//************************BUTTON***********************
		exit=new JButton("Выход");
		exit.setBackground(Color.red);
		exit.setForeground(Color.white);
		exit.addActionListener( (ae) -> System.exit(0));
		BtnStart=new JButton("Запустить тест");
		BtnStart.addActionListener(new btnAction());
		BtnStart.setBackground(Color.green);
		BtnStart.setForeground(Color.black);
		pSouth.add(BtnStart);
		pSouth.add(exit);
//***********************/BUTTON***********************	
//*****************MENU********************************
		mbar=new JMenuBar();
		file=new JMenu("File");
		about=new JMenu("About");
		save=new JMenuItem("Сохранить результат");
		exitMenu=new JMenuItem("Выход");
		exitMenu.addActionListener( (ae) -> System.exit(0) );
		help=new JMenuItem("О программе");
		help.addActionListener(new btnAction());
		file.add(save);file.add(exitMenu);
		about.add(help);
		mbar.add(file);mbar.add(about);
//***************/MENU*********************************
		frame.setSize(sizeX,sizeY);
		frame.setJMenuBar(mbar);
		frame.setLocation(x, y);
		frame.setVisible(true);
		frame.setResizable(false);
	}
class comboBoxAction implements ActionListener{
	public void actionPerformed(ActionEvent ie) {
		if(ie.getSource()==comboBox) {
			File file;
			int index=comboBox.getSelectedIndex();
			if(index>=0 && listPC.length>0)
				file=listPC[index];
			else
				return;
			System.out.println("Выбран файл: "+file.getAbsolutePath());
			//заполним массивы long[] для милисекунд и наносекунд из выбранного файла в JComboBox
			parsingFileToLongArray(file);
			//заполним все таблицы
			for(int i=0;i<10;i++) {
				//"OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> 100%"
				fileJTF[i].setText(String.valueOf(fromFileMSec[i])+" млсек // "+String.valueOf(fromFileNSec[i]) +" нсек. --> 100%");
				fileJTFM[i].setText(String.valueOf(fromFileMSec[i+10])+" млсек // "+String.valueOf(fromFileNSec[i+10]) +" нсек --> "+((fromFileNSec[i+10])*100)/fromFileNSec[i]+"%");
			}
			//теперь заполним textCPU2 JTextArea()
			parsingFileToTextCPU2(file);
		}//end if
	}//end itemStateChanged
}//end class
	private void parsingFileToTextCPU2(File f) {
		File file=f;
		int c=-1;
		StringBuilder sb=new StringBuilder();
		try(BufferedReader br=new BufferedReader(new FileReader(file)))
		{
			do {
				c=br.read();
				if(c!=-1) {
					sb.append( (char) c);
				}
			}while(c!=-1);
			osName2=sb.substring(sb.indexOf("osName:[")+8,sb.indexOf("]\n",sb.indexOf("osName:[")));
			osArch2=sb.substring(sb.indexOf("osArch:[")+8,sb.indexOf("]\n",sb.indexOf("osArch:[")));
			vendor2=sb.substring(sb.indexOf("vendor:[")+8,sb.indexOf("]\n",sb.indexOf("vendor:[")));
			model2=sb.substring(sb.indexOf("model:[")+7,sb.indexOf("]\n",sb.indexOf("model:[")));
			cores2=Integer.parseInt(sb.substring(sb.indexOf("cores:[")+7,sb.indexOf("]\n",sb.indexOf("cores:["))) );
			mhz2=Double.parseDouble( sb.substring(sb.indexOf("mhz:[")+5,sb.indexOf("]\n",sb.indexOf("mhz:["))) );
			totalCores2=Integer.parseInt(sb.substring(sb.indexOf("totalCores:[")+12,sb.indexOf("]\n",sb.indexOf("totalCores:["))) );
			totalRAM2=Integer.parseInt(sb.substring(sb.indexOf("totalRAM:[")+10,sb.indexOf("]\n",sb.indexOf("totalRAM:["))) );
			freeRAM2=Integer.parseInt(sb.substring(sb.indexOf("freeRAM:[")+9,sb.lastIndexOf("]") ) );
			textCPU2.setText("\t\tИнформация о сравниваемой системе:\n");
			String CpuInfo2=String.format("\tOS:\t\t"+osName2+" "+osArch2+"\n"
										+ "\tПроцессор:\t\t%1$s %2$s\n"
									   	+ "\tКоличество ядер:\t%3$d\n"
									   	+ "\tТактовая частота:\t%4$.1f Мгц\n"
									   	+ "\tВсего ядер:\t\t%5$d\n"
									   	+ "\tВсего RAM:\t\t%6$,d  MBytes\n"
									   	+ "\tСвободно RAM:\t%7$,d  MBytes"
									   ,vendor2,model2,cores2,mhz2,totalCores2,totalRAM2,freeRAM2);
			textCPU2.append(CpuInfo2);
		}catch(NullPointerException err0) {err0.printStackTrace();JOptionPane.showMessageDialog(frame, "В Файле "+file.getName()+" String is null. NullPointerException");
		}catch(FileNotFoundException err1) {err1.printStackTrace();JOptionPane.showMessageDialog(frame, "Файл "+file.getName()+" не найден, или к нему нет доступа");
		}catch(NumberFormatException err2) {err2.printStackTrace();JOptionPane.showMessageDialog(frame, "В файле "+file.getName()+" ошибка формата данных. Исправьте или удалите файл.");
		}catch(StringIndexOutOfBoundsException err3) {err3.printStackTrace();JOptionPane.showMessageDialog(frame, "В файле "+file.getName()+" Ошибка StringIndexOutOfBoundsException в sb.substring(start,end)");
		}catch(IOException err4) {err4.printStackTrace();JOptionPane.showMessageDialog(frame, err4);}
	}
	private void parsingFileToLongArray(File f) {
		File file=f;
		//А теперь прочитаем файл и запишем результаты в long[] fromFileMSec,fromFileNsec
		StringBuilder sb=new StringBuilder();
		int c=-1;
		try(BufferedReader br=new BufferedReader(new FileReader(file))){
			do {
				c=br.read();
				if(c!='#')
					sb.append( (char) c);
			}while(c!='#');
			System.out.println();
			System.out.println(sb.toString());
			//Вырежим цифры
			StringBuilder mlSec=new StringBuilder(sb.substring(sb.indexOf("mlsec:[")+7, sb.indexOf("]\n")) );
			StringBuilder nSec=new StringBuilder(sb.substring(sb.indexOf("nsec:[")+6, sb.lastIndexOf("]")) );
			//Удалим пробелы
			for(int i=0;i<mlSec.length();i++)
					if( mlSec.charAt(i)==' ')
						mlSec.deleteCharAt(i);
			for(int j=0;j<nSec.length();j++)
				if( nSec.charAt(j)==' ')
					nSec.deleteCharAt(j);
			//разделим цифры в строке на массиф строк из ифр
			String s1=new String(mlSec);
			String s2=new String(nSec);
			String[] mlSecs=s1.split(",");
			String[] nSecs=s2.split(",");
			//Запарсим строковые цифры в цифровой массив
			fromFileMSec=new long[mlSecs.length];
			fromFileNSec=new long[nSecs.length];
			for(int i=0;i<mlSecs.length;i++)
				fromFileMSec[i]=Long.parseLong(mlSecs[i]);
			for(int i=0;i<nSecs.length;i++)
				fromFileNSec[i]=Long.parseLong(nSecs[i]);
			for(int k=0;k<fromFileMSec.length;k++)
				System.out.print(fromFileMSec[k]+" ");
			System.out.println();
			for(int m=0;m<fromFileNSec.length;m++)
				System.out.print(fromFileNSec[m]+" ");
		}catch(NullPointerException err0) {err0.printStackTrace();JOptionPane.showMessageDialog(frame, "В Файле "+file.getName()+" String is null. NullPointerException");
		}catch(FileNotFoundException err1) {err1.printStackTrace();JOptionPane.showMessageDialog(frame, "Файл "+file.getName()+" не найден, или к нему нет доступа");
		}catch(NumberFormatException err2) {err2.printStackTrace();JOptionPane.showMessageDialog(frame, "В файле "+file.getName()+" ошибка формата данных. Исправьте или удалите файл.");
		}catch(StringIndexOutOfBoundsException err3) {err3.printStackTrace();JOptionPane.showMessageDialog(frame, "В файле "+file.getName()+" Ошибка StringIndexOutOfBoundsException в sb.substring(start,end)");
		}catch(IOException err4) {err4.printStackTrace();JOptionPane.showMessageDialog(frame, err4);}
		}
class btnAction implements ActionListener{
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==BtnStart) {
			BtnStart.setEnabled(false);
			for(int i=10;i<20;i++) {
		//ОБНУЛЯЕМ ТЕКСТОВЫЕ ПОЛЯ
				JTF[i].setText("");
				JTFM[i].setText("");
			}
			startTest();
		}
		if(ae.getSource()==help) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new About();	
				}
			});
			
		}
	}//end actionPerformed
}//end inner class btnAction
}//end
