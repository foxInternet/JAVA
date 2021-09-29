package TotalCommander;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class totalCommander {
	private static totalCommander obj;
	private static String newDir=null;
	static final String VERSION="FileFox commander (вер. 1.08-13.06.2021)";
	private int dimJTextField;
	private int countDirL; //Кол-во папок
	private int countDirR;
	private int countFilesL; //Кол-во файлов
	private int countFilesR;
	private long sizeBytesL; //Общий размер файлов
	private long sizeBytesR;
//**************************************MENU********************************************************
	private JMenuBar menuBar;
	private JMenu mFile;
	private JMenuItem mCompareTo;
	private JMenu mSelected;
	private JMenuItem mSelectedAll;
	private JMenu mNet;
	private JMenuItem mConnectNetDisk;
	private JMenuItem mDisConnectNetDisk;
	private JMenu mView;
	private JMenuItem mSortByName;
	private JMenuItem mSortByType;
	private JMenuItem mSortByDate;
	private JMenuItem mSortBySize;
	private JMenu mUtil;
	private JMenuItem mDecToBin;
	private JMenu mConfig;
	private JMenuItem mInfoSys;
	private JMenu menuHelp;
	private JMenuItem mAbout;
	private JMenuItem mHelp;
	private JMenuItem mExit;
//**********************Нижние кнопки*************************************
	private JTextField shellCMD;
	private JButton btnView;
	private JButton btnEdit;
	private JButton btnCopy;
	private JButton btnCut;
	private JButton btnDir;
	private JButton btnDel;
	private JButton btnExit2;
//*******************END Нижние кнопки************************************
	
//***********************************END MENU*******************************************************	
	private final static String startPath="C:\\";
	private String currentPathL="C:\\";
	private String currentPathR="C:\\";
	private static String currentTable="L";
	private String newPathL;
	private String newPathR;
	private static File fileLeft;
	private static File fileRight;
	private static final String[] COLUMNS= {"Имя","Тип","Размер в байтах","Дата"};
	
	private String[][] dataL;
	private String[][] dataR;
	FileView[] fsViewL;
	FileView[] fsViewR;
	private JLabel statusL;
	private JLabel statusR;
	private long statusLsizeFile; //размер выделенного файла
	private long statusRsizeFile;
	
	private DefaultTableModel deftableL;
	private DefaultTableModel deftableR;
	private JTable tableL;
	private JTable tableR;
	
//***********************Верхняя панель IP Адрес и выбор дисков**********************************************
	private DiskList objDisk;
	private JFrame frame;
	private JPanel panel;
	private JPanel panel0;//Пенель ввода IP адресов и выбора дисков
	private JPanel panel01;//Ввод ip адресов
	private JLabel inputIP;
	private JTextField ipAdress;
	private JLabel shareLabel;
	private JTextField shareName;
	private JComboBox<String> diskListL; //Выбор списка дисков СЛЕВА
	private JComboBox<String> diskListR; //Выбор списка дисков СПРАВА
	private String[][] dList;//Массив дисков
	private JPanel panel02;//Выбор дисков
	private JPanel panel02L; //Левая часть panel02
	private JPanel panel02R; //Правая часть panel02
	private JButton  diskButtonL;
	private JButton  diskButtonR;
	private JLabel diskInfoL;
	private JLabel diskInfoR;
	private JLabel userName;
	private JTextField userNameText;
	private JLabel password;
	private JPasswordField passwordText;
	private JButton connect;
//**********************************************************************************************************		
	private JPanel panel2;//панель JTable
	private JPanel panel20;
	private JPanel panel21;
	private JPanel panel22;
	private Dimension Screen;
	private ArrayList<File> listFileL=new ArrayList<File>();
	private ArrayList<File> listFileR=new ArrayList<File>();
	private JScrollPane scrollLeft;
	private JScrollPane scrollRight;
	private JLabel path;
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
		//Thread thrdMain=new Thread(new Runnable() {
			public void run() {
				obj=new totalCommander();
			}
		});
		
	}
	totalCommander(){
		createFSL(startPath);
		createFSR(startPath);
		createGUI();
		
	}
	private void createGUI() {
		
		System.out.println("Текущий поток в createGUI() :"+Thread.currentThread().getName());
		Screen=Toolkit.getDefaultToolkit().getScreenSize();
		int w=(int)Screen.getWidth()-50;//Размер фрейма
		int h=(int)Screen.getHeight()-50;//Размер фрейма
		frame=new JFrame(VERSION);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		int X=(int)(Screen.getWidth()-w)/2;//Координаты для центрирования фрейма
		int Y=(int)(Screen.getHeight()-h)/2;
		
		panel=new JPanel(new FlowLayout());
//*********************************Формирование panel0*****************************************************
		panel0=new JPanel(new GridLayout(2,1));
		panel01=new JPanel();
		panel02=new JPanel(new GridLayout(1,2));
		panel02L=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel02R=new JPanel(new FlowLayout(FlowLayout.LEFT));
		inputIP=new JLabel("IP adress/name Host:");
	
		inputIP.setFont(new Font("arial",Font.BOLD,15));
		inputIP.setForeground(Color.GRAY);
		ipAdress=new JTextField(15);
		shareLabel=new JLabel("sharename");
		shareName=new JTextField(10);
		shareName.setText("C$");
		userName=new JLabel("domain\\user:");
		userNameText=new JTextField(15);
		password=new JLabel("пароль:");
		passwordText=new JPasswordField(15);
		connect=new JButton("Connect");
		shareLabel.setFont(new Font("arial",Font.BOLD,15));
		shareName.setFont(new Font("arial",Font.BOLD,15));
		userName.setFont(new Font("arial",Font.BOLD,15));
		password.setFont(new Font("arial",Font.BOLD,15));
		userNameText.setForeground(Color.green); userNameText.setFont(new Font("arial",Font.BOLD,15));
		passwordText.setForeground(Color.green); passwordText.setFont(new Font("arial",Font.BOLD,15));
		ipAdress.setForeground(Color.green);	ipAdress.setFont(new Font("arial",Font.BOLD,15));
		shareName.setBackground(Color.black);
		shareName.setForeground(Color.green);
		userNameText.setBackground(Color.black);
		passwordText.setBackground(Color.black);
		ipAdress.setBackground(Color.black);
		connect.setFont(new Font("arial",Font.BOLD,15));
		connect.setForeground(Color.black);
		connect.setBackground(Color.green);
		
		ipAdress.setText("localhost");
		userNameText.setText("SysAdmin");
		passwordText.setText("");	
		
		panel01.add(inputIP);
		panel01.add(ipAdress);
		panel01.add(shareLabel);
		panel01.add(shareName);
		panel01.add(userName);
		panel01.add(userNameText);
		panel01.add(password);
		panel01.add(passwordText);
		panel01.add(connect);

//****************************************CREATE ComboBox*********************************************************		
		objDisk=new DiskList();
		dList=objDisk.getDiskInfo(); //Обращаемся к классу DiskList и вызываем Геттер
		File disk=new File("C:");
		diskInfoL=new JLabel(dList[0][2]+" "+dList[0][0]+"  (свободно "+String.format("%,d", disk.getFreeSpace()/1024)+" kBytes из "+String.format("%,d", disk.getTotalSpace()/1024)+" kBytes)");//Первоначальное заполнение
		diskInfoR=new JLabel(dList[0][2]+" "+dList[0][0]+"  (свободно "+String.format("%,d", disk.getFreeSpace()/1024)+" kBytes из "+String.format("%,d", disk.getTotalSpace()/1024)+" kBytes)");//Первоначальное заполнение
		diskListL=new JComboBox<String>(objDisk.disName());
		diskListR=new JComboBox<String>(objDisk.disName());

//*****************************************************************************************************************		
		diskListL.setForeground(Color.green);
		diskListL.setBackground(Color.black);
		diskListR.setForeground(Color.green);
		diskListR.setBackground(Color.black);
		diskListL.addActionListener(new comboBoxListener());
		diskListR.addActionListener(new comboBoxListener());
		connect.addActionListener(new NET());

		diskButtonL=new JButton("Close "+dList[0][0]);//Первоначальное заполнение
		diskButtonR=new JButton("Close "+dList[0][0]);//Первоначальное заполнение
		diskButtonL.setForeground(Color.black);
		diskButtonR.setForeground(Color.black);
		diskButtonL.setBackground(Color.red);
		diskButtonR.setBackground(Color.red);
		diskButtonL.setEnabled(false);
		diskButtonR.setEnabled(false);
		diskButtonL.addActionListener(new NET());
		diskButtonR.addActionListener(new NET());
			panel02L.add(diskListL);
		panel02L.add(diskButtonL);
		panel02L.add(diskInfoL);
		panel02R.add(diskListR);
		panel02R.add(diskButtonR);
		panel02R.add(diskInfoR);
		panel02L.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(Color.black,1)));
		panel02R.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(Color.black,1)));
		panel02.add(panel02L);
		panel02.add(panel02R);
		
		
		panel0.add(panel01);
		panel0.add(panel02);
//*********************************************************************************************************
		path=new JLabel("Current disk is C:");
		path.setFont(new Font("arial",Font.BOLD,15));
		//********************************JTable()*********************************************************
		deftableL=new DefaultTableModel(dataL,COLUMNS);
		deftableR=new DefaultTableModel(dataR,COLUMNS);
				tableL=new JTable(deftableL) { //Запрещаем редактирование Левой таблицы
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int i, int j) {
						return false;
					}
				};
				tableR=new JTable(deftableR){ //Запрещаем редактирование Правой таблицы
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int i, int j) {
						return false;
					}
				};
				
				
		//Изменение Цвета шапки Jtable********************************************************************
		tableL.getTableHeader().setOpaque(false);
		tableR.getTableHeader().setOpaque(false);
		tableL.getTableHeader().setBackground(Color.black);
		tableL.getTableHeader().setForeground(Color.white);
		tableR.getTableHeader().setBackground(Color.black);
		tableR.getTableHeader().setForeground(Color.white);

		
		tableL.setSelectionForeground(Color.white);
		tableL.setSelectionBackground(Color.blue);
		tableR.setSelectionForeground(Color.white);
		tableR.setSelectionBackground(Color.blue);
		tableL.setShowGrid(false);//Управление прорисовкой (показать или скрыть) сетки таблицы.
		tableR.setShowGrid(false);//Управление прорисовкой (показать или скрыть) сетки таблицы.
		
		tableL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableL.getColumnModel().getColumn(0).setPreferredWidth(w/5);
		tableL.getColumnModel().getColumn(1).setPreferredWidth(w/16);
		tableL.getColumnModel().getColumn(2).setPreferredWidth(w/11);
		tableL.getColumnModel().getColumn(3).setPreferredWidth(w/11);
		tableR.getColumnModel().getColumn(0).setPreferredWidth(w/5);
		tableR.getColumnModel().getColumn(1).setPreferredWidth(w/16);
		tableR.getColumnModel().getColumn(2).setPreferredWidth(w/11);
		tableR.getColumnModel().getColumn(3).setPreferredWidth(w/11);
		
		tableL.getTableHeader().setEnabled(true);
		tableR.getTableHeader().setEnabled(true);

		tableL.addMouseListener(new mouseListener());
		tableR.addMouseListener(new mouseListener());
		tableL.addKeyListener(new ActionKey());
		tableR.addKeyListener(new ActionKey());
//Делаем по умолчанию выделенную 1-ю строку в левой таблице
		tableL.getSelectionModel().setSelectionInterval(1, 1);
		
//END Добавляем Листенер в JTable		
//************************************************************************************************
		scrollLeft=new JScrollPane(tableL);
		scrollRight=new JScrollPane(tableR);
		scrollLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollLeft.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRight.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
		scrollLeft.setPreferredSize(new Dimension(w/2-30,h-50));
		scrollRight.setPreferredSize(new Dimension(w/2-30,h-50));
//Блок нижних кнопок****************************************
				panel2=new JPanel(new GridLayout(3,1));
				//Выравнивание Trailing по правому краю
				panel20=new JPanel(new GridLayout(1,2));
				panel21=new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
				panel22=new JPanel(new GridLayout(1,7));
				btnView=new JButton("F3 Просмотр");
				btnView.addActionListener(new actionF());
				btnEdit=new JButton("F4 Правка");
				btnEdit.addActionListener(new actionF());
				btnCopy=new JButton("F5 Копия");
				btnCopy.addActionListener(new actionF());
				btnCut=new JButton("F6 Перемещение");
				btnCut.addActionListener(new actionF());
				btnDir=new JButton("F7 Каталог");
				btnDir.addActionListener(new actionF());
				btnDel=new JButton("F8 Удаление");
				btnDel.addActionListener(new actionF());
				btnExit2=new JButton("Выход");
				btnExit2.addActionListener(new actionF());
				if(w<1400) {
					dimJTextField=60;
				}else 
					dimJTextField=100;
				shellCMD=new JTextField(dimJTextField);
				shellCMD.setBackground(Color.black);
				shellCMD.setForeground(Color.green);
				shellCMD.setFont(new Font("arial",Font.BOLD,15));
				shellCMD.addKeyListener(new ActionKey());
				panel22.add(btnView);
				panel22.add(btnEdit);
				panel22.add(btnCopy);
				panel22.add(btnCut);
				panel22.add(btnDir);
				panel22.add(btnDel);
				panel22.add(btnExit2);
			
//**********************************************************
		statusL=new JLabel("Выделено "+String.format("%,d",statusLsizeFile)+"Bytes из "+String.format("%,d",sizeBytesL/1024)+" Kbytes. Папок: 0 из "+countDirL+". Файлов 0 из "+countFilesL);
		statusR=new JLabel("Выделено "+String.format("%,d",statusRsizeFile)+"Bytes из "+String.format("%,d",sizeBytesR/1024)+" Kbytes. Папок: 0 из "+countDirR+". Файлов 0 из "+countFilesR);
		statusL.setBorder(new EmptyBorder(0,40,0,0));
		statusR.setBorder(new EmptyBorder(0,30,0,0));
		//Добавляем Листенер в JTable
				tableL.getSelectionModel().addListSelectionListener(new ActionList());
			
				tableR.getSelectionModel().addListSelectionListener(new ActionList());
		panel20.add(statusL);
		panel20.add(statusR);
		panel21.add(path);
		panel21.add(shellCMD);
//Рисуем границы JPanel21 (метка path+shellCMD)
		panel2.setBorder(new CompoundBorder
							(new EmptyBorder(10,10,10,10),
								new LineBorder(Color.black,1)));
		panel2.add(panel20);
		panel2.add(panel21);
		panel2.add(panel22);
		panel.add(scrollLeft);
		panel.add(scrollRight);
		panel.setBorder(new CompoundBorder(new EmptyBorder(10,10,0,10),new LineBorder(Color.black,1)));
		frame.getContentPane().add(BorderLayout.NORTH,panel0);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.getContentPane().add(BorderLayout.SOUTH,panel2);
//************************************create MENU**************************************************
		menuBar=new JMenuBar();
		mFile=new JMenu("Файл");
		mSelected=new JMenu("Выделение");
		mNet=new JMenu("Сеть");
		mView=new JMenu("Вид");
		mConfig=new JMenu("Конфигурация");
		mUtil=new JMenu("Утилиты");
		mDecToBin=new JMenuItem("Числовой конвертер");
		menuHelp=new JMenu("О программе");
		mCompareTo=new JMenuItem("Сравнить с...");
		mSelectedAll=new JMenuItem("Выделить всё");
		mConnectNetDisk=new JMenuItem("Подключить сетевой диск");
		mDisConnectNetDisk=new JMenuItem("отключить сетевой диск");
		mSortByName=new JMenuItem("Соррировать по имени");
		mSortByType=new JMenuItem("Сортировать по типу");
		mSortByDate=new JMenuItem("Сортировать по дате");
		mSortBySize=new JMenuItem("Сортировать по размеру");
		mInfoSys=new JMenuItem("Инфо о системе");
		mAbout=new JMenuItem("О программе");
		mHelp=new JMenuItem("Помощь");
		mExit=new JMenuItem("Выход");
		mFile.add(mCompareTo);
		mFile.add(mExit);
		mSelected.add(mSelectedAll);
		mNet.add(mConnectNetDisk);
		mNet.add(mDisConnectNetDisk);
		mView.add(mSortByName);
		mView.add(mSortByType);
		mView.add(mSortByDate);
		mView.add(mSortBySize);
		mUtil.add(mDecToBin);
		mConfig.add(mInfoSys);
		menuHelp.add(mAbout);
		menuHelp.add(mHelp);
		menuBar.add(mFile);
		menuBar.add(mSelected);
		menuBar.add(mNet);
		menuBar.add(mView);
		menuBar.add(mUtil);
		menuBar.add(mConfig);
		menuBar.add(menuHelp);
		mDecToBin.addActionListener(new menuListener());
		mCompareTo.addActionListener(new menuListener());
		mSelectedAll.addActionListener(new menuListener());
		mConnectNetDisk.addActionListener(new menuListener());
		mDisConnectNetDisk.addActionListener(new menuListener());
		mSortByName.addActionListener(new menuListener());
		mSortByType.addActionListener(new menuListener());
		mSortByDate.addActionListener(new menuListener());
		mSortBySize.addActionListener(new menuListener());
		mInfoSys.addActionListener(new menuListener());
		mAbout.addActionListener(new menuListener());
		mHelp.addActionListener(new menuListener());
		mExit.addActionListener(new menuListener());
//************************************END MENU*****************************************************
		//frame.setSize(w,h);
		frame.setJMenuBar(menuBar);
		frame.setBounds(X,Y,w,h);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
//Метод, выводящий списки директорий и файлов в JList
private void createFSL(String paths) {//**************************************LEFT PANEL
	System.out.println(paths);
	if(dataL!=null) {
		listFileL.removeAll(listFileL);
		dataL=null;
		fsViewL=null;
		System.out.println("ЛЕВЫЙ Блок проверки на заполненность массивов сработал!!!!!!!!");
	}
	countDirL=0;
	countFilesL=0;
	sizeBytesL=0;
	
	long fSize=0;
	long fTime=0;
	String fType="unknown";
	int x=1;
	String rootFile="...";
	File rootF=new File(rootFile);
	fileLeft=new File(paths);
	listFileL.add(rootF);
	try {
		int len=fileLeft.listFiles().length;	
		fsViewL=new FileView[len+1];
		dataL=new String[len+1][4];
	}catch(Exception e) {
		e.printStackTrace();
		new error(e.toString());
		fileLeft=new File(getCutPathFile(paths));
		System.out.println("После отказа в доступе к папке - путь стал = "+fileLeft.toString());
		int len=fileLeft.listFiles().length;	
		fsViewL=new FileView[len+1];
		dataL=new String[len+1][4];
	}
		
	

//**********************************CREATE ROOT ... in ARRAYS************************************************************
	fsViewL[0]=new FileView("...","","",""); //Добавляем ROOT ...
	SimpleDateFormat dateFormat=new SimpleDateFormat();
	Date dateFile;
	String date;
	
	//Сначала формируем папки в списках
	for(File dirsAndFile:fileLeft.listFiles()) {
		fSize=dirsAndFile.length();//размер в байтах
		fTime=dirsAndFile.lastModified();// Последнее время изменения файла
		dateFile=new Date(fTime);
		date=dateFormat.format(dateFile);
		
		if(dirsAndFile.isDirectory()) {
			listFileL.add(dirsAndFile); //Полный путь к файлу
			try {
				fsViewL[x]=new FileView("["+dirsAndFile.getName()+"]","DIR","папка",String.valueOf(date));
				}catch(ArrayIndexOutOfBoundsException error) {System.out.println(error);}
			x++;
			countDirL++;
			}
		}
	//Потом формируем файлы в списках
	for(File dirsAndFile:fileLeft.listFiles()) {
		fSize=dirsAndFile.length();//размер в байтах
		fTime=dirsAndFile.lastModified();// Последнее время изменения файла
		dateFile=new Date(fTime);
		date=dateFormat.format(dateFile);
		
	if(dirsAndFile.isFile()) { 
		fType=getFileExtension(dirsAndFile);
		listFileL.add(dirsAndFile);

		fsViewL[x]=new FileView(dirsAndFile.getName(), fType,String.format("%,d",fSize),String.valueOf(date));
		x++;
		countFilesL++;
		sizeBytesL+=fSize;
		}
	}
		
	//Проверочка и заодно заполним многомерные массивы dataL и dataR
		
		for(int i=0;i<fsViewL.length;i++) {
			//System.out.println(fsViewL[i].getName()+"["+fsViewL[i].getSize()+"]bytes");
			dataL[i][0]=fsViewL[i].getName();
			dataL[i][1]=fsViewL[i].getType();
			dataL[i][2]=fsViewL[i].getSize();
			dataL[i][3]=fsViewL[i].getData();			
			
		}
}//end createFSL
private void createFSR(String paths) {//RIGHTS PANEL**********************************
	if(dataR!=null) {
		listFileR.removeAll(listFileR);
		dataR=null;
		fsViewR=null;
		System.out.println("Блок проверки на заполненность массивов сработал!!!!!!!!");
	}

	countDirR=0;
	countFilesR=0;
	sizeBytesR=0;
	long fSize=0;
	long fTime=0;
	String fType="unknown";
	int x=1;
	String rootFile="...";
	File rootF=new File(rootFile);
	fileRight=new File(paths);
	
	listFileR.add(rootF);
	try {
		int len=fileRight.listFiles().length;
		fsViewR=new FileView[len+1];
		dataR=new String[len+1][4];	
	}catch(Exception e) {
		e.printStackTrace();
		new error(e.toString());
		fileRight=new File(getCutPathFile(paths));
		System.out.println("После отказа в доступе к папке - путь стал = "+fileRight.toString());
		int len=fileRight.listFiles().length;	
		fsViewR=new FileView[len+1];
		dataR=new String[len+1][4];
	}
	
//**********************************CREATE ROOT ... in ARRAYS************************************************************
	fsViewR[0]=new FileView("...","","",""); //Добавляем ROOT ...
	SimpleDateFormat dateFormat=new SimpleDateFormat();
	Date dateFile;
	String date;
	
	//Сначала формируем папки в списках
	for(File dirsAndFile:fileRight.listFiles()) {
		fSize=dirsAndFile.length();//размер в байтах
		fTime=dirsAndFile.lastModified();// Последнее время изменения файла
		dateFile=new Date(fTime);
		date=dateFormat.format(dateFile);
		
		if(dirsAndFile.isDirectory()) {
			listFileR.add(dirsAndFile); //Полный путь к файлу
			try {
				fsViewR[x]=new FileView("["+dirsAndFile.getName()+"]","DIR","папка",String.valueOf(date));
				}catch(ArrayIndexOutOfBoundsException error) {System.out.println(error);}
			x++;
			countDirR++;
			}
		}
	//Потом формируем файлы в списках
	for(File dirsAndFile:fileRight.listFiles()) {
		fSize=dirsAndFile.length();//размер в байтах
		fTime=dirsAndFile.lastModified();// Последнее время изменения файла
		dateFile=new Date(fTime);
		date=dateFormat.format(dateFile);
		
	if(dirsAndFile.isFile()) { 
		fType=getFileExtension(dirsAndFile);
		listFileR.add(dirsAndFile);

		fsViewR[x]=new FileView(dirsAndFile.getName(), fType,String.format("%,d",fSize),String.valueOf(date));
		x++;
		countFilesR++;
		sizeBytesR+=fSize;
		}
	}
	
	System.out.println(String.format("Размер всех файлов в правой панели = "+sizeBytesL));	

	//Проверочка и заодно заполним многомерные массивы dataL и dataR
		
		for(int i=0;i<fsViewR.length;i++) {
			//System.out.println(fsViewR[i].getName()+"["+fsViewR[i].getSize()+"]bytes");
			dataR[i][0]=fsViewR[i].getName();
			dataR[i][1]=fsViewR[i].getType();
			dataR[i][2]=fsViewR[i].getSize();
			dataR[i][3]=fsViewR[i].getData();			
			
		}
}//end createFSR	
//метод вырезает часть пути, для перехода на верхний уровень ../
private String getCutPathFile(String f) {
	String path=f;
	if(path.lastIndexOf("\\")==path.length()-1 && path.lastIndexOf("\\")!=2) {
		path=path.substring(0, path.length()-1);
		System.out.println(path);
	}
		
	if(path.lastIndexOf("\\")!=-1 && path.lastIndexOf("\\")!=2){
		
			return path.substring(0,path.lastIndexOf("\\")+1);	
		
		
	}else if(path.lastIndexOf("\\")!=-1 && path.lastIndexOf("\\")==2) {
		return path.substring(0,3);
	}else return path;
}
	
//метод вырезает получает расширение файла.	
private String getFileExtension(File f) {
	String fileName=f.getName();
	if(fileName.lastIndexOf(".")!=-1 && fileName.lastIndexOf(".")!=0) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
		}else return "";
}
private class mouseListener extends MouseAdapter{
/*
void mouseClicked (MouseEvent e)Вызывается, когда кнопка мыши была нажата (нажата и отпущена) на компоненте.	
void mouseDragged (MouseEvent e)Вызывается, когда кнопка мыши нажимается на компоненте, а затем перетаскивается.	
void mouseEntered (MouseEvent e)Вызывается, когда мышь входит в компонент.	
void mouseExited (MouseEvent e)Вызывается, когда мышь выходит из компонента.	
void mouseMoved (MouseEvent e)Вызывается, когда курсор мыши был перемещен на компонент, но никакие кнопки не были нажаты.	
void mousePressed (MouseEvent e)Вызывается при нажатии кнопки мыши на компоненте.	
void mouseReleased (MouseEvent e)Вызывается, когда на компоненте отпущена кнопка мыши.	
void mouseWheelMoved (MouseWheelEvent e) Вызывается при вращении колесика мыши
*/
	public void mousePressed(MouseEvent me) {
//*******************************Килк мышки в левой панели JTable***********************************************************
		if(me.getSource()==tableL && tableL.getSelectedRow()!=-1) {
			currentTable="L";
			System.out.println("Мышка кликнула в левой панели");   
			if(me.getClickCount()==2 && tableL.getSelectedRow()!=-1) {//Если двойной клик на папке - то....
					
				action("L");
			}
//*******************************Килк мышки в правой панели JTable**********************************************************
		}//Конец для левой панели
		else if(me.getSource()==tableR && tableR.getSelectedRow()!=-1) {
			currentTable="R";
//********************************************************************************************************			
			System.out.println("Мышка кликнула в правой панели");
			if(me.getClickCount()==2 && tableR.getSelectedRow()!=-1) {//Если двойной клик на папке - то....
					
				action("R");
			}
		}//Конец блока для правой панели
	}//end mousePressed
}//end MouseAdapter
private void action(String s) {
	if(s=="L") {
	
		System.out.println("Двойной клик в левой панели");
		int index=tableL.getSelectedRow();
		System.out.println("Блок MousePressed index="+index);
//*********************************Блок кода с нажатием на  ROOT ... ЛЕВАЯ Таблица************************************************				
		if(index==0 && tableL.getValueAt(0,0)=="...") {
			
				System.out.println("Блок кода с ... УРААА!!!!");
				//******************Вырезание одного уровня из пути к файлу**************************************************************
							System.out.println("Целый путь =: "+newPathL);
							newPathL=getCutPathFile(newPathL);	
							System.out.println("Обрезаный путь =: "+newPathL);
							createFSL(newPathL);
							updateTable("L");
			

		}
		if(fsViewL[index].getType()=="DIR") {
			System.out.println("УРААА, ПАПКА!!!!");
			newPathL=listFileL.get(index).getAbsolutePath();
			System.out.println(newPathL);
			createFSL(newPathL);
			updateTable("L");
		}
	}else if(s=="R") {
		
		System.out.println("Двойной клик в правой панели");
		
		int index=tableR.getSelectedRow();
		System.out.println("Блок MousePressed index="+index);
//*********************************Блок кода с нажатием на  ROOT ...Правая таблица***********************************************				
		if(index==0 && tableR.getValueAt(0,0)=="...") {
			System.out.println("Блок кода с ... УРААА!!!!");
//******************Вырезание одного уровня из пути к файлу**************************************************************
			System.out.println("Целый путь =: "+newPathR);
			newPathR=getCutPathFile(newPathR);	
			System.out.println("Обрезаный путь =: "+newPathR);
			createFSR(newPathR);
			updateTable("R");
		}
		if(fsViewR[index].getType()=="DIR") {
			System.out.println("УРААА, ПАПКА!!!!");
			newPathR=listFileR.get(index).getAbsolutePath();
			System.out.println(newPathR);
			createFSR(newPathR);
			updateTable("R");
		}
	}//end R
}//end action()
private  void updateTable(String s) {
	if(s=="L") {
		
		System.out.print("Метод update СЛЕВА Table работает");
		deftableL.getDataVector().removeAllElements();
		tableL.removeAll();
		
		deftableL.fireTableDataChanged();
		for(int i=0;i<dataL.length;i++) deftableL.addRow(dataL[i]);
		deftableL.fireTableRowsUpdated(0, tableL.getRowCount());
		deftableL.fireTableDataChanged();
		
//Блок обработки, если файл выделен - то показывать размер файла********************************************************************************		

//**********************************************************************************************************************************************		
		statusL.setText("Выделено 0 Bytes из "+String.format("%,d",sizeBytesL/1024)+" Kbytes. Папок: 0 из "+countDirL+". Файлов 0 из "+countFilesL);
		System.out.println("длина dataL="+dataL.length);
	}else if(s=="R") {
		System.out.print("Метод update СПРАВА Table работает");
		deftableR.getDataVector().removeAllElements();
		
		deftableR.fireTableDataChanged();

		for(int i=0;i<dataR.length;i++) deftableR.addRow(dataR[i]);
		
		deftableR.fireTableDataChanged();
//Блок обработки, если файл выделен - то показывать размер файла********************************************************************************		
		
//**********************************************************************************************************************************************	
		statusR.setText("Выделено 0 Bytes из "+String.format("%,d",sizeBytesR/1024)+" Kbytes. Папок: 0 из "+countDirR+". Файлов 0 из "+countFilesR);
		System.out.println("длина dataR="+dataR.length);
	}

	
	
}
//метод для отображения пути в метке JLabel path
private class ActionList implements ListSelectionListener{
	public void valueChanged(ListSelectionEvent lse) {
		int indexL=tableL.getSelectedRow();
		int indexR=tableR.getSelectedRow();
// Блок кода по расчету значений ВЫДЕЛЕННЫХ строк***************************************************************************
		int SelectedFileL=0;//Количество выделенных файлов
		int SelectedFileR=0;
		statusLsizeFile=0;
		statusRsizeFile=0;
		int SelectedDirL=0;
		int SelectedDirR=0;
		int[] countALLR=tableR.getSelectedRows();
		int[] countAllL=tableL.getSelectedRows();
//************************Подсчет всех значений в JLabel statusL************************СЛЕВА********************************		
		for(int i=0;i<countAllL.length;i++) {
			System.out.println(countAllL[i]);
			if(listFileL.get(countAllL[i]).isFile())	{
				SelectedFileL++;
				statusLsizeFile+=listFileL.get(countAllL[i]).length();
			}
			if(listFileL.get(countAllL[i]).isDirectory()) SelectedDirL++;
		}
		System.out.println("Выделено "+SelectedDirL+" папок");
		System.out.println("Выделено "+SelectedFileL+" файлов");
		statusL.setText("Выделено "+String.format("%,d",statusLsizeFile)+" Bytes "
											+ "из "+String.format("%,d",sizeBytesL/1024)+" Kbytes. "
													+ "Папок: "+SelectedDirL+" из "+countDirL+". Файлов "+SelectedFileL+" из "+countFilesL);
//**************************************************************************************************************************	
//************************Подсчет всех значений в JLabel statusR************************СПРАВА********************************		
				for(int i=0;i<countALLR.length;i++) {
					System.out.println(countALLR[i]);
					if(listFileR.get(countALLR[i]).isFile())	{
						SelectedFileR++;
						statusRsizeFile+=listFileR.get(countALLR[i]).length();
					}
					if(listFileR.get(countALLR[i]).isDirectory()) SelectedDirR++;
				}
				System.out.println("Выделено "+SelectedDirR+" папок");
				System.out.println("Выделено "+SelectedFileR+" файлов");
		statusR.setText("Выделено "+String.format("%,d",statusRsizeFile)+" Bytes "
											+ "из "+String.format("%,d",sizeBytesR/1024)+" Kbytes. "
													+ "Папок: "+SelectedDirR+" из "+countDirR+". Файлов "+SelectedFileR+" из "+countFilesR);
//**************************************************************************************************************************	
		if(indexL!=-1 && lse.getSource()==tableL.getSelectionModel()) {		
			System.out.println("Блок Label - левый");
			if(indexL==0) {
				path.setText(getCutPathFile(newPathL));
			
			}else {
				path.setText(getCutPathFile(listFileL.get(indexL).getAbsolutePath()));	
			}
				
	}else if(indexR!=-1 && lse.getSource()==tableR.getSelectionModel()) {
//**************************************************************************************************************************			
		System.out.println("Блок Label - правый");
		if(indexR==0) {
			path.setText(getCutPathFile(newPathR));
		
		}else {
			path.setText(getCutPathFile(listFileR.get(indexR).getAbsolutePath()));	
		}
			
	}
		
			
		}//end valueChanged	
	}//end ActionList
private class ActionKey implements KeyListener{
	public void keyTyped(KeyEvent ke1) {
		
	}
	public void keyPressed(KeyEvent ke2) {
		if(ke2.getSource()==tableL) {
			//*****************Если файл выделен, то показываем его размер, если выделена директория, то показываем 0		 
			if(ke2.getKeyCode()==KeyEvent.VK_ENTER) {
				path.setText("Нажата кнопка Enter");
				action("L");
			}
		}else if(ke2.getSource()==tableR) {
			if(ke2.getKeyCode()==KeyEvent.VK_ENTER) {
				path.setText("Нажата кнопка Enter");
				action("R");
			}
		}
//*******************************Командная строка shellCMD*******************НАЖАТИЕ ENTER****************************
		if(ke2.getSource()==shellCMD && ke2.getKeyCode()==KeyEvent.VK_ENTER) {
				System.out.println("Нажат Enter  в shellCMD!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				String sh=shellCMD.getText();
				String command="cd /d \""+path.getText()+"\""+"&&"+sh;
				if(sh.equals("")) {//Если пусто - пто просто запустим CMD.EXE
					try {
						Process shell=Runtime.getRuntime().exec(new String[]{"cmd.exe"," /c","start"
								//*******************************************CMD*************************************
								//"cd",
								//"%HOMEDRIVE%" Домашний диск по умолчанию c:\
								// CMD /K "cd /d D:" - переход в диск D при запуске cmd
								//*******************************************CMD*************************************
								});
						
						System.out.println("Запуск shell. shell.info()="+shell.info());
					}catch(IOException error) {error.printStackTrace();}
				}
				if(!sh.equals("")) {//Если поле shellCMD не пустое, то передадим параметр в cmd.exe
					try {
						Process shell=Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","start",command
								
								
								//*******************************************CMD*************************************
								//"cd",
								//"%HOMEDRIVE%" Домашний диск по умолчанию c:\
								// CMD /K "cd /d D:" - переход в диск D при запуске cmd
								//*******************************************CMD*************************************
								});
						System.out.println("Запуск shell. shell.info()="+shell.info());
					}catch(IOException error) {error.printStackTrace();}
					System.out.println(command);
				}
			}

//*******************************************************************************************************************
	}
	public void keyReleased(KeyEvent ke3) {
		
	}
}
//**************************MENU ACTION LISTENER************************************
private class menuListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==mExit) System.exit(0);
		if(event.getSource()==mCompareTo) {
			int indexL=tableL.getSelectedRow();
			int indexR=tableR.getSelectedRow();
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					fileCompare obj=new fileCompare();
					obj.setFile(listFileL.get(indexL),listFileR.get(indexR));
				}
			});
			
		}
		if(event.getSource()==mInfoSys) {
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new SysInfo();
				}
			});
			
		}
		if(event.getSource()==mSelectedAll) {
			System.out.print("currentTable="+currentTable);
			if(currentTable.equals("L")){
				tableL.setRowSelectionInterval(1, dataL.length-1);
			}else if(currentTable.equals("R")) {
				tableR.setRowSelectionInterval(1, dataR.length-1);
			}
		}
		if(event.getSource()==mDecToBin) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new DigitalConverter();
				}
			});
		}
	}//end actionPerformed
}//end munuListener
//********************************Слушатель для нижних функциональных кнопок F3,F4,F5,F6,F7,F8
private class actionF implements ActionListener{ 
	public void actionPerformed(ActionEvent event) {
		Runtime runtime=Runtime.getRuntime();
		Process procF;
		if(event.getSource()==btnExit2) System.exit(0);
		if(event.getSource()==btnEdit) {
			if(currentTable.equals("L")&& listFileL.get(tableL.getSelectedRow()).isFile()) {
			try {
			procF=runtime.exec("notepad.exe "+listFileL.get(tableL.getSelectedRow()).getAbsolutePath());
			System.out.println("Запуск notepad / procF.info()="+procF.info());
			}catch(IOException err) {err.printStackTrace();}
			}
			if(currentTable.equals("R")&& listFileR.get(tableR.getSelectedRow()).isFile()) {
				try {
					procF=runtime.exec("notepad.exe "+listFileR.get(tableR.getSelectedRow()).getAbsolutePath());
					}catch(IOException err) {err.printStackTrace();}
			}
			}
//***************************Кнопка F3 View - СОБСТВЕННЫЙ ПРОСМОТРЩИК ФАЙЛОВ****************************************
		if(event.getSource()==btnView) {
			boolean error=false;
			long start=0;
			double time=0.0;
			fileViewer viewer;
			StringBuilder sb=new StringBuilder();
			int i;
			int indexL=tableL.getSelectedRow();
			int indexR=tableR.getSelectedRow();
			System.out.println("currentTable="+currentTable);
			if(currentTable.equals("L") && tableL.getSelectedRow()!=-1) {
				System.out.println("Метод actionF (btnView) - ЛЕВЫЙ БЛОК");
//************************Считывание идет БИНАРНОЕ, а не СИМВОЛЬНОЕ!!!!!!!!*******************************************************				
				try(FileInputStream fisL=new FileInputStream(listFileL.get(indexL).getAbsoluteFile());
						BufferedInputStream bisL=new BufferedInputStream(fisL))
					{
					start=System.currentTimeMillis();	
					do {
						i=bisL.read();
						if(i!=-1) sb.append((char)i);
					}while(i!=-1);
					}catch(FileNotFoundException err) {err.printStackTrace();
					//********************FileNotFoundException************ИЛИ НЕТ ДОСТУПА К ФАЙЛУ*********************************
					error=true;
					time=(double)(System.currentTimeMillis()-start)/1000.000000;
					viewer=new fileViewer();
					viewer.createText(err.toString(),time,listFileL.get(indexL).getName(),listFileL.get(indexL).length());
					//*************************************************************************************************************
					}catch(IOException err1) {err1.printStackTrace();
					}finally {if(!error) {
						time=(double)(System.currentTimeMillis()-start)/1000.000000;
						viewer=new fileViewer();
						viewer.createText(sb.toString(),time,listFileL.get(indexL).getName(),listFileL.get(indexL).length());
						System.out.print("Время выполнения: "+time+" секунд");
					}
						
					}
				}
			if(currentTable.equals("R") && tableR.getSelectedRow()!=-1) {
				//************************Считывание идет БИНАРНОЕ, а не СИМВОЛЬНОЕ!!!!!!!!****************************************
				System.out.println("Метод actionF (btnView) - ПРАВЫЙ БЛОК");
				try(FileInputStream fisR=new FileInputStream(listFileR.get(indexR).getAbsoluteFile());
						BufferedInputStream bisR=new BufferedInputStream(fisR))
					{
					start=System.currentTimeMillis();	
					do {
						i=bisR.read();
						if(i!=-1) sb.append((char)i);
					}while(i!=-1);
					}catch(FileNotFoundException err) {err.printStackTrace();
					//********************FileNotFoundException************ИЛИ НЕТ ДОСТУПА К ФАЙЛУ*********************************
					error=true;
					time=(double)(System.currentTimeMillis()-start)/1000.000000;
					viewer=new fileViewer();
					viewer.createText(err.toString(),time,listFileL.get(indexL).getName(),listFileL.get(indexL).length());
					//*************************************************************************************************************
					}catch(IOException err1) {err1.printStackTrace();
					}finally {if(!error) {
						time=(double)(System.currentTimeMillis()-start)/1000.000000;
						viewer=new fileViewer();
						viewer.createText(sb.toString(),time,listFileR.get(indexR).getName(),listFileR.get(indexR).length());
						System.out.print("Время выполнения: "+time+" секунд");
					}
						
						
					}
			}
			}//end btnView
//****************************************Кнопка Создать директорию**************************************************************
			if(event.getSource()==btnDir) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							
							new mkDir();
						}
					});
	
			}
			if(event.getSource()==btnDel) {
			
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							int[] multIndexL=tableL.getSelectedRows();
							int[] multIndexR=tableR.getSelectedRows();
						
							if(currentTable.equals("L")) {
								if(multIndexL.length==1)
									new Del(listFileL.get(multIndexL[0]),false);
								else if(multIndexL.length>1)
									new Del(listFileL.get(multIndexL[0]),true);
							}
								
							else if(currentTable.equals("R")) {
								if(multIndexR.length==1)
									new Del(listFileR.get(multIndexR[0]),false);
								else if(multIndexR.length>1) 
									new Del(listFileR.get(multIndexR[0]),true);
								
							}
								
						}
					});
				
			}
			if(event.getSource()==btnCopy) {
				if(currentTable.equals("L")) {
					int[] multiIndexL=tableL.getSelectedRows();
					File[] sourceFile=new File[multiIndexL.length];
					for(int i=0;i<multiIndexL.length;i++) {  //Формирование массива выделенных файлов для передачи в класс copyFile()
						sourceFile[i]=listFileL.get(multiIndexL[i]);
					}
					if(multiIndexL.length==1) { //Если одиночное копирование
						new copyFile(sourceFile,fileRight,false,false,multiIndexL);
					}
					else if(multiIndexL.length>1) {//Множественное копирование
						new copyFile(sourceFile,fileRight,false,true,multiIndexL);
					}
					
					
				}
				else if(currentTable.equals("R")) {//Если одиночное копирование
					int[] multiIndexR=tableR.getSelectedRows();
					File[] sourceFile=new File[multiIndexR.length];
					for(int i=0;i<multiIndexR.length;i++) {  //Формирование массива выделенных файлов для передачи в класс copyFile()
						sourceFile[i]=listFileR.get(multiIndexR[i]);
					}
					if(multiIndexR.length==1) {
						new copyFile(sourceFile,fileLeft,false,false,multiIndexR);
					}
					else if(multiIndexR.length>1) {//Множественное копирование
						new copyFile(sourceFile,fileLeft,false,true,multiIndexR);
					}
					
					
				}
			}
			if(event.getSource()==btnCut) {
				if(currentTable.equals("L")) {
					int[] multiIndexL=tableL.getSelectedRows();
					File[] sourceFile=new File[multiIndexL.length];
					for(int i=0;i<multiIndexL.length;i++) {  //Формирование массива выделенных файлов для передачи в класс copyFile()
						sourceFile[i]=listFileL.get(multiIndexL[i]);
					}
					if(multiIndexL.length==1) { //Одиночное перемещение файла
						new copyFile(sourceFile,fileRight,true,false,multiIndexL);	
					}
					else if(multiIndexL.length>1) {
						new copyFile(sourceFile,fileRight,true,true,multiIndexL);	
					}
					
				}
				else if(currentTable.equals("R")) {
					int[] multiIndexR=tableR.getSelectedRows();
					File[] sourceFile=new File[multiIndexR.length];
					for(int i=0;i<multiIndexR.length;i++) {  //Формирование массива выделенных файлов для передачи в класс copyFile()
						sourceFile[i]=listFileR.get(multiIndexR[i]);
					}
					if(multiIndexR.length==1) { //Одиночное перемещение файла
						new copyFile(sourceFile,fileLeft,true,false,multiIndexR);	
					}
					else if(multiIndexR.length>1) {
						new copyFile(sourceFile,fileLeft,true,true,multiIndexR);	
					}
				}
			}//end Cut
		}//end actionPerformed
	}//end actionF
public static void endCopy() {
	if(currentTable.equals("L")) {
		obj.createFSR(obj.newPathR);
		obj.updateTable("R");
	}
	if(currentTable.equals("R")) {
		obj.createFSL(obj.newPathL);
		obj.updateTable("L");
	}
}
public static void endCut(int[] index) {//тут будет дополнительная проверка на удаление
	int[] indexSelected=index;
	
	if(currentTable.equals("L")) {
		for(int i=0;i<indexSelected.length;i++) {
		//int index=obj.tableL.getSelectedRow();
		try {
			obj.listFileL.get(indexSelected[i]).delete();
			System.out.println("endCut() удаляется файл слева "+obj.listFileL.get(indexSelected[i]).getAbsolutePath());	
		}catch(SecurityException errL) {
			new error(errL.toString());
			}
		}
	}
	else if(currentTable.equals("R")) {	
		for(int i=0;i<indexSelected.length;i++) {
		try {
			obj.listFileR.get(indexSelected[i]).delete();	
			System.out.println("endCut() удаляется файл справа "+obj.listFileR.get(indexSelected[i]).getAbsolutePath());	
		}catch(SecurityException errR) {
			new error(errR.toString());
			}
		
	}			
	
	}
	obj.createFSL(obj.newPathL);
	obj.createFSR(obj.newPathR);
	obj.updateTable("L");
	obj.updateTable("R");
}
public static void deleteDirOrFile(boolean deleteYES,boolean delMulti) {
	if(deleteYES && !delMulti) {
		if(fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {
			int indexL=obj.tableL.getSelectedRow();
			int indexR=obj.tableR.getSelectedRow();
			if(currentTable.equals("L"))
				obj.listFileL.get(indexL).delete();
			else if(currentTable.equals("R"))
				obj.listFileR.get(indexR).delete();
			obj.createFSL(obj.newPathL);
			obj.createFSR(obj.newPathR);
			obj.updateTable("R");
			obj.updateTable("L");
		}
		if(currentTable.equals("L") && !fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {
			int index=obj.tableL.getSelectedRow();
			obj.listFileL.get(index).delete();
			obj.createFSL(obj.newPathL);
			obj.updateTable("L");
		}
		if(currentTable.equals("R") && !fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {
			int index=obj.tableR.getSelectedRow();
			obj.listFileR.get(index).delete();
			obj.createFSR(obj.newPathR);
			obj.updateTable("R");
		}
	}
	if(deleteYES && delMulti) { //Множественное удаление!!!!!!!!!!!!!!!!!!!!!
		if(fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) { //Правило №1
			int[] MultIndexL=obj.tableL.getSelectedRows();
			int[] MultIndexR=obj.tableR.getSelectedRows();
			if(currentTable.equals("L")) {
				for(int i=0;i<MultIndexL.length;i++) {
					System.out.println("Правило №1 удаление файла "+obj.listFileL.get(MultIndexL[i]).getName());
					obj.listFileL.get(MultIndexL[i]).delete();
				}
			}else if(currentTable.equals("R")) {
				for(int i=0;i<MultIndexR.length;i++) {
					System.out.println("Правило №1 удаление файла "+obj.listFileR.get(MultIndexR[i]).getName());
					obj.listFileR.get(MultIndexR[i]).delete();
					}
			}
				
			obj.createFSL(obj.newPathL);
			obj.createFSR(obj.newPathR);
			obj.updateTable("R");
			obj.updateTable("L");
		}
		if(currentTable.equals("L") && !fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {//Правило №2
			int[] MultIndexL=obj.tableL.getSelectedRows();
			for(int i=0;i<MultIndexL.length;i++) {
				System.out.println("Правило №2 удаление файла "+obj.listFileL.get(MultIndexL[i]).getName());
				obj.listFileL.get(MultIndexL[i]).delete();
			}
					
			obj.createFSL(obj.newPathL);
			obj.updateTable("L");
		}
		if(currentTable.equals("R") && !fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {//правило №3
			int[] MultIndexR=obj.tableR.getSelectedRows();
			for(int i=0;i<MultIndexR.length;i++) {
				System.out.println("Правило №3 удаление файла "+obj.listFileR.get(MultIndexR[i]).getName());
				obj.listFileR.get(MultIndexR[i]).delete();
			}
				
			obj.createFSR(obj.newPathR);
			obj.updateTable("R");
		}
	}
}//end deleteDirOrFile
public static void makeNewDir(String s) {
	newDir=s;
	String newPathDir;
	
	newPathDir=obj.path.getText()+newDir;
	System.out.print("mkDir path="+newPathDir);
	File f=new File(newPathDir);
	f.mkdir();
		if(fileLeft.getAbsolutePath().equals(fileRight.getAbsolutePath())) {
			obj.createFSL(obj.getCutPathFile(newPathDir));
			obj.createFSR(obj.getCutPathFile(newPathDir));
			obj.updateTable("R");
			obj.updateTable("L");
		}
		if(currentTable.equals("L")) {
			obj.createFSL(obj.getCutPathFile(newPathDir));
			obj.updateTable("L");
			
		}
		if(currentTable.equals("R")) {
			obj.createFSR(obj.getCutPathFile(newPathDir));
			obj.updateTable("R");
		}
				
}
private class comboBoxListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		diskButtonL.setEnabled(false);
		diskButtonR.setEnabled(false);
		if(event.getSource()==diskListL) {//Если задействован левый JComboBox	
			int index=diskListL.getSelectedIndex();	
		
			if(index==-1) index=0;//Возникает при удалении diskListL.removaAllItem()
			if(dList[index][2].equals("Сетевой диск")) diskButtonL.setEnabled(true);
			currentPathL=dList[index][0].substring(0,2);
			System.out.println(currentPathL);
			File disk=new File(currentPathL);
			diskButtonL.setText("Close "+dList[index][0]);
			diskInfoL.setText(dList[index][2]+" "+dList[index][0]+"  (свободно "
					+ ""+String.format("%,d", disk.getFreeSpace()/1024)+" "
							+ "kBytes из "+String.format("%,d", disk.getTotalSpace()/1024)+" "
									+ "kBytes)");
			createFSL(currentPathL+"/");
			updateTable("L");
		}
		if(event.getSource()==diskListR) {//Если задействован левый JComboBox
			int index=diskListR.getSelectedIndex();
		
			if(index==-1) index=0;//Возникает при удалении diskListL.removaAllItem()
			if(dList[index][2].equals("Сетевой диск")) diskButtonR.setEnabled(true);
			currentPathR=dList[index][0].substring(0,2);
			File disk=new File(currentPathR);
			diskButtonR.setText("Close "+dList[index][0]);
			diskInfoR.setText(dList[index][2]+" "+dList[index][0]+"  "
					+ "(свободно "+String.format("%,d", disk.getFreeSpace()/1024)+" "
							+ "kBytes из "+String.format("%,d", disk.getTotalSpace()/1024)+" "
									+ "kBytes)");
			System.out.println(currentPathL);
			createFSR(currentPathR+"/"); //Очень ВАЖНЫй КОД - обновления дисковой информации
			updateTable("R");
		}
	}
}
private void updateComboBoxConnect() {
	messages msg=new messages("Идет подключение сетевого диска. Ждите...");
	System.out.println("Текущий поток в updateComboBoxConnect() messages :"+Thread.currentThread().getName());
	Thread thrdConnect=new Thread(new Runnable() { //поток по обработке message 
		public void run() {
			int x=0;
			int count=0;
			int count2=1;
			int count3=2;
			int count4=3;
			int count5=4;
			int count6=5;
			int count7=6;
			String[] con= {
					"Connecting ......|......0F9FF53A34D201",
					"Connecting ....../......FF01245504D100",
					"Connecting ......--.....55540680D0FFF6",
					"Connecting ......\\......2301B7B8B9FF33",
					"Connecting ......|......A7A8D1D2B0101F",
					"Connecting ....../.....23A3B7F8BB09C1",
					"Connecting ......--....8BA2B9FF0105CA",
					"Connecting ......\\......8A4F2B00F0F5FC"};
			
			while(dList.length==diskListL.getItemCount() && x<=80) {
				if(x<10)msg.msg(con[count]);
				if(x>10 & x<20)msg.msg(con[count]+"\n"+con[count2]);
				if(x>20 & x<30)msg.msg(con[count]+"\n"+con[count2]+"\n"+con[count3]);
				if(x>30 & x<40)msg.msg(con[count]+"\n"+con[count2]+"\n"+con[count3]+"\n"+con[count4]);
				if(x>40 & x<50)msg.msg(con[count]+"\n"+con[count2]+"\n"+con[count3]+"\n"+con[count4]+"\n"+con[count5]);
				if(x>50 & x<60)msg.msg(con[count]+"\n"+con[count2]+"\n"+con[count3]+"\n"+con[count4]+"\n"+con[count5]+"\n"+con[count6]);
				if(x>60 & x<80)msg.msg(con[count]+"\n"+con[count2]+"\n"+con[count3]+"\n"+con[count4]+"\n"+con[count5]+"\n"+con[count6]+"\n"+con[count7]);
										objDisk=null;
										objDisk=new DiskList();
										dList=null;
										dList=objDisk.getDiskInfo(); //Обращаемся к классу DiskList и вызываем Геттер
										try {
											Thread.sleep(300);
										}catch(InterruptedException e) {e.printStackTrace();}
										x++;
										if(count<7)count++;else count=0;
										if(count2<7) count2++;else count2=0;
										if(count3<7) count3++;else count3=0;
										if(count4<7) count4++;else count4=0;
										if(count5<7) count5++;else count5=0;
										if(count6<7) count6++;else count6=0;
										if(count7<7) count7++;else count7=0;
					
									}
			if(dList.length==diskListL.getItemCount()) { //Если Соединение не удалось, то количество дисков не изменилось, значит выводим ошибку
				msg.disposeMessages();
				new error("Ошибка подключения сетевого диска. Проверьте IP адрес, логин, пароль и др. параметры.");
			}
			else if(dList.length!=diskListL.getItemCount()){ //Формирование нового JComboBox
					diskListL.removeAllItems();
					diskListR.removeAllItems();
					for(int i=0;i<dList.length;i++) {
						diskListL.addItem(dList[i][1]);
						diskListR.addItem(dList[i][1]);
						System.out.println(diskListL.getItemAt(i));
						}	
					diskListL.revalidate();diskListR.revalidate();
					diskListL.repaint();diskListR.repaint();
					msg.disposeMessages();
			}	
			
		}
	});
	thrdConnect.start();
}//end updateComboBoxConnect()
private void updateComboBoxDelete() {
	messages msg=new messages("Идет закрытие сетевого диска. Ждите");

	while(dList.length==diskListL.getItemCount()) {
		System.out.println("Размер dList="+dList.length+"|Размер diskListL="+diskListL.getItemCount());
		objDisk=null;
		objDisk=new DiskList();
		dList=null;
		try {
			Thread.sleep(100);
		}catch(Exception e) {new error(e.toString());}
		dList=objDisk.getDiskInfo(); //Обращаемся к классу DiskList и вызываем Геттер
		
	}
		diskListL.removeAllItems();
		diskListR.removeAllItems();
		for(int i=0;i<dList.length;i++) {
			diskListL.addItem(dList[i][1]);
			diskListR.addItem(dList[i][1]);
			System.out.println(diskListL.getItemAt(i));
			}	
		diskListL.revalidate();diskListR.revalidate();
		diskListL.repaint();diskListR.repaint();
		msg.disposeMessages();
	
	}
private class NET implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==connect) {
			System.out.println("Работает блок кода ActionKey->JButton.connect");
			String netDisk="X:";
			String ip=ipAdress.getText();
			String share=shareName.getText();
			String user=userNameText.getText();
			char[] pass=passwordText.getPassword();
			String passString="";
			for(int i=0;i<pass.length;i++) passString+=pass[i];//Заполняем текстом пароль
			String command="net use "+netDisk+" \\\\"+ ip+"\\"+share+" /user:"+user+" "+passString;
			System.out.println(command);
			try {
				Process connectNetDisk=Runtime.getRuntime().exec(command);
				System.out.println("Process connectNetDisk.info()="+connectNetDisk.info().toString());
				Thread.sleep(200);
				System.out.println("Текущий поток в NET() connect :"+Thread.currentThread().getName());
				updateComboBoxConnect();
						
						

//Формируем новый массив дисков, создав новый экземпляр класса DiskList				
			}catch(SecurityException se) {new error(se.toString()); se.printStackTrace();
			}catch(IOException ioe) {new error(ioe.toString());ioe.printStackTrace();
			}catch(NullPointerException npe) {new error(npe.toString());npe.printStackTrace();
			}catch(IllegalArgumentException iae) {new error(iae.toString());iae.printStackTrace();
			}catch(Exception err) {new error(err.toString());err.printStackTrace();
		}
		}
		if(event.getSource()==diskButtonL) {
			int index=diskListL.getSelectedIndex();
			String str=dList[index][0].substring(0,2);
			System.out.println("Имя диска для net use /delete = "+str);
			String command="net use "+str+" /delete";
			try {
				Process del=Runtime.getRuntime().exec(command);
				System.out.println("Process del.info()="+del.info());
				Thread.sleep(200);
				updateComboBoxDelete();
			}catch(Exception e) {
				e.printStackTrace();
				new error(e.toString());
			}
			
		}
		if(event.getSource()==diskButtonR) {
			int index=diskListR.getSelectedIndex();
			String str=dList[index][0].substring(0,2);
			System.out.println("Имя диска  для net use /delete = "+str);
			String command="net use "+str+" /delete";
			try {
				Process del=Runtime.getRuntime().exec(command);
				System.out.println("Process del.info()="+del.info());
				Thread.sleep(200);
			}catch(Exception e) {
				e.printStackTrace();
				new error(e.toString());
			}
			updateComboBoxDelete();
		}

	}//end actionPerformed
}//end NET class
}//end class
