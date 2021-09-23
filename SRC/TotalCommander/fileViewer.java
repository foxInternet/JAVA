package TotalCommander;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;

enum CODE {TEXT,BIN,HEX,ASCII,WIN1251,WIN1252,KOI8R,UTF16,UTF32,IBM866,IBM855,MAC}

public class fileViewer {
	private static final Charset CS_UTF8=Charset.forName("UTF-8");
	private static final Charset CS_1251=Charset.forName("windows-1251");
	private static final Charset CS_1252=Charset.forName("windows-1252");
	private static final Charset CS_KOI8R=Charset.forName("KOI8-R");
	private static final Charset CS_ASCII=Charset.forName("US-ASCII");
	private static final Charset CS_UTF16=Charset.forName("UTF-16");
	private static final Charset CS_UTF32=Charset.forName("UTF-32");
	private static final Charset CS_IBM866=Charset.forName("IBM866"); //MS-DOS Ru
	private static final Charset CS_IBM855=Charset.forName("IBM855"); //IBM Cyrillic
	private static final Charset CS_MAC=Charset.forName("x-MacCyrillic");//Macintosh Cyrillic
	private Dimension screen;
	private JFrame frame;
	private JTextArea text;
	private JButton btnExit;
	private JScrollPane scroll;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel20;
	private JPanel panel21;
	private JPanel panel0;
	private JPanel panelX;
	private JLabel label;
	private double time;
	private long size;
	private String name;
	private JLabel codeLabel;
	private JButton codeText;
	private JButton codeBin;
	private JButton codeHex;
	private JButton codeUTF16;
	private JButton codeUTF32;
	private JButton codeIBM866;
	private JButton codeIBM855;
	private JButton codeMAC;
	private JButton codeASCII;
	private JButton codeWin1251;
	private JButton codeWin1252;
	private JButton codeKOI8R;
	private String originalText;
	private String currentCode="TEXT";
	
	fileViewer(){
		@SuppressWarnings("unused")
		CODE codeConstant;
		
		
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x1=(int)(screen.getWidth()-200);
		int y1=(int)(screen.getHeight()-100);
		int x2=(int)((screen.getWidth()-x1)/2);
		int y2=(int)((screen.getHeight()-y1)/2);
		frame=new JFrame("fileF0X  Просмотрщик файлов.    Вер.1.01-22.06.21");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel1=new JPanel();
		btnExit=new JButton("копировать в буфер");
		panel1.setBorder(new CompoundBorder
				(new EmptyBorder(10,10,10,10),new LineBorder(Color.black,1)));
		text=new JTextArea(100,30);
		text.setBounds(x2,y2,x1-100,y1);
		text.setEditable(false);
		text.setLineWrap(true);//Перено строк
		panel1.add(text);
		scroll=new JScrollPane(panel1);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel20=new JPanel();
		panel21=new JPanel();
		label=new JLabel("Идёт бинарное считывание файла...");
		panel20.add(label);
		panel21.add(btnExit);
		panel2.add(panel20);
		panel2.add(panel21);
//Верхние блок кнопок***************************************************************
		panelX=new JPanel();
		panelX.setLayout(new BoxLayout(panelX,BoxLayout.Y_AXIS));
		codeLabel=new JLabel("Символьная кодировка");
		codeLabel.setFont(new Font("arial",Font.BOLD,18));
		panel0=new JPanel();
		
		panel0.add(codeLabel);
		panel1=new JPanel(new GridLayout(1,8));
		codeText=new JButton("TEXT/UTF-8");
		codeBin=new JButton("BIN");
		codeHex=new JButton("HEX");
		codeUTF16=new JButton("TEXT/UTF-16");
		
		codeUTF32=new JButton("TEXT/UTF-32");
		codeIBM866=new JButton("IBM866");
		codeIBM855=new JButton("IBM855");
		codeMAC=new JButton("Mac Cyr");
		
		
		codeASCII=new JButton("ASCII(DOS)");
		codeWin1251=new JButton("Win1251");
		codeWin1252=new JButton("Win1252");
		codeKOI8R=new JButton("KOI8-R");
		codeText.addActionListener(new decodeListener());
		codeBin.addActionListener(new decodeListener());
		codeHex.addActionListener(new decodeListener());
		codeUTF16.addActionListener(new decodeListener());
		codeUTF32.addActionListener(new decodeListener());
		codeIBM866.addActionListener(new decodeListener());
		codeIBM855.addActionListener(new decodeListener());
		codeMAC.addActionListener(new decodeListener());
		codeASCII.addActionListener(new decodeListener());
		codeWin1251.addActionListener(new decodeListener());
		codeWin1252.addActionListener(new decodeListener());
		codeKOI8R.addActionListener(new decodeListener());
		panel1.add(codeText);
		panel1.add(codeBin);
		panel1.add(codeHex);
		panel1.add(codeUTF16);
		panel1.add(codeUTF32);
		panel1.add(codeIBM866);
		panel1.add(codeIBM855);
		panel1.add(codeMAC);
		
		panel1.add(codeASCII);
		panel1.add(codeWin1251);
		panel1.add(codeWin1252);
		panel1.add(codeKOI8R);
		panelX.add(panel0);
		panelX.add(panel1);
//**********************************************************************************
		frame.getContentPane().add(BorderLayout.NORTH,panelX);
		frame.getContentPane().add(BorderLayout.CENTER,scroll);
		frame.getContentPane().add(BorderLayout.SOUTH,panel2);
		frame.setBounds(x2, y2, x1, y1);
		frame.setVisible(true);	
	}
	void createText(String s,double t,String n,long sizeFile){
			time=t;
			name=n;
			size=sizeFile;
			text.append(s);
			originalText=s;
			label.setText("Время двоичного считывания файла: "+time+" сек..    Размер файла "+name+" "+String.format("%,d",size)+" байт");
		}
private class decodeListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==codeBin && currentCode!=CODE.BIN.toString()) {
			text.setText(StrToBin(originalText));
			currentCode=CODE.BIN.toString();
		}
		if(event.getSource()==codeText) {
			text.setText(originalText);
			currentCode=CODE.TEXT.toString();
		}
		if(event.getSource()==codeHex && currentCode!=CODE.HEX.toString()) {
			text.setText(StrToHex(originalText));
			currentCode=CODE.HEX.toString();
		}
		if(event.getSource()==codeWin1251 && currentCode!=CODE.WIN1251.toString()) {
			text.setText(StrToW1251(originalText));
			currentCode=CODE.WIN1251.toString();
		}
		if(event.getSource()==codeWin1252 && currentCode!=CODE.WIN1252.toString()) {
			text.setText(StrToW1252(originalText));
			currentCode=CODE.WIN1252.toString();
		}
		if(event.getSource()==codeUTF16 && currentCode!=CODE.UTF16.toString()) {
			text.setText(StrToUTF16(originalText));
			currentCode=CODE.UTF16.toString();
		}
		if(event.getSource()==codeUTF32 && currentCode!=CODE.UTF32.toString()) {
			text.setText(StrToUTF32(originalText));
			currentCode=CODE.UTF32.toString();
		}
		if(event.getSource()==codeIBM866 && currentCode!=CODE.IBM866.toString()) {
			text.setText(StrToIBM866(originalText));
			currentCode=CODE.IBM866.toString();
		}
		if(event.getSource()==codeIBM855 && currentCode!=CODE.IBM855.toString()) {
			text.setText(StrToIBM855(originalText));
			currentCode=CODE.IBM855.toString();
		}
		if(event.getSource()==codeMAC && currentCode!=CODE.MAC.toString()) {
			text.setText(StrToMAC(originalText));
			currentCode=CODE.MAC.toString();
		}
		if(event.getSource()==codeKOI8R && currentCode!=CODE.KOI8R.toString()) {
			text.setText(StrToKOI8R(originalText));
			currentCode=CODE.KOI8R.toString();
		}
		if(event.getSource()==codeASCII && currentCode!=CODE.ASCII.toString()) {
			text.setText(StrToASCII(originalText));
			currentCode=CODE.ASCII.toString();
		}
		
	}//end actionPerformed
}//end listener
private String StrToBin(String s) {

	byte[] bytes=s.getBytes(CS_UTF8);
	StringBuilder binary=new StringBuilder();
	for(byte b:bytes) {
		int value=b;
		for(int i=0;i<8;i++) {
			binary.append((value & 128)==0?0:1);
				value<<=1;
		}
		binary.append(' ');
	}
	return binary.toString();
}
private String StrToHex(String s) {	
	StringBuilder strHEX=new StringBuilder();
	byte bytes[]=s.getBytes(CS_UTF8);
	for(byte hex:bytes) {
		strHEX.append("0x"+String.format("%x",hex)+" ");
	}
	return strHEX.toString();	
	}
private String StrToW1251(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String code1251=new String(bytes,CS_1251);
	return code1251;
}
private String StrToW1252(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String code1252=new String(bytes,CS_1252);
	return code1252;
}
private String StrToUTF16(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeutf16=new String(bytes,CS_UTF16);
	return codeutf16;
}
private String StrToUTF32(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeutf32=new String(bytes,CS_UTF32);
	return codeutf32;
}
private String StrToIBM866(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeIBM866=new String(bytes,CS_IBM866);
	return codeIBM866;
}
private String StrToIBM855(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeIBM855=new String(bytes,CS_IBM855);
	return codeIBM855;
}
private String StrToMAC(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeMAC=new String(bytes,CS_MAC);
	return codeMAC;
}
private String StrToKOI8R(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeKOI8R=new String(bytes,CS_KOI8R);
	return codeKOI8R;
}
private String StrToASCII(String s) {
	byte bytes[]=s.getBytes(CS_UTF8);
	String codeASCII=new String(bytes,CS_ASCII);
	return codeASCII;
}
}//end class
