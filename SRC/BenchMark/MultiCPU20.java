package BenchMark;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JTextField;

public class MultiCPU20 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] a,orig_a;
	
	MultiCPU20(int[] a,JTextField j){
		orig_a=a;
		this.a=Arrays.copyOf(orig_a, orig_a.length);
		jtf=j;
		//������ 10
		System.out.println("----------------------------\n�������� ������������������: ");
		for(int i=0;i<10;i++)
			System.out.print(a[i]+" ");
		//� ������ ���������� ������� ����� ���������� ����� 10 �������� ������ Compute()
		
		new RUNNER(this,jtf,20);
	}

public	long Compute() {	
//�������� ���������� ������� ��������
		System.out.println("\n----------------------------\n��������������� ������������������ ������� Fork/Join framework QuickSort. ����� �����: (Test 20)");
		ForkJoinPool fjp=new ForkJoinPool();
		MultiCore10 task=new MultiCore10(a,0,a.length-1);
//������� ������� ���������� ��������� ��������
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
//**************��� ���������� ������� �����****************
		fjp.invoke(task);
//**************/��� ���������� ������� �����****************		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(a[i]+" ");
		System.out.println("\n����� ����������� �� ����� �������� �������� Fork/Join framework � ����-��������: "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������");
		RUNNER.nano=(t2nano-t1nano);
		a=null;
		a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}//end Compute
}