package BenchMark;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JTextField;

public class MultiCPU11 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] a,orig_a;
	
	MultiCPU11(int[] a,JTextField j){
		orig_a=a;
		jtf=j;
		this.a=Arrays.copyOf(orig_a, orig_a.length);
		//������ 10
		System.out.println("----------------------------\n�������� ������������������: ");
		for(int i=0;i<10;i++)
			System.out.print(a[i]+" ");
		//� ������ ���������� ������� ����� ���������� ����� 10 �������� ������ Compute()
		new RUNNER(this,jtf,11);
	}
public long Compute() {	
//�������� ���������� ������� ��������
		System.out.println("\n----------------------------\n��������������� ������������������ ���������� ������� ��������:(Test 11) ");
//������� ������� ���������� ��������� �������� ��������� Fork\Join RecursiveAction
		MultiCore1 task=new MultiCore1(a,0,a.length);
		ForkJoinPool fjp=new ForkJoinPool();
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		fjp.invoke(task);	
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_005;i++)
			System.out.print(a[i]+" ");
		for(int i=50_500;i<50_505;i++)
			System.out.print(a[i]+" ");
		System.out.println("\n����� ����������� �� ���������� ����� �������� � ����-��������: "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������");
		RUNNER.nano=(t2nano-t1nano);
		a=null;
		a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}//end Compute
}