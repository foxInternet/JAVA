package BenchMark;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.DoubleStream;
import javax.swing.JTextField;

public class MultiCPU17 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	private double min,max;
	MultiCPU17(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ double_copy17: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,17);
	}
	public long Compute() {
		ForkJoinPool fjp=new ForkJoinPool();
		//��������� ����������� ��������
		DoubleStream ds=Arrays.stream(copy_a_double).parallel();
		OptionalDouble minStream=ds.min();
		if(minStream.isPresent())
			min=minStream.getAsDouble();
		//��������� ������������ ��������
		ds=Arrays.stream(copy_a_double).parallel();
		OptionalDouble maxStream=ds.max();
		if(maxStream.isPresent())
			max=maxStream.getAsDouble();
		System.out.print("\nMIN="+min+" MAX="+max+"\n");
		MultiCore7 task=new MultiCore7(copy_a_double,0,copy_a_double.length,min,max);
		
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();

		fjp.invoke(task);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
//����� ��������� �������� - ������� �� ����� ������ 10 ��������. ��� ���� ������ ������ ����������� �����
		System.out.println("\n����� ��������������� ������������������ � ��������� �� 3-� ������ ����� ������� (Test 17)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\n����� ������������� ���������� = "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}
