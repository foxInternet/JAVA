package BenchMark;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JTextField;

public class MultiCPU15 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	MultiCPU15(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ double_copy15: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,15);
	}
	public long Compute() {
		ForkJoinPool fjp=new ForkJoinPool();
		MultiCore5 task=new MultiCore5(copy_a_double,0,copy_a_double.length);
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		
		fjp.invoke(task);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		System.out.println("\n����� ��������������� ������������������ � ��������� �� 3-� ������ ����� ������� (Test 15)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\n����� ������������� ���������� = "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}