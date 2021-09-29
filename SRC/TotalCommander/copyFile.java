package TotalCommander;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class copyFile {
	private JFrame frame;
	private JFrame frameError;
	private double time;
	private static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	private static int x=400,y=230;
	private static int w=(int)screen.getWidth();
	private static int h=(int)screen.getHeight();
	private static int x1=(w-x)/2;
	private static int y1=(h-y)/2;
	private JButton ok;
	private JButton cancel;
	private JLabel COPYLabel;
	private JLabel timeLabel;
	private JLabel sourceLabel;
	private JLabel targetLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JTextField targetPath;
	private File[] sourcePathFile;
	private File[] targetPathFile;
	private BoundedRangeModel model; //������ ������������
	private JProgressBar progress;
	private BoundedRangeModel modelAll; //������ ������������ �����
	private JProgressBar progressAll; //����� �����������
	private boolean CUT;//���� True - �� CUT(F6), ���� False, �� Copy(F5)
	private boolean multiCopy;
	private int[] indexSelected;//������ �������� ���������� ������
	
	copyFile(File[] source,File target,boolean cutOrCopy,boolean multi,int [] index){
		indexSelected=index;
		multiCopy=multi;
		CUT=cutOrCopy;
		System.out.println("File source="+source.toString()+"\nFile target="+target.toString());
		sourcePathFile=source;
		targetPathFile=new File[sourcePathFile.length];
//*************************Create targetPathFile*******************************
		for(int i=0;i<sourcePathFile.length;i++) {
			if(sourcePathFile[i].isFile()) 
				targetPathFile[i]=new File(target.getAbsoluteFile()+"\\"+sourcePathFile[i].getName());
			if(sourcePathFile[i].isDirectory())
				targetPathFile[i]=new File(target.getAbsolutePath()+"\\"+sourcePathFile[i].getName());
			System.out.println("File source="+sourcePathFile.toString()+"\nFile target="+targetPathFile.toString());
		}
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		frame=new JFrame(totalCommander.VERSION);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		targetPath=new JTextField(30);
		targetPath.setText(targetPathFile[0].getAbsolutePath());
		if(!CUT) {
			if(!multiCopy) {
				sourceLabel=new JLabel("���������� "+sourcePathFile[0].getAbsolutePath()+" �:");
				ok=new JButton("����������");	
			}
			else if(multiCopy) {
				sourceLabel=new JLabel("<HTML>���������� "+sourcePathFile[0].getAbsolutePath()+"<br> � ������ ���������� ����� �: <br></HTML>");
				ok=new JButton("���������� ����������");	
			}
		}
		if(CUT) {
			if(!multiCopy) {
				sourceLabel=new JLabel("����������� "+sourcePathFile[0].getAbsolutePath()+" �:");
				ok=new JButton("�����������");
			}
			else if(multiCopy) {
				sourceLabel=new JLabel("<HTML>����������� "+sourcePathFile[0].getAbsolutePath()+"<br> � ������ ���������� �����  �: <br></HTML>");
				ok=new JButton("�����������");
			}
		}
		panel1.add(sourceLabel);
		panel2.add(targetPath);
		ok.addActionListener(new actionListener());
		cancel=new JButton("��������");
		cancel.addActionListener((event)->{
			if(cancel.getText().equals("��������")) {
				frame.dispose();
				frame.setVisible(false);
				cancel.setText("canceled");
				targetPathFile[targetPathFile.length].delete();
				
			}
			else if(cancel.getText().equals("OK")) {
				frame.dispose();
				frame.setVisible(false);
			}
									
								}
							 );
		panel3.add(ok);
		panel3.add(cancel);
	
		frame.getContentPane().add(BorderLayout.NORTH,panel1);
		frame.getContentPane().add(BorderLayout.CENTER,panel2);
		frame.getContentPane().add(BorderLayout.SOUTH,panel3);
		frame.setBounds(x1, y1, x, y);
		frame.setVisible(true);
		frame.setResizable(false);
	}
private class actionListener implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==ok) {
					frame.dispose();
					frame.setVisible(false);
					copyStart(sourcePathFile, targetPathFile);
				}
	}//end actionPerformed
}//end actionListener
private void copyStart(File[] source,File[] target) {
	frame=new JFrame(totalCommander.VERSION);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	if(!CUT) {
		if(!multiCopy) {
			COPYLabel=new JLabel("�����������");
			timeLabel=new JLabel("����� ����������� ����� = ");
		}
		else if(multiCopy) {
			COPYLabel=new JLabel("����������� ���������� ������");
			timeLabel=new JLabel("����� ����� ����������� ������ = ");
		}
		COPYLabel.setFont(new Font("arial",Font.BOLD,18));
		sourceLabel=new JLabel("���������� ��: "+sourcePathFile[0].getAbsolutePath());
		targetLabel=new JLabel("���������� �: "+targetPathFile[0].getAbsolutePath());
	}
	else if(CUT) {
		COPYLabel=new JLabel("�����������");
		COPYLabel.setFont(new Font("arial",Font.BOLD,18));
		COPYLabel.setForeground(Color.red);
		timeLabel=new JLabel("����� ����������� ����� = ");
		sourceLabel=new JLabel("����������� ��: "+sourcePathFile[0].getAbsolutePath());
		targetLabel=new JLabel("������������ �: "+targetPathFile[0].getAbsolutePath());
	}
	panel1=new JPanel();
	panel2=new JPanel(new GridLayout(5,1));
	panel3=new JPanel();
	panel1.add(COPYLabel);
	panel2.add(timeLabel);
	panel2.add(sourceLabel);
	panel2.add(targetLabel);
//*******************JProgressBar******************************************
	//�������� ����������� ������
	model=new DefaultBoundedRangeModel(1,0,0,100);//��� �������� = 1
	modelAll=new DefaultBoundedRangeModel(1,0,0,100);//��� �������� = 1
	progress=new JProgressBar(model);
	progressAll=new JProgressBar(modelAll);
	progress.setStringPainted(true);
	progressAll.setStringPainted(true);
	
//*************************************************************************	
	panel2.add(progress);
	panel2.add(progressAll);
	cancel=new JButton("��������");
	cancel.addActionListener((event)->{
								frame.dispose();
								frame.setVisible(false);
							}
						 );
	panel3.add(cancel);
	frame.getContentPane().add(BorderLayout.NORTH,panel1);
	frame.getContentPane().add(BorderLayout.CENTER,panel2);
	frame.getContentPane().add(BorderLayout.SOUTH,panel3);
	frame.setBounds(x1, y1, x, y);
	frame.setVisible(true);
	frame.setResizable(false);
	Thread thread=new Thread(new PROGRESS());
	thread.start();
}
private class PROGRESS implements Runnable{
	public void run() {
		int BYTES;
		double timeStart=0;
		long count=0;
	//	long countAll=0;
		int countFilesAll=0;
		
//������������ �������� sourceSizeAll ��� ������ PorgressBar ��� ������������� ����������� ������
		countFilesAll=sourcePathFile.length;
	
//********************************************������ ����������� ������***************************************************************************		
		for(int i=0;i<sourcePathFile.length;i++) { 
		long sourceSize=sourcePathFile[i].length()/(100);//���� ���� ���� - �� ������ �� ���� �� ��������
		long sSize=sourceSize;
		System.out.println("SizeSource="+sourcePathFile[i].length());
		System.out.println("SizeTarget="+targetPathFile[i].length());
		//��������� try/catch � ��������� ��� ������������ ��������!!!!!!!!!!!!!!!!!!!!!!!!
		try	(FileInputStream fis=new FileInputStream(sourcePathFile[i].getAbsoluteFile());
			 BufferedInputStream bis=new BufferedInputStream(fis);
			 FileOutputStream fos=new FileOutputStream(targetPathFile[i].getAbsoluteFile());
			 BufferedOutputStream bos=new BufferedOutputStream(fos))
		{
//������ ������ 8192 �����, 8 ��	
		timeStart=(double)System.currentTimeMillis();
		while((BYTES=bis.read())!=-1){	
//**********************������ �����**************************************************				
					bos.write(BYTES);		
					count++;
//**************************ProgressBar***********************************************	
							//while(model.getValue()<model.getMaximum()) {}
						if(count==sourceSize) {
							model.setValue(model.getValue()+1);
							sourceSize+=sSize;
						    }
						else if(sourceSize<10) { //�.�. �������������� ������ � ������ ���� �� ��������� 8192 �����, �� 100 ���� � ������ ������������ � ���� ������
							model.setValue(model.getValue()+100);
						}
					}//end while
			modelAll.setValue(modelAll.getValue()+100/countFilesAll);
			bos.flush();
			fis.close();
			}catch(FileNotFoundException err0) {
				
//*****************���� ������, �� ������� ����� � ������� �� �����*******************
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						frame.dispose();
						frame.setVisible(false);
						frameError=new JFrame(totalCommander.VERSION);
						frameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						JLabel labelError=new JLabel(err0.getLocalizedMessage());
						JButton okError=new JButton("OK");
						okError.addActionListener((e)->{
							frameError.dispose();
							frameError.setVisible(false);
						});
						JPanel panelError=new JPanel();
						panelError.add(okError);
						frameError.getContentPane().add(BorderLayout.NORTH,labelError);
						frameError.getContentPane().add(BorderLayout.SOUTH,panelError);
						frameError.setBounds(x1, y1, x, y);
						frameError.setVisible(true);
						
					}
				});
				
			}
			 catch(IOException err) {err.printStackTrace();}
		}//end for - ����� ����� �������� ���������� ������ (���� ���� ������� ����, �� ���� ����� 1
		cancel.setText("OK");
		time=(double)(System.currentTimeMillis()-timeStart)/1000;
		timeLabel.setText("����� ����������� = "+String.format("%,.3f", time)+" ���.");
		System.out.println("����� ����������� ="+time+" ������");
		if(!CUT) totalCommander.endCopy();
		if(CUT) {//����� ������� ����-�������� - ��� ��� ��������!!!!!!!!!!
			totalCommander.endCut(indexSelected);
		}
		}//end run
	}//end PROGRESS
}//end class
