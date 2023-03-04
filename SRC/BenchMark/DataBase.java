package BenchMark;
import java.io.*;
import javax.swing.*;
import java.util.*;

//Класс, который пишет в файл данные о тестируемых компьютерах
public class DataBase {
	private JFrame frame;
	static File fPatch=new File("src\\BenchMark\\data\\");
	private File f;
	long[] mlsec,nsec;
	
	private String osName;
	private String osArch;
	private String vendor;
	private String model;
	private int cores;
	private double mhz;
	private int totalCores;
	private long totalRAM,freeRAM;
	
	DataBase(JFrame jf,long[] mls,long[] ns,String osN,String osAr,String ven,String mod, int cor,double mh,int totalCor,long totalRa,long freeRa){
		frame=jf;
		f=new File(fPatch.toString()+"\\"+mod+".txt");
		f.getParentFile().mkdir();//Создать одну папку
		osName=osN;
		osArch=osAr;
		vendor=ven;
		model=mod;
		cores=cor;
		mhz=mh;
		totalCores=totalCor;
		totalRAM=totalRa;
		freeRAM=freeRa;
		mlsec=mls;
		nsec=ns;
		writeToFile();
		System.out.println("Создан файл "+f.getAbsolutePath());
	}
//Если файл сущестует, то он удалится и создастся ЗАНОВО!!!! С НУЛЯ!!!
	private void writeToFile(){ 
try(BufferedWriter bw=new BufferedWriter(new FileWriter(f))){
		bw.write(model+"\n");
		bw.write("mlsec:"+Arrays.toString(mlsec)+"\n");
		bw.write("nsec:"+Arrays.toString(nsec)+"\n");
		bw.write("#\n");//STOP для парсинга милисекунд и наносекунд для East panel
		bw.write("osName:["+osName+"]\n");
		bw.write("osArch:["+osArch+"]\n");
		bw.write("vendor:["+vendor+"]\n");
		bw.write("model:["+model+"]\n");
		bw.write("cores:["+cores+"]\n");
		bw.write("mhz:["+mhz+"]\n");
		bw.write("totalCores:["+totalCores+"]\n");
		bw.write("totalRAM:["+totalRAM+"]\n");
		bw.write("freeRAM:["+freeRAM+"]");
		}catch(IOException error) {
			error.printStackTrace();
			JOptionPane.showMessageDialog(frame, error);
		}
	}
	
}
