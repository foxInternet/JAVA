package BenchMark;

import java.util.LinkedList;
import javax.swing.JTextField;
import java.util.stream.*;

public class MultiCPU18 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> ll_stream_int;
	private LinkedList<Long> ll_long=new LinkedList<Long>();
	MultiCPU18(LinkedList<Integer> ll,JTextField j){
		ll_stream_int=ll;
		ll_stream_int.forEach((x) -> ll_long.add((long) x));
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ Parallel ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");
		
		new RUNNER(this,jtf,18);
	}
public long Compute() {
	System.out.println("\n----------------------------\n���������� � ������ reduce() � ������� ������ Parallel Stream<Long>: (Test 18)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//�������� ����� ���� �������� ������� 
	//��� ����������� �������� ����� � ������������ ������ ���� ������� �� ������ 
	//<U> U reduce (U ��������_������������ ,BiFunction<U ,? super �, U>���������� BinaryOperator<U> ������������)
	long streamLongSum=ll_long.parallelStream().reduce(0L, 			//identity
													  (a,b) -> a+b, //accumulator
													  (a,b) -> a+b);//combine
		System.out.printf("�����  ��������  ������� LinkedList<Long> ll_long = %,d",streamLongSum);
	//�������� ������������ ���� �������� ���������� ������ ������� (������ ������. 1 - ����� ��������. 0 - ����� ����������)
	//****************��� ������������ ���������� ������ �����C� ������***************
	Stream<Long> cbrtROOTprod=ll_long.parallelStream().map( (i) -> {
							if(i==0)
								i=1L;
							return (long)Math.cbrt(i); //��������� ��� � ���������� �����
	});
	long longProduct=cbrtROOTprod.reduce(1L,           		   									   //identity
													(a,b) -> a*b,
													(a,b) -> a*b );    	   									  
	System.out.printf("\n������������ ���������� ������ ������� LinkedList<Long> ll_long = %,d", longProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Parallel Stream<Integer>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}
	
}
