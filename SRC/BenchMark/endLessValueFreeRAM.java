package BenchMark;
import org.hyperic.sigar.CpuInfo;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.swing.JTextArea;
/*
 * 		CpuInfo=String.format(
				  "\t\t���������� � �������:\n"
				+ "OS:\t\t"+osName+"  "+osArch+"\n���������:\t\t%1$s %2$s\n"
				+ " ���������� ����:\t%3$d\n"
				+ " �������� �������:\t%4$.1f ���\n"
				+ " ����� ����:\t\t%5$d\n"
				+ " ����� RAM:\t\t%6$,d  MBytes\n"
				+ " �������� RAM:\t%7$,d  MBytes"
					,vendor,model,cores,mhz,totalCores,totalRAM,freeRAM);
 */
public class endLessValueFreeRAM implements Runnable{
	private CpuInfo cpu;
	private double mhz;
	private JTextArea textCPU;
	private MBeanServer mbserver;
	private Object attr;
	private long freeRAM;
	private String str;
	endLessValueFreeRAM(MBeanServer mb,JTextArea jta,CpuInfo ci){
		mbserver=mb;
		textCPU=jta;
		cpu=ci;
	}
	public void run() {
		
		try {
			while(true) {
				Thread.sleep(1000);
				attr=mbserver.getAttribute(new ObjectName("java.lang","type","OperatingSystem"),"FreePhysicalMemorySize");
				freeRAM=Long.parseLong(attr.toString())/1024/1024;
				str=String.format("%,d  ",freeRAM);
				mhz=cpu.getMhz();
				//��� ���� ����� �������, ��� ������ �������� ��������� ������, � ������ ������� start � end
					StringBuilder sb=new StringBuilder(textCPU.getText());
					int indexStart=sb.lastIndexOf("RAM:")+5;
					int indexEnd=sb.lastIndexOf("MBytes");
					textCPU.replaceRange(str, indexStart, indexEnd);
				//��� ������ ��� ��������� Mhz
					indexStart=sb.lastIndexOf("����:")+6;
					indexEnd=sb.lastIndexOf(" ���");
					textCPU.replaceRange(String.valueOf(String.format("%,.1f", mhz)), indexStart, indexEnd);
				
			}
		}catch(InterruptedException | 
				InstanceNotFoundException | 
				  AttributeNotFoundException | 
				    MalformedObjectNameException | 
				      ReflectionException | 
				        MBeanException | 
				          ArrayIndexOutOfBoundsException err0) {err0.printStackTrace();}
	}

}
