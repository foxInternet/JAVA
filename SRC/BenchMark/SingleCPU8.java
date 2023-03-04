package BenchMark;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.JTextField;

public class SingleCPU8 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> ll_stream_int;
	private LinkedList<Long> ll_long=new LinkedList<Long>();
	SingleCPU8(LinkedList<Integer> ll,JTextField j){
		ll_stream_int=ll;
		ll_stream_int.forEach( (x) -> ll_long.add((long)x));
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");	
		
		new RUNNER(this,jtf,8);
	}
public long Compute() {
	/* 2 �������� reduce
	 * 		Optional<T> reduce ( B inaryOperator<T> ���������� )
			� reduce(T ��������_������������ , BinaryOperator<T> ����������) 
	 */
	System.out.println("\n----------------------------\n���������� � ������ reduce() � ������� ������ Stream<Long>: (Test 8)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime(); 
	//�������� ����� ���� �������� ������� 
	Optional<Long> streamLongSum=ll_long.stream().reduce( (a, b) -> (long)(a+b) );
	if(streamLongSum.isPresent()) //��� ��� ������� �� ������� int - ��������� long ���
		System.out.printf("�����  ��������  ������� LinkedList<Long> ll_long = %,d",streamLongSum.get());
	//�������� ������������ ���� �������� ���������� ������ ������� (������ ������. 1 - ����� ��������. 0 - ����� ����������)
	Stream<Long> cbrtROOTprod=ll_long.stream().map( (i) -> {
		if((long)Math.cbrt(i)==0)
			return 1L;
		else
			return (long)Math.cbrt(i); //��������� ��� � ���������� �����
	});
	long longProduct=cbrtROOTprod.reduce(1L,(a, b) -> a*b);
		System.out.printf("\n������������ ���������� ������ ������� LinkedList<Long> ll_long = %,d", longProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Stream<Integer>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
	}
	
}

