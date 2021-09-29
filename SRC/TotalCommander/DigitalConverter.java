package TotalCommander;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigInteger;

public class DigitalConverter {
	private JFrame frame;
	private JTextField input;
	private JLabel labelInput;
	private JTextArea BIN;
	private JLabel labelBIN;
	private JTextArea HEX;
	private JLabel labelHEX;
	private JTextArea OCT;
	private JLabel labelOCT;
	private JTextArea UNICODE;
	private JLabel labelUNICODE;
	private Dimension screen;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panelHEX;
	private JPanel panelBIN;
	private JPanel panelOCT;
	private JPanel panelUNICODE;
	private JButton convert;
	private JButton btnExit;
	private Font fontLabel;
	private Font fontDigit;
	
	DigitalConverter(){
		fontLabel=new Font("arial",Font.BOLD,14);
		fontDigit=new Font("arial",Font.BOLD,16);
		
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x1=900;
		int y1=500;
		int x=(int)screen.getWidth();
		int y=(int)screen.getHeight();
		int x0=(int)(x-x1)/2;
		int y0=(int)(y-y1)/2;
		frame=new JFrame("Конвертер чисел.Возможна работа даже с числами BigInteger(128 бит и выше)    (вер.1.00-10.07.21)");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		convert=new JButton("Конвертировать");
		convert.addActionListener((e)->{
			BIN.setText("");OCT.setText("");
			HEX.setText("");UNICODE.setText("");
			convert(input.getText());
		}
								);
		btnExit=new JButton("Выход");
		btnExit.addActionListener((event)->{
			frame.dispose();
			frame.setVisible(false);
		});
		panel3=new JPanel();
		panel3.add(btnExit);
		
		panel=new JPanel();
		labelInput=new JLabel("Введите число или символ(UNICODE)");
		labelInput.setFont(fontLabel);
		input=new JTextField(16);
		input.setFont(fontDigit);
		panel.add(labelInput);
		panel.add(input);
		panel.add(convert);
		panel2=new JPanel(new GridLayout(2,2));
		labelBIN=new JLabel("Binary code");labelBIN.setFont(fontLabel);
		labelHEX=new JLabel("Hex code");labelHEX.setFont(fontLabel);
		labelOCT=new JLabel("Oct code");labelOCT.setFont(fontLabel);
		labelUNICODE=new JLabel("Unicode code");labelUNICODE.setFont(fontLabel);
		
		BIN=new JTextArea(7,32);BIN.setForeground(Color.green);BIN.setBackground(Color.black);
		HEX=new JTextArea(7,32);HEX.setForeground(Color.green);HEX.setBackground(Color.black);
		OCT=new JTextArea(7,32);OCT.setForeground(Color.green);OCT.setBackground(Color.black);
		UNICODE=new JTextArea(7,32);UNICODE.setForeground(Color.green);UNICODE.setBackground(Color.black);
		BIN.setLineWrap(true);BIN.setFont(new Font("arial",Font.BOLD,15));
		HEX.setLineWrap(true);HEX.setFont(new Font("arial",Font.BOLD,15));
		OCT.setLineWrap(true);OCT.setFont(new Font("arial",Font.BOLD,15));
		UNICODE.setLineWrap(true);UNICODE.setFont(new Font("arial",Font.BOLD,15));
		
		BIN.setEditable(false);
		HEX.setEditable(false);
		OCT.setEditable(false);
		UNICODE.setEditable(false);
		
		panelBIN=new JPanel();
		panelBIN.add(labelBIN);
		panelBIN.add(BIN);
		panelBIN.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new LineBorder(Color.gray,1)));
		
		panelHEX=new JPanel();
		panelHEX.add(labelHEX);
		panelHEX.add(HEX);
		panelHEX.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new LineBorder(Color.gray,1)));
		
		panelOCT=new JPanel();
		panelOCT.add(labelOCT);
		panelOCT.add(OCT);
		panelOCT.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new LineBorder(Color.gray,1)));
		
		panelUNICODE=new JPanel();
		panelUNICODE.add(labelUNICODE);
		panelUNICODE.add(UNICODE);
		panelUNICODE.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new LineBorder(Color.gray,1)));
		
		
		panel2.add(panelBIN);
		panel2.add(panelHEX);
		panel2.add(panelOCT);
		panel2.add(panelUNICODE);
		panel2.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(Color.BLACK,1)));
		frame.getContentPane().add(BorderLayout.NORTH,panel);
		frame.getContentPane().add(BorderLayout.CENTER,panel2);
		frame.getContentPane().add(BorderLayout.SOUTH,panel3);
		frame.setVisible(true);
		frame.setBounds(x0, y0, x1, y1);
		frame.setResizable(false);
	}
	private void convert(String s){
		String str=s;
		long val=-0;
		
		try {
			val=Long.parseLong(str);
			System.out.println("Long value="+val+"|Long.MAX="+Long.MAX_VALUE);
			binConvert(val);
		}catch(NumberFormatException e) {
//Если число не Long, то пробуем его на совместимость с BigInteger!!!!!!!!!!!!!!!!!!!!!!!!
			try {
				BigInteger b=new BigInteger(str);
				BIGINTEGER(b);
			}catch(NumberFormatException err2) {
//Если строка не Long и не BigInteger - то это символьная строка!!!!!!!!!!!!!!!!!!!!!!!!!!
				err2.printStackTrace();
				charConvert(str);
			}
			
		}
	}//end convert
	private void charConvert(String s) {
		String chars=s;
		String strLongByte="";
		Long byteArray;
		byte[] ch=chars.getBytes();
		for(int i=0;i<ch.length;i++) {
			UNICODE.append(String.valueOf(ch[i]));
			strLongByte+=String.valueOf(ch[i]);
		}
		try {
			byteArray=Long.parseLong(strLongByte);
			binConvert(byteArray);
		}catch(NumberFormatException e) {
			BIN.setText("Код Unicode более 64 Bits <не входит в диапазон -+9,223,372,036,854,775,808>\n");
			HEX.setText("Код Unicode более 64 Bits <не входит в диапазон -+9,223,372,036,854,775,808>\n");
			OCT.setText("Код Unicode более 64 Bits <не входит в диапазон -+9,223,372,036,854,775,808>\n");
		//Если код UNICODE не влезет в Long, то помещаем в BIGINTEGER(BigInteger b);!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			try {
				BIGINTEGER(new BigInteger(strLongByte));
				}catch(NumberFormatException err) {
					err.printStackTrace();
				}
			}
			
		
		
	}
// ВАЖНО!!!!!!!!!!!!!!!!!!!!!!!
//с числами, которые не превышают long , можно сделать конвертаицю еще и так
//b.toString(2); //binary
//b.toString(8); //octal
//b.toString(10); //decimal
//b.toString(16); //hexadecimal
//b.toString("X"); -ДЛЯ BigInteger
	
	private void BIGINTEGER(BigInteger big) { //ДЛЯ ОООЧЕНЬ БОЛЬШИХ ЧИСЕЛ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! БОЛЕЕ ЧЕМ 64 БИТА!!!!!!!!!!!!!!!!!!!!!!!!
		BIN.append("Это число 128 Bit и выше: \nДесятичное число:"+String.format("%,d", big)+"\n");
		HEX.append("Это число 128 Bit и выше: ");
		OCT.append("Это число 128 Bit и выше: ");
		
		BigInteger maskLong = new BigInteger("1");
		System.out.println(big);
		//BigInteger valueBig=BigInteger.valueOf(Long.parseLong(s));  Это не прокатит, т.к. число выходит за Long.parseLong
		//А вот это прокатит!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		BigInteger valueBig=big;
		HEX.append("\n"+big.toString(16).toUpperCase());
		OCT.append("\n"+big.toString(8).toUpperCase());
		int spacer=0;
		for(int i=127;i>=0;i--) {
			spacer++;
			maskLong=maskLong.shiftLeft(i);
			System.out.println("maskLong.ONE.shiftLeft(i)="+maskLong);
//Тут ПРОБЛЕМА!!!!!!!!!!!!!! ИДЕТ побитовая операция И(&) в пределах Long, а не BigInteger
			//if((valueBig.longValue() & maskLong.longValue())==0) BIN.append("0");
			if(valueBig.and(maskLong).equals(BigInteger.ZERO)) BIN.append("0");
				else BIN.append("1");
			if((spacer & 8)!=0)	{
				BIN.append(" ");
				spacer=0;
			}
			maskLong = new BigInteger("1");
		}//end for
	}
	private void binConvert(long longBin) { //если введенное значение типа Long..., то...
		long value=longBin;
		int BITS=0;
//Определение битности числа и вывод по разрядам (8,16,32 разрядное число)
		if(value>=-128 & value<=127) BITS=8;                          //Число  8 битное
		else if(value>=-32_768 & value<=32_767) BITS=16;	          //Число 16 битное
		else if(value>=-8_388_608 & value<=8_388_607) BITS=24;        //Число 24 битное
		else if(value>=-2_147_483_648 & value<=2_147_483_647) BITS=32;//Число 32 битное 
		else if(value>=Long.MIN_VALUE & value<=Long.MAX_VALUE) {
			BITS=64; //Число 64 битное
			System.out.println("BITS="+BITS);
			System.out.println("Bits shift 1L<<63="+(long)(1L<<63));
		}			
//BIN converting
		if(BITS==64) {
			BigInteger maskLong = new BigInteger("1");
			BigInteger valueBig=BigInteger.valueOf(value);
			// Тут происходит МАГИЯ. Мы не можем сделать mask<<=63, т.к. получим -9223372036854775808
			//а не положительное число +9223372036854775807
			int spacer=0;
			for(int i=63;i>=0;i--) {
				spacer++;
				maskLong=maskLong.shiftLeft(i);
				System.out.println("maskLong.ONE.shiftLeft(i)="+maskLong);
				if((valueBig.longValue() & maskLong.longValue())==0) BIN.append("0");
					else BIN.append("1");
				if((spacer & 8)!=0)	{
					BIN.append(" ");
					spacer=0;
				}
				maskLong = new BigInteger("1");
			}//end for
		}//end if BITS==64
		else
			{   //Сдвиг для 8,16,24,32 битных чисел
			long mask=1;
			mask<<=31;
			int spacer=0;
			for(;mask>0;mask>>>=1) {
				spacer++;
				if((value & mask)==0) BIN.append("0");
					else BIN.append("1");
				if((spacer & 8)!=0) {
					BIN.append(" ");
					spacer=0;
				}
			}//end for
		}//end if BITS!=64
		
		System.out.println("BIN.getText()="+BIN.getText());
		if(BITS==8) {
			BIN.append("\nЧисло 8 битное!<диапазон -128 +127>\nBIN 8bits: "+
					BIN.getText().substring(26,31)+" "+BIN.getText().substring(31));
			OCT.append(Long.toOctalString(value).toUpperCase());
			HEX.append("0x"+Long.toHexString(value).toUpperCase());
		}
		else if(BITS==16) {
			BIN.append("\nЧисло 16 битное!<диапазон -32,768 +32,767>\nBIN 16bits: "+
					BIN.getText().substring(17,22)+" "+BIN.getText().substring(22,26)
					+BIN.getText().substring(26,31)+" "+BIN.getText().substring(31));
			OCT.append(Long.toOctalString(value).toUpperCase());
			HEX.append("0x"+Long.toHexString(value).toUpperCase());
		}
		else if(BITS==24) {
			BIN.append("\nЧисло 24 битное!<диапазон -8,388,608 +8,388,607>\nBIN 24bits: "+
					BIN.getText().substring(8,13)+" "+BIN.getText().substring(13,17)
					  +BIN.getText().substring(17,22)+" "+BIN.getText().substring(22,26)
					   +BIN.getText().substring(26,31)+" "+BIN.getText().substring(31));
			OCT.append(Long.toOctalString(value).toUpperCase());
			HEX.append("0x"+Long.toHexString(value).toUpperCase());
		}
		else if(BITS==32) {
			BIN.append("\nЧисло 32 битное!<диапазон -2,147,483,648 +2,147,483,647>\nBIN 32bits: "+
					BIN.getText().substring(0,4)+" "+BIN.getText().substring(4,8)
						+BIN.getText().substring(8,13)+" "+BIN.getText().substring(13,17)
							+BIN.getText().substring(17,22)+" "+BIN.getText().substring(22,26)
								+BIN.getText().substring(26,31)+" "+BIN.getText().substring(31));
			OCT.append(Long.toOctalString(value).toUpperCase());
			HEX.append("0x"+Long.toHexString(value).toUpperCase());
		}
		else if(BITS==64) {
			BIN.append("\nЧисло 64 битное!<диапазон  -9,223,372,036,854,775,808 +9,223,372,036,854,775,808>\nBIN 64bits: "+
				BIN.getText().substring(0,4)+" "+BIN.getText().substring(4,8)
					+BIN.getText().substring(8,13)+" "+BIN.getText().substring(13,17)
						+BIN.getText().substring(17,22)+" "+BIN.getText().substring(22,26)
							+BIN.getText().substring(26,31)+" "+BIN.getText().substring(31,35)
			+BIN.getText().substring(35,40)+" "+BIN.getText().substring(40,44)
				+BIN.getText().substring(44,49)+" "+BIN.getText().substring(49,53)
					+BIN.getText().substring(53,58)+" "+BIN.getText().substring(58,62)
					+BIN.getText().substring(62,67)+" "+BIN.getText().substring(67));
						
			OCT.append(Long.toOctalString(value).toUpperCase());
			HEX.append("0x"+Long.toHexString(value).toUpperCase());
		}
		//else BIN.append("\nДанное число имеет разрядность более 64 БИТ!!! То есть число не входит в диапазон -9223372036854775808+9223372036854775807");
		
	
		
	}//END BIN Converting
}//end class
