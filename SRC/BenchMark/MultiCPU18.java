package BenchMark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	Stream<Double> ROOTprod=ll_long.parallelStream().map( (i) -> Math.sqrt(Math.sqrt(i)) ); //��������� ��� � ���������� �����
	//LongStream cbrtROOTprod=ll_long.parallelStream().mapToLong( (i) -> {
	//															if(i==0)
	//																return 1;
	//															return (long)Math.sqrt(Math.sqrt(i));
	//															}); //��������� ��� � ���������� �����
	//long longProduct=cbrtROOTprod.reduce((a,b) -> {1L,(a,b) -> a*b, (a,b) -> a*b );
	double doubleProduct=ROOTprod.reduce(1.0,(a,b) ->  ((a*b)/1_00)+Math.PI, (a,b) -> a*b );	
	// <R, �> R collect(Collector<? super �,�,R> �������_����������)
		// interface Collector<T,�,R>
		// static <�> Collector<T,?,List<T>> t oList()
		// static <�> Collector<T,?,Set<T>> toSet() 
		// � ������ ������� ���������� �� ������ � ������ ArrayList � � ��������� TreeSet
		Stream <Long> ROOTprod2=ll_long.parallelStream().map( (i) -> (long)Math.sqrt(i) );
		Set<Long> mySet=ROOTprod2.collect(Collectors.toSet());  //��������� ��������
		TreeSet<Long> ts=new TreeSet<>(mySet);
		
		ROOTprod2=ll_long.parallelStream().map( (i) -> (long)Math.sqrt(i) );
		List<Long> myList=ROOTprod2.collect(Collectors.toList());//��������� ��������
		ArrayList<Long> al=new ArrayList<>(myList);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		System.out.printf("\n������������  ���������� ������ �� ������� ((a*b)/1_00)+Math.PI  ������ Stream<Double> = %,f", doubleProduct);
		System.out.println("\n�������� ArrayList<> ����� ��������������� �� ������: ");
			for(int i=0;i<40;i++) 
				System.out.print(al.get(i)+" ");
		System.out.println("\n�������� TreeSet<> ����� ��������������� �� ������: ");
			Iterator<Long> it=ts.iterator(); //descendingIterator() - �������� ������
			int c=0;
			while(it.hasNext()) {
				if(c>=0 && c<40)
					System.out.print(it.next()+" ");
				c++;
			}
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Parallel Stream<Integer>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}
	
}
