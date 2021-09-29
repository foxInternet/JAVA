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
	private BoundedRangeModel model; //Модель ПрогрессБара
	private JProgressBar progress;
	private BoundedRangeModel modelAll; //Модель прогрессбара общая
	private JProgressBar progressAll; //Общий прогрессБар
	private boolean CUT;//Если True - то CUT(F6), если False, то Copy(F5)
	private boolean multiCopy;
	private int[] indexSelected;//массив индексов выделенных файлов
	
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
				sourceLabel=new JLabel("Копировать "+sourcePathFile[0].getAbsolutePath()+" в:");
				ok=new JButton("Копировать");	
			}
			else if(multiCopy) {
				sourceLabel=new JLabel("<HTML>Копировать "+sourcePathFile[0].getAbsolutePath()+"<br> и другие выделенные файлы в: <br></HTML>");
				ok=new JButton("Копировать выделенное");	
			}
		}
		if(CUT) {
			if(!multiCopy) {
				sourceLabel=new JLabel("Переместить "+sourcePathFile[0].getAbsolutePath()+" в:");
				ok=new JButton("Переместить");
			}
			else if(multiCopy) {
				sourceLabel=new JLabel("<HTML>Переместить "+sourcePathFile[0].getAbsolutePath()+"<br> и другие выделенные файлы  в: <br></HTML>");
				ok=new JButton("Переместить");
			}
		}
		panel1.add(sourceLabel);
		panel2.add(targetPath);
		ok.addActionListener(new actionListener());
		cancel=new JButton("Отменить");
		cancel.addActionListener((event)->{
			if(cancel.getText().equals("Отменить")) {
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
			COPYLabel=new JLabel("Копирование");
			timeLabel=new JLabel("Время копирования файла = ");
		}
		else if(multiCopy) {
			COPYLabel=new JLabel("Копирование выделенных файлов");
			timeLabel=new JLabel("Общее время копирования файлов = ");
		}
		COPYLabel.setFont(new Font("arial",Font.BOLD,18));
		sourceLabel=new JLabel("Копировать из: "+sourcePathFile[0].getAbsolutePath());
		targetLabel=new JLabel("Копировать в: "+targetPathFile[0].getAbsolutePath());
	}
	else if(CUT) {
		COPYLabel=new JLabel("Перемещение");
		COPYLabel.setFont(new Font("arial",Font.BOLD,18));
		COPYLabel.setForeground(Color.red);
		timeLabel=new JLabel("Время перемещение файла = ");
		sourceLabel=new JLabel("Переместить из: "+sourcePathFile[0].getAbsolutePath());
		targetLabel=new JLabel("Переместитьь в: "+targetPathFile[0].getAbsolutePath());
	}
	panel1=new JPanel();
	panel2=new JPanel(new GridLayout(5,1));
	panel3=new JPanel();
	panel1.add(COPYLabel);
	panel2.add(timeLabel);
	panel2.add(sourceLabel);
	panel2.add(targetLabel);
//*******************JProgressBar******************************************
	//Создание стандартной модели
	model=new DefaultBoundedRangeModel(1,0,0,100);//тек значение = 1
	modelAll=new DefaultBoundedRangeModel(1,0,0,100);//тек значение = 1
	progress=new JProgressBar(model);
	progressAll=new JProgressBar(modelAll);
	progress.setStringPainted(true);
	progressAll.setStringPainted(true);
	
//*************************************************************************	
	panel2.add(progress);
	panel2.add(progressAll);
	cancel=new JButton("Отменить");
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
		
//Формирование значения sourceSizeAll для общего PorgressBar при множественном копировании файлов
		countFilesAll=sourcePathFile.length;
	
//********************************************массив КОПИРОВАНИЯ ФАЙЛОВ***************************************************************************		
		for(int i=0;i<sourcePathFile.length;i++) { 
		long sourceSize=sourcePathFile[i].length()/(100);//Если файл один - то ничего по сути не меняется
		long sSize=sourceSize;
		System.out.println("SizeSource="+sourcePathFile[i].length());
		System.out.println("SizeTarget="+targetPathFile[i].length());
		//использую try/catch с ресурсами для автозакрытия ресурсов!!!!!!!!!!!!!!!!!!!!!!!!
		try	(FileInputStream fis=new FileInputStream(sourcePathFile[i].getAbsoluteFile());
			 BufferedInputStream bis=new BufferedInputStream(fis);
			 FileOutputStream fos=new FileOutputStream(targetPathFile[i].getAbsoluteFile());
			 BufferedOutputStream bos=new BufferedOutputStream(fos))
		{
//Размер буфера 8192 байта, 8 кб	
		timeStart=(double)System.currentTimeMillis();
		while((BYTES=bis.read())!=-1){	
//**********************Запись файла**************************************************				
					bos.write(BYTES);		
					count++;
//**************************ProgressBar***********************************************	
							//while(model.getValue()<model.getMaximum()) {}
						if(count==sourceSize) {
							model.setValue(model.getValue()+1);
							sourceSize+=sSize;
						    }
						else if(sourceSize<10) { //Т.к. буферизованное чтение и запись идет по умолчанию 8192 байта, то 100 байт и меньше записывается в один проход
							model.setValue(model.getValue()+100);
						}
					}//end while
			modelAll.setValue(modelAll.getValue()+100/countFilesAll);
			bos.flush();
			fis.close();
			}catch(FileNotFoundException err0) {
				
//*****************Если ошибка, то выводим фрейм с ошибкой на экран*******************
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
		}//end for - конец цикла перебора выделенных файлов (если файл выделен один, то цикл будет 1
		cancel.setText("OK");
		time=(double)(System.currentTimeMillis()-timeStart)/1000;
		timeLabel.setText("Время копирования = "+String.format("%,.3f", time)+" сек.");
		System.out.println("Время копирования ="+time+" секунд");
		if(!CUT) totalCommander.endCopy();
		if(CUT) {//Нужно ЗАКРЫТЬ файл-источник - для его удаления!!!!!!!!!!
			totalCommander.endCut(indexSelected);
		}
		}//end run
	}//end PROGRESS
}//end class
